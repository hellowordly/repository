package cn.liuyan.travel.service;

import cn.liuyan.travel.domain.PageBean;
import cn.liuyan.travel.domain.Route;

//路线
public interface RouteService {

    /**
     * 根据类别查询路线
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    Route findOne(int rid);
}
