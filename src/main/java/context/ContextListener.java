package context;

import controller.UserValidator;
import db.JDBCManager;
import db.JDBCManagerUtils;
import org.apache.log4j.PropertyConfigurator;
import repository.BidRepositoryImpl;
import repository.BillRepositoryImpl;
import repository.RoomRepositoryImpl;
import repository.UserRepositoryImpl;
import repository.api.BillRepository;
import repository.api.RoomRepository;
import repository.api.UserRepository;
import service.BidServiceImpl;
import service.BillServiceImpl;
import service.RoomServiceImpl;
import service.UserServiceImpl;
import service.api.BillService;
import service.api.RoomService;
import service.api.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@WebListener
public class ContextListener implements ServletContextListener {

    ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        String log4jConfigFile = servletContext.getInitParameter("log4j-config-location");
        String fullPath = servletContext.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);
        DataSource dataSource = null;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/hotel");
        } catch (NamingException e) {
            System.out.println("exception!!!");

        }
        JDBCManagerUtils jdbcManagerUtils = new JDBCManagerUtils(dataSource);
        JDBCManager jdbcManager = new JDBCManager(jdbcManagerUtils);

        scheduler = Executors.newSingleThreadScheduledExecutor();

        RoomRepository roomRepository = new RoomRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        BidRepositoryImpl bidRepository = new BidRepositoryImpl();

        BillRepository billRepository = new BillRepositoryImpl(bidRepository);

        UserValidator userValidator = new UserValidator();

        RoomService roomService = new RoomServiceImpl(roomRepository, jdbcManager);
        UserService userService = new UserServiceImpl(userRepository, jdbcManager);
        BidServiceImpl bidService = new BidServiceImpl(bidRepository, roomService, jdbcManager);
        BillService billService = new BillServiceImpl(billRepository, jdbcManager);

        scheduler.scheduleAtFixedRate(new CheckBillStatusJob(billService), 0, 2, TimeUnit.DAYS);

        servletContext.setAttribute("roomService", roomService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("bidService", bidService);
        servletContext.setAttribute("billService", billService);

        servletContext.setAttribute("userValidator", userValidator);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        scheduler.shutdownNow();

    }

}
