import com.liqinglin.www.controller.UserLoginHandler;
import com.liqinglin.www.dao.UserMapper;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.serviceImp.UserServiceImp;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/beans.xml","classpath:spring/springmvc.xml"})
@WebAppConfiguration("src/main/resources")
public class Test {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	public void setUserService(UserServiceImp userServiceImp) {
		this.userService = userServiceImp;
	}

	@Autowired(required = false)
	private UserLoginHandler userLoginServlet;

	@org.junit.Test
	public void test() {
		System.out.println(userMapper);
		System.out.println(userService);
		System.out.println(userLoginServlet);
	}
}
