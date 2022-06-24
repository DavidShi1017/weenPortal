package com.jingtong.platform.cusInventroy.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.cusInventroy.dao.ICusInventroyDao;
import com.jingtong.platform.cusInventroy.pojo.CusInventroy;
import com.jingtong.platform.product.pojo.Product;

/** 
 * @author cl 
 * @createDate 2016-6-16
 * 
 */
public class CusInventroyDaoImpl extends BaseDaoImpl implements ICusInventroyDao {

	@Override
	public List<CusInventroy> searchCusInventroyList(CusInventroy c) {
		return getSqlMapClientTemplate().queryForList("cusInventroy.searchCusInventroyList",c);
	}

	@Override
	public int getCusInventroyListCount(CusInventroy c) {
		return (Integer)getSqlMapClientTemplate().queryForObject("cusInventroy.getCusInventroyListCount",c);
	}

	@Override
	public CusInventroy getCusInventroyById(CusInventroy c) {
		return (CusInventroy)getSqlMapClientTemplate().queryForObject("cusInventroy.getCusInventroyById",c);
	}

	@Override
	public long createCusInventroy(CusInventroy c) {
		   getSqlMapClientTemplate().insert("cusInventroy.createCusInventroy",c);
		   return 1;
	}

	@Override
	public int updateCusInventroy(CusInventroy c) {
		return (int) getSqlMapClientTemplate().update("cusInventroy.updateCusInventroy",c);
	}
	
	@Override
	public int updateCusInventroyStatus(CusInventroy c) {
		return (int) getSqlMapClientTemplate().update("cusInventroy.updateCusInventroyStatus",c);
	}

	@Override
	public List<CusInventroy> searchCusInventroyListNoPage(CusInventroy c) {
		return getSqlMapClientTemplate().queryForList("cusInventroy.searchCusInventroyListNoPage",c);
	}

	@Override
	public int searchProductCount(CusInventroy c) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("cusInventroy.searchProductCount",c);
	}
	
	@Override
	public Product getProductInfo(CusInventroy c) {
		return (Product)getSqlMapClientTemplate().queryForObject("cusInventroy.getProductInfo",c);
	}
	
	@Override
	public int searchCustomerCount(CusInventroy c) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("cusInventroy.searchCustomerCount",c);
	}
	
	@Override
	public String searchCustomerName(CusInventroy c) {
		return (String)getSqlMapClientTemplate().queryForObject("cusInventroy.searchCustomerName",c);
	}

	@Override
	public List<CusInventroy> searchCusInventroyListForEDI(CusInventroy c) {
		return getSqlMapClientTemplate().queryForList("cusInventroy.searchCusInventroyListForEDI",c);
	}

	@Override
	public int getCusInventroyListCountForEDI(CusInventroy c) {
		return (Integer)getSqlMapClientTemplate().queryForObject("cusInventroy.getCusInventroyListCountForEDI",c);
	}

	@Override
	public Long getFileId() {
		return  (Long) getSqlMapClientTemplate().queryForObject("cusInventroy.getFileId");
	}

	@Override
	public int getCusInventroyDetailListCount(CusInventroy c) {
		return (Integer)getSqlMapClientTemplate().queryForObject("cusInventroy.getCusInventroyDetailListCount",c);
	}

	@Override
	public int getCusInventroyDetailListCountForError(CusInventroy c) {
		return (Integer)getSqlMapClientTemplate().queryForObject("cusInventroy.getCusInventroyDetailListCountForError",c);
	}

	@Override
	public List<CusInventroy> searchCusInventroyListById(CusInventroy c) {
		return getSqlMapClientTemplate().queryForList("cusInventroy.searchCusInventroyListById",c);
	}

	@Override
	public int getCusInventroyListCountById(CusInventroy c) {
		return (Integer)getSqlMapClientTemplate().queryForObject("cusInventroy.getCusInventroyListCountById",c);
	}
	
	@Override
	public List<CusInventroy> searchCusInventroyListByIdForOne(CusInventroy c) {
		return getSqlMapClientTemplate().queryForList("cusInventroy.searchCusInventroyListByIdForOne",c);
	}

	

	@Override
	public int searchCusInventroyFileIdCount(CusInventroy c) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("cusInventroy.searchCusInventroyFileIdCount",c);
	}

	@Override
	public List<Integer> searchCusInventroyFileId(CusInventroy c) {
		return (List<Integer>)this.getSqlMapClientTemplate().queryForList("cusInventroy.searchCusInventroyFileId",c);
	}

	@Override
	public List<CusInventroy> searchCusInventroyListForB(CusInventroy c) {
		return getSqlMapClientTemplate().queryForList("cusInventroy.searchCusInventroyListForB",c);
	}

	@Override
	public int getCusInventroyListCountForB(CusInventroy c) {
		return (Integer)getSqlMapClientTemplate().queryForObject("cusInventroy.getCusInventroyListCountForB",c);
	}

	@Override
	public int updateCusInventroyByFileId(CusInventroy c) {
		return (int) getSqlMapClientTemplate().update("cusInventroy.updateCusInventroyByFileId",c);
	}

	@Override
	public List<CusInventroy> searchCusInventroyListForBAll(CusInventroy c) {
		return getSqlMapClientTemplate().queryForList("cusInventroy.searchCusInventroyListForBAll",c);
	}

	@Override
	public CusInventroy getDistiName(CusInventroy c) {
		return (CusInventroy)getSqlMapClientTemplate().queryForObject("cusInventroy.getDistiName",c);
	}

	@Override
	public int resetCusInventroy(CusInventroy c) {
		return (int) getSqlMapClientTemplate().update("cusInventroy.resetCusInventroy",c);
	}

	@Override
	public int approvedCusInventroy(CusInventroy c) {
		int i = (int) getSqlMapClientTemplate().update("cusInventroy.approvedCusInventroy",c);
		int m = getCusInventroyListCount(c);
		if (m >= 1) {
			int aa = getCusInventroyDetailListCount(c);
			c.setStatus("1");
			int bb = getCusInventroyDetailListCountForError(c); //pending 1
			c.setStatus("0");
			int cc = getCusInventroyDetailListCountForError(c); //reject 0
			c.setStatus_num(bb + "/" + cc + "/" + aa);
			updateCusInventroyByFileId(c);
		}
		return i;
	}

	@Override
	public int rejectCusInventroy(CusInventroy c) {
		int i = getSqlMapClientTemplate().update("cusInventroy.rejectCusInventroy",c);
		
		int m = getCusInventroyListCount(c);
		if (m >= 1) {
			int aa = getCusInventroyDetailListCount(c);
			c.setStatus("1");
			int bb = getCusInventroyDetailListCountForError(c); //pending 1
			c.setStatus("0");
			int cc = getCusInventroyDetailListCountForError(c); //reject 0
			c.setStatus_num(bb + "/"+ cc+ "/" + aa);
			updateCusInventroyByFileId(c);
		}
		return i;
	}

	@Override
	public void updateFrequencyMarkCusInventroy(CusInventroy c) throws Exception {
		getSqlMapClientTemplate().update("cusInventroy.updateFrequencyMarkCusInventroy",c);
	}

	@Override
	public CusInventroy getInvByfileId(CusInventroy c) {
		return (CusInventroy)getSqlMapClientTemplate().queryForObject("cusInventroy.getInvByfileId",c);
	}


}
