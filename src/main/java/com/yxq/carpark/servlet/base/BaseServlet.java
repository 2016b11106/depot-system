package com.yxq.carpark.servlet.base;

import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxq.carpark.service.CardtypeService;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.service.IllegalInfoService;
import com.yxq.carpark.service.IncomeService;
import com.yxq.carpark.service.ParkinfoService;
import com.yxq.carpark.service.ParkinfoallService;
import com.yxq.carpark.service.ParkspaceService;
import com.yxq.carpark.service.PlateRecognise;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.serviceImpl.CardtypeServiceImpl;
import com.yxq.carpark.serviceImpl.DepotcardServiceImpl;
import com.yxq.carpark.serviceImpl.IllegalInfoServiceImpl;
import com.yxq.carpark.serviceImpl.IncomeServiceImpl;
import com.yxq.carpark.serviceImpl.ParkinfoServiceImpl;
import com.yxq.carpark.serviceImpl.ParkinfoallServiceImpl;
import com.yxq.carpark.serviceImpl.ParkspaceServiceImpl;
import com.yxq.carpark.serviceImpl.UserServiceImpl;

public interface BaseServlet {
	
	UserService userService = new UserServiceImpl();
	ParkspaceService parkspaceService = new ParkspaceServiceImpl();
	CardtypeService cardtypeService = new CardtypeServiceImpl();
	DepotcardService depotcardService = new DepotcardServiceImpl();
	IllegalInfoService illegalInfoService = new IllegalInfoServiceImpl();
	IncomeService incomeService = new IncomeServiceImpl();
	ParkinfoService parkinfoService = new ParkinfoServiceImpl();
	ParkinfoallService parkinfoallService = new ParkinfoallServiceImpl();
	
	ObjectMapper mapper = new ObjectMapper();
}
