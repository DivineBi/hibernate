package fr.ensitech;

import fr.ensitech.model.IUserDao;
import fr.ensitech.model.UserDao;
import fr.ensitech.utils.Dates;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class MainGetNomPrenomEmailByDateNaissance {
    public static void main(String[] args) {

        try {
            IUserDao userDao = new UserDao();
            Map<String, List<String>> map = userDao.getNomPrenomEmailByDateNaissance(Dates.convertStringToDate("25/12/1930"),
                    Dates.convertStringToDate("03/11/2000"));
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Email : " + entry.getKey());
                System.out.println("  -> Nom :  " + entry.getValue().get(0));
                System.out.println("  -> Prénom :  " + entry.getValue().get(1));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}