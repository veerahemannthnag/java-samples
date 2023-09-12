import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimbaBigQueryExample {
    public static void main(String[] args) {
        try {
            // Register the Simba BigQuery JDBC driver
            Class.forName("com.simba.googlebigquery.jdbc.Driver");

            // Set up the JDBC connection URL with your BigQuery project and credentials
            String jdbcUrl = "jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;ProjectId=YOUR_PROJECT_ID;OAuthType=0;OAuthServiceAcctFilename=YOUR_CREDENTIALS_FILE.json";

            // Create a connection to BigQuery
            Connection connection = DriverManager.getConnection(jdbcUrl);

            // Create a statement for executing SQL queries
            Statement statement = connection.createStatement();

            // Execute your SQL query to fetch data from BigQuery
            String sqlQuery = "SELECT * FROM your_table_name";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Process the results
            while (resultSet.next()) {
                // Access columns using resultSet.getXXX() methods
                // Example:
                String columnName = resultSet.getString("column_name");
                System.out.println(columnName);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
