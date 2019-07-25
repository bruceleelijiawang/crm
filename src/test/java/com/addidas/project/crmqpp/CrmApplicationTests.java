package com.addidas.project.crmqpp;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.addidas.project.crmqpp.Dao.ProductDao;
import com.addidas.project.crmqpp.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {
	@Autowired
	private ProductDao proDao;
	@Test
	public void contextLoads() {
		List<Product> products = proDao.findAll();
		for (Product product : products) {
			System.out.println(product);
		}
	}
	@Test
	public void insertProduct(){
		for (int i = 0; i < 10; i++) {
			proDao.save(new Product("IPad"+i, 1000.00));
		}		
	}
}




