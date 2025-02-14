package model.strategies.vehiclesStrategy;

import model.strategies.IEstrategiaCosto;

public class CamionEstrategia implements IEstrategiaCosto {
    @Override
    public double calcularCosto(Integer tiempoReservado, double tarifaBase) {
        double costoCamion = tiempoReservado * tarifaBase;
        return costoCamion;
    }
}
