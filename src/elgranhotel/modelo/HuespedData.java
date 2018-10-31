/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgranhotel.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alejandrox33 grupo 6
 */
public class HuespedData {
    private Connection connection = null;
    
//************************CONTRUCTOR DE LA CLASE********************************
    
    
    public HuespedData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
            System.out.println("Conexion Establecida con exito en HuespedData");
            
            
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion");
        }
        
    }
    
//**********************METODO GUARDAR HUESPE***********************************
    
    
   public void guardarHuesped(Huesped huesped){
                  
       
               try {
                   
            String sql = "INSERT INTO huesped (nombre, dni, domicilio, correo,celular) VALUES ( ? , ? , ?, ?, ?);";
                   
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setString(1, huesped.getNombre());
            baseD.setInt(2, huesped.getDni());
            baseD.setString(3, huesped.getDomicilio());
            baseD.setString(4, huesped.getCorreo());
            baseD.setInt(5, huesped.getCelular());
            
            baseD.executeUpdate();
            
            ResultSet resul = baseD.getGeneratedKeys();
            
            if (resul.next()) {
                huesped.setId(resul.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un huesped");
            }
            baseD.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar un huesped: " + ex.getMessage());
        }
   }     
   
   //********************METODO PARA BUSCAR UN HUESPED**************************
   
   public Huesped buscarHuesped(int id){
         Huesped huesped = null;
    
    
    try {
            
            String sql = "SELECT * FROM huesped WHERE id_huesped =?;";

            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, id);
           
            
            ResultSet resultado = baseD.executeQuery();
            
            while(resultado.next()){
                huesped = new Huesped();
                huesped.setId(resultado.getInt("id_huesped"));
                huesped.setNombre(resultado.getString("nombre"));
                huesped.setDni(resultado.getInt("dni"));
                huesped.setDomicilio(resultado.getString("domicilio"));
                huesped.setCorreo(resultado.getString("correo"));

                
            }      
            baseD.close();
                                  
                
        } catch (SQLException ex) {
            System.out.println("Error al buscar un huesped: " + ex.getMessage());
        }
        
        return huesped;
    }
   
   
   //********************METODO OBTENER UN HUESPED******************************
   
   
   public List<Huesped> obtenerHuesped(){
       List<Huesped> huespeds = new ArrayList<>();
       
       
        try {
            
            String sql = "SELECT id_huesped, nombre, dni, domicilio, correo FROM huesped; ";
            PreparedStatement baseD = connection.prepareCall(sql);
            ResultSet resultado = baseD.executeQuery();
            Huesped huesped;
            while(resultado.next()){
                huesped = new Huesped();
                huesped.setId(resultado.getInt("id_huesped"));
                huesped.setNombre(resultado.getString("nombre"));
                huesped.setDni(resultado.getInt("dni"));
                huesped.setDomicilio(resultado.getString("domicilio"));
                huesped.setCorreo(resultado.getString("correo"));
                
                huespeds.add(huesped);
            }
            baseD.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los huespedes: " + ex.getMessage());
        }
       
        return huespeds;
   }
  
   
  //*****************METODO ACTUALIZAR UN HUESPED*******************************
   
   
   public void actualizarHuesped(Huesped huesped){
      
                try {
            
            String sql = "UPDATE huesped SET nombre = ?, dni = ?, domicilio = ?, correo = ? WHERE id_huesped =?;";
            
            PreparedStatement datos = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            datos.setString(1, huesped.getNombre());
            datos.setInt(2, huesped.getDni());
            datos.setString(3, huesped.getDomicilio());
            datos.setString(4, huesped.getCorreo());
            datos.setInt(5, huesped.getId_huesped());
            
            datos.executeUpdate();
            datos.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un huesped: " + ex.getMessage());
        }
   }
   
   
 //*********************METODO BORRAR UN HUESPED********************************
   
   
   public void borrarHuesped(int id){
    try {
            
            String sql = "DELETE FROM huesped WHERE id_huesped =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un huesped: " + ex.getMessage());
        }
        
    
    }
   
}
