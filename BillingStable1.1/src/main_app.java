import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class main_app{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Rasmalai Billing");
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550,360);

		window win = new window();

		window2 win2 = new window2();

		window3 win3 = new window3();

		window4 win4 = new window4();

		JTabbedPane tabbedpanel = new JTabbedPane();

		tabbedpanel.add("Bill",win);
		tabbedpanel.add("Watch",win2);
		tabbedpanel.add("Pending Payments",win3);
		tabbedpanel.add("Payment",win4);

		System.out.println(tabbedpanel.getSelectedIndex());
		tabbedpanel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//System.out.println(tabbedpanel.getSelectedIndex()+1);
				if(tabbedpanel.getSelectedIndex() == 1){
					System.out.println("NJK");
					win2.reSetData();
				}
				else if(tabbedpanel.getSelectedIndex() == 2){
					System.out.println("NJK");
					win3.reSetData();
				}
			}
		});
		frame.add(tabbedpanel);

		
		frame.setVisible(true);
	}
}