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

import ch.hwz.nhtb.SMS;
import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.filehendler.FileHandler;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelSMS extends JPanel implements ActionListener {
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
	private JLabel lblPanel;

	/**
	 * Create the panel.
	 */
	public PanelSMS() {

		serializer = new FileHandler();
		c = serializer.getContactsFromXML();
		contactsFile = serializer.getFile();

		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(2dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("90dlu"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14dlu"),
				RowSpec.decode("14dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("60dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
//		lblPanel = new JLabel("SMS");
//		add(lblPanel, "3, 2, 4, 1");

		lblCKind = new JLabel("Empf\u00E4nger");
		add(lblCKind, "3, 2, left, default");
	
		jcbEntry = new JComboBox(c.getContact(AddressType.Mobile));
		add(jcbEntry, "5, 2, fill, default");
		jcbEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(jcbAddress);
				jcbAddress = new JComboBox(c.getAdressOnIndex(jcbEntry
						.getSelectedIndex()));
				add(jcbAddress, "5, 3, fill, default");
				doLayout();
				jcbAddress.doLayout();

				System.out.println();
				for (int i = 0; i < c.getAdressOnIndex(jcbEntry
						.getSelectedIndex()).length; i++) {
					System.out.println(c.getAdressOnIndex(jcbEntry
							.getSelectedIndex())[i]);
				}
				System.out.println();
			}
		});
		
//		jcbEntry.addActionListener(this);
		
//		jcbEntry.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				System.out.println("Test"+arg0);
//				c.getAdressOnIndex(jcbEntry.getSelectedIndex());
//				jcbAddress = new JComboBox(c.getAdressOnIndex(jcbEntry.getSelectedIndex()));
//				jcbAddress.doLayout();
//				System.out.println();
//				for (int i = 0; i < c.getAdressOnIndex(jcbEntry.getSelectedIndex()).length; i++) {
//					System.out.println(c.getAdressOnIndex(jcbEntry.getSelectedIndex())[i]);
//				}
//				System.out.println();
//
//			}
//		});
		
//		KeyListener Example ENTER
//		jcbEntry.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
//					System.out.println("Test"+arg0);
//				}
//			}
//		});
		

		// TODO
//		String[] test = new String[2];
//		test[0] = c.getEntries()
//				.get(c.search(jcbEntry.getSelectedItem().toString()))
//				.getAddressTextOf(0).getAddressText();
//		test[1] = c.getEntries()
//				.get(c.search(jcbEntry.getSelectedItem().toString()))
//				.getAddressTextOf(1).getAddressText();

		jcbAddress = new JComboBox(c.getAdressOnIndex(jcbEntry.getSelectedIndex()));
		add(jcbAddress, "5, 3, fill, default");

		lblText = new JLabel("Text");
		add(lblText, "3, 5");

		textPane = new JTextPane();
		add(textPane, "5, 5, fill, fill");

		btnSend = new JButton("Send");
		add(btnSend, "5, 7, right, default");
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("MMS mit dem Inhalt: ");
				System.out.println(textPane.getText());
				System.out.println(" an : ");
				System.out.println(jcbEntry.getSelectedItem().toString());
				System.out.println(" verschickt...");

				SMS sms = new SMS();
				sms.setMessage(textPane.getText());
				System.out.println(sms.getMessage());
			}
		});
		
		
//		btnSend.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				System.out.println("SMS mit dem Inhalt: ");
//				System.out.println(textPane.getText());
//				System.out.println(" an : ");
//				System.out.println(jcbEntry.getSelectedItem().toString());
//				System.out.println(" verschickt...");

				// Address ad = new Address();
				//
				// if ((jtfPPn.getText() == null)
				// || "".equals(jtfPPn.getText().trim())
				// || (jtfPName.getText() == null)
				// || "".equals(jtfPName.getText().trim())) {
				// JOptionPane.showMessageDialog(new JFrame(),
				// "Bitte Name und Vorname angeben.", "Achtung",
				// JOptionPane.WARNING_MESSAGE);
				// lblName.setBackground(Color.red);
				// doLayout();
				// } else {
				// p.setPrename(jtfPPn.getText());
				// p.setName(jtfPName.getText());
				// p.setSalutation(jcbSal.getSelectedItem().toString());
				// jtfPPn.disable();
				// jtfPName.disable();
				// jcbSal.disable();
				// ad.setType((AddressType) jcbAddress.getSelectedItem());
				// ad.setAddressText(jtfAdd.getText());
				// p.add(ad);
				// JOptionPane.showMessageDialog(
				// new JFrame(),
				// ad.getType().toString()
				// + " wurde erfolgreich zum Kontakt "
				// + p.getSalutation() + " " + p.getName()
				// + " hinzugefügt.");
				// jtfAdd.setText("");
				// }
				//
//			}
//		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		remove(jcbAddress);
		jcbAddress = new JComboBox(c.getAdressOnIndex(jcbEntry.getSelectedIndex()));
		add(jcbAddress, "5, 3, fill, default");
		doLayout();
		jcbAddress.doLayout();
		
		System.out.println();
		for (int i = 0; i < c.getAdressOnIndex(jcbEntry.getSelectedIndex()).length; i++) {
			System.out.println(c.getAdressOnIndex(jcbEntry.getSelectedIndex())[i]);
		}
		System.out.println();
		
//		System.out.println(e.getSource());
//		System.out.println(jcbEntry.getSelectedItem().toString());
//	 jcbEntry.getSelectedIndex();
		// if (jcbEntry.getSelectedIndex() == 0) {
		// frame.setVisible(false);
		// subPanel = new PanelAddPerson(frame);
		// panel.add(subPanel, "1, 3, 7, 6, fill, fill");
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
