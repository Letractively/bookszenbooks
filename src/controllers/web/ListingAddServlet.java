package controllers.web;

import business.Book;
import data.DBDriver;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;
import util.RequestHelper;
import util.collections.Lexicon;
import util.collections.SystemConfig;

/**
 * Handles adding a new book listing to the system.
 *
 * @author Rick Varella
 * @version 12.12.2009
 */
public class ListingAddServlet extends HttpServlet {
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
        String pageTitle;
        int step = RequestHelper.getInt( "step", request );
        RequestDispatcher dispatcher;
        HashMap<String, String> formErrors = new HashMap<String, String>();

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "subject" );
        bzb.getLexicon().load( "book" );
        bzb.getLexicon().load( "listing" );

        pageTitle = bzb.getLexicon().get( "addListing" ) + ":";

        if( step == 2 ) {
            if( !isValidISBN( RequestHelper.getValue( "isbn", request ) ) ) {
                forwardUrl = jspPath + "newListingStep1.jsp";

                formErrors.put( "isbn", bzb.getLexicon().get( "invalidField", new String[][]{ { "field", bzb.getLexicon().get( "isbn" ) } } ) );
            }
            else {
                forwardUrl = jspPath + "newListingStep2.jsp";

                request.setAttribute( "languages", getLanguages( bzb.getLexicon(), bzb.getConfig() ) );
                request.setAttribute( "book", getBook( RequestHelper.getValue( "isbn", request ), bzb.getDBDriver() ) );
            }
        }
        else if( step == 3 ) {
            if( RequestHelper.getValue( "newBook", request ).equals( "false" ) ) {
                if( !isValidISBN( RequestHelper.getValue( "isbn", request ) ) ) {
                    forwardUrl = jspPath + "newListingStep1.jsp";

                    formErrors.put( "isbn", bzb.getLexicon().get( "invalidField", new String[][]{ { "field", bzb.getLexicon().get( "isbn" ) } } ) );
                }
                else {
                    forwardUrl = jspPath + "newListingStep3.jsp";
                }
            }
            else {
                formErrors = checkBookForm( request, bzb.getLexicon() );

                if( !formErrors.isEmpty() ) {
                    forwardUrl = jspPath + "newListingStep2.jsp";

                    request.setAttribute( "languages", getLanguages( bzb.getLexicon(), bzb.getConfig() ) );
                }
                else {
                    forwardUrl = jspPath + "newListingStep3.jsp";
                }
            }
        }
        else if( step == 4 ) {
            forwardUrl = jspPath + "newListingDone.jsp";
        }
        else {
            forwardUrl = jspPath + "newListingStep1.jsp";
        }

         /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "subjects", bzb.getSubjects() );
        request.setAttribute( "pageTitle", pageTitle );
        request.setAttribute( "formErrors", formErrors );

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

    private boolean isValidISBN( String isbn ) {
        Pattern pattern = Pattern.compile( "^[0-9]{13}$" );
        Matcher matcher = pattern.matcher( isbn );

        if( isbn == null || !matcher.find() ) {
            return false;
        }

        return true;
    }

    private Book getBook( String isbn, DBDriver driver ) {
        Book book = null;
        String where = "isbn = " + isbn;
        ResultSet result = driver.select( "book", null, where );

        try {
            if( result.next() ) {
                book = new Book();
                book.init( driver );
                book.populate( result );
            }
        } catch( SQLException e ) {

        }

        return book;
    }

    private HashMap<String, String> getLanguages( Lexicon lexicon, SystemConfig config ) {
        HashMap<String, String> languages = new HashMap<String, String>();
        List<String> langKeys = Arrays.asList( config.get( "bookLanguages" ).split( "," ) );

        for( Locale locale : Locale.getAvailableLocales() ) {
            if( langKeys.contains( locale.getLanguage() ) ) {
                languages.put( locale.getLanguage(), locale.getDisplayLanguage() );
            }
        }

        return languages;
    }

    private HashMap<String, String> checkBookForm( HttpServletRequest request, Lexicon lexicon ) {
        HashMap<String, String> errors = new HashMap<String, String>();

        if( !isValidISBN( RequestHelper.getValue( "isbn", request ) ) ) {
            errors.put( "isbn", lexicon.get( "invalidField", new String[][]{ { "field", lexicon.get( "isbn" ) } } ) );
        }
        if( RequestHelper.getValue( "title", request ).isEmpty() ) {
            errors.put( "title", lexicon.get( "emptyField", new String[][]{ { "field", lexicon.get( "title" ) } } ) );
        }
        if( RequestHelper.getValue( "author", request ).isEmpty() ) {
            errors.put( "author", lexicon.get( "emptyField", new String[][]{ { "field", lexicon.get( "author" ) } } ) );
        }
        if( RequestHelper.getValue( "edition", request ).isEmpty() ) {
            errors.put( "edition", lexicon.get( "emptyField", new String[][]{ { "field", lexicon.get( "edition" ) } } ) );
        }
        if( RequestHelper.getValue( "subject", request ).isEmpty() ) {
            errors.put( "subject", lexicon.get( "emptyField", new String[][]{ { "field", lexicon.get( "subject" ) } } ) );
        }
        if( RequestHelper.getValue( "format", request ).isEmpty() ) {
            errors.put( "format", lexicon.get( "emptyField", new String[][]{ { "field", lexicon.get( "format" ) } } ) );
        }
        if( RequestHelper.getValue( "language", request ).isEmpty() ) {
            errors.put( "language", lexicon.get( "emptyField", new String[][]{ { "field", lexicon.get( "language" ) } } ) );
        }
        if( RequestHelper.getValue( "pages", request ).isEmpty() ) {
            errors.put( "pages", lexicon.get( "emptyField", new String[][]{ { "field", lexicon.get( "pages" ) } } ) );
        }
        if( RequestHelper.getValue( "publisher", request ).isEmpty() ) {
            errors.put( "publisher", lexicon.get( "emptyField", new String[][]{ { "field", lexicon.get( "publisher" ) } } ) );
        }
        if( util.Util.parseDate( RequestHelper.getValue( "publishDate", request ) ) == null ) {
            errors.put( "publishDate", lexicon.get( "publishDateInvalid" ) );
        }

        return errors;
    }
}
