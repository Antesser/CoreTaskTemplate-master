package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        final User user1 = new User("Lola", "Zhek", (byte) 23);
        User user2 = new User("Alex", "Cheburek", (byte) 42);
        User user3 = new User("Kir", "Mek", (byte) 19);
        User user4 = new User("Lera", "Kek", (byte) 43);

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User named " + user1.getName() + " added into the DB.");
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User named " + user2.getName() + " added into the DB.");
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User named " + user3.getName() + " added into the DB.");
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User named " + user4.getName() + " added into the DB.");

        List<User> users = userService.getAllUsers();
        for (User us: users) {
            System.out.println(us);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}