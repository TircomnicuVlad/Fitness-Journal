package class_package;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Somn implements Serializable{

	int ore_somn;
	int minute;
	Date date;
	
	public Somn() {
		
	}
	
	public Somn(int ore_somn, int minute, Date date) {
		this.ore_somn = ore_somn;
		this.minute = minute;
		this.date = date;
	}
	
	public Somn(Date date, int ora_culcare, int minut_culcare, int ora_trezire, int minut_trezire) {
		this.date = date;
		this.ore_somn = ora_trezire - ora_culcare;
		if ( this.ore_somn < 0) {
			this.ore_somn += 24;
		}
		this.minute = minut_trezire - minut_culcare;
		if ( this.minute < 0 ) {
			this.minute += 60;
			this.ore_somn--;
		}
	}

	public int getOre_somn() {
		return ore_somn;
	}

	public void setOre_somn(int ore_somn) {
		this.ore_somn = ore_somn;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return ore_somn + ":" + minute + " " + date + "; ";
	}

	public void insertDB(Connection conn, int id) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		
		String query = "SELECT ore_dormite, minute_dormite FROM somn WHERE data_somn='" + formatter.format(this.date) + "' AND id_utilizator=" + id;
		
		try(Statement stmt = conn.createStatement()){
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()==false) {
				query = "INSERT INTO somn (id_utilizator, data_somn, ore_dormite, minute_dormite) VALUES('" + id + "', '" 
			+ formatter.format(this.date) + "', " + this.ore_somn + ", " + this.minute +  ")";
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
			}
			else {
				int ore = rs.getInt("ore_dormite");
				int minute_somn = rs.getInt("minute_dormite");
				ore+= this.ore_somn;
				minute_somn+= this.minute;
				query = "UPDATE somn SET ore_dormite=" + ore + ", minute_dormite=" + minute_somn + " WHERE data_somn='" + formatter.format(this.date) + "' AND id_utilizator=" + id;
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
			}
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
}
