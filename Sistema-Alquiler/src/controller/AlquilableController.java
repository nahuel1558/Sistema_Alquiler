package controller;

import dao.AlquilableDAO;
import model.clases.Alquilable;
import model.clases.CategoriaAlquilable;
import model.clases.TipoAlquilable;

public class AlquilableController{

    private final AlquilableDAO alquilableDAO;

    public AlquilableController(){
        this.alquilableDAO = AlquilableDAO.getInstance();
    }

    public Alquilable crearAlquilable(CategoriaAlquilable categoriaAlquilable, TipoAlquilable tipoAlquilable,
                                       String descripcion, boolean disponible){
        Alquilable alquilable = obtenerUltimoAlquilable();
        alquilableDAO.crear(new Alquilable(categoriaAlquilable, tipoAlquilable, descripcion, disponible));
        return alquilable;
    }

    private Alquilable obtenerUltimoAlquilable(){
        return alquilableDAO.obtenerUltimoAlquilable();
    }

}
