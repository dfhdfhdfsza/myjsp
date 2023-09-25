package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.commentVO;
import orm.DatabaseBuilder;

public class commentDAOImpl implements commentDAO 
{
	private static final Logger log=LoggerFactory.getLogger(commentDAOImpl.class);
	private SqlSession sql;
	private final String NS="CommentMapper.";
	private int isOk;
	
	public commentDAOImpl()
	{
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int post(commentVO cvo) {
		log.info("post check 3");
		isOk=sql.insert(NS+"post",cvo);
		if(isOk>0)
			sql.commit();
		return isOk;
	}

	@Override
	public List<commentVO> getList(int bno) {
		log.info("list check 3");
		return sql.selectList(NS+"list",bno);
	}

	@Override
	public int modify(commentVO cvo) {
		log.info("modify check 3");
		isOk=sql.update(NS+"modify",cvo);
		if(isOk>0)
			sql.commit();
		
		
		return isOk;
	}

	@Override
	public int remove(int cno) {
		log.info("remove check 3");
		isOk=sql.delete(NS+"remove",cno);
		if(isOk>0)
			sql.commit();
		return isOk;
	}
	

}
