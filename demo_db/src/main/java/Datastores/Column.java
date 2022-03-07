package main.java.Datastores;

public class Column {
    public enum Type {
        INT,
        STRING;
    }
    private String columnName;
    private Type columnType;

    public Column(String columnName, Type columnType) {
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return this.columnName;
    }
}


