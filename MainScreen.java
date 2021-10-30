

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.border.*;
class MainPanel extends JPanel{
	public void paintComponent(Graphics g){
		try{
			Image img = ImageIO.read(new java.io.File("images/edit3.jpg"));
          		g.drawImage(img,0,0,getWidth(),getHeight(),null);
		}
		catch(Exception e){}
	}
}


class MainScreen extends JFrame{
	//private JPanel qq;
	private JButton btnCustomer,btnDealer,btnProduct,btnPurchase,btnBill,btnReports,btnExit;

	public MainScreen(){
		btnCustomer = new JButton("Customer");
		btnDealer = new JButton("Dealer");
		btnProduct = new JButton("Product");
		btnPurchase = new JButton("Purchase");
		btnBill = new JButton("Bill");
		btnReports = new JButton("Reports");
		btnExit = new JButton("Exit");
		btnCustomer.setForeground(Color.RED);
		btnCustomer.setBackground(Color.YELLOW);
		btnCustomer.setBorder(new LineBorder(Color.GREEN,7));
		btnCustomer.setToolTipText("click here to view customer details");
		btnCustomer.setFont(new Font("Verdana", Font.BOLD,20));
		btnDealer.setForeground(Color.RED);
		btnDealer.setBackground(Color.YELLOW);
		btnDealer.setBorder(new LineBorder(Color.GREEN,7));
		btnDealer.setToolTipText("click here to view Dealer details");
		btnDealer.setFont(new Font("Verdana", Font.BOLD,20));
		btnPurchase.setForeground(Color.RED);
		btnPurchase.setBackground(Color.YELLOW);
		btnPurchase.setBorder(new LineBorder(Color.GREEN,7));
		btnPurchase.setToolTipText("click here to view purchase details");
		btnPurchase.setFont(new Font("Verdana", Font.BOLD,20));
		btnProduct.setForeground(Color.RED);
		btnProduct.setBackground(Color.YELLOW);
		btnProduct.setBorder(new LineBorder(Color.GREEN,7));
		btnProduct.setToolTipText("click here to view product details");
		btnProduct.setFont(new Font("Verdana", Font.BOLD,20));
		btnBill.setForeground(Color.RED);
		btnBill.setBackground(Color.YELLOW);
		btnBill.setBorder(new LineBorder(Color.GREEN,7));
		btnBill.setToolTipText("click here to view Bill details");
		btnBill.setFont(new Font("Verdana", Font.BOLD,20));
		btnReports.setForeground(Color.RED);
		btnReports.setBackground(Color.YELLOW);
		btnReports.setBorder(new LineBorder(Color.GREEN,7));
		btnReports.setToolTipText("click here to check Reports");
        btnReports.setFont(new Font("Verdana", Font.BOLD,20));		
		btnExit.setForeground(Color.RED);
		btnExit.setBackground(Color.YELLOW);
		btnExit.setBorder(new LineBorder(Color.GREEN,7));
		btnExit.setToolTipText("click here to Exit");
        btnExit.setFont(new Font("Verdana", Font.BOLD,20));
	  //btnCustomer.setMargin(new Insets(5,5,5,5));
		 JPanel qq = new JPanel();
		 qq.setBackground(Color.black);
		 //setBounds(50,50,0,0);
    GridBagLayout gg= new GridBagLayout();
	GridBagConstraints gbc =new GridBagConstraints();
       qq.setLayout(gg);
	   //GridBagLayout ll = new GridBagLayout();
	   //this.setLayout(ll);
	   gbc.fill=GridBagConstraints.HORIZONTAL;
	   gbc.gridx=20;
	   gbc.gridy=20;
	   gbc.insets=new Insets(20,80,20,80);
	   qq.add(btnExit,gbc);
	   gbc.fill=GridBagConstraints.HORIZONTAL;
	   gbc.gridx=20;
	   gbc.gridy=18;
	   gbc.insets=new Insets(20,80,20,80);
	   //gbc.ipady=20;
	   qq.add(btnReports,gbc);
	   gbc.fill=GridBagConstraints.HORIZONTAL;
	   gbc.gridx=20;
	   gbc.gridy=6;
	   gbc.insets=new Insets(20,80,20,80);
	   qq.add(btnBill,gbc);
	   gbc.fill=GridBagConstraints.HORIZONTAL;
	   gbc.gridx=20;
	   gbc.gridy=5;
	   gbc.insets=new Insets(20,80,20,80);
	   //gbc.ipady=40;
	   qq.add(btnPurchase,gbc);
	   gbc.fill=GridBagConstraints.HORIZONTAL;
	   gbc.gridx=20;
	   gbc.gridy=4;
	   gbc.insets=new Insets(20,80,20,80);
	   qq.add(btnProduct,gbc);
	   gbc.fill=GridBagConstraints.HORIZONTAL;
	  gbc.gridx=20;
	   gbc.gridy=3;
	   gbc.insets=new Insets(20,80,20,80);
	   qq.add(btnDealer,gbc);
	   gbc.fill=GridBagConstraints.HORIZONTAL;
	   gbc.gridx=20;
	   gbc.gridy=2;
	   gbc.insets=new Insets(20,80,20,80);
	   //gbc.weighty=1.0;
	   //gbc.gridwidth=0;
	   qq.add(btnCustomer,gbc);
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	 /*setLayout(new GridLayout(7,1,5,5));
		add(btnCustomer);
		add(btnDealer);
		add(btnProduct);
		add(btnPurchase);
		add(btnBill);
		add(btnReports);
		add(btnExit);*/

		btnCustomer.setMnemonic('C');
		btnDealer.setMnemonic('C');
		btnProduct.setMnemonic('P');
		btnPurchase.setMnemonic('r');
		btnBill.setMnemonic('B');
		btnReports.setMnemonic('o');
		btnExit.setMnemonic('x');
      setSize(100,100);
	    	setTitle("Toy Billing System v1.1");
		Rectangle bounds = getMaximizedBounds();
		setMaximizedBounds(bounds);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		add(new MainPanel(),"Center");
		add(qq,"West");
    		setVisible(true);
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
           double width = screenSize.getWidth();
    		double height = screenSize.getHeight();
             
    		setLocation((int)((width-getWidth())/2),(int)((height-getHeight())/2));
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ButtonHandler bh = new ButtonHandler();
		btnCustomer.addActionListener(bh);
		btnDealer.addActionListener(bh);
		btnProduct.addActionListener(bh);
		btnPurchase.addActionListener(bh);
		btnBill.addActionListener(bh);
		btnReports.addActionListener(bh);
		btnExit.addActionListener(bh);
	}

	class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(ae.getSource()==btnCustomer) new Customer();
			if(ae.getSource()==btnDealer) new Dealer();
			if(ae.getSource()==btnProduct) new Product();
			if(ae.getSource()==btnPurchase) new Purchase();
			if(ae.getSource()==btnBill) new Bill();
			if(ae.getSource()==btnReports) new Reports();
			if(ae.getSource()==btnExit) System.exit(0);
		}
	}
	public static void main(String[] args) {
		new MainScreen();
	}
}