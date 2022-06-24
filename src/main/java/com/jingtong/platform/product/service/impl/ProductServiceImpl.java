package com.jingtong.platform.product.service.impl;

import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.product.dao.IProductDao;
import com.jingtong.platform.product.pojo.Product;
import com.jingtong.platform.product.service.IProductService;

public class ProductServiceImpl implements IProductService{
	private IProductDao productDao;
	private TransactionTemplate transactionTemplate;
	@Override
	public int getProductListCount(Product p) {
		try {
			return productDao.getProductListCount(p);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Product> getProductList(Product p) {
		try {
			return productDao.getProductList(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int getDRProductListCount(Product p) {
		try {
			return productDao.getDRProductListCount(p);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Product> getDRProductList(Product p) {
		try {
			return productDao.getDRProductList(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Product> getProductListNoPage(Product p) {
		try {
			return productDao.getProductListNoPage(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Product getProductById(Product p) {
		try {
			return productDao.getProductById(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long createProduct(Product p) {
		try {
			return productDao.createProduct(p);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateProduct(Product p) {
		try {
			return productDao.updateProduct(p);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteProduct(Product p) {
		try {
			return productDao.deleteProduct(p);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public IProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public int getQuoteProductListCount(Product p) {
		try {
			return productDao.getQuoteProductListCount(p);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Product> getQuoteProductList(Product p) {
		try {
			return productDao.getQuoteProductList(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Product> getDRQuoteProductListNoPage(Product p) {
		try {
			return productDao.getDRQuoteProductListNoPage(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
