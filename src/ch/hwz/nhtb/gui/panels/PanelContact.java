package ch.hwz.nhtb.gui.panels;

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
import ch.hwz.nhtb.gui.App;
import ch.hwz.nhtb.gui.frames.AddContacts;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelContact extends JPanel {
	private static final long serialVersionUID = 1L;
	private JList jlContacts;
	private JScrollPane jsp;
	private JButton btnAdd;
	private JButton btnDel;
	private Contacts cPC;
	private FileHandler serializer;
	private File contactsFile;
	private JFrame frame;

	/**
	 * Pannel erstellen
	 */
	public PanelContact(final JFrame frame) {

		this.setFrame(frame);

		serializer = new FileHandler();
		Contacts c = serializer.getContactsFromXML();
		contactsFile = serializer.getFile();

		cPC = c;

		// Panel Layout definieren ->forms-1.3.0.jar WindowBuilder (jgoodies)
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"), FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),
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

		btnAdd = new JButton("Erstellen");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(true);
				new AddContacts(frame);
			}
		});

		btnDel = new JButton("Löschen");
		// Aktion an den Speichern Button anhängen
		btnDel.addMouseListener(new MouseAdapter() {
			/**
			 * Alle ausgewählten Kontake werden per Mausklick gelöscht -> XML
			 * gelöscht
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!jlContacts.isSelectionEmpty()) {
					int n = JOptionPane.showConfirmDialog(new JFrame(),
							"Wollen Sie die Auswahl wirklich löschen?",
							"Achtung", JOptionPane.YES_NO_OPTION);
					if (n == 0) {
						for (int i = 0; i < jlContacts.getSelectedValues().length; i++) {
							int index = cPC.search(jlContacts
									.getSelectedValues()[i].toString());
							if (index >= 0) {
								cPC.getEntries().remove(index);
							}
						}// Aus der XML Löschen
						try {
							serializer.writeContacts(cPC, contactsFile);
						} catch (JAXBException e) {
							e.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Es wurde kein Kontakt ausgewählt.", "Achtung",
							JOptionPane.WARNING_MESSAGE);
				}
				//Aktualisieren der Anzeige
				frame.setVisible(false);
				App app = new App();
				app.loadContactPanel();

			}
		});

		add(btnDel, "7, 6");
		add(btnAdd, "9, 6");

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
