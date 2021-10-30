import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
public class Product extends JFrame{
	private JLabel lblProdID,lblName,lblQty,lblPrice,lblCategory;
  	private JTextField txtProdID,txtName,txtQty,txtPrice;
 	private JButton btnSave,btnClear,btnDelete,btnEdit,btnSearch,
				btnViewOne,btnViewAll,btnExit;
  	private JPanel panCenter,panSouth;
	private JComboBox cmbCategory;

  	private Connection con;
  	private Statement s;
  	private PreparedStatement ps;
  	private ResultSet rs;

  	private int pid;

  	public Product(){
		lblProdID = new JLabel("Product ID:");    		
		lblName = new JLabel("Name:");
    		lblQty = new JLabel("Qty:");
    		lblPrice = new JLabel("Price:");
   		lblCategory = new JLabel("Category:");
         lblProdID.setFont(new Font("Arial Black", Font.BOLD,13));
				   lblName.setFont(new Font("Arial Black", Font.BOLD,13));
				   lblQty.setFont(new Font("Arial Black", Font.BOLD,13));
				   lblPrice.setFont(new Font("Arial Black", Font.BOLD,13));
				    lblCategory.setFont(new Font("Arial Black", Font.BOLD,13));
		txtProdID = new JTextField();
    		txtName = new JTextField();
    		txtQty = new JTextField();
    		txtPrice = new JTextField();
		cmbCategory = new JComboBox();
		//txtProdID.setBackground(Color.yellow);
			//txtName.setBackground(Color.yellow);
			//txtQty.setBackground(Color.yellow);
			//txtPrice.setBackground(Color.yellow);
			      // cmbCategory.setBackground(Color.yellow);
			 txtProdID.setBorder(new LineBorder(Color.black,1));
			txtName.setBorder(new LineBorder(Color.black,1));
               txtQty.setBorder(new LineBorder(Color.black,1));
            txtPrice.setBorder(new LineBorder(Color.black,1));
			       cmbCategory.setBorder(new LineBorder(Color.black,1));
                 txtProdID.setFont(new Font("Verdana", Font.BOLD,13));
                txtName.setFont(new Font("Verdana", Font.BOLD,13));
                txtQty.setFont(new Font("Verdana", Font.BOLD,13));
                txtPrice.setFont(new Font("Verdana", Font.BOLD,13));
                cmbCategory.setFont(new Font("Verdana", Font.BOLD,13));
		txtProdID.setEditable(false);

    		btnSave = new JButton("Save");
    		btnClear = new JButton("Clear");
    		btnSearch = new JButton("Search");
    		btnDelete = new JButton("Delete");
    		btnEdit = new JButton("Edit");
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
			// panCenter.setBackground(Color.white);
    		panCenter.setLayout(new GridLayout(5,2));
		panCenter.add(lblProdID);
		panCenter.add(txtProdID);
    		panCenter.add(lblName);
    		panCenter.add(txtName);
    		panCenter.add(lblQty);
    		panCenter.add(txtQty);
    		panCenter.add(lblPrice);
    		panCenter.add(txtPrice);
		panCenter.add(lblCategory);
		panCenter.add(cmbCategory);

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

    		setTitle("Product Information");
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
			rs = s.executeQuery("select * from item");
			rs.last();
			pid = rs.getRow()+1;

			txtProdID.setText(Integer.toString(pid));

			Statement s1 = con.createStatement();
			ResultSet rs1 = s1.executeQuery("select distinct icategory from item");
			cmbCategory.addItem("---");
			while(rs1.next()){
				cmbCategory.addItem(rs1.getString(1));
			}
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,e);
      			dispose();
    		}
  	}

  	public void saveRec(){
                   		try{
			int id = Integer.parseInt(txtProdID.getText());
      			String pn = txtName.getText();
      			int qty = Integer.parseInt(txtQty.getText());
      			float price = Float.parseFloat(txtPrice.getText());
			String cat = cmbCategory.getSelectedItem().toString();
int c1;
 c1=pn.length();
              if(c1>0 && price>0 && qty>-1)
          {
			ps = con.prepareStatement("insert into item values(?,?,?,?,?)");
			ps.setInt(1,id);
      			ps.setString(2,pn);
      			ps.setInt(3,qty);
      			ps.setFloat(4,price);
			ps.setString(5,cat);
      			ps.executeUpdate();
      			JOptionPane.showMessageDialog(null,"Record saved successfully.");
			pid++;
			clearRec();
                    txtName.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
               txtQty.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            txtPrice.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
               // cmbCategory.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder(".border"));
           
       }
        else
  {
 
txtName.setBorder(new LineBorder(Color.RED,1));
 txtQty.setBorder(new LineBorder(Color.RED,1));
txtPrice.setBorder(new LineBorder(Color.RED,1));
JOptionPane.showMessageDialog(null,"Record not Save."); 
//cmbCategory.setBorder(new LineBorder(Color.RED,1));
}
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,"Record not save please Give Correct Input ");
    		}
  	}

  	public void clearRec(){
		txtProdID.setText(Integer.toString(pid));
    		txtName.setText("");
    		txtQty.setText("");
    		txtPrice.setText("");
		cmbCategory.setSelectedIndex(0);
    		txtName.requestFocus();
  	}

  	public void searchRec(){
    		try{
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Product No:"));
      			ps = con.prepareStatement("select * from item where ino=?");
      			ps.setInt(1,id);
      			rs = ps.executeQuery();
      			if(rs.next()){
				txtProdID.setText(rs.getString(1));
        			txtName.setText(rs.getString(2));
        			txtQty.setText(rs.getString(3));
				txtPrice.setText(rs.getString(4));
				cmbCategory.setSelectedItem(rs.getString(5));
      			}
      			else{
				JOptionPane.showMessageDialog(null,"Product "+id+" not found.");
      			}    
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,e);
    		}
  	}

  	public void deleteRec(){
    		try{
      			int pno = Integer.parseInt(JOptionPane.showInputDialog("Enter Product No:"));
      			ps = con.prepareStatement("delete from item where ino=?");
      			ps.setInt(1,pno);
      			ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Record"+pno+" deleted successfully.");
			clearRec();
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,"Record Not Deleted");
    		}
  	}

  	public void editRec(){
    		try{
			int id = Integer.parseInt(txtProdID.getText());
      			String pn = txtName.getText();
      			int qty = Integer.parseInt(txtQty.getText());
      			float price = Float.parseFloat(txtPrice.getText());
			String cat = cmbCategory.getSelectedItem().toString();

      			ps = con.prepareStatement("update item set ino=?, iname=?, iqty=?, iprice=?, icategory=? where ino=?");
      			ps.setInt(1,id);
                        ps.setString(2,pn);
      			ps.setInt(3,qty);
      			ps.setFloat(4,price);
      			ps.setString(5,cat);
			ps.setInt(6,id);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Record "+id+" Updated");
			clearRec();
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,"Record Not Updated");
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
        			new ViewOneProduct();
      			if(ae.getSource()==btnViewAll)
				new ViewAllProduct();
      			if(ae.getSource()==btnSearch)
				searchRec();
      			if(ae.getSource()==btnEdit)
				editRec();
      			if(ae.getSource()==btnDelete)
        			deleteRec();
    		}
  	}

  	public static void main(String args[]){
    		new Product();
  	}
}




  