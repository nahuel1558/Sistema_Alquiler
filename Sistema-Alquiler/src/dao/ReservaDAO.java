package dao;

import config.DataBaseConnection;
import model.clases.Alquilable;
import model.clases.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO implements IDAO<Reserva> {

    private static volatile ReservaDAO instance;


    private static final String INSERT_RESERVA_SQL = "INSERT INTO reservas(dias_reservado, fecha_inicio, fecha_fin) VALUE(?,?,?);";
    private static final String SELECT_RESERVA_BY_ID_SQL = "SELECT * FROM reservas WHERE id = ?;";
    private static final String SELECT_RESERVA_ALL_SQL = "SELECT * FROM reservas";
    private static final String UPDATE_RESERVA_SQL = "UPDATE reservas SET dias_reservado=?, fecha_inicio=?, fecha_fin=? WHERE id=?;";
    private static final String DELETE_RESERVA_BY_ID_SQL = "DELETE FROM reservas WHERE id=?;";
    private static final String SELECT_LAST_ALQUILABLE_SQL = "SELECT * FROM reservas ORDER BY id DESC LIMIT 1";

    private ReservaDAO(){}

    public static ReservaDAO getInstance(){
        if(instance == null){
            synchronized (ReservaDAO.class){
                if(instance == null){
                    instance = new ReservaDAO();
                }
            }
        }
        return instance;
    }

    private Connection getConnection()throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public boolean crear(Reserva object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_RESERVA_SQL)) {

            java.sql.Date fechaInicioSQL = new java.sql.Date(object.getFechaInicio().getTime());
            java.sql.Date fechaFinSQL = new java.sql.Date(object.getFechaFin().getTime());

            statement.setInt(1, object.getDiasReservado());
            statement.setDate(2, fechaInicioSQL);
            statement.setDate(3,fechaFinSQL);

            Integer lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la reserva", e);
        }
    }

    @Override
    public boolean actualizar(Reserva object) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_RESERVA_SQL)){

            statement.setInt(1, object.getDiasReservado());
            statement.setDate(2, (Date) object.getFechaInicio());
            statement.setDate(3,(Date) object.getFechaFin());

            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reserva> listar() {
        List<Reserva> reservaList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_RESERVA_ALL_SQL);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Reserva reserva = mapReserva(resultSet);
                reservaList.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaList;
    }

    @Override
    public Reserva obtenerPorId(Long id) {
        Reserva reserva = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_RESERVA_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = mapReserva(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la reserva por ID", e);
        }

        return reserva;
    }

    @Override
    public boolean eliminarPorId(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RESERVA_BY_ID_SQL)) {
            statement.setLong(1, id);
            int lineaAfectada = statement.executeUpdate();
            return lineaAfectada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la reserva por ID", e);
        }
    }
    public Reserva obtenerUltimaReserva() {
        Reserva reserva = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LAST_ALQUILABLE_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                reserva = mapReserva(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la última reserva", e);
        }

        return reserva;
    }

    private Reserva mapReserva(ResultSet resultSet) throws SQLException {
        Reserva reserva = new Reserva();

        reserva.setIdReserva(resultSet.getLong("id"));
        reserva.setDiasReservado(resultSet.getInt("dias_reservado"));
        java.sql.Date fechaInicioSQL = resultSet.getDate("fecha_inicio");
        java.sql.Date fechaFinSQL = resultSet.getDate("fecha_fin");

        reserva.setFechaInicio(new Date(fechaInicioSQL.getTime()));  // java.util.Date
        reserva.setFechaFin(new Date(fechaFinSQL.getTime()));

        return reserva;
    }

}
