package cn.liuyan.travel.web.servlet;

import cn.liuyan.travel.domain.Category;
import cn.liuyan.travel.service.CategoryService;
import cn.liuyan.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    public List<Category> findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用方法查询
        CategoryService service = new CategoryServiceImpl();
        List<Category> list = service.findAll();

        return list;
    }
}
