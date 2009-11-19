package business;

import java.sql.ResultSet;
import java.util.Hashtable;
import data.DBDriver;
import data.ResultSetColumn;
import util.Util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public abstract class DBObject {
    protected Hashtable<String, ResultSetColumn> columnData;
    protected DBDriver driver;
    protected String tableName;
    protected String[] primaryKeys;
    private boolean dirty;
    private boolean newObject;
    

    public DBObject() {
        this.dirty = false;
        this.newObject = true;
    }

    public boolean populate( ResultSet row ) throws SQLException {
        Field[] fields = this.getClass().getDeclaredFields();
        dirty = false;
        newObject = false;
        
        initMetaData( row );

        for( int i = 0; i < fields.length; i++ ) {
            if( columnData.containsKey( fields[ i ].getName() ) ) {
                setField( fields[ i ], row );
            }
        }

        return true;
    }

    private void initMetaData( ResultSet row ) {
        ResultSetMetaData metaData;
        ResultSetColumn column;
        int countRowFields;

        try {
            metaData = row.getMetaData();
            countRowFields = metaData.getColumnCount();
            columnData = new Hashtable<String, ResultSetColumn>( countRowFields );

            for( int i = 1; i <= countRowFields; i++ ) {
                column = new ResultSetColumn( i, metaData.getColumnName( i ), metaData.getColumnType( i ) );
                columnData.put( metaData.getColumnName( i ), column );
            }
        } catch( SQLException e ) {
            throw new RuntimeException();
        }
    }

    private boolean setField( Field field, ResultSet row ) {
        Method method;
        String methodName;
        Class<?>[] methodTypes = new Class<?>[1];
        Object[] methodArgs = new Object[1];
        Class<?> type = field.getType();
        String name = field.getName();

        try {
            if( type.equals( java.lang.String.class ) ) {
                methodTypes[0] = java.lang.String.class;
                methodArgs[0] = row.getString( name );
            }
            else if( type.equals( int.class ) ) {
                methodTypes[0] = int.class;
                methodArgs[0] = row.getInt( name );
            }
            else if( type.equals( boolean.class ) ) {
                methodTypes[0] = boolean.class;
                methodArgs[0] = row.getBoolean( name );
            }
            else if( type.equals( java.lang.Double.class ) ) {
                methodTypes[0] = java.lang.Double.class;
                methodArgs[0] = row.getDouble( name );
            }
            else if( type.equals( java.lang.Long.class ) ) {
                
                methodTypes[0] = java.lang.Long.class;
                methodArgs[0] = row.getLong( name );
            }
            else if( type.equals( java.util.Date.class ) ) {
                methodTypes[0] = java.util.Date.class;
                methodArgs[0] = row.getDate( name );
            }
            else {
                return false;
            }
        } catch( SQLException e ) {
            return false;
        }
        
        methodName = "set" + Util.toUpperCaseFirst( name );
        
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
        Iterator<ResultSetColumn> it = columnData.values().iterator();
        HashMap<String, String> fields = new HashMap<String, String>( columnData.values().size() );
        ResultSetColumn column;
        Object rawValue;

        while( it.hasNext() ) {
            column = it.next();
            rawValue = getFieldValue( column.getColumnName() );
            
            fields.put( column.getColumnName(), prepareForDB( rawValue, column.getType() ).toString() );
        }

        return fields;
    }

    protected Object prepareForDB( Object value, int dbType ) {
        Object returnValue = value;
        
        switch( dbType ) {
            case java.sql.Types.TINYINT:
            case java.sql.Types.BIT:
                if( value.getClass().equals( java.lang.Boolean.class ) ) {
                    returnValue = new Integer( value.equals( true ) ? 1 : 0 );
                }
            default:
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
    public Hashtable getColumnData() {
        return columnData;
    }

    /**
     * @return the driver
     */
    public DBDriver getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver( DBDriver driver ) {
        this.driver = driver;
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
}
