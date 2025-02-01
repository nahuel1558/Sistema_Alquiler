package model.factories.vehicleFactory;

import model.specificClasses.vehicleClasses.Auto;
import model.specificClasses.vehicleClasses.Vehiculo;
import model.strategies.DisponibleBasicoStrategy;
import model.strategies.vehiclesStrategy.AutoStrategy;

public class AutoFactory extends VehiculoFactory {
    @Override
    public Vehiculo crearAlquilable(Enum categoria, Enum tipo, Object... params) {
        return new Auto();
    }
}
