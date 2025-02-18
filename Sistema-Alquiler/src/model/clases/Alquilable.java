package model.clases;

public abstract class Alquilable implements IAlquilable {
    private Long idAlquilable;
    private CategoriaAlquilable categoria;
    private boolean disponible;

    public Alquilable() {
    }

    public Alquilable(CategoriaAlquilable categoria, boolean disponible) {
        this.categoria = categoria;
        this.disponible = disponible;
    }

    public Alquilable(Long idAlquilable, CategoriaAlquilable categoria, boolean disponible) {
        this.idAlquilable = idAlquilable;
        this.categoria = categoria;
        this.disponible = disponible;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}