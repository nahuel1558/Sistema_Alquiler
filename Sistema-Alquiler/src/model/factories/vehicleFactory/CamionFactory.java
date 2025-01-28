package model.factories.vehicleFactory;

import model.specificClasses.Alquilable;
import model.specificClasses.vehicleClasses.Camion;
import model.specificClasses.vehicleClasses.Vehiculo;
import model.strategies.DisponibleBasicoStrategy;
import model.strategies.vehiclesStrategy.CamionStrategy;

public class CamionFactory implements IVehiculoFactory{
    @Override
    public Vehiculo crearVehiculo(String marca, String modelo, double tarifaBase, boolean disponible) {
        return new Camion(disponible, marca, modelo, tarifaBase, new CamionStrategy(), new DisponibleBasicoStrategy());
    }

    @Override
    public Alquilable crearAlquilable(boolean disponible) {
        return null;
    }
}
