package class_package;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Masa implements Serializable{

	Date date;
	int calorii;
	int grasimi;
	int carbohidrati;
	int proteine;
	
	public Masa() {
		
	}

	
	public Masa(Date date, int calorii, int grasimi, int carbohidrati, int proteine) {
		this.date = date;
		this.calorii = calorii;
		this.grasimi = grasimi;
		this.carbohidrati = carbohidrati;
		this.proteine = proteine;
	}
	

	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public int getCalorii() {
		return calorii;
	}



	public void setCalorii(int calorii) {
		this.calorii = calorii;
	}



	public int getGrasimi() {
		return grasimi;
	}



	public void setGrasimi(int grasimi) {
		this.grasimi = grasimi;
	}



	public int getCarbohidrati() {
		return carbohidrati;
	}



	public void setCarbohidrati(int carbohidrati) {
		this.carbohidrati = carbohidrati;
	}



	public int getProteine() {
		return proteine;
	}



	public void setProteine(int proteine) {
		this.proteine = proteine;
	}


	@Override
	public String toString() {
		return "La masa din data de " + date + " s-au consumat " + calorii + 
				" calorii, " + grasimi + " grasimi " + carbohidrati + " carbohidrati " +
				proteine + " proteine";
	}

	public void insertDB(Connection conn, int id) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		
		String query = "SELECT calorii, grasimi, carbohidrati, proteine from masa WHERE data_masa='" + formatter.format(this.date) + "' AND id_utilizator=" + id;
		
		try(Statement stmt = conn.createStatement()){
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()==false) {
				query = "INSERT INTO masa (id_utilizator, data_masa, calorii, grasimi, carbohidrati, proteine) "
						+ "VALUES(" + id + ", '" + formatter.format(this.date) + "', " + this.calorii + ",  " + this.grasimi +
						", " + this.carbohidrati + ", " + this.proteine + ")";
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
			}
			else {
				int calorii = rs.getInt("calorii");
				int grasimi = rs.getInt("grasimi");
				int carbohidrati = rs.getInt("carbohidrati");
				int proteine = rs.getInt("proteine");
				calorii+= this.calorii;
				grasimi+= this.grasimi;
				carbohidrati+= this.carbohidrati;
				proteine = this.proteine;
				query = "UPDATE masa SET calorii=" + calorii + ", grasimi=" + grasimi + ", carbohidrati=" + carbohidrati + ", proteine=" +proteine
						+ " WHERE data_masa='" + formatter.format(this.date) + "' AND id_utilizator=" + id;
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
			}
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
