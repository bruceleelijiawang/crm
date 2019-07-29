package com.addidas.project.crmqpp.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	void update(int id,String name,double price);
	@Delete("delete from t_product where id=#{id}")
	void delete(int id);
	@Update("update t_product set name=#{name},price=#{price} where id=#{id}")
	void update(Product product);
	@Select("Select * from t_product where id=#{id}")
	Product find(int id);
}
