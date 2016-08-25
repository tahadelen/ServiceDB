package com.tesa.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tesa.jersey.DBConnection;
import com.sun.org.apache.regexp.internal.RE;
import com.tesa.jersey.Constants;
import com.tesa.model.*;

public class DBConnection {
	
	/**
     * Method to create DB Connection
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName(Constants.dbClass);
            con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
        } catch (Exception e) {
        	e.printStackTrace();
            throw e;
        } finally {
            return con;
        }
    }
    /**
     * Method to insert a table in DB
     * 
     * @param table
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertTable(Tables table) throws SQLException, Exception {
        boolean insertStatus = false;
        System.out.println("insertUser");
        Connection dbConn = null;
        try {
            try {
            	System.out.println("DB Açýldý");
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	System.out.println("DB Açýlmadý insertUser");
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into table_tables(tableName, floor, tableCustNum, tableStatus, tableImg) values('"
                          + table.get_tableName() + "'," + "'" + table.get_floor() + "','" + table.get_tableCustNum() 
                          + "'," + "'" + table.get_tableStatus() + "','" + table.get_image() + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }  
    
    /**
     * Method to get tables from DB
     * 
     * @param 
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static ArrayList<Tables> getTables() throws SQLException, Exception {
        System.out.println("getTables");
        Connection dbConn = null;
        ArrayList<Tables> tables = new ArrayList<>();
        try {
            try {
            	System.out.println("DB Açýldý");
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	System.out.println("DB Açýlmadý insertProduct");
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM table_tables";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);           
            while (rs.next()) {
                System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3));
                Tables table = new Tables();
                table.set_id(rs.getInt("tableId"));
                table.set_tableName(rs.getString("tableName"));
                table.set_floor(rs.getInt("floor"));
                table.set_tableCustNum(rs.getInt("tableCustNum"));
                table.set_tableStatus(rs.getInt("tableStatus"));
                table.set_image(null);
                tables.add(table);
            }
            
            //System.out.println(records);
            //When record is successfully inserted
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return tables;
    }
    
    /**
     * Method to get categories from DB
     * 
     * @param 
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static ArrayList<Category> getCategories() throws SQLException, Exception {
        System.out.println("getCategories");
        Connection dbConn = null;
        ArrayList<Category> categories = new ArrayList<>();
        try {
            try {
            	System.out.println("DB Açýldý");
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	System.out.println("DB Açýlmadý insertProduct");
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM table_category";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);           
            while (rs.next()) {
                System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3));
                Category category = new Category();
                category.set_id(rs.getInt("catId"));
                category.set_catName(rs.getString("catName"));
                category.set_catImg(null);
                categories.add(category);
            }
            
            //System.out.println(records);
            //When record is successfully inserted
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return categories;
    }
    
    /**
     * Method to insert a table in DB
     * 
     * @param product
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertProduct(Product product) throws SQLException, Exception {
        boolean insertStatus = false;
        System.out.println("insertProduct");
        Connection dbConn = null;
        try {
            try {
            	System.out.println("DB Açýldý");
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	System.out.println("DB Açýlmadý insertProduct");
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into table_products(productName, productCat, img, price) values('"
                          + product.get_productName() + "','" + product.get_productCat() + "','" 
            		      + product.get_img() + "','" + product.get_price() +"')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }  
    
    /**
     * Method to insert a category in DB
     * 
     * @param category
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertCategory(Category category) throws SQLException, Exception {
        boolean insertStatus = false;
        System.out.println("insertCategory");
        Connection dbConn = null;
        try {
            try {
            	System.out.println("DB Açýldý");
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	System.out.println("DB Açýlmadý insertCategory");
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into table_category(catName, catImg) values('"
                          + category.get_catName() + "','" + category.get_catImg() + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }  
    
    /**
     * Method to insert a user in DB
     * 
     * @param user
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertUser(User user) throws SQLException, Exception {
        boolean insertStatus = false;
        System.out.println("insertUser");
        Connection dbConn = null;
        try {
            try {
            	System.out.println("DB Açýldý");
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	System.out.println("DB Açýlmadý insertUser");
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into table_users(userName, userPassword) values('"
                          + user.get_name() + "','" + user.get_password() + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    } 

    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public static User checkLogin(String uname, String pwd) throws Exception {
    	User userTemp = new User();
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM table_users WHERE userName = '" + uname
                    + "' AND userPassword=" + "'" + pwd + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3));
                userTemp.set_id(rs.getInt("userId"));
                userTemp.set_name(rs.getString("userName"));
                userTemp.set_password(rs.getString("userPassword"));
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return userTemp;
    }

}
