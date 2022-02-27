package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Meniu_login extends JFrame{

	private JButton blogin;
	private JButton bcreate;
	
	private JLabel lnume;
	private JLabel lparola;
	
	private JTextField cnume;
	private JPasswordField cparola;
	
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\vladi\\Pictures\\Saved Pictures\\istockphoto-1213615970-170667a.jpg");
	
	public Meniu_login (Connection conn) {
		
		super("Jurnal");
		
		this.setLayout(new FlowLayout());
		setSize(500,350);
		this.setResizable(false);
		this.setContentPane(new JPanel() {
	         @Override
	         public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(img, 0, 0, null);
	         }
	      });
		
		lnume = new JLabel("Nume Utilizator:");
		lnume.setPreferredSize(new Dimension(120,12));
		lnume.setHorizontalAlignment(SwingConstants.RIGHT);
		lparola = new JLabel("Parola:");
		lparola.setPreferredSize(new Dimension(120,12));
		lparola.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cnume = new JTextField(25);
		cparola = new JPasswordField(25);
		
		blogin = new JButton ("Autentificare");
		bcreate = new JButton ("Creeaza Cont");
		
		JPanel top1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top1.setPreferredSize(new Dimension(500,90));
		top1.setOpaque(false);
		JPanel top2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top2.setOpaque(false);
		top2.setPreferredSize(new Dimension(500,60));
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom.setPreferredSize(new Dimension(500,60));
		bottom.setOpaque(false);
		
		top1.add(lnume);
		top1.add(cnume);
		top1.setBorder(new EmptyBorder(40,10,0,0));
		add(top1);
		top2.add(lparola);
		top2.add(cparola);
		top2.setBorder(new EmptyBorder(10,11,0,0));
		add(top2);
		
		bottom.add(blogin);
		bottom.add(bcreate);
		bottom.setBorder(new EmptyBorder(0,140,0,0));
		add(bottom);
		
		JLabel eroare1 = new JLabel("Parola sau nume de utilizator gresite");
		eroare1.setPreferredSize(new Dimension(250,15));
		eroare1.setHorizontalAlignment(SwingConstants.RIGHT);
		 
		JPanel bottom1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom1.setPreferredSize(new Dimension(500,100));
		
		bottom1.add(eroare1);
		bottom1.setBorder(new EmptyBorder(0,100,0,0));
		add(bottom1);
		bottom1.setVisible(false);
		
		JLabel eroare2 = new JLabel("Nume de utilizator folosit");
		eroare2.setPreferredSize(new Dimension(250,15));
		eroare2.setHorizontalAlignment(SwingConstants.RIGHT);
		 
		JPanel bottom2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom2.setPreferredSize(new Dimension(500,100));
		
		bottom2.add(eroare2);
		bottom2.setBorder(new EmptyBorder(0,60,0,0));
		add(bottom2);
		bottom2.setVisible(false);
		
		blogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String parola = cparola.getText();
				String nume = cnume.getText();
				
				String query = "SELECT id, username, parola FROM utilizatori WHERE username='" + nume + "' AND parola='" + parola + "'";
				try(Statement stmt = conn.createStatement()){
					ResultSet rs = stmt.executeQuery(query);
					if (rs.next() == true && nume.equals("")==false && parola.equals("")==false) {
						dispose();
						int id = rs.getInt("id");
						Meniu_Principal app = new Meniu_Principal(conn, id);
						app.setVisible(true);
						app.setLocationRelativeTo(null);
					}
					else {
						bottom2.setVisible(false);
						bottom1.setVisible(true);
						
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
			
		});
		
		bcreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nume = cnume.getText();
				String parola = cparola.getText();
				
				String query = "SELECT username, parola FROM utilizatori WHERE username='" + nume + "'";
				try(Statement stmt = conn.createStatement()){
					ResultSet rs = stmt.executeQuery(query);
					if (rs.next() == false && nume.equals("")==false && parola.equals("")==false) {

						query = "INSERT INTO utilizatori(username, parola) VALUES ('" + nume + "', '" + parola + "')";
						Statement statement = conn.createStatement();
						statement.executeUpdate(query);
						
						query = "SELECT id FROM utilizatori WHERE username='" + nume + "'";
						rs = stmt.executeQuery(query);
						rs.next();
						int id = rs.getInt("id");
						dispose();
						Meniu_Principal app = new Meniu_Principal(conn, id);
						app.setVisible(true);
						app.setLocationRelativeTo(null);
					}
					else {
						bottom1.setVisible(false);
						bottom2.setVisible(true);
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
