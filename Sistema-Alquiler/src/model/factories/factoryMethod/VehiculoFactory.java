package model.factories.factoryMethod;

import model.clasesAlquileres.AlquilerVehiculo;
import model.clasesAlquileres.IGestionAlquiler;

public class VehiculoFactory extends AlquilableFactory {
    @Override
    public IGestionAlquiler crearAlquilable() {
        return new AlquilerVehiculo();
    }
}
