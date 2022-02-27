package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import class_package.Greutate_utilizator;
import class_package.Masa;


public class Masa_interface extends JFrame {

	
	private JLabel lcalorii;
	private JLabel lgrasimi;
	private JLabel lcarbohidrati;
	private JLabel lproteine;
	private JSpinner ccalorii;
	private JSpinner cgrasimi;
	private JSpinner ccarbohidrati;
	private JSpinner cproteine;

	private JButton binregistrare;
	private JButton bmenu;
	List <Masa> lista_m;
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\vladi\\Pictures\\Saved Pictures\\istockphoto-1287504319-170667a.jpg");

	public Masa_interface(Connection conn, int id) {
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
		
		lista_m = new ArrayList<>();

		lcalorii = new JLabel("Calorii:");
		lcalorii.setPreferredSize(new Dimension(120,12));
		lcalorii.setHorizontalAlignment(SwingConstants.RIGHT);
		lgrasimi = new JLabel("Grasimi:");
		lgrasimi.setPreferredSize(new Dimension(120,12));
		lgrasimi.setHorizontalAlignment(SwingConstants.RIGHT);
		lcarbohidrati = new JLabel("Carbohidrati:");
		lcarbohidrati.setPreferredSize(new Dimension(120,12));
		lcarbohidrati.setHorizontalAlignment(SwingConstants.RIGHT);
		lproteine = new JLabel("Proteine:");
		lproteine.setPreferredSize(new Dimension(120,12));
		lproteine.setHorizontalAlignment(SwingConstants.RIGHT);

		
		ccalorii = new JSpinner(new SpinnerNumberModel(1,1,10000,1));
		cgrasimi = new JSpinner(new SpinnerNumberModel(1,1,10000,1));
		ccarbohidrati = new JSpinner(new SpinnerNumberModel(1,1,10000,1));
		cproteine = new JSpinner(new SpinnerNumberModel(1,1,10000,1));

		
		binregistrare = new JButton("Introducere date");
		bmenu = new JButton("Meniu Principal");
		
		JPanel top2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top2.setPreferredSize(new Dimension(400,60));
		top2.setOpaque(false);
		JPanel top3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top3.setPreferredSize(new Dimension(400,60));
		top3.setOpaque(false);
		JPanel top4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top4.setPreferredSize(new Dimension(400,60));
		top4.setOpaque(false);
		JPanel top5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top5.setPreferredSize(new Dimension(400,60));
		top5.setOpaque(false);
		JPanel top6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top6.setPreferredSize(new Dimension(400,60));
		top6.setOpaque(false);
		
		top2.add(lcalorii);
		top2.add(ccalorii);
		add(top2);
		top3.add(lgrasimi);
		top3.add(cgrasimi);
		add(top3);
		top4.add(lcarbohidrati);
		top4.add(ccarbohidrati);
		add(top4);
		top5.add(lproteine);
		top5.add(cproteine);
		add(top5);
		top6.setBorder(new EmptyBorder(0,80,0,0));
		top6.add(binregistrare);
		top6.add(bmenu);
		add(top6);
		
		
		
		binregistrare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int calorii = (int) ccalorii.getValue();
				int grasimi = (int) cgrasimi.getValue();
				int carbohidrati = (int) ccarbohidrati.getValue();
				int proteine = (int) cproteine.getValue();
				Masa a = new Masa(new Date(System.currentTimeMillis()) , calorii, grasimi, carbohidrati, proteine);
				a.insertDB(conn, id);
			}
			
		});
		
		bmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Meniu_Principal app = new Meniu_Principal(conn, id);
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
