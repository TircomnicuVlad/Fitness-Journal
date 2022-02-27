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


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import class_package.*;

public class Exercitii_interface extends JFrame{

	private JLabel lnume_exercitiu;
	private JLabel lnr_serii;
	private JLabel lgreutate;
	private JLabel lrepetari;
	private JLabel ltimp;
	private JTextField cnume_exercitiu;
	private JSpinner cnr_serii;
	private JSpinner cgreutate;
	private JSpinner crepetari;
	private JSpinner ctimp;
	
	private JButton binregistrare1;
	private JButton bmenu1;
	private JButton binregistrare2;
	private JButton bmenu2;
	
	private JLabel lbutton;

	private JButton bgreutate;
	private JButton btimp;
	
	boolean found;
	List <Exercitiu> lista_e;
	private JSpinner[] lista_greutati;
	private JSpinner[] lista_repetarim;
	private JSpinner[] lista_timpi;
	
	JSpinner[] lista_label;
	
	int serii;
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\vladi\\Pictures\\Saved Pictures\\istockphoto-1287504319-170667a.jpg");
	
	public Exercitii_interface(Connection conn, int id) {
		
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
		
		lista_e = new ArrayList<>();
		
		lnume_exercitiu = new JLabel("Nume Exercitiu:");
		lnume_exercitiu.setPreferredSize(new Dimension(120,12));
		lnume_exercitiu.setHorizontalAlignment(SwingConstants.RIGHT);
		lnr_serii = new JLabel("Nr. Serii:");
		lnr_serii.setPreferredSize(new Dimension(120,12));
		lnr_serii.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		
		cnume_exercitiu = new JTextField(25);
		cnr_serii = new JSpinner(new SpinnerNumberModel(0,0,10,1));
		
		
		lbutton = new JLabel("Tipul de exercitiu:");
		lbutton.setPreferredSize(new Dimension(120,12));
		lbutton.setHorizontalAlignment(SwingConstants.RIGHT);
		
		bgreutate = new JButton("Greutati");
		btimp = new JButton("Timp");
		
		
		JPanel top1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top1.setPreferredSize(new Dimension(400,60));
		top1.setOpaque(false);
		JPanel top2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top2.setPreferredSize(new Dimension(400,60));
		top2.setOpaque(false);
		JPanel top4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top4.setPreferredSize(new Dimension(400,60));
		top4.setOpaque(false);
		bgreutate.setPreferredSize(new Dimension(80,30));
		btimp.setPreferredSize(new Dimension(80,30));
		
		top1.add(lnume_exercitiu);
		top1.add(cnume_exercitiu);
		top2.add(lnr_serii);
		top2.add(cnr_serii);
		add(top1);
		add(top2);
		top4.setBorder(new EmptyBorder(0,120,0,0));
		top4.add(bgreutate);
		top4.add(btimp);
		add(top4);
		
		lgreutate = new JLabel("Greutate:");
		lgreutate.setPreferredSize(new Dimension(120,12));
		lgreutate.setHorizontalAlignment(SwingConstants.RIGHT);
		lrepetari = new JLabel("Nr. repetari:");
		lrepetari.setPreferredSize(new Dimension(120,12));
		lrepetari.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cgreutate = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
		crepetari = new JSpinner(new SpinnerNumberModel(1,1,500,1));
		
		binregistrare1 = new JButton("Introducere date");
		bmenu1 = new JButton("Meniu Principal");
		
		JPanel bottom1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom1.setPreferredSize(new Dimension(400,60));
		bottom1.setOpaque(false);
		JPanel bottom2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom2.setPreferredSize(new Dimension(400,60));
		bottom2.setOpaque(false);
		JPanel bottom3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom3.setPreferredSize(new Dimension(400,60));
		bottom3.setOpaque(false);
		
		bottom1.add(lgreutate);
		bottom1.setVisible(false);
		add(bottom1);
		bottom2.add(lrepetari);
		bottom2.setVisible(false);
		add(bottom2);
		bottom3.setBorder(new EmptyBorder(0,80,0,0));
		bottom3.add(binregistrare1);
		bottom3.add(bmenu1);
		bottom3.setVisible(false);
		add(bottom3);
		
		ltimp = new JLabel("Timp:");
		ltimp.setPreferredSize(new Dimension(120,12));
		ltimp.setHorizontalAlignment(SwingConstants.RIGHT);
		
		ctimp = new JSpinner(new SpinnerNumberModel(1,1,3600,1));
		
		binregistrare2 = new JButton("Introducere date");
		bmenu2 = new JButton("Meniu Principal");
		
		JPanel bottom4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom4.setPreferredSize(new Dimension(400,60));
		bottom4.setOpaque(false);
		JPanel bottom5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom5.setPreferredSize(new Dimension(400,60));
		bottom5.setOpaque(false);
		
		bottom4.add(ltimp);
		bottom4.setVisible(false);
		add(bottom4);
		bottom5.setBorder(new EmptyBorder(0,80,0,0));
		bottom5.add(binregistrare2);
		bottom5.add(bmenu2);
		bottom5.setVisible(false);
		add(bottom5);
		
		bgreutate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nume = cnume_exercitiu.getText();
				serii = (Integer)cnr_serii.getValue();
				if(!nume.isBlank() && serii != 0) {
					
				    lista_label = new JSpinner[serii];
					for(int i = 0; i < serii; i++) {
						lista_label[i] = new JSpinner(new SpinnerNumberModel(0,0,500,1));
						bottom1.add(lista_label[i]);
					}
					
					lista_greutati = lista_label;
					
					lista_label = new JSpinner[serii];
					for(int i = 0; i < serii; i++) {
						lista_label[i] = new JSpinner(new SpinnerNumberModel(1,1,100,1));
						bottom2.add(lista_label[i]);
					}
					
					lista_repetarim = lista_label;
					
					bottom1.setVisible(true);
					bottom2.setVisible(true);
					bottom3.setVisible(true);
					
					top4.setVisible(false);
					
					binregistrare1.setEnabled(true);
					bmenu1.setEnabled(true);
				
				}
				serii = 0;
			}
			
		});
		
		btimp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nume = cnume_exercitiu.getText();
				serii = (int)cnr_serii.getValue();
				if(!nume.isBlank() && serii != 0) {
					
					lista_label = new JSpinner[serii];
					for(int i = 0; i < serii; i++) {
						lista_label[i] = new JSpinner(new SpinnerNumberModel(1,1,3600,1));
						bottom4.add(lista_label[i]);
					}
					
					lista_timpi = lista_label;
					
					bottom4.setVisible(true);
					bottom5.setVisible(true);
					
					top4.setVisible(false);
					
					binregistrare2.setEnabled(true);
					bmenu2.setEnabled(true);
			
				}
				
				serii = 0;
			}
			
		});
		
		binregistrare1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nume = cnume_exercitiu.getText();
				serii = (Integer)cnr_serii.getValue();
				List <Integer> greutati1 = new ArrayList<>();
				List <Integer> repetari1 = new ArrayList<>();
				for(int i =0; i < serii; i++) {
					greutati1.add((int)lista_greutati[i].getValue());
					repetari1.add((int)lista_repetarim[i].getValue());
				}
						Greutate a = new Greutate(nume, new Date(System.currentTimeMillis()) ,serii, greutati1, repetari1);
						a.insertDB(conn, id);
						dispose();
						Exercitii_interface app = new Exercitii_interface(conn, id);
						app.setVisible(true);
						app.setLocationRelativeTo(null);
						
			}
			
		});
		
		bmenu1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Meniu_Principal app = new Meniu_Principal(conn, id);
				app.setVisible(true);
				app.setLocationRelativeTo(null);
			}
			
		});
		
		binregistrare2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String nume = cnume_exercitiu.getText();
				serii = (Integer)cnr_serii.getValue();
				List <Integer> timp1 = new ArrayList<>();
				for(int i =0; i < serii; i++) {
					timp1.add((int)lista_timpi[i].getValue());
				}
						Timp a = new Timp(nume, new Date(System.currentTimeMillis()) ,serii, timp1);
						a.insertDB(conn, id);
						dispose();
						Exercitii_interface app = new Exercitii_interface(conn, id);
						app.setVisible(true);
						app.setLocationRelativeTo(null);
			}
			
		});
		
		bmenu2.addActionListener(new ActionListener() {

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
