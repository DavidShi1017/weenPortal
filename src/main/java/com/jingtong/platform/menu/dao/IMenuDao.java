package com.jingtong.platform.menu.dao;

import com.jingtong.platform.menu.pojo.Menu;
import java.util.List;

public interface IMenuDao {
  List<Menu> getMenuTreeList(Menu paramMenu);
  
  List<Menu> getMenuTreeList2(Menu paramMenu);
  
  int getMenuCount(Menu paramMenu);
  
  List<Menu> getMenuList(Menu paramMenu);
  
  Long createMenu(Menu paramMenu);
  
  int updateMenu(Menu paramMenu);
  
  Menu getMenuById(Long paramLong);
  
  Menu getMenu(Menu paramMenu);
  
  int deleteMenu(Menu paramMenu);
  
  int getSelectedMenu4RoleCount(Menu paramMenu);
  
  List<Menu> getSelectedMenu4RoleList(Menu paramMenu);
  
  int deleteSelectedMenu4Role(Menu paramMenu);
  
  boolean checkSelectedMenu4Role(String paramString, Long paramLong);
  
  Long selectMenu4Role(String paramString, Long paramLong);
  
  Long getParentMenuId4Role(String paramString, Long paramLong);
  
  List<Long> getChildMenuId4Role(String paramString, Long paramLong);
  
  List<Menu> blurSearchMenu(Menu paramMenu);
  
  int searchSsoCount(Menu paramMenu);
  
  void createSso(Menu paramMenu);
  
  void updateSso(Menu paramMenu);
}
