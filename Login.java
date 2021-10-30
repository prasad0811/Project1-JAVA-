import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;
public class Login extends JFrame {
	private JPasswordField txtPass;
	private JTextField cmbUserName;
	private JLabel lblUserName,lblPassword;
	private JButton btnOK,btnCancel;

	private Connection con;
  	private Statement s;
  	private PreparedStatement ps;
  	private ResultSet rs;

	public static void main(String[] args) {
		new Login();
	}

	public Login() {
		lblUserName = new JLabel("User Name:");
		lblPassword = new JLabel("Password:");
                  lblUserName.setFont(new Font("Arial Black", Font.BOLD,13));
                  lblPassword.setFont(new Font("Arial Black", Font.BOLD,13));
		txtPass = new JPasswordField();
		cmbUserName = new JTextField(10);
                        cmbUserName.setFont(new Font("Verdana", Font.BOLD,13));
                    txtPass.setFont(new Font("Verdana", Font.BOLD,13));
		btnOK = new JButton("OK");
		btnCancel = new JButton("Cancel");
                btnOK.setFont(new Font("Arial Black", Font.BOLD,16));
		btnOK.setForeground(Color.WHITE);
		btnOK.setBackground(Color.GREEN);
               btnCancel.setFont(new Font("Arial Black", Font.BOLD,16));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.RED);
		lblUserName.setDisplayedMnemonic('N');
		lblPassword.setDisplayedMnemonic('P');

		lblUserName.setLabelFor(cmbUserName);
		lblPassword.setLabelFor(txtPass);

		btnOK.setMnemonic('O');
		btnCancel.setMnemonic('C');

		setTitle("Login");
		setLayout(new GridLayout(3, 2));
		setSize(350,150);
		add(lblUserName);
		add(cmbUserName);
		add(lblPassword);
		add(txtPass);
		add(btnOK);
		add(btnCancel);		
    		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    		double width = screenSize.getWidth();
    		double height = screenSize.getHeight();
                setResizable(false);
                 getContentPane().setBackground(Color.YELLOW);		
    		setLocation((int)((width-getWidth())/2),(int)((height-getHeight())/2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ps = con.prepareStatement("select * from admin where admin_id=? and admin_pwd=?");
					String uid = cmbUserName.getText();
					String pass = txtPass.getText();
					ps.setString(1,uid);
					ps.setString(2,pass);
					rs = ps.executeQuery();
					if(rs.next()){
						JOptionPane.showMessageDialog(null,"Login Successful.");
						new MainScreen();
						dispose();
					}
					else{
                                                          cmbUserName.setBorder(new LineBorder(Color.RED,1));
                                                                     txtPass.setBorder(new LineBorder(Color.RED,1));
                                                  JOptionPane.showMessageDialog(null,"Login Failed.");
						cmbUserName.requestFocus();
                                                           
					}
		    		}
		    		catch(Exception e){
		      			JOptionPane.showMessageDialog(null,e);
		      			dispose();
		    		}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		try{
      			Class.forName("org.postgresql.Driver");
      			con = DriverManager.getConnection("jdbc:postgresql:demo","postgres","pgj123");
			s = con.createStatement();
			
    		}
    		catch(Exception e){
      			JOptionPane.showMessageDialog(null,e);
      			dispose();
    		}
	}
}
