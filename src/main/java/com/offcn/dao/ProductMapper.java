package com.offcn.dao;

import java.util.List;

import com.offcn.pojo.Product;

public interface ProductMapper {

	// 先写死 ，大白菜2017-11-01-------2017-11-30
	List<Product> getProductByNameAndRelease_Time1();

	
	// 先写死 ，大白菜2016-11-01-------2016-11-30
		List<Product> getProductByNameAndRelease_Time2();


		List<Double> getProductByMonthAVG2017();


		List<Double> getProductByMonthAVG2016();
}
