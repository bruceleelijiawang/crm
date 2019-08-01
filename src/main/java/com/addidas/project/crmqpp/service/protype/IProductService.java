package com.addidas.project.crmqpp.service.protype;

import java.util.List;

import com.addidas.project.crmqpp.entity.Product;



public interface IProductService {
	public void createProduct(Product product);
	public List<Product> getAllProductService();
}
