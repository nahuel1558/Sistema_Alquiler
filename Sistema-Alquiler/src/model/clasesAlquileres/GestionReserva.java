package model.clasesAlquileres;

import model.clases.Reserva;
import model.clases.Usuario;

public class GestionReserva {
    private Long idGestionReserva;
    private Usuario usuario;
    private Reserva reserva;
    private boolean estado;

    public GestionReserva() {
    }

    public GestionReserva(Usuario usuario, Reserva reserva, boolean estado) {
        this.usuario = usuario;
        this.reserva = reserva;
        this.estado = estado;
    }

    public GestionReserva(Long idGestionReserva, Usuario usuario, Reserva reserva, boolean estado) {
        this.idGestionReserva = idGestionReserva;
        this.usuario = usuario;
        this.reserva = reserva;
        this.estado = estado;
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


}
