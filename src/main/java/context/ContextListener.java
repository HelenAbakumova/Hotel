package context;

import db.JDBCManager;
import db.JDBCManagerUtils;
import repository.UserRepositoryImpl;
import repository.api.RoomRepository;
import repository.RoomRepositoryImpl;
import repository.api.UserRepository;
import service.RoomServiceImpl;
import service.UserServiceImpl;
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


@WebListener
public class ContextListener implements ServletContextListener {
//    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("LISTENER INIT!!!!!!!!!!!");
        ServletContext servletContext = servletContextEvent.getServletContext();
        DataSource dataSource = null;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/hotel");
        } catch (NamingException e) {
            System.out.println("exception!!!");
//            LOGGER.error("Cant get DataSource", e);
        }
        JDBCManagerUtils jdbcManagerUtils = new JDBCManagerUtils(dataSource);
        JDBCManager jdbcManager = new JDBCManager(jdbcManagerUtils);
        RoomRepository roomRepository = new RoomRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        RoomService roomService = new RoomServiceImpl(roomRepository, jdbcManager);
        UserService userService = new UserServiceImpl(userRepository, jdbcManager);

        servletContext.setAttribute("roomService", roomService);
        servletContext.setAttribute("userService", userService);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        LOGGER.info("Context destroyed");
    }

}
