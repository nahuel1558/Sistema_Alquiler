package model.clases;

public class GestionReserva implements IGestionReserva{
    private Long idGestionReserva;
    private Usuario usuario;
    private Reserva reserva;
    private IAlquilable alquilable;

    public GestionReserva() {
    }

    public GestionReserva(Usuario usuario, Reserva reserva, IAlquilable alquilable) {
        this.usuario = usuario;
        this.reserva = reserva;
        this.alquilable = alquilable;
    }

    public GestionReserva(Long idGestionReserva, Usuario usuario, Reserva reserva, IAlquilable alquilable) {
        this.idGestionReserva = idGestionReserva;
        this.usuario = usuario;
        this.reserva = reserva;
        this.alquilable = alquilable;
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

    public IAlquilable getAlquilable() {
        return alquilable;
    }

    public void setAlquilable(IAlquilable alquilable) {
        this.alquilable = alquilable;
    }
}
