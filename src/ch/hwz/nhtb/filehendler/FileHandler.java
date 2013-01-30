package ch.hwz.nhtb.filehendler;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.hwz.nhtb.contacts.Contacts;

public class FileHandler {

	private final String XMLLocation = "files/Contacts.xml";
	private File contactsFile = new File(this.XMLLocation);
	private JAXBContext jc;
	private Marshaller m;

	/**
	 * Erstellt ein leeres XML File falls noch keines vorhanden ist
	 */
	public FileHandler() {
		try {
			jc = JAXBContext.newInstance(Contacts.class);
			m = jc.createMarshaller();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		if (!contactsFile.exists()) {
			try {
				try {
					m.marshal(new Contacts(), contactsFile);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				contactsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gibt ein File zurück welches den XML Pfad beinhaltet
	 */
	public File getFile() {
		return this.contactsFile;
	}

	/**
	 * Schreibt die mitgelieferten Kontakt in das Mitgelieferte XML File
	 */
	public void writeContacts(Contacts c, File file) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Contacts.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(c, file);
	}

	/**
	 * Gibt die Kontakte zurück welche im mitgelieferten XML File gespeichert
	 * wurden
	 */
	public Contacts readContacts(File file) throws JAXBException {
		return JAXB.unmarshal(file, Contacts.class);
	}

	/**
	 * Gibt die Kontakte zurück welche im XML File "contactsFile" gespeichert
	 * wurden
	 */
	public Contacts getContactsFromXML() {
		try {
			return this.readContacts(this.contactsFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}