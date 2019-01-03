package com.shoppingstreet.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingstreet.entity.Product;
import com.shoppingstreet.service.ProductService;
import com.shoppingstreet.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductService productService = new ProductServiceImpl();
        Product product = productService.getProductById(Integer.valueOf(id));
        
        HttpSession session = request.getSession();
        
        int num = 1;
        Map<Product,Integer> car = null;
        //Object obj = request.getAttribute("car");
        Object obj = session.getAttribute("car");
        if(obj==null){
        	car = new HashMap<Product,Integer>();
        }
        else{
        	car = (Map<Product,Integer>)obj;
        	if(car.containsKey(product)){
        		num = car.get(product)+1;
        	}
        }
		
		car.put(product, num);
		
//		request.setAttribute("car", car);
		session.setAttribute("car", car);
		
		request.getRequestDispatcher("BuyCar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
