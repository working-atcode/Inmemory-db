package main.java;

import main.java.Datastores.Column;
import main.java.Datastores.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String tableName = "Employee";

    public static void main(String[] args) {

        Column name = new Column("name", Column.Type.STRING);
        Column age = new Column("age", Column.Type.INT);
        Column salary = new Column("salary", Column.Type.INT);
        Database db = new Database("MyDB");
        List<Column> columns = new ArrayList();
        columns.add(name);
        columns.add(age);
        columns.add(salary);
        db.createTable(tableName,columns);
        Map<Column,Object> columnValues = new HashMap();
        columnValues.put(name, "John");
        columnValues.put(age, 25);
        columnValues.put(salary, 10000);

        db.insertTableRows(tableName,columnValues);
        columnValues.clear();
        columnValues.put(name, "Kim");
        columnValues.put(age, 28);
        columnValues.put(salary, 12000);
        db.insertTableRows(tableName,columnValues);
        db.printTableAllRows(tableName);
        db.filterTableRecordByColumnValue(tableName, age, 28);

        db.filterTableRecordByColumnValue(tableName, name, "John");
        db.truncateTable(tableName);
        db.dropTable(tableName);
        db.printTableAllRows(tableName);
    }
}
