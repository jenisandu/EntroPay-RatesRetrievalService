package jpa;

import java.math.BigDecimal;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rate.class)
public abstract class Rate_ {

	public static volatile SingularAttribute<Rate, BigDecimal> rateValue;
	public static volatile SingularAttribute<Rate, Date> rateDate;
	public static volatile SingularAttribute<Rate, String> sellFormat;
	public static volatile SingularAttribute<Rate, String> fileN;
	public static volatile SingularAttribute<Rate, Integer> rateId;
	public static volatile SingularAttribute<Rate, String> buyFormat;

}

