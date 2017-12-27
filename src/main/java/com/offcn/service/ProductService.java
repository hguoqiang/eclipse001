package com.offcn.service;

import java.util.List;

import com.offcn.pojo.Product;

import net.sf.json.JSONArray;

public interface ProductService {


	// 先写死 ，大白菜2017-11-01-------2017-11-30
	List<Product> getProductByNameAndRelease_Time1();

	
	// 先写死 ，大白菜2016-11-01-------2016-11-30
		List<Product> getProductByNameAndRelease_Time2();

		// 写入data.json文件
		void writeToData(String jsonStr);


		List<Double> getProductByMonthAVG2017();


		List<Double> getProductByMonthAVG2016();


	
	
}
