import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class Purchase extends JFrame {
	private JLabel lblInvoiceNo,lblInvoiceDate,lblDealer,lblAddress,lblPhone,
			lblCategory,lblProduct,lblRate,lblQty,lblAmount,lblTotal;

	private JTable tabProducts;
	private DefaultTableModel dtmProducts;
	private JScrollPane spProducts;
	private JTextField txtInvoiceNo,txtInvoiceDate,txtRate,txtQty,txtAmount,txtPhone;
	private JComboBox cmbCategory,cmbProduct,cmbDealer;
	private JTextArea txtAddress;

	private SimpleDateFormat sdf;

	private JButton btnAdd,btnDelete,btnEdit,btnClose,btnSave,btnPrint;

	private JTextField txtTotal;

	private Connection con;
  	private Statement s;
  	private PreparedStatement ps;
  	private ResultSet rs;

	private int invno;
	private float total;

	public Purchase(){
		lblInvoiceNo = new JLabel("Invoice No:");
		lblInvoiceDate = new JLabel("Invoice Date:");
		lblDealer = new JLabel("Dealer:");
		lblAddress = new JLabel("Address:");
		lblPhone = new JLabel("Phone:");

		lblCategory = new JLabel("Category");
		lblProduct = new JLabel("Product");
		lblRate = new JLabel("Rate");
		lblQty = new JLabel("Qty");
		lblAmount = new JLabel("Amount");
		lblTotal = new JLabel("Total:");

		lblInvoiceNo.setBounds(12, 12, 80, 15);
		lblInvoiceDate.setBounds(261, 12, 100, 15);
		lblDealer.setBounds(12, 39, 95, 15);
		lblAddress.setBounds(12, 77, 70, 15);
		lblPhone.setBounds(12, 148, 70, 15);

		lblCategory.setBounds(12, 189, 70, 15);
		lblProduct.setBounds(139, 189, 70, 15);
		lblRate.setBounds(277, 189, 70, 15);
		lblQty.setBounds(393, 189, 70, 15);
		lblAmount.setBounds(517, 192, 70, 15);
		lblTotal.setBounds(446, 407, 70, 15);

		dtmProducts = new DefaultTableModel();
		dtmProducts.addColumn("Category");
		dtmProducts.addColumn("Product");
		dtmProducts.addColumn("Rate");
		dtmProducts.addColumn("Qty");
		dtmProducts.addColumn("Amt");

		tabProducts = new JTable(dtmProducts);
		spProducts = new JScrollPane(tabProducts);
		spProducts.setBounds(12, 241, 630, 161);

		txtInvoiceNo = new JTextField();
		txtInvoiceNo.setEditable(false);
		txtInvoiceNo.setBounds(129, 10, 114, 19);
		txtInvoiceNo.setColumns(10);
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		txtInvoiceDate = new JTextField(sdf.format(new java.util.Date()));
		txtInvoiceDate.setEditable(false);
		txtInvoiceDate.setBounds(390, 10, 114, 19);
		txtInvoiceDate.setColumns(10);

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

		cmbDealer = new JComboBox();
		cmbDealer.setBounds(129, 37, 114, 19);

		txtAddress = new JTextArea(4,40);
		txtAddress.setEditable(false);
		txtAddress.setBounds(129, 66, 219, 68);
		
		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		txtPhone.setBounds(129, 148, 114, 19);
		txtPhone.setColumns(10);

		setTitle("Invoice Details");
		setSize(850,550);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    		setLocation(x, y);
		setLayout(null);
		add(lblInvoiceNo);
		add(lblInvoiceDate);
		add(lblDealer);
		add(lblAddress);
		add(lblPhone);
		add(lblCategory);
		add(lblProduct);
		add(lblRate);
		add(lblQty);
		add(lblAmount);
		add(lblTotal);
		add(spProducts);
		add(txtInvoiceNo);
		add(txtInvoiceDate);
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
		add(cmbDealer);
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
			rs = s.executeQuery("select * from PurchaseHeader");
			rs.last();
			invno = rs.getRow()+1;
			txtInvoiceNo.setText(Integer.toString(invno));

			s = con.createStatement();
			rs = s.executeQuery("select * from dealer order by d_no");
			cmbDealer.addItem("---");
			while(rs.next()){
				cmbDealer.addItem(rs.getString(2));
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
				
					PreparedStatement ps = con.prepareStatement("select * from item where icategory=?");
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

		cmbDealer.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				try{
					String dealer = cmbDealer.getSelectedItem().toString();
				
					PreparedStatement ps = con.prepareStatement("select * from dealer where d_name=?");
					ps.setString(1,dealer);
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
						txtRate.requestFocus();
					}
		    		}
		    		catch(Exception e){}
			}
		});

		txtQty.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				float r = Float.parseFloat(txtRate.getText());
				int q = Integer.parseInt(txtQty.getText());
				float amt = r*q;
                                     if(amt<1)
                                 {
                                    JOptionPane.showMessageDialog(null,"Invalid Amount");
						return;
					}
				txtAmount.setText(Float.toString(amt));
				btnAdd.requestFocus();
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
				dtmProducts.addRow(v);
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
					if(cmbDealer.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null,"Please select dealer.");
						return;
					}

					String str="<table border=1>";

					String dn = cmbDealer.getSelectedItem().toString();
					String addr = txtAddress.getText();
					String phone = txtPhone.getText();
					int did=getDealer(dn);
					ps = con.prepareStatement("insert into PurchaseHeader values(?,?,?,?)");
					ps.setInt(1,invno);

					java.util.Date d = sdf.parse(txtInvoiceDate.getText());
					java.sql.Date newinvdate = new java.sql.Date(d.getTime());
					ps.setDate(2,newinvdate);
					ps.setFloat(3,total);
					ps.setInt(4,did);
					ps.executeUpdate();

					str+="<tr><td><b>Invoice No:</b>"+invno+"</td><td><b>Invoice Date:</b>"+txtInvoiceDate.getText()+"</td></tr>";
					str+="<tr><td colspan=3><b>Dealer:</b>"+dn+"</td></tr>";
					str+="<tr><td colspan=3><b>Address:</b>"+addr+"</td></tr>";
					str+="<tr><td colspan=3><b>Phone:</b>"+phone+"</td></tr>";
					str+="</table><table border=1><tr><th>Category</th><th>Product</th><th>Rate</th><th>Qty</th><th>Amount</th></tr>";

					int n = dtmProducts.getRowCount();
					
					for(int i=0;i<n;i++){
						ps = con.prepareStatement("insert into PurchaseDetails values(?,?,?,?)");
						int ino = getProduct(dtmProducts.getValueAt(i,1).toString());
						float price = Float.parseFloat(dtmProducts.getValueAt(i,2).toString());
						int qty = Integer.parseInt(dtmProducts.getValueAt(i,3).toString());

						ps.setInt(1,invno);
						ps.setInt(2,ino);
						ps.setInt(3,qty);
						ps.setFloat(4,price);
						ps.executeUpdate();

						ps = con.prepareStatement("update item set iqty = iqty + ?, iprice=? where ino=?");
						ps.setInt(1,qty);
						ps.setFloat(2, price);
						ps.setInt(3,ino);
						ps.executeUpdate();

						str+="<tr><td>"+dtmProducts.getValueAt(i,0)+"</td>";
						str+="<td>"+dtmProducts.getValueAt(i,1)+"</td>";
						str+="<td>"+dtmProducts.getValueAt(i,2)+"</td>";
						str+="<td>"+dtmProducts.getValueAt(i,3)+"</td>";
						str+="<td>"+dtmProducts.getValueAt(i,4)+"</td></tr>";
					}

					str+="<tr><td colspan=5 align=right><b>Total:</b>Rs."+total+"/-</td></tr></table>";
					str+="<br><input type='button' value='Print' onclick='window.print()'>";					

					FileWriter fw = new FileWriter("invoices/"+invno+".html");
					fw.write(str);
					fw.close();

					clearInvoice();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});

		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int invno = Integer.parseInt(JOptionPane.showInputDialog("Enter Invoice no:"));
					new ViewPurchase(invno);
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
					dtmProducts.getValueAt(i,4).toString());
				total-=amt;
				txtTotal.setText(Float.toString(total));
				dtmProducts.removeRow(i);
				clearRow();
			}
		});

		tabProducts.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				int i = tabProducts.getSelectedRow();
				cmbCategory.setSelectedItem(dtmProducts.getValueAt(i,0).toString());
				cmbProduct.setSelectedItem(dtmProducts.getValueAt(i,1).toString());
				txtRate.setText(dtmProducts.getValueAt(i,2).toString());
				txtQty.setText(dtmProducts.getValueAt(i,3).toString());
				txtAmount.setText(dtmProducts.getValueAt(i,4).toString());
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = tabProducts.getSelectedRow();
				float amt = Float.parseFloat(dtmProducts.getValueAt(i,4).toString());
				total-=amt;
				total += Float.parseFloat(txtAmount.getText());
				dtmProducts.setValueAt(txtQty.getText(),i,3);
				dtmProducts.setValueAt(txtAmount.getText(),i,4);
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

	public int getDealer(String dn) throws Exception{
		PreparedStatement ps = con.prepareStatement("select d_no from Dealer where d_name=?");
		ps.setString(1,dn);

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

	public void clearInvoice(){
		invno++;
		txtInvoiceNo.setText(Integer.toString(invno));
		cmbDealer.setSelectedIndex(0);
		txtAddress.setText("");	
		txtPhone.setText("");
		total = 0;
		txtTotal.setText("0.0");
		int n = dtmProducts.getRowCount();
		for(int i=0;i<n;i++) dtmProducts.removeRow(0);
		cmbDealer.requestFocus();		
	}

	public static void main(String args[]){
		new Purchase();
	}
}
