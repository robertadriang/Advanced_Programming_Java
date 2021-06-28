package Compulsory.DAO;

import Compulsory.DBConnection;
import Compulsory.Entities.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenreDAO {
    private PreparedStatement createStatement;
    private PreparedStatement findByIdStatement;
    private PreparedStatement findByNameStatement;

    public GenreDAO() throws SQLException{
        Connection connection= DBConnection.getDBInstance().getConnection();

        String createSql="INSERT INTO genres VALUES(?,?)";
        createStatement=connection.prepareStatement(createSql);

        String findByIdSql="SELECT * FROM genres WHERE id=?";
        findByIdStatement=connection.prepareStatement((findByIdSql));

        String findByNameSql="SELECT * FROM genres WHERE name=?";
        findByNameStatement=connection.prepareStatement(findByNameSql);
    }

    public void createGenre(Genre genre) throws SQLException{
        createStatement.setInt(1,genre.getId());
        createStatement.setString(2,genre.getName());
        createStatement.execute();
    }

    public Genre findGenreById(int id) throws SQLException{
        findByIdStatement.setInt(1,id);
        ResultSet results=findByIdStatement.executeQuery();
        results.next();
        return new Genre(
                results.getInt("id"),
                results.getString("name")
        );
    }

    public ArrayList<Genre> findGenreByname(String name) throws SQLException{
        ArrayList<Genre> ans=new ArrayList<Genre>();
        findByNameStatement.setString(1,name);
        ResultSet results=findByNameStatement.executeQuery();
        while(results.next()){
            Genre aux=new Genre(
                    results.getInt("id"),
                    results.getString("name")
            );
            ans.add(aux);
        }
        return ans;
    }
}
