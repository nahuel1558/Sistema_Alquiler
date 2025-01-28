package model.strategies.vehiclesStrategy;

import model.strategies.IStrategyCosto;

public class CamionStrategy implements IStrategyCosto {
    @Override
    public double calcularCosto(Integer tiempoReservado, double tarifaBase) {
        double costoCamion = (double)tiempoReservado * tarifaBase;
        return costoCamion;
    }
}
