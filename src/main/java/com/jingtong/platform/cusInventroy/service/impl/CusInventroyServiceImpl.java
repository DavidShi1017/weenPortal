package com.jingtong.platform.cusInventroy.service.impl;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.cusInventroy.dao.ICusInventroyDao;
import com.jingtong.platform.cusInventroy.pojo.CusInventroy;
import com.jingtong.platform.cusInventroy.service.ICusInventroyService;
import com.jingtong.platform.product.pojo.Product;

/**
 * @author cl
 * @createDate 2016-6-16
 * 
 */
public class CusInventroyServiceImpl implements ICusInventroyService {
	private ICusInventroyDao cusInventroyDao;

	@Override
	public List<CusInventroy> searchCusInventroyList(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyList(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCusInventroyListCount(CusInventroy c) {
		try {
			return cusInventroyDao.getCusInventroyListCount(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public CusInventroy getCusInventroyById(CusInventroy c) {
		try {
			return cusInventroyDao.getCusInventroyById(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ICusInventroyDao getCusInventroyDao() {
		return cusInventroyDao;
	}

	public void setCusInventroyDao(ICusInventroyDao cusInventroyDao) {
		this.cusInventroyDao = cusInventroyDao;
	}

	@Override
	public long createCusInventroy(CusInventroy c) {
		try {
			return cusInventroyDao.createCusInventroy(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateCusInventroy(CusInventroy c) {
		try {
			return cusInventroyDao.updateCusInventroy(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateCusInventroyStatus(CusInventroy c) {
		try {
			return cusInventroyDao.updateCusInventroyStatus(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<CusInventroy> searchCusInventroyListNoPage(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyListNoPage(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int searchProductCount(CusInventroy c) {
		try {
			return cusInventroyDao.searchProductCount(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public com.jingtong.platform.product.pojo.Product getProductInfo(CusInventroy c) {
		try {
			return cusInventroyDao.getProductInfo(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int searchCustomerCount(CusInventroy c) {
		try {
			return cusInventroyDao.searchCustomerCount(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String searchCustomerName(CusInventroy c) {
		try {
			return cusInventroyDao.searchCustomerName(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CusInventroy> searchCusInventroyListForEDI(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyListForEDI(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCusInventroyListCountForEDI(CusInventroy c) {
		try {
			return cusInventroyDao.getCusInventroyListCountForEDI(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long getFileId() {
		try {
			return cusInventroyDao.getFileId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getCusInventroyDetailListCount(CusInventroy c) {
		try {
			return cusInventroyDao.getCusInventroyDetailListCount(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getCusInventroyDetailListCountForError(CusInventroy c) {
		try {
			return cusInventroyDao.getCusInventroyDetailListCountForError(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<CusInventroy> searchCusInventroyListById(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyListById(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCusInventroyListCountById(CusInventroy c) {
		try {
			return cusInventroyDao.getCusInventroyListCountById(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<CusInventroy> searchCusInventroyListByIdForOne(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyListByIdForOne(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int searchCusInventroyFileIdCount(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyFileIdCount(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Integer> searchCusInventroyFileId(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyFileId(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CusInventroy> searchCusInventroyListForB(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyListForB(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCusInventroyListCountForB(CusInventroy c) {
		try {
			return cusInventroyDao.getCusInventroyListCountForB(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateCusInventroyByFileId(CusInventroy c) {
		try {
			return cusInventroyDao.updateCusInventroyByFileId(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public List<CusInventroy> searchCusInventroyListForBAll(CusInventroy c) {
		try {
			return cusInventroyDao.searchCusInventroyListForBAll(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CusInventroy getDistiName(CusInventroy c) {
		try {
			return cusInventroyDao.getDistiName(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int resetCusInventroy(CusInventroy c) {
		try {
			return cusInventroyDao.resetCusInventroy(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int approvedCusInventroy(CusInventroy c) {
		try {
			return cusInventroyDao.approvedCusInventroy(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int rejectCusInventroy(CusInventroy c) {
		try {
			return cusInventroyDao.rejectCusInventroy(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	////1未检查 0失败 3成功
	public String checkEDI() throws Exception {
		String Message;
		CusInventroy c = new CusInventroy();
		int num = searchCusInventroyFileIdCount(c);
		if (!"".equals(num) && num >= 1) {
			List<Integer> idList = searchCusInventroyFileId(c);
			for (Integer fileId : idList) {
				c.setFile_id(fileId);
				int n = getCusInventroyListCountForEDI(c);
				if (n >= 1) {
					List<CusInventroy> cList = searchCusInventroyListForEDI(c);
					System.out.println(cList.size());
					for (CusInventroy cus : cList) {
						String message0 = "";
						String message1 = "";
						String message2 = "";
						String message3 = "";
						String message6 = "";
						String message7 = "";
						String message8 = "";
						String message9 = "";
						String message10 = "";

						// 处理disti_name

						try {

							if ("9".equals(cus.getStatus()) && "2".equals(cus.getData_from())) {

								//cus.setCus_name(getDistiName(cus).getDisti_name());
							}

						} catch (Exception e) {
							cus.setCus_name(cus.getCus_name());
						}

						if (cus.getSales_org() != null) {
							if (!"HK10".equals(cus.getSales_org())) {
								message0 = "Sales Org  Must be HK10! ";
							} else {
								message0 = "";
							}
						} else {
							message0 = "Sales Org is empty! ";
						}

						

						
						if (cus.getSt_party() != null) {

							// 1 Customer not found
							CusInventroy cusInventroy = new CusInventroy();
							cusInventroy.setSt_party(cus.getSt_party());
							int total = searchCustomerCount(cusInventroy);
							if (total <= 0) {
								message1 = "Customer not found! ";
							}
						} else {
							message1 = "St Party is empty! Customer not found! Bad Customer Name! ";
						}

						if (cus.getCus_name() != null) {
							message2 = "";
						} else {
							message2 = "Customer Name is empty! ";
						}

						if (cus.getSender_date() != null) {

							// 3 Bad ship date
							String stri = cus.getSender_date();
							Format f = new SimpleDateFormat("yyyyMMdd");
							try {
								Date d = (Date) f.parseObject(stri);

								String tmp = f.format(d);
								System.out.println(stri + ":" + tmp.equals(stri));
								boolean flag = tmp.equals(stri);
								if (flag == false) {
									message3 = "Invalid ship date! ";
								}
							} catch (Exception e) {
								message3 = "Invalid ship date! ";
							}

							// 3 Invalid ship date
							// 获取当前日期
							if (!"Invalid ship date! ".equals(message3)) {
								try {
									Date date = new Date();
									SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
									String DateStr1 = sdf1.format(date);
									String DateStr2 = cus.getSender_date();
									DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
									Date dateTime1 = dateFormat.parse(DateStr1);
									Date dateTime2 = dateFormat.parse(DateStr2);
									int b = dateTime1.compareTo(dateTime2);
									if (b < 0) {
										message3 = "Bad ship date! ";
									}
								} catch (Exception e) {
									message3 = "Bad ship date! ";
								}
							}

						} else {
							message3 = "Sender Date is empty! Bad ship date! Invalid ship date! ";
						}

						if (cus.getPart_name() != null) {

							// 6 Bad Book Part!
							CusInventroy cusInventroy = new CusInventroy();
							cusInventroy.setPart_name(cus.getPart_name().toUpperCase());
							int total = searchProductCount(cusInventroy);
							if (total <= 0) {
								message6 = "Bad Book part! ";
							}
						} else {
							message6 = "Part Name is empty! Bad Book part! ";
						}

						if (cus.getQuantity() != null) {
							// 7 Invalid quantity
							try {
								String str = cus.getQuantity();
								int num1 = Integer.valueOf(str);// 把字符串强制转换为数字
								if (Long.valueOf(cus.getQuantity()) < 0) {
									message7 = "Bad quantity! ";
								}
								;// 如果是数字，//7 Bad quantity
							} catch (Exception e) {
								message7 = "Invalid quantity! ";// 如果抛出异常，返回False
							}
						} else {
							message7 = " Quanlity is empty! Invalid quantity! Bad quantity! ";
						}

						if (cus.getPrice_basis() != null) {
							try {
								String str = cus.getPrice_basis();
								int num1 = Integer.valueOf(str);// 把字符串强制转换为数字
							} catch (Exception e) {
								message8 = "Invalid Price Basis! ";// 如果抛出异常，返回False
							}
						} else {
							message8 = "Price Basis is empty! Invalid Price Basis! ";
						}

						if (cus.getCurrency() != null) {
							// 9 Invalid currency
							if (!"USD".equals(cus.getCurrency()) && !"EUR".equals(cus.getCurrency())) {
								message9 = "Invalid currency! ";
							}
						} else {
							message9 = "Currency is empty! Invalid currency! ";
						}

						if (cus.getPrice() != null) {
							try {
								if (Double.valueOf(cus.getPrice()) < 0) {
									message10 = "Bad Price! ";
								}
							} catch (Exception e) {
								message10 = "Bad Price! ";
							}
						} else {
							message10 = "Price is empty! Bad Price! ";
						}

						String reMessage = "";
						reMessage = message0 + message1 + message2 + message3 + message6 + message7 + message8
								+ message9 + message10;

						if (!"".equals(reMessage)) {
							cus.setTips(reMessage);
							cus.setStatus("1");
							System.out.println(cus.getId());
							updateCusInventroy(cus);
						} else {
							cus.setTips("Success!");
							cus.setStatus("3");
							updateCusInventroy(cus);
						}
					}

				}
				int m = getCusInventroyListCount(c);
				if (m >= 1) {
					int aa = getCusInventroyDetailListCount(c);
					CusInventroy cip = new CusInventroy();
					cip.setStatus("1");
					cip.setFile_id(c.getFile_id());
					int bb = getCusInventroyDetailListCountForError(cip); //1 pending
					
					CusInventroy cir = new CusInventroy();
					cir.setStatus("0");
					cir.setFile_id(c.getFile_id());
					int cc = getCusInventroyDetailListCountForError(cir); //reject 0
					c.setStatus_num(bb + "/" + cc + "/" + aa);
					System.out.println(c.getFile_id());
					System.out.println(c.getStatus_num());
					updateCusInventroyByFileId(c);
				}
			}
		}
		return "";
	}

	@Override
	public void updateFrequencyMarkCusInventroy(CusInventroy c) throws Exception {
		try {
			cusInventroyDao.updateFrequencyMarkCusInventroy(c);
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
	}

	@Override
	public CusInventroy getInvByfileId(CusInventroy c) {
		try {
			return cusInventroyDao.getInvByfileId(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
