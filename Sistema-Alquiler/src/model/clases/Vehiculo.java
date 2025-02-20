package model.clases;


public class Vehiculo implements IAlquilable {

    private Long idVehiculo;
    private Alquilable alquilable;
    private String marca;
    private String modelo;

    public Vehiculo() {
    }

    public Vehiculo(Alquilable alquilable, String marca, String modelo) {
        this.alquilable = alquilable;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Vehiculo(Long idVehiculo, Alquilable alquilable, String marca, String modelo) {
        this.idVehiculo = idVehiculo;
        this.alquilable = alquilable;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public Long getId() {
        return idVehiculo;
    }

    @Override
    public void setId(Long id) {
        this.idVehiculo = id;
    }

    @Override
    public Alquilable getAlquilable() {
        return alquilable;
    }

    @Override
    public void setAlquilable(Alquilable alquilable) {
        this.alquilable = alquilable;
    }
}
