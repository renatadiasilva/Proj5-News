package pt.uc.dei.aor.paj.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pt.uc.dei.aor.paj.data4.Class1;

public class JavaFirstHandler3 {
		 
	    // Export: Marshalling
	    public static void marshal(Class1 cla, File selectedFile)
	            throws IOException, JAXBException {
	        JAXBContext context;
	        BufferedWriter writer1 = null;
	        writer1 = new BufferedWriter(new FileWriter(selectedFile));
//	        writer1.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//	        writer1.newLine();
//  	        writer1.write("<?xml-stylesheet type=\"text/xsl\" href=\"text.xsl\"?>");
//	        writer1.newLine();
	        context = JAXBContext.newInstance(Class1.class);
	        Marshaller m = context.createMarshaller();
//	        javax.xml.bind.annotation
	        m.setProperty("com.sun.xml.internal.bind.xmlHeaders", 
	        	    "\n<?xml-stylesheet type=\"text/xsl\" href=\"text.xsl\"?>");
//	        m.setProperty(Marshaller.JAXB_FRAGMENT, true);
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        m.marshal(cla, writer1);
	        writer1.close();
	    
	    
//	        StringWriter writer = new StringWriter();
	      //add processing instructions "by hand" with escaped quotation marks
	      //or single marks

	      //create and configure marshaller to leave out processing instructions
	      Marshaller marshaller = context.createMarshaller();
	      marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);	    
	    
	    
	    
	    
	    }
	    
	    // Export: Marshalling to Console
	    public static void marshalToConsole(Class1 cla)
	            throws IOException, JAXBException {
	        JAXBContext context;
	        context = JAXBContext.newInstance(Class1.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty("com.sun.xml.internal.bind.xmlHeaders", 
	        	    "\n<?xml-stylesheet type=\"text/xsl\" href=\"text.xsl\"?>");
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