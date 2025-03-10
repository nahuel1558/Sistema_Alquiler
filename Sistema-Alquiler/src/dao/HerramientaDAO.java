package dao;

import config.DataBaseConnection;
import model.clases.Herramienta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HerramientaDAO implements IDAO<Herramienta>{
    private static volatile HerramientaDAO instance;

    private static final String INSERT_SQL = "INSERT INTO herramientas(id_alquilable, marca) VALUE(?,?);";
    private static final String UPDATE_SQL = "UPDATE herramientas SET marca=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM herramientas";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM herramientas WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM herramientas WHERE id= ?";
    private static final String SELECT_BY_ID_ALQUILABLE_SQL = "SELECT * FROM herramientas WHERE id_alquilable=?";

    private HerramientaDAO(){}

    public static HerramientaDAO getInstance(){
        if(instance == null){
            synchronized (HerramientaDAO.class){
                if(instance == null){
                    instance = new HerramientaDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }


    @Override
    public boolean crear(Herramienta object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setLong(1, object.getAlquilable().getIdAlquilable());
            statement.setString(2, object.getMarca());

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear", e);
        }    }

    @Override
    public boolean actualizar(Herramienta object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setString(1, object.getMarca());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }    }

    @Override
    public List<Herramienta> listar() {
        List<Herramienta> herramientas = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Herramienta herramienta = mapHerramienta(resultSet);
                herramientas.add(herramienta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return herramientas;
    }

    @Override
    public Herramienta obtenerPorId(Long id) {
        Herramienta herramienta = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    herramienta = mapHerramienta(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
        return herramienta;
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

    public List<Herramienta> traerListaHerramientaDisponible(List<Long> idAlquilable){
        List<Herramienta> herramientas = new ArrayList<>();
        if (!idAlquilable.isEmpty()) {
            String sql = "SELECT * FROM herramientas WHERE id_alquilable IN (" +
                    String.join(",", Collections.nCopies(idAlquilable.size(), "?")) + ")";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                for (int i = 0; i < idAlquilable.size(); i++) {
                    statement.setLong(i + 1, idAlquilable.get(i));
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Herramienta herramienta = mapHerramienta(resultSet);
                        herramientas.add(herramienta);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return herramientas;
    }

    public Herramienta obtenerByIdAlquilable(Long idAlquilable){
        Herramienta herramienta = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_ALQUILABLE_SQL)) {
            statement.setLong(1, idAlquilable);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    herramienta = mapHerramienta(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }

        return herramienta;
    }

    private Herramienta mapHerramienta(ResultSet resultSet) throws SQLException {
        Herramienta herramienta = new Herramienta();

        herramienta.setId(resultSet.getLong("id"));
        herramienta.setAlquilable(AlquilableDAO.getInstance().obtenerPorId(resultSet.getLong("id_alquilable")));
        herramienta.getAlquilable().setCategoria(CategoriaAlquilableDAO.getInstance().obtenerPorId(resultSet.getLong("id_categoria_alquilable")));
        herramienta.setMarca(resultSet.getString("marca"));

        return herramienta;
    }
}
