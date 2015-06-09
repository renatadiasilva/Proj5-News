package pt.uc.dei.aor.paj.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pt.uc.dei.aor.paj.Report;

public class JAXBXMLHandler {
 
    // Export: Marshalling
    public static void marshal(Report rep, File selectedFile)
            throws IOException, JAXBException {
        JAXBContext context;
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter(selectedFile));
        context = JAXBContext.newInstance(Report.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(rep, writer);
        writer.close();
    }
 
    // Import: Unmarshalling
    public static Report unmarshal(File importFile) throws JAXBException {
        Report metricD = null;
        JAXBContext context;
 
        context = JAXBContext.newInstance(Report.class);
        Unmarshaller um = context.createUnmarshaller();
        metricD = (Report) um.unmarshal(importFile);
 
        return metricD;
    }
}