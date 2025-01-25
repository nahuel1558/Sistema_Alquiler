package model.specificClasses;

import java.util.Date;

public class Reserva {
    private Long idReserva;
    private Alquilable alquilable;
    private Usuario usuario;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer diasReservado;

    public Reserva(Date fechaFin, Date fechaInicio, Usuario usuario, Alquilable alquilable, Integer diasReservado) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.usuario = usuario;
        this.alquilable = alquilable;
        this.diasReservado = diasReservado;
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
        return diasReservado;
    }

    public void setDiasReservado(Integer diasReservado) {
        this.diasReservado = diasReservado;
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

