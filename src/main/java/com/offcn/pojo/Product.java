package com.offcn.pojo;

public class Product {
	private Integer id; // 主键
	private String name; // 名字
	private double min_price;// 最低价
	private double average_price;// 平均价
	private double max_price;// 最高价
	private String type;// 规格类型
	private String measure_unit;// 计量单位
	private String release_date; // 发布时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMin_price() {
		return min_price;
	}

	public void setMin_price(double min_price) {
		this.min_price = min_price;
	}

	public double getAverage_price() {
		return average_price;
	}

	public void setAverage_price(double average_price) {
		this.average_price = average_price;
	}

	public double getMax_price() {
		return max_price;
	}

	public void setMax_price(double max_price) {
		this.max_price = max_price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMeasure_unit() {
		return measure_unit;
	}

	public void setMeasure_unit(String measure_unit) {
		this.measure_unit = measure_unit;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

}
