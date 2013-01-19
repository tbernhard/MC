package ch.hwz.nhtb.gui.panels;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class PanelAddPerson extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public PanelAddPerson() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				ColumnSpec.decode("max(27dlu;default)"),
				ColumnSpec.decode("max(7dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Geschlaecht");
		add(lblNewLabel, "3, 2, left, default");
		
		textField = new JTextField();
		add(textField, "5, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		add(lblName, "3, 4, default, center");
		
		JTextField txtPName = new JTextField();
		add(txtPName, "5, 4, fill, default");
		
		JLabel label = new JLabel("Vorname");
		add(label, "3, 6, left, default");
		
		textField_1 = new JTextField();
		add(textField_1, "5, 6, fill, default");
		
		JButton btnSend = new JButton("Save");
		add(btnSend, "6, 8, left, fill");

	}

}
