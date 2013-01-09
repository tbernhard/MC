package ch.tom.mc;

import java.io.File;

import javax.xml.bind.JAXBException;

public class UploadFromXMLTest {
	
	public static void main(String[] args) throws JAXBException {
		FileHandler fh = new FileHandler();
//		Pfad Thomas
//		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
//		Pfad Niko
		String XMLLocation = "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
		File contactsFile = new File(XMLLocation);
		
		Contacts c = new Contacts();
		c = fh.readContacts(contactsFile);
		
		c.print();
	}
}
