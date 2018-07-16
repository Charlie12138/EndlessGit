package com.lql.test;

import com.lql.pojo.Classes;
import com.lql.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TCTest {
	@Test
	public void fingCT(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.getClass2";
		Classes classes = session.selectOne(statement, 1);
		session.close();
		System.out.println(classes);
	}

	@Test
	public void fingCT2(){
		SqlSession session = MybatisUtil.getSession();
		String statement = "userMapper.getClass4";
		Classes classes = session.selectOne(statement, 1);
		session.close();
		System.out.println(classes);
	}
}
