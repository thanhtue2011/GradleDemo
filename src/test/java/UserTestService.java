
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import com.asiantech.entity.User;
import com.asiantech.repository.UserRepository;
import com.asiantech.service.impl.UserServiceImpl;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class UserTestService {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	@Autowired
	private UserRepository userRepository;
	
	public List<User> createListUser() {
		List<User> userlist = new ArrayList<>();
		userlist.add(new User("1","Tue12","Tran","thanhtue@gmail.com","123"));
		userlist.add(new User("2","Tue3","Tran","thanhtue2013@gmail.com","123"));
		userlist.add(new User("3","Tue2","Tran","thanhtue2041@gmail.com","123"));
		userlist.add(new User("4","Tue4","Tran","thanhtue2015@gmail.com","123"));
		userlist.add(new User("5","Tue5","Tran","thanhtue2011@gmail.com","123"));
		return userlist ;
	}
	private User createUser() {
		User user = new User("6","tue6","thanh", "tue@gmail.com" ,"123");
		return user;	
	}
	
	@Before
	public void setup(){
		//userDAO save
		Mockito.when(userRepository.save(Mockito.any(User.class))).then(new Answer<User>() {
			public User answer(InvocationOnMock invocation)
					throws Throwable {

				User user = (User) invocation.getArguments()[0];
				List<User> listUser = createListUser();
				boolean count = listUser.stream().anyMatch(us -> us.getEmail().equals(user.getEmail()));
				if (count){
					return user;
				}else{
				listUser.add(user);
				user.setPassword("");
				return user;
				}
			}
		});
		
		//getUsers
		
		Mockito.when(userRepository.findAll()).then(new Answer<List<User>>() {
			@Override
			public List<User> answer(InvocationOnMock invocation) throws Throwable {
				List<User> listUser = createListUser();
				listUser.forEach(us -> us.setPassword(""));
				return listUser;
			}
		});
		
		//getUserByEmail
		Mockito.when(userRepository.getUserbyEmail(Mockito.anyString())).then(new Answer<User>(){

			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				String email = (String) invocation.getArguments()[0];
				List<User> list = createListUser();
				Optional<User> user = list.stream().filter(us -> email.equals(us.getEmail())).findFirst();
				if (user.isPresent()){
					User rs = user.get();
					rs.setPassword("");
				return rs;
				}
				else{
					return null;
				}
			}
			
		});
	}

	@Test
	public void testSave() {
		User user = createUser();
		user = userServiceImpl.save(user);
		Assert.assertTrue("".equals(user.getPassword()));
	}

	@Test
	public void testGetUser() {
		List<User> list = userServiceImpl.getUsers();
		Assert.assertTrue(check(list) && list.size() == 5);
	}

	@Test
	public void testGetUserByEmail() {
		User user = userServiceImpl.getUserbyEmail("thanhtue@gmail.com");
		Assert.assertTrue((user != null) && "".equals(user.getPassword()));
	}
	
	boolean check(List<User> list){
		for (User user:list){
			if (!"".equals(user.getPassword())){
				return false;
			}
		}
		return true;
	}

}