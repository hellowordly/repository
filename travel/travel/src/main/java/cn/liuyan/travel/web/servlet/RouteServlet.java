package cn.liuyan.travel.web.servlet;

import cn.liuyan.travel.domain.PageBean;
import cn.liuyan.travel.domain.Route;
import cn.liuyan.travel.domain.User;
import cn.liuyan.travel.service.FavoriteService;
import cn.liuyan.travel.service.RouteService;
import cn.liuyan.travel.service.impl.FavoriteServiceImpl;
import cn.liuyan.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    //分页查询
    public PageBean<Route> pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        //tomcat7插件不能解决中文乱码
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid = 0;
        //处理参数
        if (cidStr != null && cidStr.length() >0 && !"null".equals(cidStr)){//传过来的cid有可能是null字符串
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;//当前页码(如果不传递,默认是当前第一页)
        if (currentPageStr != null && currentPageStr.length() >0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }

        int pageSize = 0;//每页显示的条数
        if (pageSizeStr != null && pageSizeStr.length() >0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;//每页显示五条数据
        }

        if ("null".equals(rname)){
            rname = null;
        }

        //调用Service查询pageBean
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize,rname);
        //转换为json
        return pb;
    }

    //根据id查询一个旅游线路的详情
    public Route findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收参数
        String ridStr = request.getParameter("rid");

        //调用方法完成查询
        Route route = service.findOne(Integer.parseInt(ridStr));
        return route;
    }

    //根据uid查询用户是否收藏过该路线
    public boolean ifFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收参数
        String ridStr = request.getParameter("rid");
        System.out.println(ridStr);
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if (user == null){
            //登录失败
            uid = 0;
        }else {
            //登陆成功
            uid = user.getUid();
        }

        //调用service来查询用户是否收藏过该路线
        boolean flag = favoriteService.isFavorite(ridStr, uid);
        return flag;
    }

    //添加收藏
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收参数
        String ridStr = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if (user == null){
            //登录失败
            return ;
        }else {
            //登陆成功
            uid = user.getUid();
        }

        ////调用service来完成添加
        favoriteService.addFavorite(ridStr,uid);
    }
}
