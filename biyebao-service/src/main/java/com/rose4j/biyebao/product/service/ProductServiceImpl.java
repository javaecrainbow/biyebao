package com.rose4j.biyebao.product.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rose4j.biyebao.domain.News;
import com.rose4j.biyebao.domain.Page;
import com.rose4j.biyebao.domain.Product;
import com.rose4j.biyebao.product.dao.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	private static List<String> except=new ArrayList<String>();
	static{
		except.add("管理");
		except.add("系统");
		except.add("设计");
		except.add("毕业");
		except.add("论坛");
		except.add("网站");
	}
	@Autowired
	public ProductMapper productmapper;

	@Override
	public List<Product> queryIndexProducts() throws Exception {
		int perNum = 9;
		List<Product> indexProducts = productmapper.queryIndexProducts(perNum);

		return indexProducts;
	}

	@Override
	public List<Product> queryRecommendProducts() throws Exception {
		int num = 11;
		List<Product> recommendProducts = productmapper
				.queryRecommendProducts(num);

		return recommendProducts;
	}

	@Override
	public Product findProductById(Long productId) throws Exception {
		Product product = productmapper.findProductById(productId);
		return product;
	}

	@Override
	public List<Product> getRelatedProducts(Long productId) throws Exception {
		int num = 9;
		Product product=findProductById(productId);
		if(product==null){
			return null;
		}
		List<String> spliderNames = getSpliders(product.getName());
		List<Product> recommendProducts = productmapper.getRelatedProducts(
				spliderNames);

		return recommendProducts;
	}

	private List<String> getSpliders(String name) {
		name=org.springframework.util.StringUtils.trimAllWhitespace(name);
		List<String> spliderNames=new ArrayList<String>();

		if(StringUtils.isBlank(name)){
			return spliderNames;
		}
		if(name.length()>2){
			for(int i=0;i<name.length()/2;i++){
					String words=name.substring(i,i+2);
					if(!except.contains(words)){
					spliderNames.add(words);
					}
			}
		}else{
			spliderNames.add(name);
		}
		return spliderNames;
	}

	@Override
	public List<Product> getLastedProductsByCategory(String categoryType) {
		Map map = new HashMap();
		map.put("categoryType", categoryType.toLowerCase());
		map.put("perNum", new Integer(9));
		List<Product> lastedProducts = productmapper
				.getLastedProductsByCategory(map);

		return lastedProducts;
	}

	@Override
	public List<Product> getHotProductsByCategory(String categoryType)
			throws Exception {
		int perNum=9;
		List<Product> hotProducts = productmapper.getHotProductsByCategory(
				perNum);

		return hotProducts;
	}

	@Override
	public List<Product> getAllProductsByCategory(String categoryType)
			throws Exception {
		if (StringUtils.isEmpty(categoryType)) {
			return new ArrayList<Product>();
		}
		List<Product> allProductProducts = productmapper
				.getAllProductByCategory(categoryType.toLowerCase());

		return allProductProducts;
	}

	@Override
	public int getProductNumByCategory(String categoryType) {
		int result =0;
			try {
				result = productmapper.getProductNumByCategory(categoryType.toLowerCase());
			} catch (Exception e) {
				//log
			}
		return result;
	}

	@Override
	public List<Product> getAllProductsByCategoryByPage(String categoryType,
			Page page) throws Exception {
		if (StringUtils.isEmpty(categoryType)) {
			return new ArrayList<Product>();
		}
		Map map = new HashMap();
		map.put("categoryType", categoryType.toLowerCase());
		map.put("start", (page.getCurrentPage()-1)*page.SIZE);
		map.put("end", page.SIZE);
		List<Product> allProductProducts = productmapper
				.getAllProductByCategoryPage(map);
		return allProductProducts;
	}

	@Override
	public int getQueryNumByCategory(String words, String categoryType) {
		int result =0;
		try {
			Map map = new HashMap();
			if(StringUtils.isNotBlank(categoryType)){
			map.put("categoryType", categoryType.toLowerCase());
			}
			List<String> spliderNames = getSpliders(words);

			map.put("spliderNames", spliderNames);
			result = productmapper.getQueryNumByCategory(map);
		} catch (Exception e) {
			//log
			System.out.println(e.getMessage());
		}
	return result;

	}

	@Override
	public List<Product> getAllQuerysByCategoryByPage(String categoryType,
			String words, Page page) throws Exception {
		Map map = new HashMap();
		if(StringUtils.isNotBlank(categoryType)){
			map.put("categoryType", categoryType.toLowerCase());
			}
		List<String> spliderNames = getSpliders(words);
		map.put("spliderNames", spliderNames);
		map.put("start", (page.getCurrentPage()-1)*page.SIZE);
		map.put("end", page.SIZE);
		List<Product> allProductProducts = productmapper
				.getAllQuerysByCategoryPage(map);
		return allProductProducts;
	}

	@Override
	public List<News> getLastedNews() {
		 Connection conn = null;
		 ResultSet resultSets=null;
		 Statement stmt=null;
	        String sql;
	        String url = "jdbc:mysql://121.41.112.38:3306/rose4j_bbs?"
	                + "user=root&password=rose4j_db&useUnicode=true&characterEncoding=UTF8";
            List<News> newsList = new ArrayList<News>();
	        try {
	            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
	            conn = DriverManager.getConnection(url);
	            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
	            stmt= conn.createStatement();
	            sql = "select topic_id,topic_title from jforum_topics where forum_id=17 order by topic_time desc limit 0,10";
	            resultSets= stmt.executeQuery(sql);
	            while(resultSets.next()){
	            	String title = resultSets.getString("topic_title");
	            	String  id = String.valueOf(resultSets.getInt("topic_id"));
	            	News news=new News();
	            	news.setTitle(title);
	            	news.setId(id);
	            	newsList.add(news);
	            }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally{
	        	  if(resultSets != null){   // 关闭记录集   
	        	        try{   
	        	        	resultSets.close() ;   
	        	        }catch(SQLException e){   
	        	            e.printStackTrace() ;   
	        	        }   
	        	          }   
	        	          if(stmt != null){   // 关闭声明   
	        	        try{   
	        	            stmt.close() ;   
	        	        }catch(SQLException e){   
	        	            e.printStackTrace() ;   
	        	        }   
	        	          }   
	        	          if(conn != null){  // 关闭连接对象   
	        	         try{   
	        	            conn.close() ;   
	        	         }catch(SQLException e){   
	        	            e.printStackTrace() ;   
	        	         }   
	        	          }  
	        	}
	        return newsList;
	        }

}
