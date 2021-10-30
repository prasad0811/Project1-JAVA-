import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;

public class ViewBill extends JFrame{
	private JEditorPane txtMain;
	private String txt="";

	public ViewBill(int bno){
		txtMain = new JEditorPane();
		txtMain.setContentType("text/html");
		txtMain.setEditable(false);

		try{
			String s="";
			BufferedReader br = new BufferedReader(
						new FileReader("bills/"+bno+".html"));
			while((s=br.readLine())!=null)
				txt+=s;
			br.close();

		}catch(Exception e){}

		setTitle("View Bill");
		setSize(700,500);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    		setLocation(x, y);
          
		add(new JScrollPane(txtMain), "Center");	
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		txtMain.setText(txt);
	}
}
