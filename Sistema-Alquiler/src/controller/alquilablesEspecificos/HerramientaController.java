package controller.alquilablesEspecificos;

import controller.alquilable.AlquilableController;
import dao.HerramientaDAO;
import model.clases.Alquilable;
import model.clases.Herramienta;
import model.clases.IAlquilable;
import model.factories.factoryMethod.AlquilableFactory;
import model.factories.factoryMethod.HerramientaFactory;

import java.util.List;

public class HerramientaController implements IAlquilableController{

    private AlquilableController alquilableController;

    private final HerramientaDAO herramientaDAO;

    public HerramientaController(){
        this.herramientaDAO = HerramientaDAO.getInstance();
        this.alquilableController = new AlquilableController();
    }

    public boolean crearHerramienta(Long idCategoria, String nombre, double tarifaBase,
                                 boolean disponible, String descripcion, String marca){
        Alquilable alquilable = alquilableController.traerAlquilable(idCategoria, nombre, tarifaBase, descripcion, disponible);
        return herramientaDAO.crear(new Herramienta(alquilable, marca));
    }

    public void eliminarPorId(Long id){
        herramientaDAO.eliminarPorId(id);
    }

    public List<Herramienta> listar(){
        return herramientaDAO.listar();
    }

    @Override
    public IAlquilable obtenerById(Long id) {
        return herramientaDAO.obtenerPorId(id);
    }

    @Override
    public AlquilableFactory traerFactory() {
        return new HerramientaFactory();
    }

    @Override
    public List<Herramienta> traerListaDisponible(List idAlquilable) {
        return herramientaDAO.traerListaHerramientaDisponible(idAlquilable);
    }
}
