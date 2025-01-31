package model.factories;

import model.specificClasses.Alquilable;

public interface IAlquilableFactory {
    Alquilable crearAlquilable(boolean disponible);
}
