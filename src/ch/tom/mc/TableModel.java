package ch.tom.mc;

import java.awt.List;

import javax.swing.table.AbstractTableModel;

class TableModel extends AbstractTableModel {

	// List wordsList;
	Contacts contacts;
	String headerList[] = new String[] { "1", "2", "3", "4" };

	public TableModel(Contacts contacts) {
		this.contacts = contacts;
	}

	@Override
	public int getColumnCount() {
		return this.headerList.length;
	}

	@Override
	public int getRowCount() {
		return contacts.getEntries().size();
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Entry entry = null;
		entry = contacts.getEntries().get(row);
		switch (column) {
		case 0:
			return entry.addresses.getFirst();
			// case 1:
			// return entity.;
		default:
			return "";
		}
	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		return headerList[col];
	}
}
