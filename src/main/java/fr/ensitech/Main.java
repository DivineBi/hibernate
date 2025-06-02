package fr.ensitech;

import fr.ensitech.model.HibernateConnector;
import org.hibernate.Session;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Test de connexion");

            Session session = HibernateConnector.getSession();
            System.out.println(session);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}