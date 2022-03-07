package test;

import main.java.Datastores.Database;
import org.junit.Assert;
import org.junit.Test;

public class TableTest {
    @Test
    public void demoTest() {
        Database db = new Database("db_name");
        Assert.assertEquals(db.getDatabaseName(), "db_name");

    }

}
