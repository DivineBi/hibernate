package fr.ensitech;

import fr.ensitech.entity.User;
import fr.ensitech.model.IUserDao;
import fr.ensitech.model.UserDao;

public class MainDeleteUser {
    public static void main(String[] args) {
        try {
            IUserDao userDao = new UserDao();
            User user = userDao.getUser(3);
            if (user != null) {
                userDao.deleteUser(user);
            }

            User _user = userDao.getUser(3);
            if (user != null) {
                System.out.println("Utilisateur trouvé: ");
                System.out.println(_user);


                System.out.println("Utilisateur supprimé avec succès. ");
            } else {
                System.out.println("Aucun Utilisateur trouvé avec l'ID");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression: ");
            e.printStackTrace();
        }
    }
}
