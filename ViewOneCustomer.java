import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ViewOneCustomer extends JFrame{
	private JLabel lblID,lblName,lblPhone,lblAddress;
  	private JTextField txtID,txtName,txtPhone,txtAddress,txtCount;
  	private JButton btnFirst,btnPrev,btnNext,btnLast;
  	private JPanel panCenter,panSouth;

  	private Connection con;
  	private Statement s;
  	private ResultSet rs;

  	private int n;

  	public ViewOneCustomer(){
    		lblID = new JLabel("ID:");
    		lblName = new JLabel("Name:");
    		lblPhone = new JLabel("Phone:");
    		lblAddress = new JLabel("Address:");
 
    		txtID = new JTextField();
    		txtName = new JTextField();
    		txtPhone = new JTextField();
    		txtAddress = new JTextField();
    		txtCount = new JTextField();
 
		txtID.setEditable(false);
		txtName.setEditable(false);
		txtPhone.setEditable(false);
		txtAddress.setEditable(false);
		txtCount.setEditable(false);

    		btnFirst = new JButton("<<");
    		btnPrev = new JButton("<");
    		btnNext = new JButton(">");
    		btnLast = new JButton(">>");
    
    		btnFirst.setToolTipText("Move to first record");
    		btnPrev.setToolTipText("Move to previous record");
    		btnNext.setToolTipText("Move to next record");
    		btnLast.setToolTipText("Move to last record");

    		panCenter = new JPanel();
    		panCenter.setLayout(new GridLayout(4,2));
		panCenter.add(lblID);
		panCenter.add(txtID);
		panCenter.add(lblName);
		panCenter.add(txtName);
		panCenter.add(lblPhone);
		panCenter.add(txtPhone);
		panCenter.add(lblAddress);
		panCenter.add(txtAddress);

    		panSouth = new JPanel();
    		panSouth.setLayout(new GridLayout(1,5));
    		panSouth.add(btnFirst);
    		panSouth.add(btnPrev);
    		panSouth.add(txtCount);
    		panSouth.add(btnNext);
    		panSouth.add(btnLast);

    		setTitle("View One Customer");
    		setSize(400,200);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    		setLocation(x, y);

    		add(panCenter,"Center");
    		add(panSouth,"South");
    		setVisible(true);
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    		ButtonHandler bh = new ButtonHandler();
    		btnFirst.addActionListener(bh);
    		btnPrev.addActionListener(bh);
    		btnNext.addActionListener(bh);
    		btnLast.addActionListener(bh);

    		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "pgj123"); 
      
      			s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
      			rs = s.executeQuery("select * from customer");
      			rs.last();
      			n = rs.getRow();
      			rs.first();
      			showRec();
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,e);
      			dispose();
    		}
  	}

  	public void showRec() throws Exception{
      		txtID.setText(rs.getString(1));
      		txtName.setText(rs.getString(2));
      		txtPhone.setText(rs.getString(3));
      		txtAddress.setText(rs.getString(4));
      		txtCount.setText(rs.getRow()+"/"+n);
	}

	class ButtonHandler implements ActionListener{
    		public void actionPerformed(ActionEvent ae){
      			try{
      				if(ae.getSource()==btnFirst){
          				rs.first(); 
      				}
      				if(ae.getSource()==btnPrev){
          				rs.previous();
          				if(rs.isBeforeFirst()) rs.first();
      				}
      				if(ae.getSource()==btnNext){
	  				rs.next();
	  				if(rs.isAfterLast()) rs.last();
      				}
      				if(ae.getSource()==btnLast){
 	  				rs.last();
      				}
        			showRec();
      			}
      			catch(Exception e){
        			JOptionPane.showMessageDialog(null,e);
      			}
    		}
  	}

	public static void main(String args[]){
		new ViewOneCustomer();
	}
}   
