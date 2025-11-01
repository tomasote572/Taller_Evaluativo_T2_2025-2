package is.escuela.edu.co.Taller_Evaluativo_2.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

@Document(collection = "recetas")
public class Receta {
    @Id
    private Long id;
    private String titulo;
    private List<String> ingredientes;
    private List<String> pasos;
    private Chef chef;

    public Receta() {}

    public Receta(Long id, String titulo, List<String> ingredientes, List<String> pasos, Chef chef) {
        this.id = id;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.chef = chef;
    }
        public Long getId() {
            return id;}
        public void setId(Long id) {
            this.id = id;}
        public String getTitulo() {
        return titulo;}
        public void setTitulo(String titulo) {
            this.titulo = titulo;}
        public List<String> getIngredientes() {
            return ingredientes;}
        public void setIngredientes(List<String> ingredientes) {
            this.ingredientes = ingredientes;}
        public List<String> getPasos() {
            return pasos;}
        public void setPasos(List<String> pasos) {
            this.pasos = pasos;}
        public Chef getChef() {
            return chef; }
        public void setChef(Chef chef) {
            this.chef = chef; }
}

