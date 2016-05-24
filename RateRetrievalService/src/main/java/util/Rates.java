
package util;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import jpa.Rate;

@XmlRootElement(name = "rates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rates {
	
	@XmlElement(name="rate")
	private List<Rate> rates = null;	
    
    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }    
}