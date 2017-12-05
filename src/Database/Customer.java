package Database;
import Data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;


public class Customer {
    
    private Connection con=null;
    
    public Customer(){
        con= DbConnection.getConnection();
    }
    
    public Long createNewCustomer(Customer newCustomer){
        
        String sqlQuery="INSERT INTO CUSTOMER (FIRSTNAME,LASTNAME,GENDER,EMAIL,CONTACTNO) VALUES(?,?,?,?,?)";
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery,new String[] { "CUSTOMERID" });
            stmt.setString(1, newCustomer.getFirstName());
            stmt.setString(2, newCustomer.getLastName());
            stmt.setString(3, newCustomer.getGender());
            stmt.setString(4, newCustomer.getEmail());
            stmt.setString(5, newCustomer.getContactNo());
           
           if(stmt.executeUpdate()>0){
               ResultSet set=stmt.getGeneratedKeys();
               if(null != set && set.next()){
                   return set.getLong(1);
               }
           }
            
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DbConnection.closeConnection();
        }
        return null;
    }
    
    public List<Customer> getAllCustomersList(){
        String sqlQuery="SELECT * FROM CUSTOMER";
        List<Customer> listOfCustomers=new ArrayList<Customer>();
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            ResultSet resultSet=stmt.executeQuery();
            int count=0;
            
            
            while(resultSet.next()){
                Customer userFound=new Customer();
                userFound.setFirstName(resultSet.getString("FIRSTNAME"));
                userFound.setLastName(resultSet.getString("LASTNAME"));
                userFound.setId(resultSet.getLong("CUSTOMERID"));
                count++;
                listOfCustomers.add(userFound);
            }
            
            if(count>=1){
                return listOfCustomers;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }finally{
            DbConnection.closeConnection();
        }
        return null;
    }

 
}
