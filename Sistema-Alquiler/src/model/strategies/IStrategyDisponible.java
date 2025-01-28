package model.strategies;

import model.specificClasses.Alquilable;

import java.util.Date;

public interface IStrategyDisponible {
    boolean verificarDisponible(Alquilable alquilable, Date fechaInicio, Date fechaFin );
}
