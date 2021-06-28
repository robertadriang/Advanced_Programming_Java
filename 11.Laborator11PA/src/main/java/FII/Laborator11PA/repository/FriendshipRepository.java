package FII.Laborator11PA.repository;

import FII.Laborator11PA.Pair;
import FII.Laborator11PA.entity.Friendship;
import FII.Laborator11PA.entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FriendshipRepository {

    private Connection connection;

    public FriendshipRepository() {
        try {
            this.connection = DBConnection.getDBInstance().getConnection();
        } catch (SQLException throwables) {
            System.err.println("SQLException in UserRepository constructor...");
            throwables.printStackTrace();
        }
    }

    public Friendship createFriendship(User user1, User user2) {
        String username1 = user1.getUserName();
        String username2 = user2.getUserName();
        String createFriendshipSQL = "INSERT INTO friendship VALUES (?, ?)";
        try {
            PreparedStatement createFriendship = connection.prepareStatement(createFriendshipSQL);
            createFriendship.setString(1, username1);
            createFriendship.setString(2, username2);
            createFriendship.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("SQLException in createFriendship...");
            throwables.printStackTrace();
            return null;
        }

        Friendship friendship = new Friendship();
        friendship.setUser1(username1);
        friendship.setUser2(username2);
        return friendship;
    }

    public Friendship createFriendship(Friendship friendship) {
        String username1 = friendship.getUser1();
        String username2 = friendship.getUser2();
        String createFriendshipSQL = "INSERT INTO friendship VALUES (?, ?)";
        try {
            PreparedStatement createFriendship = connection.prepareStatement(createFriendshipSQL);
            createFriendship.setString(1, username1);
            createFriendship.setString(2, username2);
            createFriendship.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("SQLException in createFriendship...");
            throwables.printStackTrace();
            return null;
        }

        return friendship;
    }

    public Optional<Friendship> getFriendship(User user1, User user2) {
        String username1 = user1.getUserName();
        String username2 = user2.getUserName();
        Optional<Friendship> friendshipOptional = Optional.empty();
        String getFriendshipSQL = "SELECT * FROM friendship WHERE (name1=? OR name2=?) AND (name2=? OR name1=?)";
        try {
            PreparedStatement getFriendship = connection.prepareStatement(getFriendshipSQL);
            getFriendship.setString(1, username1);
            getFriendship.setString(2, username1);
            getFriendship.setString(3, username2);
            getFriendship.setString(4, username2);
            ResultSet result = getFriendship.executeQuery();
            if (result.next()) {
                Friendship foundFriendship = new Friendship();
                foundFriendship.setUser1(result.getString(1));
                foundFriendship.setUser2(result.getString(2));
                friendshipOptional = Optional.of(foundFriendship);
            }
        } catch (SQLException throwables) {
            System.err.println("SQLException in getFriendship...");
            throwables.printStackTrace();
            return Optional.empty();
        }
        return friendshipOptional;
    }

    public Optional<Friendship> getFriendship(Friendship friendship) {
        String username1 = friendship.getUser1();
        String username2 = friendship.getUser2();
        Optional<Friendship> friendshipOptional = Optional.empty();
        String getFriendshipSQL = "SELECT * FROM friendship WHERE (name1=? OR name2=?) AND (name2=? OR name1=?)";
        try {
            PreparedStatement getFriendship = connection.prepareStatement(getFriendshipSQL);
            getFriendship.setString(1, username1);
            getFriendship.setString(2, username1);
            getFriendship.setString(3, username2);
            getFriendship.setString(4, username2);
            ResultSet result = getFriendship.executeQuery();
            if (result.next()) {
                Friendship foundFriendship = new Friendship();
                foundFriendship.setUser1(result.getString(1));
                foundFriendship.setUser2(result.getString(2));
                friendshipOptional = Optional.of(foundFriendship);
            }
        } catch (SQLException throwables) {
            System.err.println("SQLException in getFriendship...");
            throwables.printStackTrace();
            return Optional.empty();
        }
        return friendshipOptional;
    }

    public List<User> getFriendsOfUser(User user) {
        String searchFriendOfUserSQL = "SELECT * FROM friendship WHERE name1=? OR name2=?";
        List<User> friendsOfUser = new ArrayList<>();
        try {
            PreparedStatement searchFriendOfUser = connection.prepareStatement(searchFriendOfUserSQL);
            searchFriendOfUser.setString(1, user.getUserName());
            searchFriendOfUser.setString(2, user.getUserName());
            ResultSet result = searchFriendOfUser.executeQuery();
            while (result.next()) {
                String foundUsername1 = result.getString(1);
                String foundUsername2 = result.getString(2);
                if (foundUsername1.equals(user.getUserName())) {
                    User foundUser2 = new User();
                    foundUser2.setUserName(foundUsername2);
                    friendsOfUser.add(foundUser2);
                } else if (foundUsername2.equals(user.getUserName())) {
                    User foundUser1 = new User();
                    foundUser1.setUserName(foundUsername1);
                    friendsOfUser.add(foundUser1);
                }

            }
        } catch (SQLException throwables) {
            System.err.println("SQLException in getFriendsOfUser...");
            throwables.printStackTrace();
            return null;
        }
        return friendsOfUser;
    }

    public List<Friendship> getAllFriendships() {
        String getAllFriendshipsSQL = "SELECT * FROM friendship";
        List<Friendship> friendshipList = new ArrayList<>();
        try {
            PreparedStatement getAllFriendships = connection.prepareStatement(getAllFriendshipsSQL);
            ResultSet result = getAllFriendships.executeQuery();
            while (result.next()) {
                Friendship friendship = new Friendship();
                friendship.setUser1(result.getString(1));
                friendship.setUser2(result.getString(2));
                friendshipList.add(friendship);
            }
        } catch (SQLException throwables) {
            System.err.println("SQLException in getAllFriendships...");
            throwables.printStackTrace();
            return null;
        }
        return friendshipList;
    }

    public List<User> getTheMostPopularUsers(int k) {
        UserRepository userRepository = new UserRepository();
        List<User> allUsers = userRepository.getAllUsers();
        List<Pair<Integer, User>> mostPopularUsers = new ArrayList<>();
        String getTheMostPopularUsersSQL = "SELECT COUNT(*) from friendship WHERE name1=? OR name2=?";

        try {
            PreparedStatement getTheMostPopularUsers = connection.prepareStatement(getTheMostPopularUsersSQL);
            for (User user : allUsers) {
                String username = user.getUserName();
                getTheMostPopularUsers.setString(1, username);
                getTheMostPopularUsers.setString(2, username);
                ResultSet result = getTheMostPopularUsers.executeQuery();
                if (result.next()) {
                    int nbOfFriends = result.getInt(1);
                    Pair<Integer, User> pair = new Pair<Integer, User>(nbOfFriends, user);
                    mostPopularUsers.add(pair);
                }
            }
        } catch (SQLException throwables) {
            System.err.println("SQLException in getTheMostPopularUsers...");
            throwables.printStackTrace();
            return null;
        }

        mostPopularUsers.sort(new Comparator<Pair<Integer, User>>() {
            @Override
            public int compare(Pair<Integer, User> o1, Pair<Integer, User> o2) {
                if (o1.getL() > o2.getL())
                    return -1;
                else if (o1.getL().equals(o2.getL()))
                    return 0;
                else
                    return 1;
            }
        });

        List<User> mostPopularKUsers = new ArrayList<>();

        for (int i = 0; i < k; i++)
            mostPopularKUsers.add(mostPopularUsers.get(i).getR());

        return mostPopularKUsers;
    }

    public List<User> getTheLeastPopularUsers(int k) {
        UserRepository userRepository = new UserRepository();
        List<User> allUsers = userRepository.getAllUsers();
        List<Pair<Integer, User>> leastPopularUsers = new ArrayList<>();
        String getTheLeastPopularUsersSQL = "SELECT COUNT(*) from friendship WHERE name1=? OR name2=?";

        try {
            PreparedStatement getTheLeastPopularUsers = connection.prepareStatement(getTheLeastPopularUsersSQL);
            for (User user : allUsers) {
                String username = user.getUserName();
                getTheLeastPopularUsers.setString(1, username);
                getTheLeastPopularUsers.setString(2, username);
                ResultSet result = getTheLeastPopularUsers.executeQuery();
                if (result.next()) {
                    int nbOfFriends = result.getInt(1);
                    Pair<Integer, User> pair = new Pair<Integer, User>(nbOfFriends, user);
                    leastPopularUsers.add(pair);
                }
            }
        } catch (SQLException throwables) {
            System.err.println("SQLException in getTheLeastPopularUsers...");
            throwables.printStackTrace();
            return null;
        }

        leastPopularUsers.sort(new Comparator<Pair<Integer, User>>() {
            @Override
            public int compare(Pair<Integer, User> o1, Pair<Integer, User> o2) {
                if (o1.getL() > o2.getL())
                    return 1;
                else if (o1.getL().equals(o2.getL()))
                    return 0;
                else
                    return -1;
            }
        });

        List<User> leastPopularKUsers = new ArrayList<>();

        for (int i = 0; i < k; i++)
            leastPopularKUsers.add(leastPopularUsers.get(i).getR());

        return leastPopularKUsers;
    }

    public void deleteFriendship(User user1, User user2) {
        String username1 = user1.getUserName();
        String username2 = user2.getUserName();

        String deleteFriendshipSQL = "DELETE FROM friendship WHERE name1 = ? AND name2 = ?";
        try {
            PreparedStatement deleteFriendship = connection.prepareStatement(deleteFriendshipSQL);
            deleteFriendship.setString(1, username1);
            deleteFriendship.setString(2, username2);
            deleteFriendship.executeUpdate();
        } catch (SQLException throwables) {
            System.err.println("SQLException in deleteFriendship...");
            throwables.printStackTrace();
        }
    }
}
