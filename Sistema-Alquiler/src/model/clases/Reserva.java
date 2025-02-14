package model.clases;

import java.util.Date;

public class Reserva<T extends Alquilable> {
    private Long idReserva;
    private T alquilable;
    private Usuario usuario;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer diaReservado;

    public Reserva(T alquilable, Usuario usuario, Date fechaInicio, Date fechaFin, Integer diaReservado) {
        this.alquilable = alquilable;
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diaReservado = diaReservado;
    }

    public Reserva(Long idReserva, T alquilable, Usuario usuario, Date fechaInicio, Date fechaFin, Integer diaReservado) {
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

    public T getAlquilable() {
        return alquilable;
    }

    public void setAlquilable(T alquilable) {
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

