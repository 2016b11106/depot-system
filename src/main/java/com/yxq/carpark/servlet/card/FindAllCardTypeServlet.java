package com.yxq.carpark.servlet.card;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxq.carpark.entity.CardType;
import com.yxq.carpark.service.CardtypeService;
import com.yxq.carpark.serviceImpl.CardtypeServiceImpl;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/card/findAllCardType")
public class FindAllCardTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CardtypeService cardtypeService = new CardtypeServiceImpl();
		List<CardType> cardTypes=cardtypeService.findAllCardType();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		String json = mapper.writeValueAsString(Msg.success().add("cardTypes", cardTypes));
		out.write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
