package model.clases;

public class Alquilable{
    private Long idAlquilable;
    private CategoriaAlquilable categoria;
    private TipoAlquilable tipoAlquilable;
    private String descripcion;
    private boolean disponible;

    public Alquilable() {
    }

    public Alquilable(TipoAlquilable tipoAlquilable, String descripcion, boolean disponible) {
        this.tipoAlquilable = tipoAlquilable;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    public Alquilable(CategoriaAlquilable categoria, TipoAlquilable tipoAlquilable, String descripcion, boolean disponible) {
        this.categoria = categoria;
        this.tipoAlquilable = tipoAlquilable;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    public Alquilable(boolean disponible, String descripcion, TipoAlquilable tipoAlquilable, CategoriaAlquilable categoria, Long idAlquilable) {
        this.disponible = disponible;
        this.descripcion = descripcion;
        this.tipoAlquilable = tipoAlquilable;
        this.categoria = categoria;
        this.idAlquilable = idAlquilable;
    }

    public Long getIdAlquilable() {
        return idAlquilable;
    }

    public void setIdAlquilable(Long idAlquilable){
        this.idAlquilable = idAlquilable;
    }

    public CategoriaAlquilable getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAlquilable categoria) {
        this.categoria = categoria;
    }

    public TipoAlquilable getTipoAlquilable() {
        return tipoAlquilable;
    }

    public void setTipoAlquilable(TipoAlquilable tipoAlquilable) {
        this.tipoAlquilable = tipoAlquilable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}