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
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.Income;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.service.IncomeService;
import com.yxq.carpark.serviceImpl.DepotcardServiceImpl;
import com.yxq.carpark.serviceImpl.IncomeServiceImpl;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/card/rechargeDepotCardSubmit")
public class RechargeDepotCardSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		DepotcardService depotcardService = new DepotcardServiceImpl();
		IncomeService incomeService = new IncomeServiceImpl();
		
		String cardnum = request.getParameter("cardnum");
		String moneystr = request.getParameter("money");
		String payid = request.getParameter("payid");
		Income income=new Income();
		Depotcard depotcard=depotcardService.findByCardnum(cardnum);
		if(depotcard==null) {
			out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "该停车卡不存在，请重新输入！")));
			return;
		}
		double money = Double.valueOf(moneystr);
		double moneymore=depotcard.getMoney()+money;
		try {
			depotcardService.addMoney(cardnum, moneymore);
		} catch (Exception e) {
			out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "出现错误！")));
			return;
		}
		income.setCardnum(cardnum);
		income.setType(depotcard.getType());
		income.setSource(1);
		income.setMethod(Integer.parseInt(payid));
		income.setMoney(money);
		income.setTime(new Date());
		incomeService.save(income);
		out.write(mapper.writeValueAsString(Msg.success()));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
