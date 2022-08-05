package database.dao.impl;

import database.dao.inter.AbstractDao;
import database.dao.inter.UserDaoInter;
import database.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    @Override
    public User getUser(ResultSet rs) {
        try {
            Integer id = rs.getInt("id");
            String name = rs.getString("full_name");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("user_password");
            return new User(id,name,username,email,password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from user");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                User u = getUser(rs);
                users.add(u);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getByUsername(String username) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where username = ?");
            stmt.setString(1,username);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if(rs == null)
                return null;
            User u = new User();
            while(rs.next()) {
                u = getUser(rs);
            }
            if(u.getId() == null)
                return null;
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return (getByUsername(username) == null);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email = ?");
            stmt.setString(1,email);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(User u) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user (full_name,username,email,user_password) values (?,?,?,?)");
            stmt.setString(1,u.getName());
            stmt.setString(2,u.getUsername());
            stmt.setString(3,u.getEmail());
            stmt.setString(4,u.getPassword());
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User generateUser(String name, String username, String email, String password) {
        return new User(name,username,email,password);
    }

    @Override
    public boolean checkUsernamePassword(String username, String password) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where username = ? and user_password = ?");
            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User u) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user " +
                    " set " +
                    " full_name = ?, " +
                    " email = ?, " +
                    " user_password = ? " +
                    " where " +
                    "    username = ?");
            stmt.setString(1,u.getName());
            stmt.setString(2,u.getEmail());
            stmt.setString(3,u.getPassword());
            stmt.setString(4,u.getUsername());
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
