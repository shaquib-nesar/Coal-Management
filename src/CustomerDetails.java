import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class CustomerDetails {

	private JFrame frame;
	private String cid;
		
			public void run() {
				try {
					CustomerDetails window = new CustomerDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		

	/**
	 * Create the application.
	 */
	public CustomerDetails() {
		initialize();
	}
	
	
	public void insert(String name ,String aadhaar ,String email ,String mobile, String address,String zip){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
			try {
				String s = "INSERT INTO customer (name ,aadhar ,email ,mobile, address, zip) values ('"+name+"','"+aadhaar+"','"+email+"','"+mobile+"','"+address+"','"+zip+"')";
				String s2 = "SELECT CUSTOMERID FROM customer where email = '"+email+"' and aadhar = '"+aadhaar+"'";
				Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","shaquib");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				rs = st.executeQuery(s2);
				while(rs.next())
				{									
					cid = rs.getString("CUSTOMERID");
				}
				JOptionPane.showMessageDialog(null,"Your Customer ID is "+cid,"Success,Record Entered..!!",JOptionPane.DEFAULT_OPTION);
				rs.close();
				st.close();
				con.close();
								
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Try Again..!!","Error",JOptionPane.DEFAULT_OPTION);
			}
		}	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 507);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		TextField textFieldName = new TextField();
		textFieldName.setBounds(169, 102, 181, 22);
		frame.getContentPane().add(textFieldName);
		
		TextField textFieldAadhaar = new TextField();
		textFieldAadhaar.setBounds(169, 152, 181, 22);
		frame.getContentPane().add(textFieldAadhaar);
		
		TextField textFieldEmail = new TextField();
		textFieldEmail.setBounds(169, 201, 181, 22);
		frame.getContentPane().add(textFieldEmail);
		
		TextField textFieldMobile = new TextField();
		textFieldMobile.setBounds(169, 250, 181, 22);
		frame.getContentPane().add(textFieldMobile);
		
		TextField textFieldAddress = new TextField();
		textFieldAddress.setBounds(169, 300, 181, 22);
		frame.getContentPane().add(textFieldAddress);
		
		TextField textFieldZIP = new TextField();
		textFieldZIP.setBounds(169, 349, 181, 22);
		frame.getContentPane().add(textFieldZIP);
		
		Button buttonOK = new Button("OK");
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textFieldName.getText();
				String aadhaar = textFieldAadhaar.getText();
				String email = textFieldEmail.getText();
				String mobile = textFieldMobile.getText();
				String address = textFieldAddress.getText();
				String ZIP = textFieldZIP.getText();
				
				insert(name ,aadhaar ,email ,mobile, address, ZIP);
				
				textFieldAadhaar.setText(null);
				textFieldAddress.setText(null);
				textFieldEmail.setText(null);
				textFieldMobile.setText(null);
				textFieldName.setText(null);
				textFieldZIP.setText(null);
			}
		});
		buttonOK.setBounds(272, 396, 78, 34);
		frame.getContentPane().add(buttonOK);
		
		JLabel lblAadhaarNumber = new JLabel("Aadhaar Number :");
		lblAadhaarNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAadhaarNumber.setBounds(31, 152, 111, 22);
		frame.getContentPane().add(lblAadhaarNumber);
		
		JLabel lblCustomerDetails = new JLabel("Customer Details");
		lblCustomerDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomerDetails.setForeground(Color.BLUE);
		lblCustomerDetails.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCustomerDetails.setBounds(31, 27, 257, 34);
		frame.getContentPane().add(lblCustomerDetails);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(31, 201, 111, 22);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblMolbile = new JLabel("Mobile :");
		lblMolbile.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMolbile.setBounds(31, 250, 111, 22);
		frame.getContentPane().add(lblMolbile);
		
		JLabel lblZip = new JLabel("ZIP :");
		lblZip.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblZip.setBounds(31, 349, 111, 22);
		frame.getContentPane().add(lblZip);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(31, 300, 111, 22);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(31, 102, 111, 22);
		frame.getContentPane().add(lblName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 72, 319, 2);
		frame.getContentPane().add(separator);
	}
}
