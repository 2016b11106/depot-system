package com.yxq.carpark.servlet.Index;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxq.carpark.entity.IllegalInfo;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Constants;
import com.yxq.carpark.utils.PageUtil;


@WebServlet("/index/findAllIllegalinfo")
public class FindAllIllegalinfoServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagestr = request.getParameter("page");
		int page;
		if(pagestr == null) {
			page = 0;
		}else {
			page = Integer.parseInt(pagestr);
		}
		if(page != 0)
			page -= 1;
		String name = request.getParameter("name");
		if(name == null)
			name = "";
		List<IllegalInfo> illegalInfo=null;
		PageUtil<IllegalInfo> pageUtil=new PageUtil<IllegalInfo>();
		int count=0;
		int countPage=0;
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute("user");
		illegalInfo=illegalInfoService.findAllIllegalInfo(page*10,Constants.PAGESIZE,name);
		count=illegalInfoService.findAllIllegalInfoCount(name);
		countPage=count/10;
		if(count%10!=0)
		{
			countPage++;
		}
		pageUtil.setExtra(name);
		pageUtil.setPages(illegalInfo);
		pageUtil.setCount(count);
		pageUtil.setCountPage(countPage);
		pageUtil.setCurrent(page);
		session.setAttribute("illegalInfo", pageUtil);	
		request.getRequestDispatcher("/WEB-INF/jsp/illegalinfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
