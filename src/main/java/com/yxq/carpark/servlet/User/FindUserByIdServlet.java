package com.yxq.carpark.servlet.User;

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


@WebServlet("/index/user/findUserById")
public class FindUserByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		UserService userService = new UserServiceImpl();
		User user = userService.findUserById(Integer.parseInt(uid));
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		if(user == null) {
			json = mapper.writeValueAsString(Msg.fail().add("va_msg", "≤È’“ ß∞‹£°"));
		}else {
			User currentUser = (User) session.getAttribute("user");
			json = mapper.writeValueAsString
					(Msg.success().add("user", user).add("role", currentUser.getRole()));
		}
		PrintWriter out = response.getWriter();
		out.write(json);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
