package com.lql.DaoImp;

import com.lql.pojo.User;
import org.apache.ibatis.annotations.Insert;

public interface UserMapper {
	@Insert("insert into User(name, age) values (#{name}, #{age})")
	public int add(User user);
}
