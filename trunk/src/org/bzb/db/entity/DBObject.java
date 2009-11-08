package org.bzb.db.entity;

import java.sql.ResultSet;
import java.util.Hashtable;
import org.bzb.db.DBDriver;

public class DBObject {

    private Hashtable columnData;
    private boolean isDirty;
    private boolean isNew;
    private DBDriver driver;

    public boolean populate(ResultSet row) {
        throw new UnsupportedOperationException();
    }

    private int initMetaData() {
        throw new UnsupportedOperationException();
    }

    private boolean setField(String name, String type, ResultSet row) {
        throw new UnsupportedOperationException();
    }

    public boolean save() {
        throw new UnsupportedOperationException();
    }

    public boolean remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return the columnData
     */
    public Hashtable getColumnData() {
        return columnData;
    }

    /**
     * @return the isDirty
     */
    public boolean isIsDirty() {
        return isDirty;
    }

    /**
     * @return the isNew
     */
    public boolean isIsNew() {
        return isNew;
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
    public void setDriver(DBDriver driver) {
        this.driver = driver;
    }
}
