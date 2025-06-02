package fr.ensitech.model;

import fr.ensitech.entity.User;

import java.util.List;

public interface IUserDao {
    User createUser(User user) throws  Exception;
    User getUser(long id) throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUser(User user) throws Exception;
    List<User> getAllUsers() throws Exception;

}
