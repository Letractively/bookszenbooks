package business;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Book extends DBObject implements Serializable {

    private long isbn;
    private int categoryId;
    private String title;
    private String author;
    private String publisher;
    private Date publishDate;
    private int pages;
    private String language;
    private BookCategory category;
    private String tableName = "book";
    private String[] primaryKeys = { "isbn" };
    
    @Override
    public boolean populate(ResultSet row) throws SQLException {
        return super.populate( row );
    }

    @Override
    public boolean save() {
        return super.save();
    }

    @Override
    protected boolean remove() {
        return driver.delete( tableName, formatPKWhere( primaryKeys ) ) > 0;
    }

    @Override
    protected boolean insert() {
        return driver.insert( tableName, getDatabaseFields() ) > 0;
    }

    @Override
    protected boolean update() {
        return driver.update( tableName, getDatabaseFields(), formatPKWhere( primaryKeys ) ) > 0;
    }
}
