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
import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.service.ParkinfoService;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.serviceImpl.DepotcardServiceImpl;
import com.yxq.carpark.serviceImpl.ParkinfoServiceImpl;
import com.yxq.carpark.serviceImpl.ParkinfoallServiceImpl;
import com.yxq.carpark.serviceImpl.UserServiceImpl;
import com.yxq.carpark.utils.Msg;

@WebServlet("/index/card/deleteDepotCard")
public class DeleteDepotCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		DepotcardService depotcardService = new DepotcardServiceImpl();
		ParkinfoService parkinfoService = new ParkinfoServiceImpl();
		UserService userService = new UserServiceImpl();
		
		String cardnum = request.getParameter("cardnum");
		Depotcard depotcard=depotcardService.findByCardnum(cardnum);
		int cardid=depotcard.getId();
		ParkInfo parkInfo=parkinfoService.findParkinfoByCardnum(cardnum);
		if(parkInfo!=null) {
			out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "有车辆在停车，不能删除！")));
			return;
		}
		userService.deleteUserByCardid(cardid);
		depotcardService.deleteDepotCard(cardnum);
		out.write(mapper.writeValueAsString(Msg.success()));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
