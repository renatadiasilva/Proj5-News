package pt.uc.dei.aor.paj.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pt.uc.dei.aor.paj.data1.Class1;

public class JavaFirstHandler {
		 
	    // Export: Marshalling
	    public static void marshal(Class1 cla, File selectedFile)
	            throws IOException, JAXBException {
	        JAXBContext context;
	        BufferedWriter writer = null;
	        writer = new BufferedWriter(new FileWriter(selectedFile));
	        context = JAXBContext.newInstance(Class1.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        m.marshal(cla, writer);
	        writer.close();
	    }
	    
	    // Export: Marshalling to Console
	    public static void marshalToConsole(Class1 cla)
	            throws IOException, JAXBException {
	        JAXBContext context;
	        context = JAXBContext.newInstance(Class1.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        m.marshal(cla, System.out);
	    }

	 
	    // Import: Unmarshalling
	    public static Class1 unmarshal(File importFile) throws JAXBException {
	        Class1 cla = null;
	        JAXBContext context;
	 
	        context = JAXBContext.newInstance(Class1.class);
	        Unmarshaller um = context.createUnmarshaller();
	        cla = (Class1) um.unmarshal(importFile);
	 
	        return cla;
	    }
	}