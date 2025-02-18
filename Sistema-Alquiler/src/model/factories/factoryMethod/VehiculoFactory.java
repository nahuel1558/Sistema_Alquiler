package model.factories.factoryMethod;

import model.clases.IAlquilable;
import model.clases.Vehiculo;

public class VehiculoFactory extends AlquilableFactory {
    @Override
    public IAlquilable crearAlquilable() {
        return new Vehiculo();
    }
}
