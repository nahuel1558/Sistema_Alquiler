package controller.alquilables;

import controller.AlquilableController;
import dao.VehiculoDAO;
import model.clases.CategoriaAlquilable;
import model.clases.TipoAlquilable;
import model.clases.Vehiculo;
import model.clases.Alquilable;

public class VehiculoController {

    private AlquilableController alquilableController;
    private final VehiculoDAO vehiculoDAO;

    public VehiculoController(){
        this.vehiculoDAO = VehiculoDAO.getInstance();
    }

    public boolean crearVehiculo(CategoriaAlquilable categoriaAlquilable, TipoAlquilable tipoAlquilable,
                                 boolean disponible, String descripcion, String marca, String modelo){
        Alquilable alquilable = alquilableController.crearAlquilable(categoriaAlquilable, tipoAlquilable, descripcion, disponible);
        return vehiculoDAO.crear(new Vehiculo(alquilable, marca, modelo));
    }

}
