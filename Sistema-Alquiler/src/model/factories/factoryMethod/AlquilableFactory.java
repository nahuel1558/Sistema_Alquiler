package model.factories.factoryMethod;

import model.clases.IAlquilable;
import model.clases.Reserva;

public abstract class AlquilableFactory {

    public abstract IAlquilable crearAlquilable();

    public void datos(Reserva reserva){
      IAlquilable alquilable = crearAlquilable();
      alquilable.s
      mostrarDescripcion(alquilable);
      mostrarCostoDeReserva(alquilable, reserva);
      mostrarDisponibilidad(alquilable);
    }
    private void mostrarDescripcion(IAlquilable alquilable){
        System.out.println(alquilable.getDescripcion());
    }
    private void mostrarCostoDeReserva(IAlquilable alquilable, Reserva reserva){
        System.out.println("Costo de Reserva: " + alquilable.calcularCosto(reserva.getDiasReservado()));
    }
    private void mostrarDisponibilidad(IAlquilable alquilable){
        System.out.println("Alquilable disponible: " + alquilable.estaDisponible());
    }
}
