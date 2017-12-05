package Database;
import Data.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Naveen
 */
public class Users {
    
    private Connection con=null;
    
    public Users(){
        con= DbConnection.getConnection();
    }
    public Boolean createNewUser(User newUser){
        
        String sqlQuery="INSERT INTO USERS (USERNAME,USERTYPE,PASSWORD,FIRSTNAME,LASTNAME,EMAIL,CONTACTNO) VALUES(?,?,?,?,?,?,?)";
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            stmt.setString(1, newUser.getUserName());
            stmt.setString(2, newUser.getUserType());
            stmt.setString(3, newUser.getPassword());
            stmt.setString(4, newUser.getFirstName());
            stmt.setString(5, newUser.getLastName());
            stmt.setString(6, newUser.getEmail());
            stmt.setString(7, newUser.getContact());
            int rowsInserted = stmt.executeUpdate();
            
            if (rowsInserted > 0) {
                return true;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DbConnection.closeConnection();
        }
        return false;
    }
    
    public User getUserByUserName(String userName,String password){
        String sqlQuery="SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
        
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            
            ResultSet resultSet=stmt.executeQuery();
            int count=0;
            
            User userFound=new User();
            while(resultSet.next()){
                userFound.setFirstName(resultSet.getString("FIRSTNAME"));
                userFound.setUserType(resultSet.getString("USERTYPE"));
                userFound.setPassword(resultSet.getString("PASSWORD"));
                userFound.setLastName(resultSet.getString("LASTNAME"));
                userFound.setEmail(resultSet.getString("EMAIL"));
                userFound.setContact(resultSet.getString("CONTACTNO"));
                userFound.setUserName(resultSet.getString("USERNAME"));
                userFound.setId(resultSet.getLong("USERID"));
                count++;
            }
            
            if(count>=1){
                return userFound;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }finally{
            DbConnection.closeConnection();
        }
        return null;
    }
    
    public User getUserByUserName(String userName){
        String sqlQuery="SELECT * FROM USERS WHERE USERNAME=?";
        
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            stmt.setString(1, userName);
            
            ResultSet resultSet=stmt.executeQuery();
            int count=0;
            
            User userFound=new User();
            while(resultSet.next()){
                userFound.setFirstName(resultSet.getString("FIRSTNAME"));
                userFound.setUserType(resultSet.getString("USERTYPE"));
                userFound.setPassword(resultSet.getString("PASSWORD"));
                userFound.setLastName(resultSet.getString("LASTNAME"));
                userFound.setEmail(resultSet.getString("EMAIL"));
                userFound.setContact(resultSet.getString("CONTACTNO"));
                userFound.setUserName(resultSet.getString("USERNAME"));
                userFound.setId(resultSet.getLong("USERID"));
                count++;
            }
            
            if(count>=1){
                return userFound;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }finally{
            DbConnection.closeConnection();
        }
        return null;
    }
    
    
     
    public Boolean updateUser(User userDetails){
        String sqlQuery="UPDATE USERS SET FIRSTNAME=?, LASTNAME=?, CONTACTNO=?, EMAIL=?, USERTYPE=? WHERE USERID=? ";
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            stmt.setString(1, userDetails.getFirstName());
            stmt.setString(2, userDetails.getLastName());
            stmt.setString(3, userDetails.getContact());
            stmt.setString(4, userDetails.getEmail());
            stmt.setString(5, userDetails.getUserType());
            stmt.setLong(6, userDetails.getId());
            
            int rowsUpdated = stmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                return true;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }finally{
            DbConnection.closeConnection();
        }
        return false;
    }
    
    public List<User> getListOfUsers(){
        String sqlQuery="SELECT * FROM USERS";
        List<User> listOfUsers=new ArrayList<User>();
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            ResultSet resultSet=stmt.executeQuery();
            int count=0;
            
            
            while(resultSet.next()){
                User userFound=new User();
                userFound.setFirstName(resultSet.getString("FIRSTNAME"));
                userFound.setUserType(resultSet.getString("USERTYPE"));
                userFound.setPassword(resultSet.getString("PASSWORD"));
                userFound.setLastName(resultSet.getString("LASTNAME"));
                userFound.setEmail(resultSet.getString("EMAIL"));
                userFound.setContact(resultSet.getString("CONTACTNO"));
                userFound.setUserName(resultSet.getString("USERNAME"));
                userFound.setId(resultSet.getLong("USERID"));
                count++;
                listOfUsers.add(userFound);
            }
            
            if(count>=1){
                return listOfUsers;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }finally{
            DbConnection.closeConnection();
        }
        return null;
    }
}
