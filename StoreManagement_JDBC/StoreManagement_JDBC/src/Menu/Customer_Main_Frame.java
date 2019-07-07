package Menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Customer.Customer;
import Customer.Order;
import LoginPackage.LoginFrame;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Customer_Main_Frame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Main_Frame frame = new Customer_Main_Frame();
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
	public Customer_Main_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCustomers = new JButton("CUSTOMERS");
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Customer c = new Customer();
			c.setVisible(true);
			setVisible(false);
			}
		});
		btnCustomers.setForeground(Color.RED);
		btnCustomers.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnCustomers.setBackground(new Color(135, 206, 250));
		btnCustomers.setBounds(127, 73, 175, 54);
		contentPane.add(btnCustomers);
		
		JButton btnOrders = new JButton("ORDERS");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Order o;
			try {
				o = new Order();

				o.setVisible(true);
				setVisible(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnOrders.setForeground(Color.RED);
		btnOrders.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnOrders.setBackground(new Color(135, 206, 250));
		btnOrders.setBounds(127, 184, 175, 54);
		contentPane.add(btnOrders);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame l = new LoginFrame();
				l.setVisible(true);
				setVisible(false);
			}
		});
		button.setForeground(Color.RED);
		button.setFont(new Font("Arial Black", Font.BOLD, 18));
		button.setBackground(new Color(135, 206, 250));
		button.setBounds(10, 0, 175, 54);
		contentPane.add(button);
	}

}
