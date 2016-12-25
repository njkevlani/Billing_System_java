/**
 * Created by nilesh on 25/12/16.
 */
import com.sun.corba.se.spi.monitoring.LongMonitoredAttributeBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class window5 extends JPanel {
    private JPanel panel0;
    private JPanel panel1;
    private JPanel panel2;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    int sum = 0, paid = 0;

    private JComboBox<String> snipper1;
    private JTextField textField1;

    //private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    //private JFormattedTextField textField2;
    private JTextField textField2;

    private JButton button1;
    private JButton button2;

    private JTable table;
    private JScrollPane scrollpane;
    private DB db;
    //private JComboBox<String> snipper[] = new JComboBox[6];
    private String[] heading = {"Cname","Item","Rate","Qty","Date","Bill num"}; //= new String[6];
    private String[][] data;// = new String[5][6];

    private String[] CustomerList;
    //retrive from DB

    window5(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        panel0 = new JPanel();
        panel0.setLayout(new FlowLayout());
        db = new DB();
        String[] temp = null;
        int i=0;
        try{
            temp = window.getCustomer();
            CustomerList = new String[temp.length + 1];
            i=0;
            CustomerList[i++] = "Cname";
            for (String t:temp){
                CustomerList[i++] = t;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        label1 = new JLabel("Name: ");
        panel0.add(label1);

        snipper1 = new JComboBox<>(CustomerList);
        panel0.add(snipper1);

        label2 = new JLabel(" Bill num: ");
        panel0.add(label2);

        textField1 = new JTextField(4);
        panel0.add(textField1);

        label3 = new JLabel(" Date: ");
        panel0.add(label3);

        //textField2 = new JFormattedTextField(df);
        textField2 = new JTextField();
        textField2.setColumns(10);
        textField2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == '/')))
                {
                    JOptionPane.showMessageDialog(null, c + "Please Enter Valid");
                    e.consume();
                }
            }
        });

        panel0.add(textField2);
        panel0.setMaximumSize(new Dimension(1000,40));
        panel0.setMinimumSize(new Dimension(50,40));

        add(panel0);


        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        button1 = new JButton("Search");
        panel2.add(button1);
        add(panel2);


        data = db.getBillData();

        for(String[] t:data)
            sum += Integer.parseInt(t[2])*Integer.parseInt(t[3]);


        //panel1 = new JPanel();
        //panel1.setLayout(new FlowLayout());

        table = new JTable(data,heading);
        scrollpane = new JScrollPane(table);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        //add(scrollpane);
        //add(panel1);

        label4 = new JLabel("Total: " + sum);
        panel1 = new JPanel();
        panel1.add(label4);

        label5 = new JLabel("Paid: " + paid);
        panel1.add(label5);
        label5.setVisible(false);

        button2 = new JButton("Clear");
        panel1.add(button2);
        add(panel1);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                data = db.getBillData();
                textField1.setText("");
                textField2.setText("");
                label5.setVisible(false);
                reSetData();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String bill_no = textField1.getText();
                String cname = CustomerList[snipper1.getSelectedIndex()];
                String date = textField2.getText();

                data = db.getBillData(cname,bill_no,date);

                sum = 0;
                for(String[] t:data)
                    sum += Integer.parseInt(t[2])*Integer.parseInt(t[3]);
                label4.setText("Total: " + sum);

                if(!bill_no.equals("")){
                    paid = db.getPaid(bill_no);
                    if(paid != -1)
                        label5.setText("Paid: " + paid);
                    else
                        label5.setText("Fully Paid");
                    label5.setVisible(true);

                }

                for(String[] t: data) {
                for(String ts :t)
                    System.out.print( ts + " ");
                System.out.println();
                }
                reSetData();

            }
        });
    }
    void reSetData(){
        removeAll();
        add(panel0);
        add(panel2);
        add(new JScrollPane(new JTable(data,heading)));

        if(!textField1.getText().equals(""))
            label5.setVisible(true);
        add(panel1);



        //for(int i=0;i<data.lenght;i++){
        //   for(int j=0 ;j<data[14].length;j++)
        //       System.out.print(data[14][j]+" ");
        //   System.out.println();
        //}
    }
}

class s {
    public static void main(String[] args) {
        window5 win = new window5();
        JFrame f = new JFrame("TEST");
        f.setSize(550, 360);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(win);
        f.setVisible(true);
    }
}
