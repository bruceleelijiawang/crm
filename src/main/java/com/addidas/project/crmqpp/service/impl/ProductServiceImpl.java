package com.addidas.project.crmqpp.service.impl;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.addidas.project.crmqpp.Dao.ProductDao;
import com.addidas.project.crmqpp.entity.Product;
import com.addidas.project.crmqpp.service.protype.IProductService;



@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired 
	private RedisTemplate<Object, Object> rt;
	
	@Override
	public void createProduct(Product product) {
		productDao.save(product);
	}

	@Override
	@Cacheable(value="chensdasd")
	public List<Product> getAllProductService() {
		return productDao.findAll();
	}

}
