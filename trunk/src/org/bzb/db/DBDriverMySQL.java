package org.bzb.db;

import org.bzb.model.Util;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class DBDriverMySQL implements DBDriver {
    public DBConnection connection;
    
    public void init( DBConnection connection ) {
        this.connection = connection;
    }
    
    public ResultSet selectQuery( String sqlStatement ) {
        try {
            Statement stmt = ( Statement ) connection.getConnection().createStatement();

            return stmt.executeQuery( sqlStatement );
        } catch( SQLException e ) {
            throw new RuntimeException();
        }
    }

    public int updateQuery( String sqlStatement ) {
        try {
            Statement stmt = ( Statement ) connection.getConnection().createStatement();

            return stmt.executeUpdate( sqlStatement );
        } catch( SQLException e ) {
            throw new RuntimeException();
        }
    }
    
    public ResultSet select( String table, String[] fields, String where ) {
        return select( table, fields, where, null, null, null, null, 0, 20 ); //@TODO make default LIMIT a system setting
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
        sql.append( "SELECT " ).append( Util.joinArray( fields, "," ) ).append( " FROM " ).append( table );

        /* Searching on a WHERE condition? */
        if( where != null && !where.isEmpty() ) {
            sql.append( " WHERE " ).append( where );
        }

        /* Any JOINs?*/
        if( join != null && join.length > 0 ) {
            sql.append( Util.joinArray( join, " " ) );
        }
        
        /* Add GROUP BY clause */
        if( groupBy != null && groupBy.length > 0 ) {
            sql.append( Util.joinArray( groupBy, "," ) );
        }

        if( having != null && !having.isEmpty() ) {
            sql.append( " HAVING " ).append( having );
        }

        if( orderBy != null && orderBy.length > 0 ) {
            sql.append( Util.joinArray( orderBy, "," ) );
        }

        if( start < 0 ) {
            start = 0;
        }

        if( limit < 0 ) {
            limit = 20; // @TODO make limit value a system setting
        }

        sql.append( " LIMIT " ).append( start ).append( "," ).append( limit );

        /* Return the compiled SQL code */
        return selectQuery( sql.toString() );
    }
    
    public int insert( String table, Hashtable<String,?> fields ) {
        StringBuilder sql = new StringBuilder();

        sql.append( "INSERT INTO " ).append( table );
        sql.append( "(" ).append( Util.joinArray( fields.keySet(), "," ) ).append( ") " );
        sql.append( "VALUES(" ).append( Util.joinArray( ( Set<String> ) fields.values(), "," ) ).append( ") " );

        return updateQuery( sql.toString() );
    }
    
    public int update( String table, Hashtable<String,?> fields, String where, String orderBy[], int start, int limit ) {
        StringBuilder sql = new StringBuilder();

        sql.append( "UPDATE " ).append( table ).append( " SET " );

        for( Map.Entry<String, ?> entry : fields.entrySet() ) {
            sql.append( entry.getKey() ).append( "='" ).append( entry.getValue().toString() ).append( "'," );
        }

        if( orderBy != null && orderBy.length > 0 ) {
            sql.append( Util.joinArray( orderBy, "," ) );
        }

        if( start < 0 ) {
            start = 0;
        }

        if( limit < 0 ) {
            limit = 20; // @TODO make limit value a system setting
        }

        sql.append( " LIMIT " ).append( start ).append( "," ).append( limit );

        return updateQuery( sql.toString() );
    }
    
    public int delete( String table, String where, String orderBy[], int start, int limit ) {
        StringBuilder sql = new StringBuilder();

        /* Make sure a table is specified */
        if( table == null ) {
            throw new RuntimeException();
        }

        sql.append( "DELETE FROM " ).append( table ).append( " WHERE " ).append( where );

        if( orderBy != null && orderBy.length > 0 ) {
            sql.append( Util.joinArray( orderBy, "," ) );
        }

        if( start < 0 ) {
            start = 0;
        }

        if( limit < 0 ) {
            limit = 20; // @TODO make limit value a system setting
        }

        sql.append( " LIMIT " ).append( start ).append( "," ).append( limit );

        return updateQuery( sql.toString() );
    }
}