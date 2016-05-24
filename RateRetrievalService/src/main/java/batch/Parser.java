/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;

public class Parser {

	public int parse() throws IOException {
		
		File[] files = this.getFiles();
		for (File f : files) {
			JobOperator jobOperator = BatchRuntime.getJobOperator();
			Properties props = new Properties();
			String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			props.setProperty("timestamp", timestamp);
			props.setProperty("rate-file", f.getName());
			jobOperator.start("rates-parse", props);
		}
		return files.length;
	}

	public File[] getFiles() throws IOException {
		URL u = Thread.currentThread().getContextClassLoader().getResource("rates/");
		File dir = new File(u.getPath());
		return dir.listFiles();

	}

}
