package fr.ensitech.model;

import fr.ensitech.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IUserDao {
    User createUser(User user) throws  Exception;
    User getUser(long id) throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUser(User user) throws Exception;
    List<User> getAllUsers() throws Exception;
    List<User> getUsersByNomAndPrenom(String nom, String prenom) throws Exception;
    Set<User> getUsersOfVille(String ville) throws Exception;
    Map<String, Set<User>> getUsersByVille() throws Exception;
    Map<String, List<String>> getNomPrenomEmailByDateNaissance(Date dateInf, Date dateSup) throws Exception;
}
