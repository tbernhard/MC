package ch.hwz.nhtb.gui.panels;

import java.awt.BorderLayout;
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
import ch.hwz.nhtb.filehandler.FileHandler;
import ch.hwz.nhtb.gui.App;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelAddPerson extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JFrame app;
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

	private boolean b = false;

	private Contacts cPAP = new Contacts();
	private Person p = new Person();
	private Address a = new Address();

	private File contactsFile;
	private FileHandler serializer;

	/**
	 * Pannel erstellen
	 */
	public PanelAddPerson(final JFrame frame, final JFrame app) {
		serializer = new FileHandler();
		Contacts c = serializer.getContactsFromXML();
		contactsFile = serializer.getFile();

		this.setFrame(frame);
		this.setApp(app);
		cPAP = c;

		// Panel Layout definieren ->forms-1.3.0.jar WindowBuilder (jgoodies)
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

		lblPPn = new JLabel("Vorname");
		add(lblPPn, "4, 6, 2, 1, left, default");

		jtfPPn = new JTextField();
		add(jtfPPn, "6, 6, 3, 1, fill, default");

		lblName = new JLabel("Name");
		add(lblName, "4, 4, 2, 1, default, center");

		jtfPName = new JTextField();
		add(jtfPName, "6, 4, 3, 1, fill, default");
		frame.getContentPane().add(this, BorderLayout.CENTER);

		jcbAddress = new JComboBox(AddressType.getPersAddTyp());
		add(jcbAddress, "4, 8, 2, 1, left, default");

		jtfAdd = new JTextField();
		add(jtfAdd, "6, 8, 3, 1, fill, default");

		btnAdd = new JButton("Erstellen");
		add(btnAdd, "11, 8, fill, center");
		// Aktion an den Erstellen Button anhängen
		btnAdd.addMouseListener(new MouseAdapter() {
			/**
			 * Adressen werden der Kontaktart Person per Mausklick hinzugefügt
			 * Dazu muss aber eine Person angegeben werden
			 */
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent arg0) {
				Address ad = new Address();
				// Leertexte vermeinden bei Vorname und Name
				if ((jtfPPn.getText() == null)
						|| "".equals(jtfPPn.getText().trim())
						|| (jtfPName.getText() == null)
						|| "".equals(jtfPName.getText().trim())) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Bitte Name und Vorname angeben.", "Achtung",
							JOptionPane.WARNING_MESSAGE);
					doLayout();
				} else {
					// Keine Leertexte - Adresse validieren
					p.setPrename(jtfPPn.getText());
					p.setName(jtfPName.getText());
					p.setSalutation(jcbSal.getSelectedItem().toString());

					// Deaktivieren der bereits eingetragenen Angaben zur Person
					jtfPPn.disable();
					jtfPName.disable();
					jcbSal.disable();

					if (ad.validate((AddressType) jcbAddress.getSelectedItem(),
							jtfAdd.getText())) {
						// Validierung erfolgreich - Kontakt speichern
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
						// Min. eine Adresse wurde gespeichert
						b = !b;
					} else {
						JOptionPane
								.showMessageDialog(
										new JFrame(),
										"Ungültige "
												+ (AddressType) jcbAddress
														.getSelectedItem()
												+ " Adresse");
					}
				}

			}
		});

		btnSave = new JButton("Speichern");
		add(btnSave, "11, 10, fill, fill");
		// Aktion an den Speichern Button anhängen
		btnSave.addMouseListener(new MouseAdapter() {
			/**
			 * Kontaktart Person wird per Mausklick gespeichert -> in die XML
			 * geschrieben
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				Address ad = new Address();
				// Leertexte vermeinden bei Vorname und Name
				if ((jtfPPn.getText() == null)
						|| "".equals(jtfPPn.getText().trim())
						|| (jtfPName.getText() == null)
						|| "".equals(jtfPName.getText().trim())) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Bitte Name und Vorname angeben.", "Achtung",
							JOptionPane.WARNING_MESSAGE);
					doLayout();
					// Sollte bereits eine Adresse gespeichert sein ist b = true
					// - Die Person kann ohne weitere Adresse gespeichert werden
				} else if (((jtfAdd.getText() == null) || "".equals(jtfAdd
						.getText().trim())) && b) {
					// Vorheriges Fenster schliessen
					app.setVisible(false);
					p.setPrename(jtfPPn.getText());
					p.setName(jtfPName.getText());
					p.setSalutation(jcbSal.getSelectedItem().toString());

					cPAP.add(p);
					// Person in XML schreiben
					try {
						serializer.writeContacts(cPAP, contactsFile);
					} catch (JAXBException e) {
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

					// Aktualisieren der Anzeige
					frame.setVisible(false);
					App app = new App();
					app.loadContactPanel();
				} else {
					// Keine Leertexte - Adresse validieren
					if (ad.validate((AddressType) jcbAddress.getSelectedItem(),
							jtfAdd.getText())) {
						// Vorheriges Fenster schliessen
						app.setVisible(false);
						p.setPrename(jtfPPn.getText());
						p.setName(jtfPName.getText());
						p.setSalutation(jcbSal.getSelectedItem().toString());
						a.setType((AddressType) jcbAddress.getSelectedItem());
						a.setAddressText(jtfAdd.getText());
						p.add(a);

						cPAP.add(p);
						// Person in XML schreiben
						try {
							serializer.writeContacts(cPAP, contactsFile);
						} catch (JAXBException e) {
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

						// Aktualisieren der Anzeige
						frame.setVisible(false);
						App app = new App();
						app.loadContactPanel();

					} else {
						// Validierung nicht erfolgreich
						JOptionPane
								.showMessageDialog(
										new JFrame(),
										"Ungültige "
												+ (AddressType) jcbAddress
														.getSelectedItem()
												+ " Adresse");
					}
				}

			}
		});

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getApp() {
		return app;
	}

	public void setApp(JFrame app) {
		this.app = app;
	}
}
