package is.escuela.edu.co.Taller_Evaluativo_2.Controlador;

import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;
import is.escuela.edu.co.Taller_Evaluativo_2.servicios.RecetaService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @Operation(summary = "Crear una receta de Televidente")
    @PostMapping("/televidente")
    public Receta crearTelevidente(@RequestBody Receta receta) {
        receta.getChef().setTipo(TipoDeChef.TELEVIDENTE);
        return recetaService.crearReceta(receta);
    }

    @Operation(summary = "Crear una receta de Participante")
    @PostMapping("/participante")
    public Receta crearParticipante(@RequestBody Receta receta) {
        receta.getChef().setTipo(TipoDeChef.PARTICIPANTE);
        return recetaService.crearReceta(receta);
    }

    @Operation(summary = "Crear una receta de Chef Profesional")
    @PostMapping("/chef")
    public Receta crearChef(@RequestBody Receta receta) {
        receta.getChef().setTipo(TipoDeChef.CHEF);
        return recetaService.crearReceta(receta);
    }

    @Operation(summary = "Obtener todas las recetas")
    @GetMapping
    public List<Receta> obtenerTodasRecetas() {
        return recetaService.obtenerTodasRecetas();
    }

    @Operation(summary = "Obtener receta por ID")
    @GetMapping("/{id}")
    public Receta obtenerRecetaPorId(@PathVariable Long id) {
        return recetaService.obtenerRecetaPorId(id);
    }

    @Operation(summary = "Obtener recetas de Participantes")
    @GetMapping("/participantes")
    public List<Receta> obtenerRecetasParticipantes() {
        return recetaService.obtenerRecetasPorTipoChef(TipoDeChef.PARTICIPANTE);
    }

    @Operation(summary = "Obtener recetas de Televidentes")
    @GetMapping("/televidentes")
    public List<Receta> obtenerRecetasTelevidentes() {
        return recetaService.obtenerRecetasPorTipoChef(TipoDeChef.TELEVIDENTE);
    }

    @Operation(summary = "Obtener recetas de Chefs Profesionales")
    @GetMapping("/chefs")
    public List<Receta> obtenerRecetasChefs() {
        return recetaService.obtenerRecetasPorTipoChef(TipoDeChef.CHEF);
    }

    @Operation(summary = "Obtener recetas por temporada")
    @GetMapping("/temporada")
    public List<Receta> obtenerRecetasPorTemporada(@RequestParam String temporada) {
        return recetaService.obtenerRecetasPorTemporada(temporada);
    }

    @Operation(summary = "Buscar recetas por ingrediente")
    @GetMapping("/buscar")
    public List<Receta> buscarRecetasPorIngrediente(@RequestParam String ingrediente) {
        return recetaService.buscarRecetasPorIngrediente(ingrediente);
    }

    @Operation(summary = "Eliminar receta por ID")
    @DeleteMapping("/{id}")
    public void eliminarReceta(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
    }

    @Operation(summary = "Actualizar una receta por ID")
    @PutMapping("/{id}")
    public Receta actualizarReceta(@PathVariable Long id, @RequestBody Receta receta) {
        return recetaService.actualizarReceta(id, receta);
    }
}
