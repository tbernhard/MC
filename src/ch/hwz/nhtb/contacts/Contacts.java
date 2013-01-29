package ch.hwz.nhtb.contacts;

import java.io.File;
import java.util.LinkedList;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import ch.hwz.nhtb.filehendler.FileHandler;

//TODO	Methode erstellen welche nur Kontakte zurückgibt welche zu den Sendearten (SMS,MMS,Email,PrintJob) passen.

@XmlRootElement(name = "Contacts")
@XmlSeeAlso({ Entry.class, Person.class, Component.class })
public class Contacts {
	private LinkedList<Entry> entries = new LinkedList<Entry>();
	
	private static File contactsFile;
	private String XMLLocation;
	private static FileHandler serializer;

	public LinkedList<Entry> getEntries() {
		return entries;
	}

	public void setEntries(LinkedList<Entry> entries) {
		this.entries = entries;
	}

	public void add(Entry e) {
		this.entries.add(e);
	}

	public String[] getCNames() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			s[i] = this.entries.get(i).getName();
		}

		return s;
	}

	public String[] getContacts() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			if (this.entries.get(i).getClass().isInstance(new Person())) {
				Person e = (Person) this.entries.get(i);
				s[i] = e.getPrename() + " "
						+ e.getName();
				for (int j = 0; j < e.getAddresses().size(); j++) {
					s[i] += " " + e.getAddresses().get(j).getAddressText();
				}
			} else {
				Component c = (Component) this.entries.get(i);
				s[i] = c.getLocation() + " " + c.getName();
				for (int j = 0; j < c.getAddresses().size(); j++) {
					s[i] += " " + c.getAddresses().get(j).getAddressText();
				}
			}
		}
		return s;
	}
	


	public String[] getContact() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			if (this.entries.get(i).getClass().isInstance(new Person())) {
				Person e = (Person) this.entries.get(i);
				s[i] = e.getPrename() + " "
						+ e.getName();
			} else {
				Component c = (Component) this.entries.get(i);
				s[i] = c.getLocation() + " " + c.getName();
			}
		}
		return s;
	}

	public int search(String search) {
		if(!search.isEmpty()){
		String[] s = new String[this.entries.size()];
		int index = 0;
		for (int i = 0; i < this.entries.size(); i++) {
			if (this.entries.get(i).getClass().isInstance(new Person())) {
				Person e = (Person) this.entries.get(i);
				for (int j = 0; j < e.getAddresses().size(); j++) {
					if (search.matches("(.*)"+e.getPrename()+"(.*)")
							|| search.matches("(.*)"+e.getName()+"(.*)")
							|| search.matches("(.*)"+e.getName()+"(.*)")
							|| search.matches("(.*)"+e.getAddresses().get(j)
									.getAddressText()+"(.*)")) {
						index = i; 
					}
				}
			} else {
				Component c = (Component) this.entries.get(i);
				for (int j = 0; j < c.getAddresses().size(); j++) {
					if (search.matches("(.*)"+c.getName()+"(.*)")
							|| search.matches("(.*)"+c.getLocation()+"(.*)")
							|| search.matches("(.*)"+c.getAddresses().get(j)
									.getAddressText()+"(.*)")) {
						index = i; 
					}
				}
			}
		}
		return index;
		} return -1;
	}

	public String[] getCRest() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			s[i] = this.entries.get(i).get();
		}

		return s;
	}

	public void print() {
		for (int i = 0; i < this.entries.size(); i++) {
			this.entries.get(i).print();
		}
	}

	public Contacts() {

	}

	public static void main(String[] args) {
		serializer = new FileHandler();
		Contacts c = serializer.getContactsFromXML();
		contactsFile = serializer.getFile();
		
		// c.print();
		// c.getCNames();

		// System.out.println();
		// System.out.println();
		// System.out.println("TEST");
		//
		// System.out.println(c.getCRest()[0]);
		// System.out.println(c.getCNames()[0]);
		//
		// System.out.println(c.getCRest()[2]);
		// System.out.println(c.getCNames()[2]);

		// c.getContacts();
//		int x = c.search("Bernhard");
//		c.entries.get(x).print();
//		c.entries.remove(x);
		
		
	}

}
