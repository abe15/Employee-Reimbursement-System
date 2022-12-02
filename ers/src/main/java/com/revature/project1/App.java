package com.revature.project1;

import io.javalin.*;

//Database access objects
import com.revature.project1.dao.reimbursementTicket.IReimbursementDao;
import com.revature.project1.dao.reimbursementTicket.impl.ReimbursementDaoSql;
import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.dao.user.impl.UserDaoSQL;

//User and Ticket Service
import com.revature.project1.services.ITicketService;
import com.revature.project1.services.TicketServiceImpl;
import com.revature.project1.services.UserServiceImpl;
import com.revature.project1.services.IUserService;
import com.revature.project1.services.AuthService;
//Controllers
import com.revature.project1.controllers.UserController;
import com.revature.project1.controllers.TicketController;

public class App {

    public static void main(String[] args) {

        // Inject all dependencies
        Javalin app = Javalin.create();

        // Authentication and Authorization
        app._conf.accessManager(new AuthService());

        // Sql implementation for both users and tickets
        IUserDao userDao = new UserDaoSQL();
        IReimbursementDao ticketDao = new ReimbursementDaoSql();

        // Injecting DAOs to services
        IUserService uServ = new UserServiceImpl(userDao);
        ITicketService tServ = new TicketServiceImpl(ticketDao);

        // Passing the services to the controllers
        TicketController tController = new TicketController(tServ, app);
        UserController uController = new UserController(uServ, app);

        app.start(7070);

    }

}
