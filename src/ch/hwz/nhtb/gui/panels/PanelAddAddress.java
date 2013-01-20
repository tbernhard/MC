package ch.hwz.nhtb.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.contacts.Person;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelAddAddress extends JPanel implements ActionListener {
	private JFrame frame;
	private JTextField jtfAdd;
	private JComboBox jcbEntry;
	private JOptionPane jopR;
	private JButton btnAdd;
	private JButton btnDel;

	// private final String[] sal = { "Herr", "Frau" };

	/**
	 * Create the panel.
	 */
	public PanelAddAddress(final JFrame frame) {
		this.frame = frame;
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(8dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(7dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("43dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("19dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(17dlu;default)"),
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
				Contacts c = new Contacts();
				Person p = new Person();
//				p.setPrename(jtfPPn.getText());
//				p.setName(jtfPName.getText());
				p.setSalutation(jcbEntry.getSelectedItem().toString());
				c.add(p);
				System.out.println(p.getSalutation());
				System.out.println(p.getPrename());
				System.out.println(p.getName());
				jopR.showMessageDialog(new JFrame(), p.getSalutation() + " "
						+ p.getName()
						+ " wurde erfolgreich zu den Kontakten hinzugefügt");
				frame.setVisible(false);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
