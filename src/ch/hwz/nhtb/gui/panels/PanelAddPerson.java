package ch.hwz.nhtb.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import ch.hwz.nhtb.contacts.Address;
import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.contacts.Person;
import ch.hwz.nhtb.contacts.Salutation;
import ch.hwz.nhtb.filehendler.FileHandler;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelAddPerson extends JPanel implements ActionListener {
	private JFrame frame;
	private JTextField jtfPPn;
	private JTextField jtfPName;
	private JLabel lblName;
	private JLabel lblPPn;
	private JLabel lblSal;
	private JComboBox jcbSal;
	private JComboBox jcbAddress;
	private JButton btnSave;
	private JButton btnAdd;
	private JTextField jtfAdd;

	private Contacts cPAP = new Contacts();
	private Person p = new Person();
	private Address a = new Address();

	private File contactsFile;
	private String XMLLocation;
	private FileHandler serializer;

	// private static aAdd = 0;

	/**
	 * Create the panel.
	 */
	public PanelAddPerson(final JFrame frame) {
		// ------TestContactUpload-----------------------------------------------------------------------------------------
		serializer = new FileHandler();
		// Pfad Thomas
		XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
		// Pfad Niko
		// String XMLLocation =
		// "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
		contactsFile = new File(XMLLocation);
		Contacts c = new Contacts();
		try {
			c = serializer.readContacts(contactsFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// -----------------------------------------------------------------------------------------------------------------
		this.frame = frame;
		cPAP = c;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:34dlu:grow"),
				ColumnSpec.decode("max(27dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(14dlu;default)"),
				ColumnSpec.decode("left:max(20dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC, }));

		// Anrede
		lblSal = new JLabel("Anrede");

		add(lblSal, "4, 2, 2, 1, left, default");
		jcbSal = new JComboBox(Salutation.values());
		add(jcbSal, "6, 2, 2, 1, left, default");

		lblPPn = new JLabel("Vorname*");
		add(lblPPn, "4, 6, 2, 1, left, default");

		jtfPPn = new JTextField();
		add(jtfPPn, "6, 6, 3, 1, fill, default");

		lblName = new JLabel("Name*");
		add(lblName, "4, 4, 2, 1, default, center");

		jtfPName = new JTextField();
		add(jtfPName, "6, 4, 3, 1, fill, default");
		frame.getContentPane().add(this, BorderLayout.CENTER);

		jcbAddress = new JComboBox(AddressType.values());
		add(jcbAddress, "4, 8, 2, 1, left, default");

		jtfAdd = new JTextField();
		add(jtfAdd, "6, 8, 3, 1, fill, default");

		btnAdd = new JButton("Add");
		add(btnAdd, "11, 8, fill, center");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Address ad = new Address();

				if ((jtfPPn.getText() == null)
						|| "".equals(jtfPPn.getText().trim())
						|| (jtfPName.getText() == null)
						|| "".equals(jtfPName.getText().trim())) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Bitte Name und Vorname angeben.", "Achtung",
							JOptionPane.WARNING_MESSAGE);
					lblName.setBackground(Color.red);
					doLayout();
				} else {
					p.setPrename(jtfPPn.getText());
					p.setName(jtfPName.getText());
					p.setSalutation(jcbSal.getSelectedItem().toString());
					jtfPPn.disable();
					jtfPName.disable();
					jcbSal.disable();
					ad.setType((AddressType) jcbAddress.getSelectedItem());
					ad.setAddressText(jtfAdd.getText());
					p.add(ad);
					JOptionPane.showMessageDialog(
							new JFrame(),
							ad.getType().toString()
									+ " wurde erfolgreich zum Kontakt "
									+ p.getSalutation() + " " + p.getName()
									+ " hinzugefügt.");
					jtfAdd.setText("");
				}

			}
		});

		btnSave = new JButton("Save");
		add(btnSave, "11, 10, fill, fill");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				if ((jtfPPn.getText() == null)
						|| "".equals(jtfPPn.getText().trim())
						|| (jtfPName.getText() == null)
						|| "".equals(jtfPName.getText().trim())) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Bitte Name und Vorname angeben.", "Achtung",
							JOptionPane.WARNING_MESSAGE);
					doLayout();
				} else if ((jtfAdd.getText() == null)
						|| "".equals(jtfAdd.getText().trim())) {
				} else {
					p.setPrename(jtfPPn.getText());
					p.setName(jtfPName.getText());
					p.setSalutation(jcbSal.getSelectedItem().toString());
					a.setType((AddressType) jcbAddress.getSelectedItem());
					a.setAddressText(jtfAdd.getText());
					p.add(a);

					cPAP.add(p);
					try {
						serializer.writeContacts(cPAP, contactsFile);
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(
							new JFrame(),
							p.getSalutation()
									+ " "
									+ p.getName()
									+ " wurde erfolgreich zu den Kontakten hinzugefügt");

					p = new Person();
					a = new Address();
					cPAP = new Contacts();
					frame.setVisible(false);
				}

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
