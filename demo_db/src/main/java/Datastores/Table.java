package main.java.Datastores;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Table {
    private Integer autoIncrementId;
    private String name;
    private Map<String, Column> columnMap = new HashMap<>();
    List<Row> rows = new ArrayList<>();

    public Table(String tableName, List<Column> columns) {
        this.autoIncrementId = 1;
        this.name = tableName;
        populateColumnMap(columns);
    }

    public void truncateRows() {
        this.rows.clear();
    }

    public void insertRows(Map<Column, Object> columnValues) {
        for(Column column : columnValues.keySet()) {
            if(!isColumnExists(column.getColumnName()))
                return;
        }
        Integer rowId = getAutoIncrementId();
        Map<Column, Object> columnData = new HashMap<>(columnValues);
        Row row = new Row(rowId, columnData);
        this.rows.add(row);
    }

    public void printRows() {
        System.out.println("Printing all rows of the table: "+this.name);
        printRecords(rows);
    }

    public void getRecordByColumnValue(Column column, Object value) {
        List<Row> records = new ArrayList<>();
        for(Row row : this.rows) {
            Object columnValue = row.getColumnData().get(column);
            if(columnValue.equals(value))
                records.add(row);
        }
        System.out.println("Printing matching rows for the table: " + this.name);
        printRecords(records);
    }

    private void populateColumnMap(List<Column> columns) {
        for(Column c : columns) {
            columnMap.put(c.getColumnName(), c);
        }
    }

    private synchronized Integer getAutoIncrementId() {
        return this.autoIncrementId++;
    }

    private boolean isColumnExists(String columnName) {
       if(!columnMap.containsKey(columnName)){
           System.out.println("Table name: " + this.name+ "does not contains column: " + columnName);
           return false;
       }
        return true;
    }

    private void printRecords(List<Row> rows) {
        System.out.print("\t");
        for(Map.Entry<String,Column> col : columnMap.entrySet()) {
            System.out.print("\t"+col.getKey()+"\t");
        }
        for(Row row : rows) {
            System.out.print("\n\t"+row.getRowId()+".");
            for(Map.Entry<Column,Object> entry : row.getColumnData().entrySet()) {
                System.out.print("\t" + entry.getValue() + "\t");
            }
            System.out.println();
        }
    }


}
