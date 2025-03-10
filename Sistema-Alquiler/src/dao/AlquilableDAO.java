package dao;

import config.DataBaseConnection;
import model.clases.Alquilable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlquilableDAO implements IDAO<Alquilable>{


    private static volatile AlquilableDAO instance;

    private static final String INSERT_SQL = "INSERT INTO alquilables(id_tipo_alquilable, descripcion, disponible) VALUE(?,?,?);";
    private static final String UPDATE_SQL = "UPDATE alquilables SET disponible=?, descripcion=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM alquilables";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM alquilables WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM alquilables WHERE id= ?";
    private static final String SELECT_LAST_ALQUILABLE_SQL = "SELECT * FROM alquilables ORDER BY id DESC LIMIT 1";
    private static final String SELECT_ALL_DISPONIBLE_SQL = "SELECT * FROM alquilables WHERE disponible = true";
    private AlquilableDAO(){}

    public static AlquilableDAO getInstance(){
        if(instance == null){
            synchronized (AlquilableDAO.class){
                if(instance == null){
                    instance = new AlquilableDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(Alquilable object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setLong(1, object.getTipoAlquilable().getIdTipoAlquilable());
            statement.setString(2, object.getDescripcion());
            statement.setBoolean(3, object.isDisponible());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear", e);
        }
    }

    @Override
    public boolean actualizar(Alquilable object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setBoolean(1, object.isDisponible());
            statement.setString(2, object.getDescripcion());
            statement.setLong(3, object.getIdAlquilable());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alquilable> listar() {
        List<Alquilable> alquilableList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Alquilable alquilable = mapAlquilable(resultSet);
                alquilableList.add(alquilable);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alquilableList;
    }

    @Override
    public Alquilable obtenerPorId(Long id) {
        Alquilable alquilable = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    alquilable = mapAlquilable(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }

        return alquilable;
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

    public Alquilable obtenerUltimoAlquilable() {
        Alquilable alquilable = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LAST_ALQUILABLE_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                alquilable = mapAlquilable(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el último alquilable", e);
        }

        return alquilable;
    }

    public List<Long> traerListaIdAlquilableDisponible(){
        List<Long> idAlquilableList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DISPONIBLE_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Long idAlquilable = resultSet.getLong("id");
                idAlquilableList.add(idAlquilable);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idAlquilableList;
    }

    private Alquilable mapAlquilable(ResultSet resultSet) throws SQLException {
        Alquilable alquilable = new Alquilable();

        alquilable.setIdAlquilable(resultSet.getLong("id"));
        alquilable.setTipoAlquilable(TipoAlquilableDAO.getInstance().obtenerPorId(resultSet.getLong("id_tipo_alquilable")));
        alquilable.setDescripcion(resultSet.getString("descripcion"));
        alquilable.setDisponible(resultSet.getBoolean("disponible"));

        return alquilable;
    }
}
