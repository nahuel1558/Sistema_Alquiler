package model.specificClasses.vehicleClasses;

import model.specificClasses.Alquilable;
import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

import java.util.Date;

public abstract class Vehiculo extends Alquilable {
    private String marca;
    private String modelo;
    private double tarifaBase;
    private IStrategyCosto estrategiaCosto;
    private IStrategyDisponible estrategiaDisponible;

    public Vehiculo(boolean disponible, String marca, String modelo, double tarifaBase, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(disponible);
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaBase = tarifaBase;
        this.estrategiaCosto = estrategiaCosto;
        this.estrategiaDisponible = estrategiaDisponible;
    }

    public Vehiculo(Long idAlquilable, boolean disponible, String marca, String modelo, double tarifaBase, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(idAlquilable, disponible);
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaBase = tarifaBase;
        this.estrategiaCosto = estrategiaCosto;
        this.estrategiaDisponible = estrategiaDisponible;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public boolean verificarDisponibilidad(Date fechaIncio, Date fechaFin) {
        return estrategiaDisponible.verificarDisponible(this, fechaIncio, fechaFin);
    }

    public double calcularCosto(Integer tiempoReservado, double tarifaBase){
        return estrategiaCosto.calcularCosto(tiempoReservado, tarifaBase);
    }
}
