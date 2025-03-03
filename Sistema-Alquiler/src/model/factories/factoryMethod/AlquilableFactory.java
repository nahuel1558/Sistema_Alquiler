package model.factories.factoryMethod;

import model.clases.IAlquilable;
import model.clasesAlquileres.GestionReserva;
import model.clasesAlquileres.IGestionAlquiler;

public abstract class AlquilableFactory {

    public IGestionAlquiler realizarAlquiler(GestionReserva gestionReserva, IAlquilable alquilable){
        IGestionAlquiler alquilerAlquilable = crearAlquilable();
        alquilerAlquilable.setGestionReserva(gestionReserva);
        alquilerAlquilable.setAlquiler(alquilable);
        return alquilerAlquilable;
    }

    public abstract IGestionAlquiler crearAlquilable();
}
