/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgranhotel.modelo;

import elgranhotel.modelo.Huesped;
import elgranhotel.modelo.Habitacion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author CHIDORY
 */
public class Reservas {
    private int id = -1;
    private Huesped huesped;
    private Habitacion habitaciones;
    private int cantDias;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private double importeTotal;
    private boolean estado;

    public Reservas(int id, Huesped huesped, Habitacion habitaciones, int cantDias, 
                        LocalDate fechaEntrada, LocalDate fechaSalida, double importeTotal, boolean estado) {
        
        this.id = id;
        this.huesped = huesped;
        this.habitaciones = habitaciones;
        this.cantDias = cantDias;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.importeTotal = importeTotal;
        this.estado = estado;
    }

    public Reservas(Huesped huesped, Habitacion habitaciones, int cantDias, LocalDate fechaEntrada, 
                                       LocalDate fechaSalida, double importeTotal, boolean estado) {
        
        this.huesped = huesped;
        this.habitaciones = habitaciones;
        this.cantDias = cantDias;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.importeTotal = importeTotal;
        this.estado = estado;
    }
   
     public Reservas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Habitacion habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
       
   
}
