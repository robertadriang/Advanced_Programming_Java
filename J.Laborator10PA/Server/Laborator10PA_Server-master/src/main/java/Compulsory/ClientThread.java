package Compulsory;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.mxgraph.layout.*;
import com.mxgraph.util.mxCellRenderer;
import freemarker.template.TemplateException;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class ClientThread extends Thread {
    private static Integer TIMEOUT_TIME = 3600_000; // change with a smaller number to see the results
    private Socket socket = null;

    private boolean logged = false;
    private String username = "";

    private PreparedStatement createUser;
    private PreparedStatement registeredUser;
    private PreparedStatement addFriend;
    private PreparedStatement searchFriendship;
    private PreparedStatement readMessages;
    private PreparedStatement addMessage;
    private PreparedStatement searchFriendOfUser;
    private PreparedStatement getAllUsers;
    private PreparedStatement getAllFriendships;
    private PreparedStatement getMessageExchange;

    public ClientThread(Socket socket) throws SQLException, SocketException {
        this.socket = socket;
        socket.setSoTimeout(TIMEOUT_TIME); /// sec*1000
        Connection connection = DBConnection.getDBInstance().getConnection();
        String createUserSql = "INSERT INTO users VALUES (?)";
        createUser = connection.prepareStatement(createUserSql);

        String registeredUserSql = "SELECT COUNT(*) as registered from users WHERE username=?";
        registeredUser = connection.prepareStatement(registeredUserSql);

        String addFriendSQL = "INSERT INTO friendship VALUES(?,?)";
        addFriend = connection.prepareStatement(addFriendSQL);

        String searchFriendshipSQL = "SELECT COUNT(*) as friend from friendship WHERE (name1=? OR name2=?) AND (name2=? OR name1=?)";
        searchFriendship = connection.prepareStatement(searchFriendshipSQL);

        String addMessageSQL = "INSERT INTO message values(?,?,?)";
        addMessage = connection.prepareStatement(addMessageSQL);

        String searchFriendOfUserSQL = "SELECT * FROM friendship WHERE name1=? OR name2=?";
        searchFriendOfUser = connection.prepareStatement(searchFriendOfUserSQL);

        String readMessageSQL = "SELECT SENDER,MESSAGE_BODY FROM message WHERE receiver=?";
        readMessages = connection.prepareStatement(readMessageSQL);

        String getAllUsersSQL = "SELECT * from users";
        getAllUsers = connection.prepareStatement(getAllUsersSQL);

        String getAllFriendshipsSQL = "SELECT * from friendship";
        getAllFriendships = connection.prepareStatement(getAllFriendshipsSQL);

        String getMessageExchangeSQL = "SELECT COUNT(*) FROM message WHERE (sender=? OR receiver=?) AND (sender=? OR receiver=?)";
        getMessageExchange = connection.prepareStatement(getMessageExchangeSQL);
    }

    public void run() {
        try {
            while (true) {
                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                // Send the response to the oputput stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String raspuns = "";
                if (request.compareTo("stop") == 0) {
                    raspuns = "Server shutdown...";
                    out.println(raspuns);
                    out.flush();
                    Server.stopServerGracefully();
                } else if (request.equals("exit")) {
                    System.out.println("Client disconnected. Stopping thread...");
                    return;
                }
                if (!logged) {
                    if (request.length() > 8 && request.substring(0, 8).equals("register")) {
                        raspuns = "Register received";
                        String name = request.substring(9);

                        registeredUser.setString(1, name);
                        ResultSet result = registeredUser.executeQuery();
                        result.next();
                        if (result.getInt("registered") == 1) {
                            raspuns += " but user is already in the DB";
                        } else {
                            createUser.setString(1, name);
                            System.out.println(name);
                            createUser.execute();
                            raspuns += " user created!";
                        }
                        System.out.println(name);
                        out.println(raspuns);
                        out.flush();
                    } else if (request.length() > 5 && request.substring(0, 5).equals("login")) {
                        raspuns = "Login received";
                        String name = request.substring(6);
                        registeredUser.setString(1, name);
                        ResultSet result = registeredUser.executeQuery();
                        result.next();
                        if (result.getInt("registered") == 1) {
                            raspuns += " user logged in!";
                            username = name;
                            logged = true;
                        } else {
                            raspuns += " user does not exist!";
                        }
                        out.println(raspuns);
                        out.flush();
                    } else {
                        raspuns = "Server received the request \"" + request + "\", but you are not logged in ";
                        out.println(raspuns);
                        out.flush();
                    }
                } else if (logged && request.length() > 6 && request.substring(0, 6).equals("friend")) {
                    String friendList = request.substring(7);
                    var friendArray = friendList.split(",");

                    List<String> added = new ArrayList<>();
                    List<String> existent = new ArrayList<>();
                    List<String> notFound = new ArrayList<>();

                    for (var friend : friendArray) {
                        searchFriendship.setString(1, username);
                        searchFriendship.setString(2, username);
                        searchFriendship.setString(3, friend);
                        searchFriendship.setString(4, friend);
                        ResultSet result = searchFriendship.executeQuery();
                        result.next();
                        if (result.getInt("friend") > 0) {
                            existent.add(friend);
                        } else {
                            registeredUser.setString(1, friend);
                            ResultSet exists = registeredUser.executeQuery();
                            exists.next();
                            if (exists.getInt("registered") == 1) {
                                addFriend.setString(1, username);
                                addFriend.setString(2, friend);
                                addFriend.executeUpdate();
                                added.add(friend);
                            } else {
                                notFound.add(friend);
                            }
                        }
                    }

                    if (added.size() > 0) {
                        raspuns = "The following friends were added: ";
                        for (String name : added) {
                            raspuns += name + " ";
                        }
                    }
                    System.out.println(raspuns);
                    if (existent.size() > 0) {
                        raspuns += " | The following friends were already in your friendlist: ";
                        for (String name : existent) {
                            raspuns += name + " ";
                        }
                    }
                    if (notFound.size() > 0) {
                        raspuns += " | The following names are not registered: ";
                        for (String name : notFound) {
                            raspuns += name + " ";
                        }
                    }
                    System.out.println(raspuns);
                    out.println(raspuns);
                    out.flush();
                } else if (logged && request.length() > 4 && request.substring(0, 4).equals("send")) {
                    String messageBody = request.substring(5);
                    searchFriendOfUser.setString(1, username);
                    searchFriendOfUser.setString(2, username);
                    ResultSet friends = searchFriendOfUser.executeQuery();
                    List<String> friendList = new ArrayList<>();
                    while (friends.next()) {
                        if (friends.getString(1).equals(username))
                            friendList.add(friends.getString(2));
                        else if (friends.getString(2).equals(username))
                            friendList.add(friends.getString(1));
                    }

                    for (String fr : friendList) {
                        addMessage.setString(1, username);
                        addMessage.setString(2, fr);
                        addMessage.setString(3, messageBody);
                        addMessage.executeUpdate();
                    }

                    raspuns = "Sending the following message: \"" + messageBody + "\" to the following friends: ";
                    for (String fr : friendList)
                        raspuns += fr + " ";
                    out.println(raspuns);
                    out.flush();
                } else if (logged && request.length() > 3 && request.substring(0, 4).equals("read")) {
                    readMessages.setString(1, username);
                    ResultSet messages = readMessages.executeQuery();
                    while (messages.next()) {
                        raspuns += messages.getString(1) + " - " + messages.getString(2) + " | ";
                    }
                    System.out.println(raspuns);
                    out.println(raspuns);
                    out.flush();
                } else if (logged && request.length() > 4 && request.substring(0, 5).equals("graph")) {
                    /*
                    G - graf neorientat ponderat
                    V - Toti userii
                    E - Relatiile de prietenie
                    C - Nr de mesaje schimbate intre cei doi
                     */
                    Graph<String, MyEdge> g = new SimpleWeightedGraph<>(MyEdge.class);
                    ResultSet users = getAllUsers.executeQuery();
                    List<String> userList = new ArrayList<>();
                    while (users.next()) {
                        g.addVertex(users.getString("username"));
                        userList.add(users.getString("username"));
                    }
                    ResultSet friendships = getAllFriendships.executeQuery();
                    while (friendships.next()) {
                        getMessageExchange.setString(1, friendships.getString("name1"));
                        getMessageExchange.setString(2, friendships.getString("name1"));
                        getMessageExchange.setString(3, friendships.getString("name2"));
                        getMessageExchange.setString(4, friendships.getString("name2"));
                        ResultSet edgeCost = getMessageExchange.executeQuery();
                        edgeCost.next();
                        MyEdge edge = new MyEdge();
                        g.addEdge(friendships.getString("name1"), friendships.getString("name2"), edge);
                        // g.setEdgeWeight(edge,edgeCost.getInt(1));
                    }

                    JGraphXAdapter<String, MyEdge> graphAdapter = new JGraphXAdapter<>(g);
                    mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
                    layout.execute(graphAdapter.getDefaultParent());

                    BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 10, Color.WHITE, true, null);
                    File imgFile = new File("src/main/java/graph.png");
                    ImageIO.write(image, "PNG", imgFile);

                    out.println(g);
                    out.flush();

                    Report report = new Report(userList, imgFile.getPath());
                    report.call();
                } else if (logged && request.equals("upload")) {
                    STFTPClient stftpClient = new STFTPClient();
                    stftpClient.connect();
                    stftpClient.upload("src/main/java/graph.png", "/");
                    stftpClient.download("/", "src/main/java/graph2.png");
                    stftpClient.disconnect();
                    raspuns = "File uploaded";
                    out.println(raspuns);
                    out.flush();
                } else if (logged && request.equals("my friends")) {
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8081/friendship/" + username;
                    ResponseEntity<String> responseEntity = null;
                    try {
                        responseEntity = restTemplate.getForEntity(url, String.class);
                    } catch (HttpStatusCodeException e) {
                        raspuns = "HttpStatusCodeException...";
                        out.println(raspuns);
                        out.flush();
                    }

                    if (responseEntity.getStatusCode().is2xxSuccessful()) {
                        String responseEntityBody = responseEntity.getBody();
                        assert responseEntityBody != null;
                        String[] arrOfStr = responseEntityBody.split("\"");

                        raspuns = "Friends: ";

                        for (String str : arrOfStr) {

                            if (str.matches("[a-zA-z]+") && !str.contains("user"))
                                raspuns += " | - " + str;
                        }
                        out.println(raspuns);
                        out.flush();
                    } else {
                        raspuns = "Unexpected status code: " + responseEntity.getStatusCodeValue();
                        out.println(raspuns);
                        out.flush();
                    }
                } else if (logged && request.contains("most popular")) {
                    if (request.length() <= 12) {
                        raspuns = "Correct command format: \"most popular k\" i.e. most popular k users.";
                        out.println(raspuns);
                        out.flush();
                    } else {
                        String k = request.substring(13);
                        RestTemplate restTemplate = new RestTemplate();
                        String url = "http://localhost:8081/friendship/mostPopularUsers/" + k;
                        ResponseEntity<String> responseEntity = null;
                        try {
                            responseEntity = restTemplate.getForEntity(url, String.class);
                        } catch (HttpStatusCodeException e) {
                            raspuns = "HttpStatusCodeException...";
                            out.println(raspuns);
                            out.flush();
                        }

                        if (responseEntity.getStatusCode().is2xxSuccessful()) {
                            String responseEntityBody = responseEntity.getBody();
                            assert responseEntityBody != null;
                            String[] arrOfStr = responseEntityBody.split("\"");
                            raspuns = "Most popular users:";
                            for (String str : arrOfStr) {
                                if (str.matches("[a-zA-z]+") && !str.contains("user"))
                                    raspuns += " | - " + str;
                            }
                            out.println(raspuns);
                            out.flush();
                        } else {
                            raspuns = "Unexpected status code: " + responseEntity.getStatusCodeValue();
                            out.println(raspuns);
                            out.flush();
                        }
                    }
                }

                else if (logged && request.contains("least popular")) {
                    if (request.length() <= 12) {
                        raspuns = "Correct command format: \"least popular k\" i.e. least popular k users.";
                        out.println(raspuns);
                        out.flush();
                    } else {
                        String k = request.substring(13);
                        RestTemplate restTemplate = new RestTemplate();
                        String url = "http://localhost:8081/friendship/leastPopularUsers/" + k;
                        ResponseEntity<String> responseEntity = null;
                        try {
                            responseEntity = restTemplate.getForEntity(url, String.class);
                        } catch (HttpStatusCodeException e) {
                            raspuns = "HttpStatusCodeException...";
                            out.println(raspuns);
                            out.flush();
                        }

                        if (responseEntity.getStatusCode().is2xxSuccessful()) {
                            String responseEntityBody = responseEntity.getBody();
                            assert responseEntityBody != null;
                            String[] arrOfStr = responseEntityBody.split("\"");
                            raspuns = "Least popular users:";
                            for (String str : arrOfStr) {
                                if (str.matches("[a-zA-z]+") && !str.contains("user"))
                                    raspuns += " | - " + str;
                            }
                            out.println(raspuns);
                            out.flush();
                        } else {
                            raspuns = "Unexpected status code: " + responseEntity.getStatusCodeValue();
                            out.println(raspuns);
                            out.flush();
                        }
                    }
                }

                else {
                    raspuns = "Server received the request " + request + " but ?????";
                    out.println(raspuns);
                    out.flush();
                }

            }

        } catch (SocketTimeoutException e) {
            System.out.printf("%d seconds passed since the last request. Timeout.", TIMEOUT_TIME / 1000);
        } catch (IOException | SQLException e) {
            System.err.println("Communication error... " + e);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Thread stopped");
                Server.threadCount--;
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}