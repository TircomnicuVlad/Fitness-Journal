package class_package;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Apa {

	Date date;
	double volum;
	
	public Apa(){
		
	}
	
	public Apa(Date date, double volum) {
		this.date = date;
		this.volum = volum;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getVolum() {
		return volum;
	}

	public void setVolum(int volum) {
		this.volum = volum;
	}

	@Override
	public String toString() {
		return "In data de " + date + " utilizatorul a baut " + volum + " litri de apa";
	}
	
	public void insertDB(Connection conn, int id) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		
		String query = "SELECT apa from apa WHERE data_apa='" + formatter.format(this.date) + "' AND id_utilizator=" + id;
		
		try(Statement stmt = conn.createStatement()){
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()==false) {
				query = "INSERT INTO apa (id_utilizator, data_apa, apa) VALUES('" + id + "', '" + formatter.format(this.date) + "', '" + this.volum + "')";
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
			}
			else {
				double vol = rs.getDouble("apa");
				vol+= this.volum;
				query = "UPDATE apa SET apa=" + vol + " WHERE data_apa='" + formatter.format(this.date) + "' AND id_utilizator=" + id;
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
			}
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	

	
	
}
