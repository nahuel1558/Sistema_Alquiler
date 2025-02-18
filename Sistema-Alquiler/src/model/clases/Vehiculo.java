package model.clases;


public class Vehiculo extends Alquilable {
    private TipoAlquilable tipoVehiculo;
    private String marca;
    private String modelo;


    public Vehiculo() {
    }

    public Vehiculo(CategoriaAlquilable categoria, boolean disponible, TipoAlquilable tipoVehiculo, String marca, String modelo) {
        super(categoria, disponible);
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Vehiculo(Long idAlquilable, CategoriaAlquilable categoria, boolean disponible, TipoAlquilable tipoVehiculo, String marca, String modelo) {
        super(idAlquilable, categoria, disponible);
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
        this.modelo = modelo;
    }


    public TipoAlquilable getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoAlquilable tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
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
    public String getDescripcion() {
        return String.format("--Descripcion--\nMarca: " + marca + "\nModelo: " + modelo + "\nVehiculo: " + getTipoVehiculo().getNombreTipo() + "\nPrecio por dia: " + getTipoVehiculo().getTarifaBase());
    }

    @Override
    public boolean estaDisponible() {
        return isDisponible();
    }

    @Override
    public void reservarAlquilable() {
        setDisponible(false);
    }

    @Override
    public void liberarAlquilable() {
        setDisponible(true);
    }

    @Override
    public double calcularCosto(Integer diasReservados) {
        return tipoVehiculo.calcularCosto(diasReservados);
    }

    @Override
    public TipoAlquilable obtenerTipoAlquilable() {
        return tipoVehiculo;
    }
}
