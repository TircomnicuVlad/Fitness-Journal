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

import class_package.Somn;

public class Somn_Interface extends JFrame{

	private JLabel lora_culcare;
	private JLabel lora_trezire;
	private JSpinner cora_culcare;
	private JSpinner cminut_culcare;
	private JSpinner cora_trezire;
	private JSpinner cminut_trezire;
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\vladi\\Pictures\\Saved Pictures\\istockphoto-1287504319-170667a.jpg");
	
	private JButton binregistrare;
	private JButton bmenu;
	
	List <Somn> lista_s;
	
	public Somn_Interface (Connection conn, int id) {
		
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
		
		lista_s = new ArrayList<>();
		
		lora_culcare = new JLabel("Ora culcare:");
		lora_culcare.setPreferredSize(new Dimension(120,12));
		lora_culcare.setHorizontalAlignment(SwingConstants.RIGHT);
		lora_trezire = new JLabel("Ora trezire:");
		lora_trezire.setPreferredSize(new Dimension(120,12));
		lora_trezire.setHorizontalAlignment(SwingConstants.RIGHT);

		
		cora_culcare = new JSpinner(new SpinnerNumberModel(0,0,24,1));
		cora_trezire = new JSpinner(new SpinnerNumberModel(0,0,24,1));
		cminut_culcare = new JSpinner(new SpinnerNumberModel(0,0,60,1));
		cminut_trezire = new JSpinner(new SpinnerNumberModel(0,0,60,1));

		
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
		
		top2.add(lora_culcare);
		top2.add(cora_culcare);
		top2.add(cminut_culcare);
		add(top2);
		top3.add(lora_trezire);
		top3.add(cora_trezire);
		top3.add(cminut_trezire);
		add(top3);
		top4.setBorder(new EmptyBorder(0,80,0,0));
		top4.add(binregistrare);
		top4.add(bmenu);
		add(top4);
		
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
				
				int ora_culcare= (int) cora_culcare.getValue();
				int minut_culcare = (int) cminut_culcare.getValue();
				int ora_trezire = (int) cora_trezire.getValue();
				int minut_trezire = (int) cminut_trezire.getValue();
				Somn a = new Somn(new Date(System.currentTimeMillis()) , ora_culcare, minut_culcare, ora_trezire, minut_trezire);
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
