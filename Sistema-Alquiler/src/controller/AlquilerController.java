package controller;

import controller.alquilable.AlquilableController;
import controller.alquilablesEspecificos.HerramientaController;
import controller.alquilablesEspecificos.IAlquilableController;
import controller.alquilablesEspecificos.VehiculoController;
import dao.AlquilerDAO;
import model.clases.IAlquilable;
import model.clasesAlquileres.IGestionAlquiler;
import model.factories.factoryMethod.AlquilableFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlquilerController {

    private final AlquilerDAO alquilerDAO;
    private AlquilableController alquilableController;
    private Map<Long, IAlquilableController> controladoresPorCategoria;

    public AlquilerController(){
        this.alquilerDAO = AlquilerDAO.getInstance();
        this.alquilableController = new AlquilableController();
        controladoresPorCategoria = new HashMap<>();
        controladoresPorCategoria.put(1L, new VehiculoController());
        controladoresPorCategoria.put(2L, new HerramientaController());
    }

    public boolean crearAlquiler(IGestionAlquiler alquiler){
        return alquilerDAO.crear(alquiler);
    }
    public List<IAlquilable> listarAlquilablesDisponibles(Long idCategoria){
        List<Long> idAlquilables = alquilableController.traerListaIdAlquilableDisponible();
        IAlquilableController controller = obtenerControladora(idCategoria);
        return controller.traerListaDisponible(idAlquilables);
    }

    public AlquilableFactory obtenerFactory(Long idCategoria){
        IAlquilableController<?> controller = obtenerControladora(idCategoria);
        return controller.traerFactory();
    }
    public IAlquilableController obtenerControladora(Long idCategoria){
        return controladoresPorCategoria.get(idCategoria);
    }

    public List<IGestionAlquiler> listarAlquileresEnCursoVehiculo(){
        return alquilerDAO.listarAlquileresActivosVehiculos();
    }

    public boolean eliminarAlquiler(Long idAlquiler){
        return alquilerDAO.eliminarPorId(idAlquiler);
    }

    public List<IGestionAlquiler> listarAlquileresEnCursoHerramientas(){
        return alquilerDAO.listarAlquileresActivosHerramientas();
    }

}
