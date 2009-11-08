package org.bzb.model;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bzb.collection.*;
import org.bzb.db.*;

/**
 *
 * @author Rick
 */
public class BooksZenBooks {

    private DBConfig dbConfig;
    private DBConnection dbConnection;
    private DBDriver dbDriver;
    private SystemConfig config;
    private Lexicon lexicon;

    public BooksZenBooks() {
        try {
            this.dbConfig = new DBConfig();
        } catch( FileNotFoundException e ) {
            // @TODO error handling
        }

        this.dbConnection = new DBConnection( this.dbConfig );
        dbConnection.openConnectionn();

        initDBDriver();
    }

    private void initConfig() {
    }

    private void initDBDriver() {
        String dbPlatform = dbConfig.getPlatform();

        try {
            dbDriver = ( DBDriver ) Class.forName( "org.bzb.db.DBDriver" + dbPlatform ).newInstance();
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

    /**
     * @return the dbConfig
     */
    public DBConfig getDbConfig() {
        return dbConfig;
    }
}
