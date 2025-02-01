package model.specificClasses;

public abstract class Alquilable {
    private Long idAlquilable;
    private TipoCategoria categoria;
    private boolean disponible;
    private double tarifaBase;
    private String descripcion;

    public Alquilable() {

    }
    public Alquilable(TipoCategoria categoria, boolean disponible, double tarifaBase, String descripcion) {
        this.categoria = categoria;
        this.disponible = disponible;
        this.tarifaBase = tarifaBase;
        this.descripcion = descripcion;
    }

    public Alquilable(Long idAlquilable, TipoCategoria categoria, boolean disponible, double tarifaBase, String descripcion) {
        this.idAlquilable = idAlquilable;
        this.categoria = categoria;
        this.disponible = disponible;
        this.tarifaBase = tarifaBase;
        this.descripcion = descripcion;
    }


    public Long getIdAlquilable() {
        return idAlquilable;
    }

    public void setIdAlquilable(Long idAlquilable){
        this.idAlquilable = idAlquilable;
    }

    public TipoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoCategoria categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}