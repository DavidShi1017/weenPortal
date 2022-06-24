package com.jingtong.platform.staffAmount.dao;

import com.jingtong.platform.staffAmount.pojo.StaffAmount;
import com.jingtong.platform.station.pojo.Station;
import java.util.List;

public interface IStaffDao {
  int getStaffTotal(StaffAmount paramStaffAmount);
  
  List<StaffAmount> getStaffList(StaffAmount paramStaffAmount);
  
  int updateStaffAmounts(StaffAmount paramStaffAmount);
  
  int getStaffAmountCount(StaffAmount paramStaffAmount);
  
  Long createStaff(StaffAmount paramStaffAmount);
  
  List<Station> blurSearchStaff(Station paramStation);
  
  int getStaffAmountCountU(StaffAmount paramStaffAmount);
}
