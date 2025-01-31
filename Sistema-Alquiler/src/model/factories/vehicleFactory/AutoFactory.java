package model.factories.vehicleFactory;

import model.specificClasses.vehicleClasses.Auto;
import model.specificClasses.vehicleClasses.Vehiculo;
import model.strategies.DisponibleBasicoStrategy;
import model.strategies.vehiclesStrategy.AutoStrategy;

public class AutoFactory implements IVehiculoFactory{


    @Override
    public Vehiculo crearVehiculo(boolean disponible, String tipo, double tarifaBase, String descripcion, String marca, String modelo) {
        return new Auto(disponible, tipo, tarifaBase, descripcion, marca, modelo, new AutoStrategy(), new DisponibleBasicoStrategy());
    }
}
