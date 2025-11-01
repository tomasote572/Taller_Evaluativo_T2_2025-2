package is.escuela.edu.co.Taller_Evaluativo_2.servicios;

import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;
import is.escuela.edu.co.Taller_Evaluativo_2.repositorio.RecetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public Receta crearReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public List<Receta> obtenerTodasRecetas() {
        return recetaRepository.findAll();
    }

    public Receta obtenerRecetaPorId(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con id: " + id));
    }

    public List<Receta> obtenerRecetasPorTipoChef(TipoDeChef tipo) {
        return recetaRepository.findByChefTipo(tipo);
    }

    public List<Receta> obtenerRecetasPorTemporada(String temporada) {
        return recetaRepository.findByChefTemporada(temporada);
    }

    public List<Receta> buscarRecetasPorIngrediente(String ingrediente) {
        return recetaRepository.findByIngredientesContaining(ingrediente);
    }

    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }

    public Receta actualizarReceta(Long id, Receta recetaActualizada) {
        obtenerRecetaPorId(id);
        recetaActualizada.setId(id);
        if (!recetaRepository.existsById(id)) {
            throw new RuntimeException("Receta no encontrada con id: " + id);
        }
        recetaActualizada.setId(id);
        return recetaRepository.save(recetaActualizada);
    }
}