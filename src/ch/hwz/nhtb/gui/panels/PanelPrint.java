package ch.hwz.nhtb.gui.panels;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class PanelPrint extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelPrint() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Number");
		add(lblNewLabel, "3, 2, right, default");
		
		textField = new JTextField();
		add(textField, "5, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblText = new JLabel("Text");
		add(lblText, "3, 4");
		
		JTextPane textPane = new JTextPane();
		add(textPane, "5, 4, fill, fill");
		
		JButton btnSend = new JButton("Send");
		add(btnSend, "5, 6");

	}

}
