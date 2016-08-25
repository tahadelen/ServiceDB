package com.tesa.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tesa.jersey.DBConnection;
import com.tesa.jersey.Utility;
import com.tesa.model.Tables;

@Path("InsertTable")
public class InsertTable {

	// HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/InsertTable/doInsertTable
    @Path("/doInsertTable")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public String doInsertTable(@QueryParam("tableName") String tableName, @QueryParam("floor") int floor, @QueryParam("tableCustNum") int tableCustNum, 
    		              @QueryParam("tableStatus") int tableStatus){
        String response = "";
        System.out.println("Inside doLogin " + tableName + "  " + floor + " " + tableCustNum);
        int retCode = insertTable(tableName, floor, tableCustNum, tableStatus );
        if(retCode == 0){
            response = Utility.constructJSON("insert table",true);
        }else if(retCode == 1){
            response = Utility.constructJSON("insert table",false, "there is a table with this id");
        }else if(retCode == 2){
            response = Utility.constructJSON("insert table",false, "table name entered with sepicial characters");
        }else if(retCode == 3){
            response = Utility.constructJSON("insert table",false, "Error occured");
        }
        return response;
 
    }
    
    private int insertTable(String name, int floor, int tableCustNum, int tableStatus){
        System.out.println("Inside insertUSER");
        int result = 3;
        Tables table = new Tables(name, floor, tableCustNum, tableStatus, null);
        if(Utility.isNotNull(name) && Utility.isNotNull(Integer.toString(floor))){
            try {
                if(DBConnection.insertTable(table)){
                    System.out.println("insert table if");
                    result = 0;
                }
            } catch(SQLException sqle){
                System.out.println("inserttable catch sqle");
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
                System.out.println("Inside registerUser catch e ");
                result = 3;
            }
        }else{
            System.out.println("Inside InsertUser else");
            result = 3;
        }
 
        return result;
    }
	
}
