/**
 * 
 */
package com.shanlin.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author 13073457
 */
public class DbConnection {
    
    public void conn() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("","","");
        
        PreparedStatement ps = connection.prepareCall("");
        ps.executeQuery();
    }
}
