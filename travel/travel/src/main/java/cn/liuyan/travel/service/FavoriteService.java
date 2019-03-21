package cn.liuyan.travel.service;

public interface FavoriteService {

    //是否收藏
    public boolean isFavorite(String rid,int uid);

    //添加收藏
    void addFavorite(String ridStr, int uid);
}
