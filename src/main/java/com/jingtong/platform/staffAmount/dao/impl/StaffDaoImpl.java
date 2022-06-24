package com.jingtong.platform.staffAmount.dao.impl;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.staffAmount.dao.IStaffDao;
import com.jingtong.platform.staffAmount.pojo.StaffAmount;
import com.jingtong.platform.station.pojo.Station;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl extends BaseDaoImpl implements IStaffDao {
  public int getStaffTotal(StaffAmount s) {
    return ((Integer)getSqlMapClientTemplate().queryForObject("staffamount.getStaffAmountsCount", s)).intValue();
  }
  
  public List<StaffAmount> getStaffList(StaffAmount s) {
    return getSqlMapClientTemplate().queryForList(
        "staffamount.getStaffAmounts", s);
  }
  
  public int updateStaffAmounts(StaffAmount staffAmount) {
    List<StaffAmount> list = new ArrayList<StaffAmount>();
    list = getSqlMapClientTemplate().queryForList("staffamount.getStaffAmountByPid", staffAmount);
    int y = getSqlMapClientTemplate().update(
        "staffamount.updateStaffAmounts", staffAmount);
    if (y != 0)
      if (staffAmount.getAmount() == null) {
        List<StaffAmount> liststa = new ArrayList<StaffAmount>();
        liststa = getSqlMapClientTemplate().queryForList("staffamount.getStaffAmountByPid", staffAmount);
        for (int i = 0; i < liststa.size(); i++) {
          Station station = new Station();
          station.setStationId(((StaffAmount)liststa.get(i)).getStationId());
          station.setOrgId(((StaffAmount)liststa.get(i)).getOrgId());
          getSqlMapClientTemplate().delete("station.deleteStationUser", station);
        } 
      } else {
        long num = ((StaffAmount)list.get(0)).getAmount().longValue() - staffAmount.getAmount().longValue();
        if (num > 0L) {
          Station st = new Station();
          st.setStationId(((StaffAmount)list.get(0)).getStationId());
          st.setOrgId(((StaffAmount)list.get(0)).getOrgId());
          List<Station> list_sta = new ArrayList<Station>();
          list_sta = getSqlMapClientTemplate().queryForList("station.getDeleteStationCount", st);
          int listStation = list_sta.size();
          if (num <= listStation) {
            for (int i = 0; i < num; i++) {
              Station station = new Station();
              station.setCompId(((Station)list_sta.get(i)).getCompId());
              getSqlMapClientTemplate().delete("station.deleteStationUserById", station);
            } 
          } else {
            for (int i = 0; i < listStation; i++) {
              Station station = new Station();
              station.setCompId(((Station)list_sta.get(i)).getCompId());
              getSqlMapClientTemplate().delete("station.deleteStationUserById", station);
            } 
          } 
        } else {
          for (int i = 0; i < -num; i++) {
            AllUsers allUsers = new AllUsers();
            allUsers.setRoleIds(((StaffAmount)list.get(0)).getStationId());
            allUsers.setUserId("");
            allUsers.setOrgId(((StaffAmount)list.get(0)).getOrgId().toString());
            getSqlMapClientTemplate().insert("station.createStationUser", allUsers);
          } 
        } 
      }  
    return y;
  }
  
  public int getStaffAmountCount(StaffAmount staffAmount) {
    return ((Integer)getSqlMapClientTemplate().queryForObject("staffamount.getStaffAmountCount", staffAmount)).intValue();
  }
  
  public Long createStaff(StaffAmount staffAmount) {
    long l = ((Long)getSqlMapClientTemplate().insert("staffamount.createStaffAmount", staffAmount)).longValue();
    if (l != 0L)
      for (int i = 0; i < staffAmount.getAmount().longValue(); i++) {
        AllUsers allUsers = new AllUsers();
        allUsers.setRoleIds(staffAmount.getStationId());
        allUsers.setUserId("");
        allUsers.setOrgId(staffAmount.getOrgId().toString().trim());
        getSqlMapClientTemplate().insert("station.createStationUser", allUsers);
      }  
    return Long.valueOf(l);
  }
  
  public List<Station> blurSearchStaff(Station station) {
    return getSqlMapClientTemplate().queryForList("staffamount.blurSearchStaff", station);
  }
  
  public int getStaffAmountCountU(StaffAmount staffAmount) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "staffamount.getStaffAmountCountU", staffAmount)).intValue();
  }
}
