package model.specificClasses.vehicleClasses;

import model.specificClasses.TipoCategoria;
import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

public class Auto extends Vehiculo{
    public Auto(TipoCategoria categoria, boolean disponible, double tarifaBase, String descripcion, TipoVehiculo tipo, String marca, String modelo, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super( categoria, disponible, tarifaBase, descripcion, tipo, marca, modelo, estrategiaCosto, estrategiaDisponible);
    }

    public Auto(Long idAlquilable, TipoCategoria categoria, boolean disponible, double tarifaBase, String descripcion, TipoVehiculo tipo, String marca, String modelo, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(idAlquilable, categoria, disponible, tarifaBase, descripcion, tipo, marca, modelo, estrategiaCosto, estrategiaDisponible);
    }

}
