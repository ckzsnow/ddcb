package com.dd.models;

public class CityModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cityId;
	private String city;
	private String father;

	// Constructors

	/** default constructor */
	public CityModel() {
	}

	/** minimal constructor */
	public CityModel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CityModel(Integer id, String cityId, String city, String father) {
		this.id = id;
		this.cityId = cityId;
		this.city = city;
		this.father = father;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFather() {
		return this.father;
	}

	public void setFather(String father) {
		this.father = father;
	}

}