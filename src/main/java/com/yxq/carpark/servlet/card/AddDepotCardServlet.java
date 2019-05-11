package com.yxq.carpark.servlet.card;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxq.carpark.dto.DepotcardManagerData;
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.Income;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.service.IncomeService;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.serviceImpl.DepotcardServiceImpl;
import com.yxq.carpark.serviceImpl.IncomeServiceImpl;
import com.yxq.carpark.serviceImpl.UserServiceImpl;
import com.yxq.carpark.utils.Constants;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/card/addDepotCard")
public class AddDepotCardServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		DepotcardService depotcardService = new DepotcardServiceImpl();
		IncomeService incomeService = new IncomeServiceImpl();
		UserService userService = new UserServiceImpl();
		
		String moneystr = request.getParameter("money");
		String typestr = request.getParameter("type");
		String payid = request.getParameter("payid");
		if(payid == null) {
			
		}
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		DepotcardManagerData depotcardManagerData = new DepotcardManagerData();
		depotcardManagerData.setType(typestr);
		depotcardManagerData.setMoney(Double.valueOf(moneystr));
		depotcardManagerData.setDeductedtime(new Date());
		depotcardManagerData.setUsername(username);
		depotcardManagerData.setName(name);
		Depotcard depotcard=depotcardService.save(depotcardManagerData);
		
		double money=depotcardManagerData.getMoney();
		Income income=new Income();
		double tmpMoney = money;
		if (depotcard==null) {
			out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "’À∫≈“—æ≠¥Ê‘⁄£°")));
			return;
		}
		int type = Integer.parseInt(depotcardManagerData.getType());
		if(type == 1) {
			money=depotcard.getMoney();
			depotcard.setMoney(money);
			depotcardService.updateDepotcardBycardnum(depotcard);
			//income.setMoney(Constants.MONTHCARD);
		}
		else if(type==2){
			money=depotcard.getMoney();
			money+=200;
			depotcard.setMoney(money);
			depotcardService.updateDepotcardBycardnum(depotcard);
			//income.setMoney(Constants.MONTHCARD);
		}
		else if (type==3) {
			money=depotcard.getMoney();
			money+=500;
			depotcard.setMoney(money);
			depotcardService.updateDepotcardBycardnum(depotcard);
			//income.setMoney(Constants.YEARCARD);
		}
		income.setCardnum(depotcard.getCardnum());
		income.setTime(new Date());
		income.setMoney(tmpMoney);
		income.setType(type);
		income.setMethod(Integer.parseInt(payid));
		income.setSource(0);
		incomeService.save(income);
		userService.saveByaddDepotCard(depotcardManagerData.getUsername(), depotcardManagerData.getName(), depotcard.getId());
		out.write(mapper.writeValueAsString(Msg.success().add("depotcard", depotcard).add("username", depotcardManagerData.getUsername())));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
