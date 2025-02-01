package model.specificClasses.vehicleClasses;

import model.specificClasses.TipoCategoria;
import model.strategies.IStrategyCosto;
import model.strategies.IStrategyDisponible;

import java.util.function.Function;

public enum TipoVehiculo {
    AUTO(params -> new Auto(
            (TipoCategoria) params[0],
            (boolean) params[1],
            (double) params[2],
            (String) params[3],
            (TipoVehiculo) params[4],
            (String) params[5],
            (String) params[6],
            (IStrategyCosto) params[7],
            (IStrategyDisponible) params[8])),

    MOTO(params -> new Moto(
            (TipoCategoria) params[0],
            (boolean) params[1],
            (double) params[2],
            (String) params[3],
            (TipoVehiculo) params[4],
            (String) params[5],
            (String) params[6],
            (IStrategyCosto) params[7],
            (IStrategyDisponible) params[8])),

    CAMION(params -> new Camion(
            (TipoCategoria) params[0],
            (boolean) params[1],
            (double) params[2],
            (String) params[3],
            (TipoVehiculo) params[4],
            (String) params[5],
            (String) params[6],
            (IStrategyCosto) params[7],
            (IStrategyDisponible) params[8])),

    INDEFINIDO(params -> { throw new IllegalArgumentException("Tipo de vehículo no válido"); });

    private final Function<Object[], Vehiculo> constructor;

    TipoVehiculo(Function<Object[], Vehiculo> constructor) {
        this.constructor = constructor;
    }

    public Vehiculo crearVehiculo(Object... params) {
        return constructor.apply(params);
    }
}
