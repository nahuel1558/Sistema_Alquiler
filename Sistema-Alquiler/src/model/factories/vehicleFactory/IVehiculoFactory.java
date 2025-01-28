package model.factories.vehicleFactory;

import model.factories.IAlquilableFactory;
import model.specificClasses.vehicleClasses.Vehiculo;

public interface IVehiculoFactory extends IAlquilableFactory {
    Vehiculo crearVehiculo(String marca, String modelo, double tarifaBase, boolean disponible);
}
