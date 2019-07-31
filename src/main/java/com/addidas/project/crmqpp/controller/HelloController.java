package com.addidas.project.crmqpp.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.alibaba.fastjson.JSONObject;


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
	public ModelAndView toUpload() {
		ModelAndView mv = new ModelAndView();
		// -- 获取所有的分类
		File allCat = new File("D:/upload");
		String[] ac = allCat.list();
		mv.addObject("categories", ac);
		mv.setViewName("upload");
		return mv;
	
	}
	@PostMapping("/upload")
	@ResponseBody
	public String uploadFile(String apple,@RequestParam("file") MultipartFile[] files) throws Exception {
		System.out.println(files);
		for (MultipartFile file : files) {
			File f = new File("d:/upload/"+apple+"/"+ file.getOriginalFilename());
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			file.transferTo(f);
		}
		return "{'upload':'ok'}";
	}
	@GetMapping("list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		// -- 获取所有的分类
		File allCat = new File("d:/upload/");
		String[] ac = allCat.list();
		mv.addObject("categories", ac);
		mv.setViewName("list");
		return mv;
	}
	@GetMapping("/list/{category}")
	@ResponseBody
	public String list(@PathVariable String category) {
		// -- 1.拿到该目录下的所有文件
		File cf = new File("D:/upload/" + category);
		String[] files = cf.list();
		// -- 2. 返回文件列表JSON字符串
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("files", files);
		return jsonobj.toJSONString();
	}
	@GetMapping("/download/{category}/{file}")
	public void download(@PathVariable("category") String category, @PathVariable("file") String file,
			HttpServletResponse response) throws Exception {
		File f = new File("d:/upload/" + category + "/" + file);
		//-- 设置响应类型
		response.setContentType("application/force-dowmload");
		//-- 设置响应文件名
		response.addHeader("Content-Disposition", "attachment;fileName="+f.getName());
		// -- 文件输入流对象
		FileInputStream fis = new FileInputStream(f);
		// -- 文件缓存流对象
		BufferedInputStream bis = new BufferedInputStream(fis);
		// -- 文件输出流对象
		OutputStream os = response.getOutputStream();  
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = bis.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		bis.close();
		fis.close();

	}

	
}
