package com.yxq.carpark.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yxq.carpark.dao.IllegalInfoMapper;
import com.yxq.carpark.entity.IllegalInfo;
import com.yxq.carpark.service.IllegalInfoService;
import com.yxq.carpark.utils.MyBatisUtil;



public class IllegalInfoServiceImpl implements IllegalInfoService {


	private IllegalInfoMapper illegalInfoDao;
	public void save(IllegalInfo info) {
		SqlSession session = MyBatisUtil.getSession();
		IllegalInfoMapper mapper = session.getMapper(IllegalInfoMapper.class);
		mapper.save(info);
		session.commit();
		session.close();
	}
	public List<IllegalInfo> findAllIllegalInfo(int page,int size,String name) {
		SqlSession session = MyBatisUtil.getSession();
		IllegalInfoMapper mapper = session.getMapper(IllegalInfoMapper.class);
		List<IllegalInfo> illegalInfos = mapper.findAllIllegalInfo(page, size, name);
		session.close();
		return illegalInfos;
	}
	public IllegalInfo findById(int id) {
		SqlSession session = MyBatisUtil.getSession();
		IllegalInfoMapper mapper = session.getMapper(IllegalInfoMapper.class);
		IllegalInfo illegalInfo = mapper.findById(id);
		session.close();
		return illegalInfo;
	}
	public void deleteById(int id) {
		SqlSession session = MyBatisUtil.getSession();
		IllegalInfoMapper mapper = session.getMapper(IllegalInfoMapper.class);
		mapper.deleteById(id);
		session.commit();
		session.close();
	}
	public IllegalInfo findByCarnum(String carnum,Date parkin) {
		return illegalInfoDao.findByCarnum(carnum,parkin);
	}
	public IllegalInfo findByCardnum(String cardNum) {
		return illegalInfoDao.findByCardnum(cardNum);
	}
	public int findAllIllegalInfoCount(String name) {
		SqlSession session = MyBatisUtil.getSession();
		IllegalInfoMapper mapper = session.getMapper(IllegalInfoMapper.class);
		int count = mapper.findAllIllegalInfoCount(name);
		session.close();
		return count;
	}
	public List<IllegalInfo> findByUid(int uid) {
		return illegalInfoDao.findByUid(uid);
	}
	public void updateCardnum(String cardnum, String cardnumNew) {
		illegalInfoDao.updateCardnum(cardnum,cardnumNew);
	}
	public IllegalInfo findByCardnumParkin(String cardNum, Date parkin) {
		SqlSession session = MyBatisUtil.getSession();
		IllegalInfoMapper mapper = session.getMapper(IllegalInfoMapper.class);
		IllegalInfo illegalInfo = mapper.findByCardnumParkin(cardNum, parkin);
		session.close();
		return illegalInfo;
	}

}
