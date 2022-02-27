package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Show_ex extends JFrame{

	private JList lista;
	private DefaultListModel<String> l1;
	
	private JScrollPane p1;
	private JTextField cnume;
	private JSpinner crepetari;
	
	private JButton bmenu;
	private JButton bcalorii;
	private JButton bgrasimi;
	private JButton bcarbohidrati;
	private JButton bproteine;
	private JButton brepetari;
	private JButton bgreutate_timp;
	
	JPanel bottom1; 
	JPanel bottom2;
	JPanel bottom3;
	JPanel bottom4;
	
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\vladi\\Pictures\\Saved Pictures\\2968326.jpg");
	
	private double ming;
	
	private int ok=0;
	
	public Show_ex(Connection conn, int id, int opt) {
		
		super("Jurnal");
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(1024,676);
		this.setResizable(false);
		this.setContentPane(new JPanel() {
	         @Override
	         public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(img, 0, 0, null);
	         }
	      });
		
		l1 = new DefaultListModel<>();
		lista = new JList(l1);
		
		p1 = new JScrollPane(lista);

		bmenu = new JButton("Meniu Principal");
		p1.setPreferredSize(new Dimension(800,400));
		
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.setPreferredSize(new Dimension(1000,400));
		top.setOpaque(false);
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom.setPreferredSize(new Dimension(1000,60));
		bottom.setOpaque(false);
		
		top.add(p1);
		top.setBorder( new EmptyBorder(0,100,0,0));
		bottom.add(bmenu);
		bottom.setBorder( new EmptyBorder(10,450,0,0));
		add(top);
		add(bottom);
		
		//exercitii
		if(opt==1) {
			
			bgreutate_timp = new JButton("Greutate/Timp");
			brepetari = new JButton("Repetari");
			crepetari = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
			cnume = new JTextField(25);
			bottom2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			bottom2.setPreferredSize(new Dimension(1000,60));
			bottom2.setOpaque(false);
			bottom2.add(cnume);
			bottom2.add(bgreutate_timp);
			bottom2.setBorder(new EmptyBorder(0,120,0,0));
			add(bottom2);
			
			bottom3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			bottom3.setPreferredSize(new Dimension(1000,60));
			bottom3.setOpaque(false);
			bottom3.add(crepetari);
			bottom3.add(brepetari);
			bottom3.setBorder(new EmptyBorder(0,120,0,0));
			add(bottom3);
			
			bottom2.setVisible(true);
			bottom3.setVisible(true);
			
			bgreutate_timp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String nume = cnume.getText();
					String data="";
					String query = "SELECT tip_ex FROM exercitii WHERE id_utilizator=" + id + " AND nume_ex='" + nume + "'" ;
					int tip=0;
					
					try(Statement stmt = conn.createStatement()){
						
						ResultSet rs = stmt.executeQuery(query);
						if(rs.next()) {
							tip = rs.getInt("tip_ex");
						}
						else {
							JLabel eroare = new JLabel("Exercitiu inexistent");
							bottom4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
							bottom4.setPreferredSize(new Dimension(1000,60));
							bottom4.setOpaque(false);
							bottom4.add(eroare);
							add(bottom4);
							ok=1;
						}
						
						if(tip==1) {
							query = "SELECT MAX(greutate), data_inr, MIN(nr_repetari) FROM exercitii WHERE nume_ex='" + nume + "' GROUP BY data_inr";
							rs = stmt.executeQuery(query);
							int greutate=0, repetari=0;
							while(rs.next()) {
								data = rs.getString("data_inr");
								greutate = rs.getInt("MAX(greutate)");
								repetari = rs.getInt("MIN(nr_repetari)");
								String grafic = "";
								while(repetari > 0) {
									repetari--;
									grafic+= "*";
								}
								l1.addElement(data + " Greutate=" + greutate + ": " + grafic);
							}
							
						}else if (tip==2) {
							query = "SELECT MAX(timp), data_inr FROM exercitii WHERE nume_ex='" + nume + "' GROUP BY data_inr";
							rs = stmt.executeQuery(query);
							int timp=0;
							while(rs.next()) {
								data = rs.getString("data_inr");
								timp = rs.getInt("MAX(timp)");
								String grafic = "";
								while(timp > 0) {
									timp-=10;
									grafic+= "*";
								}
								l1.addElement(data + " Timp: " + grafic);
							}
							
						}


							bottom3.setVisible(false);
							bottom2.setVisible(false);
						} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			});
		}
		//somn
		else if(opt==2) {

			
			String query = "SELECT data_somn, ore_dormite, minute_dormite FROM somn WHERE id_utilizator=" + id;
			
			try(Statement stmt = conn.createStatement()){
				
				ResultSet rs = stmt.executeQuery(query);
				
				while( rs.next() ) {
					int ore_dormite = rs.getInt("ore_dormite");
					int minute_dormite = rs.getInt("minute_dormite");
					String data = rs.getString("data_somn");
					String grafic = "";
					while(ore_dormite>0) {
						ore_dormite--;
						grafic += "****";
					}
					while(minute_dormite>15) {
						minute_dormite-= 15;
						grafic += "*";
					}
					l1.addElement(data + ": " + grafic);
				}
					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		//masa
		else if(opt==3) {
			
			
			bcalorii = new JButton("Calorii");
			bgrasimi = new JButton("Grasimi");
			bcarbohidrati = new JButton("Carbohidrati");
			bproteine = new JButton("Proteine");
			bottom1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			bottom1.setPreferredSize(new Dimension(1000,60));
			bottom1.setOpaque(false);
			bottom1.add(bcalorii);
			bottom1.add(bgrasimi);
			bottom1.add(bcarbohidrati);
			bottom1.add(bproteine);
			add(bottom1);
			
			bottom1.setVisible(true);
			
			bcalorii.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String query = "SELECT data_masa, calorii FROM masa WHERE id_utilizator=" + id;
					
					try(Statement stmt = conn.createStatement()){
						
						ResultSet rs = stmt.executeQuery(query);
						
						while( rs.next() ) {
							int calorii = rs.getInt("calorii");
							String data = rs.getString("data_masa");
							String grafic = "";
							while(calorii > 0) {
								calorii-= 100;
								grafic += "*";
							}
							l1.addElement(data + ": " + grafic);
						}
						
						bottom1.setVisible(false);
							
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			});
			
			bgrasimi.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String query = "SELECT data_masa, grasimi FROM masa WHERE id_utilizator=" + id;
					
					try(Statement stmt = conn.createStatement()){
						
						ResultSet rs = stmt.executeQuery(query);
						
						while( rs.next() ) {
							int calorii = rs.getInt("grasimi");
							String data = rs.getString("data_masa");
							String grafic = "";
							while(calorii > 0) {
								calorii-= 2;
								grafic += "*";
							}
							l1.addElement(data + ": " + grafic);
						}
						
						bottom1.setVisible(false);
							
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			});
			
			bcarbohidrati.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String query = "SELECT data_masa, carbohidrati FROM masa WHERE id_utilizator=" + id;
					
					try(Statement stmt = conn.createStatement()){
						
						ResultSet rs = stmt.executeQuery(query);
						
						while( rs.next() ) {
							int calorii = rs.getInt("carbohidrati");
							String data = rs.getString("data_masa");
							String grafic = "";
							while(calorii > 0) {
								calorii-= 2;
								grafic += "*";
							}
							l1.addElement(data + ": " + grafic);
						}
						
						bottom1.setVisible(false);
							
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			});
			
			bproteine.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String query = "SELECT data_masa, proteine FROM masa WHERE id_utilizator=" + id;
					
					try(Statement stmt = conn.createStatement()){
						
						ResultSet rs = stmt.executeQuery(query);
						
						while( rs.next() ) {
							int calorii = rs.getInt("proteine");
							String data = rs.getString("data_masa");
							String grafic = "";
							while(calorii > 0) {
								calorii-= 2;
								grafic += "*";
							}
							l1.addElement(data + ": " + grafic);
						}
						
						bottom1.setVisible(false);
							
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			});
		}
		//apa
		else if(opt==4) {
			
			String query = "SELECT data_apa,apa FROM apa WHERE id_utilizator=" + id;
			
			try(Statement stmt = conn.createStatement()){
				
				ResultSet rs = stmt.executeQuery(query);
				
				while( rs.next() ) {
					double apa = rs.getDouble("apa");
					String data = rs.getString("data_apa");
					String grafic = "";
					while(apa>=1) {
						apa--;
						grafic += "**********";
					}
					while(apa>0) {
						apa-=0.1;
						grafic += "*";
					}
					l1.addElement(data + ": " + grafic);
				}
					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//greutate
		else {
			String query = "SELECT data_greutate,greutate FROM greutate WHERE id_utilizator=" + id + " ORDER BY data_greutate ASC";
			
			try(Statement stmt = conn.createStatement()){
				
				String query2 = "SELECT min(greutate) FROM greutate WHERE id_utilizator=" + id;
				
				ResultSet rs1 = stmt.executeQuery(query2);
				
				if(rs1.next()==true) {
					double ming = rs1.getDouble("min(greutate)");
								
				ResultSet rs = stmt.executeQuery(query);
				
				
				while( rs.next() ) {
					double greutate = rs.getDouble("greutate");
					String data = rs.getString("data_greutate");
					String grafic = "";
					
					greutate-= ming-0.5;
					while(greutate>0) {
						greutate-= 0.5;
						grafic += "*";
					}
					l1.addElement(data + ": " + grafic);
				}
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		bmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(opt==1) {

					bottom2.setVisible(false);
					bottom3.setVisible(false);
					if(ok==1) {
						bottom4.setVisible(false);
						ok=0;
					}
				}
				else if (opt==3) {
					bottom1.setVisible(false);
				}
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
