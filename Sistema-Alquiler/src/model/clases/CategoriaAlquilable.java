package model.clases;

public class CategoriaAlquilable {
    private Long idCategoria;
    private String nombreCategoria;

    public CategoriaAlquilable(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public CategoriaAlquilable(Long idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
