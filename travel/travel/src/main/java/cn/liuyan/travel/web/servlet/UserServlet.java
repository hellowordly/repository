package cn.liuyan.travel.web.servlet;

import cn.liuyan.travel.domain.ResultInfo;
import cn.liuyan.travel.domain.User;
import cn.liuyan.travel.service.UserService;
import cn.liuyan.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")//访问路径
public class UserServlet extends BaseServlet {
    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public ResultInfo regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        //封装对象
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //优先校验验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String verifyCode = (String) session.getAttribute("VerifyCode");//服务端
        session.removeAttribute("VerifyCode");//防止复用验证码

        if (check == null || !check.equalsIgnoreCase(verifyCode)){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //响应到客户端
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
        }

        //调用service层来查询数据
        UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //响应数据
        if (flag){
            //成功
            info.setFlag(true);
        }else {
            //失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }
        return info;
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public ResultInfo login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String yes = request.getParameter("yes");

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ///优先校验验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String verifyCode = (String) session.getAttribute("VerifyCode");//服务端
        session.removeAttribute("VerifyCode");//防止复用验证码

        if (check == null || !check.equalsIgnoreCase(verifyCode)) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误!");
            return info;
        }

        //调用service完成查询
        UserService service = new UserServiceImpl();
        User u = service.loginUser(user);
        ResultInfo info = new ResultInfo();
        //响应数据
        if (u == null) {
            //失败
            info.setFlag(false);
            info.setErrorMsg("用户名或者密码错误!");
        }

        //是否激活
        if (u != null && !"Y".equals(u.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("您尚未激活,请先激活!");
        }

        //登陆成功
        if (u != null && "Y".equals(u.getStatus())) {
            if ("on".equals(yes)) {
                String str = username + "&" + password;
                System.out.println(str);
                //保存到cookie
                Cookie cookie = new Cookie("autoLogin", str);
                cookie.setMaxAge(60 * 60 * 24);
                //发送cookie
                response.addCookie(cookie);
            }
            //存入域对象
            request.getSession().setAttribute("user", u);
            info.setFlag(true);
        }
        return info;
    }

    /**
     * 激活
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据激活码查询用户,然后修改该用户的激活状态,完成激活!
        //获取code
        String code = request.getParameter("code");
        //判断
        if (code != null){
            //激活码不为空,调用service完成激活
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);

            String msg = null;
            //判断
            if (flag) {
                //激活成功
                msg = "激活成功,登录<a href='login.html'>登录</a>";
            }else {
                //激活失败
                msg = "激活失败,请联系管理员!";
            }
            //设置回响消息数据头
            response.setContentType("text/html;charset=utf-8");
            //回响信息到前台
            response.getWriter().write(msg);
        }
    }

    /**
     * 根据姓名查找
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public User findUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        return user;
    }

    /**
     * 退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
