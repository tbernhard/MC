package ch.hwz.nhtb.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import ch.hwz.nhtb.MMS;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelMMS extends JPanel {
	private JTextField textFieldNumber;

	/**
	 * Create the panel.
	 */
	public PanelMMS() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblNumber = new JLabel("Number");
		add(lblNumber, "3, 2, right, default");

		textFieldNumber = new JTextField();
		add(textFieldNumber, "5, 2, fill, default");
		textFieldNumber.setColumns(10);

		JLabel lblMessage = new JLabel("Message");
		add(lblMessage, "3, 4");

		final JTextPane textPaneMessage = new JTextPane();
		add(textPaneMessage, "5, 4, fill, fill");

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MMS mms = new MMS();
				mms.setMessage(textPaneMessage.getText());
				System.out.println(mms.getMessage());
			}
		});
		add(btnSend, "5, 6");

	}

}
