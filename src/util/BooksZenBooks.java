package util;

import business.User;
import data.DBConfig;
import data.DBDriver;
import data.DBConnection;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import util.collections.*;

/**
 *
 * @author Rick
 */
public class BooksZenBooks {

    private DBConnection dbConnection;
    private DBDriver dbDriver;
    private SystemConfig config;
    private Lexicon lexicon;
    private User user;

    public BooksZenBooks( String language, String dbConfigResource ) {
        initDatabase( dbConfigResource );

        this.config = new SystemConfig( dbDriver );
        this.lexicon = new Lexicon( language, dbDriver );

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

        initDBDriver( dbConfig );
    }

    private void initDBDriver( DBConfig dbConfig ) {
        String dbPlatform = dbConfig.getPlatform();

        try {
            dbDriver = ( DBDriver ) Class.forName( "data.DBDriver" + dbPlatform ).newInstance();
            Method init = dbDriver.getClass().getMethod( "init", dbConnection.getClass() );
            init.invoke( dbDriver, dbConnection );
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

    public User getAuthenticatedUser( HttpServletRequest request ) {
        ResultSet result;
        User authUser;
        String where;

        if( user == null ) {
            authUser = new User();
            
            authUser.init( getDBDriver() );
            authUser.setEmail( CookieCutter.getCookie( request.getCookies(), "email" ) );
            authUser.setPassword( CookieCutter.getCookie( request.getCookies(), "password" ) );

            where = "email = '" + authUser.getEmail() + "' AND password = '" + authUser.getPassword() + "'";

            result = getDBDriver().select( "user", null, where );

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

    /**
     * @return the driver
     */
    public DBDriver getDBDriver() {
        return dbDriver;
    }

    /**
     * @return the config
     */
    public SystemConfig getConfig() {
        return config;
    }

    /**
     * @return the lexicon
     */
    public Lexicon getLexicon() {
        return lexicon;
    }
}
