package com.emergentes.controlador;

import com.emergentes.modelo.publicacion;
import com.emergentes.utiles.conn_DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BlogControlador", urlPatterns = {"/BlogControlador"})
public class BlogControlador extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String opc;
        opc =(request.getParameter("opc") != null) ? request.getParameter("opc"): "list";
        ArrayList<publicacion> lista = new ArrayList <publicacion>();
        
        conn_DB canal = new conn_DB();
        Connection conn = canal.conectar();
        
        PreparedStatement ps;
        ResultSet rs;
        
        if(opc.equals("list")){
            try {
                String sql = "select * from contenido";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()){
                    publicacion publi = new publicacion();
                    publi.setId(rs.getInt("id"));
                    publi.setTitulo(rs.getString("titulo"));
                    publi.setDescripcion(rs.getString("descripcion"));
                    publi.setAutor(rs.getString("autor"));
                    publi.setFecha(rs.getString("fecha"));
                    
                    lista.add(publi);  
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request,response );
                
            } catch (SQLException ex) {
                System.out.println("Error en sql" + ex.getMessage());
            }finally{
                canal.desconectar();
            }   
        }
        if(opc.equals("nuevo")){
            publicacion p = new publicacion();
            request.setAttribute("publicacio", p);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(opc.equals("editar")){
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                
                String sql = "select * from contenido where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                publicacion pu = new publicacion();
                
                while(rs.next()){
                
                    pu.setId(rs.getInt("id"));
                    pu.setTitulo(rs.getString("Titulo"));
                    pu.setDescripcion(rs.getString("Descripcion"));
                    pu.setAutor(rs.getString("Titulo"));
                    pu.setFecha(rs.getString("Fecha"));
                }
                request.setAttribute("publicacion", pu);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error en SQL" + ex.getMessage());
            }
        }
        if(opc.equals("eliminar")){
            try {
                int id = Integer.parseInt(request.getParameter(("id")));
                
                String sql = "delete from contenido where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error del SQL" + ex.getMessage());
            }finally{
                canal.desconectar();
            }
            response.sendRedirect("BlogControlador"); 
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
       int  id = Integer.parseInt(request.getParameter("id"));
       String titulo=request.getParameter("titulo");
       String descripcion = request.getParameter("descripcion ");
       String autor = request.getParameter("autor");
       String fecha = request.getParameter("fecha");
       
       publicacion p = new publicacion();
       p.setId(id);
       p.setTitulo(titulo);
       p.setDescripcion(descripcion);
       p.setAutor(autor);
       p.setFecha(fecha);
       
       conn_DB canal = new conn_DB  ();
       Connection conn = canal.conectar();
       PreparedStatement ps;
       ResultSet rs;
       
       if(id == 0){
           try {
               String sql = "insert into contenido(titulo, descripcion, autor, fecha) values (?, ?, ?, ?)";
               ps = conn.prepareStatement(sql);
               ps.setString(1, p.getTitulo());
               ps.setString(2, p.getDescripcion());
               ps.setString(3, p.getAutor());
               ps.setString(4, p.getFecha());
               
               ps.executeUpdate();
               
           } catch (SQLException ex) {
               System.out.println("Error de sql" + ex.getMessage());           
           }finally{
               canal.desconectar();
               }
           response.sendRedirect("BlogControlador");
       } 
    
       else{
           try {
               String sql ="update contenido set titulo=?, descripcion=?, autor=?, fecha=? where id=?";
               ps = conn.prepareStatement(sql);
               ps.setString(1, p.getTitulo());
               ps.setString(2, p.getDescripcion());
               ps.setString(3, p.getAutor());
               ps.setString(4, p.getFecha());
               ps.setInt(5, p.getId());
               ps.executeUpdate();
           } catch (SQLException ex) {
               System.out.println("Error al actualizar"+ex.getMessage());
           }
           response.sendRedirect("BlogControlador");
       }
    }
}
