package model.strategies;

import model.specificClasses.Alquilable;

import java.util.Date;

public class DisponibleBasicoStrategy implements IStrategyDisponible {

    @Override
    public boolean verificarDisponible(Alquilable alquilable, Date fechaInicio, Date fechaFin) {
        boolean disponible = true;
        return disponible;
    }
}
