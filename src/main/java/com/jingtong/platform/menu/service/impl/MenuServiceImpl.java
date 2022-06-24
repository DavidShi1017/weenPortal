package com.jingtong.platform.menu.service.impl;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.menu.dao.IMenuDao;
import com.jingtong.platform.menu.pojo.Menu;
import com.jingtong.platform.menu.service.IMenuService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class MenuServiceImpl implements IMenuService {
  private static final Log logger = LogFactory.getLog(MenuServiceImpl.class);
  
  private IMenuDao menuDao;
  
  private TransactionTemplate transactionTemplate;
  
  public List<Menu> getMenuTreeList(Menu menu) {
    try {
      return this.menuDao.getMenuTreeList(menu);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
      return null;
    } 
  }
  
  public List<Menu> getMenuTreeList2(Menu menu) {
    try {
      return this.menuDao.getMenuTreeList2(menu);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
      return null;
    } 
  }
  
  public int getMenuCount(Menu menu) {
    try {
      return this.menuDao.getMenuCount(menu);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
      return 0;
    } 
  }
  
  public List<Menu> getMenuList(Menu menu) {
    try {
      return this.menuDao.getMenuList(menu);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
      return null;
    } 
  }
  
  public StringResult createMenu(Menu menu) {
    StringResult result = new StringResult();
    try {
      Long id = this.menuDao.createMenu(menu);
      result.setResult(id.toString());
      result.setCode("success");
    } catch (Exception e) {
      result.setCode("error");
      result.setResult("error");
      logger.error(LogUtil.parserBean(menu), e);
    } 
    return result;
  }
  
  public StringResult updateMenu(Menu menu) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.menuDao.updateMenu(menu);
      if (c == 1)
        result.setCode("success"); 
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
    } 
    return result;
  }
  
  public Menu getMenuById(Long id) {
    try {
      return this.menuDao.getMenuById(id);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(id), e);
      return null;
    } 
  }
  
  public Menu getMenu(Menu menu) {
    try {
      return this.menuDao.getMenu(menu);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
      return null;
    } 
  }
  
  public StringResult deleteMenu(Menu menu) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.menuDao.deleteMenu(menu);
      result.setResult(String.valueOf(c));
      result.setCode("success");
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
    } 
    return result;
  }
  
  public int getSelectedMenu4RoleCount(Menu menu) {
    try {
      return this.menuDao.getSelectedMenu4RoleCount(menu);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
      return 0;
    } 
  }
  
  public List<Menu> getSelectedMenu4RoleList(Menu menu) {
    try {
      return this.menuDao.getSelectedMenu4RoleList(menu);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
      return null;
    } 
  }
  
  public StringResult selectMenu4Role(Menu menu) {
    StringResult result = new StringResult();
    try {
      final String roleId = menu.getRoleId();
      StringBuffer ids = new StringBuffer();
      byte b;
      int i;
      String[] arrayOfString;
      for (i = (arrayOfString = menu.getCodes()).length, b = 0; b < i; ) {
        String code = arrayOfString[b];
        if (!code.equals(" ")) {
          final Long menuId = Long.valueOf(Long.parseLong(code.trim()));
          boolean bool = this.menuDao.checkSelectedMenu4Role(roleId, menuId);
          if (bool)
            continue; 
          Object o = this.transactionTemplate
            .execute(new TransactionCallback() {
                public Object doInTransaction(TransactionStatus ts) {
                  Long roleMenuId;
                  try {
                    roleMenuId = MenuServiceImpl.this.menuDao.selectMenu4Role(
                        roleId, menuId);
                  } catch (Exception e) {
                    MenuServiceImpl.logger.error("roleId:" + roleId + 
                        "menuId:" + menuId, e);
                    ts.setRollbackOnly();
                    return null;
                  } 
                  Long id = menuId;
                  do {
                    try {
                      id = MenuServiceImpl.this.menuDao.getParentMenuId4Role(
                          roleId, id);
                      if (id == null)
                        break; 
                      MenuServiceImpl.this.menuDao.selectMenu4Role(roleId, id);
                    } catch (Exception e) {
                      MenuServiceImpl.logger.error("roleId:" + roleId + 
                          "id:" + id, e);
                      ts.setRollbackOnly();
                      return null;
                    } 
                  } while (id != null);
                  return roleMenuId;
                }
              });
          if (o != null) {
            if (ids.length() != 0)
              ids.append(","); 
            ids.append(o);
          } 
        } 
        result.setResult(ids.toString());
        result.setCode("success");
        continue;
      } 
    } catch (Exception e) {
      result.setCode("error");
      result.setResult("error");
      logger.error(LogUtil.parserBean(menu), e);
    } 
    return result;
  }
  
  public StringResult deleteSelectedMenu4Role(Menu menu) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.menuDao.deleteSelectedMenu4Role(menu);
      result.setResult(String.valueOf(c));
      result.setCode("success");
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
    } 
    return result;
  }
  
  private List<Long> conversion(String roleId, List<Long> ids) {
    List<Long> menuIds = new ArrayList<Long>();
    List<Long> childIds = null;
    for (Long id : ids) {
      menuIds.add(id);
      childIds = this.menuDao.getChildMenuId4Role(roleId, id);
      if (childIds != null && childIds.size() != 0)
        menuIds.addAll(conversion(roleId, childIds)); 
    } 
    return menuIds;
  }
  
  public List<Menu> blurSearchMenu(Menu menu) {
    try {
      return this.menuDao.blurSearchMenu(menu);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
      return null;
    } 
  }
  
  public void createOrUpdateSso(Menu menu) {
    try {
      int r = this.menuDao.searchSsoCount(menu);
      if (r > 0) {
        this.menuDao.updateSso(menu);
      } else {
        this.menuDao.createSso(menu);
      } 
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(menu), e);
    } 
  }
  
  public IMenuDao getMenuDao() {
    return this.menuDao;
  }
  
  public void setMenuDao(IMenuDao menuDao) {
    this.menuDao = menuDao;
  }
  
  public TransactionTemplate getTransactionTemplate() {
    return this.transactionTemplate;
  }
  
  public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
    this.transactionTemplate = transactionTemplate;
  }
}
