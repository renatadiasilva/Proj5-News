package pt.uc.dei.aor.paj.bean;

import javax.enterprise.context.SessionScoped;
//import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBException;

import pt.uc.dei.aor.paj.MetricData;
import pt.uc.dei.aor.paj.Report;
import pt.uc.dei.aor.paj.xml.JAXBXMLHandler;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class Teste implements Serializable {
	
	private static final long serialVersionUID = -5381236051617076780L;
	
	private String cnn;
	
	private Report rep;
	
	public Teste() {
		MetricData metricD = new MetricData();
		metricD.setMetricName("cpus_available");
		metricD.setTimestamp(BigInteger.valueOf(Long.parseLong("1308046204003")));
		metricD.setValue(BigDecimal.valueOf(0.0));
		metricD.setType("unit32");
		metricD.setSpoof("EDGITest|fusion:EDGITest|fusion");
		metricD.setDirection("dgsg|boinc");
		
		List<MetricData> listMD = new ArrayList<MetricData>();
		listMD.add(metricD);
		
		BigInteger ts = BigInteger.valueOf(Long.parseLong("1308046204104"));
		String tz = "GMT";
		BigDecimal vers = BigDecimal.valueOf(1.1);
		
		rep = new Report(listMD, ts, tz, vers);
		
	}
	
	public String demo() {

		cnn = "CNN!!!";
		try {
			//Marshalling: Writing Java object to XML file
			JAXBXMLHandler.marshal(rep, new File("report.xml"));

			//Unmarshalling: Converting XML content to Java objects
			Report rep2 = JAXBXMLHandler.unmarshal(new File("report.xml"));
			System.out.println(rep2);  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return null;
	}    

	public String doSomething() {
		cnn = "CNN!!!";
		return null;
	}

	public String getCnn() {
		return cnn;
	}

	public void setCnn(String cnn) {
		this.cnn = cnn;
	}

	public Report getRep() {
		return rep;
	}

	public void setRep(Report rep) {
		this.rep = rep;
	}

}