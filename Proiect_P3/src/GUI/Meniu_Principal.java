package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Meniu_Principal extends JFrame{
	
	private JButton bexercitii;
	private JButton bsomn;
	private JButton bmasa;
	private JButton bapa;
	private JButton bgreutate;
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\vladi\\Pictures\\Saved Pictures\\istockphoto-1287504319-170667a.jpg");
	
	public Meniu_Principal(Connection conn, int id) {
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
		
		bexercitii = new JButton("Exercitiu");
		bsomn = new JButton("Somn");
		bmasa = new JButton("Masa");
		bapa = new JButton("Apa");
		bgreutate = new JButton("Greutate utilizator");
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.setPreferredSize(new Dimension(450,100));
		top.setBorder(new EmptyBorder(40,0,0,0));
		top.setOpaque(false);
		
		top.add(bexercitii);
		top.add(bsomn);
		top.add(bmasa);
		top.add(bapa);
		top.add(bgreutate);
		add(top);
		
		bexercitii.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Intermediar app = new Intermediar(conn, id, 1);
				app.setVisible(true);
				app.setLocationRelativeTo(null);
				
			}
			
		});
		
		bsomn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Intermediar app = new Intermediar(conn, id, 2);
				app.setVisible(true);
				app.setLocationRelativeTo(null);
			}

			
		});
		
		bmasa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Intermediar app = new Intermediar(conn, id, 3);
				app.setVisible(true);
				app.setLocationRelativeTo(null);
			}
			
		});
		
		bapa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Intermediar app = new Intermediar(conn, id, 4);
				app.setVisible(true);
				app.setLocationRelativeTo(null);
			}
			
		});
		
		bgreutate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Intermediar app = new Intermediar(conn, id, 5);
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
