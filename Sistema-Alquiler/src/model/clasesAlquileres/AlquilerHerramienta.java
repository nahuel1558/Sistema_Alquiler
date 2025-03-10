package model.clasesAlquileres;

import model.clases.Herramienta;
import model.clases.IAlquilable;
import model.strategies.HerramientaEstrategia;

public class AlquilerHerramienta implements IGestionAlquiler{
    private Long id;
    private GestionReserva gestionReserva;
    private Herramienta herramienta;

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
        return herramienta;
    }

    @Override
    public void setAlquiler(IAlquilable alquiler) {
    this.herramienta = (Herramienta) alquiler;
    }

    @Override
    public double calcularCosto() {
        herramienta.getAlquilable().getTipoAlquilable().setEstrategiaCosto(new HerramientaEstrategia());
        return herramienta.getAlquilable().getTipoAlquilable().calcularCosto(getGestionReserva().getReserva().getDiasReservado());
    }

    @Override
    public void guardarCosto() {
        gestionReserva.setCosto(calcularCosto());
    }

    @Override
    public void setDisponible(IAlquilable alquilable) {
        herramienta.getAlquilable().setDisponible(false);
    }

    @Override
    public String verDescripcion() {
        return "";
    }

    @Override
    public String toString() {
        return "N°:" + id +
                "Usuario: " + gestionReserva.getUsuario().getNombre() + " " + gestionReserva.getUsuario().getApellido() +
                ", Inicio: " + gestionReserva.getReserva().getFechaInicio() +
                ", Fin: " + gestionReserva.getReserva().getFechaFin() +
                ", Días Reserva: " + gestionReserva.getReserva().getDiasReservado() +
                ", Costo: " + gestionReserva.getCosto() +
                ", Tipo: " +  herramienta.getAlquilable().getTipoAlquilable().getNombreTipo() +
                ", Tarifa Base: " +  herramienta.getAlquilable().getTipoAlquilable().getTarifaBase();
    }

}
