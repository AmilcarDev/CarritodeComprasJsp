/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Amilcar Cortez
 */
public class Cliente {
    
    int id;
    String Dni;
    String Nombres;
    String Direccion;
    String Email;
    String Pass;

    public Cliente() {
    }

    public Cliente(int id, String Dni, String Nombres, String Direccion, String Email, String Pass) {
        this.id = id;
        this.Dni = Dni;
        this.Nombres = Nombres;
        this.Direccion = Direccion;
        this.Email = Email;
        this.Pass = Pass;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    
    
    
}
