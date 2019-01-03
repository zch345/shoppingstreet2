package com.shoppingstreet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingstreet.entity.User;
import com.shoppingstreet.service.UserService;
import com.shoppingstreet.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String isAgree = request.getParameter("isAgree");
		
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		
		UserService userService = new UserServiceImpl();
		boolean flag = userService.add(user);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println("<html>");
		writer.println("<meta charset=\"utf-8\"");
		writer.println("<head></head>");
		if(flag==true){
			writer.println("<body>¹§Ï²"+loginName+"×¢²á³É¹¦</body>");
		}
		else{
			writer.println("<body>×¢²áÊ§°Ü</body>");	
		}
		writer.println("</html>");
		
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
