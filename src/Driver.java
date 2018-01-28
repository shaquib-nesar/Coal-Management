import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class Driver {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver window = new Driver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Driver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	  private void initialize() {
		frame = new JFrame();
		//frame.setBounds(100, 100, 1024, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		
		
		
		JButton btnStockAvailability = new JButton("Stock Availability");
		btnStockAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StockAvailability stockAvailability = new StockAvailability();
				stockAvailability.run();				
			}});
		btnStockAvailability.setEnabled(false);
		btnStockAvailability.setBounds(380, 463, 156, 42);
		frame.getContentPane().add(btnStockAvailability);
		
		
		JButton btnPushStock = 	new JButton("Push Stock");
		btnPushStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PushStock pushStock = new PushStock();
				pushStock.run();	
			}});
		btnPushStock.setEnabled(false);
		btnPushStock.setBounds(632, 463, 121, 42);
		frame.getContentPane().add(btnPushStock);
		
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Order order = new Order();
				order.run();				
			}});
		btnOrder.setEnabled(false);
		btnOrder.setBounds(857, 463, 121, 42);
		frame.getContentPane().add(btnOrder);
		
		JButton btnCustomerDetails = new JButton("Customer Details");
		btnCustomerDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerDetails customerDetails = new CustomerDetails();
				customerDetails.run();				
			}});
		btnCustomerDetails.setEnabled(false);
		btnCustomerDetails.setBounds(1070, 463, 156, 42);
		frame.getContentPane().add(btnCustomerDetails);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				login.frame.setVisible(true);			
				
			    if (Login.c()) {
					btnStockAvailability.setEnabled(Login.c());
					btnPushStock.setEnabled(Login.c());
					btnOrder.setEnabled(Login.c());
					btnCustomerDetails.setEnabled(Login.c());
					btnLogin.setEnabled(false);
				}	
			}
		});
		btnLogin.setBounds(150, 463, 121, 42);
		frame.getContentPane().add(btnLogin);		
		
		JLabel lblCoalManagement = new JLabel("Coal Management System");
		lblCoalManagement.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblCoalManagement.setBounds(632, 46, 673, 81);
		frame.getContentPane().add(lblCoalManagement);
	}
}
