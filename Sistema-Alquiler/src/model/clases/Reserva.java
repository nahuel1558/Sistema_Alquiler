package model.clases;

import java.util.Date;

public class Reserva {
    private Long idReserva;
    private Usuario usuario;
    private Integer diasReservado;
    private Date fechaInicio;
    private Date fechaFin;
    private EstadoReserva estadoReserva;

    public Reserva(Usuario usuario, Integer diasReservado, Date fechaInicio, Date fechaFin, EstadoReserva estadoReserva) {
        this.usuario = usuario;
        this.diasReservado = diasReservado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoReserva = estadoReserva;
    }

    public Reserva(Long idReserva, Usuario usuario, Integer diasReservado, Date fechaInicio, Date fechaFin, EstadoReserva estadoReserva) {
        this.idReserva = idReserva;
        this.usuario = usuario;
        this.diasReservado = diasReservado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoReserva = estadoReserva;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
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

    public void setDiasReservado(Integer diaReservado) {
        this.diasReservado = diaReservado;
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

    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
}

