package Customer;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database_Classes.ConnectDB;
import Menu.Customer_Main_Frame;
import Misc.DialogMessage;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Customer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Customer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBounds(232, 94, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_1.setBounds(232, 135, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_2.setBounds(232, 175, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_3.setBounds(232, 217, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("INSERT");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname = textField.getText();
				String lname = textField_1.getText();
				String address = textField_2.getText();
			    int phone = Integer.parseInt(textField_3.getText());
				int num = ConnectDB.Insert_Customer(fname,lname, address,phone);
				if(num>0)
				{
				DialogMessage.showInfoDialog("Item Inserted Succesfully");
				}
				else
				{
					DialogMessage.showWarningDialog("Error in Inserting the Item");
				}
			}
		});
		btnNewButton.setBounds(155, 264, 115, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setFont(new Font("Arial", Font.BOLD, 14));
		lblFirstname.setBounds(79, 97, 115, 13);
		contentPane.add(lblFirstname);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Arial", Font.BOLD, 14));
		lblLastName.setBounds(79, 138, 115, 13);
		contentPane.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Arial", Font.BOLD, 14));
		lblAddress.setBounds(79, 178, 115, 13);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 14));
		lblPhoneNumber.setBounds(79, 220, 115, 13);
		contentPane.add(lblPhoneNumber);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer_Main_Frame c = new Customer_Main_Frame();
				setVisible(false);
				c.setVisible(true);
			}
		});
		button.setBounds(10, 10, 85, 21);
		contentPane.add(button);
		
		JLabel lblInsertCustomers = new JLabel("INSERT CUSTOMERS");
		lblInsertCustomers.setFont(new Font("Arial", Font.BOLD, 18));
		lblInsertCustomers.setBounds(102, 50, 190, 21);
		contentPane.add(lblInsertCustomers);
	}
}
