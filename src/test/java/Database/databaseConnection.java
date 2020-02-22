package Database;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.apache.poi.util.SystemOutLogger;
import org.junit.Test;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class databaseConnection {

//ec2-3-87-242-51.compute-1.amazonaws.com
//ec2-18-212-148-196.compute-1.amazonaws.com
String dbURL ="jdbc:oracle:thin:@ec2-18-212-148-196.compute-1.amazonaws.com:1521:xe";
String dbUsername = "hr";
String dbPassword = "hr";

/**
 * dbConnectionJDBC will  set a connection to ORAClE DB
 * connection takes  dbURL, dbUsername, dbPassword as parameters
 * Statement will allow us to scroll
 * ResultSet will execute query
 * Example in executeQuery: select * from employees
 * @throws SQLException
 * @Author Alex Rodriguez
 *
 */





@Test
    public void dbConnectionJDBC() throws SQLException {
    Connection con = DriverManager.getConnection(dbURL,dbUsername,dbPassword);

    Statement satatement =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    ResultSet resultSet = satatement.executeQuery("select * from employees");

    resultSet.last();
    int rowCount = resultSet.getRow();
    System.out.println("Row count :"+ rowCount);

     resultSet.first();
     while (resultSet.next()){
         System.out.println(resultSet.getString(3)
                 +" <-->"+ resultSet.getString("first_name")
                 + " "+resultSet.getString("last_name"));


 }
      resultSet.close();
     satatement.close();
     con.close();

}
/**
 * Whrite a quary that will print the count of employees working in department_id 100
 */
//1
@Test
    public  void dbConnectionJDBC1() throws SQLException{
    Connection con = DriverManager.getConnection(dbURL,dbUsername,dbPassword);

    Statement statement =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    ResultSet resultSet = statement.executeQuery("select count (*) from employees where department_id=100");

    resultSet.first();
    System.out.println(resultSet.getString(1));

    resultSet.close();
    statement.close();
    con.close();

}
//*
// Query the first 5 employees in the table
// */


//2
@Test
public  void countEmployeesInDep100() throws SQLException {
    Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    ResultSet resultSet = statement.executeQuery("select * from employees where rownum<=5");


    //resultSet.first();
    while(resultSet.next())
    System.out.println(resultSet.getString(1)+
            "<-->"+ resultSet.getString("first_name")+
            " "+ resultSet.getString("last_name"));

    resultSet.close();
    statement.close();
    con.close();
}

//3
@Test
public void dbMeTadata() throws SQLException {
    Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    String query = "select employee_id,last_name, job_id, salary from employees";
    ResultSet resultSet = statement.executeQuery(query);


    //Database SQL collect the metada
    DatabaseMetaData dbMetadata = con.getMetaData();
    System.out.println("USER: " + dbMetadata.getUserName());
    System.out.println("DataBase type :" + dbMetadata.getDatabaseProductName());

    //Result set metadata
    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    System.out.println("columns count" + resultSetMetaData.getColumnCount());
    System.out.println(resultSetMetaData.getColumnName(1));
    System.out.println(resultSetMetaData.getColumnName(4));


    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
        System.out.println(i + "-->" + resultSetMetaData.getColumnName(i));

    }
     List< Map<String, Object>> mapList = new ArrayList<>();
    ResultSetMetaData resultSetMetaData1 = resultSet.getMetaData();

    int colCount = resultSetMetaData1.getColumnCount();

    while (resultSet.next()){
        Map<String,Object>rowMap =new HashMap<>();
    //TODO loop and print results for column name and object
        for(int col =1; col<=colCount;col++){
            rowMap.put(resultSetMetaData1.getColumnName(col),resultSet.getObject(col));
        }
        mapList.add(rowMap);
    }
    for (Map<String,Object>map: mapList) {
        //System.out.println(map);
        System.out.println(map.get("SALARY"));
    }
    resultSet.close();
    statement.close();
    con.close();

}

}
