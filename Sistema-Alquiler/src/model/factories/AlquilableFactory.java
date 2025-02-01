package model.factories;

import model.specificClasses.Alquilable;

public abstract class AlquilableFactory {
    public abstract Alquilable crearAlquilable(Enum categoria, Enum tipo, Object... params);
}
