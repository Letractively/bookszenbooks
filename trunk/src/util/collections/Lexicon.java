package util.collections;

import business.LexiconEntry;
import data.DBDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class Lexicon {
    private Hashtable<String, String> lexicons;
    private String language;
    private DBDriver driver;

    /**
     *
     * @param language
     * @param driver
     */
    public Lexicon( String language, DBDriver driver ) {
        this.lexicons = new Hashtable<String, String>();
        this.language = language;
        this.driver = driver;
    }

    /**
     * Gets the lexicon string with the matching key.
     *
     * @param key The lexicon's key.
     * @return The lexicon string.
     */
    public String get( String key ) {
        return exists( key ) ? lexicons.get( key ) : "";
    }

    /**
     * Gets the parsed lexicon string with the matching key.
     * @param key The lexicon's key.
     * @param replace The replacement array. Index 0 is the string to search for.
     * Example: { { "find", "replaced text" } } will replace all occurences of
     * "${find}" with "replaced text".
     * @return The parsed lexicon string.
     */
    public String get( String key, String[][] replace ) {
        String string = get( key );
        /*Iterator<Map.Entry<String, String>> it = replace.entrySet().iterator();
        Map.Entry<String, String> entry;

        while( it.hasNext() ) {
            entry = it.next();

            string = string.replace( "${" + entry.getKey() + "}", entry.getValue() );
        }*/

        for( int i = 0; i < replace.length; i++ ) {
            string = string.replace( "${" + replace[i][0] + "}", replace[i][1] );
        }

        return string;
    }

    public void set( String key, String value ) {
        if( exists( key ) ) {
            lexicons.put( key, value );
        }
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
