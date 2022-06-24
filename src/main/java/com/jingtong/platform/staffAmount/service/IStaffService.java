package com.jingtong.platform.staffAmount.service;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.staffAmount.pojo.StaffAmount;
import com.jingtong.platform.station.pojo.Station;
import java.util.List;

public interface IStaffService {
  public static final String SUCCESS = "success";
  
  public static final String ERROR = "error";
  
  public static final String ERROR_MESSAGE = "error";
  
  public static final String ERROR_INPUT_MESSAGE = "error";
  
  public static final String ERROR_NULL_MESSAGE = "error";
  
  public static final String MENU_REDIRECT_URL = "/menu/menuAction!redirectMenu.jspa?node=";
  
  int getStaffTotal(StaffAmount paramStaffAmount);
  
  List<StaffAmount> getStaffList(StaffAmount paramStaffAmount);
  
  StringResult updateStaffAmounts(StaffAmount paramStaffAmount);
  
  int getStaffAmountCount(StaffAmount paramStaffAmount);
  
  StringResult createStaff(StaffAmount paramStaffAmount);
  
  List<Station> blurSearchStaff(Station paramStation);
  
  int getStaffAmountCountU(StaffAmount paramStaffAmount);
}
