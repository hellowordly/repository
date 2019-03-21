package cn.liuyan.travel.dao;

import cn.liuyan.travel.domain.Favorite;

public interface FavoriteDao {

    //查询收藏对象
    public Favorite findByRidAndUid(int rid,int uid);

    //查询收藏次数
    public int findCountByRid(int rid);

    //添加收藏
    void add(int rid, int uid);
}
