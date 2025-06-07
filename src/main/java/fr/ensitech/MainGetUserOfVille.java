package fr.ensitech;

import fr.ensitech.entity.User;
import fr.ensitech.model.IUserDao;
import fr.ensitech.model.UserDao;

import java.util.Set;

public class MainGetUserOfVille {
    public static void main(String[] args) {

        try {
            IUserDao userDao = new UserDao();
            Set<User> users = userDao.getUsersOfVille("Cergy");
            users.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
