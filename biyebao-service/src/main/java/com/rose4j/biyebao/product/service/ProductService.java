package com.rose4j.biyebao.product.service;

import java.util.List;

import com.rose4j.biyebao.domain.News;
import com.rose4j.biyebao.domain.Page;
import com.rose4j.biyebao.domain.Product;

public interface ProductService {
	public List<Product> queryRecommendProducts() throws Exception;

	public List<Product> queryIndexProducts() throws Exception;

	public Product findProductById(Long productId) throws Exception;

	public List<Product> getRelatedProducts(Long productId) throws Exception;

	public List<Product> getLastedProductsByCategory(String categoryType)
			throws Exception;

	public List<Product> getHotProductsByCategory(String categoryType)
			throws Exception;

	public List<Product> getAllProductsByCategory(String categoryType)
			throws Exception;
	public List<Product> getAllProductsByCategoryByPage(String categoryType,Page page)
			throws Exception;
	public int getProductNumByCategory(String categoryType);
	public int getQueryNumByCategory(String words,String categoryType);
	public List<Product> getAllQuerysByCategoryByPage(String categoryType,String words,Page page)
			throws Exception;
	
	public List<News> getLastedNews();

}
