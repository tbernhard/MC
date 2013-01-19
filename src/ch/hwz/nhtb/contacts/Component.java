package ch.hwz.nhtb.contacts;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Component extends Entry {
	private String location;

	public Component() {
		super.name = "Component name";
		this.location = "Location";
	}

	public Component(String name, String location) {
		super.name = name;
		this.location = location;
	}

	public String get(){
		return getLocation();
	}
	
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
		for (int i = 0; i < this.getAddresses().size(); i++) {
			this.getAddresses().get(i).print();
		}
	}
}
