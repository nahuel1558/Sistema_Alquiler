package model.factories.vehicleFactory;

import model.clases.Vehiculo;
import model.factories.AlquilableFactory;
import model.factories.IAlquilable;

public class VehiculoFactory extends AlquilableFactory {
    @Override
    public IAlquilable crearAlquilable() {
        return new Vehiculo();
    }
}
