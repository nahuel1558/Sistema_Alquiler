package model.strategies.vehiclesStrategy;

import model.strategies.IEstrategiaCosto;

public class MotoEstrategia implements IEstrategiaCosto {
    @Override
    public double calcularCosto(Integer tiempoReservado, double tarifaBase){
             double costoMoto = tiempoReservado * tarifaBase;
             return costoMoto;
         }
}
