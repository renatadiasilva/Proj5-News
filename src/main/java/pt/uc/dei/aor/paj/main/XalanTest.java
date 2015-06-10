package pt.uc.dei.aor.paj.main;

//import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XalanTest {

	public static void main(String[] args) throws TransformerException {

	    StreamSource xlsStreamSource = new StreamSource(Paths
	            .get("report_to_html.xsl")
	            .toAbsolutePath().toFile());

//	    StreamSource xlsStreamSource = new StreamSource(new File("C:\\Users\\Renata\\Desktop\\projeto5\\Proj5-News\\report_to_html.xsl"));
//	    StreamSource xmlStreamSource = new StreamSource(new File("C:\\Users\\Renata\\Desktop\\projeto5\\Proj5-News\\test.xml"));
	    StreamSource xmlStreamSource = new StreamSource(Paths
	            .get("test.xml")
	            .toAbsolutePath().toFile());

	    TransformerFactory transformerFactory = TransformerFactory.newInstance(
	            "org.apache.xalan.processor.TransformerFactoryImpl", null);

	    Path pathToHtmlFile = Paths.get("src/main/webapp/report.html");
	    StreamResult result = new StreamResult(pathToHtmlFile.toFile());
//	    StreamResult result = new StreamResult(new File("C:\\Users\\Renata\\Desktop\\projeto5\\Proj5-News\\src\\main\\webapp\\

	    Transformer transformer = transformerFactory.newTransformer(xlsStreamSource);
	    transformer.transform(xmlStreamSource, result);

	}
}
