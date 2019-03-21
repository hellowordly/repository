package cn.liuyan.travel.dao;

import cn.liuyan.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    //根据cid查询总记录数
    int findTotalCount(int cid, String rname);
    //根据cid,start.pageSize查询当前页数的集合
    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    Route findById(int rid);
}
