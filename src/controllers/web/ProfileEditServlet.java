package controllers.web;

import business.BookListing;
import business.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;
import util.DigestHelper;
import util.RequestHelper;

public class ProfileEditServlet extends HttpServlet {
    private String dbConfigResource;
    private String jspPath;
    private String[] requiredFields;
    private BooksZenBooks bzb;

    /**
     * Initializes the servlet and sets up required instance variables.
     */
    @Override
    public void init() throws ServletException {
        super.init();

        dbConfigResource = getServletContext().getInitParameter("dbConfigResource");
        jspPath = getServletContext().getInitParameter("jspPath");
        requiredFields = getServletConfig().getInitParameter( "requiredFields" ).split( "," );
    }

    /**
     * Handles all incoming POST requests to the servlet.
     *
     * @param request The contents of the HTTP request.
     * @param response The contents of the HTTP response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        bzb = new BooksZenBooks( "en", dbConfigResource ); // @TODO language should be a request param
        String forwardUrl;
        String pageTitle;
        String action = RequestHelper.getString( "action", request );
        RequestDispatcher dispatcher;
        HashMap<String, String> formErrors;
        User authUser = bzb.getAuthenticatedUser( request );

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "subject" );
        bzb.getLexicon().load( "register" );
        bzb.getLexicon().load( "ucp" );

        if( authUser == null ) {
            bzb.getLexicon().load( "error" );
            bzb.getLexicon().load( "register" );

            forwardUrl = jspPath + "401.jsp";
            pageTitle = bzb.getLexicon().get( "unauthorized" );
        }
        else if( action.equals( "save" ) ) {
            formErrors = checkForm( request );

            if( !formErrors.isEmpty() ) {
                bzb.getLexicon().load( "register" );

                forwardUrl = jspPath + "editProfile.jsp";
                pageTitle = bzb.getLexicon().get( "editProfile" );

                request.setAttribute( "countries", getCountries() );
                request.setAttribute( "formErrors", formErrors );
            }
            else {
                pageTitle = bzb.getLexicon().get( "profileUpdated" );
                forwardUrl = jspPath + "editProfileSuccess.jsp";
                
                request.setAttribute( "user", saveUser( request ) );
            }
        }
        else {
            bzb.getLexicon().load( "register" );

            forwardUrl = jspPath + "editProfile.jsp";
            pageTitle = bzb.getLexicon().get( "editProfile" );

            request.setAttribute( "countries", getCountries() );
            request.setAttribute( "user", authUser );
        }

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "subjects", bzb.getSubjects() );
        request.setAttribute( "pageTitle", pageTitle );

        /* Set up forward and display JSP */
        dispatcher = getServletContext().getRequestDispatcher( forwardUrl );

