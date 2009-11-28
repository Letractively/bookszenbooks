/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.web;

import business.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
public class HomeServlet extends HttpServlet {
   
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
        User user = bzb.getAuthenticatedUser( request );
        String displayName;

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "home" );

        displayName = user != null ? user.getFirstName() : bzb.getLexicon().get( "guest" );

        bzb.getLexicon().set( "welcome", String.format( bzb.getLexicon().get( "welcome" ), displayName ) );

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "pageTitle", bzb.getLexicon().get( "home" ) );

        forwardUrl = "/home.jsp";

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

}
