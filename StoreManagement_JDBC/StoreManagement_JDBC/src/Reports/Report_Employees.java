package Reports;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import Database_Classes.ConnectDB;
import Employees.Update_Employees;
import Menu.Main_Frame;
import Misc.DialogMessage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Report_Employees extends JPanel {
	private JTextField txt_EmployeeID;
	public static String id;
	public static String fname;
	public static String lname;
	public static String desg;
	public static String dept;
	
	public Report_Employees() {
        initializeUI();    
    }

    private void initializeUI() {
        setPreferredSize(new Dimension(539, 312));
        setLayout(null);

        JTable table = new JTable(25,15);

        // Turn off JTable's auto resize so that JScrollPane will show a
        // horizontal scroll bar.
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 85, 539, 187);
        
        JButton btnGenerate = new JButton("Generate");
        btnGenerate.setFont(new Font("Arial", Font.BOLD, 14));
        btnGenerate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					DefaultTableModel tableModel = new DefaultTableModel();
					tableModel = ConnectDB.getEmployeesReport();
					table.setModel(tableModel);
				
        		} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        btnGenerate.setBounds(193, 10, 134, 21);
        add(btnGenerate);
        add(pane);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
       
        btnDelete.setBounds(35, 54, 85, 21);
        add(btnDelete);
        
        txt_EmployeeID = new JTextField();
        txt_EmployeeID.setFont(new Font("Arial", Font.BOLD, 12));
        txt_EmployeeID.setBounds(193, 55, 134, 19);
        add(txt_EmployeeID);
        txt_EmployeeID.setColumns(10);
        
        JButton button = new JButton("Back");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JComponent comp = (JComponent) e.getSource();
      		  Window win = SwingUtilities.getWindowAncestor(comp);
      		  win.dispose();
      		  Main_Frame m = new Main_Frame();
      		  m.setVisible(true);
        	}
        });
        button.setBounds(10, 10, 85, 21);
        add(button);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					ArrayList<String> a = new ArrayList<String>();
        			id = txt_EmployeeID.getText();
        			System.out.println(id);
					a = ConnectDB.getEmployeeUpdateInfo(Integer.parseInt(txt_EmployeeID.getText()));
					
        			fname = a.get(0).toString();
        			lname = a.get(1).toString();
        			desg = a.get(2).toString();
        			dept = a.get(3).toString();
        			txt_EmployeeID.setText("");
        			Update_Employees u = new Update_Employees();	
        		u.setVisible(true);
    
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnUpdate.setBounds(374, 54, 117, 21);
        add(btnUpdate);
        
        JButton btnExport = new JButton("Export");
        btnExport.setFont(new Font("Arial", Font.BOLD, 12));
        btnExport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	        	try {
							int n = ConnectDB.exportEmployee();
							if(n==1)
							{
								DialogMessage.showInfoDialog("EXPORTED");
							}
							else
							{
								DialogMessage.showWarningDialog("ERROR");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	}
        });
        btnExport.setBounds(374, 10, 117, 21);
        add(btnExport);
    
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			int id = Integer.parseInt(txt_EmployeeID.getText());
        			int num  = ConnectDB.deleteEmployee(id);
					if(num>0)
					{
						DialogMessage.showInfoDialog("DELETED");
						txt_EmployeeID.setText("");
					}
					else
					{
						DialogMessage.showWarningDialog("ERROR");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
    }

    public static void showFrame() {
        JPanel panel = new Report_Employees();
        panel.setOpaque(true);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Employees Report");
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Report_Employees.showFrame();
            }
        });
    }
}