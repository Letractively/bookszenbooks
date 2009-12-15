package controllers.web;

import business.BookListing;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Rick
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
        String action = RequestHelper.getString( "action", request );
        int listId = RequestHelper.getInt( "listId", request );
        RequestDispatcher dispatcher;

        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "subject" );
        bzb.getLexicon().load( "cart" );
        
        pageTitle = bzb.getLexicon().get( "cart" );
        forwardUrl = jspPath + "shoppingCart.jsp";

        if( bzb.getAuthenticatedUser( request ) == null ) {
            bzb.getLexicon().load( "error" );

            forwardUrl = jspPath + "401.jsp";
            pageTitle = bzb.getLexicon().get( "unauthorized" );
        }
        else if( action.equals( "add" ) ) {
            if( addToCart( listId ) ) {
                message = bzb.getLexicon().get( "cartAddSuccess" );
            }
            else {
                message = bzb.getLexicon().get( "cartAddError" );
            }
        }
        else if( action.equals( "remove" ) ) {
            if( removeFromCart( listId ) ) {
                message = bzb.getLexicon().get( "cartRemoveSuccess" );
            }
            else {
                message = bzb.getLexicon().get( "cartRemoveError" );
            }
        }

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "subjects", bzb.getSubjects() );
        request.setAttribute( "pageTitle", pageTitle );
        request.setAttribute( "message", message );
        request.setAttribute( "listings", getListings() );

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

    private boolean addToCart( int listId ) {
        return true;
    }

    private boolean removeFromCart( int listId ) {
        return true;
    }

    private ArrayList<BookListing> getListings() {
        ArrayList<BookListing> listings = new ArrayList<BookListing>();

        return listings;
    }
}
