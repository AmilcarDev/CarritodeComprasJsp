/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Amilcar Cortez
 */
public class Conexion {
    
    Connection con;
    String url="jdbc:mysql://localhost:3306/bdcarritocompras";
    String user="root";
    String pass="";
    public Connection getConection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
        }
        return con;
    }
    
     public boolean ejecutarSQL(PreparedStatement sentencia){
    
        try {
            sentencia.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error al ejecuatar");
            return false;
        }
    }
    
    
    
}
