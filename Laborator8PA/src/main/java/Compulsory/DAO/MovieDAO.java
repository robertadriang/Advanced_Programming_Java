package Compulsory.DAO;

import Compulsory.DBConnection;
import Compulsory.Entities.Movie;

import java.sql.*;
import java.util.ArrayList;

public class MovieDAO {

    private PreparedStatement createStatement;
    private PreparedStatement findByIdStatement;
    private PreparedStatement findByNameStatement;

    public MovieDAO() throws SQLException {
        Connection connection = DBConnection.getDBInstance().getConnection();

        String createSql = "INSERT INTO movies VALUES (?, ?, ?, ?, ?)";
        createStatement = connection.prepareStatement(createSql);

        String findByIdSql = "SELECT * FROM movies WHERE id=?";
        findByIdStatement = connection.prepareStatement(findByIdSql);

        String findByNameSql = "SELECT * FROM movies WHERE title LIKE ?";
        findByNameStatement = connection.prepareStatement(findByNameSql);
    }

    public void createMovie(Movie movie) throws SQLException {
        createStatement.setInt(1, movie.getId());
        createStatement.setString(2, movie.getTitle());
        createStatement.setDate(3, movie.getReleaseDate());
        createStatement.setInt(4, movie.getDuration());
        createStatement.setInt(5, movie.getScore());
        createStatement.execute();
    }

    public Movie findMovieById(int id) throws SQLException {
        findByIdStatement.setInt(1, id);
        ResultSet results = findByIdStatement.executeQuery();
        results.next();
        return new Movie(
                results.getInt("id"),
                results.getString("title"),
                results.getDate("release_date"),
                results.getInt("duration"),
                results.getInt("score")
        );
    }
    public ArrayList<Movie> findMovieByName(String name) throws SQLException{
        ArrayList<Movie> result=new ArrayList<Movie>();
        findByNameStatement.setString(1,"%"+name+"%");
        ResultSet results=findByNameStatement.executeQuery();
        System.out.println("test");
        while(results.next()){
            Movie aux= new Movie(
                    results.getInt("id"),
                    results.getString("title"),
                    results.getDate("release_date"),
                    results.getInt("duration"),
                    results.getInt("score")
            );
            result.add(aux);
        }
        return result;
    }
}
