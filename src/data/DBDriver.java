package data;

import java.sql.ResultSet;
import java.util.HashMap;

public interface DBDriver {

    public void init( DBConnection connection );
    public ResultSet selectQuery( String sqlStatement );
    public int updateQuery( String sqlStatement );
    public ResultSet select( String table, String[] fields, String where );
    public ResultSet select( String table, String[] fields, String where, String[] join, String groupBy[], String having, String orderBy[], int start, int limit );
    public int insert( String table, HashMap<String, String> fields );
    public int update( String table, HashMap<String, String> fields, String where );
    public int update( String table, HashMap<String, String> fields, String where, String orderBy[], int start, int limit );
    public int delete( String table, String where );
    public int delete( String table, String where, String orderBy[], int start, int limit );
    public DBConnection getConnection();

}