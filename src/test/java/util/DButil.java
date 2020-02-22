package util;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DButil {
    private static Connection connection;
    private static Statement statement;
    private  static ResultSet resultSet;


    public  static  void establishDBConnection(DBType dbutil) throws SQLException {
            switch (dbutil){
                case ORACLE:
                    connection= DriverManager.getConnection(ConfigReader.readProperty("dbURL"),ConfigReader.readProperty("dbUsername"),ConfigReader.readProperty("dbPassword"));
                    break;
                case MYSQL:
                    connection =null;
                    break;
                case MONGODB:
                    connection =null;
                    break;
                default:
                    connection=null;








            }
    }

    public static int getRowsCount(String sql) throws SQLException {
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet =statement.executeQuery(sql);
        resultSet.last();
        return resultSet.getRow();

    }
public static List<Map<String,Object>> runSQLQuery(String query) throws SQLException {
    statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    resultSet =statement.executeQuery(query);

    List<Map<String,Object>>list =new ArrayList<>();
    ResultSetMetaData rsMdata= resultSet.getMetaData();

    int colCount =rsMdata.getColumnCount();

    while (resultSet.next()){
        Map<String,Object> rowMap =new HashMap<>();
        for(int col=1; col<= colCount;col++){
            rowMap.put(rsMdata.getColumnName(col),resultSet.getObject(col));
        }
        list.add(rowMap);

    }
    return list;
}
    public static void closeConnections() { // good example for explaining try and catch
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();   //first it will try, if rS, s, con is not null, close it
            }                   // if its null, goes to catch
        } catch (Exception e) {
            e.printStackTrace(); // prints the error, what kind of error u r getting
        }
    }
}

