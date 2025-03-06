package model.clases;

import java.util.Date;

public class Reserva {
    private Long idReserva;
    private Integer diasReservado;
    private Date fechaInicio;
    private Date fechaFin;
    private double costo;

    public Reserva() {
    }

    public Reserva(Long idReserva, Integer diasReservado, Date fechaInicio, Date fechaFin, double costo) {
        this.idReserva = idReserva;
        this.diasReservado = diasReservado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
    }

    public Reserva(Integer diasReservado, Date fechaInicio, Date fechaFin, double costo) {
        this.diasReservado = diasReservado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
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

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}

