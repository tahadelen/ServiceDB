package com.tesa.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tesa.jersey.DBConnection;
import com.tesa.jersey.Utility;
import com.tesa.model.User;


@Path("InsertUser")
public class InsertUser {
	
	// HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doInsertUser")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public String doInsertUser(@QueryParam("userName") String userName, @QueryParam("userPassword") String userPassword){
        String response = "";
        System.out.println("Inside doInsertUser " + userName + "  " + userPassword );
        int retCode = insertUser(userName, userPassword);
        if(retCode == 0){
            response = Utility.constructJSON("insert user",true);
        }else if(retCode == 1){
            response = Utility.constructJSON("insert user",false, "there is a user with this id");
        }else if(retCode == 2){
            response = Utility.constructJSON("insert user",false, "user name entered with sepicial characters");
        }else if(retCode == 3){
            response = Utility.constructJSON("insert user",false, "Error occured");
        }
        return response;
 
    }
    
    private int insertUser(String userName, String userPassword){
        System.out.println("Inside insertUSER");
        int result = 3;
        User user = new User(userName, userPassword);
        if(Utility.isNotNull(userName) && Utility.isNotNull(userPassword)){
            try {
                if(DBConnection.insertUser(user)){
                    System.out.println("insert user if");
                    result = 0;
                }
            } catch(SQLException sqle){
                System.out.println("insert user catch sqle");
                //When Primary key violation occurs that means user is already registered
                if(sqle.getErrorCode() == 1062){
                    result = 1;
                } 
                //When special characters are used in name
                else if(sqle.getErrorCode() == 1064){
                    System.out.println(sqle.getErrorCode());
                    result = 2;
                }
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside User catch e ");
                result = 3;
            }
        }else{
            System.out.println("Inside Insert User else");
            result = 3;
        }
 
        return result;
    }
}
