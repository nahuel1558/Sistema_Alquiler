package model.clasesAlquileres;

import model.clases.IAlquilable;

public interface IGestionAlquiler {
    Long getId();
    void setId(Long id);
    GestionReserva getGestionReserva();
    void setGestionReserva(GestionReserva gestionReserva);
    IAlquilable getAlquiler();
    void setAlquiler(IAlquilable alquiler);
    double calcularCosto();
    void guardarCosto();

    String verDescripcion();
    }
