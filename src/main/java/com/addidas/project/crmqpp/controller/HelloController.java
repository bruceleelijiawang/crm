package com.addidas.project.crmqpp.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.addidas.project.crmqpp.Dao.ProductDao;
import com.addidas.project.crmqpp.entity.Product;
import com.addidas.project.crmqpp.service.impl.IProductService;
@Controller
public class HelloController {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private IProductService prodService;
	@RequestMapping("/hello")
	@ResponseBody
	public  String hello(){
		return "das";
	}
	@GetMapping("/index")
	public ModelAndView index(){
		
		ModelAndView mv = new ModelAndView();
		List<Product> products = prodService.getAllProducts();
		mv.addObject("msg", "大中华");
		mv.addObject("products", products);
		mv.setViewName("index");
		return mv;
		
	}
	@RequestMapping(value = "/add_product", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String reg(Product product) {
		System.out.println(product.getName());
		System.out.println(product.getPrice());
		productDao.save(product);
		return "{\"0\":\"添加成功,请查看数据库。\"}";
		
	}
	@RequestMapping(value = "/delete_product", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String qq(int id) {
		productDao.delete(id);
		return "{\"0\":\"删除成功,请查看数据库。\"}";
		
	}
	@RequestMapping(value = "/update_product", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody	
	public String qwer(Product product) {
		
		productDao.update(product);
		return "{\"0\":\"修改成功,请查看数据库。\"}";
		
	}
	@GetMapping("toupload")
	public String toupload(){
		return "upload";
	}
	@PostMapping("/upload")
	@ResponseBody
	public String demoo(@RequestParam("file") MultipartFile[] files) throws Exception{
		for (MultipartFile file : files) {
			file.transferTo(new File("D:/aaa/"+file.getOriginalFilename()));
		}
		return "{'upload':'ok}";
	}
}
