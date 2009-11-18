package business;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LexiconEntry extends DBObject implements Serializable {

    private String key;
    private String topic;
    private String language;
    private String value;
    private String tableName = "lexiconentry";
    private String[] primaryKeys = { "key", "topic", "language" };

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
