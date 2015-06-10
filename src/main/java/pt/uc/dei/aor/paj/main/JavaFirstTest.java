package pt.uc.dei.aor.paj.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import pt.uc.dei.aor.paj.data4.Class1;
import pt.uc.dei.aor.paj.data4.Student;
import pt.uc.dei.aor.paj.xml.JavaFirstHandler3;

public class JavaFirstTest {
	public static void main(String[] args) {
		
		Student s = new Student();
		s.setName("Alberto");
		s.setAge(21);
		s.setId(Long.parseLong("201134441110"));
		
		List<Student> listSt = new ArrayList<Student>();
		listSt.add(s);
		
		s = new Student();
		s.setName("Patricia");
		s.setAge(22);
		s.setId(Long.parseLong("201134441116"));
		listSt.add(s);

		s = new Student();
		s.setName("Luis");
		s.setAge(21);
		s.setId(Long.parseLong("201134441210"));
		listSt.add(s);

		Class1 cla = new Class1(listSt);

		try {
			//Marshalling: Writing Java object to XML file
			JavaFirstHandler3.marshal(cla, new File("C:\\Users\\Renata\\Desktop\\projeto5\\Proj5-News\\classStudent5.xml"));
			JavaFirstHandler3.marshalToConsole(cla);

			//Unmarshalling: Converting XML content to Java objects
//			Report rep2 = JAXBXMLHandler.unmarshal(new File("C:\\Users\\Renata\\Desktop\\report.xml"));
//			Report rep2 = JAXBXMLHandler.unmarshal(new File("C:\\Users\\Renata\\Desktop\\projeto5\\Proj5-News\\test.xml"));
//			System.out.println(rep2);  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}