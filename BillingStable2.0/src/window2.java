/**
 * Created by nilesh on 22/12/16.
 */
import javax.swing.*;

public class window2 extends JPanel {
    private JTable table;
    private JScrollPane scrollpane;
    private DB db;
    private String[] heading = {"Cname","Item","Rate","Qty","Date","Bill num"};
    private String[][] data;

    window2(){
        db = new DB();
        data = db.getBillData();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        table = new JTable(data,heading);
        scrollpane = new JScrollPane(table);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollpane);
    }
    void reSetData(){
        data = db.getBillData();
        removeAll();
        table = new JTable(data,heading);
        table.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
        table.getColumnModel().getColumn(2).setPreferredWidth(4);
        table.getColumnModel().getColumn(3).setPreferredWidth(4);
        table.getColumnModel().getColumn(5).setPreferredWidth(6);
        scrollpane = new JScrollPane(table);
        add(scrollpane);
    }
}
