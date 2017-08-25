package member.dao;

import java.util.HashMap;
import java.util.Map;

import member.model.Member;

public class MemoryMemberDao implements MemberDao {
	private Map<String, Member> map = new HashMap<>();
	
	@Override
	public Member selectById(String id) {
		return map.get(id);
	}

	@Override
	public void insert(Member member) {
		map.put(member.getMemberId(), member);
	}

}
