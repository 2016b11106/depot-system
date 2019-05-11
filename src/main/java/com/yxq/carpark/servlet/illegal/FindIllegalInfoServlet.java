package com.yxq.carpark.servlet.illegal;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxq.carpark.entity.IllegalInfo;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.servlet.base.BaseServlet;
import com.yxq.carpark.utils.Msg;

@WebServlet("/index/illegal/findIllegalInfo")
public class FindIllegalInfoServlet extends HttpServlet implements BaseServlet{
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String idstr = request.getParameter("id");
		int id = Integer.parseInt(idstr);
		IllegalInfo illegalInfo=illegalInfoService.findById(id);
		if(illegalInfo==null)
		{
			out.write(mapper.writeValueAsString(Msg.fail().add("va_msg", "发生错误，请重新查看！")));
			return;
		}
		int uid=illegalInfo.getUid();
		User user=userService.findUserById(uid);
		illegalInfo.setUsername(user.getUsername());
		Date date=illegalInfo.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=formatter.format(date);
		illegalInfo.setFormatDate(time);
		out.write(mapper.writeValueAsString(Msg.success().add("illegalInfo", illegalInfo)));
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
