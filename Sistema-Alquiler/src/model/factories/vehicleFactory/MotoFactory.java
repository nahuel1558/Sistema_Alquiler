package model.factories.vehicleFactory;

import model.specificClasses.vehicleClasses.Moto;
import model.specificClasses.vehicleClasses.Vehiculo;
import model.strategies.DisponibleBasicoStrategy;
import model.strategies.vehiclesStrategy.MotoStrategy;

public class MotoFactory implements IVehiculoFactory{

    @Override
    public Vehiculo crearVehiculo(boolean disponible, String tipo,double tarifaBase, String descripcion,  String marca, String modelo) {
        return new Moto(disponible, tipo, tarifaBase, descripcion, marca, modelo, new MotoStrategy(), new DisponibleBasicoStrategy());
    }
}
