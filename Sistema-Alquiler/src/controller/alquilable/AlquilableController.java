package controller.alquilable;


import dao.AlquilableDAO;
import model.clases.Alquilable;
import model.clases.TipoAlquilable;
import java.util.List;

public class AlquilableController{

    private final AlquilableDAO alquilableDAO;

    public AlquilableController(){
        this.alquilableDAO = AlquilableDAO.getInstance();
    }

    public Alquilable traerAlquilable(TipoAlquilable tipoAlquilable, String descripcion, boolean disponible){
        crearAlquilable(tipoAlquilable, descripcion, disponible);
        Alquilable alquilable = obtenerUltimoAlquilable();
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
