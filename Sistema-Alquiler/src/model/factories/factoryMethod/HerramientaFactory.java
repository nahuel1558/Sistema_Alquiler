package model.factories.factoryMethod;

import model.clasesAlquileres.AlquilerHerramienta;
import model.clasesAlquileres.IGestionAlquiler;

public class HerramientaFactory extends AlquilableFactory{
    @Override
    public IGestionAlquiler crearAlquilable() {
        return new AlquilerHerramienta();
    }
}
