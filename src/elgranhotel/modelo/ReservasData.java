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
            this.conexion = conexion;
            connection = conexion.getConexion();
            System.out.println("Conexion Establecida con exito en ReservasData");
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexion");
        }
    }

//**************************GUARDAR RESERVA EN BD*******************************    
    public void guardarReservas(Reservas reservas) {

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

//**************************OBTENER RESERVAS POR HUESPED************************
    public List<Reservas> obtenerReservasXhuesped(int id) {
        List<Reservas> Reservadas = new ArrayList<>();

        try {

            String sql = "SELECT * FROM Reservas r, tipohabitacion th WHERE r.id = th.id_thabitacion "
                    + "AND id_huesped = ?;";

            PreparedStatement baseD = connection.prepareStatement(sql);
            baseD.setInt(1, id);

            ResultSet resultado = baseD.executeQuery();
            Reservas res;

            while (resultado.next()) {
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
    public Huesped buscarHues(int id) {
        HuespedData hd = new HuespedData(conexion);
        return hd.buscarHuesped(id);
    }

    public Habitacion buscarHab(int id) {
        HabitacionData hbd = new HabitacionData(conexion);
        return hbd.buscarHabitacion(id);
    }

    public TipoHabitacion buscaThab(int id) {
        TipoHabitacionData thab = new TipoHabitacionData(conexion);
        return thab.buscarThabitacion(id);
    }

//**********************BUSCAR RESERVA******************************************
    public Reservas buscarReservas(int id) {

        Reservas reserva = null;

        try {

            String sql = "SELECT * FROM reservas,tipohabitacion WHERE id =?;";
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, id);

            ResultSet resultado = baseD.executeQuery();

            while (resultado.next()) {
                reserva = new Reservas();
                reserva.setId(resultado.getInt("id"));

                Huesped hus = buscarHues(resultado.getInt("id_huesped"));
                reserva.setHuesped(hus);

                Habitacion hab = buscarHab(resultado.getInt("id_habitacion"));
                reserva.setHabitaciones(hab);

                TipoHabitacion tha = buscaThab(resultado.getInt("id_thabitacion"));
                hab.setThabitacion(tha);

                reserva.setCantDias(resultado.getInt("cantDias"));
                reserva.setFechaEntrada(resultado.getDate("fechaEntrada").toLocalDate());
                reserva.setFechaSalida(resultado.getDate("fechaSalida").toLocalDate());
                reserva.setImporteTotal(resultado.getDouble("importeTotal"));
                reserva.setEstado(resultado.getBoolean("estado"));

            }

            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar una Reserva: " + ex.getMessage());
        }

        return reserva;

    }

//*******************BUSCAR RESERVA POR HUESPED*********************************
    public Reservas buscarReservasporhuesped(int id_huesped) {

        Reservas reserva = null;

        try {

            String sql = "SELECT * FROM reservas,tipohabitacion WHERE id_huesped =?;";
            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, id_huesped);

            ResultSet resultado = baseD.executeQuery();

            while (resultado.next()) {
                reserva = new Reservas();
                reserva.setId(resultado.getInt("id"));

                Huesped hus = buscarHues(resultado.getInt("id_huesped"));
                reserva.setHuesped(hus);

                Habitacion hab = buscarHab(resultado.getInt("id_habitacion"));
                reserva.setHabitaciones(hab);

                TipoHabitacion tha = buscaThab(resultado.getInt("id_thabitacion"));
                hab.setThabitacion(tha);

                reserva.setCantDias(resultado.getInt("cantDias"));
                reserva.setFechaEntrada(resultado.getDate("fechaEntrada").toLocalDate());
                reserva.setFechaSalida(resultado.getDate("fechaSalida").toLocalDate());
                reserva.setImporteTotal(resultado.getDouble("importeTotal"));
                reserva.setEstado(resultado.getBoolean("estado"));

            }

            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar una Reserva por huesped: " + ex.getMessage());
        }

        return reserva;

    }

//*****************************FIN RESERVA**************************************
    public Reservas finReserava(Huesped huesped) {

        Reservas reserva = null;

        try {

            reserva = buscarReservasporhuesped(huesped.getId_huesped());

            String sql = "UPDATE reservas SET estado =0 WHERE id_huesped =?;";

            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, reserva.getHuesped().getId_huesped());

            baseD.executeUpdate();

            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar estado de una reserva: " + ex.getMessage());
        }

        try {

            String sql = "UPDATE habitacion SET estado =0 WHERE id_habitacion =?;";

            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, reserva.getHabitaciones().getId_habitacion());

            baseD.executeUpdate();

            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar estado de una habitacion: " + ex.getMessage());
        }

        return reserva;

    }

//***********************BORRAR RESERVA*************************************
    public void borrarReserva(int idres) {

        try {
            String sql = "DELETE FROM reserva WHERE id =?;";

            PreparedStatement baseD = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            baseD.setInt(1, idres);

            baseD.executeUpdate();
            baseD.close();

        } catch (SQLException ex) {
            System.out.println("Error al borrar la reserva: " + ex.getMessage());
        }

    }

}
