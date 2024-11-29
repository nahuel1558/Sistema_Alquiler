package model.specificClasses.vehicleClasses;

import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

public class Camion extends Vehiculo{
    public Camion(Long id, boolean disponible, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(id, disponible, estrategiaCosto, estrategiaDisponible);
    }
}
