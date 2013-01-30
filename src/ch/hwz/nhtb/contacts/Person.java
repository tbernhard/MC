package ch.hwz.nhtb.contacts;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Person extends Entry {
	private String salutation;
	private String prename;

	public Person() {
		super.name = "Person name";
		this.prename = "Person prename";
		this.salutation = "Salutation";
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
	
	public String get(){
		return getPrename() + getSalutation();
	}

	public void print() {
		System.out
				.println("--------------------Person------------------------");
		System.out.println(this.salutation);
		System.out.println(super.name);
		this.printAddress();
		// super.print();
		System.out
				.println("----------------------------------------------------");
	}

	public void printAddress() {
		for (int i = 0; i < this.getAddresses().size(); i++) {
			this.getAddresses().get(i).print();
		}
	}

}
