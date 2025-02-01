package model.specificClasses;

import java.util.Date;

public class Reserva {
    private Long idReserva;
    private Alquilable alquilable;
    private Usuario usuario;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer diaReservado;

    public Reserva(Date fechaFin, Date fechaInicio, Usuario usuario, Alquilable alquilable, Integer diaReservado) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.usuario = usuario;
        this.alquilable = alquilable;
        this.diaReservado = diaReservado;
    }

    public Reserva(Long idReserva, Alquilable alquilable, Usuario usuario, Date fechaInicio, Date fechaFin, Integer diaReservado) {
        this.idReserva = idReserva;
        this.alquilable = alquilable;
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diaReservado = diaReservado;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Alquilable getAlquilable() {
        return alquilable;
    }

    public void setAlquilable(Alquilable alquilable) {
        this.alquilable = alquilable;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getDiasReservado() {
        return diaReservado;
    }

    public void setDiasReservado(Integer diaReservado) {
        this.diaReservado = diaReservado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}

