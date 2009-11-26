package util.collections;

import business.LexiconEntry;
import data.DBDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class Lexicon {
    private Hashtable<String, String> lexicons;
    private String language;
    private DBDriver driver;
    
    public Lexicon( String language, DBDriver driver ) {
        this.lexicons = new Hashtable<String, String>();
        this.language = language;
        this.driver = driver;
    }

    public String get( String key ) {
        return exists( key ) ? lexicons.get( key ) : "";
    }

    public void load( String topic ) {
        ResultSet result;
        LexiconEntry entry;
        String where = "language = '" + language + "'";

        if( topic != null ) {
            where += " AND topic = '" + topic + "'";
        }

        result = driver.select( "lexiconentry", null, where );

        try {
            while( result.next() ) {
                entry = new LexiconEntry();
                entry.init( driver );
                entry.populate( result );
                lexicons.put( entry.getKey(), entry.getValue() );
            }
        } catch( SQLException e ) {

        }
    }

    public boolean clearCache() {
        throw new UnsupportedOperationException();
    }

    public boolean exists( String key ) {
        return lexicons.containsKey( key );
    }

    private boolean loadCache(String language, String topic) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return the lexicons
     */
    public Hashtable<String, String> getLexicons() {
        return lexicons;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }
}
