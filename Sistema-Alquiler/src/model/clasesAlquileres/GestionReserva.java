package model.clasesAlquileres;

import model.clases.Reserva;
import model.clases.Usuario;

public class GestionReserva {
    private Long idGestionReserva;
    private Usuario usuario;
    private Reserva reserva;
    private boolean estado;
    private double costo;

    public GestionReserva() {
    }

    public GestionReserva(Usuario usuario, Reserva reserva, boolean estado) {
        this.usuario = usuario;
        this.reserva = reserva;
        this.estado = estado;
    }

    public GestionReserva(Usuario usuario, Reserva reserva, boolean estado, double costo) {
        this.usuario = usuario;
        this.reserva = reserva;
        this.estado = estado;
        this.costo = costo;
    }

    public GestionReserva(Long idGestionReserva, Usuario usuario, Reserva reserva, boolean estado, double costo) {
        this.idGestionReserva = idGestionReserva;
        this.usuario = usuario;
        this.reserva = reserva;
        this.estado = estado;
        this.costo = costo;
    }

    public Long getIdGestionReserva() {
        return idGestionReserva;
    }

    public void setIdGestionReserva(Long idGestionReserva) {
        this.idGestionReserva = idGestionReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
