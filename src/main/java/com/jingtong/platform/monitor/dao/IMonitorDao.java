package com.jingtong.platform.monitor.dao;

import com.jingtong.platform.monitor.pojo.HessianDetail;
import java.util.List;

public interface IMonitorDao {
  List<HessianDetail> searchHessian();
}
