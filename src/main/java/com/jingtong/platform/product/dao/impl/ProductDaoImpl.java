package com.jingtong.platform.product.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.product.dao.IProductDao;
import com.jingtong.platform.product.pojo.Product;

public class ProductDaoImpl  extends BaseDaoImpl implements IProductDao{

	@Override
	public int getProductListCount(Product p) {
		return (Integer) getSqlMapClientTemplate().queryForObject("product.getProductListCount",p);
	}

	@Override
	public List<Product> getProductList(Product p) {
		return (List<Product>) getSqlMapClientTemplate().queryForList("product.getProductList",p);
	}
	
	@Override
	public int getDRProductListCount(Product p) {
		return (Integer) getSqlMapClientTemplate().queryForObject("product.getDRProductListCount",p);
	}

	@Override
	public List<Product> getDRProductList(Product p) {
		return (List<Product>) getSqlMapClientTemplate().queryForList("product.getDRProductList",p);
	}
	@Override
	public List<Product> getProductListNoPage(Product p) {
		return (List<Product>) getSqlMapClientTemplate().queryForList("product.getProductListNoPage",p);
	}
	@Override
	public Product getProductById(Product p) {
		return (Product) getSqlMapClientTemplate().queryForObject("product.getProductById",p);
	}

	@Override
	public long createProduct(Product p) {
		return (Long) getSqlMapClientTemplate().insert("product.createProduct",p);
	}

	@Override
	public int updateProduct(Product p) {
		return (Integer) getSqlMapClientTemplate().update("product.updateProduct",p);
	}

	@Override
	public int deleteProduct(Product p) {
		return (Integer) getSqlMapClientTemplate().update("product.deleteProduct",p);
	}

	@Override
	public int getQuoteProductListCount(Product p) {
		return (Integer) getSqlMapClientTemplate().queryForObject("product.getQuoteProductListCount",p);
	}

	@Override
	public List<Product> getQuoteProductList(Product p) {
		return (List<Product>) getSqlMapClientTemplate().queryForList("product.getQuoteProductList",p);
	}

	@Override
	public List<Product> getDRQuoteProductListNoPage(Product p) {
		return (List<Product>) getSqlMapClientTemplate().queryForList("product.getDRQuoteProductListNoPage",p);
	}

}
