/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgranhotel.modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHIDORY
 */
public class ElGranHotel {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        try {
            String fechaE = "05/10/2018";
            String fechaS = "30/10/2018";
            
            LocalDate fE = LocalDate.parse(fechaE, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fS = LocalDate.parse(fechaS, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
            Conexion conexion = new Conexion("jdbc:mysql://localhost/elgranhotel", "root", "");
           
            HuespedData heD = new HuespedData(conexion);
            Huesped hues = new Huesped(1,"eitan", 888888, "379", "kasmex777@gmail.com", 15600733);
            //heD.borrarHuesped(4);
            heD.obtenerHuesped().forEach(huesped -> {
            System.out.println("Nombre: " + huesped.getNombre());
            });
           
            
            for(Huesped h : heD.obtenerHuesped()){
                System.out.println("Nombre: " + h.getNombre()+", " + h.getDni());
            }
            
            
            //Huesped h = heD.buscarHuesped(hues.getId_huesped());
                      
            /*  TipoHabitacionData tipo = new TipoHabitacionData(conexion);
            TipoHabitacion th = new TipoHabitacion("doble", 2525, 4, 2, "suit", 120);
            tipo.guardarTipoH(th);
            TipoHabitacion a = tipo.buscarThabitacion(th.getId_thabitacion());
            //tipo.borrarThabitacion(1);*/
            
            //System.out.println(""+a);
            HabitacionData habD = new HabitacionData(conexion);
            //Habitacion hab = new Habitacion(1, a , true);
            //habD.actualizarEstadoHabitacion(7,8 , false);
            //habD.guardarHabitacion(hab);
           // Habitacion h1 = habD.buscarHabitacion(hab.getId_habitacion());
           
              for (Habitacion habi : habD.buscarHabporTipo("doble", 4, true)) {
                  System.out.println("la"+habi.getThabitacion().getTipo()+" "+habi.getThabitacion().getCantPersonas()+" "+habi.getEstado());
            }
 
            
            
            
            
            //ReservasData resD = new ReservasData(conexion);
            //Reservas res = new Reservas(h, h1, 4, fE, fS, 50,true);
            //resD.guardarReservas(res);
            //System.out.println("el id es "+h1+""+h);
            /*for(Reservas h : resD.obtenerReservasXhuesped(4)){
            System.out.println("Nombre: " + h.getHuesped().getNombre()+" "+h.getHabitaciones().getThabitacion().getTipo()
            +" "+h.getHabitaciones().getThabitacion().getPrecioNoche());
            }*/
           
            //ArrayList<TipoHabitacion> tipo = new ArrayList<TipoHabitacion>();
            
            //Huesped persona1 = new Huesped(1,"martin avallar", 32247315, "venezuela 379", "kasmex777@gmail.com");
            //TipoHabitacion tipoH = new TipoHabitacion("standar simple", 0025, 2, 2, "simple", 200.0);
            //tipo.add(tipoH);
            
            //Habitacion habi = new Habitacion(persona1, tipoH, 20, true);
            
            //Reservas reserva1 = new Reservas(persona1, habi, 2, fEntrada, fSalida,250.0);
            
            //tipo.forEach(action -> {System.out.println(habi.getThabitacion());} );
            
            
            //for(int i = 0;i < tipo.size();i++){
            
            
            /*System.out.println(tipoH.getTipo());
            System.out.println(tipoH.getCantCamas());
            System.out.println(tipoH.getCodigo());
            System.out.println(tipoH.getPrecioNoche());
            System.out.println(tipoH.getTipoCamas());*/
            //}
        } catch (ClassNotFoundException ex) {
            System.out.println("No se puedo guardar en base de datos");
        
        }     
    }
    
}
