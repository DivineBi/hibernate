package fr.ensitech;

import fr.ensitech.entity.User;
import fr.ensitech.model.IUserDao;
import fr.ensitech.model.UserDao;

public class MainUpdateUser {
    public static void main(String[] args) {
        try {
            IUserDao userDao = new UserDao();
            User user = userDao.getUser(3);

            user.setNom(user.getNom().concat("-MAJ"));
            user.setPrenom(user.getPrenom().concat("-MAJ"));
            userDao.updateUser(user);

            User _user = userDao.getUser(3);
            System.out.println(_user);


            } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
