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
 * @author Alejandrox33 grupo 6
 */

public class HabitacionData {
    private Connection connection = null;
    private Conexion conexion;
    
                    
//*********************CONSTRUTOR DE LA CLASE***********************************
    
    
    public HabitacionData(Conexion conexion)  {
      try {
            this.conexion=conexion;
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    
//***************METODO PARA GUARDAR UNA HABITACION EN BASE DE DATOS ***********
    
    
    public void guardarHabitacion(Habitacion habitacion){
                
        try {
            
            String sql = "INSERT INTO habitacion (piso, id_thabitacion, estado) VALUES ( ?, ?, ? );";

            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, habitacion.getPiso());
            baseD.setInt(2, habitacion.getThabitacion().getId_thabitacion());
            baseD.setBoolean(3, habitacion.getEstado());
            
            baseD.executeUpdate();
            
            ResultSet resultado = baseD.getGeneratedKeys();
            
            if (resultado.next()) {
                habitacion.setId_habitacion(resultado.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar la habitacion");
            }
            
            baseD.close();
                        
        } catch (SQLException ex) {
            System.out.println("Error al insertar la habitacion: " + ex.getMessage());
        }
    }
    
    
//***************METODO PARA BUSCAR UNA HABITACION POR ID***********************
    
    
    public Habitacion buscarHabitacion(int id){
        
        Habitacion habitacion =null;
                        
        try {
            
            String sql = "SELECT id_habitacion, piso, id_thabitacion, estado "
                       + "FROM habitacion WHERE id_habitacion =?;";
                       
            
            
            PreparedStatement baseD = connection.prepareStatement(sql, com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, id);
                    
            ResultSet resultado = baseD.executeQuery();
            
                           
            while (resultado.next()) {
                habitacion = new Habitacion();
                habitacion.setId_habitacion(resultado.getInt("id_habitacion"));
                habitacion.setPiso(resultado.getInt("piso"));
                habitacion.setId_habitacion(resultado.getInt("id_thabitacion"));
                habitacion.setEstado(resultado.getBoolean("estado"));
                                               
            }
            
            baseD.close();
        
        
        } catch (SQLException ex) {
            System.out.println("Error al buscar un TipoHabitacion: " + ex.getMessage());
        }
        return habitacion;
        
    }
    
    
//*****************METODO PARA OBTENER UNA HABITACION***************************
    
    
    public List<Habitacion> obtenerHabitacion(){
        List<Habitacion> habitaciones = new ArrayList<>();
        
        
        try {
            
            String sql = "SELECT id_habitacion, piso, id_thabitacion, estado "
                       + "FROM habitacion;";
            
            
        PreparedStatement baseD = connection.prepareStatement(sql);
         ResultSet resultado = baseD.executeQuery();
         Habitacion habitacion;
         
         while(resultado.next()){
             habitacion = new Habitacion();
             habitacion.setId_habitacion(resultado.getInt("id_habitacion"));
             habitacion.setPiso(resultado.getInt("piso"));
             
             TipoHabitacion th = buscarTHabitacion(resultado.getInt("id_thabitacion"));
             habitacion.setThabitacion(th);
             
             habitacion.setEstado(resultado.getBoolean("estado"));
             
             habitaciones.add(habitacion);
         }
         
         baseD.close();
         
         
        } catch (SQLException ex) {
            System.out.println("Error al obtener las habitaciones: " + ex.getMessage());
        }
       
        
        
        return habitaciones;
        
    }
    
    
    
//***********METODO PARA BUSCAR UNA TIPO DE HABITACION POR ID*******************
    
    
    public TipoHabitacion buscarTHabitacion(int id) throws SQLException{
        TipoHabitacionData th = new TipoHabitacionData(conexion);
                
        return th.buscarThabitacion(id);
    }
    
    
    
//*****************METODO PARA ACTUALIZAR UNA HABITACION************************
    
    
    public void actualizarEstadoHabitacion(int id_habitacion, int id_thabitacion, boolean estado ){
        
        String sql = "UPDATE habitacion SET estado =? WHERE id_habitacion =? AND id_thabitacion =?;";
        
        try {
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setBoolean(1, estado);
            baseD.setInt(2, id_habitacion);
            baseD.setInt(3, id_thabitacion);
            
            baseD.executeUpdate();
            
            baseD.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar una habitacion: " + ex.getMessage());
        }
               
    }
    
    
    //*****************METODO PARA BORRAR UNA HABITACION************************
    
    
     public void borrarHabitacion(int id_habitacion){
            String sql = "DELETE FROM habitacion WHERE id_habitacion =?; ";
            
        try {
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, id_habitacion);
            
            baseD.executeUpdate();
            
            baseD.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al borrar una habitacion: " + ex.getMessage());
        }
           
        }
    
}
