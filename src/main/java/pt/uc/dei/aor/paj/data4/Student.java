package pt.uc.dei.aor.paj.data4;

//import javax.persistence.criteria.CriteriaBuilder.In;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
//import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "age"
})
@XmlRootElement(name = "metric_data")
public class Student {

    @XmlAttribute(name = "id", required = true)
    protected Long id;
    @XmlElement(required = true)
//    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
//    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlElement(required = true)
    protected Integer age;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

    @Override
    public String toString() {
        return "Student [ name=" + name + ", age=" + age + "]";
    }

}