package data;

import java.util.HashMap;

/**
 *
 * @author Rick Varella
 * @version 12.13.2009
 */
public class SchemaData {
    private HashMap<String, SchemaColumn> columns;
    private String tableName;
    private String[] primaryKeys;
    
    public SchemaData( HashMap<String, SchemaColumn> columns, String tableName, String[] primaryKeys ) {
        this.columns = columns;
        this.tableName = tableName;
        this.primaryKeys = primaryKeys;
    }

    /**
     * @return the columns
     */
    public HashMap<String, SchemaColumn> getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns( HashMap<String, SchemaColumn> columns ) {
        this.columns = columns;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName( String tableName ) {
        this.tableName = tableName;
    }

    /**
     * @return the primaryKeys
     */
    public String[] getPrimaryKeys() {
        return primaryKeys;
    }

    /**
     * @param primaryKeys the primaryKeys to set
     */
    public void setPrimaryKeys( String[] primaryKeys ) {
        this.primaryKeys = primaryKeys;
    }

}
