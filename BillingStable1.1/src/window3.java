import javax.swing.*;

/**
 * Created by nilesh on 23/12/16.
 */
//Payment Pending window
public class window3 extends JPanel {
    private JTable table;
    private JScrollPane scrollpane;
    private DB db;
    //private JComboBox<String> snipper[] = new JComboBox[6];
    private String[] heading = {"Name","Bill num","Amount","Paid","Date"}; //= new String[6];
    private String[][] data;// = new String[5][6];
    //retrive from DB

    window3(){
        db = new DB();
        data = db.getBillPaymentPendingData();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //data = {{"Cname","Item","Rate","Qty","Date","Bill num"}};
        table = new JTable(data,heading);
        scrollpane = new JScrollPane(table);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollpane);
    }
    void reSetData(){
        data = db.getBillPaymentPendingData();
        removeAll();
        table = new JTable(data,heading);
        scrollpane = new JScrollPane(table);
        add(scrollpane);


        //for(int i=0;i<data.lenght;i++){
        //   for(int j=0 ;j<data[14].length;j++)
        //       System.out.print(data[14][j]+" ");
        //   System.out.println();
        //}
    }
}
