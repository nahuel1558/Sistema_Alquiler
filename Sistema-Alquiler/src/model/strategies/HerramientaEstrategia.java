package model.strategies;

public class HerramientaEstrategia implements IEstrategiaCosto{
    @Override
    public double calcularCosto(Integer tiempoReservado, double tarifaBase) {
        return tiempoReservado * tarifaBase;
    }
}
