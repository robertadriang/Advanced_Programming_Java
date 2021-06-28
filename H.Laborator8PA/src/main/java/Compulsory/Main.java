package Compulsory;

import Compulsory.DAO.MovieDAO;

import java.sql.SQLException;

/*Create a relational database using any RDBMS (Oracle, Postgres, MySql, Java DB, etc.).
Write an SQL script that will create the following tables:
movies: id, title, release_date, duration, score
genres: id, name (for example: Action, Drama)
an associative (junction) table in order to store each movie genres
Update pom.xml, in order to add the database driver to the project libraries.
Create a singleton class in order to manage a connection to the database.
Create DAO classes that offer methods for creating movies and genres, and finding them by their ids and names;
Implement a simple test using your classes.
*/
public class Main {
    public static void main(String[] args) {
        try {
            var dao = new MovieDAO();
            // dao.createMovie(new Movie(3,"Titanic3",new Date(1997,11,18),195,4));
            System.out.println(dao.findMovieById(1));
            System.out.println(dao.findMovieByName("Titanic"));


        }catch(SQLException e){
            System.out.println(e);
        }

    }
}
