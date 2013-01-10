package ch.tom.mc;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


class EmailListener implements ActionListener {

	private final JTextField yourNameField;
	private final JTextField subjectField;
	private final JTextField textField;
	private final JTextField emailField;
	private final JTextField phoneField;

	
	
	
	
	private JPanel mainPanel;

	EmailListener(JPanel mainPanel) {
		this.mainPanel = mainPanel;
		yourNameField = new JTextField("Thomas Bernhard", 20);
		emailField = new JTextField("xxx@xxx.ch", 20);
		phoneField = new JTextField("079-444-7894", 20);
		subjectField = new JTextField("Betreff", 20);
		textField = new JTextField("Nachrichten Text", 20);
		
	}

	@Override
	public void actionPerformed(ActionEvent ignored) {
		System.out.println("Email");
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(2,5));
		mainPanel.add(new JLabel("Your name: "));
		mainPanel.add(yourNameField);


		mainPanel.add(new JLabel("Recipient Email: "));
		mainPanel.add(emailField);

		mainPanel.add(new JLabel("Recipient Phone: "));
		mainPanel.add(phoneField);
		
		mainPanel.add(new JLabel("Betreff: "));
		mainPanel.add(subjectField);
		mainPanel.add(new JLabel());

		mainPanel.add(new JLabel("Nachrichten Text: "));
		mainPanel.add(textField);
		mainPanel.add(new JLabel());

		mainPanel.doLayout();

		// try {
		// processor.send(new SmsMessage(yourNameField.getText(),
		// phoneField.getText(), providerField.getText()));
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage());
		// }
	}
}