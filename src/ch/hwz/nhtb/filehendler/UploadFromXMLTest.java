package ch.hwz.nhtb.filehendler;

import java.io.File;

import javax.xml.bind.JAXBException;

import ch.hwz.nhtb.contacts.Contacts;

public class UploadFromXMLTest {
	
	public static void main(String[] args) throws JAXBException {
		FileHandler fh = new FileHandler();
		File contactsFile = fh.getFile();
		Contacts c = new Contacts();
		c = fh.readContacts(contactsFile);
		
		c.print();
	}
}
