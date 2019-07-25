package com.addidas.project.crmqpp.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;

import com.addidas.project.crmqpp.entity.Product;

/***
 * 产品Dao
 * @author 13195
 *
 */


public interface ProductDao {
	@Select("select * from t_product")
	List<Product> findAll();
	void save(Product product);
}
