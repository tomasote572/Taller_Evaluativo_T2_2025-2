package is.escuela.edu.co.Taller_Evaluativo_2.repositorio;

import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends MongoRepository<Receta, Long> {
    List<Receta> findByChefTipo(TipoDeChef tipo);
    List<Receta> findByChefTemporada(String temporada);
    List<Receta> findByIngredientesContaining(String ingrediente);
}