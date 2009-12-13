package controllers.web;

import business.BookListing;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;
import util.RequestHelper;

/**
 * Handles requests related to displaying a book listing.
 * 
 * @author Rick Varella
 * @version 12.13.2009
 */
public class ListingDisplayServlet extends HttpServlet {
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
        RequestDispatcher dispatcher;
        int listId = RequestHelper.getInt( "listId", request );
        BookListing listing = getBookListing( listId );

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "book" );
        bzb.getLexicon().load( "listing" );
        bzb.getLexicon().load( "subject" );

        if( listing == null ) {
            request.setAttribute( "pageTitle", bzb.getLexicon().get( "listingNotFound" ) );
        }
        else {
            request.setAttribute( "pageTitle", bzb.getLexicon().get( "showingListing" ) + ": " + listing.getBook().getTitle() );
        }

        forwardUrl = jspPath + "bookListing.jsp";

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "subjects", bzb.getSubjects() );
        request.setAttribute( "listing", listing );

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

    private BookListing getBookListing( int listId ) {
        BookListing listing = null;
        String where = "listId = " + listId;
        String[] fields = { "b.*, l.listId, l.price, l.comment, l.listDate, l.active, l.condition, l.currency, u.*" };
        String[] join = { "INNER JOIN bzb.book b ON l.isbn=b.isbn",
                    "INNER JOIN bzb.user u ON l.userId=u.userId" };
        ResultSet result = bzb.getDriver().select( "booklisting l", fields, where, join, null, null, null, 0, 1 );

        try {
            if( result.next() ) {
                listing = new BookListing();
                listing.init( bzb.getDriver() );
                listing.populate( result );
            }
        } catch( SQLException e ) {

        }

        return listing;
    }
}
