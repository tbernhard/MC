package ch.hwz.nhtb.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hwz.nhtb.contacts.Address;
import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Contacts;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

//TODO	Kontakte inc 
//TODO	Addresse an PAnelAddPErson zurückgeben und dort als xml speichern

public class PanelAddAddress extends JPanel implements ActionListener {
	private JFrame frame;
	private JTextField jtfAdd;
	private JComboBox jcbEntry;
	private JOptionPane jopR;
	private JButton btnAdd;
	private JButton btnDel;

	private Contacts c;

	// private final String[] sal = { "Herr", "Frau" };

	/**
	 * Create the panel.
	 */
	public PanelAddAddress(final JFrame frame, Contacts c) {
		this.frame = frame;
		this.c = c;
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(8dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(6dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("37dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("19dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("21dlu"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("1dlu"),
				RowSpec.decode("15dlu"),}));
		jcbEntry = new JComboBox(AddressType.values());
		add(jcbEntry, "1, 2, left, default");

		jtfAdd = new JTextField();
		add(jtfAdd, "4, 2, 4, 1, fill, default");

		btnDel = new JButton("-");
		add(btnDel, "9, 2, left, center");

		btnAdd = new JButton("+");
		add(btnAdd, "11, 2, left, center");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				Address a = new Address();
				a.setType((AddressType) jcbEntry.getSelectedItem());
				a.setAddressText(jtfAdd.getText());

				// c.add(a);

				System.out.println(a.getType());
				System.out.println(a.getAddressText());

				jopR.showMessageDialog(
						new JFrame(),
						a.getType()
								+ " "
								+ a.getAddressText()
								+ " wurde erfolgreich zu den Kontakten hinzugefügt");
				frame.setVisible(false);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
