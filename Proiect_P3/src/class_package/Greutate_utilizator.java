package class_package;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Greutate_utilizator {

	double kg;
	Date date;
	
	public Greutate_utilizator(Date date, double kg) {
		
		this.kg = kg;
		this.date = date;
	}

	public double getKg() {
		return kg;
	}

	public void setKg(int kg) {
		this.kg = kg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Greutatea utilizatorului in data de " + date + ": " + kg;
	}
	
	public void insertDB(Connection conn, int id) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		
		String query = "SELECT greutate from greutate WHERE data_greutate='" + formatter.format(this.date) + "' AND id_utilizator=" + id;
		
		try(Statement stmt = conn.createStatement()){
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()==false) {
				query = "INSERT INTO greutate (id_utilizator, data_greutate, greutate) VALUES(" + id + ", '" + formatter.format(this.date) + "', " + this.kg + ")";
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
			}
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
