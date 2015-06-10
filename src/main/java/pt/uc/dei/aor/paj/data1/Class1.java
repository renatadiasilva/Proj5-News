package pt.uc.dei.aor.paj.data1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "students"
})
@XmlRootElement(name = "class")
public class Class1 {
	
	//private?
	@XmlElement(name = "student", required = true)
    protected List<Student> students;

	public Class1() {
		
	}

	public Class1(List<Student> students) {
		super();
		this.students = students;
	}

	public List<Student> getStudents() {
		if (students == null) students = new ArrayList<Student>();
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
    @Override
    public String toString() {
    	String out ="Class: \n";
    	int i = 1;
    	for (Student s: students) {
    		out += i+": "+s+"\n";
    		i++;
    	}
    	
    	return out;
    }
	
	
}
