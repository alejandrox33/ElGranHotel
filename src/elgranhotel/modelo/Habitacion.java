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
    private int piso;
    private TipoHabitacion thabitacion;
    private boolean estado;

    public Habitacion(int id_habitacion, int piso, TipoHabitacion thabitacion, boolean estado) {
        this.id_habitacion = id_habitacion;
        this.piso = piso;
        this.thabitacion = thabitacion;
        this.estado = estado;
    }

    public Habitacion(int piso, TipoHabitacion thabitacion, boolean estado) {
        this.piso = piso;
        this.thabitacion = thabitacion;
        this.estado = estado;
    }

    public Habitacion(TipoHabitacion thabitacion, boolean estado) {
        this.thabitacion = thabitacion;
        this.estado = estado;
    }
    
       
    public Habitacion() {
        
    }
    
    public double calcularMonto(int cantDias){          // Metodo para calcular el monto
        return thabitacion.getPrecioNoche() * cantDias;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
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
