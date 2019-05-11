package com.yxq.carpark.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yxq.carpark.dao.ParkinfoallMapper;
import com.yxq.carpark.dto.ParkinfoallData;
import com.yxq.carpark.entity.Parkinfoall;
import com.yxq.carpark.service.ParkinfoallService;
import com.yxq.carpark.utils.MyBatisUtil;



public class ParkinfoallServiceImpl implements ParkinfoallService {

	private ParkinfoallMapper parkinfoallDao;
	public List<ParkinfoallData> findAllParkinfoall(int page,int size) {
		return parkinfoallDao.findAllParkinfoall(page,size);
	}
	public void save(Parkinfoall parkinfoall) {
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoallMapper mapper = session.getMapper(ParkinfoallMapper.class);
		mapper.save(parkinfoall);
		session.commit();
		session.close();
	}
	public ParkinfoallData findById(int id) {
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoallMapper mapper = session.getMapper(ParkinfoallMapper.class);
		ParkinfoallData parkinfoallData = mapper.findById(id);
		session.close();
		return parkinfoallData;
	}
	public int findAllParkinfoallCount(String name) {
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoallMapper mapper = session.getMapper(ParkinfoallMapper.class);
		int count = mapper.findAllParkinfoallCount(name);
		session.close();
		return count;
	}
	public List<ParkinfoallData> findAllParkinfoallByLike(int page, int size, String name) {
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoallMapper mapper = session.getMapper(ParkinfoallMapper.class);
		List<ParkinfoallData> parkinfoallDatas = mapper.findAllParkinfoallByLike(page, size, name);
		session.close();
		return parkinfoallDatas;
	}
	public List<ParkinfoallData> findByCardNum(String cardnum,String name) {
		return parkinfoallDao.findByCardNum(cardnum,name);
	}
	public void updateCardnum(String cardnum, String cardnumNew) {
		parkinfoallDao.updateCardnum(cardnum,cardnumNew);
	}
	public List<ParkinfoallData> findByCardNumByPage(int page, int size, String cardnum, String name) {
		return parkinfoallDao.findByCardNumByPage(page,size,cardnum,name);
	}

}
