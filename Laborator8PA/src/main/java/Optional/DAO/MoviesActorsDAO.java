package Optional.DAO;

import Compulsory.DBConnection;
import Compulsory.Entities.Movie;
import Optional.Entities.Actor;
import Optional.Entities.MovieActor;
import Optional.Entities.Relation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoviesActorsDAO {
    private PreparedStatement createStatement;
    private PreparedStatement getActorsOfMovieStatement;

    public MoviesActorsDAO() throws SQLException {
        Connection connection= DBConnection.getDBInstance().getConnection();

        String createSql="INSERT INTO movies_actors VALUES(?,?)";
        createStatement=connection.prepareStatement(createSql);

        String getActorsOfMovieSQL = "SELECT actor_id,name FROM (SELECT * FROM (SELECT * FROM movies WHERE id=?)  NATURAL JOIN movies_actors) JOIN actors on actor_id=actors.id";
        getActorsOfMovieStatement=connection.prepareStatement(getActorsOfMovieSQL);
    }

    public void createMoviesActors(Relation relation) throws SQLException{
        createStatement.setInt(1,relation.getId1());
        createStatement.setInt(2,relation.getId2());
        createStatement.execute();
    }

    public ArrayList<Actor> getMovieActors(int id) throws SQLException{
        ArrayList<Actor> result=new ArrayList<Actor>();
        getActorsOfMovieStatement.setInt(1,id);
        ResultSet results=getActorsOfMovieStatement.executeQuery();
        while(results.next()){
            Actor aux=new Actor(
                    results.getInt("actor_id"),
                    results.getString("name")
            );
            result.add(aux);
        }
        return result;
    }
}
