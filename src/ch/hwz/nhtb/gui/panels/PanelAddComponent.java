package ch.hwz.nhtb.gui.panels;

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
import ch.hwz.nhtb.contacts.Component;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.filehendler.FileHandler;
import ch.hwz.nhtb.gui.App;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelAddComponent extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JFrame app;
	private JLabel lblLoc;
	private JLabel lblName;
	private JTextField jtfLoc;
	private JTextField jtfCName;
	private JTextField jtfAdd;
	private JComboBox jcbAddress;
	private JButton btnSave;
	private JButton btnAdd;

	private boolean b = false;

	private Contacts cPAC = new Contacts();
	private Component comp = new Component();
	private Address a = new Address();

	private File contactsFile;
	private FileHandler serializer;

	/**
	 * Create the panel.
	 */
	public PanelAddComponent(final JFrame frame, final JFrame app) {
		serializer = new FileHandler();
		Contacts c = serializer.getContactsFromXML();
		contactsFile = serializer.getFile();

		this.setFrame(frame);
		this.app = app;
		cPAC = c;

		setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("50dlu"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(32dlu;default)"),
						ColumnSpec.decode("20dlu"),
						ColumnSpec.decode("max(13dlu;default)"),
						ColumnSpec.decode("max(14dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						RowSpec.decode("max(24dlu;default)"),
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		lblLoc = new JLabel("Location");
		add(lblLoc, "3, 2, left, default");

		jtfLoc = new JTextField();
		add(jtfLoc, "5, 2, 3, 1, fill, default");
		jtfLoc.setColumns(10);

		lblName = new JLabel("Name");
		add(lblName, "3, 4, default, center");

		jtfCName = new JTextField();
		add(jtfCName, "5, 4, 3, 1, fill, default");

		jcbAddress = new JComboBox(AddressType.getCompAddT());
		add(jcbAddress, "3, 6, left, default");

		jtfAdd = new JTextField();
		add(jtfAdd, "5, 6, 3, 1, fill, default");

		btnAdd = new JButton("Add");
		add(btnAdd, "10, 6, fill, center");
		btnAdd.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "deprecation" })
			@Override
			public void mousePressed(MouseEvent arg0) {
				Address ad = new Address();

				if ((jtfCName.getText() == null)
						|| "".equals(jtfCName.getText().trim())
						|| (jtfLoc.getText() == null)
						|| "".equals(jtfLoc.getText().trim())) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Bitte Location und Name angeben.", "Achtung",
							JOptionPane.WARNING_MESSAGE);
					doLayout();
				} else {
					comp.setName(jtfCName.getText());
					comp.setLocation(jtfLoc.getText());

					jtfLoc.disable();
					jtfCName.disable();

					if (ad.validate((AddressType) jcbAddress.getSelectedItem(),
							jtfAdd.getText())) {
						ad.setType((AddressType) jcbAddress.getSelectedItem());
						ad.setAddressText(jtfAdd.getText());
						comp.add(ad);
						JOptionPane.showMessageDialog(new JFrame(),
								ad.getType().toString()
										+ " wurde erfolgreich zum Kontakt "
										+ comp.getName() + " hinzugefügt.");
						jtfAdd.setText("");
						b = !b;
					} else {
						JOptionPane
								.showMessageDialog(
										new JFrame(),
										"Ungültige "
												+ (AddressType) jcbAddress
														.getSelectedItem()
												+ " Adresse");
						jtfAdd.setBackground(Color.RED);
					}
				}

			}
		});

		btnSave = new JButton("Save");
		add(btnSave, "10, 8, fill, fill");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				app.setVisible(false);
				if ((jtfCName.getText() == null)
						|| "".equals(jtfCName.getText().trim())
						|| (jtfLoc.getText() == null)
						|| "".equals(jtfLoc.getText().trim())) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Bitte Location und Name angeben.", "Achtung",
							JOptionPane.WARNING_MESSAGE);
					doLayout();
				} else if (((jtfAdd.getText() == null) || "".equals(jtfAdd
						.getText().trim())) || b) {
					comp.setName(jtfCName.getText());
					comp.setLocation(jtfLoc.getText());
				
					cPAC.add(comp);

					try {
						serializer.writeContacts(cPAC, contactsFile);
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(new JFrame(), comp.getName()
							+ " wurde erfolgreich zu den Kontakten hinzugefügt");

					comp = new Component();
					a = new Address();
					cPAC = new Contacts();
					frame.setVisible(false);
					App app = new App();
					app.loadContactPanel();
				} else {
					comp.setName(jtfCName.getText());
					comp.setLocation(jtfLoc.getText());
					a.setType((AddressType) jcbAddress.getSelectedItem());
					a.setAddressText(jtfAdd.getText());
					comp.add(a);

					cPAC.add(comp);

					try {
						serializer.writeContacts(cPAC, contactsFile);
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(new JFrame(), comp.getName()
							+ " wurde erfolgreich zu den Kontakten hinzugefügt");

					comp = new Component();
					a = new Address();
					cPAC = new Contacts();
					frame.setVisible(false);
					App app = new App();
					app.loadContactPanel();

				}

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
