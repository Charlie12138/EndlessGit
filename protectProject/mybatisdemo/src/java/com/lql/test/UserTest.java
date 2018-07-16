package com.lql.test;

import com.lql.DaoImp.UserMapper;
import com.lql.pojo.ConditionUser;
import com.lql.pojo.Order;
import com.lql.pojo.User;
import com.lql.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserTest {
	@Test
	public  void addUser(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.addUser";
		int ins = session.insert(statement, new User(-1, "sss", 26));
		session.close();
		System.out.println(ins);
	}

	@Test
	public void updateUser(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.updateUser";
		int ins = session.insert(statement, new User(3, "aaa", 25));
		session.close();
		System.out.println(ins);
	}

	@Test
	public void deleteUser(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.deleteUser";
		int ins = session.delete(statement, 7);
		session.close();
		System.out.println(ins);
	}

	@Test
	public void findUser(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.findById";
		User user = session.selectOne(statement, 3);
		session.close();
		System.out.println(user);
	}

	@Test
	public void getAllUser(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.getAll";
		List<User> users = session.selectList(statement);
		session.close();
		System.out.println(users);
	}

	@Test
	public  void addUser2(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.addUser";
		UserMapper mapper = session.getMapper(UserMapper.class);
		int ins = mapper.add(new User(-1, "lql", 16));
		session.close();
		System.out.println(ins);
	}

	@Test
	public void findOrder(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.selectOrderResultMap";
		Order order = session.selectOne(statement, 3);
		session.close();
		System.out.println(order);
	}

	@Test
	public void findUser2(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.getUser";
		List<User> users = session.selectList(statement, new ConditionUser("%o%", 12, 18));
		session.close();
		System.out.println(users);
	}
}
