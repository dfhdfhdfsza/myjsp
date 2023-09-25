package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.commentDAO;
import dao.commentDAOImpl;
import domain.commentVO;

public class commentServiceImpl implements commentService 
{
	private static final Logger log=LoggerFactory.getLogger(commentServiceImpl.class);
	private commentDAO cdao;
	
	public commentServiceImpl()
	{
		cdao=new commentDAOImpl();
	}

	@Override
	public int post(commentVO cvo) 
	{
		log.info("post check 2");
		return cdao.post(cvo);
	}

	@Override
	public List<commentVO> getList(int bno) {
		log.info("list check 2");
		return cdao.getList(bno);
	}

	@Override
	public int modify(commentVO cvo) {
		log.info("modify check 2");
		return cdao.modify(cvo);
	}

	@Override
	public int remove(int cno) {
		log.info("remove check 2");
		return cdao.remove(cno);
	}
	

}
