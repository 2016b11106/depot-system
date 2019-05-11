package com.yxq.carpark.servlet.card;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxq.carpark.entity.CardType;
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.CardtypeService;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.serviceImpl.CardtypeServiceImpl;
import com.yxq.carpark.serviceImpl.DepotcardServiceImpl;
import com.yxq.carpark.serviceImpl.UserServiceImpl;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/card/findDepotCardByCardnum")
public class FindDepotCardByCardnumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepotcardService depotcardService = new DepotcardServiceImpl();
		UserService userService = new UserServiceImpl();
		CardtypeService cardtypeService = new CardtypeServiceImpl();
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		
		String cardnum = request.getParameter("cardnum");
		User currentUser=(User) session.getAttribute("user");
		Depotcard depotcard=depotcardService.findByCardnum(cardnum);
		if (depotcard==null) {
			String json = mapper.writeValueAsString(Msg.fail());
			out.write(json);
			return;
		}
		int typeid=depotcard.getType();
		int cardid=depotcard.getId();
		User user=userService.findUserByCardid(cardid);
		CardType cardType=cardtypeService.findCardTypeByid(typeid);
		List<CardType> cardTypes=cardtypeService.findAllCardType();
		String json = mapper.writeValueAsString(Msg.success().add("depotcard", depotcard).add("cardType", cardType)
				.add("cardTypes", cardTypes).add("user", user).add("user_role", currentUser.getRole()));
		out.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