        dispatcher.forward( request, response );
    }

    /**
     * Handles all incoming GET requests to the servlet.
     *
     * @param request The contents of the HTTP request.
     * @param response The contents of the HTTP response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doPost( request, response );
    }

    /**
     * Gets a list of ISO country codes country names.
     *
     * @return An ArrayList of string arrays. Index 0 contains the ISO country code
     * and index 1 contains the country name.
     */
    private ArrayList<String[]> getCountries() {
        ArrayList<String[]> countries = new ArrayList<String[]>();
        String[] countryData;

        // @TODO sorting countries

        for( Locale locale : Locale.getAvailableLocales() ) {
            if( !locale.getDisplayCountry().isEmpty() ) {
                countryData = new String[] { locale.getCountry(), locale.getDisplayCountry() };
                countries.add( countryData );
            }
        }

        return countries;
    }

    private HashMap<String, String> checkForm( HttpServletRequest request ) {
        HashMap<String, String> errors = new HashMap<String, String>();
        String newEmail = RequestHelper.getString( "newEmail", request );
        String currentPassword = RequestHelper.getString( "currentPassword", request );
        String newPassword = RequestHelper.getString( "newPassword", request );
        String newPasswordConfirm = RequestHelper.getString( "newPasswordConfirm", request );

        /* Check that required fields are filled in. */
        for( String fieldName : requiredFields ) {
            if( RequestHelper.getString( fieldName, request ).isEmpty() ) {
                errors.put( fieldName, bzb.getLexicon().get( "emptyField", new String[][] { { "field", bzb.getLexicon().get( fieldName ) } } ) );
            }
        }

        /* Make sure the email address is valid and unregistered. */
        if( !newEmail.isEmpty() ) {
            if( !isValidEmail( newEmail ) ) {
                errors.put( "newEmail", bzb.getLexicon().get( "emailInvalid", new String[][] {
                    { "validEmails", bzb.getConfig().get( "validEmailDomains" ).replace( "\n", ", ") }
                } ) );
            }
            else if( isEmailRegistered( newEmail ) ) {
                errors.put( "newEmail", bzb.getLexicon().get( "emailRegistered", new String[][] { { "email", newEmail } } ) );
            }
        }
        

        /* Make sure the password is valid and matches the confirm field. */
        if( !newPassword.isEmpty() ) {
            if( !isValidPassword( newPassword ) ) {
                errors.put( "newPassword", bzb.getLexicon().get( "emptyField", new String[][] { { "field", bzb.getLexicon().get( "password" ) } } ) );
            }
            else if( !newPassword.equals( newPasswordConfirm ) ) {
                errors.put( "newPassword", bzb.getLexicon().get( "passwordNotMatch" ) );
            }

            if( !DigestHelper.md5( currentPassword ).equals( bzb.getAuthenticatedUser( request ).getPassword() ) ) {
                errors.put( "currentPassword", bzb.getLexicon().get( "passwordIncorrect" ) );
            }
        }


        /* Check that the birthdate is valid, if entered */
        if( !errors.containsKey( "birthDate" ) && util.Util.parseDate( RequestHelper.getString( "birthDate", request ) ) == null ) {
            errors.put( "birthDate", bzb.getLexicon().get( "birthDateInvalid" ) );
        }

        System.out.println( util.Util.parseDate( RequestHelper.getString( "birthDate", request ) ) );

        return errors;
    }

    private User saveUser( HttpServletRequest request ) {
        User authUser = bzb.getAuthenticatedUser( request, true );
        
        authUser.init( bzb.getDriver() );
        authUser.setAddress( RequestHelper.getString( "address", request ) );
        authUser.setBirthDate( util.Util.parseDate( RequestHelper.getString( "birthDate", request ) ) );
        authUser.setCity( RequestHelper.getString( "city", request ) );
        authUser.setFirstName( RequestHelper.getString( "firstName", request ) );
        authUser.setLastName( RequestHelper.getString( "lastName", request ) );
        authUser.setPhone( RequestHelper.getString( "phone", request ) );
        authUser.setPostalCode( RequestHelper.getString( "postalCode", request ) );
        authUser.setState( RequestHelper.getString( "state", request ) );

        if( !RequestHelper.getString( "newEmail", request ).isEmpty() ) {
            authUser.setEmail( RequestHelper.getString( "newEmail", request ) );
        }
        if( !RequestHelper.getString( "newPassword", request ).isEmpty() ) {
            authUser.setPassword( DigestHelper.md5( RequestHelper.getString( "newPassword", request ) ) );
        }

        authUser.save();

        return authUser;
    }

    private boolean isEmailRegistered( String email ) {
        String where = "email = '" + email + "'";
        String[] fields = { "COUNT(*) as count" };
        ResultSet result = bzb.getDriver().select( "user", fields, where );
        int count = 0;

        try {
            if( result.next() ) {
                count = result.getInt( "count" );
            }
        } catch( SQLException e ) {

        }

        return count > 0;
    }

    public boolean isValidPassword( String password ) {
        if( password.isEmpty() ) {
            return false;
        }

        return true;
    }

    public boolean isValidEmail( String email ) {
        String domains = bzb.getConfig().get( "validEmailDomains" ).replace( "\n", "|" );
        Pattern pattern = Pattern.compile( "^[A-Z0-9_+-]+(.[A-Z0-9_+-]+)*@(" + domains + ")$", Pattern.CASE_INSENSITIVE );
        Matcher matcher = pattern.matcher( email );

        if( email.isEmpty() ) {
            return false;
        }

        if( !matcher.find() ) {
            return false;
        }

        return true;
    }
}