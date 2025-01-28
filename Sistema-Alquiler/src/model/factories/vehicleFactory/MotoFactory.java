package model.factories.vehicleFactory;

import model.specificClasses.Alquilable;
import model.specificClasses.vehicleClasses.Moto;
import model.specificClasses.vehicleClasses.Vehiculo;
import model.strategies.DisponibleBasicoStrategy;
import model.strategies.vehiclesStrategy.MotoStrategy;

public class MotoFactory implements IVehiculoFactory{

    @Override
    public Vehiculo crearVehiculo(String marca, String modelo, double tarifaBase, boolean disponible) {
        return new Moto(disponible, marca, modelo, tarifaBase, new MotoStrategy(), new DisponibleBasicoStrategy());
    }

    @Override
    public Alquilable crearAlquilable(boolean disponible) {
        return null;
    }
}
