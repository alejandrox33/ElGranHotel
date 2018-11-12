/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgranhotel.modelo;





/**
 *
 * @author CHIDORY
 */
public class Habitacion {
    private int id_habitacion = -1 ;
    private TipoHabitacion thabitacion;
    private boolean estado;
    private double monto;

    public Habitacion(int id_habitacion, TipoHabitacion thabitacion, boolean estado) {
        this.id_habitacion = id_habitacion;
        this.thabitacion = thabitacion;
        this.estado = estado;
    }

    public Habitacion( TipoHabitacion thabitacion, boolean estado) {
        this.thabitacion = thabitacion;
        this.estado = estado;
    }

    public Habitacion() {
        
    }
    
    public void calcularMonto(double precio,int cantDias){          // Metodo para calcular el monto
        monto = precio * cantDias;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public TipoHabitacion getThabitacion() {
        return thabitacion;
    }

    public void setThabitacion(TipoHabitacion thabitacion) {
        this.thabitacion = thabitacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

                 
}
