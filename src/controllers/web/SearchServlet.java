package controllers.web;

import business.BookListing;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import data.DBDriver;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import util.BooksZenBooks;
import util.RequestHelper;
import util.collections.Lexicon;

/**
 * Handles requests related to searching for books.
 * 
 * @author Rick Varella
 * @version 11.29.2009
 */
public class SearchServlet extends HttpServlet {
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
        String action = RequestHelper.getString( "action", request );
        String forwardUrl;
        RequestDispatcher dispatcher;
        ArrayList<String> searchParams;

        /* Load necessary lexicons */
        bzb.getLexicon().load( "global" );
        bzb.getLexicon().load( "search" );
        bzb.getLexicon().load( "book" );
        bzb.getLexicon().load( "subject" );

        searchParams = buildSearchParams( request );

        if( action.equals( "advancedSearch" ) ) {
            forwardUrl = jspPath + "advancedSearch.jsp";

            request.setAttribute( "conditions", getConditions( bzb.getLexicon() ) );
            request.setAttribute( "languages", getLanguages( bzb.getLexicon() ) );
            request.setAttribute( "sortFields", getSortFields( bzb.getLexicon() ) );
            request.setAttribute( "pageTitle", bzb.getLexicon().get( "advancedSearch" ) );
        }
        else {
            forwardUrl = jspPath + "searchResults.jsp";

            request.setAttribute( "pageTitle", bzb.getLexicon().get( "searchResults" ) );
            request.setAttribute( "listings", getSearchResults( searchParams, bzb.getDBDriver() ) );
        }

        /* Make lexicons and config settings available to JSP */
        request.setAttribute( "config", bzb.getConfig().getSettings() );
        request.setAttribute( "lexicon", bzb.getLexicon().getLexicons() );
        request.setAttribute( "language", bzb.getLexicon().getLanguage() );
        request.setAttribute( "subjects", bzb.getSubjects() );

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

    public ArrayList<String> buildSearchParams( HttpServletRequest request ) {
        ArrayList<String> params = new ArrayList<String>();
        String keywords = RequestHelper.getString( "keywords", request );
        String author = RequestHelper.getString( "author", request );
        String title = RequestHelper.getString( "title", request );
        String isbn = RequestHelper.getString( "isbn", request );
        String publisher = RequestHelper.getString( "publisher", request );
        String subject = RequestHelper.getString( "subject", request );
        String condition = RequestHelper.getString( "condition", request );
        String language = RequestHelper.getString( "language", request );

        if( !keywords.isEmpty() ) {
            params.add( "(b.title LIKE '%" + keywords + "%' OR b.isbn = '" + keywords + "' OR b.author LIKE '%" + keywords + "%')" );
        }
        if( !author.isEmpty() ) {
            params.add( "b.author LIKE '%" + author + "%'" );
        }
        if( !title.isEmpty() ) {
            params.add( "b.title LIKE '%" + title + "%'" );
        }
        if( !isbn.isEmpty() ) {
            params.add( "b.isbn = '" + isbn + "'" );
        }
        if( !publisher.isEmpty() ) {
            params.add( "b.publisher LIKE '%" + publisher + "%'" );
        }
        if( !subject.isEmpty() ) {
            params.add( "b.subjectId = '" + subject + "'" );
        }
        if( !condition.isEmpty() ) {
            params.add( "l.condition = '" + condition + "'" );
        }
        if( !language.isEmpty() ) {
            params.add( "b.language = '" + language + "'" );
        }

        return params;
    }

    private String getWhereString( ArrayList<String> parameters ) {
        StringBuilder where = new StringBuilder();
        Iterator<String> it = parameters.iterator();

        while( it.hasNext() ) {
            where.append( it.next() );

            if( it.hasNext() ) {
                where.append( " AND " );
            }
        }

        return where.toString();
    }

    private ArrayList<BookListing> getSearchResults( ArrayList<String> parameters, DBDriver driver ) {
        ResultSet result;
        BookListing listing;
        ArrayList<BookListing> listings = new ArrayList<BookListing>();
        String where = getWhereString( parameters );
        String[] fields = { "l.*", "b.*,u.*" };
        String[] join = { "INNER JOIN bzb.book b ON l.isbn=b.isbn", 
                    "INNER JOIN bzb.user u ON l.userId=u.userId" };

        result = driver.select( "booklisting l", fields, where, join, null, null, null, 0, 0 );

        try {
            while( result.next() ) {
                listing = new BookListing();
                listing.init( driver );
                listing.populate( result );
                listings.add( listing );
            }
        } catch( SQLException e ) {

        }

        return listings;
    }

    private HashMap<String, String> getConditions( Lexicon lexicon ) {
        HashMap<String, String> conditions = new HashMap<String, String>();
        String conditionString = getServletConfig().getInitParameter( "bookConditions" );
        String[] conditionArray = conditionString.split( "," );
        String key;

        for( int i = 0; i < conditionArray.length; i++ ) {
            conditions.put( conditionArray[ i ], lexicon.get( conditionArray[ i ] ) );
        }

        return conditions;
    }

    private HashMap<String, String> getLanguages( Lexicon lexicon ) {
        HashMap<String, String> languages = new HashMap<String, String>();
        String langString = getServletConfig().getInitParameter( "bookLanguages" );
        List<String> langKeys = Arrays.asList( langString.split( "," ) );
        
        for( Locale locale : Locale.getAvailableLocales() ) {
            if( langKeys.contains( locale.getLanguage() ) ) {
                languages.put( locale.getLanguage() , locale.getDisplayLanguage() );
            }
        }

        return languages;
    }

    private HashMap<String, String> getSortFields( Lexicon lexicon ) {
        HashMap<String, String> fields = new HashMap<String, String>();
        String fieldString = getServletConfig().getInitParameter( "sortFields" );
        String[] fieldArray = fieldString.split( "," );

        for( int i = 0; i < fieldArray.length; i++ ) {
            fields.put( fieldArray[ i ], lexicon.get( fieldArray[ i ] ) );
        }

        return fields;
    }
}
