package controller.vehiculos;

import model.factories.vehicleFactory.MotoFactory;
import model.specificClasses.vehicleClasses.Vehiculo;
import service.vehiculos.VehiculoService;

public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService){
        this.vehiculoService = vehiculoService;
    }

    public boolean crearMoto(String marca, String modelo, double tarifaBase, boolean disponible){
        MotoFactory motoFactory = new MotoFactory();
        Vehiculo nuevaMoto = motoFactory.crearVehiculo(marca, modelo, tarifaBase, disponible);

        return vehiculoService.guardarVehiculo(nuevaMoto);
    }
}
