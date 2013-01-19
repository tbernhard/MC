package ch.hwz.nhtb.contacts;

import java.io.File;
import java.util.LinkedList;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import ch.hwz.nhtb.filehendler.FileHandler;

@XmlRootElement(name = "Contacts")
@XmlSeeAlso({ Entry.class, Person.class, Component.class })
public class Contacts {
	private LinkedList<Entry> entries = new LinkedList<Entry>();

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

	public String[] getCRest() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			s[i] = this.entries.get(i).get();
		}

		return s;
	}

//	public String[][] getContacs() {
//			String[][] s = new String[this.entries.size()][this.entries.get(1).getAddresses().get(i)];
//	
//			for (int i = 0; i < this.entries.size(); i++) {
//				s[i] = this.entries.get(i).getName();
//			}
//			for (int i = 0; i < this.entries.size(); i++) {
//				s[i] = this.entries.get(i).get();
//			}
//	
////		 s;
//		return new String[][] {{" ", " "},{" ", " "}};
//	}

	public void print() {
		for (int i = 0; i < this.entries.size(); i++) {
			this.entries.get(i).print();
		}
	}

	public Contacts() {

	}

	public static void main(String[] args) {
		// ------------------------------------------------------------------------------------------------------------------------
		// Load data from xml
		FileHandler fh = new FileHandler();
		// Pfad Thomas
		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
		// Pfad Niko
		// String XMLLocation =
		// "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
		File contactsFile = new File(XMLLocation);

		Contacts c = new Contacts();
		try {
			c = fh.readContacts(contactsFile);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ------------------------------------------------------------------------------------------------------------------------
		c.print();
		// c.getCNames();

		System.out.println();
		System.out.println();
		System.out.println("TEST");

		System.out.println(c.getCRest()[0]);
		System.out.println(c.getCNames()[0]);

		System.out.println(c.getCRest()[2]);
		System.out.println(c.getCNames()[2]);

	}

}
