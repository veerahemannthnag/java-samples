
import java.io.FileInputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
//import com.google.cloud.bigquery.QueryJobConfiguration.Builder;
import com.google.cloud.bigquery.TableResult;

public class Main {
    public static void main(String[] args) throws Exception {
        // Set your Google Cloud credentials file path
        String credentialsFilePath = "java/bqsa.json";

        // Initialize the BigQuery client with credentials
        BigQuery bigQuery = BigQueryOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(credentialsFilePath)))
                .build()
                .getService();

        // Your SQL query
        String query = "SELECT * FROM `project-2021-vmw.mydataset.my_table` LIMIT 1000";

        // Create a query job configuration
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query)
                .setUseLegacySql(false) // Set to true if you're using Legacy SQL
                .build();

        // Create a job ID, optionally set a job name
        JobId jobId = JobId.newBuilder().setLocation("us").build();

        // Create the query job
        Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        // Wait for the job to complete
        queryJob = queryJob.waitFor();

        if (queryJob.getStatus().getError() == null) {
            // Job completed successfully
            TableResult result = queryJob.getQueryResults();
            for (com.google.cloud.bigquery.FieldValueList row : result.iterateAll()) {
                // Process each row of data
                System.out.println(row);
            }
        } else {
            // Handle error
            System.err.println("Job failed with error: " + queryJob.getStatus().getError());
        }
    }
}
