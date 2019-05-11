package com.yxq.carpark.servlet.check;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/check/findParkinfoDetailByParknum")
public class FindParkinfoDetailByParknumServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String parknumstr = request.getParameter("parkNum");
		ParkInfo parkInfo = parkinfoService.findParkinfoByParknum(Integer.parseInt(parknumstr));
		if(parkInfo==null)
		{
			out.write(mapper.writeValueAsString(Msg.fail()));
			return;
		}
		Date date=parkInfo.getParkin();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String parkin=formatter.format(date);
		String cardnum=parkInfo.getCardnum();
		Depotcard depotcard=depotcardService.findByCardnum(cardnum);
		int cardid=0;
		User user =null;
		if(depotcard!=null)
		{
			cardid=depotcard.getId();
			user =userService.findUserByCardid(cardid);
		}
		out.write(mapper.writeValueAsString
				(Msg.success().add("parkInfo", parkInfo).add("user", user).add("parkin", parkin)));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
