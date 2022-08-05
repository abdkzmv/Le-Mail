package database.dao.inter;

import database.entity.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserDaoInter {
    User getUser(ResultSet rs);
    List<User> getAllUsers();
    User getByUsername(String username);
    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);
    boolean addUser(User u);
    User generateUser(String name, String username, String email, String password);
    boolean checkUsernamePassword(String username, String password);
    boolean updateUser(User u);
}
