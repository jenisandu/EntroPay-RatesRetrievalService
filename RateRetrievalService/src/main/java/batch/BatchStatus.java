package batch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "status")
public class BatchStatus {
	
	@XmlElement
	public int numberOfFiles;
	
	public BatchStatus(){
		
	}
	
	public BatchStatus(int nbrFiles){
		this.numberOfFiles = nbrFiles;
	}

}
