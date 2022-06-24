package com.jingtong.platform.product.dao;

import java.util.List;

import com.jingtong.platform.product.pojo.Product;


public interface IProductDao {
	/**
	 * 获取产品列表数
	 * @param c
	 * @return
	 */
	public int getProductListCount(Product p);
	/**
	 * 获取产品信息列表
	 * @param c
	 * @return
	 */
	public List<Product> getProductList(Product p);
	/**
	 * 获取产品列表数
	 * @param c
	 * @return
	 */
	public int getDRProductListCount(Product p);
	/**
	 * 获取产品信息列表
	 * @param c
	 * @return
	 */
	public List<Product> getDRProductList(Product p);
	/**
	 * 根据ID获取产品信息
	 * @param c
	 * @return
	 */
	public Product getProductById(Product p);
	/**
	 * 产品信息新增
	 * @param p
	 * @return
	 */
	public long createProduct(Product p);
	/**
	 * 修改产品信息
	 * @param p
	 * @return
	 */
	public int updateProduct(Product p);
	/**
	 * 删除产品信息(逻辑删除)
	 * @param p
	 * @return
	 */
	public int deleteProduct(Product p);
	List<Product> getProductListNoPage(Product p);
	public int getQuoteProductListCount(Product p);
	public List<Product> getQuoteProductList(Product p);
	public List<Product> getDRQuoteProductListNoPage(Product p);

}
