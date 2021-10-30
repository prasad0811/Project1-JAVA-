import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class Bill extends JFrame {
	private JLabel lblBillNo,lblBillDate,lblCustomer,lblAddress,lblPhone,
			lblCategory,lblProduct,lblRate,lblQty,lblAmount,lblTotal;

	private JTable tabProducts;
	private DefaultTableModel dtmBill;
	private JScrollPane spBill;
	private JTextField txtBillNo,txtBillDate,txtRate,txtQty,txtAmount,txtPhone;
	private JComboBox cmbCategory,cmbProduct,cmbCustomer;
	private JTextArea txtAddress;

	private SimpleDateFormat sdf;

	private JButton btnAdd,btnDelete,btnEdit,btnClose,btnSave,btnPrint;

	private JTextField txtTotal;

	private Connection con;
  	private Statement s;
  	private PreparedStatement ps;
  	private ResultSet rs;
      public  int amt;
	public int bno;
	private float total;

	public Bill(){
		lblBillNo = new JLabel("Bill No:");
		lblBillDate = new JLabel("Bill Date:");
		lblCustomer = new JLabel("Customer:");
		lblAddress = new JLabel("Address:");
		lblPhone = new JLabel("Phone:");

		lblCategory = new JLabel("Category");
		lblProduct = new JLabel("Product");
		lblRate = new JLabel("Rate");
		lblQty = new JLabel("Qty");
		lblAmount = new JLabel("Amount");
		lblTotal = new JLabel("Total:");


		lblBillNo.setBounds(12, 12, 70, 15);
		lblBillDate.setBounds(261, 12, 70, 15);
		lblCustomer.setBounds(12, 39, 95, 15);
		lblAddress.setBounds(12, 77, 70, 15);
		lblPhone.setBounds(12, 148, 70, 15);

		lblCategory.setBounds(12, 189, 70, 15);
		lblProduct.setBounds(139, 189, 70, 15);
		lblRate.setBounds(277, 189, 70, 15);
		lblQty.setBounds(393, 189, 70, 15);
		lblAmount.setBounds(517, 192, 70, 15);
		lblTotal.setBounds(446, 407, 70, 15);

		dtmBill = new DefaultTableModel();
		dtmBill.addColumn("Category");
		dtmBill.addColumn("Product");
		dtmBill.addColumn("Rate");
		dtmBill.addColumn("Qty");
		dtmBill.addColumn("Amt");

		tabProducts = new JTable(dtmBill);
		spBill = new JScrollPane(tabProducts);
		spBill.setBounds(12, 241, 630, 161);

		txtBillNo = new JTextField();
		txtBillNo.setEditable(false);
		txtBillNo.setBounds(129, 10, 114, 19);
		txtBillNo.setColumns(10);
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		txtBillDate = new JTextField(sdf.format(new java.util.Date()));
		txtBillDate.setEditable(false);
		txtBillDate.setBounds(349, 10, 114, 19);
		txtBillDate.setColumns(10);

		btnAdd = new JButton("Add");
		btnDelete = new JButton("Delete");
		btnEdit = new JButton("Edit");
		btnClose = new JButton("Close");
		btnSave = new JButton("Save");
		btnPrint = new JButton("Print");

		btnAdd.setMnemonic('A');
		btnAdd.setBounds(654, 236, 117, 25);
		btnDelete.setMnemonic('D');
		btnDelete.setBounds(654, 273, 117, 25);
		btnEdit.setMnemonic('E');
		btnEdit.setBounds(654, 314, 117, 25);
		btnClose.setMnemonic('C');
		btnClose.setBounds(654, 351, 117, 25);
		btnSave.setMnemonic('S');
		btnSave.setBounds(217, 449, 117, 25);
		btnPrint.setMnemonic('P');
		btnPrint.setBounds(346, 449, 117, 25);

		txtTotal = new JTextField("0.0");
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(528, 405, 114, 19);

		txtRate = new JTextField();
		txtRate.setEditable(false);
		txtRate.setBounds(265, 208, 122, 19);
		txtRate.setColumns(10);
		
		txtQty = new JTextField();
		txtQty.setBounds(393, 208, 123, 19);
		txtQty.setColumns(10);

		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setBounds(517, 208, 114, 19);
		txtAmount.setColumns(10);

		cmbCategory = new JComboBox();
		cmbCategory.setBounds(12, 208, 126, 19);

		cmbProduct = new JComboBox();
		cmbProduct.setBounds(139, 208, 126, 19);

		cmbCustomer = new JComboBox();
		cmbCustomer.setBounds(129, 37, 114, 19);

		txtAddress = new JTextArea(4,40);
		txtAddress.setEditable(false);
		txtAddress.setBounds(129, 66, 219, 68);
		
		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		txtPhone.setBounds(129, 148, 114, 19);
		txtPhone.setColumns(10);

		setTitle("Bill Details");
		setSize(850,550);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    		setLocation(x, y);
		setLayout(null);
		add(lblBillNo);
		add(lblBillDate);
		add(lblCustomer);
		add(lblAddress);
		add(lblPhone);
		add(lblCategory);
		add(lblProduct);
		add(lblRate);
		add(lblQty);
		add(lblAmount);
		add(lblTotal);
		add(spBill);
		add(txtBillNo);
		add(txtBillDate);
		add(btnAdd);
		add(btnDelete);
		add(btnEdit);
		add(btnClose);
		add(btnSave);
		add(btnPrint);
		add(txtTotal);
		add(txtRate);
		add(txtQty);
		add(txtAmount);
		add(cmbCategory);
		add(cmbProduct);
		add(cmbCustomer);
		add(txtAddress);
		add(txtPhone);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);

		try{
      			Class.forName("org.postgresql.Driver");
      			con = DriverManager.getConnection("jdbc:postgresql:demo","postgres","pgj123");
			s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery("select * from BillHeader");
			rs.last();
			bno = rs.getRow()+1;
			txtBillNo.setText(Integer.toString(bno));

			s = con.createStatement();
			rs = s.executeQuery("select * from customer order by c_no");
			cmbCustomer.addItem("---");
			while(rs.next()){
				cmbCustomer.addItem(rs.getString(2));
			}

			s = con.createStatement();
			rs = s.executeQuery("select distinct icategory from item");
			cmbCategory.addItem("---");
			while(rs.next()){
				cmbCategory.addItem(rs.getString(1));
			}
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,e);
      			dispose();
    		}

		cmbCategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				try{
					String cat = cmbCategory.getSelectedItem().toString();
				
					PreparedStatement ps = con.prepareStatement("select * from item where icategory=? and iqty>0");
					ps.setString(1,cat);
					ResultSet rs = ps.executeQuery();

					cmbProduct.removeAllItems();
					cmbProduct.addItem("---");		
					while(rs.next()){
						cmbProduct.addItem(rs.getString(2));	
					}
		    		}
		    		catch(Exception e){
		      			JOptionPane.showMessageDialog(null,e);
		      			dispose();
		    		}
			}
		});

		cmbCustomer.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				try{
					String cust = cmbCustomer.getSelectedItem().toString();
				
					PreparedStatement ps = con.prepareStatement("select * from customer where c_name=?");
					ps.setString(1,cust);
					ResultSet rs = ps.executeQuery();

					if(rs.next()){
						txtAddress.setText(rs.getString(4));
						txtPhone.setText(rs.getString(3));
					}
		    		}
		    		catch(Exception e){}
			}
		});

		cmbProduct.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				try{
					String iname = cmbProduct.getSelectedItem().toString();
				
					PreparedStatement ps = con.prepareStatement("select iprice from item where iname=?");
					ps.setString(1,iname);
					ResultSet rs = ps.executeQuery();

					if(rs.next()){
						txtRate.setText(rs.getString(1));
						txtQty.requestFocus();
					}
		    		}
		    		catch(Exception e){}
			}
		});

		txtQty.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int r = Integer.parseInt(txtRate.getText());
				int q = Integer.parseInt(txtQty.getText());
			      amt = r*q;
                                         if(amt<1)
                                 {
                                    JOptionPane.showMessageDialog(null,"Invalid Amount");
						return;
					}
                          else
                  {

				txtAmount.setText(Integer.toString(amt));
				btnAdd.requestFocus();
			}
                   }
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector v = new Vector();
				v.add(cmbCategory.getSelectedItem());
				v.add(cmbProduct.getSelectedItem());
				v.add(txtRate.getText());
				v.add(txtQty.getText());
				v.add(txtAmount.getText());
				dtmBill.addRow(v);
				total+=Float.parseFloat(txtAmount.getText());
				txtTotal.setText(Float.toString(total));
				cmbCategory.setSelectedIndex(0);
				cmbProduct.removeAllItems();
				txtRate.setText("");
				txtQty.setText("");
				txtAmount.setText("");
				cmbCategory.requestFocus();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(cmbCustomer.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null,"Please select customer.");
						return;
					}
                                 
					String str="<table border=1>";

					String cn = cmbCustomer.getSelectedItem().toString();
					String addr = txtAddress.getText();
					String phone = txtPhone.getText();
					int cid=getCustomer(cn);
					ps = con.prepareStatement("insert into BillHeader values(?,?,?,?)");
					ps.setInt(1,bno);

					java.util.Date d = sdf.parse(txtBillDate.getText());
					java.sql.Date newbdate = new java.sql.Date(d.getTime());
					ps.setDate(2,newbdate);
					ps.setFloat(3,total);
					ps.setInt(4,cid);
					ps.executeUpdate();
                       
					str+="<tr><td><b>Bill No:</b>"+bno+"</td><td><b>Bill Date:</b>"+txtBillDate.getText()+"</td></tr>";
					str+="<tr><td colspan=3><b>Customer:</b>"+cn+"</td></tr>";
					str+="<tr><td colspan=3><b>Address:</b>"+addr+"</td></tr>";
					str+="<tr><td colspan=3><b>Phone:</b>"+phone+"</td></tr>";
					str+="</table><table border=1><tr><th>Category</th><th>Product</th><th>Rate</th><th>Qty</th><th>Amount</th></tr>";
                           

					int n = dtmBill.getRowCount();
					
					for(int i=0;i<n;i++){
						ps = con.prepareStatement("insert into BillDetails values(?,?,?)");
						int ino = getProduct(dtmBill.getValueAt(i,1).toString());
						int qty = Integer.parseInt(dtmBill.getValueAt(i,3).toString());

						ps.setInt(1,bno);
						
						ps.setInt(2,ino);
						ps.setInt(3,qty);
						ps.executeUpdate();

						ps = con.prepareStatement("update item set iqty = iqty - ? where ino=?");
						ps.setInt(1,qty);
						ps.setInt(2,ino);
						ps.executeUpdate();

						str+="<tr><td>"+dtmBill.getValueAt(i,0)+"</td>";
						str+="<td>"+dtmBill.getValueAt(i,1)+"</td>";
						str+="<td>"+dtmBill.getValueAt(i,2)+"</td>";
						str+="<td>"+dtmBill.getValueAt(i,3)+"</td>";
						str+="<td>"+dtmBill.getValueAt(i,4)+"</td></tr>";
					}

					str+="<tr><td colspan=5 align=right><b>Total:</b>Rs."+total+"/-</td></tr></table>";
					str+="<h3> Thank You </h3>"; 
					str+="<h3> Visit Again</h3>";
					str+="<br><input type='button' value='Print' onclick='window.print()'>";					

					FileWriter fw = new FileWriter("bills/"+bno+".html");
					fw.write(str);
					fw.close();

					clearBill();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});

		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int bno = Integer.parseInt(JOptionPane.showInputDialog("Enter bill no:"));
					new ViewBill(bno);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=tabProducts.getSelectedRow();
				float amt = Float.parseFloat(
					dtmBill.getValueAt(i,4).toString());
				total-=amt;
				txtTotal.setText(Float.toString(total));
				dtmBill.removeRow(i);
				clearRow();
			}
		});

		tabProducts.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				int i = tabProducts.getSelectedRow();
				cmbCategory.setSelectedItem(dtmBill.getValueAt(i,0).toString());
				cmbProduct.setSelectedItem(dtmBill.getValueAt(i,1).toString());
				txtRate.setText(dtmBill.getValueAt(i,2).toString());
				txtQty.setText(dtmBill.getValueAt(i,3).toString());
				txtAmount.setText(dtmBill.getValueAt(i,4).toString());
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = tabProducts.getSelectedRow();
				float amt = Float.parseFloat(dtmBill.getValueAt(i,4).toString());
				total-=amt;
				total += Float.parseFloat(txtAmount.getText());
				dtmBill.setValueAt(txtQty.getText(),i,3);
				dtmBill.setValueAt(txtAmount.getText(),i,4);
				clearRow();
			}
		});

	}

	public void clearRow(){
		cmbCategory.setSelectedIndex(0);
		cmbProduct.setSelectedIndex(0);
		txtRate.setText("");
		txtQty.setText("");
		txtAmount.setText("");
		cmbCategory.requestFocus();
	}

	public int getCustomer(String cn) throws Exception{
		PreparedStatement ps = con.prepareStatement("select c_no from Customer where c_name=?");
		ps.setString(1,cn);

		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	public int getProduct(String in) throws Exception{
		PreparedStatement ps = con.prepareStatement("select ino from item where iname=?");
		ps.setString(1,in);

		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	public void clearBill(){
		bno++;
		txtBillNo.setText(Integer.toString(bno));
		cmbCustomer.setSelectedIndex(0);
		txtAddress.setText("");	
		txtPhone.setText("");
		total = 0;
		txtTotal.setText("0.0");
		int n = dtmBill.getRowCount();
		for(int i=0;i<n;i++) dtmBill.removeRow(0);
		cmbCustomer.requestFocus();		
	}

	public static void main(String args[]){
		new Bill();
	}
}
