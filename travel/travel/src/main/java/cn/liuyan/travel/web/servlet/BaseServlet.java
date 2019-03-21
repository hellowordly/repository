package cn.liuyan.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet{

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException {
        //方法分发
        //获取请求路径
        String requestURI = request.getRequestURI();
        //获取方法名称
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        try {
            //获取方法对象
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            Object o = method.invoke(this, request, response);
            if (o != null) {
                //响应数据
                ObjectMapper mapper = new ObjectMapper();

                response.setContentType("application/json;charset=utf-8");
                mapper.writeValue(response.getOutputStream(), o);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
