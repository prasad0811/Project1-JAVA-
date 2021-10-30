import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

public class ViewAllProduct extends JFrame{
	private JEditorPane txtMain;
	private JComboBox cmbCategory;
	private JPanel panNorth;

	private Connection con;
	private Statement s;
	private ResultSet rs;

	public ViewAllProduct(){
		txtMain = new JEditorPane();
		txtMain.setContentType("text/html");
		txtMain.setEditable(false);

		cmbCategory = new JComboBox();
		panNorth = new JPanel();
		panNorth.add(cmbCategory);

		setTitle("View All Product");
		setSize(700,500);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    		setLocation(x, y);
		add(panNorth,"North");
		add(new JScrollPane(txtMain), "Center");	
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "pgj123"); 
			s = con.createStatement();
			rs = s.executeQuery("select distinct icategory from item");
			cmbCategory.addItem("Select Category");
			while(rs.next()){
				cmbCategory.addItem(rs.getString(1));
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			dispose();
		}		
		
		cmbCategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				try{
					String cat = cmbCategory.getSelectedItem().toString();
					s = con.createStatement();
					rs = s.executeQuery("select * from item where icategory='"+cat+"' order by ino");

					String html= "<table border=1><tr><th>Product ID</th><th>Name</th><th>Qty</th><th>Price</th></tr>";

					while(rs.next()){
						html+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>";
					}
	
					html+="</table>";

					txtMain.setText(html);
				}	 
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					dispose();
				}
			}
		});	
	}

	public static void main(String args[]){
		new ViewAllProduct();
	}
}
