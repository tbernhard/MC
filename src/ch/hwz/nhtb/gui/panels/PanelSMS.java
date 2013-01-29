package ch.hwz.nhtb.gui.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.filehendler.FileHandler;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelSMS extends JPanel {
	private JButton btnSend;
	private JComboBox jcbEntry;
	private JComboBox jcbAddress;
	private JTextPane textPane;
	private JLabel lblText;
	private JLabel lblCKind;

	private JTextField textField;
	private FileHandler serializer;
	private File contactsFile;

	/**
	 * Create the panel.
	 */
	public PanelSMS() {

		serializer = new FileHandler();
		Contacts c = serializer.getContactsFromXML();
		contactsFile = serializer.getFile();

		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(2dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("90dlu:grow"), FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("70dlu"), FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		lblCKind = new JLabel("Empfänger");
		add(lblCKind, "3, 2, left, default");
		jcbEntry = new JComboBox(c.getContact());
		add(jcbEntry, "5, 2, fill, default");
		
		
		//TODO	
		String[] test = new String[2];
		test[0] = c.getEntries().get(c.search(jcbEntry.getSelectedItem().toString())).getAddressTextOf(0).getAddressText();
		test[1] = c.getEntries().get(c.search(jcbEntry.getSelectedItem().toString())).getAddressTextOf(1).getAddressText();
		
		jcbAddress = new JComboBox(test);
		add(jcbAddress, "5, 3, fill, default");

		// JLabel lblNewLabel = new JLabel("Number");
		// add(lblNewLabel, "3, 2, right, default");
		//
		// textField = new JTextField();
		// add(textField, "5, 2, fill, default");
		// textField.setColumns(10);

		lblText = new JLabel("Text");
		add(lblText, "3, 5");

		textPane = new JTextPane();
		add(textPane, "5, 5, fill, fill");
		// jcbEntry.addActionListener(this);

		// JButton btnSearch = new JButton("Search");
		// add(btnSearch, "6, 2");
		// btnSearch.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mousePressed(MouseEvent arg0) {
		// Address ad = new Address();
		//
		// if ((jtfPPn.getText() == null)
		// || "".equals(jtfPPn.getText().trim())
		// || (jtfPName.getText() == null)
		// || "".equals(jtfPName.getText().trim())) {
		// JOptionPane.showMessageDialog(new JFrame(),
		// "Bitte Name und Vorname angeben.", "Achtung",
		// JOptionPane.WARNING_MESSAGE);
		// lblName.setBackground(Color.red);
		// doLayout();
		// } else {
		// p.setPrename(jtfPPn.getText());
		// p.setName(jtfPName.getText());
		// p.setSalutation(jcbSal.getSelectedItem().toString());
		// jtfPPn.disable();
		// jtfPName.disable();
		// jcbSal.disable();
		// ad.setType((AddressType) jcbAddress.getSelectedItem());
		// ad.setAddressText(jtfAdd.getText());
		// p.add(ad);
		// JOptionPane.showMessageDialog(
		// new JFrame(),
		// ad.getType().toString()
		// + " wurde erfolgreich zum Kontakt "
		// + p.getSalutation() + " " + p.getName()
		// + " hinzugefügt.");
		// jtfAdd.setText("");
		// }
		//
		// }
		// });

		btnSend = new JButton("Send");
		add(btnSend, "5, 7, right, default");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("SMS mit dem Inhalt: ");
				System.out.println(textPane.getText());
				System.out.println(" an : ");
				System.out.println(jcbEntry.getSelectedItem().toString());
				System.out.println(" verschickt...");

				// Address ad = new Address();
				//
				// if ((jtfPPn.getText() == null)
				// || "".equals(jtfPPn.getText().trim())
				// || (jtfPName.getText() == null)
				// || "".equals(jtfPName.getText().trim())) {
				// JOptionPane.showMessageDialog(new JFrame(),
				// "Bitte Name und Vorname angeben.", "Achtung",
				// JOptionPane.WARNING_MESSAGE);
				// lblName.setBackground(Color.red);
				// doLayout();
				// } else {
				// p.setPrename(jtfPPn.getText());
				// p.setName(jtfPName.getText());
				// p.setSalutation(jcbSal.getSelectedItem().toString());
				// jtfPPn.disable();
				// jtfPName.disable();
				// jcbSal.disable();
				// ad.setType((AddressType) jcbAddress.getSelectedItem());
				// ad.setAddressText(jtfAdd.getText());
				// p.add(ad);
				// JOptionPane.showMessageDialog(
				// new JFrame(),
				// ad.getType().toString()
				// + " wurde erfolgreich zum Kontakt "
				// + p.getSalutation() + " " + p.getName()
				// + " hinzugefügt.");
				// jtfAdd.setText("");
				// }
				//
			}
		});

	}
}
