package controllers.mgr;

import business.Book;
import business.LexiconEntry;
import business.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;

/**
 * Handles requests to the main page of the system.
 *
 * @author Rick Varella
 * @version 11.29.2009
 */
public class ManageLexiconServlet extends HttpServlet {
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
        String forwardUrl = jspPath + "home";
        RequestDispatcher dispatcher;
        User user = bzb.getAuthenticatedUser( request );

        /* Load necessary lexicons */
        bzb.getLexicon().load( "manager" );
        bzb.getLexicon().load( "book" );

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "subjects", bzb.getSubjects() );
        request.getSession().setAttribute( "authUser", user );

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

    private ArrayList<LexiconEntry> getLexicons( HttpServletRequest request ) {
        throw new UnsupportedOperationException();
    }

    private LexiconEntry getLexicon( String key, String language ) {
        throw new UnsupportedOperationException();
    }

    private boolean updateLexicon( LexiconEntry entry, HttpServletRequest request ) {
        throw new UnsupportedOperationException();
    }

    private boolean removeLexicon( LexiconEntry entry ) {
        throw new UnsupportedOperationException();
    }
}