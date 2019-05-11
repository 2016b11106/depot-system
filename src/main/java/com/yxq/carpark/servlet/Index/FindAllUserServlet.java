package com.yxq.carpark.servlet.Index;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.serviceImpl.UserServiceImpl;
import com.yxq.carpark.utils.Constants;
import com.yxq.carpark.utils.PageUtil;


@WebServlet("/index/findAllUser")
public class FindAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagstr = request.getParameter("tag");
		String pagestr = request.getParameter("page");
		int tag, page;
		if(tagstr == null) {
			tag = 0;
		}else {
			tag = Integer.parseInt(tagstr);
		}
		if(pagestr == null) {
			page = 0;
		}else {
			page = Integer.parseInt(pagestr);
		}
		if(page != 0)
			page -= 1;
		List<User> users = null;
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute("user");
		PageUtil<User> pageUtil = new PageUtil<>();
		int count = 0;
		int countPage = 0;
		UserService userService = new UserServiceImpl();
		if(user1 != null) {
			users = userService.findUsersByRoleMan(tag, page*10, Constants.PAGESIZE);
			count = userService.findAllUserCountMan(tag);
		}
		countPage = count/10;
		if(count%10 != 0) {
			countPage += 1;
		}
		pageUtil.setCount(count);
		pageUtil.setCountPage(countPage);
		pageUtil.setPages(users);
		session.setAttribute("users", pageUtil);
		System.out.println(pageUtil.getPages().size());
		request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
