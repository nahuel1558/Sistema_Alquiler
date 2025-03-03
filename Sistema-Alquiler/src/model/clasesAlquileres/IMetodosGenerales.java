package model.clasesAlquileres;

import model.clases.IAlquilable;

public interface IMetodosGenerales {
    Long getId();
    void setId(Long id);
    double calcularCosto();
    String verDescripcion();
}
