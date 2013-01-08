package ch.tom.mc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

class SmsListener implements ActionListener {
		
		private JPanel mainPanel; 
		
		SmsListener(JPanel mainPanel) {
			this.mainPanel = mainPanel; 
		}

		@Override
		public void actionPerformed(ActionEvent ignored) {
			System.out.println("SMS");
			mainPanel.removeAll(); 
			mainPanel.setLayout(new BorderLayout()); 
			mainPanel.add(new JLabel("Sending SMS..."), BorderLayout.CENTER);
			mainPanel.doLayout(); 
			
//			try {
//				processor.send(new SmsMessage(yourNameField.getText(),
//						phoneField.getText(), providerField.getText()));
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, e.getMessage());
//			}
		}
	}