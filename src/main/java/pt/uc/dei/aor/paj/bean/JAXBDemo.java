package pt.uc.dei.aor.paj.bean;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import pt.uc.dei.aor.paj.MetricData;
import pt.uc.dei.aor.paj.Report;
import pt.uc.dei.aor.paj.xml.JAXBXMLHandler;

public class JAXBDemo {

	private Report rep;
	
	public JAXBDemo() {
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
	
	public void demo() {

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
	}    
}