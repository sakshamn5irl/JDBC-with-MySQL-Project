package Products;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database_Classes.ConnectDB;
import Menu.Main_Frame;
import Misc.DialogMessage;

import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Products extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Item_Name;
	private JTextField txt_Item_Price;
	private JTextField txt_Item_Quantity;
	static String[] type = {"Hello","World"};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					//type = ConnectDB.getItemTypes();
					Products frame = new Products();
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
	public Products() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInsertProduct = new JLabel("Insert Product");
		lblInsertProduct.setFont(new Font("Arial", Font.BOLD, 16));
		lblInsertProduct.setBounds(132, 51, 136, 19);
		contentPane.add(lblInsertProduct);
		
		JLabel label_1 = new JLabel("Item Name:");
		label_1.setFont(new Font("Arial", Font.BOLD, 14));
		label_1.setBounds(62, 101, 122, 19);
		contentPane.add(label_1);
		
		txt_Item_Name = new JTextField();
		txt_Item_Name.setFont(new Font("Arial", Font.PLAIN, 12));
		txt_Item_Name.setColumns(10);
		txt_Item_Name.setBounds(230, 101, 122, 19);
		contentPane.add(txt_Item_Name);
		
		
		
		JLabel label_2 = new JLabel("Item Type");
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		label_2.setBounds(62, 137, 122, 19);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Item Price");
		label_3.setFont(new Font("Arial", Font.BOLD, 14));
		label_3.setBounds(62, 181, 122, 19);
		contentPane.add(label_3);
		
		txt_Item_Price = new JTextField();
		txt_Item_Price.setFont(new Font("Arial", Font.PLAIN, 12));
		txt_Item_Price.setColumns(10);
		txt_Item_Price.setBounds(230, 178, 122, 19);
		contentPane.add(txt_Item_Price);
		
		txt_Item_Quantity = new JTextField();
		txt_Item_Quantity.setFont(new Font("Arial", Font.PLAIN, 12));
		txt_Item_Quantity.setColumns(10);
		txt_Item_Quantity.setBounds(230, 219, 122, 19);
		contentPane.add(txt_Item_Quantity);
		
		JLabel label_4 = new JLabel("Item Quantity");
		label_4.setFont(new Font("Arial", Font.BOLD, 14));
		label_4.setBounds(62, 219, 133, 19);
		contentPane.add(label_4);
		
		JButton btnItemInsert = new JButton("INSERT");
		
		btnItemInsert.setFont(new Font("Arial", Font.BOLD, 16));
		btnItemInsert.setBounds(132, 275, 122, 27);
		contentPane.add(btnItemInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Frame m = new Main_Frame();
				m.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setBounds(10, 23, 85, 21);
		contentPane.add(btnBack);
		type = ConnectDB.getItemTypes();
		JComboBox combo_Item_type = new JComboBox();
		combo_Item_type.setFont(new Font("Arial", Font.BOLD, 12));
		combo_Item_type.setModel(new DefaultComboBoxModel<>(type));
		combo_Item_type.setBounds(230, 138, 122, 21);
		contentPane.add(combo_Item_type);
	
		btnItemInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txt_Item_Name.getText();
				String type = combo_Item_type.getSelectedItem().toString();
				int price = Integer.parseInt(txt_Item_Price.getText());
				int quantity = Integer.parseInt(txt_Item_Quantity.getText());
				int num = ConnectDB.Insert_item(name, type,price,quantity);
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
