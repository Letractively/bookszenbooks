package business;

import data.DBDriver;
import data.SchemaBuilder;
import java.util.Date;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookListing extends DBObject implements Serializable {

    private int userId;
    private int listId;
    private String isbn;
    private double price;
    private String comment;
    private String currency;
    private Date listDate;
    private String condition;
    private boolean active;
    private Book book;
    private User user;
    private String tableName = "booklisting";
    private String[] primaryKeys = { "listId" };

    public BookListing() {
        this.userId = 0;
        this.book = new Book();
        this.user = new User();
    }

    /**
     * Initialize the object. This must be called prior to any database-related methods.
     * @param driver The database driver instance.
     */
    @Override
    public void init( DBDriver driver ) {
        super.init( driver );

        String className = this.getClass().getName();
        Field[] fields = this.getClass().getDeclaredFields();
        SchemaBuilder builder = new SchemaBuilder( driver );
        schema = builder.getSchema( className, tableName, fields );

        /* Initialize related objects */
        book.init( driver );
        user.init( driver );
    }

    /**
     * Populates the object with the ResultSet cursor content.
     * @param row The ResultSet cursor to populate the object with.
     * @return True on success, false otherwise.
     * @throws SQLException
     */
    @Override
    public boolean populate( ResultSet row ) throws SQLException {
        book.populate( row );
        user.populate( row );

        return super.populate( row );
    }

    /**
     * Saves the object to the database.
     * @return True if the object has been saved successfully, false otherwise.
     */
    @Override
    public boolean save() {
        return super.save();
    }

    /**
     * Removes the database record matching this object's primary key content fields.
     * @return True if the record has been removed successfully, false otherwise.
     */
    public boolean remove() {
        return driver.delete( tableName, formatPKWhere( primaryKeys ) ) > 0;
    }

    /**
     * Inserts a record into the database containing the content of this object.
     * @return True if the record has been inserted successfully, false otherwise.
     */
    protected boolean insert() {
        int affectedRows;

        if( ( affectedRows = driver.insert( tableName, getDatabaseFields() ) ) > 0 ) {
            setNewObject( false );
        }

        return affectedRows > 0;
    }

    /**
     * Updates the database record matching this object's primary key content fields.
     * @return True if the record has been updated successfully, false otherwise.
     */
    protected boolean update() {
        int affectedRows;

        if( ( affectedRows = driver.update( tableName, getDatabaseFields(), formatPKWhere( primaryKeys ) ) )  > 0 ) {
            setDirty( false );
        }

        return affectedRows > 0;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the listDate
     */
    public Date getListDate() {
        return listDate;
    }

    /**
     * @param listDate the listDate to set
     */
    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn( String isbn ) {
        this.isbn = isbn;
    }

    /**
     * @return the listId
     */
    public int getListId() {
        return listId;
    }

    /**
     * @param listId the listId to set
     */
    public void setListId(int listId) {
        this.listId = listId;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
