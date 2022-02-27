package main_package;

import java.sql.Connection;
import java.sql.DriverManager;

import GUI.*;


public class Main {

	public static void main(String[] args) {
		
		try {
			
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnesspal","root", "");
			System.out.print("Database is connected !");
			Meniu_login app = new Meniu_login(conn);
			app.setVisible(true);
			app.setLocationRelativeTo(null);
			
		} catch (Exception e) {

			System.out.print("Do not connect to DB - Error:"+e);
		}

	}

}
