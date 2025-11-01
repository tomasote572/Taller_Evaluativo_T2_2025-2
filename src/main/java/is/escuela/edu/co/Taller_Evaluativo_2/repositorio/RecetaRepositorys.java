package is.escuela.edu.co.Taller_Evaluativo_2.repositorio;

import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RecetaRepositorys {

    private final Map<Long, Receta> recetas = new HashMap<>();
    private final AtomicLong contador = new AtomicLong(1);

    public Receta guardar(Receta receta) {
        if (receta.getId() == null) {
            receta.setId(contador.getAndIncrement());
        }
        recetas.put(receta.getId(), receta);
        return receta;
    }

    public List<Receta> encontrarTodas() {
        return new ArrayList<>(recetas.values());
    }

    public Optional<Receta> encontrarPorId(Long id) {
        return Optional.ofNullable(recetas.get(id));
    }

    public List<Receta> encontrarPorTipoChef(TipoDeChef tipo) {
        return recetas.values().stream()
                .filter(receta -> receta.getChef().getTipo() == tipo)
                .toList();
    }

    public List<Receta> encontrarPorTemporada(String temporada) {
        return recetas.values().stream()
                .filter(receta -> temporada.equals(receta.getChef().getTemporada()))
                .toList();
    }

    public List<Receta> encontrarPorIngrediente(String ingrediente) {
        return recetas.values().stream()
                .filter(receta -> receta.getIngredientes().contains(ingrediente))
                .toList();
    }

    public void eliminarPorId(Long id) {
        recetas.remove(id);
    }
}