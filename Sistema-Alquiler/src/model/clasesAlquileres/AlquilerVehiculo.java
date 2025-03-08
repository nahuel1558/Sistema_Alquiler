package model.clasesAlquileres;

import model.clases.IAlquilable;
import model.clases.Vehiculo;
import model.strategies.VehiculoEstrategia;

public class AlquilerVehiculo implements IGestionAlquiler{

    private Long id;
    private GestionReserva gestionReserva;
    private Vehiculo vehiculo;

    public AlquilerVehiculo() {
    }

    public AlquilerVehiculo(GestionReserva gestionReserva, Vehiculo vehiculo) {
        this.gestionReserva = gestionReserva;
        this.vehiculo = vehiculo;
    }

    public AlquilerVehiculo(Long id, GestionReserva gestionReserva, Vehiculo vehiculo) {
        this.id = id;
        this.gestionReserva = gestionReserva;
        this.vehiculo = vehiculo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public GestionReserva getGestionReserva() {
        return gestionReserva;
    }

    @Override
    public void setGestionReserva(GestionReserva gestionReserva) {
        this.gestionReserva = gestionReserva;
    }

    @Override
    public IAlquilable getAlquiler() {
        return vehiculo;
    }

    @Override
    public void setAlquiler(IAlquilable alquiler) {
        this.vehiculo = (Vehiculo) alquiler;
    }

    @Override
    public double calcularCosto() {
        vehiculo.getAlquilable().getTipoAlquilable().setEstrategiaCosto(new VehiculoEstrategia());
        return vehiculo.getAlquilable().getTipoAlquilable().calcularCosto(getGestionReserva().getReserva().getDiasReservado());
    }

    @Override
    public void guardarCosto(){
        gestionReserva.setCosto(calcularCosto());
    }

    @Override
    public void setDisponible(IAlquilable alquilable) {
        vehiculo.getAlquilable().setDisponible(false);
    }

    @Override
    public String verDescripcion() {
        return "Vehículo: " + vehiculo +
                "Reserva: " + gestionReserva.getReserva() +
                "Usuario: " + gestionReserva.getUsuario();
    }

    @Override
    public String toString() {
        return "Alquiler N°:" + id +
                "Usuario: " + gestionReserva.getUsuario().getNombre() + " " + gestionReserva.getUsuario().getApellido() +
                ", Inicio: " + gestionReserva.getReserva().getFechaInicio() +
                ", Fin: " + gestionReserva.getReserva().getFechaFin() +
                ", Días Reserva: " + gestionReserva.getReserva().getDiasReservado() +
                ", Costo: " + gestionReserva.getCosto() +
                ", Categoría: " + vehiculo.getAlquilable().getCategoria().getNombreCategoria() +
                ", Descripción: " +  vehiculo.getAlquilable().getDescripcion() +
                ", Tipo: " +  vehiculo.getAlquilable().getTipoAlquilable().getNombreTipo() +
                ", Tarifa Base: " +  vehiculo.getAlquilable().getTipoAlquilable().getTarifaBase();
    }
}
