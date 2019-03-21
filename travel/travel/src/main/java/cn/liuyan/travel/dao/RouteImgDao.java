package cn.liuyan.travel.dao;

import cn.liuyan.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    List<RouteImg> findById(int rid);
}
