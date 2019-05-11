package com.yxq.carpark.servlet.User;

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
import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.service.ParkinfoService;
import com.yxq.carpark.service.ParkinfoallService;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.serviceImpl.DepotcardServiceImpl;
import com.yxq.carpark.serviceImpl.ParkinfoServiceImpl;
import com.yxq.carpark.serviceImpl.UserServiceImpl;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/user/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		UserService userService = new UserServiceImpl();
		User user = userService.findUserById(Integer.parseInt(uid));
		DepotcardService depotcardService = new DepotcardServiceImpl();
		ParkinfoService parkinfoService = new ParkinfoServiceImpl();
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		PrintWriter out = response.getWriter();
		if(user != null) {

			int cardid = user.getCardid();
			Depotcard depotcard = depotcardService.findByCardid(cardid);
			String cardnum=depotcard.getCardnum();
			ParkInfo parkInfo = parkinfoService.findParkinfoByCardnum(cardnum);
			if(parkInfo != null) {
				json = mapper.writeValueAsString(Msg.fail().add("va_msg", "有车辆在停车，不能删除！"));
				out.write(json);
				return;
			}else {
				depotcardService.deleteDepotCard(cardnum);
			}
			userService.delUserById(Integer.parseInt(uid));
			System.out.println(uid+"-----");
			json = mapper.writeValueAsString(Msg.success().add("va_msg", "删除成功！"));
			out.write(json);
			return;
		}
		json = mapper.writeValueAsString(Msg.fail().add("va_msg", "删除失败！"));
		out.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
