package org.generation.italy;



public class Country {
	private int countryId;
	private String name;
	private int area;
	private java.sql.Date nationalDay;
	private String countryCode;
	private String countryCode2;
	private int regionId;
	
	
	
	public Country (int countryId ,String name, int area ,java.sql.Date nationalDay ,  String countryCode , String countryCode2 , int regionId) {
		this.countryId = countryId;
		this.name = name;
		this.area=area;
		this.nationalDay = nationalDay;
		this.countryCode = countryCode;
		this.countryCode2 = countryCode2;
		this.regionId = regionId;
	}



	public int getCountryId() {
		return countryId;
	}



	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getArea() {
		return area;
	}



	public void setArea(int area) {
		this.area = area;
	}



	public java.sql.Date getNationalDay() {
		return nationalDay;
	}



	public void setNationalDay(java.sql.Date nationalDay) {
		this.nationalDay = nationalDay;
	}



	public String getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	public String getCountryCode2() {
		return countryCode2;
	}



	public void setCountryCode2(String countryCode2) {
		this.countryCode2 = countryCode2;
	}



	public int getRegionId() {
		return regionId;
	}



	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	
	
	
}
