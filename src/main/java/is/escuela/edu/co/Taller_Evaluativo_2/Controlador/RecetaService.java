package is.escuela.edu.co.Taller_Evaluativo_2.Controlador;

import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;

import java.util.List;

public interface RecetaService {
    Receta guardar(Receta receta);
    List<Receta> todaslasrecetas();
    Receta porId(Long id);
    List<Receta> porTipoChef(TipoDeChef tipo);
    List<Receta> porTemporada(String temporada);
    List<Receta> porIngrediente(String ingrediente);
    void eliminar(Long id);
    Receta actualizar(Long id, Receta receta);
}
