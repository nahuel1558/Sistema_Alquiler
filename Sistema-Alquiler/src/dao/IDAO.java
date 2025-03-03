package dao;

import java.util.List;

public interface IDAO <E extends Object> {
    boolean crear(E object);
    boolean actualizar(E object);
    List<E> listar();
    Object obtenerPorId(Long id);
    boolean eliminarPorId(Long id);
}
