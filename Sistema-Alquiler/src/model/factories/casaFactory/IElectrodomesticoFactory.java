package model.factories.casaFactory;

import model.factories.IAlquilableFactory;
import model.specificClasses.casaClasses.Electrodomestico;

public interface IElectrodomesticoFactory extends IAlquilableFactory {
    @Override
    Electrodomestico crearAlquilable();
}
