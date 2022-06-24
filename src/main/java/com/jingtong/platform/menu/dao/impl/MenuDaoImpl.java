package com.jingtong.platform.menu.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.menu.dao.IMenuDao;
import com.jingtong.platform.menu.pojo.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDaoImpl extends BaseDaoImpl implements IMenuDao {
  public List<Menu> getMenuTreeList(Menu menu) {
    return getSqlMapClientTemplate().queryForList(
        "menu.getMenuTreeList", menu);
  }
  
  public List<Menu> getMenuTreeList2(Menu menu) {
    return getSqlMapClientTemplate().queryForList(
        "menu.getMenuTreeList2", menu);
  }
  
  public int getMenuCount(Menu menu) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "menu.getMenuCount", menu)).intValue();
  }
  
  public List<Menu> getMenuList(Menu menu) {
    return getSqlMapClientTemplate().queryForList(
        "menu.getMenuList", menu);
  }
  
  public Long createMenu(Menu menu) {
    return (Long)getSqlMapClientTemplate().insert("menu.createMenu", menu);
  }
  
  public int updateMenu(Menu menu) {
    return getSqlMapClientTemplate().update("menu.updateMenu", menu);
  }
  
  public Menu getMenuById(Long id) {
    return (Menu)getSqlMapClientTemplate().queryForObject(
        "menu.getMenuById", id);
  }
  
  public Menu getMenu(Menu menu) {
    return (Menu)getSqlMapClientTemplate().queryForObject("menu.getMenu", 
        menu);
  }
  
  public int deleteMenu(Menu menu) {
    return getSqlMapClientTemplate().delete("menu.deleteMenu", menu);
  }
  
  public int getSelectedMenu4RoleCount(Menu menu) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "menu.getSelectedMenu4RoleCount", menu)).intValue();
  }
  
  public List<Menu> getSelectedMenu4RoleList(Menu menu) {
    return getSqlMapClientTemplate().queryForList(
        "menu.getSelectedMenu4RoleList", menu);
  }
  
  public int deleteSelectedMenu4Role(Menu menu) {
    return getSqlMapClientTemplate().delete("menu.deleteSelectedMenu4Role", 
        menu);
  }
  
  public boolean checkSelectedMenu4Role(String roleId, Long menuId) {
    Map<String, Object> m = new HashMap<String, Object>();
    m.put("roleId", roleId);
    m.put("menuId", menuId);
    int c = ((Integer)getSqlMapClientTemplate().queryForObject(
        "menu.checkSelectedMenu4Role", m)).intValue();
    return !(c == 0);
  }
  
  public Long selectMenu4Role(String roleId, Long menuId) {
    Map<String, Object> m = new HashMap<String, Object>();
    m.put("roleId", roleId);
    m.put("menuId", menuId);
    return (Long)getSqlMapClientTemplate().insert("menu.selectMenu4Role", 
        m);
  }
  
  public Long getParentMenuId4Role(String roleId, Long menuId) {
    Map<String, Object> m = new HashMap<String, Object>();
    m.put("roleId", roleId);
    m.put("menuId", menuId);
    return (Long)getSqlMapClientTemplate().queryForObject(
        "menu.getParentMenuId4Role", m);
  }
  
  public List<Long> getChildMenuId4Role(String roleId, Long menuId) {
    Map<String, Object> m = new HashMap<String, Object>();
    m.put("roleId", roleId);
    m.put("menuId", menuId);
    return getSqlMapClientTemplate().queryForList(
        "menu.getChildMenuId4Role", m);
  }
  
  public List<Menu> blurSearchMenu(Menu menu) {
    return getSqlMapClientTemplate().queryForList(
        "menu.blurSearchMenu", menu);
  }
  
  public int searchSsoCount(Menu menu) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "menu.searchSsoCount", menu)).intValue();
  }
  
  public void createSso(Menu menu) {
    getSqlMapClientTemplate().insert("menu.createSso", menu);
  }
  
  public void updateSso(Menu menu) {
    getSqlMapClientTemplate().update("menu.updateSso", menu);
  }
}
