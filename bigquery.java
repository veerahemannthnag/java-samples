import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

public class BigQueryFetchData {

    public static void main(String[] args) {
        // Create a BigQuery client object.
        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

        // Create a query job configuration.
        QueryJobConfiguration queryConfig =
                new QueryJobConfiguration()
                        .setQuery("SELECT * FROM `my-project.my-dataset.my-table`");

        // Run the query job.
        TableResult result = bigquery.query(queryConfig);

        // Print the results of the query.
        for (TableRow row : result.iterateRows()) {
            System.out.println(row.toString());
        }
    }
}
