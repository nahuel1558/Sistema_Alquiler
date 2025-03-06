package model.clases;

import java.util.Calendar;
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

    public static Reserva nuevaReserva(int diasReservado) {
        Date fechaInicio = new Date();
        Date fechaFin = calcularFechaFin(fechaInicio, diasReservado);
        return new Reserva(diasReservado, fechaInicio, fechaFin);
    }

    private static Date calcularFechaFin(Date fechaInicio, int diasReservado) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.DAY_OF_YEAR, diasReservado);
        return calendar.getTime();
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

