package com.offcn.service;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.dao.ProductMapper;
import com.offcn.pojo.Product;

//http://localhost:8080/day03-work/getProductByNameAndRelease_Time1.action
@Transactional
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override// 先写死 ，大白菜2017-11-01-------2017-11-30
	public List<Product> getProductByNameAndRelease_Time1() {
		List<Product> list = productMapper.getProductByNameAndRelease_Time1();
		return list;
	}

	// 先写死 ，大白菜2016-11-01-------2016-11-30
	public List<Product> getProductByNameAndRelease_Time2() {
		List<Product> list = productMapper.getProductByNameAndRelease_Time2();
		return list;
	}

	@Override
	public void writeToData(String jsonStr) {
	
		
		FileOutputStream  out =null;
		try {
			
		 out = new FileOutputStream("src/main/webapp/json/data3.json");
		
		
			
			out.write(jsonStr.getBytes());
			
			out.flush();
	
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
		
		
	}

	@Override  // 大白菜  2017一个整年度每个月份的平均价值 
	public List<Double> getProductByMonthAVG2017() {
		
		List<Double> list =	productMapper.getProductByMonthAVG2017();
		return list;
	}

	@Override// 大白菜  2016一个整年度每个月份的平均价值 
	public List<Double> getProductByMonthAVG2016() {
		
		List<Double>  list= productMapper.getProductByMonthAVG2016();
		return list;
	}

}
