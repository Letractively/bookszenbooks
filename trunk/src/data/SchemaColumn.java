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

    public SchemaColumn( int columnId, String columnName, int type, String javaType ) {
        this.id = columnId;
        this.name = columnName;
        this.dbType = type;
        this.javaType = javaType;
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
}
