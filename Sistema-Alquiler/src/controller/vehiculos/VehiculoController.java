package controller.vehiculos;

import model.factories.vehicleFactory.MotoFactory;
import model.specificClasses.vehicleClasses.Vehiculo;
import service.vehiculos.VehiculoService;

public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService){
        this.vehiculoService = vehiculoService;
    }

    public boolean crearVehiculo(boolean disponible, String tipo, double tarifaBase, String descripcion, String marca, String modelo){
        return vehiculoService.guardarVehiculo(disponible, tipo, tarifaBase, descripcion, marca, modelo);
    }
}
