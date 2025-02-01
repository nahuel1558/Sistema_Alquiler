package model.factories.vehicleFactory;

import model.specificClasses.vehicleClasses.Moto;
import model.specificClasses.vehicleClasses.Vehiculo;

public class MotoFactory extends VehiculoFactory {

    @Override
    public Vehiculo crearAlquilable(Enum categoria, Enum tipo, Object... parametros) {
        return new Moto();
    }
}
