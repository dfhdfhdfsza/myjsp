package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.memberDAO;
import dao.memberDAOImpl;
import domain.memberVO;

public class memberServiceImpl implements memberService 
{
	private static final Logger log=LoggerFactory.getLogger(memberServiceImpl.class);
	
	private memberDAO mdao;
	
	public memberServiceImpl()
	{
		mdao=new memberDAOImpl();
	}

	@Override
	public int join(memberVO mvo) 
	{
		log.info("join check 2");
		return mdao.join(mvo);
	}

	@Override
	public memberVO login(memberVO mvo) {
		log.info("login check 2");
		return mdao.login(mvo);
	}

	@Override
	public int logout(String id) {
		log.info("logout check2");
		return mdao.logout(id);
	}

	@Override
	public List<memberVO> list() {
		log.info("list check 2");
		return mdao.list();
	}

	@Override
	public memberVO modify(String id) {
		log.info("modify check 2");
		return mdao.modify(id);
	}

	@Override
	public int update(memberVO mvo) {
		log.info("update check 2");
		return mdao.update(mvo);
	}

	@Override
	public int remove(memberVO mvo) {
		log.info("remove check 2");
		return mdao.remove(mvo);
	}
	
	
	

}
