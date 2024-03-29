package controllers.mgr;

import business.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;

/**
 * Handles managing users through the Manager interface.
 *
 * @author Rick Varella
 * @version 12.17.2009
 */
public class ManageUserServlet extends HttpServlet {
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
        bzb.getLexicon().load( "user" );

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

    private User getUser( HttpServletRequest request ) {
        throw new UnsupportedOperationException();
    }

    private boolean checkUserForm( HttpServletRequest request ) {
        throw new UnsupportedOperationException();
    }

    private boolean updateUser( User user, HttpServletRequest request ) {
        throw new UnsupportedOperationException();
    }

    private boolean removeUser( User user ) {
        throw new UnsupportedOperationException();
    }
}