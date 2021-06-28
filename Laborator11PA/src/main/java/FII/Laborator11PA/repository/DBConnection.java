package FII.Laborator11PA.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;

    private static final String url ="jdbc:oracle:thin:@localhost:1521:XE";
    private static final String user="student";
    private static final String password="STUDENT";

    private Connection connection;

    private DBConnection() throws SQLException{
        connection= DriverManager.getConnection(url,user,password);
    }

    public static DBConnection getDBInstance() throws SQLException{
        if(instance==null)
            instance=new DBConnection();
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
