package dao;

import config.DataBaseConnection;
import model.clases.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehiculoDAO implements IDAO<Vehiculo>{
    private static volatile VehiculoDAO instance;

    private static final String INSERT_SQL = "INSERT INTO vehiculos(id_alquilable, marca, modelo, id_categoria_alquilable) VALUE(?,?,?,?);";
    private static final String UPDATE_SQL = "UPDATE vehiculos SET marca=?, modelo=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM vehiculos";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM vehiculos WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM vehiculos WHERE id= ?";
    private static final String SELECT_BY_ID_ALQUILABLE_SQL = "SELECT * FROM vehiculos WHERE id_alquilable=?";

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
            statement.setLong(4, object.getAlquilable().getCategoria().getIdCategoria());

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

    public List<Vehiculo> traerListaVehiculoDisponible(List<Long> idAlquilable){
        List<Vehiculo> vehiculoList = new ArrayList<>();
        if (!idAlquilable.isEmpty()) {
            String sql = "SELECT * FROM vehiculos WHERE id_alquilable IN (" +
                    String.join(",", Collections.nCopies(idAlquilable.size(), "?")) + ")";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                for (int i = 0; i < idAlquilable.size(); i++) {
                    statement.setLong(i + 1, idAlquilable.get(i));
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Vehiculo vehiculo = mapVehiculo(resultSet);
                        vehiculoList.add(vehiculo);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return vehiculoList;
    }

    public Vehiculo obtenerByIdAlquilable(Long idAlquilable){
        Vehiculo vehiculo = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_ALQUILABLE_SQL)) {
            statement.setLong(1, idAlquilable);
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

    private Vehiculo mapVehiculo(ResultSet resultSet) throws SQLException {
        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setId(resultSet.getLong("id"));
        vehiculo.setAlquilable(AlquilableDAO.getInstance().obtenerPorId(resultSet.getLong("id_alquilable")));
        vehiculo.getAlquilable().setCategoria(CategoriaAlquilableDAO.getInstance().obtenerPorId(resultSet.getLong("id_categoria_alquilable")));
        vehiculo.setMarca(resultSet.getString("marca"));
        vehiculo.setModelo(resultSet.getString("modelo"));

        return vehiculo;
    }
}
