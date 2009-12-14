/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.web;

import business.BookListing;
import business.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
public class ListingEditServlet extends HttpServlet {
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
        BookListing listing = getListing( RequestHelper.getInt( "listId", request ) );
        User authUser = bzb.getAuthenticatedUser( request );

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "subject" );
        bzb.getLexicon().load( "book" );
        bzb.getLexicon().load( "listing" );

        if( listing == null ) {
            bzb.getLexicon().load( "error" );

            forwardUrl = jspPath + "404.jsp";
            pageTitle = bzb.getLexicon().get( "listingNotFound" );

            request.setAttribute( "customError", bzb.getLexicon().get( "listingNotFoundDesc" ) );
        }
        else if( authUser == null || authUser.getUserId() != listing.getUserId() ) {
            bzb.getLexicon().load( "error" );
            bzb.getLexicon().load( "register" );

            forwardUrl = jspPath + "401.jsp";
            pageTitle = bzb.getLexicon().get( "unauthorized" );

            request.setAttribute( "customError", bzb.getLexicon().get( "notUserListing" ) );
        }
        else if( action.equals( "save" ) ) {
            formErrors = checkListingForm( request );

            listing.setCondition( RequestHelper.getString( "condition", request ) );
            listing.setPrice( RequestHelper.getDouble( "price", request ) );
            listing.setComment( RequestHelper.getString( "comment", request ) );
            listing.setActive( RequestHelper.getBoolean( "active", request ) );

            if( !formErrors.isEmpty() ) {
                forwardUrl = jspPath + "editListingForm.jsp";
                pageTitle = bzb.getLexicon().get( "editListing" );
                
                request.setAttribute( "listing", listing );
                request.setAttribute( "formErrors", formErrors );
                request.setAttribute( "conditions", getConditions() );
            }
            else {
                updateListing( listing, request );

                forwardUrl = jspPath + "editListingSuccess.jsp";
                pageTitle = bzb.getLexicon().get( "listingUpdated" );
            }
        }
        else {            
            forwardUrl = jspPath + "editListingForm.jsp";
            pageTitle = bzb.getLexicon().get( "editListing" );

            request.setAttribute( "listing", listing );
            request.setAttribute( "conditions", getConditions() );
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

    private HashMap<String, String> checkListingForm( HttpServletRequest request ) {
        HashMap<String, String> errors = new HashMap<String, String>();

        if( RequestHelper.getDouble( "price", request ) < 0.01 ) {
            errors.put( "price", bzb.getLexicon().get( "invalidField", new String[][]{ { "field", bzb.getLexicon().get( "price" ) } } ) );
            System.out.println( "sfsdfdsfsdfsd:" + bzb.getLexicon().get( "price" ));
        }
        if( RequestHelper.getString( "condition", request ).isEmpty() ) {
            errors.put( "condition", bzb.getLexicon().get( "emptyField", new String[][]{ { "field", bzb.getLexicon().get( "condition" ) } } ) );
        }

        return errors;
    }

    private BookListing getListing( int listId ) {
        BookListing listing = null;
        String[] fields = { "l.*", "b.*" };
        String where = "l.listId = " + listId;
        String[] join = { "INNER JOIN bzb.book b ON l.isbn = b.isbn" };
        ResultSet result;

        if( listId < 1 ) {
            return listing;
        }

        result = bzb.getDriver().select( "booklisting l", fields, where, join, null, null, null, 0, 1 );

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

    private HashMap<String, String> getConditions() {
        HashMap<String, String> conditions = new HashMap<String, String>();
        String conditionString = bzb.getConfig().get( "bookConditions" );
        String[] conditionArray = conditionString.split( "," );

        for( int i = 0; i < conditionArray.length; i++ ) {
            conditions.put( conditionArray[ i ], bzb.getLexicon().get( conditionArray[ i ] ) );
        }

        return conditions;
    }

    private void updateListing( BookListing listing, HttpServletRequest request ) {
        listing.save();
    }
}
