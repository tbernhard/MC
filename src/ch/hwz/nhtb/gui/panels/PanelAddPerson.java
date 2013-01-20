package ch.hwz.nhtb.gui.panels;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.contacts.Person;
import ch.hwz.nhtb.filehendler.FileHandler;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
//import java.awt.FlowLayout;

//TODO	Kontakte incl. Addressen speichern 
//TODO	Addresse an PAnelAddPErson zurückgeben und dort als xml speichern

public class PanelAddPerson extends JPanel implements ActionListener {
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
	private JPanel subPanel;
//	private JScrollPane jsp;
//	private JPanel subPanel0;
//	private PanelAddAddress subPanel1;
//	private PanelAddAddress subPanel2;
//	private PanelAddAddress subPanel3;
//	private PanelAddAddress subPanel4;
	private JPanel jpCollect;

	private final String[] sal = { "Herr", "Frau" };

	private Contacts c = new Contacts();
	private Person p = new Person();

	// private static aAdd = 0;

	/**
	 * Create the panel.
	 */
	public PanelAddPerson(final JFrame frame) {
		// ------TestContactUpload-----------------------------------------------------------------------------------------
		FileHandler fh = new FileHandler();
		// Pfad Thomas
		String XMLLocation = "D:/hwz/java/hwz_workspace/MC/dataFiles/Contacts.xml";
		// Pfad Niko
		// String XMLLocation =
		// "D:/Privat/HWZ/3. Semester/Java 1 und 2/Projekt/workspace/MC/dataFiles/Contacts.xml";
		File contactsFile = new File(XMLLocation);

		Contacts c = new Contacts();
		try {
			c = fh.readContacts(contactsFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// -----------------------------------------------------------------------------------------------------------------
		this.frame = frame;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:34dlu:grow"),
				ColumnSpec.decode("max(27dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(19dlu;default)"),
				ColumnSpec.decode("max(35dlu;default)"),
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
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default)"),
				RowSpec.decode("top:max(9dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:max(14dlu;default)"),
				RowSpec.decode("top:max(6dlu;default)"),}));

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

		subPanel = new PanelAddAddress(frame, c);
		add(subPanel, "3, 8, 11, 1, fill, fill");
//		jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
//		add(jsp, "3, 8, 9, 4, fill, fill");
//
//		jpCollect = new JPanel();
//		FlowLayout flowLayout = (FlowLayout) jpCollect.getLayout();
//		flowLayout.setAlignOnBaseline(true);
//		jsp.setViewportView(jpCollect);
//		
//		subPanel0 = new PanelAddAddress(frame, c);
//		jpCollect.add(subPanel0, "3, 8, 11, 1, fill, fill");
//
//		subPanel1 = new PanelAddAddress((JFrame) null, (Contacts) null);
////		jsp.setViewportView(subPanel1);
//		jpCollect.add(subPanel1, "3, 9, 11, 1, fill, center");
//
//		subPanel2 = new PanelAddAddress((JFrame) null, (Contacts) null);
////		jsp.setViewportView(subPanel2);
//		jpCollect.add(subPanel2, "3, 10, 11, 1, fill, fill");
//
//		subPanel3 = new PanelAddAddress((JFrame) null, (Contacts) null);
//		jpCollect.add(subPanel3, "3, 11, 11, 1, fill, fill");
//
//		subPanel4 = new PanelAddAddress((JFrame) null, (Contacts) null);
//
//		FormLayout formLayout = (FormLayout) subPanel4.getLayout();
//		jpCollect.add(subPanel4, "3, 12, 11, 1, fill, fill");

		btnSave = new JButton("Save");
		add(btnSave, "11, 13, left, fill");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				p.setPrename(jtfPPn.getText());
				p.setName(jtfPName.getText());
				p.setSalutation(jcbEntry.getSelectedItem().toString());
				// c.add(p);
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
		// JComboBox jcbEntry = (JComboBox) e.getSource();
		// subPanel.removeAll();
		// remove(subPanel);
		// if (jcbEntry.getSelectedIndex() == 0) {
		// frame.setVisible(false);
		// subPanel = new PanelAddPerson(frame);
		// add(subPanel, "1, 3, 7, 6, fill, fill");
		// frame.getContentPane().add(panel, BorderLayout.CENTER);
		//
		// frame.setVisible(true);
		// // System.out.println("PanelAddPerson");
		// } else if (jcbEntry.getSelectedIndex() == 1) {
		// frame.setVisible(false);
		// subPanel = new PanelAddComponent(frame);
		// panel.add(subPanel, "1, 3, 7, 6, fill, fill");
		// frame.getContentPane().add(panel, BorderLayout.CENTER);
		// frame.setVisible(true);
		// // System.out.println("PanelAddComponent");
		// }

	}
}
