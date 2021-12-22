package org.generation.italy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
	private final static String DB_URL = "jdbc:mysql://localhost:3306/nation";				
	private final static String DB_USER = "root";
	private final static String DB_PASSWORD = "rootpassword";

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			
		
		Country choosenCountry = selectCountryById (con,scan);
		if(choosenCountry != null) {
			System.out.println("You chose " +  choosenCountry.getName());
			System.out.println("national day: " + choosenCountry.getNationalDay());
			
			if (choosenCountry.getNationalDay() == null) {
				System.out.println("Insert the national day,please(d/MM/yyyy)");
				String nationalDayInput = scan.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				  LocalDate localDate = LocalDate.parse(nationalDayInput, formatter);
				  choosenCountry.setNationalDay(java.sql.Date.valueOf( localDate ));
				  updateCountry(con,choosenCountry);
				  
				
			}
		}
		
		
		
		}catch (SQLException e) {
			System.out.println("OOPS an error as occured");
			System.out.println(e.getMessage());
		}
		scan.close();
		

	}
	private static Country selectCountryById (Connection con,Scanner scan) throws SQLException {
		Country choosenCountry = null;
		System.out.println("Select country id: ");
		int countryId = Integer.parseInt(scan.nextLine());
		
		String selectCountry = "select *\r\n"
				+ "from countries c\r\n"
				+ "where c.country_id = ?;";
		
		try(PreparedStatement psCountry = con.prepareStatement(selectCountry)){
			psCountry.setInt(1, countryId);
			try(ResultSet rsCountry = psCountry.executeQuery()) {
				rsCountry.next();
				choosenCountry = new Country(rsCountry.getInt(1), rsCountry.getString(2), rsCountry.getInt(3), rsCountry.getDate(4), rsCountry.getString(5), rsCountry.getString(6), rsCountry.getInt(7));
			}
		}
		
		
		return choosenCountry; 
	}
	private static void updateCountry (Connection con,Country choosenCountry) throws SQLException {
		
		String sqlUpdated = "update countries\r\n"
				+ "set country_id = ?,name = ? , area =?,national_day =?,country_code2 =?,country_code3 =?,region_id =?\r\n"
				+ "where country_id =?;";
		try(PreparedStatement psUpdateCountry = con.prepareStatement(sqlUpdated) ){
			psUpdateCountry.setInt(1, choosenCountry.getCountryId());
			psUpdateCountry.setString(2, choosenCountry.getName());			
			psUpdateCountry.setInt(3, choosenCountry.getArea());
			psUpdateCountry.setDate(4, choosenCountry.getNationalDay());
			psUpdateCountry.setString(5, choosenCountry.getCountryCode());	
			psUpdateCountry.setString(6, choosenCountry.getCountryCode2());	
			psUpdateCountry.setInt(7, choosenCountry.getRegionId());
			psUpdateCountry.setInt(8, choosenCountry.getCountryId());
			int result = psUpdateCountry.executeUpdate();
			if(result == 1) {
				System.out.println(result + " column changed");
			}else if(result > 1) {
				System.out.println(result + "columns have been changed");
			}else {
				System.out.println("No changes made");
			}
			
		}
	}

}
