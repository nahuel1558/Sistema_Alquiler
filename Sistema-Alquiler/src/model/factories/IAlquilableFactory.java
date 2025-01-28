package model.factories;

import model.specificClasses.Alquilable;

public abstract interface IAlquilableFactory {
    Alquilable crearAlquilable(boolean disponible);
}
