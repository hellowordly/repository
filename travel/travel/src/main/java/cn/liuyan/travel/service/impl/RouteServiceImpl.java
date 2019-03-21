package cn.liuyan.travel.service.impl;

import cn.liuyan.travel.dao.FavoriteDao;
import cn.liuyan.travel.dao.RouteDao;
import cn.liuyan.travel.dao.RouteImgDao;
import cn.liuyan.travel.dao.SellerDao;
import cn.liuyan.travel.dao.impl.FavoriteDaoImpl;
import cn.liuyan.travel.dao.impl.RouteDaoImpl;
import cn.liuyan.travel.dao.impl.RouteImgDaoImpl;
import cn.liuyan.travel.dao.impl.SellerDaoImpl;
import cn.liuyan.travel.domain.PageBean;
import cn.liuyan.travel.domain.Route;
import cn.liuyan.travel.domain.RouteImg;
import cn.liuyan.travel.domain.Seller;
import cn.liuyan.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao dao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装pageBean
        PageBean<Route> pb = new PageBean<>();
        //设置当前页数
        pb.setCurrentPage(currentPage);
        //设置每页显示的条数
        pb.setPageSize(pageSize);

        //查询总记录数totalCount
        int totalCount = dao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //查询每页显示的记录数
        int start = (currentPage - 1) * pageSize;//起始索引
        List<Route> list = dao.findByPage(cid, start, pageSize,rname);
        pb.setList(list);

        //设置中记录数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(int rid) {
        //根据rid查询route对象 routeDao
        Route route = dao.findById(rid);
        //根据rid查询线路id查询图片表,设置到route对象里
        List<RouteImg> routeImgList = routeImgDao.findById(rid);
        route.setRouteImgList(routeImgList);
        //根据sid查询到卖家信息,设置到route对象里
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);

        //查询收藏次数,并设置到route里
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        return route;
    }
}
