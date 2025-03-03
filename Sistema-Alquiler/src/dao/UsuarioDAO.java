package dao;

import config.DataBaseConnection;
import model.clases.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IDAO<Usuario>{

    private static volatile UsuarioDAO instance;

    private static final String INSERT_SQL = "INSERT INTO usuarios(nombre, apellido, dni, email, celular) VALUE(?,?,?,?,?);";
    private static final String UPDATE_SQL = "UPDATE usuarios SET nombre=?, apellido=?, dni=?, email=?, celular=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM usuarios";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM usuarios WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM usuarios WHERE id=?";

    private UsuarioDAO(){}

    public static UsuarioDAO getInstance(){
        if(instance == null){
            synchronized (UsuarioDAO.class){
                if(instance == null){
                    instance = new UsuarioDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(Usuario object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, object.getNombre());
            statement.setString(2, object.getApellido());
            statement.setLong(3, object.getDni());
            statement.setString(4,object.getEmail());
            statement.setLong(5,object.getCelular());

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear usuario", e);
        }
    }

    @Override
    public boolean actualizar(Usuario object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setString(1, object.getNombre());
            statement.setString(2, object.getApellido());
            statement.setLong(3, object.getDni());
            statement.setString(4, object.getEmail());
            statement.setLong(5, object.getCelular());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarioList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Usuario usuario = mapUsuario(resultSet);
                usuarioList.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarioList;
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        Usuario usuario = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = mapUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }

        return usuario;
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

    private Usuario mapUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(resultSet.getLong("id"));
        usuario.setNombre(resultSet.getString("nombre"));
        usuario.setApellido(resultSet.getString("apellido"));
        usuario.setDni(resultSet.getLong("dni"));
        usuario.setEmail(resultSet.getString("email"));
        usuario.setCelular(resultSet.getLong("celular"));

        return usuario;
    }
}
