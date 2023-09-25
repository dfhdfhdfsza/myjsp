package dao;

import java.util.List;

import domain.commentVO;

public interface commentDAO {

	int post(commentVO cvo);

	List<commentVO> getList(int bno);

	int modify(commentVO cvo);

	int remove(int cno);

}
