package com.lql.test;

import com.lql.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class UserTest {
	public static void main(String[] args){
		String  resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session = sqlSessionFactory.openSession();
			User user = session.selectOne("findById", 1);
			session.commit();
			System.out.println(user.getAge());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
