package model.clases;

public class Herramienta implements IAlquilable{
    private Long id;
    private Alquilable alquilable;
    private String marca;

    public Herramienta() {
    }

    public Herramienta(Alquilable alquilable, String marca) {
        this.alquilable = alquilable;
        this.marca = marca;
    }

    public Herramienta(Long id, Alquilable alquilable, String marca) {
        this.id = id;
        this.alquilable = alquilable;
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
this.id = id;
    }

    @Override
    public Alquilable getAlquilable() {
        return alquilable;
    }

    @Override
    public void setAlquilable(Alquilable alquilable) {
    this.alquilable = alquilable;
    }

    @Override
    public String toString(){
        return id + " - " +
                marca + " - " +
                alquilable.getTipoAlquilable().getNombreTipo() + " - " +
                alquilable.getTipoAlquilable().getTarifaBase() + " - " +
                alquilable.getDescripcion() + " - " +
                alquilable.isDisponible();
    }
}
