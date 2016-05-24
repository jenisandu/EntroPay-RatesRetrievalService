package rest;

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

@Path("rate")
public class RateResource{


	@Context
	private UriInfo context;
	
	@PersistenceContext
	protected EntityManager em;
	
	@Context
	Request request;

	String id;
	
	public RateResource(){
	}
	
	public RateResource(UriInfo uriInfo, Request request, String id){
		this.context = uriInfo;
		this.request = request;
		this.id = id;
		System.out.println(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("{rate}")
	public Rate getXml(@PathParam("rate") String id){
		if (id == null && this.id != null) {
			id = this.id;
		}
		System.out.println(id);
		Query q = em.createNamedQuery("Rate.findByRateId");
		q.setParameter("rateId", new Integer(id));
		
		Rate rate = (Rate) q.getSingleResult();
		        
        return rate;

	}
	
	
}
