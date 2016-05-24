package batch;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import jpa.Rate;

@Named("RatesFileProcessor")
public class RatesFileProcessor implements ItemProcessor {

	@Inject
	JobContext jobContext;
	
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");

	@Override
	public Object processItem(Object arg0) throws Exception {
		if (arg0 == null) {
			return null;
		}
		
		Rate record = null;
		String line = (String) arg0;
		
		if (line.length() == 0) {
			return null;
		}
		if (line.length() != 22 || line.length()==0) {
			System.out.println("Invalid entry " + line.length());
			return null;
		}
		
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
		String resourceName = (String) jobParameters.get("rate-file");

		
		System.out.println("processItem: " + line);

		try {
			LocalDate date = LocalDate.parse(line.substring(0, 8),format);
			
			String value = line.substring(8, 16);
			String buyCurrency = line.substring(16, 19);
			String sellCurrency = line.substring(19, 22);
			BigDecimal bd = new BigDecimal(value);
			bd = bd.divide(new BigDecimal(100000));
			Rate rate = new Rate();
			rate.setBuyFormat(buyCurrency);
			rate.setSellFormat(sellCurrency);
			Date d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			java.sql.Date sqlDate = new java.sql.Date(d.getTime());
			rate.setRateDate(sqlDate);
			rate.setRateValue(bd);
			rate.setFileN(resourceName);

			record = rate;
			
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		return record;
	}

}
