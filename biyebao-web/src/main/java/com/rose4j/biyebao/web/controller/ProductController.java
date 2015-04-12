package com.rose4j.biyebao.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rose4j.biyebao.common.Constants;
import com.rose4j.biyebao.domain.News;
import com.rose4j.biyebao.domain.Page;
import com.rose4j.biyebao.domain.Product;
import com.rose4j.biyebao.product.service.ProductService;
import com.rose4j.biyebao.web.dto.ProductColumn;

@Controller
public class ProductController extends BaseController{
	@Autowired
	private ProductService productService;
	/**
	 * 首页列表（权重排序）
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String listForHandler(Model model,HttpServletRequest request) {
		System.out.println(request.getAttribute("test"));
		try {
			List<Product> indexProducts = productService.queryIndexProducts();

			List<Product> jspProduct = new ArrayList<Product>();
			List<Product> aspProduct = new ArrayList<Product>();
			List<Product> phpProduct = new ArrayList<Product>();
			List<Product> androidProduct = new ArrayList<Product>();
			List<Product> vbProduct = new ArrayList<Product>();
			List<Product> vcProduct = new ArrayList<Product>();

			for (Product product : indexProducts) {
				if (!checkInvalidProduct(product)) {
					continue;
				}
				if (Constants.LANGE_TYPE_ASP.equals(product.getLangType())) {
					aspProduct.add(product);
				} else if (Constants.LANGE_TYPE_JSP.equals(product
						.getLangType())) {
					jspProduct.add(product);

				} else if (Constants.LANGE_TYPE_PHP.equals(product
						.getLangType())) {
					phpProduct.add(product);
				} else if (Constants.LANGE_TYPE_ANDROID.equals(product
						.getLangType())) {
					androidProduct.add(product);
				}else if(Constants.LANGE_TYPE_VB.equals(product
						.getLangType())){
					vbProduct.add(product);
				}else if(Constants.LANGE_TYPE_VC.equals(product
						.getLangType())){
					vcProduct.add(product);
				}
			}
			List<ProductColumn<Product>> products = new ArrayList<ProductColumn<Product>>();
			products.add(new ProductColumn<Product>("JSP", jspProduct));
			products.add(new ProductColumn<Product>("ASP", aspProduct));

			products.add(new ProductColumn<Product>("PHP", phpProduct));

			products.add(new ProductColumn<Product>("Android", androidProduct));
			products.add(new ProductColumn<Product>("VB", vbProduct));
			products.add(new ProductColumn<Product>("VC", vcProduct));

			model.addAttribute("indexProducts", products);
			List<Product> recommendProducts=null;
			try {
				recommendProducts = productService.queryRecommendProducts();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("recommendProducts", recommendProducts);

		} catch (Exception e) {
			e.printStackTrace();
		}
		buildNews(model);
		return "product/index";
	}
	
	private void buildNews(Model model){
		List<News> news = productService.getLastedNews();
		model.addAttribute("newsList", news);
	}
	/**
	 * 产品详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/{id}.html")
	public String detailForHandle(Model model, @PathVariable String id) {
		System.out.println(id);
		try {
			Product product = productService
					.findProductById(Long.parseLong(id));
			String productCloumn = product.getLangType();
			model.addAttribute("productCloumn", productCloumn);
			model.addAttribute("product", product);
			List<Product> products= productService.getRelatedProducts(Long.parseLong(id));
			model.addAttribute("relatedProducts",products);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "product/detail";
	}
	/**
	 * 栏目产品列表
	 * @param model
	 * @param columnType
	 * @return
	 */
	@RequestMapping(value = "/column/{columnType}.html")
	public String listForHandler(Model model, @PathVariable String columnType,HttpServletRequest request) {
		// valid
		System.out.println(request.getContextPath());
		String strPage="1";
		String[] param=columnType.split("-");
		if(param!=null && param.length>1){
			strPage=columnType.split("-")[1];
		}
		columnType=columnType.split("-")[0];
		model.addAttribute("productCloumn", columnType.split("-")[0]);
		int total = productService.getProductNumByCategory(columnType);
		Page page = buildPageCommand(request, strPage);
		try {
			List<Product> listProducts = productService
					.getAllProductsByCategoryByPage(columnType,page);
			model.addAttribute("cloumnProducts", listProducts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Product> hotProducts=null;
		try {
			hotProducts = productService.getHotProductsByCategory(columnType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hotProducts", hotProducts);

		List<Product> lastedProducts=null;
		try {
			lastedProducts = productService.getLastedProductsByCategory(columnType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("lastedProducts", lastedProducts);

		//page
		
		page.setTotal(total);
		model.addAttribute("page", page);
		return "product/list";
	}
	@RequestMapping(value = "/productsearch/{search}.html")
	public String searchForHandler(Model model,HttpServletRequest request,HttpServletResponse response, @PathVariable String search) throws UnsupportedEncodingException {
		// valid
		String words=request.getParameter("words");
		if(StringUtils.isNotBlank(words)){
			Cookie namecookie = new Cookie("searchwords",java.net.URLEncoder.encode(words));    
			   
			//生命周期        
			namecookie.setMaxAge(60*60*24*365);         
			response.addCookie(namecookie);    
		}else{
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				 for (int i = 0; i < cookies.length; i++){
					 Cookie c = cookies[i]; 
					 if(c.getName().equalsIgnoreCase("searchwords")){
						 words = java.net.URLDecoder.decode(c.getValue());
						 break;
					 } 
				 }
			} 
		}
		String columnType=request.getParameter("columnType");
		if(StringUtils.isBlank(columnType)){
			columnType="";
		}
		String strPage="1";
		String[] param=search.split("-");
		if(param!=null && param.length>1){
			strPage=param[1];
		}
		//columnType=columnType.split("-")[0];
		//model.addAttribute("productCloumn", columnType.split("-")[0] );
		String searchWords=words;
		int total = productService.getQueryNumByCategory(searchWords, columnType);
		Page page = buildPageCommand(request, strPage);
		try {
			List<Product> listProducts = productService
					.getAllQuerysByCategoryByPage(columnType,searchWords,page);
			model.addAttribute("cloumnProducts", listProducts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//page
		
		page.setTotal(total);
		model.addAttribute("page", page);
		model.addAttribute("title", searchWords);

		model.addAttribute("words", request.getParameter("words"));

		return "product/search";
	}
	@RequestMapping(value="/about.html")
	public String aboutForHandle(){
		return "common/about";
	}
	@RequestMapping(value="/contact.html")
	public String contactForHandle(){
		return "common/contact";
	}
	@RequestMapping(value="/process.html")
	public String processForHandle(){
		return "common/process";
	}
	private boolean checkInvalidProduct(Product p) {
		return p != null && StringUtils.isNotBlank(p.getName());
	}
	}
