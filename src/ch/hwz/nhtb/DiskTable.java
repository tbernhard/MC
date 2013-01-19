package ch.hwz.nhtb;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
 
public class DiskTable extends JFrame {
 
    public DiskTable() {
        List<Disk> list = new ArrayList<Disk>();
        list.add(new CD("TrulaTrula", "????"));
        list.add(new CD("Titel1", "Interpret X"));
        list.add(new CD("Titel2", "Interpret X"));
        list.add(new DVD("Titel4", "Interpret Y"));
        list.add(new CD("Titel5", "Interpret Z"));
        list.add(new DVD("Titel6", "Interpret X"));
        JTable table = new JTable(new DiskModel(list));
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    }
 
    class DiskModel extends AbstractTableModel {
        private List<Disk> list;
 
        public DiskModel(List<Disk> list) {
            this.list = list;
        }
 
        public String getColumnName(int column) {
            switch (column) {
            case 0:
                return "Titel";
            case 1:
                return "Interpret";
            case 2:
                return "Typ";
            }
            return "";
        }
 
        public Object getValueAt(int row, int column) {
            Disk disk = list.get(row);
            switch (column) {
            case 0:
                return disk.getTitle();
            case 1:
                return disk.getInterpret();
            case 2:
                return disk.getType();
            }
            return null;
        }
 
        public int getColumnCount() {
            return 3;
        }
 
        public int getRowCount() {
            return list.size();
        }
    }
 
    class CD extends Disk {
        public CD(String title, String interpret) {
            super(title, interpret);
        }
    }
 
    class DVD extends Disk {
        public DVD(String title, String interpret) {
            super(title, interpret);
        }
    }
 
    abstract class Disk {
        private String title, interpret;
 
        public Disk(String title, String interpret) {
            this.title = title;
            this.interpret = interpret;
        }
 
        public String getTitle() {
            return title;
        }
 
        public String getInterpret() {
            return interpret;
        }
 
        public String getType() {
            return this.getClass().getSimpleName();
        }
    }
 
    public static void main(String[] args) {
        JFrame frame = new DiskTable();
        frame.setBounds(0, 0, 500, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}