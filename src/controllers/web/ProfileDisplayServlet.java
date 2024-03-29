package controllers.web;

import business.BookListing;
import business.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;
import util.RequestHelper;

/**
 * Handles the display of user profiles.
 * 
 * @author Rick Varella
 * @version 13.13.2009
 */
public class ProfileDisplayServlet extends HttpServlet {
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
        String forwardUrl;
        String pageTitle;
        RequestDispatcher dispatcher;
        User user;
        int userId = RequestHelper.getInt( "userId", request );

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "subject" );

        if( bzb.getAuthenticatedUser( request ) == null ) {
            bzb.getLexicon().load( "error" );

            forwardUrl = jspPath + "401.jsp";
            pageTitle = bzb.getLexicon().get( "unauthorized" );
        }
        else {
            bzb.getLexicon().load( "profile" );
            bzb.getLexicon().load( "book" );
            bzb.getLexicon().load( "search" );

            user = getUser( userId );
            forwardUrl = jspPath + "displayUser.jsp";

            pageTitle = bzb.getLexicon().get( "viewingProfile", new String[][]{ { "user", user.getEmail() } } );
            
            request.setAttribute( "user", user );
            request.setAttribute( "stats", getUserStats( userId ) );
            request.setAttribute( "listings", getUserListings( userId ) );
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
        /* doPost() handles all requests */
        doPost(request, response);
    }

    private User getUser( int userId ) {
        ResultSet result;
        User user = null;
        String where = "userId = " + userId;

        /* Query for user data */
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

    private HashMap<String, String> getUserStats( int userId ) {
        HashMap<String, String> stats = new HashMap<String, String>();
        ResultSet result;
        String where = "userId = " + userId;
        String[] fields = { "COUNT(*) as totalListings" };

        /* Query for stats */
        result = bzb.getDriver().select( "booklisting", fields, where );

        try {
            /* Make sure there's a result */
            if( result.next() ) {
                stats.put( "totalListings", Integer.toString( result.getInt( "totalListings" ) ) );
            }
        } catch( SQLException e ) {

        }

        return stats;
    }

    private ArrayList<BookListing> getUserListings( int userId ) {
        ArrayList<BookListing> listings = new ArrayList<BookListing>();
        BookListing listing;
        String where = "userId = " + userId;
        String[] fields = { "l.*", "b.*" };
        String[] join = { "INNER JOIN bzb.book b ON l.isbn=b.isbn" };
        String[] orderBy = { "l.listDate DESC" };
        ResultSet result = bzb.getDriver().select( "booklisting l", fields, where, join, null, null, orderBy, 0, 5 );

        try {
            while( result.next() ) {
                listing = new BookListing();
                listing.init( bzb.getDriver() );
                listing.populate( result );
                listings.add( listing );
            }
        } catch( SQLException e ) {

        }

        return listings;
    }
}
