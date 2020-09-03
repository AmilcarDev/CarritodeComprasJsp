/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import config.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Carrito;
import modelo.Cliente;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.ClienteDAO;

/**
 *
 * @author Amilcar Cortez
 */
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ProductoDAO pdao = new ProductoDAO();
    Producto p = new Producto();
    List<Producto> productos = new ArrayList();
    List<Carrito> listacarrito = new ArrayList();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;

    int idp;
    Carrito car;
    Conexion cn = new Conexion();
    
    private Cliente cliente;
    private ClienteDAO clientedao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String accion = request.getParameter("accion");
        productos = pdao.listar();
        switch (accion) {
            
            case "ActualizarCantidad":
                int idpro=Integer.parseInt(request.getParameter("idp"));
                int cant=Integer.parseInt(request.getParameter("cantidad"));
                for (int i = 0; i < listacarrito.size(); i++) {
                    if(listacarrito.get(i).getIdProducto()==idpro){
                        listacarrito.get(i).setCantidad(cant);
                        double st=listacarrito.get(i).getPrecioCompra()*cant;
                        listacarrito.get(i).setSubTotal(st);
                    }
                }
                break;

            case "Comprar":
                totalPagar = 0.0;
                int idp = Integer.parseInt(request.getParameter("id"));
                // pdao.listarId(idp);
                p = pdao.listarId(idp);
                item = item + 1;
                car = new Carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(p.getPrecio() * cantidad);
                listacarrito.add(car);
                for (int i = 0; i < listacarrito.size(); i++) {
                    totalPagar = totalPagar + listacarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listacarrito);
                request.setAttribute("contador", listacarrito.size());
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;

            case "AgregarCarrito":
                int pos = 0;
                cantidad = 1;
                idp = Integer.parseInt(request.getParameter("id"));
                // pdao.listarId(idp);
                p = pdao.listarId(idp);
                if (listacarrito.size() > 0) {
                    for (int i = 0; i < listacarrito.size(); i++) {
                        if (idp == listacarrito.get(i).getIdProducto()) {
                            pos = i;
                        }
                    }

                    if (idp == listacarrito.get(pos).getIdProducto()) {
                        cantidad = listacarrito.get(pos).getCantidad() + cantidad;
                        double subtotal = listacarrito.get(pos).getPrecioCompra() * cantidad;
                        listacarrito.get(pos).setCantidad(cantidad);
                        listacarrito.get(pos).setSubTotal(subtotal);
                    } else {
                        item = item + 1;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdProducto(p.getId());
                        car.setNombres(p.getNombres());
                        car.setDescripcion(p.getDescripcion());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(p.getPrecio() * cantidad);
                        listacarrito.add(car);
                    }
                } else {
                    item = item + 1;
                    car = new Carrito();
                    car.setItem(item);
                    car.setIdProducto(p.getId());
                    car.setNombres(p.getNombres());
                    car.setDescripcion(p.getDescripcion());
                    car.setPrecioCompra(p.getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(p.getPrecio() * cantidad);
                    listacarrito.add(car);
                }

                request.setAttribute("contador", listacarrito.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "Delete":
                int idproducto = Integer.parseInt(request.getParameter("idp"));
                for (int i = 0; i < listacarrito.size(); i++) {
                    if (listacarrito.get(i).getIdProducto() == idproducto) {
                        listacarrito.remove(i);
                    }
                }
                break;
            case "Carrito":
                totalPagar = 0.0;
                for (int i = 0; i < listacarrito.size(); i++) {
                    totalPagar = totalPagar + listacarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listacarrito);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
                
                 case "AgregarCliente":
                request.getRequestDispatcher("Cliente.jsp").forward(request, response);
                break;
            case "insertar":
               
               /* try {
                      
                cliente.setDni(request.getParameter("dni"));
                cliente.setNombres(request.getParameter("nombres"));
                cliente.setDireccion(request.getParameter("direccion"));
                cliente.setEmail(request.getParameter("username"));
                cliente.setPass(request.getParameter("password"));                
                clientedao=new ClienteDAO(cliente);
                
                if(request.getParameter("registrarCliente") != null){
                    clientedao.registrar();
                }
                } catch (Exception e) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
                }
               
               
               
                
               request.getRequestDispatcher("Cliente.jsp").forward(request, response);*/
                
                 
                 String dni=request.getParameter("dni");
                 String nom=request.getParameter("nombres");
            
            String dir=request.getParameter("direccion");
            String user=request.getParameter("username");
            String pass=request.getParameter("password");
            
              PreparedStatement sta=cn.getConection().prepareStatement("insert into cliente values(?,?,?,?,?)");
                sta.setString(1, dni);
                sta.setString(2, nom);
                sta.setString(3, dir);
                sta.setString(4, user);
                sta.setString(5, pass);
                sta.executeUpdate();
                
                
                break;
                
                
                
                
            default:

                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
               
               
               
             
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
