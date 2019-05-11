package com.yxq.carpark.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yxq.carpark.dao.ParkinfoMapper;
import com.yxq.carpark.dao.ParkspaceMapper;
import com.yxq.carpark.dto.FormData;
import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.entity.ParkSpace;
import com.yxq.carpark.service.ParkinfoService;
import com.yxq.carpark.utils.MyBatisUtil;



public class ParkinfoServiceImpl implements ParkinfoService {

	private ParkinfoMapper parkinfoDao;
	public void saveParkinfo(FormData data) {
		Date parkin=new Date();
		ParkInfo parkInfo=new ParkInfo();
		parkInfo.setParknum(data.getParkNum());
		parkInfo.setCarnum(data.getCarNum());
		parkInfo.setCardnum(data.getCardNum());
		parkInfo.setParktem(data.getParkTem());
		parkInfo.setParkin(parkin);
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoMapper mapper = session.getMapper(ParkinfoMapper.class);
		mapper.save(parkInfo);
		session.commit();
		session.close();
	}
	public ParkInfo findParkinfoByParknum(int parknum) {
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoMapper mapper = session.getMapper(ParkinfoMapper.class);
		ParkInfo parkInfo = mapper.findParkinfoByParknum(parknum);
		session.close();
		return parkInfo;
	}
	public void deleteParkinfoByParkNum(int parkNum) {
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoMapper mapper = session.getMapper(ParkinfoMapper.class);
		mapper.deleteParkinfoByParkNum(parkNum);
		session.commit();
		session.close();
	}
	public ParkInfo findParkinfoByCardnum(String cardnum) {
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoMapper mapper = session.getMapper(ParkinfoMapper.class);
		ParkInfo parkInfo = mapper.findParkinfoByCardnum(cardnum);
		session.close();
		return parkInfo;
	}
	public void updateCardnum(String cardnum, String cardnumNew) {
		parkinfoDao.updateCardnum(cardnum,cardnumNew);
	}
	public List<ParkInfo> getAllParkInfo(){
		SqlSession session = MyBatisUtil.getSession();
		ParkinfoMapper mapper = session.getMapper(ParkinfoMapper.class);
		List<ParkInfo> parkInfos = mapper.getAllParkInfo();
		session.close();
		return parkInfos;
	}
}
