package ch.hwz.nhtb.filehendler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	

	public FileHandler() {
		try {
			jc = JAXBContext.newInstance(Contacts.class);
			m = jc.createMarshaller();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!contactsFile.exists()) {
			try {
				try {
					m.marshal(new Contacts(),contactsFile);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contactsFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	public File getFile() {
		return this.contactsFile;
	}

	public void writeContacts(Contacts c, File file) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Contacts.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(c, file);
	}

	public Contacts readContacts(File file) throws JAXBException {
		return JAXB.unmarshal(file, Contacts.class);
	}

	public Contacts getContactsFromXML() {
		try {
			return this.readContacts(this.contactsFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	}

}