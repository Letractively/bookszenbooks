package data;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DBConfig {
    private String database;
    private String user;
    private String password;
    private String charset;
    private String server;
    private String driver;
    private String platform;

    public DBConfig( String configResource ) throws FileNotFoundException {
        URL configFile = this.getClass().getResource( configResource ); // "dbConfig.xml"
        Document xmlDocument;
        Element xmlRoot;

        if( configFile == null ) {
            throw new FileNotFoundException();
        }
        
        try {
            xmlDocument = parseXML( configFile );
        } catch( DocumentException e ) {
            throw new RuntimeException();
        }
        
        xmlRoot = xmlDocument.getRootElement();

        readXML( xmlRoot );
    }

    public Document parseXML( URL file ) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read( file );

        return document;
    }

    public void readXML( Element container ) {
        Element xmlChild;
        Iterator iterator = container.elementIterator();
        String childName;

        while( iterator.hasNext() ) {
            xmlChild = ( Element ) iterator.next();
            childName = xmlChild.getName();

            if( childName.equals( "database" ) ) {
                database = xmlChild.getText();
            }
            else if( childName.equals( "server" ) ) {
                server = xmlChild.getText();
            }
            else if( childName.equals( "user" ) ) {
                user = xmlChild.getText();
            }
            else if( childName.equals( "password" ) ) {
                password = xmlChild.getText();
            }
            else if( childName.equals( "charset" ) ) {
                charset = xmlChild.getText();
            }
            else if( childName.equals( "driver" ) ) {
                driver = xmlChild.getText();
            }
            else if( childName.equals( "platform" ) ) {
                platform = xmlChild.getText();
            }
        }
    }

    /**
     * @return the username
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @return the driverClass
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }
}
