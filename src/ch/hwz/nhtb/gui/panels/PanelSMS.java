package ch.hwz.nhtb.gui.panels;

import java.awt.event.MouseAdapter;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.xml.bind.JAXBException;

import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.filehendler.FileHandler;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelSMS extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelSMS() {
		
		// ------------------------------------------------------------------------------------------------------------------------
		// Load data from xml
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
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ------------------------------------------------------------------------------------------------------------------------
				
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Number");
		add(lblNewLabel, "3, 2, right, default");
		
		textField = new JTextField();
		add(textField, "5, 2, fill, default");
		textField.setColumns(10);
		
		
		JLabel lblText = new JLabel("Text");
		add(lblText, "3, 4");
		
		JTextPane textPane = new JTextPane();
		add(textPane, "5, 4, fill, fill");

		JButton btnSearch = new JButton("Search");
		add(btnSearch, "6, 2");
		btnSearch.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				Address ad = new Address();
//
//				if ((jtfPPn.getText() == null)
//						|| "".equals(jtfPPn.getText().trim())
//						|| (jtfPName.getText() == null)
//						|| "".equals(jtfPName.getText().trim())) {
//					JOptionPane.showMessageDialog(new JFrame(),
//							"Bitte Name und Vorname angeben.", "Achtung",
//							JOptionPane.WARNING_MESSAGE);
//					lblName.setBackground(Color.red);
//					doLayout();
//				} else {
//					p.setPrename(jtfPPn.getText());
//					p.setName(jtfPName.getText());
//					p.setSalutation(jcbSal.getSelectedItem().toString());
//					jtfPPn.disable();
//					jtfPName.disable();
//					jcbSal.disable();
//					ad.setType((AddressType) jcbAddress.getSelectedItem());
//					ad.setAddressText(jtfAdd.getText());
//					p.add(ad);
//					JOptionPane.showMessageDialog(
//							new JFrame(),
//							ad.getType().toString()
//									+ " wurde erfolgreich zum Kontakt "
//									+ p.getSalutation() + " " + p.getName()
//									+ " hinzugefügt.");
//					jtfAdd.setText("");
//				}
//
//			}
		});
		
		
		JButton btnSend = new JButton("Send");
		add(btnSend, "6, 6");
		btnSend.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				Address ad = new Address();
//
//				if ((jtfPPn.getText() == null)
//						|| "".equals(jtfPPn.getText().trim())
//						|| (jtfPName.getText() == null)
//						|| "".equals(jtfPName.getText().trim())) {
//					JOptionPane.showMessageDialog(new JFrame(),
//							"Bitte Name und Vorname angeben.", "Achtung",
//							JOptionPane.WARNING_MESSAGE);
//					lblName.setBackground(Color.red);
//					doLayout();
//				} else {
//					p.setPrename(jtfPPn.getText());
//					p.setName(jtfPName.getText());
//					p.setSalutation(jcbSal.getSelectedItem().toString());
//					jtfPPn.disable();
//					jtfPName.disable();
//					jcbSal.disable();
//					ad.setType((AddressType) jcbAddress.getSelectedItem());
//					ad.setAddressText(jtfAdd.getText());
//					p.add(ad);
//					JOptionPane.showMessageDialog(
//							new JFrame(),
//							ad.getType().toString()
//									+ " wurde erfolgreich zum Kontakt "
//									+ p.getSalutation() + " " + p.getName()
//									+ " hinzugefügt.");
//					jtfAdd.setText("");
//				}
//
//			}
		});
		

	}
}
