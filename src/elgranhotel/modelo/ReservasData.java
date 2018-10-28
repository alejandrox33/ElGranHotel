/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgranhotel.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandrox33 grupo 6
 */
public class ReservasData {
    private Connection connection = null;

//******************CONSTRUCTOR DE LA CLASE*************************************
    
    public ReservasData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReservasData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//**************************GUARDAR RESERVA EN BD*******************************    
    
    public void guardarReservas(Reservas reservas){
        
        try {
            
     String sql = "INSERT INTO reservas (id_huesped, id_habitacion, cantDias, fechaEntrada, fechaSalida, importeTotal, estado) "
                    +"VALUES ( ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, reservas.getHuesped().getId());
            baseD.setInt(2, reservas.getHabitaciones().getId_habitacion());
            baseD.setInt(3, reservas.getCantDias());
            baseD.setDate(4, Date.valueOf(reservas.getFechaEntrada()));
            baseD.setDate(5, Date.valueOf(reservas.getFechaSalida()));
            baseD.setDouble(6, reservas.getImporteTotal());
            baseD.setBoolean(7, reservas.getEstado());
            
            baseD.executeUpdate();
            ResultSet resultado = baseD.getGeneratedKeys();
            
            if (resultado.next()) {
                reservas.setId(resultado.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una reserva");
            }
             baseD.close();
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar una reserva: " + ex.getMessage());
        }
    }
    
    
//**************************BUSCAR RESERVAS*************************************    
    
}
