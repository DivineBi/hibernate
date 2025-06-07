package fr.ensitech;

import fr.ensitech.entity.User;
import fr.ensitech.model.IUserDao;
import fr.ensitech.model.UserDao;

import java.util.List;

public class MainGetAllUsers {
    public static void main(String[] args) {
        try {

            IUserDao userDao = new UserDao();
            List<User> users = userDao.getAllUsers();
            users.forEach(System.out::println);
    //        users.forEach(u -> System.out.println(u));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
