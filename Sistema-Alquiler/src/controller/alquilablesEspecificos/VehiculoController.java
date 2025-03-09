package controller.alquilablesEspecificos;

import controller.alquilable.AlquilableController;
import dao.VehiculoDAO;
import model.clases.*;
import model.factories.factoryMethod.AlquilableFactory;
import model.factories.factoryMethod.VehiculoFactory;

import java.util.List;

public class VehiculoController implements IAlquilableController<Vehiculo> {

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

    @Override
    public Vehiculo obtenerById(Long id) {
        return vehiculoDAO.obtenerPorId(id);
    }

    @Override
    public List<Vehiculo> traerListaDisponible(List<Long> idAlquilable) {
        return vehiculoDAO.traerListaVehiculoDisponible(idAlquilable);
    }

    @Override
    public AlquilableFactory traerFactory() {
        return new VehiculoFactory();
    }
}
