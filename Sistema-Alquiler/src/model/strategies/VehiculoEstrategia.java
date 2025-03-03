package model.strategies;

public class VehiculoEstrategia implements IEstrategiaCosto{
    @Override
    public double calcularCosto(Integer tiempoReservado, double tarifaBase) {
        return tiempoReservado * tarifaBase;
    }
}
