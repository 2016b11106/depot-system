package com.yxq.carpark.servlet.check;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxq.carpark.dto.FormData;
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Msg;

@WebServlet("/index/check/checkIn")
public class CheckInServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String cardNum = request.getParameter("cardNum");
		String idstr = request.getParameter("id");
		String parkNumstr = request.getParameter("parkNum");
		String parkTemstr = request.getParameter("parkTem");
		String carNum = request.getParameter("carNum");
		FormData data = new FormData();
		data.setCardNum(cardNum);
		data.setParkNum(Integer.parseInt(parkNumstr));
		data.setParkTem(Integer.parseInt(parkTemstr));
		data.setId(Integer.parseInt(idstr));
		data.setCarNum(carNum);
		Depotcard depotcard=depotcardService.findByCardnum(cardNum);
		if(depotcard!=null)
		{
			List<ParkInfo> parkInfos = parkinfoService.getAllParkInfo();
			for(ParkInfo parkinfo : parkInfos) {
				if(parkinfo.getCardnum().equals(cardNum)) {
					out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "停车卡已被使用")));
					return;
				}
			}
			if(depotcard.getIslose()==1)
			{
				out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "该卡已挂失！")));
				return;
			}
		}else{
			out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "该卡不存在！")));
			return;
		}
		parkinfoService.saveParkinfo(data);
		parkspaceService.changeStatus(data.getId(), 1);
		out.write(mapper.writeValueAsString(Msg.success()));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
