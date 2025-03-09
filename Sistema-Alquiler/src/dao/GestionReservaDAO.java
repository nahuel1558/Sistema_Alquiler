package dao;

import config.DataBaseConnection;
import model.clases.Alquilable;
import model.clases.Usuario;
import model.clasesAlquileres.GestionReserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionReservaDAO implements IDAO<GestionReserva> {

    private static volatile GestionReservaDAO instance;

    private static final String INSERT_SQL = "INSERT INTO gestion_reservas(id_usuario, id_reserva, estado, costo) VALUE(?,?,?,?);";
    private static final String UPDATE_SQL = "UPDATE gestion_reservas SET estado=?, costo=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM gestion_reservas ";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM gestion_reservas WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM gestion_reservas WHERE id= ?";
    private static final String SELECT_LAST_ALQUILABLE_SQL = "SELECT * FROM gestion_reservas ORDER BY id DESC LIMIT 1";
    private static final String SELECT_ALL_BY_ID_DISPONIBLE_SQL = "SELECT * FROM gestion_reservas WHERE id_usuario = ? AND estado = true";
    private GestionReservaDAO(){}

    public static GestionReservaDAO getInstance(){
        if(instance == null){
            synchronized (GestionReservaDAO.class){
                if(instance == null){
                    instance = new GestionReservaDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(GestionReserva object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setLong(1, object.getUsuario().getIdUsuario());
            statement.setLong(2, object.getReserva().getIdReserva());
            statement.setBoolean(3, object.isEstado());
            statement.setDouble(4, object.getCosto());

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear", e);
        }
    }

    @Override
    public boolean actualizar(GestionReserva object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)){

            statement.setBoolean(1, object.isEstado());
            statement.setDouble(2, object.getCosto());
            statement.setLong(3, object.getIdGestionReserva());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GestionReserva> listar() {
        List<GestionReserva> gestionReservaList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                GestionReserva gestionReserva = mapGestionReserva(resultSet);
                gestionReservaList.add(gestionReserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gestionReservaList;
    }

    @Override
    public GestionReserva obtenerPorId(Long id) {
        GestionReserva gestionReserva = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    gestionReserva = mapGestionReserva(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
        return gestionReserva;
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

    public GestionReserva obtenerUltimaGestionReserva() {
        GestionReserva gestionReserva = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LAST_ALQUILABLE_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                gestionReserva = mapGestionReserva(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el último alquilable", e);
        }

        return gestionReserva;
    }

    public List<GestionReserva> listarGestionReservaEnCurso(Long idUsuario) {
        List<GestionReserva> gestiones = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID_DISPONIBLE_SQL)) {

            statement.setLong(1, idUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    GestionReserva gestionReserva = mapGestionReserva(resultSet);
                    gestiones.add(gestionReserva);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la lista", e);
        }

        return gestiones;
    }

    private GestionReserva mapGestionReserva(ResultSet resultSet) throws SQLException {
        GestionReserva gestionReserva = new GestionReserva();
        gestionReserva.setIdGestionReserva(resultSet.getLong("id"));
        gestionReserva.setReserva(ReservaDAO.getInstance().obtenerPorId(resultSet.getLong("id_reserva")));
        gestionReserva.setUsuario(UsuarioDAO.getInstance().obtenerPorId(resultSet.getLong("id_usuario")));
        gestionReserva.setEstado(resultSet.getBoolean("estado"));
        gestionReserva.setCosto(resultSet.getDouble("costo"));
        return gestionReserva;
    }
}
