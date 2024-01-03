package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMap_example {

    String dbUrl = "jdbc:oracle:thin:@3.80.189.73:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run the query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

       //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list of keeping all rows a map
        List<Map<String, Object>> queryData=new ArrayList<>();

        Map<String, Object> row1=new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_PRES");
        System.out.println(row1.toString());

        Map<String, Object> row2=new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochhar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");
        System.out.println(row2.toString());

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the Steven last name directly from the list
        System.out.println(queryData.get(0).get("last_name "));

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
