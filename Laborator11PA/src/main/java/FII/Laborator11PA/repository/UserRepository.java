package FII.Laborator11PA.repository;

import FII.Laborator11PA.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private Connection connection;

    public UserRepository() {
        try {
            this.connection = DBConnection.getDBInstance().getConnection();
        } catch (SQLException throwables) {
            System.err.println("SQLException in UserRepository constructor...");
            throwables.printStackTrace();
        }
    }

    public User createUser(User user) {
        String createUserSQL = "INSERT INTO users VALUES (?)";
        PreparedStatement createUser = null;
        try {
            createUser = connection.prepareStatement(createUserSQL);
            createUser.setString(1, user.getUserName());
            createUser.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("SQLException in createUser...");
            throwables.printStackTrace();
            return null;
        }
        return user;
    }

    /////////////////// check if UPDATE query actually works
    public User updateUser(User newUser, String userName) {
        String updateUserSQL = "UPDATE users SET username = ? where username = ?";
        try {
            PreparedStatement updateUser = connection.prepareStatement(updateUserSQL);
            updateUser.setString(1, newUser.getUserName());
            updateUser.setString(2, userName);
            updateUser.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return newUser;
    }

    public List<User> getAllUsers() {
        String getAllUsersSQL = "SELECT username FROM users";
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement getAllUsers = connection.prepareStatement(getAllUsersSQL);
            ResultSet result = getAllUsers.executeQuery();

            while (result.next()) {
                User foundUser = new User();
                foundUser.setUserName(result.getString(1));
                userList.add(foundUser);
            }
        } catch (SQLException throwables) {
            System.err.println("SQLException in getAllUsers...");
            throwables.printStackTrace();
            return null;
        }
        return userList;
    }

    public Optional<User> findUserByUserName(String userName) {
        String findUserByUserNameSQL = "SELECT username FROM users WHERE username = ?";
        Optional<User> userOptional = Optional.empty();
        try {
            PreparedStatement findUserByUserName = connection.prepareStatement(findUserByUserNameSQL);
            findUserByUserName.setString(1, userName);
            ResultSet result = findUserByUserName.executeQuery();
            if (result.next()) {
                User foundUser = new User();
                foundUser.setUserName(result.getString(1));
                userOptional = Optional.of(foundUser);
            }
        } catch (SQLException throwables) {
            System.err.println("SQLException in findUserByUserName...");
            throwables.printStackTrace();
            return Optional.empty();
        }
        return userOptional;
    }

    public void deleteUser(String userName) {
        String deleteUserSQL = "DELETE FROM users WHERE username = ?";
        try {
            PreparedStatement deleteUser = connection.prepareStatement(deleteUserSQL);
            deleteUser.setString(1, userName);
            deleteUser.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("SQLException in deleteUser...");
            throwables.printStackTrace();
        }
    }

}
