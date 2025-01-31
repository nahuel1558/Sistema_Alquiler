package model.specificClasses.vehicleClasses;

import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

public class Auto extends Vehiculo{
    public Auto(boolean disponible, String tipo, double tarifaBase, String descripcion, String marca, String modelo, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(disponible, tipo, tarifaBase, descripcion, marca, modelo, estrategiaCosto, estrategiaDisponible);
    }

    public Auto(Long idAlquilable, boolean disponible, String tipo, double tarifaBase, String descripcion, String marca, String modelo, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(idAlquilable, disponible, tipo, tarifaBase, descripcion, marca, modelo, estrategiaCosto, estrategiaDisponible);
    }

}
