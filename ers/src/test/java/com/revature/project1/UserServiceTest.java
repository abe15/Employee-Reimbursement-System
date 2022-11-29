package com.revature.project1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.dao.user.impl.UserDaoSQL;
import com.revature.project1.models.UserModel;
import com.revature.project1.services.IUserService;
import com.revature.project1.services.UserServiceImpl;

public class UserServiceTest {

	// Class to be tested
	private static IUserService uServ;

	// Dependent class needed to be mocked by Mockito (will be mocked)
	private static UserDaoSQL userDao;

	private static Optional<UserModel> temp;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		/* Mockito setup */
		// 1. mock the depending classes
		userDao = Mockito.mock(UserDaoSQL.class);

		// 2. inject your service with mocked classes
		uServ = new UserServiceImpl(userDao);

		// 3. provide a user stub to test with
		temp = Optional.of(new UserModel("abraham", "barboza", "abrabarb", "a@gmail.com", "password"));

	}

	@AfterEach
	public void resetMockAfter() throws Exception {
		Mockito.reset(userDao);
	}

	@Test
	@DisplayName("1. Test Log in - Correct password") //this annotation will allow you to give your test case a custom, readable name in the TestRunner
	void testCorrectLogin() {
		
		//mocking userDao call
		when(userDao.findByUserName("abrabarb")).thenReturn(temp);
		
		//act (do the service call)
		Optional<UserModel> result = uServ.login("abrabarb","password");
		
		//assert & verify that the service method was used once
		//checking if the getById returns a User object that matches temp user
		assertEquals(temp.get(), result.get());
		
		//verifying that the dao method was used once in the execution of the service call
		verify(userDao, times(1)).findByUserName("abrabarb");
	}

	@Test
	@DisplayName("2. Test Log in - Incorrect password") 
	void testIncorrectPasswordLogin() {
	
		when(userDao.findByUserName("abrabarb")).thenReturn(temp);
		
		
		Optional<UserModel> result = uServ.login("abrabarb","pass");
		
		assertEquals(false, result.isPresent());
		
	
		verify(userDao, times(1)).findByUserName("abrabarb");
	}

	@Test
	@DisplayName("3. Test Log in - Incorrect username/Non existing user") 
	void testNonExistingUserName() {
	
		when(userDao.findByUserName("abrabasarb")).thenReturn(Optional.empty());
		
		//act (do the service call)
		Optional<UserModel> result = uServ.login("abrabasarb","pass");
		
		assertEquals(false, result.isPresent());
		
	
		verify(userDao, times(1)).findByUserName("abrabasarb");
	}

	@Test
	@DisplayName("4. Test Register - Valid Input") 
	void testRegistrationCorrectInput() {
	

			when(userDao.save(temp.get())).thenReturn(1);
					
					
			boolean result = uServ.registerUser(temp.get());

			assertEquals(true, result);


			verify(userDao, times(1)).save(temp.get());
		
	}

	@Test
	@DisplayName("5. Test Register - Invalid Input")
	void testRegistrationIncorrectInput() {

		UserModel invalidUser = new UserModel("Abraham", null, "abe", "a@gmail.com", "password");

		when(userDao.save(invalidUser)).thenReturn(0);

		boolean result = uServ.registerUser(invalidUser);

		assertEquals(false, result);

		verify(userDao, times(1)).save(invalidUser);

	}

	@Test
	@DisplayName("6. Test Register - Username is already used")
	void testRegistrationUserNameAlreadyExists() {

		
		when(userDao.save(temp.get())).thenReturn(0);

		boolean result = uServ.registerUser(temp.get());

		assertEquals(false, result);

		verify(userDao, times(1)).save(temp.get());

	}
}
