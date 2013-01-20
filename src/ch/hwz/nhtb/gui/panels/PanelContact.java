package ch.hwz.nhtb.gui.panels;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	private JButton btnDel;
	private Contacts cPC;

	/**
	 * Create the panel.
	 */
	public PanelContact() {

		// ------------------------------------------------------------------------------------------------------------------------
		// Load data from xml
		final FileHandler serializer = new FileHandler();
		// Pfad Thomas
		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
		// Pfad Niko
		// String XMLLocation =
		// "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
		final File contactsFile = new File(XMLLocation);

		Contacts c = new Contacts();
		try {
			c = serializer.readContacts(contactsFile);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ------------------------------------------------------------------------------------------------------------------------

		cPC = c;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(66dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(28dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(28dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(9dlu;default)"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		jlContacts = new JList(c.getContact());
		jlContacts.ensureIndexIsVisible(14);
		jsp = new JScrollPane(jlContacts);
		add(jsp, "3, 2, 7, 3, fill, fill");

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddContacts();
			}
		});

		btnDel = new JButton("Del");
		btnDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!jlContacts.isSelectionEmpty()) {
					int n = JOptionPane.showConfirmDialog(new JFrame(),
							"Wollen Sie die Auswahl wirklich l�schen?",
							"Achtung", JOptionPane.YES_NO_OPTION);
					if (n == 0) {
						System.out.println("JA, l�schen.");
						System.out.println("Folgende "
								+ jlContacts.getSelectedValues().length
								+ " Kontakte werden gel�scht");
						for (int i = 0; i < jlContacts.getSelectedValues().length; i++) {
							System.out.println(jlContacts.getSelectedValues()[i]);
							int index = cPC.search(jlContacts
									.getSelectedValues()[i].toString());
							if(index >= 0){
								cPC.getEntries().remove(index);
							}									
						}

						try {
							serializer.writeContacts(cPC, contactsFile);
						} catch (JAXBException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("Nein, nicht l�schen.");
					}

				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Es wurde kein Kontakt ausgew�hlt.", "Achtung",
							JOptionPane.WARNING_MESSAGE);
				}


				setVisible(false);

				
			}
		});
		add(btnDel, "7, 6");
		add(btnAdd, "9, 6");

	}
}
