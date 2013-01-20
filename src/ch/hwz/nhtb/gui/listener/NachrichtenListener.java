package ch.hwz.nhtb.gui.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.xml.bind.JAXBException;

import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.filehendler.FileHandler;


class NachrichtenListener implements ActionListener {

	private JPanel mainPanel;
	private final JButton addContact;
	
	NachrichtenListener(JPanel mainPanel)  {
		this.mainPanel = mainPanel; 
		this.addContact = new JButton("Add");
		addContact.addActionListener(new AddContactListener_old());//mainPanel
	}
	@Override
	public void actionPerformed(ActionEvent e) {
//------------------------------------------------------------------------------------------------------------------------
//		Load data from xml
		FileHandler fh = new FileHandler();
//		Pfad Thomas
		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
//		Pfad Niko
//		String XMLLocation = "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
		File contactsFile = new File(XMLLocation);
		
		Contacts c = new Contacts();
		try {
			c = fh.readContacts(contactsFile);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//------------------------------------------------------------------------------------------------------------------------		
		
		
		// TODO Auto-generated method stub
		System.out.println("Kontakte Verwalten");
		JPanel form = new JPanel(new BorderLayout()); 
		form.add(addContact, BorderLayout.SOUTH);
//		form.add(new JButton("Add"), BorderLayout.SOUTH);
//		form.add(new JList(
//				new String[]{"Thomas", "Hamlet", "Niko"}), BorderLayout.CENTER); 
		
		form.add(new JList(c.getCNames()), BorderLayout.CENTER); 
		
		mainPanel.removeAll(); 
		mainPanel.setLayout(new BorderLayout()); 
		mainPanel.add(form, BorderLayout.CENTER);
		mainPanel.doLayout(); 
		form.doLayout(); 
		
		
	}
	
	
}