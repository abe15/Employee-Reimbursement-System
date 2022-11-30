package com.revature.project1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import com.revature.project1.dao.reimbursementTicket.impl.ReimbursementDaoSql;
import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.dao.user.impl.UserDaoSQL;
import com.revature.project1.models.ReimbursementTicketModel;
import com.revature.project1.models.TicketStatus;
import com.revature.project1.models.UserModel;
import com.revature.project1.services.ITicketService;
import com.revature.project1.services.IUserService;
import com.revature.project1.services.TicketServiceImpl;
import com.revature.project1.services.UserServiceImpl;

public class TicketServiceTest {

    // Class to be tested
    private static ITicketService tServ;

    // Dependent class needed to be mocked by Mockito (will be mocked)
    private static ReimbursementDaoSql ticketDao;

    private static Optional<ReimbursementTicketModel> temp;
    private static List<ReimbursementTicketModel> ticketList;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        /* Mockito setup */
        // 1. mock the depending classes
        ticketDao = Mockito.mock(ReimbursementDaoSql.class);

        // 2. inject your service with mocked classes
        tServ = new TicketServiceImpl(ticketDao);
        ticketList = new ArrayList<>();
        // 3. provide a ticket stub to test with
        // temp = Optional.of(new UserModel("abraham", "barboza", "abrabarb",
        // "a@gmail.com", "password"));
        for (int i = 1; i < 12; i++) {
            ReimbursementTicketModel tt = new ReimbursementTicketModel();
            tt.setReimbId(i);
            tt.setReimbAuthor(i);
            ticketList.add(tt);

        }

    }

    @AfterEach
    public void resetMockAfter() throws Exception {
        Mockito.reset(ticketDao);
    }

    @Test
	@DisplayName("1. Test get all Ticket") //this annotation will allow you to give your test case a custom, readable name in the TestRunner
	void testGetAllTicket() {
		
		//mocking userDao call
		when(ticketDao.getAll()).thenReturn(ticketList);
		
		//act (do the service call)
		List<ReimbursementTicketModel> result = tServ.getAllReimTickets();
		
		//assert & verify that the service method was used once
		//checking if the getById returns a User object that matches temp user
		assertEquals(ticketList, result);
		
		//verifying that the dao method was used once in the execution of the service call
		verify(ticketDao, times(1)).getAll();
	}

    @Test
    @DisplayName("2. Test get ticket by userid - Ticket present")
    void testGetTicketByUserId() {
        List<ReimbursementTicketModel> ex = new ArrayList<>();
        ex.add(ticketList.get(3));
        // mocking userDao call
        when(ticketDao.getTicketsByAuthorUserName(3)).thenReturn(ex);

        // act (do the service call)
        List<ReimbursementTicketModel> result = tServ.getAllReimTicketsByUserName(3);

        assertEquals(ex, result);

        verify(ticketDao, times(1)).getTicketsByAuthorUserName(3);
    }

    @Test
    @DisplayName("2. Test get ticket by userid - Ticket not present")
    void testGetTicketByUserIdNoTicketPresent() {
        List<ReimbursementTicketModel> ex = new ArrayList<>();
        // ex.add(ticketList.get(3));
        // mocking userDao call
        when(ticketDao.getTicketsByAuthorUserName(100)).thenReturn(ex);

        // act (do the service call)
        List<ReimbursementTicketModel> result = tServ.getAllReimTicketsByUserName(100);

        assertEquals(ex, result);

        verify(ticketDao, times(1)).getTicketsByAuthorUserName(100);
    }

}
