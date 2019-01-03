package com.shoppingstreet.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingstreet.entity.Order;
import com.shoppingstreet.entity.Product;
import com.shoppingstreet.entity.User;
import com.shoppingstreet.service.OrderService;
import com.shoppingstreet.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class CreateOrderServlet
 */
@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1��ȡ���¶������û���Ϣ
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			User user = (User) session.getAttribute("user");
			
//			2���򶩵�������в��붩������
//			3���򶩵����в��붩����Ϣ
			String address = "�û���ַ";
			double totalPrice = Double.parseDouble(session.getAttribute("totalPrice").toString());
			Map<Product, Integer> car = (Map<Product, Integer>) session.getAttribute("car");
			OrderService orderService = new OrderServiceImpl();
			Order order = orderService.payShoppingCart(car, totalPrice, user, address);
			request.setAttribute("order", order);
//			4��������ﳵ����
			session.removeAttribute("car");
			session.removeAttribute("totalPrice");
			request.getRequestDispatcher("BuyCar3.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
