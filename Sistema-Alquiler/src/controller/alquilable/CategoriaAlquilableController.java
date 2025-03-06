package controller.alquilable;

import dao.CategoriaAlquilableDAO;
import model.clases.CategoriaAlquilable;

import java.util.List;

public class CategoriaAlquilableController {
    private final CategoriaAlquilableDAO categoriaAlquilableDAO;

    public CategoriaAlquilableController(){
        this.categoriaAlquilableDAO = CategoriaAlquilableDAO.getInstance();
    }

    public List<CategoriaAlquilable> traerLista(){
        return categoriaAlquilableDAO.listar();
    }
}
