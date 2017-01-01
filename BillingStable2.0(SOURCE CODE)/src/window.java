import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class window extends JPanel{

	DB db;

	private JLabel label0;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;

	String bill_no;

	private JComboBox<String> snipper;
	String[] ItemList;

	private JComboBox<String> snipper0;
	String[] CustmerList;

	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;

	private JTable table;
	private JScrollPane scrollpane;
	static int i = 0,sum = 0;

	private JPanel panel0;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;

	String[][] data = new String[10][4];;
	String[] heading = {"Item","Quantity","Price","SubTotal"};

	static String[] getItem() throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.home") + "/Rasmalai-Billing/Files/items.txt"));
		ArrayList<String> list = new ArrayList<>();
		String t=in.readLine();
		while(t != null){
			list.add(t);
			t = in.readLine();
		}
		in.close();
		return list.toArray(new String[list.size()]);

	}
	static String[] getCustomer()throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.home") + "/Rasmalai-Billing/Files/customers.txt"));
		String t = in.readLine();
		ArrayList<String> list = new ArrayList<>();
		while (t != null){
			list.add(t);
			t = in.readLine();
		}
		in.close();
		return list.toArray(new String[list.size()]);
	}

	static String get_bill_no() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/Rasmalai-Billing/Files/bill.txt"));
		String str = br.readLine();
		br.close();
		return str;
	}

	void set_bill_no() throws Exception{
		bill_no = "" + (Integer.parseInt(bill_no)+1);

		FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Rasmalai-Billing/Files/bill.txt");
		fos.write(bill_no.getBytes());

		fos.close();
	}

	public window(){
		db = new DB();
		try{
			bill_no = get_bill_no();
			CustmerList =  getCustomer();
			ItemList = getItem();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		panel0 = new JPanel();
		panel0.setLayout(new FlowLayout());
		panel0.setSize(new Dimension(550, 35));
		panel0.setPreferredSize(new Dimension(550, 35));
		panel0.setMaximumSize(new Dimension(550, 35));


		label0 = new JLabel("Name: ");
		panel0.add(label0);
		
		snipper0 = new JComboBox<String>(CustmerList);
		panel0.add(snipper0);

		label7 = new JLabel("  Bill num: " + bill_no);
		panel0.add(label7);

		String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

		label5 = new JLabel("   Date:  " + date);
		panel0.add(label5);

		add(panel0);

		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setSize(new Dimension(550, 35));
		panel1.setPreferredSize(new Dimension(550, 35));
		panel1.setMaximumSize(new Dimension(550, 35));

		label1 = new JLabel("Item: ");
		panel1.add(label1);

		snipper = new JComboBox<String>(ItemList);
		panel1.add(snipper);

		label2 = new JLabel("Qty: ");
		panel1.add(label2);

		textField1 = new JTextField(3);
		panel1.add(textField1);

		label3 = new JLabel("Price: ");
		label3.setToolTipText("Price of item");
		panel1.add(label3);

		textField2 = new JTextField(3);
		panel1.add(textField2);

		button1 = new JButton("Add");
		panel1.add(button1);

		add(panel1);

		panel2 = new JPanel();
		panel2.setSize(new Dimension(550, 35));
		panel2.setMaximumSize(new Dimension(550, 35));

		table = new JTable(data,heading);
		scrollpane = new JScrollPane(table);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());
		scrollpane.setPreferredSize(new Dimension(530, 19));
		panel2.add(scrollpane);

		add(panel2);

		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.setSize(new Dimension(550, 35));
		panel3.setPreferredSize(new Dimension(550, 35));
		panel3.setMaximumSize(new Dimension(550, 35));

		button4 = new JButton("Cancel Bill");
		panel3.add(button4);

		label4 = new JLabel("Total: " + sum);
		panel3.add(label4);

		label6 = new JLabel("  Paid: ");
		panel3.add(label6);

		textField3 = new JTextField("0",4);
		panel3.add(textField3);

		button2 = new JButton("Done");
		button2.setToolTipText("Done making bill");
		panel3.add(button2);

		button3 = new JButton("Print");
		button3.setVisible(false);
		panel3.add(button3);

		add(panel3);


		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textField2.getText().equals("") || textField1.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Fille Price and Quantity both!");

				else{
					data[i][0] = ItemList[snipper.getSelectedIndex()];
					data[i][1] = textField1.getText();
					data[i][2] = textField2.getText();
					int temp = Integer.parseInt(data[i][1]) * Integer.parseInt(data[i][2]);
					data[i][3] = "" + temp;
					sum += temp;
					i++;

					textField1.setText("");
					textField2.setText("");

					snipper.requestFocus();

					panel2.setMaximumSize(new Dimension(550, 35 + 16*i));
					scrollpane.setPreferredSize(new Dimension(530, 19 + (16*i)));
					scrollpane.setSize(new Dimension(530, 19 + (16*i)));
					label4.setText("Total: " + sum);
					revalidate();
					repaint();
				}
			}
		});
		//done button
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panel1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(true);
				button4.setText("New Bill");

				String cust = CustmerList[snipper0.getSelectedIndex()];

				for(int l=0;l<i;l++) {
					String[] ts = {cust, data[l][0], bill_no, date};
					int[] ti = {Integer.parseInt(data[l][2]), Integer.parseInt(data[l][1])};

					db.add_row_in_Billing(ts, ti);
				}
				String t = textField3.getText();
				int paid = 0;
				paid = Integer.parseInt(t);
				if(sum != paid)
					db.add_in_BillPaymentPending(cust,bill_no,sum,paid,date);

				try{set_bill_no();}
				catch (Exception ee){ee.printStackTrace();}



				revalidate();
				repaint();
			}
		}
		);

		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				PrinterJob pjob = PrinterJob.getPrinterJob();
				PageFormat preformat = pjob.defaultPage();
				preformat.setOrientation(PageFormat.LANDSCAPE);
				PageFormat postformat = pjob.pageDialog(preformat);
				if (preformat != postformat) {
					pjob.setPrintable(new Printer(panel0.getParent()), postformat);
					if (pjob.printDialog()) {
						try{
							pjob.print();
						}
						catch (Exception e){
							e.printStackTrace();
						}
					}
				}

			}
		});

		//textField constraints

		textField1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))
				{
					JOptionPane.showMessageDialog(null, c + "Please Enter Valid");
					e.consume();
				}
			}
		});

		textField2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))
				{
					JOptionPane.showMessageDialog(null, c + "Please Enter Valid");
					e.consume();
				}
			}
		});
		textField3.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))
				{
					JOptionPane.showMessageDialog(null, c + "Please Enter Valid");
					e.consume();
				}
			}
		});

		//new / cancel
		button4.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						i=sum=0;
						snipper0.requestFocus();
						textField1.setText("");
						textField2.setText("");
						textField3.setText("0");
						panel1.setVisible(true);
						label4.setText("  Total: " + sum);
						label7.setText("  Bill num: " + bill_no);
						button2.setVisible(true);
						button3.setVisible(false);
						scrollpane.setSize(530,19);
						scrollpane.setPreferredSize(new Dimension(530,19));
						panel2.revalidate();
						panel2.repaint();
						revalidate();
						repaint();
					}
				}

		);
	}
}