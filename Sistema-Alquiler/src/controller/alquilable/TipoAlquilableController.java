package controller.alquilable;

import dao.TipoAlquilableDAO;
import model.clases.TipoAlquilable;

public class TipoAlquilableController {

    private final TipoAlquilableDAO tipoAlquilableDAO;

    public TipoAlquilableController(){
        this.tipoAlquilableDAO = TipoAlquilableDAO.getInstance();
    }

    public TipoAlquilable traerTipoAlquilble(String nombre, double tarifaBase){
        crearTipoAlquilable(nombre, tarifaBase);
        TipoAlquilable tipoAlquilable = obtenerUltimoTipoAlquilable();
        return tipoAlquilable;
    }

    private void crearTipoAlquilable(String nombre, double tarifaBase){
        tipoAlquilableDAO.crear(new TipoAlquilable(nombre, tarifaBase));
    }

    private TipoAlquilable obtenerUltimoTipoAlquilable(){
        return tipoAlquilableDAO.obtenerUltimoTipoAlquilable();
    }
}
