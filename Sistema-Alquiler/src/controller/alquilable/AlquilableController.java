package controller.alquilable;


import dao.AlquilableDAO;
import model.clases.Alquilable;
import model.clases.CategoriaAlquilable;
import model.clases.TipoAlquilable;
import java.util.List;

public class AlquilableController{

    private final AlquilableDAO alquilableDAO;
    private TipoAlquilableController tipoAlquilableController;
    private CategoriaAlquilableController categoriaAlquilableController;

    public AlquilableController(){
        this.alquilableDAO = AlquilableDAO.getInstance();
        tipoAlquilableController = new TipoAlquilableController();
        categoriaAlquilableController = new CategoriaAlquilableController();
    }

    public Alquilable traerAlquilable(Long idCategoria, String nombre, Double tarifaBase, String descripcion, boolean disponible){
        TipoAlquilable tipoAlquilable = tipoAlquilableController.traerTipoAlquilble(nombre, tarifaBase);
        crearAlquilable(tipoAlquilable, descripcion, disponible);
        Alquilable alquilable = obtenerUltimoAlquilable();
        CategoriaAlquilable categoriaAlquilable = categoriaAlquilableController.traerCategoriaById(idCategoria);
        alquilable.setCategoria(categoriaAlquilable);
        return alquilable;
    }

    private void crearAlquilable(TipoAlquilable tipoAlquilable, String descripcion, boolean disponible){
        alquilableDAO.crear(new Alquilable(tipoAlquilable, descripcion, disponible));
    }

    public boolean actualizar(Alquilable alquilable){
        return alquilableDAO.actualizar(alquilable);
    }

    public List<Alquilable> traerLista(){
        return alquilableDAO.listar();
    }

    private Alquilable obtenerUltimoAlquilable(){
        return alquilableDAO.obtenerUltimoAlquilable();
    }

    public List<Long> traerListaIdAlquilableDisponible(){
        return alquilableDAO.traerListaIdAlquilableDisponible();
    }
}
