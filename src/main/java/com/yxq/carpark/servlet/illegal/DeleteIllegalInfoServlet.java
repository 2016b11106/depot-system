package com.yxq.carpark.servlet.illegal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.IllegalInfo;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Msg;


@WebServlet("/index/illegal/deleteIllegalInfo")
public class DeleteIllegalInfoServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		int illcount = 0;
		try {
			IllegalInfo illegalInfo = illegalInfoService.findById(Integer.parseInt(id));
			illegalInfoService.deleteById(Integer.parseInt(id));
			String cardnum = illegalInfo.getCardnum();
			Depotcard depotcard = depotcardService.findByCardnum(cardnum);
			illcount = depotcard.getIllegalcount()-1;
			depotcard.setIllegalcount(illcount);
			depotcardService.updateDepotcardBycardnum(depotcard);
		} catch (Exception e) {
			out.write(mapper.writeValueAsString(Msg.fail()));
			return;
		}
		out.write(mapper.writeValueAsString(Msg.success().add("illcount", illcount)));	
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
