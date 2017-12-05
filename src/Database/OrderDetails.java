package Database;

import Data.WorkDetails;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
    
    private Connection con=null;
    
    public OrderDetails(){
        con= DbConnection.getConnection();
    }
    
    public Boolean updateUser(WorkDetails workDetails){
        String sqlQuery="UPDATE ORDERDETAILS SET STATUS=? WHERE ORDERID=? ";
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            stmt.setString(1, workDetails.getStatus());
            stmt.setLong(2, workDetails.getId());
            
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
    
    
 
    
    
    public List<WorkDetails> getWorkById(Long workId){
        
        String sqlQuery="SELECT * FROM ORDERDETAILS WHERE WORKID=?";
        
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            stmt.setLong(1, workId);
            
            ResultSet resultSet=stmt.executeQuery();
            int count=0;
            
            List<WorkDetails> listOfWork=new ArrayList<WorkDetails>();
            
            while(resultSet.next()){
                WorkDetails userFound=new WorkDetails();
                userFound.setId(resultSet.getLong("WORKID"));
                List<String> list=new ArrayList<String>();
                list.add(resultSet.getString("APPLIANCE"));                       
                userFound.setAppliances(list);
                userFound.setStatus(resultSet.getString("STATUS"));
                count++;
                listOfWork.add(userFound);
            }
            
            if(count>=1){
                return listOfWork;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }finally{
            DbConnection.closeConnection();
        }
        return null;
    }
    
       public Boolean createNewWorkDetails(WorkDetails workDetails){
        
        int count=0;
        
        for(String appliance: workDetails.getAppliances()){
            
            String sqlQuery="INSERT INTO ORDERDETAILS(WORKID,APPLIANCES,STATUS) VALUES(?,?,?)";
            try{   

                PreparedStatement stmt=con.prepareStatement(sqlQuery);
                stmt.setLong(1, workDetails.getWorkId());
                stmt.setString(2, appliance);
                stmt.setString(3, workDetails.getStatus());
                
                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    count++;
                }else{
                    return false;
                }

            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            
        }
        
        DbConnection.closeConnection();
        if(count>0)
            return true;
        else
            return false;
        
        
    }

    public List<WorkDetails> getWorkById(Long workId,Long engineerId){
        String sqlQuery="SELECT * FROM ORDERDETAILS WD JOIN ORDERS W ON WD.WORKID=W.ID AND WORKID=? AND ENGINEERID=?";
        
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            stmt.setLong(1, workId);
            stmt.setLong(2, engineerId);
            
            ResultSet resultSet=stmt.executeQuery();
            int count=0;
            
            List<WorkDetails> listOfWork=new ArrayList<WorkDetails>();
            
            while(resultSet.next()){
                WorkDetails userFound=new WorkDetails();
                userFound.setId(resultSet.getLong("WORKID"));
                List<String> list=new ArrayList<String>();
                list.add(resultSet.getString("APPLIANCE"));                       
                userFound.setAppliances(list);
                userFound.setStatus(resultSet.getString("STATUS"));
                count++;
                listOfWork.add(userFound);
            }
            
            if(count>=1){
                return listOfWork;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }finally{
            DbConnection.closeConnection();
        }
        return null;
    }
    
}
