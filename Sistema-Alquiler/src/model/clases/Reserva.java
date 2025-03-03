package model.clases;

import java.util.Date;

public class Reserva {
    private Long idReserva;
    private Integer diasReservado;
    private Date fechaInicio;
    private Date fechaFin;

    public Reserva() {
    }

    public Reserva(Integer diasReservado, Date fechaInicio, Date fechaFin) {
        this.diasReservado = diasReservado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Reserva(Long idReserva, Integer diasReservado, Date fechaInicio, Date fechaFin) {
        this.idReserva = idReserva;
        this.diasReservado = diasReservado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
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
}

