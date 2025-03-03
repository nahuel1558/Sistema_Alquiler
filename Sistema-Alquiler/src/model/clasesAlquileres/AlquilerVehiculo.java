package model.clasesAlquileres;

import model.clases.IAlquilable;
import model.clases.Vehiculo;
import model.strategies.VehiculoEstrategia;

public class AlquilerVehiculo implements IGestionAlquiler{

    private Long idAlquilerVehiculo;
    private GestionReserva gestionReserva;
    private Vehiculo vehiculo;

    public AlquilerVehiculo() {
    }

    public AlquilerVehiculo(GestionReserva gestionReserva, Vehiculo vehiculo) {
        this.gestionReserva = gestionReserva;
        this.vehiculo = vehiculo;
    }

    public AlquilerVehiculo(Long idAlquilerVehiculo, GestionReserva gestionReserva, Vehiculo vehiculo) {
        this.idAlquilerVehiculo = idAlquilerVehiculo;
        this.gestionReserva = gestionReserva;
        this.vehiculo = vehiculo;
    }

    @Override
    public Long getId() {
        return idAlquilerVehiculo;
    }

    @Override
    public void setId(Long id) {
        this.idAlquilerVehiculo = id;
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
    public String verDescripcion() {
        return "Vehículo: " +
                vehiculo.getMarca() + "-" +
                vehiculo.getModelo() + "-" +
                vehiculo.getAlquilable().getDescripcion() + "-" +
                vehiculo.getAlquilable().getTipoAlquilable().getTarifaBase();
    }
}
