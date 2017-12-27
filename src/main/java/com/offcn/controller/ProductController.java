package com.offcn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.offcn.pojo.Product;
import com.offcn.service.ProductService;

import net.sf.json.JSONArray;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/getProductByNameAndRelease_Time1.action")
	public String getProductByNameAndRelease_Time1() {

		// 先写死 ，大白菜2017-11-01-------2017-11-30
		List<Product> list = productService.getProductByNameAndRelease_Time1();

		JSONArray jsonStr = JSONArray.fromObject(list);

		productService.writeToData(jsonStr.toString());

		// 先写死 ，大白菜2016-11-01-------2016-11-30
		List<Product> list2 = productService.getProductByNameAndRelease_Time2();

		JSONArray jsonStr2 = JSONArray.fromObject(list2);

		return "baicai1";
	}

	// 把2个list集合放到一个Map中，然后写到 data.json文件
	@RequestMapping("/getProductByNameAndRelease_Time2.action")
	public String getProductByNameAndRelease_Time2() {
		// 先写死 ，大白菜2017-11-01-------2017-11-30
		List<Product> list1 = productService.getProductByNameAndRelease_Time1();

		// 先写死 ，大白菜2016-11-01-------2016-11-30
		List<Product> list2 = productService.getProductByNameAndRelease_Time2();
		
		
		
		
		// 大白菜  2017一个整年度每个月份的平均价值 
		List<Double> monthAVGList2017 = productService.getProductByMonthAVG2017();
		
		
		// 大白菜  2016一个整年度每个月份的平均价值 
		List<Double> monthAVGList2016 = productService.getProductByMonthAVG2016();
		
		
		
		Map map = new HashMap();
		map.put("list1", list1);
		map.put("list2", list2);
		
		
		map.put("monthAVGList2017", monthAVGList2017);
		map.put("monthAVGList2016", monthAVGList2016);
		
		JSONArray jsonStr = JSONArray.fromObject(map);

		productService.writeToData(jsonStr.toString());

		

		return "baicai3";
	}

}
