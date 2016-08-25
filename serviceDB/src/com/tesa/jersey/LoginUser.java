package com.tesa.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tesa.jersey.DBConnection;
import com.tesa.jersey.Utility;
import com.tesa.model.User;

@Path("LoginUser")
public class LoginUser {

	// HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/LoginUser/doLoginUser
    @Path("/doLoginUser")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public String doLoginUser(@QueryParam("userName") String userName, @QueryParam("userPassword") String userPassword){
    	String response = "";
    	User user = checkUser(userName, userPassword);
        if(user.get_id()>0){
            response = Utility.constructJSON(true, user.get_id(), user.get_name(), user.get_password());
        }else{
            response = Utility.constructJSON("login", false, "Incorrect name or Password");
        }
    return response;
 
    }
    
    private User checkUser(String userName, String userPassword){
    	System.out.println("Inside checkCredentials");
        User result = new User();
        if(Utility.isNotNull(userName) && Utility.isNotNull(userPassword)){
            try {
                result = DBConnection.checkLogin(userName, userPassword);
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside Login catch");
                result = null;
            }
        }else{
            System.out.println("Inside checkLogin else");
            result = null;
        }
 
        return result;
    }
	
}
