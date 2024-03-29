package controllers.web;

import javax.servlet.http.HttpServletRequest;
import business.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.BooksZenBooks;
import util.DigestHelper;
import util.RequestHelper;

/**
 * Handles all login and logout requests, including setting and
 * removing cookies and user session data.
 *
 * @author Rick Varella
 * @version 12.13.2009
 */

public class LoginServlet extends HttpServlet {
    private String dbConfigResource;
    private String jspPath;
    private BooksZenBooks bzb;

     /**
     * Initializes the servlet and sets up required instance variables.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        
        dbConfigResource = getServletContext().getInitParameter( "dbConfigResource" );
        jspPath = getServletContext().getInitParameter( "jspPath" );
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
        String action = RequestHelper.getString( "action", request );
        String forwardUrl;
        RequestDispatcher dispatcher;
        User user;

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "register" );
        bzb.getLexicon().load( "subject" );

        /* Handle logout request */
        if( action.equals( "logout" ) ) {
            forwardUrl = jspPath + "/loggedOut.jsp";

            endSession( request, response );
        }
        /* Handle login request */
        else if( action.equals( "login" ) ) {
            /* Already logged in? */
            if( bzb.getAuthenticatedUser( request ) != null ) {
                forwardUrl = "/home";
            }
            else {
                /* Verify email and password */
                user = checkCredentials( request );

                /* No matching user */
                if( user == null ) {
                    forwardUrl = jspPath + "login.jsp";

                    request.setAttribute( "pageTitle", bzb.getLexicon().get( "logIn" ) );
                    request.setAttribute( "formError", bzb.getLexicon().get( "invalidLogin" ) );
                }
                /* Not validated yet */
                else if( !user.isValidated() ) {
                    forwardUrl = jspPath + "login.jsp";

                    request.setAttribute( "pageTitle", bzb.getLexicon().get( "logIn" ) );
                    request.setAttribute( "formError", bzb.getLexicon().get( "notValidated" ) );
                }
                /* User matched AND is validated */
                else {
                    forwardUrl = "/home";

                    startSession( user, request, response );
                }
            }
        }
        /* Requesting the initial login page */
        else {
            if( bzb.getAuthenticatedUser( request ) != null ) {
                forwardUrl = "/home";
            }
            else {
                forwardUrl = jspPath + "login.jsp";

                request.setAttribute( "pageTitle", bzb.getLexicon().get( "logIn" ) );
            }
        }

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "subjects", bzb.getSubjects() );

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
        /* doPost() handles all requests */
        doPost( request, response );
    }

    /**
     * Checks that the login request data matches an existing user.
     *
     * @param request The content of the HTTP request.
     * @param bzb The BooksZenBooks class.
     * @return The matching User if one is found, null otherwise.
     */
    public User checkCredentials( HttpServletRequest request ) {
        ResultSet result;
        User user = null;
        String email = RequestHelper.getString( "email", request );
        String password = RequestHelper.getString( "password", request );
        String where = "email = '" + email + "' AND password = '" + DigestHelper.md5( password ) + "'";

        /* Query for matching user */
        result = bzb.getDriver().select( "user", null, where );

        try {
            /* Make sure there's a result */
            if( result.next() ) {
                user = new User();

                user.init( bzb.getDriver() );
                user.populate( result );
            }
        } catch( SQLException e ) {

        }

        return user;
    }

    /**
     * Begins a new login session for the user.
     *
     * @param user The validated user.
     * @param request The request object.
     * @param response The response object.
     * @param config The configuration object.
     */
    public void startSession( User user, HttpServletRequest request, HttpServletResponse response ) {
        HttpSession session = request.getSession();
        Cookie idCookie = new Cookie( "userId", Integer.toString( user.getUserId() ) );
        Cookie emailCookie = new Cookie( "email", user.getEmail() );
        Cookie passwordCookie = new Cookie( "password", user.getPassword() );
        int lifetime = Integer.parseInt( bzb.getConfig().get( "maxCookieLifetime" ) );

        /* Set up cookies */
        idCookie.setMaxAge( lifetime );
        idCookie.setPath( "/" );
        emailCookie.setMaxAge( lifetime );
        emailCookie.setPath( "/" );
        passwordCookie.setMaxAge( lifetime );
        passwordCookie.setPath( "/" );

        /* Add cookies to browser */
        response.addCookie( idCookie );
        response.addCookie( emailCookie );
        response.addCookie( passwordCookie );

        /* Set user session attribute */
        session.setAttribute( "authUser", user );
    }

    /**
     * Ends the current session and logs the user out of the system.
     *
     * @param request The request object.
     * @param response The response object.
     */
    public void endSession( HttpServletRequest request, HttpServletResponse response ) {
        HttpSession session = request.getSession();
        Cookie idCookie = new Cookie( "userId", null );
        Cookie emailCookie = new Cookie( "email", null );
        Cookie passwordCookie = new Cookie( "password", null );

        /* Invalidate cookies */
        idCookie.setMaxAge( 0 );
        idCookie.setPath( "/" );
        emailCookie.setMaxAge( 0 );
        emailCookie.setPath( "/" );
        passwordCookie.setMaxAge( 0 );
        passwordCookie.setPath( "/" );

        /* Overwrite existing cookies with invalid ones */
        response.addCookie( idCookie );
        response.addCookie( emailCookie );
        response.addCookie( passwordCookie );

        /* Invalidate session contents */
        session.invalidate();
    }
}