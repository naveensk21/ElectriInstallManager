package Database;


import Data.Work;
import Data.WorkDetails;
import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.*;


public class Orders {
    
    private Connection con=null;
    
    public Orders(){
        con= DbConnection.getConnection();
    }
    
    public Long createNewWorkOrder(Work work){
        String sqlQuery="INSERT INTO ORDERS (CUSTOMERID,WORKTYPE,ADDRESS,WORKDATE,ENGINEERID,WORKSTATUS) VALUES(?,?,?,?,?,?,?)";
        try{   
            PreparedStatement stmt=con.prepareStatement(sqlQuery,new String[] { "ORDERID" });
            stmt.setLong(1, work.getCustomerId());
            stmt.setString(2, work.getWorkType());
            stmt.setString(3, work.getAddress());
            stmt.setTimestamp(4, new Timestamp(work.getWorkDate().getTime()));
            stmt.setLong(5, work.getNoOfRooms());
            stmt.setLong(6, work.getEngineerId());
            stmt.setString(7, work.getWorkStatus());
            
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

    public List<Work> getAllWorks(Long engineerId){
        String sqlQuery="SELECT * FROM ORDERS WHERE ENGINEERID=?";
        List<Work> listOfWork=new ArrayList<Work>();
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            stmt.setLong(1, engineerId);
            
            ResultSet resultSet=stmt.executeQuery();
            int count=0;
            
            
            while(resultSet.next()){
                Work userFound=new Work();
                userFound.setCustomerId(resultSet.getLong("CUSTOMERID"));
                userFound.setWorkType(resultSet.getString("WORKTYPE"));
                userFound.setAddress(resultSet.getString("ADDRESS"));
                userFound.setWorkDate(resultSet.getTimestamp("WORKDATE"));
              
                userFound.setEngineerId(resultSet.getLong("ENGINEERID"));
                userFound.setWorkStatus(resultSet.getString("WORKSTATUS"));
                userFound.setId(resultSet.getLong("ORDERID"));
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
    
        public List<Work> getAllWorks(){
        String sqlQuery="SELECT * FROM ORDERS";
        List<Work> listOfWork=new ArrayList<Work>();
        try{   
            
            PreparedStatement stmt=con.prepareStatement(sqlQuery);
            ResultSet resultSet=stmt.executeQuery();
            int count=0;
            
            
            while(resultSet.next()){
                Work userFound=new Work();
                userFound.setCustomerId(resultSet.getLong("CUSTOMERID"));
                userFound.setWorkType(resultSet.getString("WORKTYPE"));
                userFound.setAddress(resultSet.getString("ADDRESS"));
                userFound.setWorkDate(resultSet.getTimestamp("WORKDATE"));
               
                userFound.setEngineerId(resultSet.getLong("ENGINEERID"));
                userFound.setWorkStatus(resultSet.getString("WORKSTATUS"));
                userFound.setId(resultSet.getLong("ORDERID"));
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
