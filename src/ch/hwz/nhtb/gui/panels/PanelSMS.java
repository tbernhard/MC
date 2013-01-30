package ch.hwz.nhtb.gui.panels;

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
import javax.swing.JTextField;
import javax.swing.JTextPane;

import ch.hwz.nhtb.Message;
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

public class PanelSMS extends JPanel {
	private JButton btnSend;
	private JComboBox jcbEntry;
	private JComboBox jcbAddress = new JComboBox();
	private JTextPane textPane;
	private JScrollPane jsp;
	private JLabel lblText;
	private JLabel lblCKind;
	private JLabel lblPanel;

	private JTextField textField;
	private FileHandler serializer;
	private File contactsFile;
	private final Contacts c;

	/**
	 * Create the panel.
	 */
	public PanelSMS() {
		serializer = new FileHandler();
		c = serializer.getContactsFromXML();
		contactsFile = serializer.getFile();

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
						RowSpec.decode("60dlu"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(10dlu;default)"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		lblPanel = new JLabel("SMS");
		add(lblPanel, "3, 2, 4, 1");

		lblCKind = new JLabel("Empfänger");
		add(lblCKind, "3, 4, left, default");

		jcbEntry = new JComboBox(c.getContact(AddressType.Mobile));
		add(jcbEntry, "5, 4, fill, default");
		jcbEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(jcbAddress);
				jcbAddress = new JComboBox(c.getAddressOnIndex(jcbEntry
						.getSelectedItem().toString(), AddressType.Mobile));
				add(jcbAddress, "5, 3, fill, default");
				doLayout();
				jcbAddress.doLayout();

				// System.out.println();
				// for (int i = 0; i < c.getAddressOnIndex(jcbEntry
				// .getSelectedIndex()).length; i++) {
				// System.out.println(c.getAddressOnIndex(jcbEntry
				// .getSelectedIndex())[i]);
				// }
				// System.out.println();
			}
		});

		// jcbEntry.addActionListener(this);

		// jcbEntry.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mousePressed(MouseEvent arg0) {
		// System.out.println("Test"+arg0);
		// c.getAdressOnIndex(jcbEntry.getSelectedIndex());
		// jcbAddress = new
		// JComboBox(c.getAdressOnIndex(jcbEntry.getSelectedIndex()));
		// jcbAddress.doLayout();
		// System.out.println();
		// for (int i = 0; i <
		// c.getAdressOnIndex(jcbEntry.getSelectedIndex()).length; i++) {
		// System.out.println(c.getAdressOnIndex(jcbEntry.getSelectedIndex())[i]);
		// }
		// System.out.println();
		//
		// }
		// });

		// KeyListener Example ENTER
		// jcbEntry.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent arg0) {
		// if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
		// System.out.println("Test"+arg0);
		// }
		// }
		// });

		// TODO
		// String[] test = new String[2];
		// test[0] = c.getEntries()
		// .get(c.search(jcbEntry.getSelectedItem().toString()))
		// .getAddressTextOf(0).getAddressText();
		// test[1] = c.getEntries()
		// .get(c.search(jcbEntry.getSelectedItem().toString()))
		// .getAddressTextOf(1).getAddressText();

		jcbAddress = new JComboBox(c.getAddressOnIndex(jcbEntry
				.getSelectedItem().toString(), AddressType.Mobile));
		add(jcbAddress, "5, 5, fill, default");

		lblText = new JLabel("Text");
		add(lblText, "3, 7");

		textPane = new JTextPane();
		jsp = new JScrollPane(textPane);
		add(jsp, "5, 7, fill, fill");

		btnSend = new JButton("Senden");
		add(btnSend, "5, 9, right, default");

		btnSend.addActionListener(new ActionListener() {
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

				SMS sms = new SMS();
				sms.setMessage(textPane.getText());
				sms.setRecipient(e);
				if (sms.validate()) {
					Message m = new SMS();
					m.send(sms);
					JOptionPane.showMessageDialog(new JFrame(),
							"SMS wurde erfolgreich versendet", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					textPane.setText("");
				}
			}
		});
	}
}
