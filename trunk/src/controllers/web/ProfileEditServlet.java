package controllers.web;

import business.BookListing;
import business.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;
import util.RequestHelper;

public class ProfileEditServlet extends HttpServlet {
    private String dbConfigResource;
    private String jspPath;
    private BooksZenBooks bzb;

    /**
     * Initializes the servlet and sets up required instance variables.
     */
    @Override
    public void init() throws ServletException {
        super.init();

        dbConfigResource = getServletContext().getInitParameter("dbConfigResource");
        jspPath = getServletContext().getInitParameter("jspPath");
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

            }
            else {
                pageTitle = bzb.getLexicon().get( "editProfileSuccess" );
                
                saveUser();

                forwardUrl = jspPath + "editProfileSuccess.jsp";
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

    private HashMap<String, String> checkForm(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void saveUser() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}