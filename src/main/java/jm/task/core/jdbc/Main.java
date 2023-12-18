package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl lol = new UserServiceImpl();
        lol.createUsersTable();
        lol.saveUser("Иван","Сафронов", (byte) 23);
        lol.saveUser("Boris","Jonson", (byte) 46);
        lol.saveUser("Vladimir","Putin", (byte) 71);
        lol.saveUser("Lev","Tolstoy", (byte) 82);
        List<User> allusers = lol.getAllUsers();
        allusers.forEach(System.out::println);
        lol.cleanUsersTable();
        lol.dropUsersTable();

    }
}
