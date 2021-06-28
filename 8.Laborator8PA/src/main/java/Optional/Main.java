package Optional;

import Compulsory.Entities.Movie;
import Optional.DAO.DAOCreator;
import Optional.DAO.MovieDAO;
import Optional.DAO.ActorDAO;
import Optional.DAO.MoviesActorsDAO;
import Optional.Entities.Actor;
import Optional.Entities.Genres;
import Optional.Entities.Movies;
import Optional.Entities.Relation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

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

            var obj=new Movies(9999999,"TestObjCreatorMovie",new Date(1997,11,18),999,999);
            var testDao=new DAOCreator<Movies>(obj);

            var obj2=new Genres(99,"TestObjCreatorGenre");
            var testDao2=new DAOCreator<Genres>(obj2);

//            testDao.createObj(obj);
//            testDao2.createObj(obj2);
            System.out.println(testDao.findObjById(9999999));





//            //var actorDAO=new ActorDAO();
//            //actorDAO.createActor(new Actor(1,"Leonardo DiCaprio"));
//            //var movAcDAO=new MoviesActorsDAO();
//            //movAcDAO.createMoviesActors(new Relation(1,1));
//            //actorDAO.createActor(new Actor(2,"Kate Winslet"));
//            //var movAcDAO=new MoviesActorsDAO();
//            //movAcDAO.createMoviesActors(new Relation(1,2));
//            var movieActorsDAO = new MoviesActorsDAO();
//            System.out.println(movieActorsDAO.getMovieActors(1));
//
//            String csvFilePath = "C:\\Users\\Robert\\OneDrive\\Desktop\\IMDb movies.csv";
//
//            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
//            String lineText = null;
//
//            int count = 0;
//
//            lineReader.readLine(); // skip header line
//
//            while ((lineText = lineReader.readLine()) != null) {
//                String[] aux = lineText.split("\"");
//                ArrayList<String> arr = new ArrayList<String>();
//                for (int i = 0; i < aux.length; i += 2) {
//                    String[] variable = aux[i].split(",");
//                    for (int j = 0; j < variable.length; ++j) {
//                        arr.add(variable[j]);
//                    }
//                    if (i + 1 < aux.length)
//                        arr.add(aux[i + 1]);
//                }
//
//                while (arr.contains("")) {
//                    arr.remove("");
//                }
//
//                int movie_id=Integer.parseInt(arr.get(0).substring(2));
//                String title=arr.get(1);
//                String[] date = arr.get(2).split("-");
//                int[]releaseDate={Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2])};
//                String[]Genres=arr.get(3).split(",");
//                int duration=Integer.parseInt(arr.get(4));
//                int vote= (int) Double.parseDouble(arr.get(7));
//
//                // dao.createMovie(new Movie(3,"Titanic3",new Date(1997,11,18),195,4));
//                dao.createMovie(new Movie(
//                        movie_id,
//                        title,
//                        new Date(releaseDate[0],releaseDate[1],releaseDate[2]),
//                        duration,
//                        vote
//                ));
//
//            }

        } catch (SQLException e/*| IllegalAccessException e*/) {
            System.out.println(e);}
//        } catch (IOException e) {
//            System.out.println(e);
//        }

    }
}
