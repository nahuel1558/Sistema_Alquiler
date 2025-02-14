package model.strategies.vehiclesStrategy;

import model.strategies.IEstrategiaCosto;

public class AutoEstrategia implements IEstrategiaCosto {
    @Override
    public double calcularCosto(Integer tiempoReservado, double tarifaBase) {
        double costoAuto = tiempoReservado * tarifaBase;
        return costoAuto;
    }
}
