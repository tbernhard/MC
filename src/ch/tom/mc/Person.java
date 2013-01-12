package ch.tom.mc;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Person extends Entry {
	private String salutation;

	public Person() {
		this.salutation = "Salutation";
		super.name = "Person name";
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	// @Override
	// public String toXML() throws Exception{
	// String XMLLocation = "D:/hwz/java/hwz_workspace/MultiChannelTest/xml/"+
	// this.getClass() +".xml";
	// ObjectSerializationToXML serializer = new ObjectSerializationToXML();
	// serializer.serializeObjectToXML(XMLLocation, this);
	// return "Das Object "+ this.getName() +" wurde in "+ XMLLocation;
	// }

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
		for (int i = 0; i < this.addresses.size(); i++) {
			this.addresses.get(i).print();
		}
	}
	
	

}
