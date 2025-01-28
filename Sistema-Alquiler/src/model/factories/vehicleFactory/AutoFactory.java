package model.factories.vehicleFactory;

import model.specificClasses.Alquilable;
import model.specificClasses.vehicleClasses.Auto;
import model.specificClasses.vehicleClasses.Vehiculo;
import model.strategies.DisponibleBasicoStrategy;
import model.strategies.vehiclesStrategy.AutoStrategy;

public class AutoFactory implements IVehiculoFactory{
    @Override
    public Vehiculo crearVehiculo(String marca, String modelo, double tarifaBase, boolean disponible) {
        return new Auto(disponible, marca, modelo, tarifaBase, new AutoStrategy(), new DisponibleBasicoStrategy());
    }

    @Override
    public Alquilable crearAlquilable(boolean disponible) {
        return null;
    }
}
