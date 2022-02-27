package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Intermediar extends JFrame{

	private JButton binsert;
	private JButton bshow;
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\vladi\\Pictures\\Saved Pictures\\istockphoto-1287504319-170667a.jpg");
	
	public Intermediar (Connection conn, int id, int opt) {
		
		super("Jurnal");
		this.setLayout(new FlowLayout());
		setSize(500,200);
		this.setResizable(false);

		this.setContentPane(new JPanel() {
		         @Override
		         public void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            g.drawImage(img, 0, 0, null);
		         }
		      });
		
		binsert = new JButton ("Adaugare date");
		bshow = new JButton ("Date inregistrate");
		
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.setPreferredSize(new Dimension(400,100));
		top.setOpaque(false);
		
		top.add(binsert);
		top.add(bshow);
		top.setBorder(new EmptyBorder(40,60,0,0));
		add(top);
		
		binsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				if(opt == 1) {
					Exercitii_interface app = new Exercitii_interface(conn, id);
					app.setVisible(true);
					app.setLocationRelativeTo(null);
				}
				if(opt == 2) {
					Somn_Interface app = new Somn_Interface(conn, id);
					app.setVisible(true);
					app.setLocationRelativeTo(null);
				}
				if(opt == 3) {
					Masa_interface app = new Masa_interface(conn, id);
					app.setVisible(true);
					app.setLocationRelativeTo(null);
				}
				if(opt == 4) {
					Apa_Interface app = new Apa_Interface(conn, id);
					app.setVisible(true);
					app.setLocationRelativeTo(null);
				}
				if(opt == 5) {
					Greutate_Utilizator_Interface app = new Greutate_Utilizator_Interface(conn, id);
					app.setVisible(true);
					app.setLocationRelativeTo(null);
				}
				
			}
			
		});
		
		bshow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Show_ex app = new Show_ex(conn, id, opt);
				app.setVisible(true);
				app.setLocationRelativeTo(null);
			}
			
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(EXIT_ON_CLOSE);
			}
		});
	}
	
}
