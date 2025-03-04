package dao;

import config.DataBaseConnection;
import model.clases.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO implements IDAO<Vehiculo>{
    private static volatile VehiculoDAO instance;

    private static final String INSERT_SQL = "INSERT INTO vehiculos(alquilable_id, marca, modelo) VALUE(?,?,?);";
    private static final String UPDATE_SQL = "UPDATE vehiculos SET marca=?, modelo=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT FROM vehiculos";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM vehiculos WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM vehiculos WHERE id= ?";

    private VehiculoDAO(){}

    public static VehiculoDAO getInstance(){
        if(instance == null){
            synchronized (VehiculoDAO.class){
                if(instance == null){
                    instance = new VehiculoDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException{
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(Vehiculo object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setLong(1, object.getAlquilable().getIdAlquilable());
            statement.setString(2, object.getMarca());
            statement.setString(3, object.getModelo());

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear", e);
        }
    }

    @Override
    public boolean actualizar(Vehiculo object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setString(1, object.getMarca());
            statement.setString(2, object.getModelo());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehiculo> listar() {
        List<Vehiculo> vehiculoList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Vehiculo vehiculo = mapVehiculo(resultSet);
                vehiculoList.add(vehiculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehiculoList;
    }

    @Override
    public Vehiculo obtenerPorId(Long id) {
        Vehiculo vehiculo = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    vehiculo = mapVehiculo(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }

        return vehiculo;
    }

    @Override
    public boolean eliminarPorId(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar por ID", e);
        }
    }

    private Vehiculo mapVehiculo(ResultSet resultSet) throws SQLException {
        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setId(resultSet.getLong("id"));
        vehiculo.setAlquilable(AlquilableDAO.getInstance().obtenerPorId(resultSet.getLong("id_alquilable")));
        vehiculo.setMarca(resultSet.getString("marca"));
        vehiculo.setModelo(resultSet.getString("modelo"));

        return vehiculo;
    }
}
