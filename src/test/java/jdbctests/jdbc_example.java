package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

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

        //how to find how many rows we have for the query
        //go to last row
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        //we need to move before first row to get full list since we are at the last row right now
        resultSet.beforeFirst();
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        resultSet=statement.executeQuery("Select * from regions");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run the query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get Database Metadata
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User = " + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());

        //get resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //column names
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        //print all the column name dynamically
        for(int i=1; i<=colCount; i++){
            System.out.println(rsMetadata.getColumnName(i));
        }
        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
