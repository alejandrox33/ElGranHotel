/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgranhotel.modelo;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author CHIDORY
 */
public class TipoHabitacionData {
    private Connection connection = null;
    
//***************************CONSTRUCTOR DE LA CLASE****************************    

    public TipoHabitacionData(Conexion conexion)  {
        
       try {
            
            connection = conexion.getConexion();
            System.out.println("Conexion Establecida con exito en TipoHabitacionData");
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
       
    }
    
//**************************GUARDAR UN TIPO DE HABITACION***********************
    
    
    public void guardarTipoH(TipoHabitacion tipoH){
        
        
        try {
            
            String sql = "INSERT INTO tipohabitacion (tipo, codigo, cantPersonas, cantCamas, tipoCamas, precioNoche) "
                       + "VALUES ( ?, ?, ?, ?, ?, ? );";
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            baseD.setString(1, tipoH.getTipo());
            baseD.setInt(2, tipoH.getCodigo());
            baseD.setInt(3, tipoH.getCantPersonas());
            baseD.setInt(4, tipoH.getCantCamas());
            baseD.setString(5, tipoH.getTipoCamas());
            baseD.setDouble(6, tipoH.getPrecioNoche());
            
            baseD.executeUpdate();
            ResultSet resultado = baseD.getGeneratedKeys();
            
            if (resultado.next()) {
                tipoH.setId_thabitacion(resultado.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar el Tipo de Habitacion");
            }
            
            baseD.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar el tipo de habitacion: " + ex.getMessage());
        }
        
        
    }
    
    
    
//**********************BUSCAR TIPO DE HABITACION POR ID************************
    
    
    public TipoHabitacion buscarThabitacion(int id){
        TipoHabitacion tipo1 = null;
        
                
        try {
            
            String sql = "SELECT * FROM tipohabitacion WHERE id_thabitacion = ?;";
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, id);
            
             ResultSet resultado = baseD.executeQuery();
            
            while(resultado.next()){
                tipo1 = new TipoHabitacion();
                tipo1.setId_thabitacion(resultado.getInt("id_thabitacion"));
                tipo1.setTipo(resultado.getString("tipo"));
                tipo1.setCodigo(resultado.getInt("codigo"));
                tipo1.setCantPersonas(resultado.getInt("cantPersonas"));
                tipo1.setCantCamas(resultado.getInt("cantCamas"));
                tipo1.setTipoCamas(resultado.getString("tipoCamas"));
                tipo1.setPrecioNoche(resultado.getDouble("precioNoche"));
                
            }
                                               
                        
            baseD.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar un Tipo de Habitacion: " + ex.getMessage());
        }
        
        return tipo1;
        
    }
    
    
//**************************OBTENER UN TIPO DE HABITACION***********************    
    

    public List<TipoHabitacion> obtenerThabitacion(){
        List<TipoHabitacion> tHabitaciones = new ArrayList<>();
       
        String sql = "SELECT id_thabitacion, tipo, codigo, cantPersonas, cantCamas, tipoCamas, precioNoche"
                   + "FROM tipohabitacion;";
        try {
            
            PreparedStatement baseD = connection.prepareStatement(sql);
            ResultSet resultado = baseD.executeQuery();
            TipoHabitacion thabitacion;
            
            while (resultado.next()) {
                thabitacion = new TipoHabitacion();
                thabitacion.setId_thabitacion(resultado.getInt("id_thabitacion"));
                thabitacion.setTipo(resultado.getString("tipo"));
                thabitacion.setCodigo(resultado.getInt("codigo"));
                thabitacion.setCantPersonas(resultado.getInt("cantPersonas"));
                thabitacion.setCantCamas(resultado.getInt("cantCamas"));
                thabitacion.setTipoCamas(resultado.getString("tipoCamas"));
                thabitacion.setPrecioNoche(resultado.getDouble("precioNoche"));
                
            }
            
            baseD.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los Tipo de Habitaciones: " + ex.getMessage());
        }
        
        return tHabitaciones;
    }   
    
    
//********************ACTUALIZAR UN TIPO DE HABITACION**************************
    
    
    public void actualizarThabitacion(TipoHabitacion tipo){
        
        
        try {
            String sql = "UPDATE tipohabitacion SET tipo =?, codigo =?, cantPersonas =?, "
                       + "cantCamas =?, tipoCamas =?, precioNoche =? WHERE id_thabitacion =?;";
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setString(1, tipo.getTipo());
            baseD.setInt(2, tipo.getCodigo());
            baseD.setInt(3, tipo.getCantPersonas());
            baseD.setInt(4, tipo.getCantCamas());
            baseD.setString(5, tipo.getTipoCamas());
            baseD.setDouble(6, tipo.getPrecioNoche());
            baseD.setInt(7, tipo.getId_thabitacion());
            
            baseD.executeUpdate();
            baseD.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un tipo de habitacion: " + ex.getMessage());
        }
        
        
    
    }
    
    
//************************BORRAR UN TIPO DE HABITACION POR ID*******************


    public void borrarThabitacion(int id_thabitacion){
        
        
        try {
            String sql = "DELETE FROM tipohabitacion WHERE id_thabitacion = ?;";
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, id_thabitacion);
            
            baseD.executeUpdate();
            baseD.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al borrar un tipo de habitacion: " + ex.getMessage());
        }
    }
    
    
//*************************ACTUALIZA PRECIO ************************************

    public void actualizaPrecio(int id_habitacion, int id_thabitacion, double precio){
        
        try {
            String sql = "UPDATE tipohabitacion SET precioNoche =? WHERE id_habitacion =? AND id_thabitacion";
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setDouble(1, precio);
            baseD.setInt(2, id_habitacion);
            baseD.setInt(3, id_thabitacion);
            
            baseD.executeUpdate();
            baseD.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar precio: " + ex.getMessage());
        }
    
    
    } 
    
    

       
}
