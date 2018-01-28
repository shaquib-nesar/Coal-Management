import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class Order {

	private JFrame frame;	
	private JTextField textFieldCustomerID;
	private JTextField textFieldAmount;

	
			public void run() {
				try {
					Order window = new Order();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	/**
	 * Create the application.
	 */
	public Order() {
		initialize();
	}
	
	
	
	
	public void updateOrders(String type, String grade, String amount,String customerID){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
			try {
				String s = "INSERT INTO orders VALUES ("+customerID+","+amount+",'"+type+"','"+grade+"',sysdate)";
				String s2 = "UPDATE STOCK set AMOUNT = AMOUNT-"+amount+"where TYPE = '"+type+"' and GRADE = '"+grade+"'";
				Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","shaquib");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				rs = st.executeQuery(s2);
				JOptionPane.showMessageDialog(null,"Order Placed","Success",JOptionPane.DEFAULT_OPTION);
				
				rs.close();
				st.close();
				con.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 524, 318);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Type:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(58, 148, 169, 43);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JComboBox<String> comboBoxType = new JComboBox<String>();
		comboBoxType.setBounds(6, 16, 157, 20);
		panel_2.add(comboBoxType);
		comboBoxType.setEditable(false);
		comboBoxType.addItem("Coking Coal");
		comboBoxType.addItem("Non-coking Coal");
		comboBoxType.addItem("Semi-coking");
		comboBoxType.addItem("NEC Coal");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Grade:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(58, 209, 169, 43);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JComboBox<String> comboBoxGrade = new JComboBox<String>();
		comboBoxGrade.setBounds(6, 16, 157, 20);
		panel_3.add(comboBoxGrade);
		comboBoxGrade.setEditable(false);
		comboBoxGrade.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				comboBoxGrade.removeAllItems();
				if(comboBoxType.getSelectedItem().toString().equals("Coking Coal")){
					comboBoxGrade.addItem("Steel Grade");
					comboBoxGrade.addItem("Washery Grade");
					//comboBox_1.addItem("");
				}
				if(comboBoxType.getSelectedItem().toString().equals("Non-coking Coal")){
					comboBoxGrade.addItem("A");
					comboBoxGrade.addItem("B");
					comboBoxGrade.addItem("C");
				}
				if(comboBoxType.getSelectedItem().toString().equals("Semi-coking")){
					comboBoxGrade.addItem("grade - I");
					comboBoxGrade.addItem("grade - II");
				}
				if(comboBoxType.getSelectedItem().toString().equals("NEC Coal")){
					comboBoxGrade.addItem("A");
					comboBoxGrade.addItem("B");
				}
			}
			
			
		});
		
		
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String type = (String) comboBoxType.getSelectedItem();
				String grade = (String) comboBoxGrade.getSelectedItem();
				String amount = (String) textFieldAmount.getText();
				String customerID = (String) textFieldCustomerID.getText();
				updateOrders(type, grade, amount, customerID);
			
				
			}
		});
		btnPlaceOrder.setBounds(286, 178, 168, 40);
		frame.getContentPane().add(btnPlaceOrder);
		
		Label label = new Label("Orders");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.BOLD, 28));
		label.setBounds(58, 26, 144, 33);
		frame.getContentPane().add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(58, 65, 380, 2);
		frame.getContentPane().add(separator);
		
		JButton btnClickHere = new JButton("click here");
		btnClickHere.setForeground(Color.BLUE);
		btnClickHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerDetails customersDetails = new CustomerDetails();
				customersDetails.run();
			}
		});
		btnClickHere.setBounds(376, 255, 100, 23);
		frame.getContentPane().add(btnClickHere);
		btnClickHere.setBorderPainted(false);
		btnClickHere.setOpaque(false);
		btnClickHere.setBackground(Color.WHITE);
		
		JLabel lblNewCustomer = new JLabel("New Customer");
		lblNewCustomer.setBounds(312, 259, 90, 14);
		frame.getContentPane().add(lblNewCustomer);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CustomerID:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(58, 94, 166, 43);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textFieldCustomerID = new JTextField();
		textFieldCustomerID.setBounds(6, 16, 154, 20);
		panel.add(textFieldCustomerID);
		textFieldCustomerID.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Amount:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(286, 95, 166, 43);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(6, 16, 154, 20);
		panel_1.add(textFieldAmount);
		textFieldAmount.setColumns(10);
	}
}
