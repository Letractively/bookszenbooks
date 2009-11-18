package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private DBConfig config;
    private Connection connection;

    public DBConnection( DBConfig config ) {
        this.config = config;
    }

    public void openConnectionn()  {
        try {
            Class.forName( config.getDriver() );
            connection = ( Connection ) DriverManager.getConnection( config.getServer(), config.getUser(), config.getPassword() );
        } catch( SQLException e ) {
            throw new RuntimeException();
        } catch ( ClassNotFoundException e ) {
            throw new RuntimeException();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch( SQLException e ) {
            throw new RuntimeException();
        }
    }

    @Override
    public void finalize() throws SQLException {
        if( !connection.isClosed() ) {
            closeConnection();
        }
    }

    /**
     * @return the config
     */
    public DBConfig getConfig() {
        return config;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
}
