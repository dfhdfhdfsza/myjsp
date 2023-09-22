package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.boardDAO;
import dao.boardDAOImpl;
import domain.boardVO;
import domain.pagingVO;

public class boardServiceImpl implements boardService 
{
	private static final Logger log=LoggerFactory.getLogger(boardServiceImpl.class);
	
	private boardDAO bdao;
	public boardServiceImpl()
	{
		bdao=new boardDAOImpl();
	}
	
	@Override
	public int insert(boardVO bvo) {
		log.info("edit check 2");
		return bdao.insert(bvo);
	}

	@Override
	public List<boardVO> list() {
		log.info("list check 2");
		return bdao.list();
	}

	@Override
	public boardVO detail(int bno) {
		log.info("detail check 2");
		return bdao.detail(bno);
	}

	@Override
	public int edit(boardVO bvo) {
		log.info("edit check 2");
		return bdao.edit(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove check 2");
		return bdao.remove(bno);
	}

	@Override
	public int totalcount(pagingVO pgvo) {
		log.info("totalcount check 2");
		return bdao.totalcount(pgvo);
	}

	@Override
	public List<boardVO> getPageList(pagingVO pgvo) {
		log.info("getPageList check 2");
		return bdao.getPageList(pgvo);
	}
	
	

}
