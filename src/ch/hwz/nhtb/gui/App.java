package ch.hwz.nhtb.gui;

import java.awt.BorderLayout;
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

import ch.hwz.nhtb.gui.panels.PanelContact;
import ch.hwz.nhtb.gui.panels.PanelEmail;
import ch.hwz.nhtb.gui.panels.PanelMMS;
import ch.hwz.nhtb.gui.panels.PanelPrint;
import ch.hwz.nhtb.gui.panels.PanelSMS;

public class App {

	private JFrame frame;
	private JPanel panel = new JPanel();
	private Container contentPane;
	private JMenuBar menuBar;
	private JMenu hmNach;
	private JMenu jmKVer;
	private JMenuItem jmiSMS;
	private JMenuItem jmiMMS;
	private JMenuItem jmiEmail;
	private JMenuItem jmiPrint;

	/**
	 * Starten der Applikation
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
	 * Erstellen der Applikation.
	 */
	public App() {
		initialize();
	}

	/**
	 * Inhalt des Frames initialisieren
	 */
	private void initialize() {
		// Erstellen und definieren des Frames
		frame = new JFrame("MultiChannel");
		frame.setSize(390, 280);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Frame in der Mitte des Monitors setzen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
				/ 2 - frame.getSize().height / 2);

		// JMenuBar erstellen
		menuBar = new JMenuBar();
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));

		// Menu Nachrichten erstellen
		hmNach = new JMenu("Nachrichten");
		hmNach.setLayout(new BoxLayout(hmNach, BoxLayout.LINE_AXIS));
		hmNach.setMinimumSize(hmNach.getPreferredSize());
		hmNach.setMaximumSize(hmNach.getPreferredSize());

		// Untermenu SMS
		jmiSMS = new JMenuItem("SMS");
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

		// Untermenu MMS
		jmiMMS = new JMenuItem("MMS");
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

		// Untermenu EMail
		jmiEmail = new JMenuItem("E-Mail");
		jmiEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.getContentPane().remove(panel);
				panel = new PanelEmail();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
		hmNach.add(jmiEmail);

		// Untermenu Print
		jmiPrint = new JMenuItem("Print");
		jmiPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.getContentPane().remove(panel);
				panel = new PanelPrint();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
		hmNach.add(jmiPrint);
		menuBar.add(hmNach);

		// Menu Kontakte erstellen
		jmKVer = new JMenu("  Kontakte  ");
		jmKVer.setLayout(new BoxLayout(jmKVer, BoxLayout.LINE_AXIS));
		jmKVer.setMinimumSize(jmKVer.getPreferredSize());
		jmKVer.setMaximumSize(jmKVer.getPreferredSize());
		jmKVer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				loadContactPanel();
			}
		});
		menuBar.add(jmKVer);

		// ContentPane aufsetzen und erstellen
		contentPane = frame.getContentPane();
		contentPane.add(menuBar, BorderLayout.LINE_START);
	}

	public void loadContactPanel() {
		frame.getContentPane().remove(panel);
		panel = new PanelContact(frame);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
