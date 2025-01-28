package model.specificClasses.vehicleClasses;

import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

public class Camion extends Vehiculo{
    public Camion(boolean disponible, String marca, String modelo, double tarifaBase,  IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(disponible, marca, modelo, tarifaBase, estrategiaCosto, estrategiaDisponible);
    }
}
