package ch.hwz.nhtb.contacts;

import javax.xml.bind.annotation.XmlType;

//JAXB Deklaration
@XmlType
public class Component extends Entry {
	private String location;

	public Component() {
	}

	public Component(String name, String location) {
		super.name = name;
		this.location = location;
	}

	public String get() {
		return getLocation();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
