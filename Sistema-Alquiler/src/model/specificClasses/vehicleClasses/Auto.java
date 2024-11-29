package model.specificClasses.vehicleClasses;

import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

public class Auto extends Vehiculo{
    public Auto(Long id, boolean disponible, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(id, disponible, estrategiaCosto, estrategiaDisponible);
    }
}
