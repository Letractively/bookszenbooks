package business;

import data.DBDriver;
import data.SchemaBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Book extends DBObject implements Serializable {

    private String isbn;
    private int subjectId;
    private String title;
    private String author;
    private String publisher;
    private Date publishDate;
    private int pages;
    private String language;
    private int edition;
    private String format;
    private BookSubject subject;
    
    public Book() {
        this.subject = new BookSubject();
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
        schema = builder.getSchema( className, fields );
    }

    /**
     * Populates the object with the ResultSet cursor content.
     * @param row The ResultSet cursor to populate the object with.
     * @return True on success, false otherwise.
     * @throws SQLException
     */
    @Override
    public boolean populate( ResultSet row ) throws SQLException {
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
        return driver.delete( schema.getTableName(), formatPKWhere( schema.getPrimaryKeys() ) ) > 0;
    }

    /**
     * Inserts a record into the database containing the content of this object.
     * @return True if the record has been inserted successfully, false otherwise.
     */
    protected boolean insert() {
        int affectedRows;

        if( ( affectedRows = driver.insert( schema.getTableName(), getDatabaseFields() ) ) > 0 ) {
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

        if( ( affectedRows = driver.update( schema.getTableName(), getDatabaseFields(), formatPKWhere( schema.getPrimaryKeys() ) ) )  > 0 ) {
            setDirty( false );
        }

        return affectedRows > 0;
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
     * @return the categoryId
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the publishDate
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the edition
     */
    public int getEdition() {
        return edition;
    }

    /**
     * @param edition the edition to set
     */
    public void setEdition(int edition) {
        this.edition = edition;
    }

    /**
     * @return the category
     */
    public BookSubject getSubject() {
        return subject;
    }

    /**
     * @param category the category to set
     */
    public void setSubject( BookSubject subject ) {
        this.subject = subject;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }
}
