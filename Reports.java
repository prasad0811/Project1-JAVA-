import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
class Reports extends JFrame{
	private JButton btnCustomer,btnDealer,btnProduct,btnPurchaseSale,btnExit,btnReorderList,today;

	public Reports(){
		btnCustomer = new JButton("Customer");
		btnDealer = new JButton("Dealer");
		btnProduct = new JButton("Product");
		btnPurchaseSale = new JButton("Purchase/Sale");
		btnReorderList = new JButton("Reorder List");
		btnExit = new JButton("Exit");
		Color blue3 = new Color(0,255,255);
		today = new JButton("Today Purchase/Sale");
                      btnExit.setBackground(blue3);
                btnCustomer.setFont(new Font("Arial Black", Font.BOLD,15));
		btnCustomer.setForeground(Color.BLACK);
                    btnCustomer.setBackground(blue3);
					//btnCustomer.setBorder(new LineBorder(Color.RED,4));
                btnDealer.setFont(new Font("Arial Black", Font.BOLD,15));
		btnDealer.setForeground(Color.BLACK);
                 btnDealer.setBackground(blue3);
				// btnDealer.setBorder(new LineBorder(Color.BLUE,4));
                btnProduct.setFont(new Font("Arial Black", Font.BOLD,15));
		btnProduct.setForeground(Color.BLACK);
                     btnProduct.setBackground(blue3);
                btnPurchaseSale.setFont(new Font("Arial Black", Font.BOLD,15));
		btnPurchaseSale.setForeground(Color.BLACK);
                 btnPurchaseSale.setBackground(blue3);
                btnReorderList .setFont(new Font("Arial Black", Font.BOLD,15));
		btnReorderList .setForeground(Color.BLACK);
                     btnReorderList .setBackground(blue3);
                btnExit.setFont(new Font("Arial Black", Font.BOLD,15));
		btnExit.setForeground(Color.BLACK);
              today.setFont(new Font("Arial Black", Font.BOLD,15));
		today.setForeground(Color.BLACK);
                 today.setBackground(blue3);
    		setTitle("Reports");
    		setSize(400,200);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    		setLocation(x, y);
		setLayout(new GridLayout(7,1));
    		add(btnCustomer);
    		add(btnDealer);
    		add(btnProduct);
    		add(btnPurchaseSale);
			add(today);
		add(btnReorderList);
    		add(btnExit);
    		setVisible(true);
                setResizable(false);
    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    		ButtonHandler bh = new ButtonHandler();
    		btnCustomer.addActionListener(bh);
    		btnDealer.addActionListener(bh);
    		btnProduct.addActionListener(bh);
    		btnPurchaseSale.addActionListener(bh);
			today.addActionListener(bh);
    		btnExit.addActionListener(bh);
		btnReorderList.addActionListener(bh);
	}

	class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(ae.getSource()==btnCustomer) new ViewAllCustomer();
			if(ae.getSource()==btnDealer) new ViewAllDealer();
			if(ae.getSource()==btnProduct) new ViewAllProduct();
			if(ae.getSource()==btnPurchaseSale) new PurchaseSale();
			if(ae.getSource()==btnExit) dispose();
			if(ae.getSource()==btnReorderList) new ViewReorderList();
			if (ae.getSource()==today) new PurchaseSale2();
		}
	}
	public static void main(String[] args) {
		new Reports();
	}
}		
	

