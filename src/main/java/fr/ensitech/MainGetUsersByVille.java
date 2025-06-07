package fr.ensitech;

import fr.ensitech.entity.User;
import fr.ensitech.model.IUserDao;
import fr.ensitech.model.UserDao;

import java.util.Map;
import java.util.Set;

public class MainGetUsersByVille {
    public static void main(String[] args) {
        try {
            IUserDao userDao = new UserDao();
            Map<String, Set<User>> map = userDao.getUsersByVille();
            for (Map.Entry<String, Set<User>> entry : map.entrySet()) {
                System.out.println("ville : " + entry.getKey());
                for (User user : entry.getValue()) {
                    System.out.println("   -> user : " + user);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
