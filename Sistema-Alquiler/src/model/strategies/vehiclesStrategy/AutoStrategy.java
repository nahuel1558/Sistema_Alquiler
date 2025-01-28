package model.strategies.vehiclesStrategy;

import model.strategies.IStrategyCosto;

public class AutoStrategy implements IStrategyCosto {
    @Override
    public double calcularCosto(Integer tiempoReservado, double tarifaBase) {
        double costoAuto = (double)tiempoReservado * tarifaBase;
        return costoAuto;
    }
}
