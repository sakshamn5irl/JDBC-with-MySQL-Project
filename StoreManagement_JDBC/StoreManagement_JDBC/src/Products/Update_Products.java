package Products;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database_Classes.ConnectDB;
import Misc.DialogMessage;
import Reports.Report_Products;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Update_Products extends JFrame {

	private JPanel contentPane;
	private JTextField txt_quantity;
	private JTextField txt_price;
	private JTextField txt_Name;
	private JTextField txt_ID;
	static String[] type = {"Hello","World"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Products frame = new Update_Products();
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
	public Update_Products() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Item Quantity");
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setBounds(84, 238, 122, 19);
		contentPane.add(label);
		
		txt_quantity = new JTextField();
		txt_quantity.setFont(new Font("Arial", Font.PLAIN, 12));
		txt_quantity.setColumns(10);
		txt_quantity.setBounds(241, 238, 96, 19);
		txt_quantity.setText(Report_Products.item_quantity);
		contentPane.add(txt_quantity);
		
		txt_price = new JTextField();
		txt_price.setFont(new Font("Arial", Font.PLAIN, 12));
		txt_price.setColumns(10);
		txt_price.setBounds(241, 197, 96, 19);
		txt_price.setText(Report_Products.item_price);
		contentPane.add(txt_price);
		
		JLabel label_1 = new JLabel("Item Price");
		label_1.setFont(new Font("Arial", Font.BOLD, 14));
		label_1.setBounds(84, 200, 111, 19);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Item Type");
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		label_2.setBounds(84, 156, 111, 19);
		contentPane.add(label_2);
		
		JComboBox combo_type = new JComboBox();
		combo_type.setFont(new Font("Arial", Font.BOLD, 12));
		type = ConnectDB.getItemTypes();
		combo_type.setModel(new DefaultComboBoxModel<>(type));
		combo_type.setBounds(241, 157, 96, 21);
		combo_type.setSelectedItem(Report_Products.item_type);
		contentPane.add(combo_type);
		
		txt_Name = new JTextField();
		txt_Name.setFont(new Font("Arial", Font.PLAIN, 12));
		txt_Name.setColumns(10);
		txt_Name.setBounds(241, 120, 96, 19);
		txt_Name.setText(Report_Products.item_name);
		contentPane.add(txt_Name);
		
		JLabel label_3 = new JLabel("Item Name:");
		label_3.setFont(new Font("Arial", Font.BOLD, 14));
		label_3.setBounds(84, 120, 111, 19);
		contentPane.add(label_3);
		
		JButton btn_Update = new JButton("UPDATE");
		btn_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txt_Name.getText();
				String type = combo_type.getSelectedItem().toString();
				int price = Integer.parseInt(txt_price.getText());
				int quan = Integer.parseInt(txt_quantity.getText());
				int id = Integer.parseInt(txt_ID.getText());
				int num = ConnectDB.Update_Item(name,type,price,quan, id);
				if(num==1)
				{
					DialogMessage.showInfoDialog("UPDATED");
					setVisible(false);
				}
				else if(num==0)
				{
					DialogMessage.showWarningDialog("Error");
				}
			}
		});
		btn_Update.setFont(new Font("Arial", Font.BOLD, 16));
		btn_Update.setBounds(151, 288, 136, 27);
		contentPane.add(btn_Update);
		
		txt_ID = new JTextField();
		txt_ID.setFont(new Font("Arial", Font.BOLD, 12));
		txt_ID.setText((String) null);
		txt_ID.setEditable(false);
		txt_ID.setColumns(10);
		txt_ID.setBounds(241, 83, 96, 19);
		contentPane.add(txt_ID);
		txt_ID.setText(Report_Products.item_id);
		
		JLabel label_4 = new JLabel("ID");
		label_4.setFont(new Font("Arial", Font.BOLD, 14));
		label_4.setBounds(84, 83, 111, 19);
		contentPane.add(label_4);
		
		JLabel lblUpdateProduct = new JLabel("Update Product");
		lblUpdateProduct.setFont(new Font("Arial", Font.BOLD, 16));
		lblUpdateProduct.setBounds(132, 29, 136, 19);
		contentPane.add(lblUpdateProduct);
	}

}
