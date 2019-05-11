package com.yxq.carpark.servlet.card;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.serviceImpl.DepotcardServiceImpl;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/card/alertDepotCard")
public class AlertDepotCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		DepotcardService depotcardService = new DepotcardServiceImpl();
		
		String cardnum = request.getParameter("cardnum");
		String islose = request.getParameter("islose");
		Depotcard depotcard = depotcardService.findByCardnum(cardnum);
		if(Integer.parseInt(islose) != depotcard.getIslose()) {
			depotcard.setIslose(Integer.parseInt(islose));
			depotcardService.updateDepotcardBycardnum(depotcard);
			out.write(mapper.writeValueAsString(Msg.success()));
		}else {
			out.write(mapper.writeValueAsString(Msg.fail()));
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
