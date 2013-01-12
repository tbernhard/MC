package ch.tom.mc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

class AddContactListener implements ActionListener {

	private JPanel mainPanel;
	private final JButton addAddress;
	private final JTextField emailField;
	private final JTextField yourNameField;

	public AddContactListener() {
		// JPanel mainPanel
		// this.mainPanel = mainPanel;
		this.addAddress = new JButton("Add");

		yourNameField = new JTextField("Thomas Bernhard", 20);
		emailField = new JTextField("xxx@xxx.ch", 20);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AddContactListener app = new AddContactListener();

		JFrame frame = new JFrame("MultiChannel - Kontakte Hinzufuegen");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setSize(400, 150);
		frame.doLayout();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
				/ 2 - frame.getSize().height / 2);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new GridLayout(2,2));
		
		String[] entryTypes = {"Person","Component"};	
		
		//Create the combo box, select item at index 4.
		JComboBox kindOfEntry = new JComboBox(entryTypes);
//		kindOfAdd.setSelectedIndex(4);
//		kindOfAdd.addActionListener(this);
		mainPanel.add(kindOfEntry);
		mainPanel.add(new JLabel());
		
		
		mainPanel.add(new JLabel("Name: "));
		mainPanel.add(app.yourNameField);
		
//		AddressType[] addressTypes = {AddressType.EMAIL, AddressType.FAX, AddressType.FESTNETZ, AddressType.MOBILE, AddressType.NETWORK };	
//		
//		//Create the combo box, select item at index 4.
//		JComboBox kindOfAdd = new JComboBox(addressTypes);
////		kindOfAdd.setSelectedIndex(4);
////		kindOfAdd.addActionListener(this);
//		mainPanel.add(kindOfAdd);
//		mainPanel.add(app.emailField);
//		

		Container contentPane = frame.getContentPane();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(app.addAddress, BorderLayout.LINE_END);

		// TODO Auto-generated method stub
		System.out.println("Kontakte Hinzufügen");

		mainPanel.doLayout();
		
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setVisible(true);

	}

}