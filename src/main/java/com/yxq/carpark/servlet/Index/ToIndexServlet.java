package com.yxq.carpark.servlet.Index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxq.carpark.entity.ParkSpace;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.ParkspaceService;
import com.yxq.carpark.serviceImpl.ParkspaceServiceImpl;
import com.yxq.carpark.utils.Constants;
import com.yxq.carpark.utils.PageUtil;


@WebServlet("/index/toindex")
public class ToIndexServlet extends HttpServlet {
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
		PageUtil<ParkSpace> pageUtil=new PageUtil<ParkSpace>();
		pageUtil.setCurrent(page);
		pageUtil.setTag(tag);
		HttpSession session = request.getSession();
		User user = new User();
		user.setId(1);
		user.setUsername("123456");
		user.setName("π‹¿Ì‘±");
		user.setPassword("123456");
		user.setSex("ƒ–");
		user.setTel("123456");
		user.setRole(2);
		session.setAttribute("user", user);
		User user1 = (User) session.getAttribute("user");
		List<ParkSpace> list = new ArrayList<>();
		int count = 0;
		int countPage = 0;
		ParkspaceService parkspaceService = new ParkspaceServiceImpl();
		if(user1 != null)
		{
			if(tag == 0)
			{
				list = parkspaceService.findAllParkspace(page*10, Constants.PAGESIZE);
			}
			else
			{
				list = parkspaceService.findParkspaceByTag(tag, page*10, Constants.PAGESIZE);
			}
			count = parkspaceService.findAllParkspaceCount(tag);
			countPage = count/10;
			if(count%10 != 0)
			{
				countPage+=1;
			}
			pageUtil.setCountPage(countPage);
			pageUtil.setCount(count);
			pageUtil.setPages(list);
			session.setAttribute("parkspaces", pageUtil);
			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);;
		}else {
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
