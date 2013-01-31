package ch.hwz.nhtb.gui.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import ch.hwz.nhtb.MMS;
import ch.hwz.nhtb.Message;
import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Component;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.contacts.Entry;
import ch.hwz.nhtb.contacts.Person;
import ch.hwz.nhtb.filehandler.FileHandler;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelMMS extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnSend;
	private JComboBox jcbEntry;
	private JComboBox jcbAddress = new JComboBox();
	private JTextPane subPane;
	private JTextPane textPane;
	private JScrollPane jsp;
	private JScrollPane jspSP;
	private JLabel lblText;
	private JLabel lblCKind;
	private JLabel lblSub;
	private JLabel lblPanel;

	private FileHandler serializer;
	private File contactsFile;
	private final Contacts c;

	/**
	 * Pannel erstellen
	 */
	public PanelMMS() {
		// Kontakte aus XML lesen
		serializer = new FileHandler();
		c = serializer.getContactsFromXML();
		setContactsFile(serializer.getFile());

		// Panel Layout definieren ->forms-1.3.0.jar WindowBuilder (jgoodies)
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(2dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("90dlu"),
				FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC, },
				new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("14dlu"), RowSpec.decode("14dlu"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("fill:12dlu"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("60dlu"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(10dlu;default)"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		lblPanel = new JLabel("MMS");
		add(lblPanel, "3, 2, 4, 1");

		lblCKind = new JLabel("Empfänger");
		add(lblCKind, "3, 4, left, default");

		jcbEntry = new JComboBox(c.getContact(AddressType.getMMSAddTyp()));
		add(jcbEntry, "5, 4, fill, default");
		// Aktion an das Dropdown Feld anhängen
		jcbEntry.addActionListener(new ActionListener() {
			/**
			 * Alle Kontake welche eine Email Adresse oder eine Mobile Adresse haben werden im Dropdown
			 * Feld ausgegeben
			 */
			public void actionPerformed(ActionEvent arg0) {
				remove(jcbAddress);
				jcbAddress = new JComboBox(c.getAddressOnIndex(jcbEntry
						.getSelectedItem().toString(), AddressType
						.getMMSAddTyp()));
				add(jcbAddress, "5, 5, fill, default");
				doLayout();
				jcbAddress.doLayout();
			}
		});

		/**
		 * Alle Zugehörigen Adressen werden zu den Kontakten ausgegeben
		 */
		jcbAddress = new JComboBox(c.getAddressOnIndex(jcbEntry
				.getSelectedItem().toString(), AddressType.getMMSAddTyp()));
		add(jcbAddress, "5, 5, fill, default");

		lblSub = new JLabel("Betreff");
		add(lblSub, "3, 7");

		subPane = new JTextPane();
		jspSP = new JScrollPane(subPane);
		jspSP.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		add(jspSP, "5, 7, fill, fill");

		lblText = new JLabel("Text");
		add(lblText, "3, 9");

		textPane = new JTextPane();
		jsp = new JScrollPane(textPane);
		add(jsp, "5, 9, fill, fill");

		btnSend = new JButton("Senden");
		add(btnSend, "5, 11, right, default");
		// Aktion an den Senden Button anhängen
		btnSend.addActionListener(new ActionListener() {
			/**
			 * MMS versenden
			 */
			public void actionPerformed(ActionEvent arg0) {
				jcbEntry.getSelectedItem().toString();
				jcbAddress.getSelectedItem().toString();
				Entry e = c.getEntries().get(
						c.search(jcbEntry.getSelectedItem().toString()));
				if (e.isPerson()) {
					e = new Person();
					e = e.createEntry(jcbEntry.getSelectedItem().toString(),
							jcbAddress.getSelectedItem().toString());
				} else {
					e = new Component();
					e = e.createEntry(jcbEntry.getSelectedItem().toString(),
							jcbAddress.getSelectedItem().toString());
				}
				// MMS erstellen
				MMS mms = new MMS();
				mms.setMessage(textPane.getText());
				mms.setSubject(subPane.getText());
				mms.setRecipient(e);
				if (mms.validate()) {
					Message m = new MMS();
					m.send(mms);
					JOptionPane.showMessageDialog(new JFrame(),
							"MMS wurde erfolgreich versendet", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					subPane.setText("");
					textPane.setText("");
				}

			}
		});

	}

	public File getContactsFile() {
		return contactsFile;
	}

	public void setContactsFile(File contactsFile) {
		this.contactsFile = contactsFile;
	}
}
