package com.jingtong.platform.menu.service;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.menu.pojo.Menu;
import java.util.List;

public interface IMenuService {
  public static final String SUCCESS = "success";
  
  public static final String ERROR = "error";
  
  public static final String ERROR_MESSAGE = "error";
  
  public static final String ERROR_INPUT_MESSAGE = "error";
  
  public static final String ERROR_NULL_MESSAGE = "error";
  
  public static final String MENU_REDIRECT_URL = "/menuAction!redirectMenu.jspa?node=";
  
  List<Menu> getMenuTreeList(Menu paramMenu);
  
  List<Menu> getMenuTreeList2(Menu paramMenu);
  
  int getMenuCount(Menu paramMenu);
  
  List<Menu> getMenuList(Menu paramMenu);
  
  StringResult createMenu(Menu paramMenu);
  
  StringResult updateMenu(Menu paramMenu);
  
  Menu getMenuById(Long paramLong);
  
  Menu getMenu(Menu paramMenu);
  
  StringResult deleteMenu(Menu paramMenu);
  
  int getSelectedMenu4RoleCount(Menu paramMenu);
  
  List<Menu> getSelectedMenu4RoleList(Menu paramMenu);
  
  StringResult selectMenu4Role(Menu paramMenu);
  
  StringResult deleteSelectedMenu4Role(Menu paramMenu);
  
  List<Menu> blurSearchMenu(Menu paramMenu);
  
  void createOrUpdateSso(Menu paramMenu);
}
