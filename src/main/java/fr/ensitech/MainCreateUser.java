package fr.ensitech;

import fr.ensitech.entity.Adresse;
import fr.ensitech.entity.User;
import fr.ensitech.model.IUserDao;
import fr.ensitech.model.UserDao;
import fr.ensitech.utils.Dates;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainCreateUser {
    public static void main(String[] args) {

        try {
            //System.out.println("Test de connexion à la BDD");
            //Session session = HibernateConnector.getSession();
            //System.out.println(session);



            User user = new User();
            user.setNom("VERNE");
            user.setPrenom("Jules");
            user.setEmail("jules.verne@gmail.com");
            user.setPassword("vernepass");
            user.setDateNaissance(Dates.convertStringToDate("08/02/1928"));
            user.setActif(false);


            Adresse adresse1 = new Adresse();
            adresse1.setNumero("24");
            adresse1.setRue("Avenue du Château");
            adresse1.setVille("Saint-Germain-en-Laye");
            adresse1.setCodePostal("78100");
            adresse1.setUser(user);
            user.getAdresses().add(adresse1);

            Adresse adresse2 = new Adresse();
            adresse2.setNumero("3");
            adresse2.setRue("Rue Jules Verne");
            adresse2.setVille("Nantes");
            adresse2.setCodePostal("44000");
            adresse2.setUser(user);
            user.getAdresses().add(adresse2);



            IUserDao userDao = new UserDao();
            User _user = userDao.createUser(user);
            System.out.println("user crée avec succès.");
            System.out.println(_user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}