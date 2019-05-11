package com.yxq.carpark.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yxq.carpark.dao.DepotcardMapper;
import com.yxq.carpark.dto.DepotcardManagerData;
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.utils.MyBatisUtil;



public class DepotcardServiceImpl implements DepotcardService {


	private DepotcardMapper depotcardDao;
	
	public List<DepotcardManagerData> findAllDepotcard(String cardnum,int page,int size) {
		SqlSession session = MyBatisUtil.getSession();
		DepotcardMapper mapper = session.getMapper(DepotcardMapper.class);
		List<DepotcardManagerData> depotcardManagerDatas=mapper.findAllDepotcard(cardnum,page,size);
		session.close();
		return depotcardManagerDatas;
	}

	public Depotcard save(DepotcardManagerData depotcardManagerData) {
		SqlSession session = MyBatisUtil.getSession();
		DepotcardMapper mapper = session.getMapper(DepotcardMapper.class);
		
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String trans=formatter.format(date);
		String dateStr=trans.replaceAll(" ", "").replaceAll("-", "");
		String cardnum=depotcardManagerData.getUsername()+dateStr;
		Depotcard depotcardTem=mapper.findByCardnum(cardnum);
		//Í£³µ¿¨ÒÑ´æÔÚ
		if(depotcardTem!=null)
		{
			return null;
		}
		Depotcard depotcard=new Depotcard();
		depotcard.setCardnum(cardnum);
		depotcard.setMoney(depotcardManagerData.getMoney());
		depotcard.setTime(date);
		depotcard.setType(Integer.parseInt(depotcardManagerData.getType()));
		depotcard.setDeductedtime(depotcardManagerData.getDeductedtime());
		mapper.save(depotcard);
		session.commit();
		depotcard=mapper.findByCardnum(cardnum);
		session.close();
		return depotcard;
	}

	public Depotcard findByCardid(int cardid) {
		SqlSession session = MyBatisUtil.getSession();
		DepotcardMapper mapper = session.getMapper(DepotcardMapper.class);
		Depotcard depotcard = mapper.findByCardid(cardid);
		session.close();
		return depotcard;
	}

	public Depotcard findByCardnum(String cardnum) {
		SqlSession session = MyBatisUtil.getSession();
		DepotcardMapper mapper = session.getMapper(DepotcardMapper.class);
		Depotcard depotcard= mapper.findByCardnum(cardnum);
		session.close();
		return depotcard;
	}

	public void updateDepotcardBycardnum(Depotcard depotcard) {
		SqlSession session = MyBatisUtil.getSession();
		DepotcardMapper mapper = session.getMapper(DepotcardMapper.class);
		mapper.updateDepotcardBycardnum(depotcard);
		session.commit();
		session.close();
	}

	public void deleteDepotCard(String cardnum) {
		SqlSession session = MyBatisUtil.getSession();
		DepotcardMapper mapper = session.getMapper(DepotcardMapper.class);
		mapper.deleteDepotCard(cardnum);
		session.commit();
		session.close();
	}

	public void addMoney(String cardnum, double money) {
		SqlSession session = MyBatisUtil.getSession();
		DepotcardMapper mapper = session.getMapper(DepotcardMapper.class);
		mapper.addMoney(cardnum,money);
		session.commit();
		session.close();
	}

	public int findAllDepotcardCount(String cardnum) {
		SqlSession session = MyBatisUtil.getSession();
		DepotcardMapper mapper = session.getMapper(DepotcardMapper.class);
		int count = mapper.findAllDepotcardCount(cardnum);
		session.close();
		return count;
	}

	public List<DepotcardManagerData> findByCardId(int cardid) {
		return depotcardDao.findByCardId(cardid);
	}

	public void updateCardnum(String cardnum, String cardnumNew) {
		
		depotcardDao.updateCardnum(cardnum,cardnumNew);
	}

	@Override
	public String findCardnumByCarnum(String carnum) {
		
		
		return depotcardDao.findCardnumByCarnum(carnum);
	}

}
