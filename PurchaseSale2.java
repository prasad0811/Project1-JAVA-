import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class PurchaseSale2 extends JFrame{
	private JEditorPane txtMain;
    private SimpleDateFormat sdf;
	private Connection con;
	private Statement s;
	private ResultSet rs;
    private PreparedStatement ps;
	public PurchaseSale2(){
		txtMain = new JEditorPane();
		txtMain.setContentType("text/html");
		txtMain.setEditable(false);

		setTitle("Purchase/Sale");
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
			ps = con.prepareStatement("select p_no,p_date,p_amt,d_name from purchaseheader,dealer where purchaseheader.d_no = dealer.d_no and p_date=?");
              java.util.Date d = new java.util.Date() ;
					java.sql.Date newbdate = new java.sql.Date(d.getTime());
					ps.setDate(1,newbdate);
			         rs = ps.executeQuery();
			String html= "<table border=1><tr><th>Invoice No</th><th> Invoice Date</th><th>Amount</th><th>Dealer</th></tr>";
			float tot1=0;
			while(rs.next()){
				html+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>";
				tot1+=rs.getFloat(3);
			}
	
			html+="</table><br><b>Purchase Total:</b>Rs."+tot1+"/-<br><br>";

			s = con.createStatement();
			ps = con.prepareStatement("select b_no,b_date,b_amt,c_name from billheader,customer where billheader.c_no = customer.c_no and b_date=?");
              java.util.Date d2 = new java.util.Date() ;
					java.sql.Date newbdate2 = new java.sql.Date(d2.getTime());
					ps.setDate(1,newbdate2);
			         rs = ps.executeQuery();
			html+= "<table border=1><tr><th>Bill No</th><th> Bill Date</th><th>Amount</th><th>Customer</th></tr>";
			float tot2=0;
			while(rs.next()){
				html+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>";
				tot2+=rs.getFloat(3);
			}
	
			html+="</table><br><b>Sale Total:</b>Rs."+tot2+"/-<br>";

			txtMain.setText(html);
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			dispose();
		}		

	}

	public static void main(String args[]){
		new PurchaseSale2();
	}
}
