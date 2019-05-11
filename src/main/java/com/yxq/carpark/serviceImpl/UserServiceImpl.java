package com.yxq.carpark.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yxq.carpark.dao.UserMapper;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.utils.Msg;
import com.yxq.carpark.utils.MyBatisUtil;



public class UserServiceImpl implements UserService {

	private static UserMapper mapper = null;

	public User findUserByUsername(String username) {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user=mapper.findUserByUserName(username);
		session.close();
		return user;
	}

	public void saveByaddDepotCard(String username,String name, int cardid) {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.saveByaddDepotCard(username,name,cardid);
		session.commit();
		session.close();
	}

	public User findUserByCardid(int cardid) {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.findUserByCardid(cardid);
		return user;
	}

	public List<User> findAllUser(int page,int size) {
		return mapper.findAllUser(page,size);
	}

	public void deleteUserByCardid(int cardid) {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.deleteUserByCardid(cardid);
		session.commit();
		session.close();
	}

	public void save(User user) {
		mapper.save(user);
	}

	public List<User> findUsersByRole(int role,int page,int size) {
		return mapper.findUsersByRole(role,page,size);
	}

	public List<User> findUsersByRoleMan(int role,int page,int size){
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> list = mapper.findUsersByRoleMan(role, page, size);
		System.out.println(list);
		session.close();
		return list;
	}

	public User findUserById(int uid) {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.findUserById(uid);
		session.close();
		return user;
	}

	public void update(User user) {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.update(user);
		session.commit();
		session.close();
	}

	public void delUserById(int uid) {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.delUserById(uid);
		session.commit();
		session.close();
	}

	public int findAllUserCount(int role) {
		return mapper.findAllUserCount(role);
	}

	public int findAllUserCountMan(int role){
		SqlSession session = MyBatisUtil.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		int count = mapper.findAllUserCountMan(role);
		session.close();
		return count;
	};

	public List<User> finAllUserByRole(int role) {
		return mapper.finAllUserByRole(role);
	}

}
