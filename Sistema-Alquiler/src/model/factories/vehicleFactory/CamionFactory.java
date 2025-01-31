package model.factories.vehicleFactory;

import model.specificClasses.vehicleClasses.Camion;
import model.specificClasses.vehicleClasses.Vehiculo;
import model.strategies.DisponibleBasicoStrategy;
import model.strategies.vehiclesStrategy.CamionStrategy;

public class CamionFactory implements IVehiculoFactory{


    @Override
    public Vehiculo crearVehiculo(boolean disponible, String tipo, double tarifaBase, String descripcion, String marca, String modelo) {
        return new Camion(disponible, tipo, tarifaBase, descripcion, marca, modelo, new CamionStrategy(), new DisponibleBasicoStrategy());
    }
}
