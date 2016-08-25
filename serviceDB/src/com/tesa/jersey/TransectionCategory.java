package com.tesa.jersey;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.tesa.jersey.DBConnection;
import com.tesa.jersey.Utility;

import com.tesa.model.Category;

@Path("TransectionCategory")
public class TransectionCategory {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	// HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/InsertTable/doInsertTable
    @Path("/doInsertCategory")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    public String doInsertCategory(@QueryParam("catName") String catName){
		String response = "";
		System.out.println("Inside doLogin " + catName );
		int retCode = insertCategory(catName);
		if(retCode == 0){
		response = Utility.constructJSON("insert category",true);
		}else if(retCode == 1){
		response = Utility.constructJSON("insert category",false, "there is a category with this id");
		}else if(retCode == 2){
		response = Utility.constructJSON("insert category",false, "category name entered with sepicial characters");
		}else if(retCode == 3){
		response = Utility.constructJSON("insert category",false, "Error occured");
		}
		return response;
    }
    
    @GET
	@Path("/getCategory")
	@Produces("application/json")
	public List<Category> getCategory() throws SQLException, Exception {
			return DBConnection.getCategories();
	}
    
    private int insertCategory(String name){
        System.out.println("Inside insertCategory");
        int result = 3;
        Category category = new Category(name,null);
        if(Utility.isNotNull(name)){
            try {
                if(DBConnection.insertCategory(category)){
                    System.out.println("insert category if");
                    result = 0;
                }
            } catch(SQLException sqle){
                System.out.println("insert category catch sqle");
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
                System.out.println("Inside insert category catch e ");
                result = 3;
            }
        }else{
            System.out.println("Inside InsertCategory else");
            result = 3;
        }
 
        return result;
    }    
}
