package Database;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


import util.ConfigReader;
import util.DBType;
import util.DButil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UsingDBUtil {

    @BeforeClass
    public  void setUp() throws SQLException {
        DButil.establishDBConnection(DBType.ORACLE);
    }

    @AfterClass
    public void claseConnections() throws SQLException {
        DButil.closeConnections();

    }
    //Query department name from department

    @Test
    public  void test1() throws SQLException {
        //select department_name from departments
       List<Map<String,Object>> result=DButil.runSQLQuery("select department_name from department");
        System.out.println(result);
        System.out.println(result.get(1));
    }

    //query first name and last name from employees table for those employees whos employee id =105

    @Test
    public void test() throws SQLException {
        //query first name and last name from employees table for those employees whos employee id =105
       String query= "select first_name,last_name from employees where employee_id=105";
        List<Map<String,Object>> result=DButil.runSQLQuery(query);

        //Assert first_name ="David" last_name ="Austin"
        Assert.assertEquals(result.get(0).get("FIRST_NAME"),"David");
        Assert.assertEquals(result.get(0).get("LAST_NAME"),"Austin");
    }
    @Test
    public void test3() throws SQLException {

        String query="select country_id from countries where country_id= 'BR'";
        List<Map<String,Object>> result=DButil.runSQLQuery(query);
        Assert.assertEquals(result.get(0).get("COUNTRY_ID"),"BR");
    }
}
