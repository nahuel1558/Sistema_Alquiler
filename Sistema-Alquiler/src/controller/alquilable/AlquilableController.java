package controller.alquilable;

import controller.alquilablesEspecificos.IAlquilableController;
import controller.alquilablesEspecificos.VehiculoController;
import dao.AlquilableDAO;
import model.clases.Alquilable;
import model.clases.CategoriaAlquilable;
import model.clases.IAlquilable;
import model.clases.TipoAlquilable;
import model.factories.factoryMethod.AlquilableFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlquilableController{

    private final AlquilableDAO alquilableDAO;
    private Map<String, IAlquilableController> controladoresPorCategoria;


    public AlquilableController(){
        this.alquilableDAO = AlquilableDAO.getInstance();
        controladoresPorCategoria = new HashMap<>();
        controladoresPorCategoria.put("vehiculos", new VehiculoController());
        //controladoresPorCategoria.put("herramientas", new HerramientaController());
    }

    public Alquilable crearAlquilable(CategoriaAlquilable categoriaAlquilable, TipoAlquilable tipoAlquilable,
                                             String descripcion, boolean disponible){
        alquilableDAO.crear(new Alquilable(categoriaAlquilable, tipoAlquilable, descripcion, disponible));
        Alquilable alquilable = obtenerUltimoAlquilable();
        return alquilable;
    }

    public boolean actualizar(Alquilable alquilable){
        return alquilableDAO.actualizar(alquilable);
    }

    public List<Alquilable> traerLista(){
        return alquilableDAO.listar();
    }

    public List<IAlquilable> traerListaDisponibles(CategoriaAlquilable categoriaAlquilable){
        List<Long> idAlquilables = traerListaIdAlquilableDisponible();
        IAlquilableController controller = controladoresPorCategoria.get(categoriaAlquilable.getNombreCategoria().toLowerCase());
        return controller.traerListaDisponible(idAlquilables);
    }

    public AlquilableFactory obtenerFactory(CategoriaAlquilable categoriaAlquilable){
        IAlquilableController<?> controller = controladoresPorCategoria.get(categoriaAlquilable.getNombreCategoria().toLowerCase());
        return controller.traerFactory();
    }
    public IAlquilableController obtenerControladora(String nombre){
        return controladoresPorCategoria.get(nombre.toLowerCase());
    }

    private Alquilable obtenerUltimoAlquilable(){
        return alquilableDAO.obtenerUltimoAlquilable();
    }

    private List<Long> traerListaIdAlquilableDisponible(){
        return alquilableDAO.traerListaIdAlquilableDisponible();
    }
}
