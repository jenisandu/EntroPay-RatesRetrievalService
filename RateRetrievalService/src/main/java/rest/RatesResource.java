package rest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import jpa.Rate;
import util.Rates;

@Path("rates")
public class RatesResource{

	private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
	@Context
	private UriInfo context;
	
	@PersistenceContext
	protected EntityManager em;
	
	@Context
	Request request;

	String id;
	
	public RatesResource(){
	}
	
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Rates getXml(){
		
		Query q = em.createNamedQuery("Rate.findAll");
				
		List<Rate> rates = q.getResultList();
		
		Rates ratesObj = new Rates();
		ratesObj.setRates(rates);        
        return ratesObj;

	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("{date}")
	public Rates getRatesByDate(@PathParam("date") String date){
		System.out.println(date);
		LocalDate dat = LocalDate.parse(date,format);
		Query q = em.createNamedQuery("Rate.findByRateDate");
		
		Date d = Date.from(dat.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		q.setParameter("rateDate", sqlDate);
		
		
		List<Rate> rates = q.getResultList();
		
		Rates ratesObj = new Rates();
		ratesObj.setRates(rates);        
        return ratesObj;	}
	
}
