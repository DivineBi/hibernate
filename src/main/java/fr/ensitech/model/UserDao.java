package fr.ensitech.model;

import fr.ensitech.entity.Adresse;
import fr.ensitech.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

public class UserDao implements IUserDao {

    @Override
    public User createUser(User user) throws Exception {

        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getSession();
            tx = session.beginTransaction();
            session.save(user);
            if (user.getAdresses() != null && !user.getAdresses().isEmpty()) {
                for (Adresse a : user.getAdresses()) {
                    session.save(a);
                }
            }
            tx.commit();
            return user;
        } catch (RollbackException e) {
            tx.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public User getUser(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("l'id doit être > 0 !");
        }
        Session session = null;
        try {
            session = HibernateConnector.getSession();
            User user = (User) session.get(User.class, id);
            return user;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateUser(User user) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getSession();
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();

        } catch (RollbackException e) {
            tx.rollback();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteUser(User user) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getSession();
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (RollbackException e) {
            tx.rollback();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public List<User> getAllUsers() throws Exception {
        Session session = null;
        try {
            session = HibernateConnector.getSession();
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            throw new Exception("Erreur en récupérant les utilisateurs", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }

    @Override
    public List<User> getUsersByNomAndPrenom(String nom, String prenom) throws Exception {
        Session session = null;
        try {
            session = HibernateConnector.getSession();
            TypedQuery<User> query = session.createNamedQuery("User::ByNomAndPrenom", User.class);
            query.setParameter(1, nom);
            query.setParameter(2, prenom);
            return query.getResultList();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public Set<User> getUsersOfVille(String ville) throws Exception {
        Session session = null;
        try {
    //        session = HibernateConnector.getSession();

    //        Query<User> query = session.createQuery("SELECT u FROM User u JOIN Adresse a ON a.ville =:ville", User.class);
    //        query.setParameter("ville", ville);
    //        return query.stream().collect(Collectors.toSet());

            session = HibernateConnector.getSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> user = query.from(User.class);

            query.select(user).where(cb.equal(user.join("adresses").get("ville").as(String.class), ville));
            return session.createQuery(query).stream().collect(Collectors.toSet());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Map<String, Set<User>> getUsersByVille() throws Exception {
        Session session = null;
        try{
            session = HibernateConnector.getSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<String> query = cb.createQuery(String.class);
            Root<Adresse> adresse = query.from(Adresse.class);
//            query.multiselect(adresse.get("ville").as(String.class)).groupBy(adresse.get("ville").as(String.class));
            query.select(adresse.get("ville").as(String.class)).distinct(true).orderBy(cb.asc(adresse.get("ville").as(String.class)));
            List<String> viles = session.createQuery(query).list();
            Map<String, Set<User>> map = new HashMap<>();
            for (String v : viles) {
                Set<User> users = getUsersOfVille(v);
                map.put(v, users);
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, List<String>> getNomPrenomEmailByDateNaissance(Date dateInf, Date dateSup) throws Exception {
//        Session session = null;
//        try {
//            session = HibernateConnector.getSession();
//            Criteria criteria = session.createCriteria(User.class);
//            List<User> users = criteria.add(Restrictions.between("dateNaissance", dateInf, dateSup)).list();
//            Map<String, List<String>> map = new HashMap<>();
//            for (User user : users) {
//                List<String> nomPrenom = new ArrayList<>();
//                nomPrenom.add(0, user.getNom());
//                nomPrenom.add(1, user.getPrenom());
//                map.put(user.getEmail(), nomPrenom);
//            }
//            return map;
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
        Session session = null;
        try {
            session = HibernateConnector.getSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> user = query.from(User.class);
            query.select(user).where(cb.between(user.get("dateNaissance"), dateInf, dateSup))
                    .orderBy(cb.asc(user.get("nom").as(String.class)));
            List<User> users = session.createQuery(query).list();
            Map<String, List<String>> map = new HashMap<>();
            for (User u : users) {
                List<String> nomPrenom = new ArrayList<>();
                nomPrenom.add(0, u.getNom());
                nomPrenom.add(1, u.getPrenom());
                map.put(u.getEmail(), nomPrenom);
            }
            return map;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }
}