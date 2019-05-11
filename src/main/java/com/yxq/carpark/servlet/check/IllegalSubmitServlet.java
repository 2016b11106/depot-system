package com.yxq.carpark.servlet.check;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.IllegalInfo;
import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/check/illegalSubmit")
public class IllegalSubmitServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String cardNum = request.getParameter("cardNum");
		String carNum = request.getParameter("carNum");
		String illegalInfostr = request.getParameter("illegalInfo");
		ParkInfo parkInfo=parkinfoService.findParkinfoByCardnum(cardNum);
		IllegalInfo info=new IllegalInfo();
		IllegalInfo illegalInfo=illegalInfoService.findByCardnumParkin(cardNum,parkInfo.getParkin());
		if(illegalInfo!=null)
		{
			out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "添加失败,已经有违规！")));
			return;
		}
		Depotcard depotcard = depotcardService.findByCardnum(cardNum);
		int illcount = depotcard.getIllegalcount()+1;
		depotcard.setIllegalcount(illcount);
		depotcardService.updateDepotcardBycardnum(depotcard);
		info.setCardnum(cardNum);
		info.setCarnum(carNum);
		info.setIllegalInfo(illegalInfostr);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		User user1 = userService.findUserByUsername(user.getUsername());
		info.setUid(user1.getId());
		Date date=new Date();
		info.setTime(date);
		info.setParkin(parkInfo.getParkin());
		info.setDelete("N");
		try {
			
			illegalInfoService.save(info);
			
			} catch (Exception e) {
				e.printStackTrace();
				out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "添加失败")));
				return;
			}
		out.write(mapper.writeValueAsString(Msg.success().add("va_msg", "添加成功").add("illcount", illcount)));
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
