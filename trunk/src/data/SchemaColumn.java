package data;

/**
 *
 * @author Rick
 */
public class SchemaColumn {
    private int id;
    private String name;
    private int dbType;
    private String javaType;
    private String index;

    public SchemaColumn( int columnId, String columnName, int type, String javaType, String index  ) {
        this.id = columnId;
        this.name = columnName;
        this.dbType = type;
        this.javaType = javaType;
        this.index = index;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the dbType
     */
    public int getDbType() {
        return dbType;
    }

    /**
     * @return the javaType
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * @return the index
     */
    public String getIndex() {
        return index;
    }
}
