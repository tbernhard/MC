package ch.hwz.nhtb.contacts;

import javax.xml.bind.annotation.XmlType;

//JAXB Deklaration
@XmlType
public class Person extends Entry {
	private String salutation;
	private String prename;

	public Person() {
	}	
	
	public Person(String name, String prename) {
		super.name = name;
		this.prename = prename;
	}	
	
	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	
}
