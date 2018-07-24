package com.lql.DaoImp;

import com.lql.Dao.UserDao;
import com.lql.PO.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserDaoImp implements UserDao {
	private SqlSessionTemplate sqlSession;

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<User> selectUser() {
		return sqlSession.selectList("userMapper.getUser");
	}
}
