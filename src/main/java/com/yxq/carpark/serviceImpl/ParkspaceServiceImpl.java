package com.yxq.carpark.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yxq.carpark.dao.ParkspaceMapper;
import com.yxq.carpark.entity.ParkSpace;
import com.yxq.carpark.service.ParkspaceService;
import com.yxq.carpark.utils.MyBatisUtil;



public class ParkspaceServiceImpl implements ParkspaceService {

	
	private static ParkspaceMapper parkspaceDao;
	private static ParkSpace parkSpace;
	
	
	
	public void addParkspace(int count) {
		int max=parkspaceDao.findMaxSpace();
		if(max==0)
		{
			for(int i=1;i<=count;i++)
			{
				parkSpace.setParkid(i);
				parkspaceDao.save(parkSpace);
			}
		}else {
			for(int i=max+1;i<=count+max;i++)
			{
				parkSpace.setParkid(i);
				parkspaceDao.save(parkSpace);
			}
		}
	}
	public List<ParkSpace> findAllParkspace(int page,int size) {
		SqlSession session = MyBatisUtil.getSession();
		ParkspaceMapper mapper = session.getMapper(ParkspaceMapper.class);
		List<ParkSpace> list = mapper.findAllParkspace(page, size);
		session.close();
		return list;
	}
	public void changeStatus(int id, int status) {
		SqlSession session = MyBatisUtil.getSession();
		ParkspaceMapper mapper = session.getMapper(ParkspaceMapper.class);
		mapper.changeStatus(id, status);
		session.commit();
		session.close();
	}
	public List<ParkSpace> findParkspaceByTag(int tag,int page,int size) {
		SqlSession session = MyBatisUtil.getSession();
		ParkspaceMapper mapper = session.getMapper(ParkspaceMapper.class);
		List<ParkSpace> list = mapper.findParkspaceByTag(tag, page, size);
		session.close();
		return list;
	}
	public void changeStatusByParkNum(int parkNum, int status) {
		SqlSession session = MyBatisUtil.getSession();
		ParkspaceMapper mapper = session.getMapper(ParkspaceMapper.class);
		mapper.changeStatusByParkNum(parkNum, status);
		session.commit();
		session.close();
	}
	public int findAllParkspaceCount(int tag) {
		SqlSession session = MyBatisUtil.getSession();
		ParkspaceMapper mapper = session.getMapper(ParkspaceMapper.class);
		int count = mapper.findAllParkspaceCount(tag);
		session.close();
		return count;
	}
	@Override
	public int findNowParkspace(int status) {
		// TODO Auto-generated method stub
		return parkspaceDao.findNowParkspace(status);
	}

}
