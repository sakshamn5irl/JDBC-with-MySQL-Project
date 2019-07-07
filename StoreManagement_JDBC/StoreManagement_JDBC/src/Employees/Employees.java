package Employees;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database_Classes.ConnectDB;
import Menu.Main_Frame;
import Misc.DialogMessage;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Employees extends JFrame {

	private JPanel contentPane;
	private JTextField txt_LastName;
	private JTextField txt_FirstName;
	static String[] type = {"Hello","World"};
	static String[] type1 = {"Hello","World"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employees frame = new Employees();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Employees() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		type = ConnectDB.getEmployeeDepartment();
		type1 = ConnectDB.getEmployeeDesignation();
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(10, 10, 466, 473);
		contentPane.add(panel);
		
		JLabel lblEmployee = new JLabel("Insert Employee");
		lblEmployee.setFont(new Font("Arial", Font.BOLD, 16));
		lblEmployee.setBounds(118, 46, 146, 30);
		panel.add(lblEmployee);
		
		JLabel lblFirstName = new JLabel("Last Name");
		lblFirstName.setFont(new Font("Arial", Font.BOLD, 14));
		lblFirstName.setBounds(52, 143, 111, 19);
		panel.add(lblFirstName);
		
		txt_LastName = new JTextField();
		txt_LastName.setFont(new Font("Arial", Font.PLAIN, 12));
		txt_LastName.setColumns(10);
		txt_LastName.setBounds(209, 143, 127, 19);
		panel.add(txt_LastName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Arial", Font.BOLD, 14));
		lblGender.setBounds(52, 179, 111, 19);
		panel.add(lblGender);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setFont(new Font("Arial", Font.BOLD, 14));
		lblDesignation.setBounds(52, 223, 111, 19);
		panel.add(lblDesignation);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Arial", Font.BOLD, 14));
		lblDepartment.setBounds(52, 261, 122, 19);
		panel.add(lblDepartment);
		
		JButton button = new JButton("INSERT");
		
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setBounds(118, 319, 146, 30);
		panel.add(button);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Main_Frame m = new Main_Frame();
			setVisible(false);
			m.setVisible(true);
			}
		});
		button_1.setBounds(0, 10, 85, 21);
		panel.add(button_1);
		
		txt_FirstName = new JTextField();
		txt_FirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		txt_FirstName.setColumns(10);
		txt_FirstName.setBounds(209, 104, 127, 19);
		panel.add(txt_FirstName);
		
		JLabel label = new JLabel("First Name");
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setBounds(52, 104, 111, 19);
		panel.add(label);
		
		JComboBox combo_Department = new JComboBox();
		combo_Department.setFont(new Font("Arial", Font.BOLD, 12));
		combo_Department.setModel(new DefaultComboBoxModel<>(type));
		combo_Department.setBounds(209, 262, 127, 21);
		panel.add(combo_Department);
		
		JComboBox combo_Designation = new JComboBox();
		combo_Designation.setFont(new Font("Arial", Font.BOLD, 12));
		combo_Designation.setModel(new DefaultComboBoxModel<>(type1));
		combo_Designation.setBounds(209, 224, 127, 21);
		panel.add(combo_Designation);
		
		JComboBox combo_Gender = new JComboBox();
		combo_Gender.setFont(new Font("Arial", Font.BOLD, 12));
		combo_Gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		combo_Gender.setBounds(209, 180, 127, 21);
		panel.add(combo_Gender);
	
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String fname = txt_FirstName.getText();
				String lname = txt_LastName.getText();
				String gender = combo_Gender.getSelectedItem().toString();
				String desg = combo_Designation.getSelectedItem().toString();
				String dept = combo_Department.getSelectedItem().toString();
				int num = ConnectDB.Insert_Employee(fname,lname, gender,desg,dept);
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
	}
}
