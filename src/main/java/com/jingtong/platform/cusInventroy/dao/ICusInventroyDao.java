package com.jingtong.platform.cusInventroy.dao;

import java.util.List;

import com.jingtong.platform.cusInventroy.pojo.CusInventroy;
import com.jingtong.platform.ecgroup.pojo.GroupInfo;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.product.pojo.Product;

/**
 * @author cl
 * @createDate 2016-6-16
 * 
 */
public interface ICusInventroyDao {
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
	public int updateCusInventroyStatus(CusInventroy c);//根据FileID，上传时,修改status_num
	public int updateCusInventroyByFileId(CusInventroy c);//根据fileID,EDI检查时
	
	
	public List<CusInventroy> searchCusInventroyListNoPage(CusInventroy c);
	
	 public int searchProductCount(CusInventroy c);
	 public int searchCustomerCount(CusInventroy c);
	 public String  searchCustomerName(CusInventroy c);
	 public CusInventroy getDistiName(CusInventroy c);
	 
	 //获取文件ID
	 public Long getFileId();
	 
	 //检查EDI状态为1
	 public List<CusInventroy> searchCusInventroyListForEDI(CusInventroy c);
	 public int getCusInventroyListCountForEDI(CusInventroy c);
	 
	 //获取status_num
	 public int getCusInventroyDetailListCount(CusInventroy c);
	 public int getCusInventroyDetailListCountForError(CusInventroy c);
	 
	 //二级查看页面
	 public List<CusInventroy> searchCusInventroyListById(CusInventroy c);
	 public int getCusInventroyListCountById(CusInventroy c);
	 //单条下载
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
	public void updateFrequencyMarkCusInventroy(CusInventroy c) throws Exception ;
	
	public CusInventroy getInvByfileId(CusInventroy c) ;

	Product getProductInfo(CusInventroy c);
}
