package util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateSerializer extends XmlAdapter<String, java.sql.Date>{
	
	public String marshal(java.sql.Date date){
		
		Date d = new Date(date.getTime());
		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),ZoneId.systemDefault());		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return ldt.format(formatter);
		
	}

	@Override
	public java.sql.Date unmarshal(String v) throws Exception {
		// Intentionally left empty
		return null;
	}

}
