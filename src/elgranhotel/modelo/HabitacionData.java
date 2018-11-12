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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alejandrox33 grupo 6
 */

public class HabitacionData {

    private Connection connection = null;
    private Conexion conexion;

//*********************CONSTRUTOR DE LA CLASE***********************************
    public HabitacionData(Conexion conexion) {
        try {
            this.conexion = conexion;
            connection = conexion.getConexion();
            System.out.println("Conexion Establecida con exito en HabitacionData");
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }

//***************METODO PARA GUARDAR UNA HABITACION EN BASE DE DATOS ***********
    public void guardarHabitacion(Habitacion habitacion) {

        try {

            String sql = "INSERT INTO habitacion (id_thabitacion, estado) VALUES ( ?, ?);";

            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, habitacion.getThabitacion().getId_thabitacion());
            baseD.setBoolean(2, habitacion.getEstado());

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
    public Habitacion buscarHabitacion(int id) {

        Habitacion habitacion = null;

        try {

            String sql = "SELECT id_habitacion,id_thabitacion, estado "
                    + "FROM habitacion WHERE id_habitacion =?;";

            PreparedStatement baseD = connection.prepareStatement(sql, com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, id);

            ResultSet resultado = baseD.executeQuery();

            while (resultado.next()) {
                habitacion = new Habitacion();
                habitacion.setId_habitacion(resultado.getInt("id_habitacion"));
                habitacion.setId_habitacion(resultado.getInt("id_thabitacion"));
                habitacion.setEstado(resultado.getBoolean("estado"));

            }

            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar una Habitacion: " + ex.getMessage());
        }
        return habitacion;

    }

//*****************METODO PARA OBTENER UNA HABITACION***************************
    public List<Habitacion> obtenerHabitacion() {
        List<Habitacion> habitaciones = new ArrayList<>();

        try {

            String sql = "SELECT id_habitacion, piso, id_thabitacion, estado "
                    + "FROM habitacion;";

            PreparedStatement baseD = connection.prepareStatement(sql);
            ResultSet resultado = baseD.executeQuery();
            Habitacion habitacion;

            while (resultado.next()) {
                habitacion = new Habitacion();
                habitacion.setId_habitacion(resultado.getInt("id_habitacion"));

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
    public TipoHabitacion buscarTHabitacion(int id) {
        TipoHabitacionData th = new TipoHabitacionData(conexion);

        return th.buscarThabitacion(id);
    }

//*****************METODO PARA ACTUALIZAR ESTADO HABITACION************************
    public void actualizarEstadoHabitacion(int id_habitacion, int id_thabitacion, boolean estado) {

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
    public void borrarHabitacion(int id_habitacion) {
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

    //****************************BUSCAR POR TIPO Y CANTPERSONAS********************
    public List<Habitacion> obtenerHabiporTipo(int cantPer, boolean estado) {
        List<Habitacion> habitaciones = new ArrayList<>();

        try {

            String sql = "SELECT * FROM habitacion t, tipohabitacion th WHERE t.id_habitacion = th.id_thabitacion "
                       + "AND cantPersonas =? AND estado =?;";

            PreparedStatement baseD = connection.prepareStatement(sql, com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
            //baseD.setString(1, tipo);
            baseD.setInt(1, cantPer);
            baseD.setBoolean(2, estado);

            ResultSet resultado = baseD.executeQuery();
            Habitacion hab = null;

            while (resultado.next()) {
                hab = new Habitacion();
                hab.setId_habitacion(resultado.getInt("id_habitacion"));

                TipoHabitacion t = buscarTHabitacion(resultado.getInt("id_thabitacion"));
                hab.setThabitacion(t);

                hab.setEstado(resultado.getBoolean("estado"));

                habitaciones.add(hab);

            }

            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar un Tipo de Habitacion: " + ex.getMessage());
        }

        return habitaciones;

    }
    //  METODO SOBRECARGADO
    public List<Habitacion> obtenerHabiporTipo(boolean estado) {
        List<Habitacion> habitaciones = new ArrayList<>();

        try {

            String sql = "SELECT * FROM habitacion t, tipohabitacion th WHERE t.id_habitacion = th.id_thabitacion "
                       + "AND estado =?;";

            PreparedStatement baseD = connection.prepareStatement(sql, com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
            baseD.setBoolean(1, estado);

            ResultSet resultado = baseD.executeQuery();
            Habitacion hab = null;

            while (resultado.next()) {
                hab = new Habitacion();
                hab.setId_habitacion(resultado.getInt("id_habitacion"));

                TipoHabitacion t = buscarTHabitacion(resultado.getInt("id_thabitacion"));
                hab.setThabitacion(t);

                hab.setEstado(resultado.getBoolean("estado"));

                habitaciones.add(hab);

            }

            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar un Tipo de Habitacion: " + ex.getMessage());
        }

        return habitaciones;

    }

//***********************CREAR HABITACIONES POR TIPO****************************
    public void crearHabitacionPorTipo(int desde, int hasta, String tipo, int codigo, int cantpersonas, int cantCamas, String tipoCamas, double precioNoche, boolean estado) {
        int cantidad = Math.abs(desde - hasta) + 1;

        for (int i = 0; i < cantidad; i++) {
            Habitacion ha = null;

            TipoHabitacionData thaD = new TipoHabitacionData(conexion);
            TipoHabitacion thab = new TipoHabitacion(tipo, codigo, cantpersonas, cantCamas, tipoCamas, precioNoche);
            thaD.guardarTipoH(thab);
            TipoHabitacion id_tipo = buscarTHabitacion(thab.getId_thabitacion());
            
            HabitacionData haD = new HabitacionData(conexion);
            ha = new Habitacion(id_tipo, estado);
            haD.guardarHabitacion(ha);

        }

    }

//*******************ACTUALIZAR HABITACION**************************************
    public void actualizarHabitacion(Habitacion habitacion) {

        try {
            String sql = "UPDATE habitacion SET id_thabitacion =?, estado =? WHERE id_habitacion =? ;";

            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, habitacion.getThabitacion().getId_thabitacion());
            baseD.setBoolean(2, habitacion.getEstado());
            baseD.setInt(3, habitacion.getId_habitacion());

            baseD.executeUpdate();
            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar una habitacion: " + ex.getMessage());
        }
    }
    
//******************************CONTADOR****************************************    
        public int contadorHabitacionPorTipo(String tipo){
        int cantidad = 0;
        
        try {
            String sql = "SELECT COUNT(th.tipo) FROM habitacion h, tipohabitacion th "
                       + "WHERE h.id_habitacion = th.id_thabitacion AND th.tipo =?;";
            
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setString(1, tipo);
            
            ResultSet resultado = baseD.executeQuery();
            
            while(resultado.next()){
                cantidad = resultado.getInt(1);
                
            }
                                   
            baseD.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al contar el tipo de Habitacion: " + ex.getMessage());
        }
        
        return cantidad;
    }

}
