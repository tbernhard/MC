package ch.hwz.nhtb.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.bind.JAXBException;

import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.filehendler.FileHandler;
import ch.hwz.nhtb.gui.frames.AddContacts;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

//TODO Refresh after add contacts

public class PanelContact extends JPanel {
	private JList jlContacts;
	private JScrollPane jsp;
	private JButton btnAdd;

	/**
	 * Create the panel.
	 */
	public PanelContact() {

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

		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		jlContacts = new JList(c.getContacts());
		jlContacts.ensureIndexIsVisible(14);
		jsp = new JScrollPane(jlContacts);
		add(jsp, "5, 2, 2, 3, fill, fill");

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddContacts();
//				revalidate();
//				validate();
//				doLayout();
			}
		});
		add(btnAdd, "6, 6");

//		addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent arg0) {
//				new AddContacts();
//			}
//		});

	}

}
