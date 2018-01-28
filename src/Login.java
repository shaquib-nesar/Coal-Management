import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login {
	public static boolean flag;
	JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	/**
	 * Connection to database for login table
	 */
	public static boolean loginCheck(String user, String pass)  
	{	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
			try {
				String s = "Select * from logindb where username = '"+user+"' and password = '"+pass+"'";
				Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","shaquib");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				while(rs.next())
				{				
					return true;
				}
				rs.close();
				st.close();
				con.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
	
	public static boolean c()
	{
		return flag;
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblLogin.setBounds(66, 29, 130, 34);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(66, 95, 107, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(66, 136, 107, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				@SuppressWarnings("deprecation")
				String password = txtPassword.getText();
				String username = txtUsername.getText();
								
				flag = loginCheck(username,password);
				if(flag) {
					txtUsername.setText(null);
					txtPassword.setText(null);
					frame.setVisible(false);	
				}
				else {
					JOptionPane.showMessageDialog(null,"Invalid Details","Login Error",JOptionPane.ERROR_MESSAGE);
					txtUsername.setText(null);
					txtPassword.setText(null);
				}
				
			}
			
		});
		btnLogin.setBounds(341, 197, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(200, 93, 168, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(66, 174, 364, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(66, 74, 364, 2);
		frame.getContentPane().add(separator_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(200, 134, 168, 20);
		frame.getContentPane().add(txtPassword);
	}
}
