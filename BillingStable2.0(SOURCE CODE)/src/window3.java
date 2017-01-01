import javax.swing.*;
//Payment Pending window
public class window3 extends JPanel {
    private JTable table;
    private JScrollPane scrollpane;
    private DB db;
    private String[] heading = {"Name","Bill num","Amount","Paid","Date"};
    private String[][] data;

    window3(){
        db = new DB();
        data = db.getBillPaymentPendingData();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        table = new JTable(data,heading);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(1).setPreferredWidth(6);
        table.getColumnModel().getColumn(2).setPreferredWidth(6);
        table.getColumnModel().getColumn(3).setPreferredWidth(6);
        scrollpane = new JScrollPane(table);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollpane);
    }
    void reSetData(){
        data = db.getBillPaymentPendingData();
        removeAll();
        table = new JTable(data,heading);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(1).setPreferredWidth(6);
        table.getColumnModel().getColumn(2).setPreferredWidth(6);
        table.getColumnModel().getColumn(3).setPreferredWidth(6);
        scrollpane = new JScrollPane(table);
        add(scrollpane);
    }
}
