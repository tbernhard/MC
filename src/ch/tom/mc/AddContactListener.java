package ch.tom.mc;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.xml.bind.JAXBException;

class AddContactListener implements ActionListener {

	private JPanel mainPanel;
	private final JButton addAddress;
	private final JTextField emailField;
	private final JTextField yourNameField;
	
	public AddContactListener()  {
		//JPanel mainPanel
		//this.mainPanel = mainPanel; 
		this.addAddress = new JButton("Add");
		
		yourNameField = new JTextField("Thomas Bernhard", 20);
		emailField = new JTextField("xxx@xxx.ch", 20);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AddContactListener app = new AddContactListener();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new GridLayout(7, 4));
		
	
		// TODO Auto-generated method stub
		System.out.println("Kontakte Hinzufügen");
		
		
		mainPanel.add(app.yourNameField);
		mainPanel.add(app.addAddress);
		
//		JPanel form = new JPanel(new BorderLayout()); 
//		form.add(new JButton("Add"), BorderLayout.SOUTH);
//		form.add(new JList(
//				new String[]{"Thomas", "Hamlet", "Niko"}), BorderLayout.CENTER); 
		
//		form.add(new JList(c.getCNames()), BorderLayout.CENTER); 
		
		mainPanel.removeAll(); 
		mainPanel.setLayout(new BorderLayout()); 
//		mainPanel.add(form, BorderLayout.CENTER);
		mainPanel.doLayout(); 
//		form.doLayout(); 
		
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
}