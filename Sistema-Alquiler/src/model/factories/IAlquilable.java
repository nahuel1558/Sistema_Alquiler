package model.factories;

import model.clases.Alquilable;

public interface IAlquilable <T extends Object> {

    String getDescripcion();
    boolean isDisponible();
    void setDisponible(boolean disponible);
}
