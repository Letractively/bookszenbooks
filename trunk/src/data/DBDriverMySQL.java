package data;

import util.Util;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DBDriverMySQL implements DBDriver {
    private DBConnection connection;
    
    public void init( DBConnection connection ) {
        this.connection = connection;
    }
    
    public ResultSet selectQuery( String sqlStatement ) {
        System.out.println( sqlStatement );
        try {
            Statement stmt = ( Statement ) connection.getConnection().createStatement();

            return stmt.executeQuery( sqlStatement );
        } catch( SQLException e ) {
            throw new RuntimeException();
        }
    }

    public int updateQuery( String sqlStatement ) {
        int affectedRows = 0;

        try {
            Statement stmt = ( Statement ) connection.getConnection().createStatement();
            affectedRows = stmt.executeUpdate( sqlStatement );
        } catch( SQLException e ) {
            System.out.println( e.getMessage() );
            throw new RuntimeException();
        }

        return affectedRows;
    }
    
    public ResultSet select( String table, String[] fields, String where ) {
        return select( table, fields, where, null, null, null, null, 0, 0 );
    }

    public ResultSet select( String table, String[] fields, String where, String[] join, String groupBy[], String having, String orderBy[], int start, int limit ) {
        StringBuilder sql = new StringBuilder();

        /* Make sure a table is specified */
        if( table == null ) {
            throw new RuntimeException();
        }

        /* Empty field list means we'll select all fields */
        if( fields == null || fields.length < 1 ) {
            fields = new String[1];
            fields[0] = "*";
        }

        /* Add table and fields list to query */
        sql.append( "SELECT " ).append( Util.joinArray( fields, "," ) ).append( " FROM " ).append( getFullTableName( table ) );

        /* Any JOINs?*/
        if( join != null && join.length > 0 ) {
            sql.append( " " ).append( Util.joinArray( join, " " ) );
        }

        /* Searching on a WHERE condition? */
        if( where != null && !where.isEmpty() ) {
            sql.append( " WHERE " ).append( where );
        }        
        
        /* Add GROUP BY clause */
        if( groupBy != null && groupBy.length > 0 ) {
            sql.append( Util.joinArray( groupBy, "," ) );
        }

        if( having != null && !having.isEmpty() ) {
            sql.append( " HAVING " ).append( having );
        }

        if( orderBy != null && orderBy.length > 0 ) {
            sql.append( " ORDER BY " ).append( Util.joinArray( orderBy, "," ) );
        }

        if( limit > 0 ) {
            if( start < 1 ) {
                start = 0;
            }

            sql.append( " LIMIT " ).append( start ).append( "," ).append( limit );
        }

        /* Return the compiled SQL code */
        return selectQuery( sql.toString() );
    }
    
    public int insert( String table, HashMap<String, String> fields ) {
        StringBuilder sql = new StringBuilder();

        sql.append( "INSERT INTO " ).append( getFullTableName( table ) );
        sql.append( "(" ).append( Util.joinArray( fields.keySet(), "," ) ).append( ") " );
        sql.append( "VALUES('" ).append( Util.joinArray( fields.values(), "','" ) ).append( "') " );

        return updateQuery( sql.toString() );
    }

    public int update( String table, HashMap<String, String> fields, String where ) {
        return update( table, fields, where, null, 0, 0 );
    }
    
    public int update( String table, HashMap<String, String> fields, String where, String orderBy[], int start, int limit ) {
        StringBuilder sql = new StringBuilder();
        Iterator<Map.Entry<String,String>> it = fields.entrySet().iterator();
        Map.Entry<String, String> entry = null;

        sql.append( "UPDATE " ).append( getFullTableName( table ) ).append( " SET " );

        while( it.hasNext() ) {
            entry = ( Map.Entry<String, String> ) it.next();
            sql.append( entry.getKey() ).append( "='" ).append( entry.getValue() ).append( "'" );

            if( it.hasNext() ) {
                sql.append( ", " );
            }
        }

        if( where != null && !where.isEmpty() ) {
            sql.append( " WHERE " ).append( where );
        }

        if( orderBy != null && orderBy.length > 0 ) {
            sql.append( " ORDER BY " ).append( Util.joinArray( orderBy, "," ) );
        }

        if( limit > 0 ) {
            if( start < 1 ) {
                start = 0;
            }

            sql.append( " LIMIT " ).append( start ).append( "," ).append( limit );
        }

        return updateQuery( sql.toString() );
    }

    public int delete( String table, String where ) {
        return delete( table, where, null, 0, 0 );
    }
    
    public int delete( String table, String where, String orderBy[], int start, int limit ) {
        StringBuilder sql = new StringBuilder();

        /* Make sure a table is specified */
        if( table == null ) {
            throw new RuntimeException();
        }

        sql.append( "DELETE FROM " ).append( getFullTableName( table ) );
        sql.append( " WHERE " ).append( where );

        if( orderBy != null && orderBy.length > 0 ) {
            sql.append( Util.joinArray( orderBy, "," ) );
        }

        if( limit > 0 ) {
            if( start < 1 ) {
                start = 0;
            }

            sql.append( " LIMIT " ).append( start ).append( "," ).append( limit );
        }

        return updateQuery( sql.toString() );
    }

    public DBConnection getConnection() {
        return this.connection;
    }

    public String getFullTableName( String table ) {
        StringBuffer buffer = new StringBuffer();

        buffer.append( connection.getConfig().getDatabase() ).append( "." ).append( table );

        return buffer.toString();
    }
}