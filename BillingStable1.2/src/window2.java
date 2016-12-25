/**
 * Created by nilesh on 22/12/16.
 */
import javax.swing.*;



public class window2 extends JPanel {
    private JTable table;
    private JScrollPane scrollpane;
    private DB db;
    //private JComboBox<String> snipper[] = new JComboBox[6];
    private String[] heading = {"Cname","Item","Rate","Qty","Date","Bill num"}; //= new String[6];
    private String[][] data;// = new String[5][6];
    //retrive from DB

    window2(){
        db = new DB();
        data = db.getBillData();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //data = {{"Cname","Item","Rate","Qty","Date","Bill num"}};
        table = new JTable(data,heading);
        scrollpane = new JScrollPane(table);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollpane);
    }
    void reSetData(){
        data = db.getBillData();
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

/*
class s{
    public static void main(String[] args) {
        window2 win = new window2();
        JFrame f = new JFrame("TEST");
        f.setSize(500,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(win);
        f.setVisible(true);
    }
}
*/