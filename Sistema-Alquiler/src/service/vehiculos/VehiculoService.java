package service.vehiculos;

import dao.vehiculos.VehiculoDAO;
import model.factories.vehicleFactory.VehiculoFactory;
import model.specificClasses.TipoCategoria;
import model.specificClasses.vehicleClasses.TipoVehiculo;
import model.specificClasses.vehicleClasses.Vehiculo;

import java.sql.SQLException;
import java.util.Map;

public class VehiculoService {

    private final VehiculoDAO vehiculoDAO;
    private final Map<TipoVehiculo, VehiculoFactory> factoryMap;

    public VehiculoService(VehiculoDAO vehiculoDAO, Map<TipoVehiculo, VehiculoFactory> factoryMap) {
        this.vehiculoDAO = vehiculoDAO;
        this.factoryMap = factoryMap;

    }

    public boolean guardarVehiculo(TipoCategoria categoria, TipoVehiculo tipo, boolean disponible, TipoVehiculo tipo, double tarifaBase, String descripcion, String marca, String modelo) {
        try {
            Vehiculo nuevoVehiculo = verificarTipoDeVehiculo(tipo).crearAlquilable(categoria, tipo, disponible, tipo, tarifaBase, descripcion, marca, modelo);
            return guardarVehiculoEnDAO(nuevoVehiculo);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el vehículo en la base de datos", e);
        }
    }
    private VehiculoFactory verificarTipoDeVehiculo(TipoVehiculo tipo){
        VehiculoFactory vehiculoFactory = factoryMap.get(tipo);
        if(vehiculoFactory == null){
            throw new IllegalArgumentException("Tipo de vehiculo no válido: " + tipo);
        }
        return vehiculoFactory;
    }

    private boolean guardarVehiculoEnDAO(Vehiculo vehiculo) throws SQLException {
        return vehiculoDAO.guardarVehiculo(vehiculo);
    }

}
