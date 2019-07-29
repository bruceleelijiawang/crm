package com.addidas.project.crmqpp.service.protype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addidas.project.crmqpp.Dao.ProductDao;
import com.addidas.project.crmqpp.entity.Product;
import com.addidas.project.crmqpp.service.impl.IProductService;
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductDao productDao;
	


	@Override
	public void createProduct(Product product) {
		productDao.save(product);
		
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productDao.findAll();
	}

	@Override
	public void updateProduct(int id, String name, double price) {
		productDao.update(id, name, price);
		
	}
}
