package model.specificClasses.vehicleClasses;

import model.specificClasses.Alquilable;
import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

import java.util.Date;

public class Vehiculo extends Alquilable {
    private TipoVehiculo tipo;
    private String marca;
    private String modelo;
    private IStrategyCosto estrategiaCosto;
    private IStrategyDisponible estrategiaDisponible;

    public Vehiculo(boolean disponible, double tarifaBase, String descripcion, TipoVehiculo tipo, String marca, String modelo, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(disponible, tarifaBase, descripcion);
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.estrategiaCosto = estrategiaCosto;
        this.estrategiaDisponible = estrategiaDisponible;
    }

    public Vehiculo(Long idAlquilable, boolean disponible, double tarifaBase, String descripcion, TipoVehiculo tipo, String marca, String modelo, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(idAlquilable, disponible, tarifaBase, descripcion);
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.estrategiaCosto = estrategiaCosto;
        this.estrategiaDisponible = estrategiaDisponible;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
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


    public boolean verificarDisponible(Alquilable vehiculo, Date fechaIncio, Date fechaFin) {
        return estrategiaDisponible.verificarDisponible(vehiculo, fechaIncio, fechaFin);
    }

    public double calcularCosto(Integer tiempoReservado, double tarifaBase){
        return estrategiaCosto.calcularCosto(tiempoReservado, tarifaBase);
    }
}
