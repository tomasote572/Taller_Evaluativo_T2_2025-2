package is.escuela.edu.co.Taller_Evaluativo_2.modelo;

public class Chef {
    private Long id;
    private String nombre;
    private TipoDeChef tipo;
    private String temporada;

    public Chef(Long id, String nombre, TipoDeChef tipo, String temporada) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.temporada = temporada;
    }

    public Long getId() {
        return id; }
    public void setId(Long id) {
        this.id = id; }
    public String getNombre() {
        return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }
    public TipoDeChef getTipo() {
        return tipo; }
    public void setTipo(TipoDeChef tipo) {
        this.tipo = tipo; }
    public String getTemporada() {
        return temporada; }
    public void setTemporada(String temporada) {
        this.temporada = temporada; }
}

