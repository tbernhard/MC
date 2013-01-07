package ch.tom.mc;

import java.io.File;

import javax.xml.bind.JAXBException;

public class UploadFromXMLTest {
	
	public static void main(String[] args) throws JAXBException {
		FileHandler fh = new FileHandler();
		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
		File contactsFile = new File(XMLLocation);
		
		Contacts c = new Contacts();
		c = fh.readContacts(contactsFile);
		
		c.print();
	}
}
