package ch.hwz.nhtb.gui.panels;

import java.awt.BorderLayout;
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

import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.contacts.Person;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelAddPerson extends JPanel implements ActionListener{
	private JFrame frame;
	private JTextField textField;
	private JTextField jtfPPn;
	private JTextField jtfPName;
	private JLabel lblName;
	private JLabel lblPPn;
	private JLabel lblCKind;
	private JComboBox jcbEntry;
	private JOptionPane jopR;
	private JButton btnSave;
	private JPanel subPanel = new JPanel();
	private final String[] sal = { "Herr", "Frau" };

	/**
	 * Create the panel.
	 */
	public PanelAddPerson(final JFrame frame) {
		this.frame = frame;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:34dlu:grow"),
				ColumnSpec.decode("max(27dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				ColumnSpec.decode("max(29dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,}));

		// Anrede
		lblCKind = new JLabel("Anrede");

		add(lblCKind, "4, 2, 2, 1, left, default");
		jcbEntry = new JComboBox(sal);
		add(jcbEntry, "6, 2, 2, 1, left, default");

		lblPPn = new JLabel("Vorname");
		add(lblPPn, "4, 6, 2, 1, left, default");

		jtfPPn = new JTextField();
		add(jtfPPn, "6, 6, 3, 1, fill, default");

		lblName = new JLabel("Name");
		add(lblName, "4, 4, 2, 1, default, center");

		jtfPName = new JTextField();
		add(jtfPName, "6, 4, 3, 1, fill, default");
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setVisible(true);
		
				subPanel = new PanelAddAddress(frame);
				
				add(subPanel, "3, 8, 11, 1, fill, fill");
		
		btnSave = new JButton("Save");
		add(btnSave, "11, 10, left, fill");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Contacts c = new Contacts();
				Person p = new Person();
				p.setPrename(jtfPPn.getText());
				p.setName(jtfPName.getText());
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
