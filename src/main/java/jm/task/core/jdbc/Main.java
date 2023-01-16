package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vova", "Pupkin", (byte) 31);
        userService.saveUser("Petr", "Pupkin", (byte) 25);
        userService.saveUser("Igor", "Pupkin", (byte) 21);
        userService.saveUser("Oleg", "Pupkin", (byte) 17);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
