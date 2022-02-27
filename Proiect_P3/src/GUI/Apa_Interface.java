package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import class_package.Apa;

public class Apa_Interface extends JFrame{

	
	private JLabel lapa;
	private JSpinner capa;

	private JButton binregistrare;
	private JButton bmenu;
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\vladi\\Pictures\\Saved Pictures\\istockphoto-1287504319-170667a.jpg");

	public Apa_Interface(Connection conn, int id) {
		super("Jurnal");
		this.setLayout(new FlowLayout());
		setSize(500,370);
		this.setResizable(false);

		this.setContentPane(new JPanel() {
	         @Override
	         public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(img, 0, 0, null);
	         }
	      });
		
		lapa = new JLabel("Apa:");
		lapa.setPreferredSize(new Dimension(120,12));
		lapa.setHorizontalAlignment(SwingConstants.RIGHT);
		
		capa = new JSpinner(new SpinnerNumberModel(0,0,5,0.1));
		capa.setPreferredSize(new Dimension(50,20));

		binregistrare = new JButton("Introducere date");
		bmenu = new JButton("Meniu Principal");
		
		JPanel top2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top2.setPreferredSize(new Dimension(400,60));
		top2.setOpaque(false);
		JPanel top3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top3.setPreferredSize(new Dimension(400,60));
		top3.setOpaque(false);

		top2.add(lapa);
		top2.add(capa);
		add(top2);
		top3.setBorder(new EmptyBorder(0,80,0,0));
		top3.add(binregistrare);
		top3.add(bmenu);
		add(top3);
		

		bmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Meniu_Principal app = new Meniu_Principal(conn, id);
				app.setVisible(true);
				app.setLocationRelativeTo(null);
				
			}

			
		});
		
		binregistrare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Apa a = new Apa(new Date(System.currentTimeMillis()),(double) capa.getValue());
				a.insertDB(conn, id);
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
