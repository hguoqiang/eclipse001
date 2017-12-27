package com.offcn.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
//@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})//加载spring配置文件
public class ProductServiceImplTest {

	// @Autowired
	// private ProductService productService;
	@Test
	public void testWriteToData() {

		String[] split = "1,240.99".split(",");

		String str = "";
		for (String string : split) {
			str += string;
		}
		System.out.println(str);

		try {
			InputStream is = new FileInputStream("src/main/webapp/json/data.json");
			System.out.println(is);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

}
