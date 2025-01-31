package service.vehiculos;

import dao.vehiculos.VehiculoDAO;
import model.specificClasses.vehicleClasses.Vehiculo;

import java.sql.SQLException;

public class VehiculoService {

    private final VehiculoDAO vehiculoDAO;

    public VehiculoService(VehiculoDAO vehiculoDAO) {
        this.vehiculoDAO = vehiculoDAO;
    }

    public boolean guardarVehiculo( Vehiculo vehiculo ){
        try {
            return vehiculoDAO.guardarVehiculo(vehiculo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
