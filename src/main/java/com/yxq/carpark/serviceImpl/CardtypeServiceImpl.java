package com.yxq.carpark.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yxq.carpark.dao.CardtypeMapper;
import com.yxq.carpark.entity.CardType;
import com.yxq.carpark.service.CardtypeService;
import com.yxq.carpark.utils.MyBatisUtil;



public class CardtypeServiceImpl implements CardtypeService {

	
	public List<CardType> findAllCardType() {
		SqlSession session = MyBatisUtil.getSession();
		CardtypeMapper mapper = session.getMapper(CardtypeMapper.class);
		List<CardType> cardTypes=mapper.findAllCardType();
		session.close();
		return cardTypes;
	}

	public CardType findCardTypeByid(int typeid) {
		SqlSession session = MyBatisUtil.getSession();
		CardtypeMapper mapper = session.getMapper(CardtypeMapper.class);
		CardType cardType = mapper.findCardTypeByid(typeid);
		session.close();
		return cardType;
	}

}
