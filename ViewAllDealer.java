import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

public class ViewAllDealer extends JFrame{
	private JEditorPane txtMain;

	private Connection con;
	private Statement s;
	private ResultSet rs;

	public ViewAllDealer(){
		txtMain = new JEditorPane();
		txtMain.setContentType("text/html");
		txtMain.setEditable(false);

		setTitle("View All Dealer");
		setSize(700,500);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    		setLocation(x, y);

		add(new JScrollPane(txtMain), "Center");	
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "pgj123"); 
			s = con.createStatement();
			rs = s.executeQuery("select * from dealer order by d_no");

			String html= "<table border=1><tr><th>Dealer ID</th><th>Name</th><th>Phone</th><th>Address</th></tr>";

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

	public static void main(String args[]){
		new ViewAllDealer();
	}
}
