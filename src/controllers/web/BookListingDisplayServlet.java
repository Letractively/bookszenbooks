package controllers.web;

import business.BookListing;
import business.User;
import javax.servlet.http.HttpServletRequest;
import data.DBDriver;
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
 * @author Rick Varella
 */
public class BookListingDisplayServlet extends HttpServlet {
    private static String dbConfigResource;
    private static String jspPath;

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
        BooksZenBooks bzb = new BooksZenBooks( "en", dbConfigResource ); // @TODO language should be a request param
        String forwardUrl;
        RequestDispatcher dispatcher;
        User user = bzb.getAuthenticatedUser( request );
        int listId = RequestHelper.getInt( "listId", request );
        BookListing listing = getBookListing( listId, bzb.getDBDriver() );

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

    private BookListing getBookListing( int listId, DBDriver driver ) {
        BookListing listing = null;
        String where = "listId = " + listId;
        String[] fields = { "b.*, l.listId, l.price, l.comment, l.listDate, l.active, l.condition, l.currency, u.*" };
        String[] join = { "INNER JOIN bzb.book b ON l.isbn=b.isbn",
                    "INNER JOIN bzb.user u ON l.userId=u.userId" };
        ResultSet result = driver.select( "booklisting l", fields, where, join, null, null, null, 0, 1 );

        try {
            if( result.next() ) {
                listing = new BookListing();
                listing.init( driver );
                listing.populate( result );
            }
        } catch( SQLException e ) {

        }

        return listing;
    }

    public String outputIsbnForm() {
        throw new UnsupportedOperationException();
    }

    public String outputListingForm() {
        throw new UnsupportedOperationException();
    }

    public String outputListing() {
        throw new UnsupportedOperationException();
    }

    public boolean bookExists(Object int_isbn) {
        throw new UnsupportedOperationException();
    }

    public boolean createBook() {
        throw new UnsupportedOperationException();
    }

    public boolean createListing() {
        throw new UnsupportedOperationException();
    }
}
