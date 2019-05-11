package com.yxq.carpark.servlet.Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.serviceImpl.UserServiceImpl;
import com.yxq.carpark.utils.Msg;


@WebServlet("/login/index")
public class LoginIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user1 = userService.findUserByUsername(username);
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		if(user1.getPassword().equals(password)) {
			session.setAttribute("user", user1);
			json = mapper.writeValueAsString(Msg.success());
		}else {
			json = mapper.writeValueAsString(Msg.fail());
		}
		PrintWriter out = response.getWriter();
		out.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
