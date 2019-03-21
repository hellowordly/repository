package cn.liuyan.travel.web.servlet;

import cn.liuyan.travel.util.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码
 */
@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		//禁止缓存
		//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setDateHeader("Expires", -1);

		/**
		 * 随机成功验证码图片
		 */
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		int width=130;
		int height=40;
		/**
		 * 保存验证码
		 */
		request.getSession().setAttribute("VerifyCode",verifyCode);

		VerifyCodeUtils.outputImage(width,height,response.getOutputStream(),verifyCode);

		System.out.println(verifyCode);


	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}
}



