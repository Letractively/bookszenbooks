package business;

import java.util.Date;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookListing extends DBObject implements Serializable {

    private int userId;
    private Book book;
    private User user;
    private double price;
    private String comment;
    private String currency;
    private int edition;
    private Date listDate;
    private String condition;
    private boolean isActive;
    private int isbn;
    private String tableName = "booklisting";
    private String[] primaryKeys = { "listId" };

    @Override
    public boolean populate( ResultSet row ) throws SQLException {
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
