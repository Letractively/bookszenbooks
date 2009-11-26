package util.collections;

import java.util.Hashtable;
import business.SystemSetting;
import data.DBDriver;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemConfig {

    private Hashtable<String, String> settings;
    private DBDriver driver;

    public SystemConfig( DBDriver driver ) {
        this.settings = new Hashtable<String, String>();
        this.driver = driver;
    }

    public String get( String key ) {
        return exists( key ) ? getSettings().get( key ) : "";
    }

    public boolean exists( String key ) {
        return getSettings().containsKey( key );
    }

    public void load() {
        SystemSetting entry;
        ResultSet result = driver.select( "systemsetting", null, null );

        try {
            while( result.next() ) {
                entry = new SystemSetting();
                entry.init( driver );
                entry.populate( result );
                getSettings().put( entry.getKey(), entry.getValue() );
            }
        } catch( SQLException e ) {

        }
    }

    public boolean clearCache() {
        throw new UnsupportedOperationException();
    }

    private boolean loadCache() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return the settings
     */
    public Hashtable<String, String> getSettings() {
        return settings;
    }
}
