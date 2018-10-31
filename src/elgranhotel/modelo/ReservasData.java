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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandrox33 grupo 6
 */
public class ReservasData {
    private Connection connection = null;
    private Conexion conexion;

//******************CONSTRUCTOR DE LA CLASE*************************************
    
    public ReservasData(Conexion conexion) {
        try {
            this.conexion=conexion;
            connection = conexion.getConexion();
            System.out.println("Conexion Establecida con exito en ReservasData");
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexion");
        }
    }
    
//**************************GUARDAR RESERVA EN BD*******************************    
    
    public void guardarReservas(Reservas reservas){
        
        try {
            
     String sql = "INSERT INTO reservas (id_huesped, id_habitacion, cantDias, fechaEntrada, fechaSalida, importeTotal, estado) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, reservas.getHuesped().getId_huesped());
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
    
    
//**************************OBTENER RESERVAS POR HUESPED*************************
    
    public List<Reservas> obtenerReservasXhuesped(int id){
        List<Reservas> Reservadas = new ArrayList<>();
        
        try {
            
            
            String sql = "SELECT * FROM Reservas r, tipohabitacion th WHERE r.id = th.id_thabitacion "
                       + "AND id_huesped = ?;";
            
            
            PreparedStatement baseD = connection.prepareStatement(sql);
            baseD.setInt(1, id);
            ResultSet resultado = baseD.executeQuery();
            Reservas res;
            
            while(resultado.next()){
                res = new Reservas();
                res.setId(resultado.getInt("id"));
                
                Huesped hu = buscarHues(resultado.getInt("id_huesped"));
                res.setHuesped(hu);
                
                                
                Habitacion hab = buscarHab(resultado.getInt("id_habitacion"));
                res.setHabitaciones(hab);
                
                TipoHabitacion t = buscaThab(resultado.getInt("id_thabitacion"));
                hab.setThabitacion(t);
                
                               
                res.setCantDias(resultado.getInt("cantDias"));
                res.setFechaEntrada(resultado.getDate("fechaEntrada").toLocalDate());
                res.setFechaSalida(resultado.getDate("fechaSalida").toLocalDate());
                res.setImporteTotal(resultado.getDouble("importeTotal"));
                res.setEstado(resultado.getBoolean("estado"));
                
                
                Reservadas.add(res);
                        
            }
            baseD.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener las Reservas por Huesped: " + ex.getMessage());
        }
        
        return Reservadas;      
    }


//*****************METODOS BUSCAR***********************************************    
    
    public Huesped buscarHues(int id){
            HuespedData hd = new HuespedData(conexion);
            return hd.buscarHuesped(id);
    }
    
    public Habitacion buscarHab(int id){
        HabitacionData hbd = new HabitacionData(conexion);
        return hbd.buscarHabitacion(id);
    }
    
    public TipoHabitacion buscaThab(int id){
        TipoHabitacionData thab = new TipoHabitacionData(conexion);
        return thab.buscarThabitacion(id);
    }
    
}
