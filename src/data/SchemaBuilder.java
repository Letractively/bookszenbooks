package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Handles generating and reading XML schemas for the business classes
 * that interact with the database.
 *
 * @author Rick Varella
 * @version 12.13.2009
 */
public class SchemaBuilder {
    private DBDriver driver;
    public static final String SCHEMA_PATH = "resources/schema/";

    public SchemaBuilder( DBDriver driver ) {
        this.driver = driver;
    }

    public SchemaData getSchema( String className, Field[] fields ) {
        SchemaData schema = null;
        HashMap<String, SchemaColumn> columns = new HashMap<String, SchemaColumn>();
        ClassLoader loader = this.getClass().getClassLoader();
        SchemaColumn column;
        InputStream schemaFile;
        Document xmlDocument;
        Element xmlRoot;
        Element columnRoot;
        Iterator<Element> columnIterator;
        String tableName = null;
        ArrayList<String> primaryKeys = new ArrayList<String>();
        
        try {
            //System.out.println( "FILENAME: " + SCHEMA_PATH + className.toLowerCase() + ".schema.xml" );
            schemaFile = loader.getResourceAsStream( SCHEMA_PATH + className.toLowerCase() + ".schema.xml" );

            /*if( schemaFile == null ) {
                throw new FileNotFoundException();
            }*/

            xmlDocument = parseSchema( schemaFile );
            xmlRoot = xmlDocument.getRootElement();
            columnIterator = xmlRoot.elementIterator();
            tableName = xmlRoot.attributeValue( "name" ) == null ? classToTableName( className ) : xmlRoot.attributeValue( "name" );

            //buildSchema( getMetaData( tableName ), schemaFile, className, tableName, fields );

            while( columnIterator.hasNext() ) {
                columnRoot = columnIterator.next();
                column = readSchemaColumn( columnRoot );
                columns.put( column.getName(), column );

                if( column.getIndex() != null && column.getIndex().equals( "pk" ) ) {
                    primaryKeys.add( column.getName() );
                }
            }
        } /*catch( FileNotFoundException e ) {
            throw new RuntimeException();
        }*/ catch( DocumentException e ) {
            throw new RuntimeException();
        }

        schema = new SchemaData( columns, tableName, primaryKeys.toArray( new String[ primaryKeys.size() ] ) );

        return schema;
    }

    public Document parseSchema( InputStream file ) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read( file );

        return document;
    }


    private SchemaColumn readSchemaColumn( Element container ) {
        int columnId = 0;
        int dbType = 0;
        String javaType = null;
        String columnName = null;
        String index = null;
        Iterator<Attribute> attrIterator = container.attributeIterator();
        Attribute attribute;

        while( attrIterator.hasNext() ) {
            attribute = ( Attribute )attrIterator.next();

            if( attribute.getName().equals( "dbType" ) ) {
                dbType = Integer.parseInt( attribute.getText() );
            }
            else if( attribute.getName().equals( "name" ) ) {
                columnName = attribute.getText();
            }
            else if( attribute.getName().equals( "javaType" ) ) {
                javaType = attribute.getText();
            }
            else if( attribute.getName().equals( "index" ) ) {
                index = attribute.getText();
            }
        }

        return new SchemaColumn( columnId, columnName, dbType, javaType, index );
    }

    private void buildSchema( ResultSet columns, InputStream schemaFile, String className, String tableName, Field[] fields ) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "table" );
        root.addAttribute( "name", tableName );
        root.addAttribute( "className", className );
        XMLWriter writer;
        Element field;
        HashMap<String, Field> fieldMap = new HashMap<String, Field>();
        int i;

        for( i = 0; i < fields.length; i++ ) {
            fieldMap.put( fields[ i ].getName(), fields[ i ] );
        }

        try {
            i = 0;

            while( columns.next() ) {System.out.println( columns.getString( "COLUMN_NAME" ) );
                field = root.addElement( "field" );
                field.addAttribute( "id", Integer.toString( i++ ) );
                field.addAttribute( "name", columns.getString( "COLUMN_NAME" ) );
                field.addAttribute( "dbType", columns.getString( "DATA_TYPE" ) );
                field.addAttribute( "javaType", fieldMap.get( columns.getString( "COLUMN_NAME" ) ).getType().toString() );
                
            }

            OutputFormat format = OutputFormat.createPrettyPrint();
            writer = new XMLWriter( System.out, format );
            //writer.write( document );
        } catch( SQLException e ) {

        } catch( IOException e ) {

        }

            /*writer = new XMLWriter( new FileOutputStream( schemaFile ) );
            writer.write( document );
            writer.close();*/
    }

    private ResultSet getMetaData( String tableName ) {
        DatabaseMetaData metaData;
        ResultSet schemas = null;

        try {
            metaData = driver.getConnection().getConnection().getMetaData();
            schemas = metaData.getColumns( driver.getConnection().getConfig().getDatabase(), null, tableName, null );
        } catch( SQLException e ) {
            throw new RuntimeException();
        }

        return schemas;
    }

    private String classToTableName( String className ) {
        return className.replace( "business.", "" ).replace( ".schema.xml", "" );
    }
}
