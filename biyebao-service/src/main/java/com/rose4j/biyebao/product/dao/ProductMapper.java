package com.rose4j.biyebao.product.dao;

import java.util.List;
import java.util.Map;

import com.rose4j.biyebao.domain.Product;

public interface ProductMapper {
	public List<Product> queryIndexProducts(int perNum);

	public List<Product> queryRecommendProducts(int num);

	public Product findProductById(Long productId);

	public List<Product> getRelatedProducts(List productSplidNames);

	public List<Product> getLastedProductsByCategory(Map map);

	public List<Product> getHotProductsByCategory(int perNum);

	public List<Product> getAllProductByCategory(String categoryType);
	public List<Product> getAllProductByCategoryPage(Map map);

	public int getProductNumByCategory(String categoryType);
	public int getQueryNumByCategory(Map map);
	public List<Product> getAllQuerysByCategoryPage(Map map);



}