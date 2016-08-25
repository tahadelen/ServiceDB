package com.tesa.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tesa.jersey.DBConnection;
import com.tesa.jersey.Utility;
import com.tesa.model.Product;

@Path("TransectionProduct")
public class TransectionProduct {
		
	// HTTP Get Method
	@GET
	// Path: http://localhost/<appln-folder-name>/InsertTable/doInsertTable
    @Path("/doInsertProduct")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	public String doInsertProduct(@QueryParam("productName") String productName, @QueryParam("productCat") String productCat, @QueryParam("price") int price){
		String response = "";
		System.out.println("Inside doLogin " + productName );
		int retCode = insertProduct(productName, productCat, price);
		if(retCode == 0){
		response = Utility.constructJSON("insert product",true);
		}else if(retCode == 1){
		response = Utility.constructJSON("insert product",false, "there is a product with this id");
		}else if(retCode == 2){
		response = Utility.constructJSON("insert product",false, "product name entered with sepicial characters");
		}else if(retCode == 3){
		response = Utility.constructJSON("insert product",false, "Error occured");
		}
		return response;
	}
	
	private int insertProduct(String productName, String productCat, int price){
        System.out.println("Inside insertProduct");
        int result = 3;
        Product product = new Product(productName, productCat, null, price);
        if(Utility.isNotNull(productName)){
            try {
                if(DBConnection.insertProduct(product)){
                    System.out.println("insert product if");
                    result = 0;
                }
            } catch(SQLException sqle){
                System.out.println("insert product catch sqle");
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
                System.out.println("Inside insert product catch e ");
                result = 3;
            }
        }else{
            System.out.println("Inside InsertProduct else");
            result = 3;
        }
 
        return result;
    } 
}

