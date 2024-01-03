package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl="jdbc:oracle:thin:@3.85.214.90:1521:xe";
    String dbUsername="hr";
    String dbPassword="hr";

    @Test
    public void MetaDataExample_dynamic() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet= statement.executeQuery("select first_name, last_name, salary, job_id from employees\n" + "where rownum <6");
        ResultSetMetaData rsMetadata=resultSet.getMetaData();

        //list of keeping all rows a map
        List<Map<String, Object>> queryData=new ArrayList<>();

        //number of columns
        int colCount=rsMetadata.getColumnCount();

        //loop through each row
        while (resultSet.next()) {
            Map<String, Object> row=new HashMap<>();
            for (int i=1; i<=colCount; i++) {
                row.put(rsMetadata.getColumnName(i), resultSet.getString(i));
            }
            queryData.add(row);
        }

        for (Map<String, Object> map:queryData) {
            System.out.println(map.toString());
        }


        resultSet.close();
        statement.close();
        connection.close();
    }
}
