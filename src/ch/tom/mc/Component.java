package ch.tom.mc;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Component extends Entry {
	private String location;

	public Component() {
		super();
		this.location = "Location";
		super.name = "Component name";
	}

	// @Override
	// public String toXML() throws Exception{
	// String XMLLocation = "D:/hwz/java/hwz_workspace/MultiChannelTest/xml/"+
	// this.getClass() +".xml";
	// ObjectSerializationToXML serializer = new ObjectSerializationToXML();
	// serializer.serializeObjectToXML(XMLLocation, this);
	// return "Das Object "+ this.getName() +" wurde in "+ XMLLocation;
	// }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public void print() {
		System.out
				.println("--------------------Component----------------------");
		System.out.println(this.location);
		System.out.println(super.name);
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
