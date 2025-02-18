package model.factories.factoryMethod;

import model.clases.IAlquilable;
import model.clases.Reserva;
import model.clases.Usuario;

public abstract class AlquilableFactory {

    public IGestionReserva crearReserva(Usuario usuario, Reserva reserva, IAlquilable alquilable){
        IGestionReserva alquilerAlquilable = crearAlquilable();
        alquilerAlquilable.setUsuario(usuario);
        alquilerAlquilable.setReserva(reserva);
        alquilerAlquilable.setAlquilable(alquilable);
        return alquilerAlquilable;
    }

    public abstract IAlquilable crearAlquilable();
}
