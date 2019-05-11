package com.yxq.carpark.servlet.User;

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


@WebServlet("/index/user/editUser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String sex = request.getParameter("sex");	
		String uid = request.getParameter("id");
		UserService userService = new UserServiceImpl();
		User temUser = userService.findUserById(Integer.parseInt(uid));
		User user = new User();
		user.setId(Integer.parseInt(uid));
		user.setUsername(username);
		user.setRole(temUser.getRole());
		user.setCardid(temUser.getCardid());
		user.setPassword(temUser.getPassword());
		user.setName(name);
		user.setTel(tel);
		user.setSex(sex);
		userService.update(user);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(Msg.success().add("va_msg", "ÐÞ¸Ä³É¹¦£¡"));
		System.out.println(json);
		PrintWriter out = response.getWriter();
		out.write(json);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
