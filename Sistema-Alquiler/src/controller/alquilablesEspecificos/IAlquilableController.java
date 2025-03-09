package controller.alquilablesEspecificos;

import model.clases.IAlquilable;
import model.factories.factoryMethod.AlquilableFactory;

import java.util.List;

public interface IAlquilableController<T extends Object> {

    IAlquilable obtenerById(Long id);
    List<T> traerListaDisponible(List<Long> idAlquilable);
    AlquilableFactory traerFactory();
}
