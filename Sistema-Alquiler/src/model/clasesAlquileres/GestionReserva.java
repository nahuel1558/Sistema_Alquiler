package model.clasesAlquileres;

import model.clases.Reserva;
import model.clases.Usuario;

public class GestionReserva {
    private Long idGestionReserva;
    private Usuario usuario;
    private Reserva reserva;

    public GestionReserva() {
    }

    public GestionReserva(Usuario usuario, Reserva reserva) {
        this.usuario = usuario;
        this.reserva = reserva;
    }

    public GestionReserva(Long idGestionReserva, Usuario usuario, Reserva reserva) {
        this.idGestionReserva = idGestionReserva;
        this.usuario = usuario;
        this.reserva = reserva;
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
}
