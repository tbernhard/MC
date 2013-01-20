package ch.hwz.nhtb.gui.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hwz.nhtb.contacts.Component;
import ch.hwz.nhtb.contacts.Contacts;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelAddComponent extends JPanel {
	private JFrame frame;
	private JTextField jtfLoc;
	private JTextField jtfCName;
	private JButton btnSave;

	/**
	 * Create the panel.
	 */
	public PanelAddComponent(JFrame frame) {
		this.frame = frame;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(7dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblNewLabel = new JLabel("Location");
		add(lblNewLabel, "3, 2, left, default");

		jtfLoc = new JTextField();
		add(jtfLoc, "5, 2, fill, default");
		jtfLoc.setColumns(10);

		JLabel lblName = new JLabel("Name");
		add(lblName, "3, 4, default, center");

		jtfCName = new JTextField();
		add(jtfCName, "5, 4, fill, default");

		btnSave = new JButton("Save");
		add(btnSave, "6, 6, left, bottom");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Contacts c = new Contacts();
				Component cp = new Component();
				cp.setLocation(jtfLoc.getText());
				cp.setName(jtfCName.getText());
				c.add(cp);
				System.out.println(cp.getLocation());
				System.out.println(cp.getName());

			}
		});

	}
	

}
