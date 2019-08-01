package com.addidas.project.crmqpp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.addidas.project.crmqpp.Dao.ProductDao;
import com.addidas.project.crmqpp.entity.Product;
import com.addidas.project.crmqpp.service.protype.IProductService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
@Autowired
private StringRedisTemplate srt;
@Autowired
private ProductDao produnctDao;
@Autowired
private RedisTemplate<Object, Object> rt;
	@Autowired
	private IProductService productservice;
	@Test
	public void tets01(){
		srt.opsForValue().set("javaee", "UNJF1903");
	}
	@Test
	public void tets02(){
		String value = srt.opsForValue().get("javaee");
		System.out.println(value);
	}
	@Test
	public void test03(){
		Product p = new Product();
		p.setId(1);
		p.setName("Bijiben");
		p.setPrice(5000);
		rt.opsForValue().set("asdasdas", p);
		
	}
	@Test
	public void tets05(){
		List<Product> products = productservice.getAllProductService();
		for (Product product : products) {
			System.out.println(product);
		}
	}
	@Test
	public void tets04(){
		Product product = (Product)rt.opsForValue().get("asdasdas");
		System.out.println(product);
	}
}
