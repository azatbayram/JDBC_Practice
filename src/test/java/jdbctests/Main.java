package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@3.80.189.73:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement();
        //run the query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from regions");

//        //move pointer to first row
//        resultSet.next();
//        System.out.println(resultSet.getString(1));
//        System.out.println(resultSet.getString("region_name"));
//        System.out.println(resultSet.getString("region_id"));
//        System.out.println(resultSet.getString(2));
//
//        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString(2));
//
//        //move pointer second row
//        resultSet.next();
//        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString(2));

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();



    }
}
