package business;

import java.sql.ResultSet;
import data.DBDriver;
import data.SchemaColumn;
import util.Util;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public abstract class DBObject {
    protected DBDriver driver;
    protected HashMap<String, SchemaColumn> schema;
    private String tableName;
    private String[] primaryKeys;
    private boolean dirty;
    private boolean newObject;

    public DBObject() {
        this.dirty = false;
        this.newObject = true;
    }

    public void init( DBDriver driver ) {
        this.driver = driver;
    }

    public boolean populate( ResultSet row ) throws SQLException {
        ResultSetMetaData rsMetaData = row.getMetaData();
        int columnCount = rsMetaData.getColumnCount();
        String columnName;
        dirty = false;
        newObject = false;
        
        for( int i = 1; i <= columnCount; i++ ) {
            columnName = rsMetaData.getColumnName( i );

            if( schema.containsKey( columnName ) ) {
                setField( columnName, row );
            }
        }

        return true;
    }

    private boolean setField( String key, ResultSet row ) {
        Method method;
        String methodName;
        Class<?>[] methodTypes = new Class<?>[1];
        Object[] methodArgs = new Object[1];
        SchemaColumn column = schema.get( key );

        try {
            if( column.getJavaType().equals( "class java.lang.String" ) ) {
                methodTypes[0] = java.lang.String.class;
                methodArgs[0] = row.getString( key );
            }
            else if( column.getJavaType().equals( "int" ) ) {
                methodTypes[0] = int.class;
                methodArgs[0] = row.getInt( key );
            }
            else if( column.getJavaType().equals( "boolean" ) ) {
                methodTypes[0] = boolean.class;
                methodArgs[0] = row.getBoolean( key );
            }
            else if( column.getJavaType().equals( "class java.lang.Double" ) ) {
                methodTypes[0] = java.lang.Double.class;
                methodArgs[0] = row.getDouble( key );
            }
            else if( column.getJavaType().equals( "class java.lang.Long" ) ) {
                
                methodTypes[0] = java.lang.Long.class;
                methodArgs[0] = row.getLong( key );
            }
            else if( column.getJavaType().equals( "class java.util.Date" ) ) {
                methodTypes[0] = java.util.Date.class;
                methodArgs[0] = row.getDate( key );
            }
            else {
                return false;
            }
        } catch( SQLException e ) {
            return false;
        }
        
        methodName = "set" + Util.toUpperCaseFirst( key );
        
        try {
            method = this.getClass().getMethod( methodName, methodTypes );
            method.invoke( this, methodArgs );
        } catch( NoSuchMethodException e ) {
            return false;
        } catch( IllegalAccessException e ) {
             return false;
        } catch( InvocationTargetException e ) {
            return false;
        }

        return true;
    }

    public boolean save() {
        if( newObject ) {
            return this.insert();
        }

        return this.update();
    }

    abstract protected boolean insert();

    abstract protected boolean update();

    abstract protected boolean remove();

    protected String formatPKWhere( String[] primaryKeys ) {
        String where = "";

        for( int i = 0; i < primaryKeys.length - 1; i++ ) {
            where += primaryKeys[ i ] + "='" + getFieldValue( primaryKeys[ i ] ) + "' AND ";
        }

        where += primaryKeys[ primaryKeys.length - 1 ] + "='" + getFieldValue( primaryKeys[ primaryKeys.length - 1 ] ) + "'";

        return where;
    }

    protected HashMap<String, String> getDatabaseFields() {
        Iterator<SchemaColumn> it = schema.values().iterator();
        HashMap<String, String> fields = new HashMap<String, String>( schema.values().size() );
        SchemaColumn column;
        Object rawValue;

        while( it.hasNext() ) {
            column = it.next();
            rawValue = getFieldValue( column.getName() );
            
            fields.put( column.getName(), prepareForDB( rawValue, column.getDbType(), column.getJavaType() ) );
        }

        return fields;
    }

    protected String prepareForDB( Object value, int dbType, String javaType ) {
        String returnValue = null;
        SimpleDateFormat simpleDate;

        if( value == null ) {
            return null;
        }

        switch( dbType ) {
            case java.sql.Types.TINYINT:
            case java.sql.Types.BIT:
                if( javaType.equals( "boolean" ) ) {
                    returnValue = value.equals( true ) ? "1" : "0";
                }
                break;
            case java.sql.Types.DATE:
                simpleDate = new SimpleDateFormat( "yyyy-MM-dd" );
                returnValue = simpleDate.format( ( Date ) value );
                break;
            case java.sql.Types.TIMESTAMP:
                simpleDate = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                returnValue = simpleDate.format( ( Date ) value );
                break;
            default:
                returnValue = value == null ? null : value.toString();
                break;
        }

        return returnValue;
    }

    protected Object getFieldValue( String field ) {
        String methodName;
        Method method;
        Object value = null;

        try {
            methodName = "get" + Util.toUpperCaseFirst( field );
            method = this.getClass().getMethod( methodName );
            value = method.invoke( this );
        } catch( NoSuchMethodException e ) {

        } catch( IllegalAccessException e ) {

        } catch( InvocationTargetException e ) {

        }

        return value;
    }

    /**
     * @return the columnData
     */
    public HashMap getSchema() {
        return schema;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return the dirty
     */
    public boolean isDirty() {
        return dirty;
    }

    /**
     * @return the newObject
     */
    public boolean isNewObject() {
        return newObject;
    }

    /**
     * @return the primaryKeys
     */
    public String[] getPrimaryKeys() {
        return primaryKeys;
    }

    protected void setDirty( boolean isDirty ) {
        this.dirty = isDirty;
    }

    protected void setNewObject( boolean isNewObject ) {
        this.newObject = isNewObject;
    }
}
