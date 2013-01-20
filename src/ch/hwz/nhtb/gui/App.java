package ch.hwz.nhtb.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ch.hwz.nhtb.gui.panels.PanelEmail;
import ch.hwz.nhtb.gui.panels.PanelContact;
import ch.hwz.nhtb.gui.panels.PanelMMS;
import ch.hwz.nhtb.gui.panels.PanelPrint;
import ch.hwz.nhtb.gui.panels.PanelSMS;

public class App {

	private JFrame frame;
	private JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Create and set up the window.
		frame = new JFrame("MultiChannel");
		frame.setSize(380, 233);
		// Set the frame in the center of the monitor
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
				/ 2 - frame.getSize().height / 2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(Color.WHITE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));

		// Create menu Nachrichten
		JMenu hmNach = new JMenu("Nachrichten");
		hmNach.setLayout(new BoxLayout(hmNach, BoxLayout.LINE_AXIS));
		hmNach.setMinimumSize(hmNach.getPreferredSize());
		hmNach.setMaximumSize(hmNach.getPreferredSize());
		// SMS
		JMenuItem jmiSMS = new JMenuItem("SMS");
		jmiSMS.setAlignmentX(Component.LEFT_ALIGNMENT);
		jmiSMS.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.getContentPane().remove(panel);
				panel = new PanelSMS();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
		hmNach.add(jmiSMS);
		// MMS
		JMenuItem jmiMMS = new JMenuItem("MMS");
		jmiMMS.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.getContentPane().remove(panel);
				panel = new PanelMMS();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
		hmNach.add(jmiMMS);
		// EMail
		JMenuItem jmiEmail = new JMenuItem("E-Mail");
		jmiEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.getContentPane().remove(panel);
				panel = new PanelEmail();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
		hmNach.add(jmiEmail);
		// Print
		JMenuItem jmiPrint = new JMenuItem("Print");
		jmiPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.getContentPane().remove(panel);
				panel = new PanelPrint();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
		hmNach.add(jmiPrint);
		menuBar.add(hmNach);

		// Create menu Kontakte
		JMenu jmKVer = new JMenu("  Kontakte  ");
		jmKVer.setLayout(new BoxLayout(jmKVer, BoxLayout.LINE_AXIS));
		jmKVer.setMinimumSize(jmKVer.getPreferredSize());
		jmKVer.setMaximumSize(jmKVer.getPreferredSize());
		jmKVer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.getContentPane().remove(panel);
				panel = new PanelContact();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
		menuBar.add(jmKVer);

		// Create and set up the content pane.
		Container contentPane = frame.getContentPane();
		contentPane.add(menuBar, BorderLayout.LINE_START);
	}
}
