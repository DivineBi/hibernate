package fr.ensitech;

import fr.ensitech.entity.Adresse;
import fr.ensitech.entity.User;
import fr.ensitech.model.IUserDao;
import fr.ensitech.model.UserDao;

public class MainGetUser {
    public static void main(String[] args) {
        try {

            IUserDao userDao = new UserDao();
            User user = userDao.getUser(3);
            System.out.println(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
