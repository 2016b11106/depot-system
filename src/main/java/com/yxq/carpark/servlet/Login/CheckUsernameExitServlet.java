package com.yxq.carpark.servlet.Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.serviceImpl.UserServiceImpl;
import com.yxq.carpark.utils.Msg;


@WebServlet("/login/checkUsernameExit")
public class CheckUsernameExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		String username = request.getParameter("username");
		User user = userService.findUserByUsername(username);
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		if(user == null) {
			json = mapper.writeValueAsString(Msg.fail().add("va_msg", "用户名不存在"));
		}else {
			json = mapper.writeValueAsString(Msg.success());
		}
		PrintWriter out = response.getWriter();
		out.write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
