package ch.hwz.nhtb.filehendler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.hwz.nhtb.contacts.Address;
import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Component;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.contacts.Person;

public class FileHandler {

	/*
	 * @TODO serializeObjectToXML changed to ObjectToXML deserializeXMLToObject
	 * changed to XMLToObject implement into Contact.java implement Properties
	 * read in
	 */
	// public void ObjectToXML(String xmlFileLocation, Object objectToSerialize)
	// throws Exception {
	// FileOutputStream os = new FileOutputStream(xmlFileLocation);
	// XMLEncoder encoder = new XMLEncoder(os);
	// encoder.writeObject(objectToSerialize);
	// encoder.close();
	// }
	//
	// public Object XMLToObject(String xmlFileLocation) throws Exception {
	// FileInputStream os = new FileInputStream(xmlFileLocation);
	// XMLDecoder decoder = new XMLDecoder(os);
	// Object deSerializedObject = decoder.readObject();
	// decoder.close();
	// return deSerializedObject;
	// }

	public void writeContacts(Contacts c, File file) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Contacts.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(c, file);
	}

	public Contacts readContacts(File file) throws JAXBException {
		return JAXB.unmarshal(file, Contacts.class);
	}

	// @TODO Properties File .properties oder .xml nur importieren
	public void LoadProperties() {
		Properties props = new Properties();
		InputStream is = null;

		// First try loading from the current directory
		try {
			File f = new File("mc.properties");
			is = new FileInputStream(f);
		} catch (Exception e) {
			is = null;
		}

		try {
			if (is == null) {
				// Try loading from classpath
				is = getClass().getResourceAsStream("mc.properties");
			}

			// Try loading properties from the file (if found)
			props.load(is);
		} catch (Exception e) {
		}

		// serverAddr = props.getProperty("ServerAddress", "192.168.0.1");
		// serverPort = new Integer(props.getProperty("ServerPort", "8080"));
		// threadCnt = new Integer(props.getProperty("ThreadCount", "5"));

	}

	public static void main(String[] args) throws Exception {
		/* Location of XML File */
//		Pfad Thomas
		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
//		Pfad Niko
//		String XMLLocation = "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
		File contactsFile = new File(XMLLocation);

		FileHandler serializer = new FileHandler();

		/* Creating and filling a bean object */
		// Contacts erstellen
		Contacts c = new Contacts();

		// Person hinzufügen
		Person e = new Person();
		e.setName("Bernhard");
		e.setPrename("Thomas");
		e.setSalutation("Herr");

		c.add(e);

		Address a = new Address();
		a.setType(AddressType.EMail); 
		a.setAddressText("tbernhard87@gmail.com");

		Address m = new Address();
		m.setType(AddressType.Mobile); 
		m.setAddressText("012312312"); 

		e.add(a);
		e.add(m);

		// Person hinzufügen
		Person e2 = new Person();
		e2.setName("Herrmann");
		e2.setPrename("Niko");
		e2.setSalutation("Herr");

		c.add(e2);

		Address a2 = new Address();
		a2.setType(AddressType.EMail); 
		a2.setAddressText("nhe@cnd-ag.ch");

		Address m2 = new Address();
		m2.setType(AddressType.Mobile);
		m2.setAddressText("0792231212");

		Address t1 = new Address();
		t1.setType(AddressType.Mobile); 
		t1.setAddressText("0663453636"); 
		Address t2 = new Address();
		t2.setType(AddressType.FAX); 
		t2.setAddressText("0663444636"); 
		Address t3 = new Address();
		t3.setType(AddressType.Tel); 
		t3.setAddressText("0623453636"); 


		e2.add(a2);
		e2.add(m2);
		e2.add(t1);
		e2.add(t2);
		e2.add(t3);

		// Component hinzufügen
		Component e1 = new Component();
		e1.setName("Drucker");
		e1.setLocation("Zürich Hauptsitz");

		Address n = new Address();
		n.setAddressText("127.0.0.1");
		n.setType(AddressType.IP);
		e1.add(n);
		c.add(e1);

	
		/* Serialzing Object to XML */
		serializer.writeContacts(c, contactsFile);

//		Contacts c2 = new Contacts();
//		// Person hinzufügen
//		Person e3 = new Person();
//		e3.setName("Sebastiano Santorro");
//		e3.setSalutation("Herr");
//
//		c2.add(e3);
//
//		EMailAddress a3 = new EMailAddress();
//		a3.setEmail("sesa@gmail.com");
//
//		MailAddress m3 = new MailAddress();
//		m3.setCity("Luzern");
//		m3.setPlz(6009);
//		m3.setStreet("Gangynweg 6");
//
//		e3.add(a3);
//		e3.add(m3);
//
//		serializer.writeContacts(c2, contactsFile);

		// System.out.println("Starting Serialization...");
		// serializer.ObjectToXML(XMLLocation, c);
		// serializer.ObjectToXML(XMLLocation, m);
		// System.out.println("Serialized Object: " + e.getClass().getName());
		// System.out.println("Destination XML: " + XMLLocation);

		// /* Reading the object from serialized XML */
		// System.out.println("\n\nStarting De-Serialization...");
		// System.out.println("Source XML: " + XMLLocation);
		// MyBeanToSerialize deserializedObj = (MyBeanToSerialize)
		// serializer.deserializeXMLToObject(XMLLocation);
		// System.out.println("De-serialized Object: " +
		// deserializedObj.getClass().getName());
		// System.out.println("\nChecking For Values In De-Serialized Object");
		// System.out.println("...<span id="+"IL_AD9"+" class="+"IL_AD"+">First Name</span>: "
		// + deserializedObj.getFirstName());
		// System.out.println("...Last Name: " + deserializedObj.getLastName());
		// System.out.println("...Age: " + deserializedObj.getAge());
	}
}