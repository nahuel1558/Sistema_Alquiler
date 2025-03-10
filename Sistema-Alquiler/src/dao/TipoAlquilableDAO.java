package dao;

import config.DataBaseConnection;
import model.clases.TipoAlquilable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoAlquilableDAO implements IDAO<TipoAlquilable> {

    private static volatile TipoAlquilableDAO instance;

    private static final String INSERT_SQL = "INSERT INTO tipos_alquilable(nombre, tarifa_base) VALUE(?,?);";
    private static final String UPDATE_SQL = "UPDATE tipos_alquilable SET nombre=?, tarifa_base=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM tipos_alquilable";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM tipos_alquilable WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM tipos_alquilable WHERE id= ?";
    private static final String SELECT_LAST_TIPO_ALQUILABLE_SQL = "SELECT * FROM tipos_alquilable ORDER BY id DESC LIMIT 1";


    private TipoAlquilableDAO(){}

    public static TipoAlquilableDAO getInstance(){
        if(instance == null){
            synchronized (TipoAlquilableDAO.class){
                if(instance == null){
                    instance = new TipoAlquilableDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(TipoAlquilable object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, object.getNombreTipo());
            statement.setDouble(2, object.getTarifaBase());


            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear", e);
        }
    }

    @Override
    public boolean actualizar(TipoAlquilable object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setString(1, object.getNombreTipo());
            statement.setDouble(2, object.getTarifaBase());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TipoAlquilable> listar() {
        List<TipoAlquilable> tipoAlquilableList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                TipoAlquilable tipoAlquilable = mapTipoAlquilable(resultSet);
                tipoAlquilableList.add(tipoAlquilable);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tipoAlquilableList;
    }

    @Override
    public TipoAlquilable obtenerPorId(Long id) {
        TipoAlquilable tipoAlquilable = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    tipoAlquilable = mapTipoAlquilable(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }

        return tipoAlquilable;
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

    public TipoAlquilable obtenerUltimoTipoAlquilable() {
        TipoAlquilable tipoAlquilable = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LAST_TIPO_ALQUILABLE_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                tipoAlquilable = mapTipoAlquilable(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el último tipo alquilable", e);
        }

        return tipoAlquilable;
    }

    private TipoAlquilable mapTipoAlquilable(ResultSet resultSet) throws SQLException {
        TipoAlquilable tipoAlquilable = new TipoAlquilable();

        tipoAlquilable.setIdTipoAlquilable(resultSet.getLong("id"));
        tipoAlquilable.setNombreTipo(resultSet.getString("nombre"));
        tipoAlquilable.setTarifaBase(resultSet.getDouble("tarifa_base"));

        return tipoAlquilable;
    }
}
