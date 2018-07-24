package com.lql.Test;

import com.lql.Dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/beans.xml");
        UserDao userDao = (UserDao) ac.getBean("userDao");
        System.out.println(userDao.selectUser().size());
    }
}
