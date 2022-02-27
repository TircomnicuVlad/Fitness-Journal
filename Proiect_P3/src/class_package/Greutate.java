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

public class Greutate extends Exercitiu implements Serializable{

	List <List<Integer>> greutate = new ArrayList<>();
	List <List<Integer>> repetari = new ArrayList<>();
	
	public Greutate() {
		super();
	}
	
	public Greutate(String nume, List<Date> date, List <Integer> serii, List<List<Integer>> greutate, List <List<Integer>> repetari) {
		
		super(nume,date,serii);
		this.greutate = greutate;
		this.repetari = repetari;
	}
	
	public Greutate(String nume, Date date, int serii, List <Integer> greutate, List <Integer> repetari) {
		super(nume, date, serii);
		this.greutate.add(greutate);
		this.repetari.add(repetari);
	}

	public List<List<Integer>> getGreutate() {
		return greutate;
	}

	public void setGreutate(List<List<Integer>> greutate) {
		this.greutate = greutate;
	}

	public List<List<Integer>> getRepetari() {
		return repetari;
	}

	public void setRepetari(List<List<Integer>> repetari) {
		this.repetari = repetari;
	}
	
	public void afisare() {
		super.afisare();;
		System.out.print("Folosind greutatile: ");
		for(List<Integer> i: greutate) {
			for(Integer j: i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.print("Nr repetari: ");
		for(List<Integer> i: repetari) {
			for(Integer j: i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	
	public String getSimpleName() {
		return "Greutate";
	}

	@Override
	public String toString() {
		return super.toString()+ "\n" +"Folosind greutatile: " + greutate + "\n" + "Nr repetari: =" + repetari;
	}
	
	public String toFile() {
		return super.toFile() + greutate + repetari;
	}
	
	public void add(Date new_date, int new_serii, List<Integer> new_gr, List<Integer> new_rep, List<Integer> new_t) {
		super.add(new_date, new_serii, new_gr, new_rep, new_t);
		greutate.add(new_gr);
		repetari.add(new_rep);
	}
	
	public void insertDB(Connection conn, int id) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");

		try(Statement stmt = conn.createStatement()){
			
			for(int i=0; i< greutate.get(0).size(); i++) {
					String query = "INSERT INTO exercitii (id_utilizator, data_inr, nume_ex, tip_ex, greutate, nr_repetari) "
						+ "VALUES('" + id + "', '" + formatter.format(this.date.get(0)) + "', '" + this.nume + "', "
						+ 1 + ", " + this.greutate.get(0).get(i) + ", " + this.repetari.get(0).get(i) + ")";
					Statement statement = conn.createStatement();
					statement.executeUpdate(query);
				}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
