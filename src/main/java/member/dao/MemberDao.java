package member.dao;

import org.apache.ibatis.annotations.Mapper;

import member.model.Member;

@Mapper
public interface MemberDao {

	Member selectById(String id);

}
