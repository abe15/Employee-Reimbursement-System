package com.revature.project1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.dao.user.impl.UserDaoList;
import com.revature.project1.models.UserModel;

/**
 * Unit test for simple App.
 */
public class UserServiceTest {
    /**
     * Rigorous Test :-)
     */

    // stubs or mocks and perform expected behaviors based on what you
    // as the developer set for the test

    private static IUserDao userDao;

    @BeforeAll
    public void setUpBeforeClass() throws Exception {
        // create userDao
        userDao = Mockito.mock(UserDaoList.class);

    }

    @Test
    public void testGetUserById() {
        //tell mockito what it should do if given a specific situation in the program
        //act
        when(userDao.findByUserName("abrabarb")).thenReturn(Optional.of(new UserModel("abraham", "barboza", "abrabarb", "a@email.com","password")));
        //User result = userser
    
    }
}
