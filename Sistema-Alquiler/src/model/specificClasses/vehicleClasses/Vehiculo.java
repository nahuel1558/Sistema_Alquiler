package model.specificClasses.vehicleClasses;

import model.specificClasses.Alquilable;
import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

public abstract class Vehiculo extends Alquilable {
    private IStrategyCosto estrategiaCosto;
    private IStrategyDisponible estrategiaDisponible;

    public Vehiculo(Long id, boolean disponible, IStrategyCosto estrategiaCosto, IStrategyDisponible estrategiaDisponible) {
        super(id, disponible);
        this.estrategiaCosto = estrategiaCosto;
        this.estrategiaDisponible = estrategiaDisponible;
    }

    @Override
    public double calcularCosto(int dias) {
        return estrategiaCosto.calcularCosto(dias);
    }

    public boolean verificarDisponibilidad() {
        return estrategiaDisponible.verificar();
    }
}
