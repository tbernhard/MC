

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {

	private final JTextField yourNameField;
	private final JTextField subjectField;
	private final JTextField textField;
	private final JTextField emailField;
	private final JTextField phoneField;
	private final JTextField providerField;
	private final JTextField printerField;
	private final JButton emailButton;
	private final JButton smsButton;
	private final JButton mmsButton;
	private final JButton printButton;
	private final MessageProcessor processor = new MessageProcessor();

	public Application() {

		yourNameField = new JTextField("Thomas Bernhard", 20);
		emailField = new JTextField("xxx@xxx.ch", 20);
		phoneField = new JTextField("079-444-7894", 20);
		providerField = new JTextField("Swisscom", 20);
		subjectField = new JTextField("Betreff", 20);
		textField = new JTextField("Nachrichten Text", 20);
		printerField = new JTextField("Printer", 20);
		
		emailButton = new JButton("Email");
		emailButton.addActionListener(new EmailButtonListener());

		smsButton = new JButton("SMS");
		smsButton.addActionListener(new SmsButtonListener());

		mmsButton = new JButton("MMS");
		mmsButton.addActionListener(new MmsButtonListener());

		printButton = new JButton("Print");
		printButton.addActionListener(new PrintButtonListener());

	}

	public static void main(String[] args) {

		Application app = new Application();

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel(new GridLayout(7, 4));
		mainPanel.add(new JLabel("Your name: "));
		mainPanel.add(app.yourNameField);
		mainPanel.add(app.emailButton);

		mainPanel.add(new JLabel("Recipient Email: "));
		mainPanel.add(app.emailField);
		mainPanel.add(app.smsButton);

		mainPanel.add(new JLabel("Recipient Phone: "));
		mainPanel.add(app.phoneField);
		mainPanel.add(app.mmsButton);

		mainPanel.add(new JLabel("Printer: "));
		mainPanel.add(app.printerField);
		mainPanel.add(app.printButton);
		
		mainPanel.add(new JLabel("Recipient Provider: "));
		mainPanel.add(app.providerField);
		mainPanel.add(new JLabel());

		mainPanel.add(new JLabel("Betreff: "));
		mainPanel.add(app.subjectField);
		mainPanel.add(new JLabel());

		mainPanel.add(new JLabel("Nachrichten Text: "));
		mainPanel.add(app.textField);
		mainPanel.add(new JLabel());

		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}

	private class EmailButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ignored) {
			try {
				processor.send(new EmailMessage(yourNameField.getText(),
						emailField.getText()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	private class SmsButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ignored) {
			try {
				processor.send(new SmsMessage(yourNameField.getText(),
						phoneField.getText(), providerField.getText()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	private class MmsButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ignored) {
			try {
				processor.send(new MmsMessage(yourNameField.getText(),
						phoneField.getText(), providerField.getText()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	private class PrintButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ignored) {
			try {
				processor.send(new PrintMessage(yourNameField.getText(),
						printerField.getText()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
}
