package Optional.DAO;

import Compulsory.DBConnection;
import Optional.Entities.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActorDAO {
    private PreparedStatement createStatement;

    public ActorDAO() throws SQLException{
        Connection connection= DBConnection.getDBInstance().getConnection();
        String createSql="INSERT INTO actors VALUES(?,?)";
        createStatement=connection.prepareStatement(createSql);
    }

    public void createActor(Actor actor) throws SQLException{
        createStatement.setInt(1,actor.getId());
        createStatement.setString(2,actor.getName());
        createStatement.execute();
    }
}
