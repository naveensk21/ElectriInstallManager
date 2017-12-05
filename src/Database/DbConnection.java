package Database;

import java.sql.*;

/**
 *
 * @author Naveen
 */
public class DbConnection {
    
    private static Connection con=null;
    
    public static Connection getConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","naveen","123");
         }catch(Exception e){ 
             System.out.println(e);
         }
        return con;
    }
        
    public static void closeConnection(){
       try{
        con.close();
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
}
