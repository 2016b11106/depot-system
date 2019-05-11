package com.yxq.carpark.servlet.depot;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxq.carpark.dto.ParkinfoallData;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/depot/findParkinfoById")
public class FindParkinfoByIdServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		ParkinfoallData parkinfoall=parkinfoallService.findById(id);
		if(parkinfoall!=null)
		{
			out.write(mapper.writeValueAsString(Msg.success().add("parkinfoall", parkinfoall)));
			return;
		}
		out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "系统出错，找不到该停车信息。")));
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
