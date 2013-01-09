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
//		Pfad Thomas
//		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
//		Pfad Niko
		String XMLLocation = "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
		File contactsFile = new File(XMLLocation);
		
		Contacts c = new Contacts();
		c = fh.readContacts(contactsFile);

		
//		TODO How to choose from a list of all contacts the one you need
//		create a new contacts object and add the one you need
		Contacts cs = new Contacts();		// sender contact
		cs.add(c.getEntries().getFirst());
//		System.out.println(c.getEntries());
		Contacts cr = new Contacts();		// recipient contact
		cr.add(c.getEntries().getLast());
		
		
//		Test MMS erstellen		
		MMS mms = new MMS();

//		Attachment erstellen	-- möglich so files zu übermitteln?	
		
//		Pfad Thomas
//		String newFile = "D:/hwz/java/hwz_workspace/MC/dataFiles/HalloMMS.xml";
//		Pfad Niko
//		Grössenvalidierung NOK  -- MEIN HalloMMS.txt ist ca 3MB gross. (mp3 lied umbenannt)
//		String newFile = "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/HalloMMS.txt";
		
//		Grössenvalidierung OK		
//		Pfad Thomas
//		String newFile = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
//		Pfad Niko
		String newFile = "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
//		ACHTUNG: Attachment ist ein Array. Muss noch angepasst werden
		File attachment[] = {new File(newFile)};
		
		mms.setSender(cs);
		mms.setRecipient(cr);
//		Dieser Text ist 16 Byte gross.
		mms.setMessage("Dies ist ein MMS");
		mms.setSubject("MMS");
		mms.setAttachment(attachment);
		
//		mms.print();
		
		MessageProcessor mb = new MessageProcessor();
//		mms.getAttachment();
//		System.out.println("Grösse der MMS Nachricht ist: " + this.mms.getMessage().length() + " Byte.");
//		System.out.println("Grösse des Anhang ist: " + this.mms.getAttachment().length + "Byte");
		
		mms.validate();
		mb.send(mms);
		
		
		
//  	Test SMS erstellen
		SMS sms = new SMS();

//		Attachment erstellen	-- möglich so files zu übermitteln?	
//		String newFile = "D:/hwz/java/hwz_workspace/MC/dataFiles/HalloMMS.txt";
//		File attachment[] = {new File(newFile)};
		
		sms.setSender(cs);
		sms.setRecipient(cr);
//		Nachricht grösser als 160 Zeichen setzen zum testen
//		sms.setMessage("Dieser Text ist sicher über 160 Zeichen lang, damit getestet werden kann ob die SMS Nachricht nicht länger sein kann. Es sollte ein Fehler ausgeben werden… Ist noch ziemlich viel Text… HA HA HA total=176Zeichen");
//		Nachricht nicht grösser als 160 Zeichen
		sms.setMessage("Dieser Text ist sicher nicht über 160 Zeichen. Ende");
		
//		sms.print();
//		sms.validate();
		
		
		MessageProcessor mc = new MessageProcessor();
		mc.send(sms);

		
		
	}
}
