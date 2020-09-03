/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Amilcar Cortez
 */
public class ClienteDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    private Cliente cliente;
 
    /*public boolean agregarCliente(Cliente cliente){
         boolean agregado=false;
        try {
           con = cn.getConection(); 
           if(con!=null){
               Statement st;
    st = con.createStatement();
    st.executeUpdate("INSERT INTO cliente('Dni','Nombres','Direccion','Email','Password') VALUES ('"+cliente.getDni()+"','"+cliente.getNombres()+"',"+cliente.getDireccion()+",'"+cliente.getEmail()+"','"+cliente.getPass()+"')");
    agregado=true;
   }
        } catch (Exception e) {
             agregado=false;
   e.printStackTrace();
        }
        return agregado;
    }*/
    
   /* public void insertar(String dni,String nombres,String direccion,String email,String pass) {
        String sql = "INSERT INTO cliente(Dni,Nombres,Direccion,Email,Password) values (?,?,?,?,?)";
        Cliente c = new Cliente();
        try {
            con = cn.getConection();
            //Statement st;
            //st = con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setString(1,c.getDni());
            ps.setString(2,c.getNombres());
            ps.setString(3,c.getDireccion());
            ps.setString(4,c.getEmail());
            ps.setString(5,c.getPass());
          // st.executeUpdate("INSERT INTO cliente('Dni','Nombres','Direccion','Email','Password') VALUES ('"+cliente.getDni()+"','"+cliente.getNombres()+"',"+cliente.getDireccion()+",'"+cliente.getEmail()+"','"+cliente.getPass()+"')");
          boolean ejecucion =cn.ejecutarSQL(ps);
            if (ejecucion == true) {
                System.out.println("Ropa registrado correctamente");
            } else if (ejecucion == false) {
                System.out.println("Error al guardar ropa");
            }

        } catch (Exception e) {
             System.out.println("error al insertar"+e);
        }

    }*/
    
    public ClienteDAO(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    public void registrar() throws SQLException{
     
        try {
            Statement st = this.cn.getConection().createStatement();
            st.executeUpdate("INSERT INTO cliente(Dni, Nombres, Direccion, Email, Password) VALUES ("+cliente.getDni()+", '"+cliente.getNombres()+"', '"+cliente.getDireccion()+"', '"+cliente.getEmail()+"', '"+cliente.getPass()+"')");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
