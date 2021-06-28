package Optional.DAO;

import Compulsory.DBConnection;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DAOCreator<T> {

    private PreparedStatement createObjStatement;
    private PreparedStatement findByIdStatement;

    T auxiliary;
    public DAOCreator(T obj) throws SQLException{
        Connection connection = DBConnection.getDBInstance().getConnection();
        auxiliary=obj;
        var aux=obj.getClass().getName().split("\\.");
        String command="INSERT INTO "+aux[aux.length-1]+" VALUES (";

        var fields=obj.getClass().getDeclaredFields();
        int it=0;
        while(it<fields.length){
            command+="?, ";
            ++it;
        }
        command=command.substring(0,command.length()-2)+")";
        createObjStatement = connection.prepareStatement(command);

       String command2 = "SELECT * FROM $tableName WHERE id=?";
        String query=command2.replace("$tableName",aux[aux.length-1]);
        findByIdStatement = connection.prepareStatement(query);
        System.out.println("ceva");
    }

    public void createObj(T obj) throws SQLException, IllegalAccessException {
        var fields=obj.getClass().getDeclaredFields();

        int it=0;
        for(var field:fields){
            String fieldType=field.getType().toString();
            var aux=fieldType.split("\\.");
            fieldType=aux[aux.length-1];
            if(fieldType.equals("int")){
                createObjStatement.setInt(++it,(int)field.get(obj)); // by getter: obj.getClass().getDeclaredmethods().getName().startsWith("get)" & contains (field.getName())
            }
            else if(fieldType.equals("String")){
                createObjStatement.setString(++it,(String)field.get(obj));
            }
            else if(fieldType.equals("Date")){
                createObjStatement.setDate(++it,(Date)field.get(obj));
            }
        }
        createObjStatement.execute();
    }

    public Map<String,Object> findObjById(int id) throws SQLException{
        findByIdStatement.setInt(1,id);
        ResultSet results=findByIdStatement.executeQuery();
        results.next();
        Map<String,Object> ans=new HashMap<>();

        var fields=auxiliary.getClass().getDeclaredFields();
        for(var field:fields){
            String fieldType=field.getType().toString();
            var aux=fieldType.split("\\.");
            fieldType=aux[aux.length-1];
            if(fieldType.equals("int")){
                ans.put(field.getName(),results.getInt(field.getName())); // by getter: obj.getClass().getDeclaredmethods().getName().startsWith("get)" & contains (field.getName())
            }
            else if(fieldType.equals("String")){
                ans.put(field.getName(),results.getString(field.getName()));
            }
            else if(fieldType.equals("Date")){
                if(field.getName().compareTo("releaseDate")==0){/* work-around because we destroyed the naming conventions*/
                    ans.put(field.getName(),results.getDate("release_date"));
                }
                else{
                    ans.put(field.getName(),results.getDate(field.getName()));
                }

            }
        }

        return ans;
    }
}

