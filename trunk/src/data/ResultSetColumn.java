package data;

/**
 *
 * @author Rick
 */
public class ResultSetColumn {
    private int columnId;
    private String columnName;
    private int type;

    public ResultSetColumn( int columnId, String columnName, int type ) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.type = type;
    }

    /**
     * @return the columnId
     */
    public int getColumnId() {
        return columnId;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }
}
