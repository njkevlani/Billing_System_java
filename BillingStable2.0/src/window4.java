import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Payment tab
public class window4 extends JPanel {
    private JLabel label1,label2;
    private JTextField textField1,textField2;
    private JButton button1;

    DB db;

    window4(){
        setLayout(new FlowLayout());
        db = new DB();

        label1 = new JLabel("Bill num: ");
        add(label1);
        textField1 = new JTextField(5);
        add(textField1);

        label2 = new JLabel("  Amount paying: ");
        add(label2);
        textField2 = new JTextField(5);
        add(textField2);

        button1 = new JButton("Done");
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(textField2.getText().equals("") || textField1.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Fill bill number and amount both");

                else{

                    String bill_no = textField1.getText();
                    int amount = Integer.parseInt(textField2.getText());
                    textField1.setText("");
                    textField2.setText("");

                    db.update_BillPaymentPending(bill_no,amount);
                }
            }
        });

    }
}
