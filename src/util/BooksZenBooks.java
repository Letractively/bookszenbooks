package util;

import data.DBConfig;
import data.DBDriver;
import data.DBConnection;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import util.collections.*;

/**
 *
 * @author Rick
 */
public class BooksZenBooks implements Serializable {

    private DBConnection dbConnection;
    private DBDriver dbDriver;
    private SystemConfig config;
    private Lexicon lexicon;

    public BooksZenBooks() {
        
    }

    private void initConfig() {
    }

    public void initDatabase( String configResource ) {
        DBConfig dbConfig = null;

        try {
            dbConfig = new DBConfig( configResource );
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
