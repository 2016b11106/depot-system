package com.yxq.carpark.servlet.check;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/check/findParkinfoByCardnum")
public class FindParkinfoByCardnumServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String cardnum = request.getParameter("cardnum");
		ParkInfo parkInfo = parkinfoService.findParkinfoByCardnum(cardnum);
		if(parkInfo!=null)
		{ 
			out.write(mapper.writeValueAsString(Msg.success().add("parkInfo", parkInfo)));
			return;
		}
		out.write(mapper.writeValueAsString(Msg.fail()));
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
