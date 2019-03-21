package cn.liuyan.travel.service.impl;

import cn.liuyan.travel.dao.FavoriteDao;
import cn.liuyan.travel.dao.impl.FavoriteDaoImpl;
import cn.liuyan.travel.domain.Favorite;
import cn.liuyan.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService{

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    //是否收藏
    @Override
    public boolean isFavorite(String rid,int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        //如果对象有值,就返回true,反之返回false
        if (favorite == null){
            return false;
        }else {
            return true;
        }
    }

    //添加收藏
    @Override
    public void addFavorite(String ridStr, int uid) {
        favoriteDao.add(Integer.parseInt(ridStr),uid);
    }
}
