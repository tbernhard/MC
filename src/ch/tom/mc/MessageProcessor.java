package ch.tom.mc;

import java.io.File;

import javax.xml.bind.JAXBException;

public class MessageProcessor {

	public void send(Message message) {
		message.print();
	}

	public static void main(String[] args) throws JAXBException {
//		Load data from xml
		FileHandler fh = new FileHandler();
		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
		File contactsFile = new File(XMLLocation);
		
		Contacts c = new Contacts();
		c = fh.readContacts(contactsFile);

		
//		TODO How to choose from a list of all contacts the one you need
//		create a new contacts object and add the one you need
		Contacts cs = new Contacts();		// sender contact
		cs.add(c.getEntries().getFirst());
		Contacts cr = new Contacts();		// recipient contact
		cr.add(c.getEntries().getLast());
		
		
//	MMS erstellen		
		MMS mms = new MMS();

//		Attachment erstellen	-- möglich so files zu übermitteln?	
		String newFile = "D:/hwz/java/hwz_workspace/MC/dataFiles/HalloMMS.txt";
		File attachment[] = {new File(newFile)};
		
		mms.setSender(cs);
		mms.setRecipient(cr);
		mms.setMessage("Dies ist ein MMS");
		mms.setSubject("MMS");
		mms.setAttachment(attachment);
		
		mms.print();
		
//		MessageProcessor mc = new MessageProcessor();
//		mc.send(mms);
		
		
		
		
		
		
	}
}
