package ch.hwz.nhtb.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import ch.hwz.nhtb.Message;
import ch.hwz.nhtb.Print;
import ch.hwz.nhtb.SMS;
import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Component;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.contacts.Entry;
import ch.hwz.nhtb.contacts.Person;
import ch.hwz.nhtb.filehendler.FileHandler;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelPrint extends JPanel{
	private JButton btnSend;
	private JComboBox jcbEntry;
	private JComboBox jcbAddress = new JComboBox();
	private JTextPane textPane;
	private JLabel lblText;
	private JLabel lblCKind;

	private JTextField textField;
	private FileHandler serializer;
	private File contactsFile;
	private final Contacts c;
	private JScrollPane jsp;

	/**
	 * Create the panel.
	 */
	public PanelPrint() {

		serializer = new FileHandler();
		c = serializer.getContactsFromXML();
		contactsFile = serializer.getFile();

		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(2dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("90dlu"),
				FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC, },
				new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("14dlu"), RowSpec.decode("14dlu"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("60dlu"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(10dlu;default)"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		lblCKind = new JLabel("Empf\u00E4nger");
		add(lblCKind, "3, 2, left, default");

		jcbEntry = new JComboBox(c.getContact(AddressType.IP));
		add(jcbEntry, "5, 2, fill, default");
		jcbEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(jcbAddress);
				jcbAddress = new JComboBox(c.getAddressOnIndex(jcbEntry
						.getSelectedItem().toString(), AddressType.IP));
				add(jcbAddress, "5, 3, fill, default");
				doLayout();
				jcbAddress.doLayout();

//				System.out.println();
//				for (int i = 0; i < c.getAddressOnIndex(jcbEntry
//						.getSelectedIndex()).length; i++) {
//					System.out.println(c.getAddressOnIndex(jcbEntry
//							.getSelectedIndex())[i]);
//				}
//				System.out.println();
			}
		});

		jcbAddress = new JComboBox(c.getAddressOnIndex(jcbEntry
				.getSelectedItem().toString(), AddressType.IP));
		add(jcbAddress, "5, 3, fill, default");

		lblText = new JLabel("Text");
		add(lblText, "3, 5");

		textPane = new JTextPane();
		jsp = new JScrollPane(textPane);
		add(jsp, "5, 5, fill, fill");

		btnSend = new JButton("Send");
		add(btnSend, "5, 7, right, default");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jcbEntry.getSelectedItem().toString();
				jcbAddress.getSelectedItem().toString();
				Entry e = c.getEntries().get(
						c.search(jcbEntry.getSelectedItem().toString()));
				if (e.isPerson()) {
					e = new Person();
					e = e.createEntry(jcbEntry.getSelectedItem().toString(), jcbAddress
						.getSelectedItem().toString());
				}else{
					e = new Component();
					e = e.createEntry(jcbEntry.getSelectedItem().toString(), jcbAddress
						.getSelectedItem().toString());
				}
				
				Print p = new Print();
				p.setMessage(textPane.getText());

				p.setRecipient(e);
				if (p.validate()) {
					Message m = new Print();
					m.send(p);
				}
			}
		});

	}
}
