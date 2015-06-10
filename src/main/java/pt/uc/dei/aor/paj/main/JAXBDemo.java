package pt.uc.dei.aor.paj.main;

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
	public static void main(String[] args) {

		MetricData metricD = new MetricData();
		metricD.setMetricName("cpus_available");
		metricD.setTimestamp(BigInteger.valueOf(Long.parseLong("1308046204003")));
		metricD.setValue(BigDecimal.valueOf(0.0));
		metricD.setType("unit32");
		metricD.setSpoof("EDGITest|fusion:EDGITest|fusion");
		metricD.setDirection("dgsg|boinc");
		
		List<MetricData> listMD = new ArrayList<MetricData>();
		listMD.add(metricD);
		
		metricD = new MetricData();
		metricD.setMetricName("gflops");
		metricD.setTimestamp(BigInteger.valueOf(Long.parseLong("1308046204056")));
		metricD.setValue(BigDecimal.valueOf(0.0));
		metricD.setType("float");
		metricD.setSpoof("EDGITest|fusion:EDGITest|fusion");
		metricD.setDirection("dgsg|boinc");
		listMD.add(metricD);
		
		metricD = new MetricData();
		metricD.setMetricName("past_workunits");
		metricD.setTimestamp(BigInteger.valueOf(Long.parseLong("1308046204058")));
		metricD.setValue(BigDecimal.valueOf(0.0));
		metricD.setType("unit32");
		metricD.setUnits("wus");
		metricD.setSpoof("EDGITest|fusion:EDGITest|fusion");
		metricD.setDirection("dgsg|boinc");
		listMD.add(metricD);

		BigInteger ts = BigInteger.valueOf(Long.parseLong("1308046204104"));
		String tz = "GMT";
		BigDecimal vers = BigDecimal.valueOf(1.1);
		
		Report rep = new Report(listMD, ts, tz, vers);

		try {
			//Marshalling: Writing Java object to XML file
			JAXBXMLHandler.marshal(rep, new File("C:\\Users\\Renata\\Desktop\\report.xml"));
			JAXBXMLHandler.marshalToConsole(rep);

			//Unmarshalling: Converting XML content to Java objects
			Report rep2 = JAXBXMLHandler.unmarshal(new File("C:\\Users\\Renata\\Desktop\\report.xml"));
//			Report rep2 = JAXBXMLHandler.unmarshal(new File("C:\\Users\\Renata\\Desktop\\projeto5\\Proj5-News\\test.xml"));
			System.out.println(rep2);  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}