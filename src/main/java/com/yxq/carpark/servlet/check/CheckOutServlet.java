package com.yxq.carpark.servlet.check;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxq.carpark.dto.FormData;
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.Income;
import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.entity.Parkinfoall;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Constants;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/check/checkOut")
public class CheckOutServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parkNumstr = request.getParameter("parkNum");
		FormData data = new FormData();
		data.setParkNum(Integer.parseInt(parkNumstr));
		String payid = request.getParameter("payid");
		
		Date parkout=new Date();
		Parkinfoall parkinfoall=new Parkinfoall();
		ParkInfo parkInfo=parkinfoService.findParkinfoByParknum(data.getParkNum());
		Depotcard depotcard = depotcardService.findByCardnum(parkInfo.getCardnum());
		double money = depotcard.getMoney();
		double moneyLess = money-Constants.PAY;
		depotcard.setMoney(moneyLess);
		depotcardService.updateDepotcardBycardnum(depotcard);
		
		Income income = new Income();
		income.setMoney(Constants.PAY);
		income.setMethod(Integer.parseInt(payid));
		income.setType(depotcard.getType());
		income.setCardnum(parkInfo.getCardnum());
		income.setIsillegal(0);
		income.setSource(2);
		income.setTime(new Date());
		incomeService.save(income);
		
		parkinfoall.setCardnum(parkInfo.getCardnum());
		parkinfoall.setCarnum(parkInfo.getCarnum());
		parkinfoall.setParkin(parkInfo.getParkin());
		parkinfoall.setParknum(data.getParkNum());
		parkinfoall.setParkout(parkout);
		parkinfoall.setParktemp(parkInfo.getParktem());
		parkinfoallService.save(parkinfoall);
		parkspaceService.changeStatusByParkNum(data.getParkNum(),0);
		parkinfoService.deleteParkinfoByParkNum(data.getParkNum());
		
		PrintWriter out = response.getWriter();
		out.write(mapper.writeValueAsString(Msg.success()));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
