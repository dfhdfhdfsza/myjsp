package service;

import java.util.List;

import domain.memberVO;

public interface memberService {

	int join(memberVO mvo);

	memberVO login(memberVO mvo);

	int logout(String id);

	List<memberVO> list();

	memberVO modify(String id);

	int update(memberVO mvo);

	int remove(memberVO mvo);

}
