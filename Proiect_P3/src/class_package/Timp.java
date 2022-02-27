package class_package;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Timp extends Exercitiu implements Serializable{

	List <List<Integer>> timp = new ArrayList<>();
	
	public Timp() {
		super();
	}
	
	public Timp(String nume, List<Date> date, List <Integer> serii,List <List<Integer>> timp) {
		
		super(nume, date, serii);
		this.timp = timp;
	}
	
	public Timp(String nume, Date date, int serii, List<Integer> timp) {
		super(nume, date, serii);
		this.timp.add(timp);
	}

	public List<List<Integer>> getTimp() {
		return timp;
	}

	public void setTimp(List<List<Integer>> timp) {
		this.timp = timp;
	}
	
	public void afisare() {
		super.afisare();
		System.out.print("Timp: ");
		for( List<Integer> i: timp ) {
			for( int j: i ) {
				System.out.print( j + " ");
			}
			System.out.println();
		}
	}

	public String getSimpleName() {
		return "Timp";
	}
	
	@Override
	public String toString() {
		return super.toString()+ "\n" + "Timp: " + timp;
	}
	
	public void add(Date new_date, int new_serii, List<Integer> new_gr, List<Integer> new_rep,  List<Integer> new_t) {
		super.add(new_date, new_serii, new_gr, new_rep, new_t);
		timp.add(new_t);
	}
	
	public void insertDB(Connection conn, int id) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		
		try(Statement stmt = conn.createStatement()){
			
				for(int i=0; i< timp.get(0).size(); i++) {
					String query = "INSERT INTO exercitii (id_utilizator, data_inr, nume_ex, tip_ex, timp) "
						+ "VALUES(" + id + ", '" + formatter.format(this.date.get(0)) + "', '" + this.nume + "', "
						+ 2 + ", " + this.timp.get(0).get(i) + ")";
					Statement statement = conn.createStatement();
					statement.executeUpdate(query);
				}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
