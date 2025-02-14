package dao;

import config.DataBaseConnection;
import model.clases.Vehiculo;

import java.sql.*;

public class VehiculoDAO {
    private static volatile VehiculoDAO instance;

    private static final String INSERT_SQL = "INSERT INTO vehiculo(tipo_vehiculo, marca, modelo, tarifaBase, disponible) VALUE(?,?,?,?,?);";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM vehiculos WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM vehiculos";
    private static final String UPDATE_SQL = "UPDATE vehiculos SET tipo_vehiculo=?, marca=?, modelo=?, tarifaBase=?, disponible=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM vehiculos WHERE = ?";

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

    public boolean guardarVehiculo(Vehiculo vehiculo) throws SQLException{

        return false;
    }

}
