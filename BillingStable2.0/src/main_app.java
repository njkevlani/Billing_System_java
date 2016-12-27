import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class main_app{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Rasmalai Billing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(720,480);

		window win = new window();

		window2 win2 = new window2();

		window3 win3 = new window3();

		window4 win4 = new window4();

		window5 win5 = new window5();

		JTabbedPane tabbedpanel = new JTabbedPane();

		tabbedpanel.add("Bill",win);
		tabbedpanel.add("Watch",win2);
		tabbedpanel.add("Pending Payments",win3);
		tabbedpanel.add("Payment",win4);
		tabbedpanel.add("Search Bill",win5);

		tabbedpanel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabbedpanel.getSelectedIndex() == 1){
					win2.reSetData();
				}
				else if(tabbedpanel.getSelectedIndex() == 2){
					win3.reSetData();
				}
				else if(tabbedpanel.getSelectedIndex() == 4){
					win5.reSetData();
				}
			}
		});
		frame.add(tabbedpanel);

		
		frame.setVisible(true);
	}
}

