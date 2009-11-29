package controllers.web;

import business.User;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import data.DBDriver;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;
import util.DigestHelper;
import util.RequestHelper;

public class RegisterServlet extends HttpServlet {
    private static String[] requiredFields;
    private static String dbConfigResource;
    
    @Override
    public void init() {
        ServletContext context = this.getServletContext();
        ServletConfig config = this.getServletConfig();
        dbConfigResource = context.getInitParameter( "dbConfigResource" );
        requiredFields = config.getInitParameter( "requiredFields" ).split( "," );
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
        BooksZenBooks bzb = new BooksZenBooks( "en", dbConfigResource ); // @TODO language should be a request param
        String action = RequestHelper.getValue( "action", request );
        String forwardUrl;
        RequestDispatcher dispatcher;
        HashMap<String, String> formErrors;
        User user;
        
        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "register" );

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );

        /* Already logged in? */
        if( bzb.getAuthenticatedUser( request ) != null ) {
            forwardUrl = "/index";
        }
        /* Handle registration request */
        else if( action.equals( "register" ) ) { 
            formErrors = checkRegistration( request, bzb );

            /* No form errors, create the user and display validation page */
            if( formErrors.isEmpty() ) {
                createUser( request, bzb.getDBDriver() );
                
                forwardUrl = "/validationCode.jsp";
            }
            /* Show form errors */
            else {
                forwardUrl = "/register.jsp";

                request.setAttribute( "formErrors", formErrors );
                request.setAttribute( "countries", getCountries() );
            }
        }
        /* Confirming an account */
        else if( action.equals( "confirm" ) ) {
            /* No email entered - display the form */
            if( request.getParameter( "email" ) == null ) {
                forwardUrl = "/validationCode.jsp";

                request.setAttribute( "pageTitle", bzb.getLexicon().get( "confirmAccount" ) );
            }
            /* Code and email match - update user and display success */
            else if( ( user = checkValidationCode( request, bzb.getDBDriver() ) ) != null ) {
                forwardUrl = "/registerSuccess.jsp";

                user.setValidated( true );
                user.save();

                request.setAttribute( "pageTitle", bzb.getLexicon().get( "registerSuccess" ) );
            }
            /* Show validation page with errors */
            else {
                forwardUrl = "/validationCode.jsp";

                request.setAttribute( "pageTitle", bzb.getLexicon().get( "confirmAccount" ) );
                request.setAttribute( "codeInvalid", true );
            }
        }
        /* Display the registration form */
        else {
            forwardUrl = "/register.jsp";

            request.setAttribute( "pageTitle", bzb.getLexicon().get( "registerAccount" ) );
            request.setAttribute( "countries", getCountries() );
        }
        
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

    /**
     *
     * @param email
     * @param allowedDomains
     * @return
     */
    public boolean isValidEmail( String email, String allowedDomains ) {
        String domains = allowedDomains.replace( "\n", "|" );
        Pattern pattern = Pattern.compile( "^[A-Z0-9_+-]+(.[A-Z0-9_+-]+)*@" + domains + "$", Pattern.CASE_INSENSITIVE );
        Matcher matcher = pattern.matcher( email );

        if( email.isEmpty() ) {
            return false;
        }

        if( !matcher.find() ) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param password
     * @return
     */
    public boolean isValidPassword( String password ) {
        if( password.isEmpty() ) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param birthDate
     * @return
     */
    public Date parseDate( String birthDate ) {
        SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy" );

        format.setLenient( false );

        try {
            return format.parse( birthDate );
        } catch( ParseException e ) {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public Hashtable checkRequiredFields() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param request
     * @param bzb
     * @return
     */
    private HashMap<String, String> checkRegistration( HttpServletRequest request, BooksZenBooks bzb ) {
        HashMap<String, String> errors = new HashMap<String, String>();
        String email = RequestHelper.getValue( "email", request );
        String password = RequestHelper.getValue( "password", request );
        String confirmPassword = RequestHelper.getValue( "confirmPassword", request );
        HashMap<String, String> replace = new HashMap<String, String>();

        /* Check that required fields are filled in. */
        for( String fieldName : requiredFields ) {
            if( RequestHelper.getValue( fieldName, request ).isEmpty() ) {
                replace = new HashMap();
                replace.put( "field", bzb.getLexicon().get( fieldName ) );
                errors.put( fieldName, bzb.getLexicon().get( "emptyField", replace ) );
            }
        }

        /* Make sure the email address is valid and unregistered. */
        if( !isValidEmail( email, bzb.getConfig().get( "validEmailDomains" ) ) ) {
            replace = new HashMap();
            replace.put( "field", bzb.getLexicon().get( "email" ) );
            errors.put( "email", bzb.getLexicon().get( "emptyField", replace ) );
        }
        else if( isEmailRegistered( email, bzb.getDBDriver() ) ) {
            replace = new HashMap();
            replace.put( "email", email );
            errors.put( "email", bzb.getLexicon().get( "emailRegistered", replace ) );
        }

        /* Make sure the password is valid and matches the confirm field. */
        if( !isValidPassword( password ) ) {
            replace = new HashMap();
            replace.put( "field", bzb.getLexicon().get( "password" ) );
            errors.put( "password", bzb.getLexicon().get( "emptyField", replace ) );
        }
        else if( !password.equals( confirmPassword ) ) {
            errors.put( "password", bzb.getLexicon().get( "passwordNotMatch" ) );
        }

        /* Check that the birthdate is valid, if entered */
        if( !errors.containsKey( "birthDate" ) && parseDate( RequestHelper.getValue( "birthDate", request ) ) == null ) {
            errors.put( "birthDate", bzb.getLexicon().get( "birthDateInvalid" ) );
        }

        /* Make sure the user has agreed to the terms */
        if( !RequestHelper.getValue( "agreeTerms", request ).equals( "on" ) ) {
            errors.put( "agreeTerms", bzb.getLexicon().get( "agreeTermsEmpty" ) );
        }

        System.out.println( "XXX:" + errors.get( "city" ) );
        
        /*if( RequestHelper.getValue( "firstName", request ).isEmpty() ) {
            replace = new HashMap();
            replace.put( "field", bzb.getLexicon().get( "firstName" ) );
            errors.put( "firstName", bzb.getLexicon().get( "invalidField", replace ) );
        }
        if( RequestHelper.getValue( "lastName", request ).isEmpty() ) {
            replace = new HashMap();
            replace.put( "field", bzb.getLexicon().get( "lastName" ) );
            errors.put( "lastName", bzb.getLexicon().get( "invalidField", replace ) );
        }
        if( !RequestHelper.getValue( "birthDate", request ).isEmpty() && parseDate( RequestHelper.getValue( "birthDate", request ) ) == null ) {
            replace = new HashMap();
            replace.put( "field", bzb.getLexicon().get( "birthDate" ) );
            errors.put( "birthDate", bzb.getLexicon().get( "invalidField", replace ) );
        }
        if( !RequestHelper.getValue( "agreeTerms", request ).equals( "on" ) ) {
            errors.put( "agreeTerms", bzb.getLexicon().get( "agreeTermsEmpty" ) );
        }*/
        
        return errors;
    }

    /**
     *
     * @param request
     * @param driver
     * @return
     */
    private boolean createUser( HttpServletRequest request, DBDriver driver ) {
        User user = new User();

        user.init( driver );

        user.setEmail( RequestHelper.getValue( "email", request ) );
        user.setPassword( DigestHelper.md5( RequestHelper.getValue( "password", request ) ) );
        user.setFirstName( RequestHelper.getValue( "firstName", request ) );
        user.setLastName( RequestHelper.getValue( "lastName", request ) );
        user.setAddress( RequestHelper.getValue( "address", request ) );
        user.setCity( RequestHelper.getValue( "city", request ) );
        user.setState( RequestHelper.getValue( "state", request ) );
        user.setPostalCode( RequestHelper.getValue( "postalCode", request ) );
        user.setCountry( RequestHelper.getValue( "country", request ) );
        user.setPhone( RequestHelper.getValue( "phone", request ) );
        user.setBirthDate( parseDate( RequestHelper.getValue( "birthDate", request ) ) );
        user.setJoinDate( new Date() );
        user.setValidationCode( generateCode() );
        user.setValidated( false );
        user.setSuperUser( false );

        if( user.save() ) {
            return true;
        }

        return false;
    }

    /**
     *
     * @param email
     * @param driver
     * @return
     */
    private boolean isEmailRegistered(String email, DBDriver driver ) {
        String where = "email = '" + email + "'";
        String[] fields = { "COUNT(*) as count" };
        ResultSet result = driver.select( "user", fields, where );
        int count = 0;

        try {
            if( result.next() ) {
                count = result.getInt( "count" );
            }
        } catch( SQLException e ) {

        }

        return count > 0;
    }

    /**
     *
     * @return
     */
    private String generateCode() {
        StringBuilder builder = new StringBuilder();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();

        for( int i = 0; i < 20; i++ ) {
            if( i > 0 && i % 5 == 0 ) {
                builder.append( "-" );
            }
            
            switch( random.nextInt( 10 ) % 2 ) {
                case 1:
                    builder.append( numbers.charAt( random.nextInt( numbers.length() ) ) );
                    break;
                default:
                    builder.append( letters.charAt( random.nextInt( letters.length() ) ) );
                    break;
            }
        }

        return builder.toString();
    }

    /**
     *
     * @param request
     * @param driver
     * @return
     */
    private User checkValidationCode( HttpServletRequest request, DBDriver driver ) {
        String email = RequestHelper.getValue( "email", request );
        String code = RequestHelper.getValue( "confirmCode", request );
        String where = "email = '" + email + "' AND validationCode = '" + code + "'";
        ResultSet result = driver.select( "user", null, where );
        User user = null;

        try {
            if( result.next() ) {
                user = new User();
                user.init( driver );
                user.populate( result );
            }
        } catch( SQLException e ) {

        }

        return user;
    }
}
