package business;

import data.DBDriver;
import data.SchemaBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSubject extends DBObject implements Serializable {

    private int subjectId;
    private String text;
    private String i18nText;

    public BookSubject() { }

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
     * @return the subjectId
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the i18nText
     */
    public String getI18nText() {
        return i18nText;
    }

    /**
     * @param i18nText the i18nText to set
     */
    public void setI18nText(String i18nText) {
        this.i18nText = i18nText;
    }
}
