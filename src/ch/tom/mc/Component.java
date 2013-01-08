package ch.tom.mc;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Component extends Entry {
	private String name;

	public Component() {
		super();
		this.name = "Name";
	}

	// @Override
	// public String toXML() throws Exception{
	// String XMLLocation = "D:/hwz/java/hwz_workspace/MultiChannelTest/xml/"+
	// this.getClass() +".xml";
	// ObjectSerializationToXML serializer = new ObjectSerializationToXML();
	// serializer.serializeObjectToXML(XMLLocation, this);
	// return "Das Object "+ this.getName() +" wurde in "+ XMLLocation;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void print() {
		System.out
				.println("--------------------Component----------------------");
		System.out.println(this.name);
		printAddress();		
		System.out
				.println("----------------------------------------------------");
	}
	
	public void printAddress() {
		for (int i = 0; i < this.addresses.size(); i++) {
			this.addresses.get(i).print();
		}
	}
}
