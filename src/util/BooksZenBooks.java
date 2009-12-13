package util;

import business.BookSubject;
import business.User;
import data.DBConfig;
import data.DBDriver;
import data.DBConnection;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import util.collections.*;

/**
 *
 * @author Rick
 */
public class BooksZenBooks {

    private DBConnection dbConnection;
    private DBDriver driver;
    private SystemConfig config;
    private Lexicon lexicon;
    private User user;

    public BooksZenBooks( String language, String dbConfigResource ) {
        initDatabase( dbConfigResource );

        this.config = new SystemConfig( driver );
        this.lexicon = new Lexicon( language, driver );

        this.config.load();
    }

    private void initDatabase( String dbConfigResource ) {
        DBConfig dbConfig = null;

        try {
            dbConfig = new DBConfig( dbConfigResource );
        } catch( FileNotFoundException e ) {
            // @TODO implement error handling
        }

        this.dbConnection = new DBConnection( dbConfig );
        dbConnection.openConnectionn();

        initDriver( dbConfig );
    }

    private void initDriver( DBConfig dbConfig ) {
        String dbPlatform = dbConfig.getPlatform();

        try {
            driver = ( DBDriver ) Class.forName( "data.DBDriver" + dbPlatform ).newInstance();
            Method init = driver.getClass().getMethod( "init", dbConnection.getClass() );
            init.invoke( driver, dbConnection );
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException();
        } catch (InvocationTargetException ex) {
            throw new RuntimeException();
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException();
        } catch (SecurityException ex) {
            throw new RuntimeException();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException();
        } catch (InstantiationException ex) {
            throw new RuntimeException();
        } catch (IllegalAccessException ex) {
            throw new RuntimeException();
        }
    }

    public synchronized User getAuthenticatedUser( HttpServletRequest request ) {
        ResultSet result;
        User authUser;
        String where;
        String email = CookieCutter.getCookie( request.getCookies(), "email" );
        String password = CookieCutter.getCookie( request.getCookies(), "password" );
        User sessionUser = ( User ) request.getSession().getAttribute( "authUser" );

        if( sessionUser != null ) {
            user = sessionUser;
        }
        else if( user == null && email != null && password != null ) {
            authUser = new User();
            
            authUser.init( getDriver() );
            authUser.setEmail( email );
            authUser.setPassword( password );

            where = "email = '" + authUser.getEmail() + "' AND password = '" + authUser.getPassword() + "'";

            result = getDriver().select( "user", null, where );

            try {
                if( result.next() ) {
                    authUser.populate( result );
                    user = authUser;
                }
            } catch( SQLException e ) {

            }
        }

        return user;
    }

    public ArrayList<BookSubject> getSubjects() {
        ArrayList<BookSubject> subjects = new ArrayList<BookSubject>();
        BookSubject subject;
        String[] orderBy = { "text ASC" };
        ResultSet result = driver.select( "booksubject", null, null, null, null, null, orderBy, 0, 0 );

        try {
            while( result.next() ) {
                subject = new BookSubject();
                subject.init( driver );
                subject.populate( result );
                subject.setI18nText( lexicon.get( subject.getText() ) );
                subjects.add( subject );
            }
        } catch( SQLException e ) {

        }

        return subjects;
    }

    /**
     * @return the driver
     */
    public synchronized DBDriver getDriver() {
        return driver;
    }

    /**
     * @return the config
     */
    public synchronized SystemConfig getConfig() {
        return config;
    }

    /**
     * @return the lexicon
     */
    public synchronized Lexicon getLexicon() {
        return lexicon;
    }
}
