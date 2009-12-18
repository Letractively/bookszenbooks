package controllers.web;

import business.BookListing;
import business.ShoppingCartEntry;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;
import util.RequestHelper;

/**
 *
 * @author Rick Varella
 * @version 12.16.2009
 */
public class ShoppingCartServlet extends HttpServlet {
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
        String message = null;
        boolean isError = false;
        String action = RequestHelper.getString( "action", request );
        RequestDispatcher dispatcher;

        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "subject" );
        bzb.getLexicon().load( "cart" );
        bzb.getLexicon().load( "search" );
        bzb.getLexicon().load( "book" );
        
        pageTitle = bzb.getLexicon().get( "cart" );
        forwardUrl = jspPath + "shoppingCart.jsp";

        if( bzb.getAuthenticatedUser( request ) == null ) {
            bzb.getLexicon().load( "error" );
            bzb.getLexicon().load( "register" );

            forwardUrl = jspPath + "401.jsp";
            pageTitle = bzb.getLexicon().get( "unauthorized" );
        }
        else if( action.equals( "add" ) ) {
            if( isValidListing( request ) && addToCart( request ) ) {
                message = bzb.getLexicon().get( "cartAddSuccess" );
            }
            else {
                message = bzb.getLexicon().get( "cartAddError" );
                isError = true;
            }
        }
        else if( action.equals( "remove" ) ) {
            if( removeFromCart( request ) ) {
                message = bzb.getLexicon().get( "cartRemoveSuccess" );
            }
            else {
                message = bzb.getLexicon().get( "cartRemoveError" );
                isError = true;
            }
        }

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "subjects", bzb.getSubjects() );
        request.setAttribute( "pageTitle", pageTitle );
        request.setAttribute( "message", message );
        request.setAttribute( "isError", isError );
        request.setAttribute( "listings", getListings( request ) );

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

    private boolean addToCart( HttpServletRequest request ) {
        ShoppingCartEntry entry = new ShoppingCartEntry();
        entry.init( bzb.getDriver() );
        entry.setListId( RequestHelper.getInt( "listId", request ) );
        entry.setUserId( bzb.getAuthenticatedUser( request ).getUserId() );

        return entry.save();
    }

    private boolean removeFromCart( HttpServletRequest request ) {
        ShoppingCartEntry entry;
        String where = "listId = " + RequestHelper.getInt( "listId", request );
        ResultSet result = bzb.getDriver().select( "shoppingcartentry", null, where );

        try {
            if( result.next() ) {
                entry = new ShoppingCartEntry();
                entry.init( bzb.getDriver() );
                entry.populate( result );

                return entry.remove();
            }
        } catch( SQLException e ) {

        }

        return false;
    }

    private ArrayList<BookListing> getListings( HttpServletRequest request ) {
        ArrayList<BookListing> listings = new ArrayList<BookListing>();
        ShoppingCartEntry entry;
        String[] fields = { "l.*", "b.*", "u.*" };
        String where = "e.userId = " + bzb.getAuthenticatedUser( request ).getUserId();
        String[] join = { "INNER JOIN bzb.booklisting l ON l.listId = e.listId",
                        "INNER JOIN bzb.book b ON b.isbn = l.isbn",
                        "INNER JOIN bzb.user u ON u.userId = l.userId" };
        ResultSet result = bzb.getDriver().select( "shoppingcartentry e", fields, where, join, null, null, null, 0, 0 );

        try {
            while( result.next() ) {
                entry = new ShoppingCartEntry();
                entry.init( bzb.getDriver() );
                entry.populate( result );
                listings.add( entry.getListing() );
            }
        } catch( SQLException e ) {

        }


        return listings;
    }

    private boolean isValidListing( HttpServletRequest request ) {
        BookListing listing;
        boolean isValid = true;
        String[] fields = { "*" };
        String where = "listId = " + RequestHelper.getInt( "listId", request );
        ResultSet result = bzb.getDriver().select( "booklisting", fields, where );

        try {
            if( result.next() ) {
                listing = new BookListing();
                listing.init( bzb.getDriver() );
                listing.populate( result );

                if( !listing.isActive() ) {
                    isValid = false;
                }
                else if( listing.getUserId() == bzb.getAuthenticatedUser( request ).getUserId() ) {
                    isValid = false;
                }
            }
            else {
                isValid = false;
            }

            result.close();
        } catch( SQLException e ) {

        }

        fields = new String[] { "COUNT(*) as count" };
        result = bzb.getDriver().select( "shoppingcartentry", fields, where );

        try {
            result.next();

            if( result.getInt( "count" ) > 0 ) {
                isValid = false;
            }
        } catch( SQLException e ) {

        }

        return isValid;
    }
}
