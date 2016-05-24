package batch;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.Rate;

@Dependent
@Named("RatesFileWriter")
public class RatesFileWriter extends AbstractItemWriter {

    @PersistenceContext(unitName="rates")
    EntityManager em;

	@Override
	public void writeItems(List<Object> list) throws Exception {
        System.out.println("writeItems: " + list);
        for (Object rate : list) {
        	Rate r = (Rate) rate;
            em.persist(r);
        }
    }

}
