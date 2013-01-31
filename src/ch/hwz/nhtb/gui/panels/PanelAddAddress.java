package ch.hwz.nhtb.gui.panels;

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

public class PanelAddAddress extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField jtfAdd;
	private JComboBox jcbEntry;
	private JOptionPane jopR;
	private JButton btnAdd;

	private Contacts c;

	/**
	 * Pannel erstellen
	 */
	public PanelAddAddress(final JFrame frame) {
		this.setFrame(frame);
		// Panel Layout definieren ->forms-1.3.0.jar WindowBuilder (jgoodies)
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("max(44dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(6dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("37dlu"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(20dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("19dlu"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("27dlu"),
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
						RowSpec.decode("1dlu"), RowSpec.decode("15dlu"), }));

		jcbEntry = new JComboBox(AddressType.values());
		add(jcbEntry, "1, 2, left, default");

		jtfAdd = new JTextField();
		add(jtfAdd, "4, 2, 4, 1, fill, default");

		btnAdd = new JButton("Erstellen");
		add(btnAdd, "10, 2, 2, 1, fill, center");
		// Aktion an den Erstellen Button anhängen
		btnAdd.addMouseListener(new MouseAdapter() {

			/**
			 * Adresse wird per Mausklick gespeichert
			 */
			@SuppressWarnings("static-access")
			@Override
			public void mousePressed(MouseEvent arg0) {
				Address a = new Address();
				a.setType((AddressType) jcbEntry.getSelectedItem());
				a.setAddressText(jtfAdd.getText());
				System.out.println(a.getType());
				System.out.println(a.getAddressText());
				jopR.showMessageDialog(
						new JFrame(),
						a.getType()
								+ " "
								+ a.getAddressText()
								+ " wurde erfolgreich zu den Kontakten hinzugefügt",
						"Speichervorgang", JOptionPane.INFORMATION_MESSAGE);
				jtfAdd.setText("");
			}
		});

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Contacts getC() {
		return c;
	}

	public void setC(Contacts c) {
		this.c = c;
	}
}
