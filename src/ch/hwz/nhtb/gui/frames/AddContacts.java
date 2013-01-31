package ch.hwz.nhtb.gui.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hwz.nhtb.gui.panels.PanelAddComponent;
import ch.hwz.nhtb.gui.panels.PanelAddPerson;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AddContacts extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private JFrame app = new JFrame();
	private JPanel panel = new JPanel();
	private JPanel subPanel = new JPanel();

	/**
	 * Pannel erstellen
	 */
	public AddContacts(JFrame app) {
		this.app = app;
		// Erstellen und definieren des Frames
		frame = new JFrame("MultiChannel - Kontakte hinzufügen");
		frame.setSize(390, 220);
		frame.setResizable(false);
		//Aktuelles Panel entfernen
		frame.getContentPane().remove(panel);
		
		// Frame in der Mitte des Monitors setzen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
				/ 2 - frame.getSize().height / 2);

		//Panel Layout definieren ->forms-1.3.0.jar WindowBuilder (jgoodies)
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(34dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(69dlu;default)"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(5dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		// Kontaktarten
		String[] entryTypes = { "Person", "Komponente" };
		JLabel lblCKind = new JLabel("Kontakt Art");
		panel.add(lblCKind, "3, 2, left, default");
		JComboBox jcbEntry = new JComboBox(entryTypes);
		panel.add(jcbEntry, "5, 2, right, default");
		// Aktion an das Dropdown Feld anhängen
		jcbEntry.addActionListener(this);

		subPanel = new PanelAddPerson(frame, app);

		panel.add(subPanel, "1, 3, 21, 6, fill, fill");
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	/**
	 * Je nach auswhal im Dropdown feld wird ein neues Panel geladen.
	 * PanelAddPerson oder PanelAddComponent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox jcbEntry = (JComboBox) e.getSource();
		subPanel.removeAll();
		panel.remove(subPanel);
		if (jcbEntry.getSelectedIndex() == 0) {
			frame.setVisible(false);
			subPanel = new PanelAddPerson(frame, app);
			panel.add(subPanel, "1, 3, 21, 6, fill, fill");
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			frame.setVisible(true);
		} else if (jcbEntry.getSelectedIndex() == 1) {
			frame.setVisible(false);
			subPanel = new PanelAddComponent(frame, app);
			panel.add(subPanel, "1, 3, 21, 6, fill, fill");
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			frame.setVisible(true);
		}
	}
}
