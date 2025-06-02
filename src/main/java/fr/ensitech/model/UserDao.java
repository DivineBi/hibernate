package fr.ensitech.model;

import fr.ensitech.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.RollbackException;
import java.util.List;

public class UserDao implements  IUserDao {

    @Override
    public User createUser(User user) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return user;
        } catch (RollbackException e) {
            tx.rollback();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public User getUser(long id) throws Exception {
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
}
