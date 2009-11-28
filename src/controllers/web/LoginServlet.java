package controllers.web;

import javax.servlet.http.HttpServletRequest;
import business.User;
import data.DBDriver;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.BooksZenBooks;
import util.DigestHelper;
import util.RequestHelper;

/**
 * This servlet handles all login and logout requests, including setting and
 * removing cookies and user session data.
 *
 * @author Rick Varella
 * @version 11.25.2009
 */

public class LoginServlet extends HttpServlet {

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
        ServletContext context = this.getServletContext();
        String dbConfigResource = context.getInitParameter( "dbConfigResource" );
        BooksZenBooks bzb = new BooksZenBooks( "en", dbConfigResource ); // @TODO language should be a request param
        String action = RequestHelper.getValue( "action", request );
        String forwardUrl;
        RequestDispatcher dispatcher;
        User user;

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "register" );

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );

        /* Handle logout request */
        if( action.equals( "logout" ) ) {
            forwardUrl = "/home";

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
                user = checkCredentials( request, bzb.getDBDriver() );

                if( user == null ) {
                    forwardUrl = "/login.jsp";

                    request.setAttribute( "pageTitle", bzb.getLexicon().get( "logIn" ) );
                    request.setAttribute( "formError", bzb.getLexicon().get( "invalidLogin" ) );
                }
                else if( !user.getValidated() ) {
                    forwardUrl = "/login.jsp";

                    request.setAttribute( "pageTitle", bzb.getLexicon().get( "logIn" ) );
                    request.setAttribute( "formError", bzb.getLexicon().get( "notValidated" ) );
                }
                else {
                    forwardUrl = "/home";

                    startSession( user, request, response, Integer.parseInt( bzb.getConfig().get( "maxCookieLifetime" ) ) );
                }
            }
        }
        /* Requesting the initial login page */
        else {
            if( bzb.getAuthenticatedUser( request ) != null ) {
                forwardUrl = "/home";
            }
            else {
                forwardUrl = "/login.jsp";

                request.setAttribute( "pageTitle", bzb.getLexicon().get( "logIn" ) );
            }
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
    public User checkCredentials( HttpServletRequest request, DBDriver driver ) {
        ResultSet result;
        User user = null;
        String email = RequestHelper.getValue( "email", request );
        String password = RequestHelper.getValue( "password", request );
        
        String where = "email = '" + email + "' AND password = '" + DigestHelper.md5( password ) + "'";

        /* Query for matching user */
        result = driver.select( "user", null, where );

        try {
            /* Make sure there's a result */
            if( result.next() ) {
                user = new User();

                user.init( driver );
                user.populate( result );

                return user;
            }
        } catch( SQLException e ) {

        }

        return null;
    }

    /**
     * Begins a new login session for the user.
     *
     * @param user The validated user.
     * @param request The request object.
     * @param response The response object.
     * @param config The configuration object.
     */
    public void startSession( User user, HttpServletRequest request, HttpServletResponse response, int cookieLifetime ) {
        HttpSession session = request.getSession();
        Cookie idCookie = new Cookie( "userId", Integer.toString( user.getUserId() ) );
        Cookie emailCookie = new Cookie( "email", user.getEmail() );
        Cookie passwordCookie = new Cookie( "password", user.getPassword() );

        /* Set up cookies */
        idCookie.setMaxAge( cookieLifetime );
        idCookie.setPath( "/" );
        emailCookie.setMaxAge( cookieLifetime );
        emailCookie.setPath( "/" );
        passwordCookie.setMaxAge( cookieLifetime );
        passwordCookie.setPath( "/" );

        /* Add cookies to browser */
        response.addCookie( idCookie );
        response.addCookie( emailCookie );
        response.addCookie( passwordCookie );

        /* Set user session attribute */
        session.setAttribute( "user", user );
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