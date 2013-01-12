/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package ch.tom.mc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 * @author ges
 * @author kwalrath
 */
/* MenuLayoutDemo.java requires no other files. */

public class MenuLayoutDemo implements ActionListener {

	private JTextField yourNameField;
	private JTextField subjectField;
	private JTextField textField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField providerField;
	private JTextField printerField;
	private JButton smsButton;
	private MessageProcessor processor = new MessageProcessor();
	private final JPanel mainPanel;

	public MenuLayoutDemo(JPanel mainPanel) {
		this.mainPanel = mainPanel;
		mainPanel.setBackground(Color.WHITE);
		// TODO Auto-generated constructor stub
	}

	public JMenuBar createMenuBar() {

		yourNameField = new JTextField("Thomas Bernhard", 20);
		emailField = new JTextField("xxx@xxx.ch", 20);
		phoneField = new JTextField("079-444-7894", 20);
		providerField = new JTextField("Swisscom", 20);
		subjectField = new JTextField("Betreff", 20);
		textField = new JTextField("Nachrichten Text", 20);
		printerField = new JTextField("Printer", 20);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
		menuBar.add(createMenu("Nachrichten"));
		menuBar.add(createMenuKontakte("Kontakte      "));

		menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
				Color.BLACK));

		return menuBar;
	}

	public JMenuBar createMain() {

		yourNameField = new JTextField("Thomas Bernhard", 20);
		emailField = new JTextField("xxx@xxx.ch", 20);
		phoneField = new JTextField("079-444-7894", 20);
		providerField = new JTextField("Swisscom", 20);
		subjectField = new JTextField("Betreff", 20);
		textField = new JTextField("Nachrichten Text", 20);
		printerField = new JTextField("Printer", 20);

		smsButton = new JButton("SMS");
		smsButton.addActionListener(new SmsListener(mainPanel));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
		menuBar.add(createMenu("Nachrichten"));
		menuBar.add(createMenuKontakte("Kontakte      "));
		// menuBar.add(createMenu("Menu 3"));

		menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
				Color.BLACK));

		return menuBar;
	}

	// used by createMenuBar
	public JMenu createMenu(String title) {
		JMenu m = new HorizontalMenu(title);

		JMenu submenu = new HorizontalMenu("Senden");

		JMenuItem x = submenu.add("SMS");
		x.addActionListener(new SmsListener(mainPanel));

		submenu.add("MMS");

		JMenuItem y = submenu.add("E-Mail");
		y.addActionListener(new EmailListener(mainPanel));

		submenu.add("PrintJob");
		m.add(submenu);

		// m.add(title + " verwalten");

		return m;
	}

	public JMenu createMenuKontakte(String title) {
		JMenu m = new HorizontalMenu(title);
		// m.addActionListener(new NachrichtenListener(mainPanel));
		JMenuItem x = m.add("Kontakte verwalten");
		x.addActionListener(new NachrichtenListener(mainPanel));
		return m;
	}

	public JMenu createMain(String title) {
		JMenu m = new HorizontalMenu(title);

		m.add(title + " verwalten");
		return m;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("MultiChannel");
		frame.setSize(400, 150);
		// Set the frame in the center of the monitor
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
				/ 2 - frame.getSize().height / 2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JPanel mainPanel = new JPanel();
		MenuLayoutDemo demo = new MenuLayoutDemo(mainPanel);
		Container contentPane = frame.getContentPane();
		contentPane.add(demo.createMenuBar(), BorderLayout.LINE_START);
		contentPane.add(mainPanel, BorderLayout.CENTER);

		// Display the window.
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	class HorizontalMenu extends JMenu {
		HorizontalMenu(String label) {
			super(label);
			JPopupMenu pm = getPopupMenu();
			pm.setLayout(new BoxLayout(pm, BoxLayout.LINE_AXIS));
		}

		public Dimension getMinimumSize() {
			return getPreferredSize();
		}

		public Dimension getMaximumSize() {
			return getPreferredSize();
		}

		public void setPopupMenuVisible(boolean b) {
			boolean isVisible = isPopupMenuVisible();
			if (b != isVisible) {
				if ((b == true) && isShowing()) {
					// Set location of popupMenu (pulldown or pullright).
					// Perhaps this should be dictated by L&F.
					int x = 0;
					int y = 0;
					Container parent = getParent();
					if (parent instanceof JPopupMenu) {
						x = 0;
						y = getHeight();
					} else {
						x = getWidth();
						y = 0;
					}
					getPopupMenu().show(this, x, y);
				} else {
					getPopupMenu().setVisible(false);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if(e.getSource()==){}
		System.out.println("SMS");

	}
}
