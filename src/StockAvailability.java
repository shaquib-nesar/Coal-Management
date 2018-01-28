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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class StockAvailability {

	private JFrame frame;
	private String amount;
	
			public void run() {
				try {
					StockAvailability window = new StockAvailability();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	

	/**
	 * Create the application.
	 */
	public StockAvailability() {
		initialize();
	}

	
	
	public void getAvailability(String type, String grade){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
			try {
				String s = "Select * from stock where type = '"+type+"' and grade = '"+grade+"'";
				Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","shaquib");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				while(rs.next())
				{									
					amount = rs.getString("amount");
				}
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
		frame.setBounds(100, 100, 552, 326);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Type:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(45, 95, 208, 63);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox<String> comboBoxType = new JComboBox<String>();
		comboBoxType.setBounds(6, 16, 196, 40);
		panel.add(comboBoxType);
		comboBoxType.setEditable(false);
		comboBoxType.addItem("Coking Coal");
		comboBoxType.addItem("Non-coking Coal");
		comboBoxType.addItem("Semi-coking");
		comboBoxType.addItem("NEC Coal");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Grade:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(285, 95, 208, 63);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox<String> comboBoxGrade = new JComboBox<String>();
		comboBoxGrade.setBounds(6, 16, 196, 40);
		panel_1.add(comboBoxGrade);
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
					comboBoxGrade.addItem("grade-I");
					comboBoxGrade.addItem("grade-II");
				}
				if(comboBoxType.getSelectedItem().toString().equals("NEC Coal")){
					comboBoxGrade.addItem("A");
					comboBoxGrade.addItem("B");
				}
			}
			
			
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Amount(in kgs):", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(285, 190, 208, 63);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JTextPane textPaneAmount = new JTextPane();
		textPaneAmount.setForeground(new Color(0, 0, 0));
		textPaneAmount.setFont(new Font("Tahoma", Font.PLAIN, 26));
		textPaneAmount.setToolTipText("");
		textPaneAmount.setBounds(6, 16, 196, 40);
		panel_2.add(textPaneAmount);
		textPaneAmount.setEditable(false);
		textPaneAmount.setBackground(Color.WHITE);
		
		
		
		Label label = new Label("Stocks");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.BOLD, 28));
		label.setBounds(45, 23, 144, 33);
		frame.getContentPane().add(label);
		
		
		
		JButton btnCheckAvailability = new JButton("Check Availability");
		btnCheckAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type = (String) comboBoxType.getSelectedItem();
				String grade = (String) comboBoxGrade.getSelectedItem();
				getAvailability(type, grade);
				textPaneAmount.setText(amount);
				
				}
		});
		btnCheckAvailability.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCheckAvailability.setBounds(51, 206, 196, 40);
		frame.getContentPane().add(btnCheckAvailability);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(45, 67, 448, 2);
		frame.getContentPane().add(separator);
	}
}
