package model.factories.vehicleFactory;

import model.factories.AlquilableFactory;
import model.specificClasses.vehicleClasses.Vehiculo;

public class VehiculoFactory extends AlquilableFactory {

    @Override
    public Vehiculo crearAlquilable(Enum categoria, Enum tipo, Object... params) {
        return new Vehiculo();
    }
}


