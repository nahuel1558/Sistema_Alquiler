package model.factories.vehicleFactory;

import model.specificClasses.vehicleClasses.Vehiculo;

public interface IVehiculoFactory {

    Vehiculo crearVehiculo(boolean disponible, String tipo, double tarifaBase, String descripcion, String marca, String modelo);

}


