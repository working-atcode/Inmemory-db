package main.java.Datastores;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private String databaseName;
    private Map<String, Table> tables = new HashMap<>();

    public Database(String databaseName) {
        this.databaseName = databaseName;
    }

    public void createTable(String tableName, List<Column> columns) {
        if(isTableExists(tableName)) {
            System.out.println("Tablename: " + tableName+ "already exists");
        } else {
            Table table = new Table(tableName, columns);
            this.tables.put(tableName, table);
        }

    }

    public void dropTable(String tableName) {
        if(!isTableExists(tableName)) {
            System.out.println("Tablename: " + tableName+ "does not exists");
        } else
            tables.remove(tableName);
    }

    public void truncateTable(String tableName) {
        if(isTableExists(tableName)) {
            Table table = tables.get(tableName);
            table.truncateRows();
        }
    }

    public void insertTableRows(String tableName, Map<Column, Object> columnValues) {
        if(isTableExists(tableName)){
            Table table = tables.get(tableName);
            table.insertRows(columnValues);
        }
    }

    public void printTableAllRows(String tableName) {
        if(isTableExists(tableName)) {
            Table table = tables.get(tableName);
            table.printRows();
        }
    }

    public void filterTableRecordByColumnValue(String tableName, Column column, Object value) {
        if(isTableExists(tableName)) {
            Table table = tables.get(tableName);
            table.getRecordByColumnValue(column, value);
        }
    }

    private boolean isTableExists(String tableName) {
        if(!tables.containsKey(tableName)) {
            System.out.println("Table name: " + tableName+ " does not exists");
            return false;
        }
        return true;
    }

    public String getDatabaseName() {
        return databaseName;
    }
}
