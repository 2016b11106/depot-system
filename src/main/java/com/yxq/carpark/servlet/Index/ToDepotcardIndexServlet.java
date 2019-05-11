package com.yxq.carpark.servlet.Index;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxq.carpark.dto.DepotcardManagerData;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.serviceImpl.DepotcardServiceImpl;
import com.yxq.carpark.utils.Constants;
import com.yxq.carpark.utils.PageUtil;


@WebServlet("/index/toDepotcardIndex")
public class ToDepotcardIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagestr = request.getParameter("page");
		String cardnum = request.getParameter("cardnum");
		int page;
		if(pagestr == null) {
			page = 0;
		}else {
			page = Integer.parseInt(pagestr);
		}
		if(page != 0)
			page--;
		List<DepotcardManagerData> depotcardManagerDatas = null;
		PageUtil<DepotcardManagerData> pageUtil=new PageUtil<DepotcardManagerData>();
		int count =0;
		int countPage=0;
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute("user");
		if(cardnum==null)
		{
			cardnum="";
		}
		if(user1 != null) {
			DepotcardService depotcardService = new DepotcardServiceImpl();
			depotcardManagerDatas = depotcardService.findAllDepotcard(cardnum,page*10,Constants.PAGESIZE);
			count=depotcardService.findAllDepotcardCount(cardnum);
		}
		countPage=count/10;
		if(count%10>0)
		{
			countPage++;
		}
		pageUtil.setExtra(cardnum);
		pageUtil.setCurrent(page);
		pageUtil.setCount(count);
		pageUtil.setCountPage(countPage);
		pageUtil.setPages(depotcardManagerDatas);
		session.setAttribute("depotcardManagerDatas", pageUtil);
		request.getRequestDispatcher("/WEB-INF/jsp/depotcard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
