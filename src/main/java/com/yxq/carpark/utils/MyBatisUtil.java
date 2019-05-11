package com.yxq.carpark.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static SqlSessionFactory sessionFactory;
	static{
		Reader reader=null;
		try {
			reader= Resources.getResourceAsReader("mybatis-config.xml");
		 } catch (IOException e) {
			 e.printStackTrace();
		}
		sessionFactory=new SqlSessionFactoryBuilder().build(reader);	
	}
	
	public static SqlSession getSession() {
		SqlSession session=sessionFactory.openSession();
		return session;
	}
	
	
}
