package com.jingtong.platform.cusInventroy.service;

import java.util.List;

import com.jingtong.platform.cusInventroy.pojo.CusInventroy;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;

/** 
 * @author cl 
 * @createDate 2016-6-16
 * 
 */
public interface ICusInventroyService {
	/**
	 * 查询CusInventroy信息
	 * 
	 * @return
	 */
	public List<CusInventroy> searchCusInventroyList(CusInventroy c);

	/**
	 * 查询CusInventroy信息总行数
	 * 
	 * @return
	 */
	public int getCusInventroyListCount(CusInventroy c);
	
	public CusInventroy getCusInventroyById(CusInventroy c);
	
	
	
	
	public long createCusInventroy(CusInventroy c);

	public int updateCusInventroy(CusInventroy c);//根据ID，上传时
	public int updateCusInventroyStatus(CusInventroy c);//根据ID，上传时
	public int updateCusInventroyByFileId(CusInventroy c);//根据fileID,EDI检查时
	
	
	public List<CusInventroy> searchCusInventroyListNoPage(CusInventroy c);
	
	 public int searchProductCount(CusInventroy c);
	 public int searchCustomerCount(CusInventroy c);
	 public String searchCustomerName(CusInventroy c);
	 public CusInventroy getDistiName(CusInventroy c);
	 
	 //获取文件ID
	 public long getFileId();
	 //检查EDI
	 public List<CusInventroy> searchCusInventroyListForEDI(CusInventroy c);
	 public int getCusInventroyListCountForEDI(CusInventroy c);
	 
	 
	 public int getCusInventroyDetailListCount(CusInventroy c);
	 public int getCusInventroyDetailListCountForError(CusInventroy c);
	 
	 public List<CusInventroy> searchCusInventroyListById(CusInventroy c);
	 public int getCusInventroyListCountById(CusInventroy c);
	 public List<CusInventroy> searchCusInventroyListByIdForOne(CusInventroy c);
	 
	 //edi检查
	 public int searchCusInventroyFileIdCount(CusInventroy c);
	 public List<Integer> searchCusInventroyFileId(CusInventroy c);
	 //重置状态为9/3/0
	 public int resetCusInventroy(CusInventroy c);
	 public int approvedCusInventroy(CusInventroy c);
	 public int rejectCusInventroy(CusInventroy c);
	 
	//报表
		public List<CusInventroy> searchCusInventroyListForB(CusInventroy c);
		public int getCusInventroyListCountForB(CusInventroy c);
		public List<CusInventroy> searchCusInventroyListForBAll(CusInventroy c);
		
		public String checkEDI() throws Exception;
		
		public void updateFrequencyMarkCusInventroy(CusInventroy c) throws Exception;
		
		public CusInventroy getInvByfileId(CusInventroy c);

		public com.jingtong.platform.product.pojo.Product getProductInfo(CusInventroy c);
		
		
}
