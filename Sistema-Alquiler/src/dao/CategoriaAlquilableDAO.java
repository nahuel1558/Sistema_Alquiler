package dao;

import config.DataBaseConnection;
import model.clases.CategoriaAlquilable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaAlquilableDAO implements IDAO<CategoriaAlquilable> {

    private static volatile CategoriaAlquilableDAO instance;

    private static final String INSERT_SQL = "INSERT INTO categorias_alquilable(id, nombre_categoria) VALUE(?,?);";
    private static final String UPDATE_SQL = "UPDATE categorias_alquilable SET nombre_categoria=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM categorias_alquilable";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM categorias_alquilable WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM categorias_alquilable WHERE id= ?";


    private CategoriaAlquilableDAO(){}

    public static CategoriaAlquilableDAO getInstance(){
        if(instance == null){
            synchronized (CategoriaAlquilableDAO.class){
                if(instance == null){
                    instance = new CategoriaAlquilableDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(CategoriaAlquilable object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, object.getNombreCategoria());

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear", e);
        }
    }

    @Override
    public boolean actualizar(CategoriaAlquilable object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setString(1, object.getNombreCategoria());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CategoriaAlquilable> listar() {
        List<CategoriaAlquilable> categoriaList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                CategoriaAlquilable categoria = mapCategoria(resultSet);
                categoriaList.add(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoriaList;
    }

    @Override
    public CategoriaAlquilable obtenerPorId(Long id) {
        CategoriaAlquilable categoriaAlquilable = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    categoriaAlquilable = mapCategoria(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }

        return categoriaAlquilable;
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

    private CategoriaAlquilable mapCategoria(ResultSet resultSet) throws SQLException {
        CategoriaAlquilable categoria = new CategoriaAlquilable();

        categoria.setIdCategoria(resultSet.getLong("id"));
        categoria.setNombreCategoria(resultSet.getString("nombre_categoria"));

        return categoria;
    }
}
