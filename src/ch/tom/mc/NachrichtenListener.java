package ch.tom.mc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class NachrichtenListener implements ActionListener {

	private JPanel mainPanel;
	NachrichtenListener(JPanel mainPanel)  {
		this.mainPanel = mainPanel; 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Kontakte Verwalten");
		JPanel form = new JPanel(new BorderLayout()); 
		form.add(new JButton("Add"), BorderLayout.SOUTH);
		form.add(new JList(
				new String[]{"Thomas", "Hamlet", "Niko"}), BorderLayout.CENTER); 
		mainPanel.removeAll(); 
		mainPanel.setLayout(new BorderLayout()); 
		mainPanel.add(form, BorderLayout.CENTER);
		mainPanel.doLayout(); 
		form.doLayout(); 
		
		
	}
	
	
}