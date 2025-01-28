package model.strategies.vehiclesStrategy;

import model.strategies.IStrategyCosto;

public class MotoStrategy implements IStrategyCosto {
    @Override
    public double calcularCosto(Integer tiempoReservado, double tarifaBase) {
        double costoMoto = (double)tiempoReservado * tarifaBase;
        return costoMoto;
    }
}
