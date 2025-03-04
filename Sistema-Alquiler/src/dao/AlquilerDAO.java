
package dao;

import config.DataBaseConnection;
import model.clases.CategoriaAlquilable;
import model.clasesAlquileres.AlquilerVehiculo;
import model.clasesAlquileres.IGestionAlquiler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAO implements IDAO<IGestionAlquiler> {

    private static volatile AlquilerDAO instance;

    private static final String INSERT_SQL = "INSERT INTO alquileres(id_gestion_reserva, id_alquilable, categoria) VALUE(?,?);";
    private static final String UPDATE_SQL = "UPDATE alquileres SET id_gestion_reserva=?, id_alquilable=?, categoria=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM alquileres";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM alquileres ";
    private static final String DELETE_SQL = "DELETE FROM alquileres WHERE id= ?";
    //private static final String SELECT_LAST_ALQUILABLE_SQL = "SELECT * FROM alquileres ORDER BY id DESC LIMIT 1";

    private AlquilerDAO(){}

    public static AlquilerDAO getInstance(){
        if(instance == null){
            synchronized (AlquilerDAO.class){
                if(instance == null){
                    instance = new AlquilerDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(IGestionAlquiler object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setLong(1, object.getGestionReserva().getIdGestionReserva());
            statement.setLong(2, object.getAlquiler().getAlquilable().getIdAlquilable());
            statement.setString(3, object.getAlquiler().getAlquilable().getCategoria().getNombreCategoria());

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear", e);
        }    }

    @Override
    public boolean actualizar(IGestionAlquiler object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setLong(1, object.getGestionReserva().getIdGestionReserva());
            statement.setLong(2, object.getAlquiler().getAlquilable().getIdAlquilable());
            statement.setString(3, object.getAlquiler().getAlquilable().getCategoria().getNombreCategoria());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<IGestionAlquiler> listar() {
        List<IGestionAlquiler> iGestionAlquilerList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                IGestionAlquiler gestionAlquiler = mapGestionAlquiler(resultSet);
                iGestionAlquilerList.add(gestionAlquiler);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return iGestionAlquilerList;
    }

    @Override
    public IGestionAlquiler obtenerPorId(Long id) {
        IGestionAlquiler iGestionAlquiler = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    iGestionAlquiler = mapGestionAlquiler(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }

        return iGestionAlquiler;
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
    private IGestionAlquiler mapGestionAlquiler(ResultSet resultSet) throws SQLException {
        IGestionAlquiler iGestionAlquiler = null;

        iGestionAlquiler.setId(resultSet.getLong("id"));
        iGestionAlquiler.setGestionReserva(GestionReservaDAO.getInstance().obtenerPorId(resultSet.getLong("id_gestion_reserva")));
        return iGestionAlquiler;
    }

    public List<IGestionAlquiler> listarAlquilerVehiculo(CategoriaAlquilable categoria) {
        List<IGestionAlquiler> iGestionAlquilerList = new ArrayList<>();
        String sql = "SELECT * FROM alquileres WHERE categoria = 'vehiculo'";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, categoria.getNombreCategoria().toLowerCase());
    try(ResultSet resultSet = statement.executeQuery()){
        while(resultSet.next()){
            IGestionAlquiler iGestionAlquiler = mapAlquilerVehiculo(resultSet);
            iGestionAlquilerList.add(iGestionAlquiler);
        }
    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return iGestionAlquilerList;
    }

    private IGestionAlquiler mapAlquilerVehiculo(ResultSet resultSet) throws SQLException {
        IGestionAlquiler iGestionAlquiler = new AlquilerVehiculo();
        iGestionAlquiler.setId(resultSet.getLong("id"));
        iGestionAlquiler.setGestionReserva(GestionReservaDAO.getInstance().obtenerPorId(resultSet.getLong("id_gestion_reserva")));
        iGestionAlquiler.setAlquiler(VehiculoDAO.getInstance().obtenerPorId(resultSet.getLong("id_alquilable")));
        return iGestionAlquiler;
    }
}
