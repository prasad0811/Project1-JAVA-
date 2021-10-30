import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
public class Dealer extends JFrame{
	private JLabel lblDealerID,lblName,lblPhone,lblAddress;
  	private JTextField txtDealerID,txtName,txtPhone,txtAddress;
 	private JButton btnSave,btnClear,btnDelete,btnEdit,btnSearch,
				btnViewOne,btnViewAll,btnExit;
  	private JPanel panCenter,panSouth;

  	private Connection con;
  	private Statement s;
  	private PreparedStatement ps;
  	private ResultSet rs;
         public int id2;
   public String cn2,addr2,phno2;
  	private int did;

  	public Dealer(){
		lblDealerID = new JLabel("Dealer ID:");    		
		lblName = new JLabel("Dealer Name:");
    		lblPhone = new JLabel("Phone No:");
    		lblAddress = new JLabel("Address:");
             lblDealerID.setFont(new Font("Arial Black", Font.BOLD,13));
				   lblName.setFont(new Font("Arial Black", Font.BOLD,13));
				   lblPhone.setFont(new Font("Arial Black", Font.BOLD,13));
				   lblAddress.setFont(new Font("Arial Black", Font.BOLD,13));
		txtDealerID = new JTextField();
    		txtName = new JTextField();
    		txtPhone = new JTextField();
    		txtAddress = new JTextField();
			//txtDealerID.setBackground(Color.yellow);
			//txtName.setBackground(Color.yellow);
			//txtPhone.setBackground(Color.yellow);
			//txtAddress.setBackground(Color.yellow);
			 txtDealerID.setBorder(new LineBorder(Color.black,1));
			txtName.setBorder(new LineBorder(Color.black,1));
               txtPhone.setBorder(new LineBorder(Color.black,1));
            txtAddress.setBorder(new LineBorder(Color.black,1));
                txtDealerID.setFont(new Font("Verdana", Font.BOLD,13));
                txtName.setFont(new Font("Verdana", Font.BOLD,13));
                txtPhone.setFont(new Font("Verdana", Font.BOLD,13));
                txtAddress.setFont(new Font("Verdana", Font.BOLD,13));
		txtDealerID.setEditable(false);

    		btnSave = new JButton("Save");
    		btnClear = new JButton("Clear");
    		btnSearch = new JButton("Search");
    		btnDelete = new JButton("Delete");
    		btnEdit = new JButton("Update");
    		btnViewOne = new JButton("View One");
    		btnViewAll = new JButton("View All");
    		btnExit = new JButton("Exit");
            Color blue8 = new Color(0,255,255);
                btnSave.setBackground(blue8);
                btnSave.setFont(new Font("Arial Black", Font.BOLD,13));
		btnSave.setForeground(Color.BLACK);
		Color blue7 = new Color(0,255,255);
                   btnClear.setBackground(blue7);
                btnClear.setFont(new Font("Arial Black", Font.BOLD,13));
		btnClear.setForeground(Color.BLACK);
		Color blue9 = new Color(0,255,255);
		btnSearch.setBackground(blue9);
                btnSearch.setFont(new Font("Arial Black", Font.BOLD,13));
		btnSearch.setForeground(Color.BLACK);
		Color blue = new Color(0,255,255);
		btnDelete.setBackground(blue);
             btnDelete.setFont(new Font("Arial Black", Font.BOLD,13));
		btnDelete.setForeground(Color.BLACK);
		Color blue5 = new Color(0,255,255);
		btnEdit.setBackground(blue5);
                btnEdit.setFont(new Font("Arial Black", Font.BOLD,13));
		btnEdit.setForeground(Color.BLACK);
      Color blue4 = new Color(0,255,255);	
	btnViewOne.setBackground(blue4);
                btnViewOne.setFont(new Font("Arial Black", Font.BOLD,13));
		btnViewOne.setForeground(Color.BLACK);
		Color blue3 = new Color(0,255,255);
		btnViewAll.setBackground(blue3);
                btnViewAll.setFont(new Font("Arial Black", Font.BOLD,13));
		btnViewAll.setForeground(Color.BLACK);
     Color blue2 = new Color(0,255,255);
		btnExit.setBackground(blue2);
                btnExit.setFont(new Font("Arial Black", Font.BOLD,13));
		btnExit.setForeground(Color.BLACK);
    		btnSave.setMnemonic('S');
    		btnClear.setMnemonic('C');
    		btnEdit.setMnemonic('E');
    		btnDelete.setMnemonic('D');
    		btnViewOne.setMnemonic('O');
    		btnViewAll.setMnemonic('A');
    		btnSearch.setMnemonic('r');
    		btnExit.setMnemonic('x');
    
    		panCenter = new JPanel();
			//panCenter.setBackground(Color.white);
    		panCenter.setLayout(new GridLayout(4,2));
		panCenter.add(lblDealerID);
		panCenter.add(txtDealerID);
    		panCenter.add(lblName);
    		panCenter.add(txtName);
    		panCenter.add(lblPhone);
    		panCenter.add(txtPhone);
    		panCenter.add(lblAddress);
    		panCenter.add(txtAddress);

		panSouth = new JPanel();
		panSouth.setLayout(new GridLayout(1,8));
		panSouth.add(btnSave);
    		panSouth.add(btnClear);
    		panSouth.add(btnSearch);
    		panSouth.add(btnEdit);
    		panSouth.add(btnDelete);
    		panSouth.add(btnViewOne);
    		panSouth.add(btnViewAll);
		panSouth.add(btnExit);

    		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    		double width = screenSize.getWidth();
    		double height = screenSize.getHeight();

    		setTitle("Dealer Information");
    		setSize(850,250);
    		setLocation((int)((width-getWidth())/2),(int)((height-getHeight())/2));
    		add(panCenter,"Center");
    		add(panSouth,"South");
    		setVisible(true);
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setResizable(false);
    		ButtonHandler bh = new ButtonHandler();
    		btnSave.addActionListener(bh);
    		btnClear.addActionListener(bh);
    		btnSearch.addActionListener(bh);
    		btnEdit.addActionListener(bh);
    		btnDelete.addActionListener(bh);
    		btnViewOne.addActionListener(bh);
    		btnViewAll.addActionListener(bh);
    		btnExit.addActionListener(bh);
     
    		try{
      			Class.forName("org.postgresql.Driver");
      			con = DriverManager.getConnection("jdbc:postgresql:demo","postgres","pgj123");
			s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery("select * from Dealer");
			rs.last();
			did = rs.getRow()+1;

			txtDealerID.setText(Integer.toString(did));
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,e);
      			dispose();
    		}
  	}

  	public void saveRec(){
    		try{
			int id = Integer.parseInt(txtDealerID.getText());
      			String cn = txtName.getText();
      			String phno = txtPhone.getText();
      			String addr = txtAddress.getText();
                       int c1,c2,c3;
                      id2=id;
                   cn2=new String(cn);
                   phno2=new String(phno);
                   addr2=new String(addr);

                   c1=cn.length(); c2=phno.length(); c3=addr.length();
                 if(c1>0 && c2==10 && c3>0)
            {              

          		ps = con.prepareStatement("insert into Dealer values(?,?,?,?)");
			ps.setInt(1,id);
      			ps.setString(2,cn);
      			ps.setString(3,phno);
      			ps.setString(4,addr);
      			ps.executeUpdate();
      			JOptionPane.showMessageDialog(null,"Record saved successfully.");
			did++;
			clearRec();
                   txtName.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
               txtPhone.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            txtAddress.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
    		}

              else 
      {
       txtName.setBorder(new LineBorder(Color.RED,1));
               txtPhone.setBorder(new LineBorder(Color.RED,1));
            txtAddress.setBorder(new LineBorder(Color.RED,1));
         JOptionPane.showMessageDialog(null,"Record not Save please Give Correct Input ");
        }
}
    		catch(Exception e){
      			xx(id2,cn2,phno2,addr2);
    		}
  	}

  	public void clearRec(){
		txtDealerID.setText(Integer.toString(did));
    		txtName.setText("");
    		txtPhone.setText("");
    		txtAddress.setText("");
    		txtName.requestFocus();
  	}

  	public void searchRec(){
    		try{
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Dealer No:"));
      			ps = con.prepareStatement("select * from Dealer where d_no=?");
      			ps.setInt(1,id);
      			rs = ps.executeQuery();
      			if(rs.next()){
				txtDealerID.setText(rs.getString(1));
        			txtName.setText(rs.getString(2));
        			txtPhone.setText(rs.getString(3));
				txtAddress.setText(rs.getString(4));
                                txtName.setEditable(false);
                                txtPhone.setEditable(false);
                                txtAddress.setEditable(false);
      			}
      			else{
				JOptionPane.showMessageDialog(null,"Dealer "+id+" not found.");
      			}    
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,e);
    		}
  	}

  	public void deleteRec(){
    		try{
      			int cno = Integer.parseInt(JOptionPane.showInputDialog("Enter Dealer No:"));
        int cx = Integer.parseInt(txtDealerID.getText());
                     if(cno==cx || cno>cx)
                 {
              throw new NullPointerException();
}
      			ps = con.prepareStatement("delete from Dealer where d_no=?");
      			ps.setInt(1,cno);
      			ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Record "+ cno+" deleted successfully.");
			clearRec();
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,"Record not found ");
    		}
  	}

  	 void update(){
   try{
                       int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Dealer No:"));
      			ps = con.prepareStatement("select * from Dealer where d_no=?");
      			ps.setInt(1,id);
      			rs = ps.executeQuery();
      			if(rs.next()){
				txtDealerID.setText(rs.getString(1));
        			txtName.setText(rs.getString(2));
        			txtPhone.setText(rs.getString(3));
				txtAddress.setText(rs.getString(4));
                                txtName.setEditable(true);
                                txtPhone.setEditable(true);
                                txtAddress.setEditable(true);
      			}
      			else{
				throw new NullPointerException();
      			}    
             
    		}
            
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,"Record Not Updated");
                 clearRec();
    		}
}



           

  	                      void xx(int v1, String s4,String s5, String s6)
{
    try{
			int idd = v1;
      			String cn =new String(s4); 
      			String ph = new String(s5); 
      			String addr = new String(s6); 

      			ps = con.prepareStatement("update Dealer set d_no=? ,d_name=?, d_phno=?, d_addr=? where d_no=?");
      			ps.setInt(1,idd);
                        ps.setString(2,cn);
      			ps.setString(3,ph);
      			ps.setString(4,addr);
      			ps.setInt(5,idd);
			ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Customer "+idd+" updated.");
			clearRec();
                  }
    		
            
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,"Record Not Updated");
                   clearRec();
    		}
}
  		

  	class ButtonHandler implements ActionListener{
    		public void actionPerformed(ActionEvent ae){
      			if(ae.getSource()==btnSave)
        			saveRec();
      			if(ae.getSource()==btnClear)
        			clearRec();
      			if(ae.getSource()==btnExit)
        			dispose();
      			if(ae.getSource()==btnViewOne)
        			new ViewOneDealer();
      			if(ae.getSource()==btnViewAll)
				new ViewAllDealer();
      			if(ae.getSource()==btnSearch)
				searchRec();
      			if(ae.getSource()==btnEdit)
				update();
      			if(ae.getSource()==btnDelete)
        			deleteRec();
    		}
  	}

  	public static void main(String args[]){
    		new Dealer();
  	}
}




  











    





  


    

