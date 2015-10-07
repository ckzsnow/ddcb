package com.dd.models;

public class ProvinceModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String provinceId;
	private String province;

	public ProvinceModel() {
	}

	public ProvinceModel(Integer id) {
		this.id = id;
	}

	public ProvinceModel(Integer id, String provinceId, String province) {
		this.id = id;
		this.provinceId = provinceId;
		this.province = province;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "ProvinceModel [id=" + id + ", provinceId=" + provinceId + ", province=" + province + "]";
	}

}