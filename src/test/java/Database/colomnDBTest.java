package Database;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class colomnDBTest {

    String dbURL = "jdbc:oracle:thin:@ec2-18-212-148-196.compute-1.amazonaws.com:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    //query the details for employees whos employee ID in 150 or 160


    @Test
    public void getId() throws SQLException {

        Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String query="select * from employees where employee_id in (150,160)";
        ResultSet resultSet = statement.executeQuery(query);
        //ResultSetMetaData resultSetMetaData =resultSet.getMetaData();


        List<Map<String, Object>> mapList = new ArrayList<>();
        ResultSetMetaData resultSetMetaData1 = resultSet.getMetaData();

        int colCount = resultSetMetaData1.getColumnCount();

        while (resultSet.next()){
            Map<String,Object>rowMap =new HashMap<>();

            for(int col =1; col<=colCount;col++){
                rowMap.put(resultSetMetaData1.getColumnName(col),resultSet.getObject(col));
            }
            mapList.add(rowMap);
        }
        for (Map<String,Object>map: mapList) {
            System.out.println(map);

        }
        resultSet.close();
        statement.close();
        con.close();

    }


    }

