package ch.hwz.nhtb.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import ch.hwz.nhtb.Email;
import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.filehendler.FileHandler;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelEmail extends JPanel{
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

	/**
	 * Create the panel.
	 */
	public PanelEmail() {

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

		lblCKind = new JLabel("Empf�nger");
		add(lblCKind, "3, 2, left, default");

		jcbEntry = new JComboBox(c.getContact(AddressType.getEmailMMSAddT()));
		add(jcbEntry, "5, 2, fill, default");
		jcbEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(jcbAddress);
				jcbAddress = new JComboBox(c.getAddressOnIndex(jcbEntry
						.getSelectedItem().toString(), AddressType.getEmailMMSAddT()));
				add(jcbAddress, "5, 3, fill, default");
				doLayout();
				jcbAddress.doLayout();

				System.out.println();
				for (int i = 0; i < c.getAddressOnIndex(jcbEntry
						.getSelectedIndex()).length; i++) {
					System.out.println(c.getAddressOnIndex(jcbEntry
							.getSelectedIndex())[i]);
				}
				System.out.println();
			}
		});
		

		jcbAddress = new JComboBox(c.getAddressOnIndex(jcbEntry
				.getSelectedItem().toString(), AddressType.getEmailMMSAddT()));
		add(jcbAddress, "5, 3, fill, default");

		lblText = new JLabel("Text");
		add(lblText, "3, 5");

		textPane = new JTextPane();
		add(textPane, "5, 5, fill, fill");

		btnSend = new JButton("Send");
		add(btnSend, "5, 7, right, default");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Email mit dem Inhalt: ");
				System.out.println(textPane.getText());
				System.out.println(" an : ");
				System.out.println(jcbEntry.getSelectedItem().toString());
				System.out.println(" verschickt...");

				Email email = new Email();
				email.setMessage(textPane.getText());
				System.out.println(email.getMessage());
			}
		});

	}
}
