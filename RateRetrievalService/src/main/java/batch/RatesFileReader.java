package batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("RatesFileReader")
public class RatesFileReader extends AbstractItemReader {

	@Inject
	JobContext jobContext;
	
	private BufferedReader reader;
	private int recordNumber = 0;

	@Override
	public void open(Serializable checkpoint) throws Exception {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
		String resourceName = (String) jobParameters.get("rate-file");

		reader = new BufferedReader(new InputStreamReader(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("rates/" + resourceName)));
		
		System.out.println("reader: " + reader);
		
		if (checkpoint != null) {
			System.out.println("Checkpoint found @" + checkpoint);
			recordNumber = (Integer) checkpoint;
		}

		for (int i = 1; i < recordNumber; i++) {
			reader.readLine();
		}

		System.out.println("Opened file "+ resourceName +" for reading at line number: " + recordNumber + " job id:," + jobContext.getExecutionId());

	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return recordNumber;
	}

	@Override
	public Object readItem() throws Exception {
		Object res = null;
		String line = reader.readLine();
		if (line != null) {
			recordNumber++;
			res = line.trim();
		}

		return res;
	}

}
