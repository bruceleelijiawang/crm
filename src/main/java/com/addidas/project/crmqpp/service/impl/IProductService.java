package com.addidas.project.crmqpp.service.impl;

import java.util.List;

import com.addidas.project.crmqpp.entity.Product;

/***
 * 
 * 产品业务接口
 * @author 13195
 *
 */
public interface IProductService {
	
	void createProduct(Product product);		//..生产产品
	List<Product> getAllProducts();				//..
	void updateProduct(int id,String name,double price);
}
