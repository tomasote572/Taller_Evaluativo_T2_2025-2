package is.escuela.edu.co.Taller_Evaluativo_2.Controlador;

import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping("/Registrar receta de televidente")
    public Receta crearTelevidente(@RequestBody Receta receta) {
        return recetaService.guardar(receta);
    }

    @PostMapping("/Registrar receta de participante")
    public Receta crearParticipante(@RequestBody Receta receta) {
        return recetaService.guardar(receta);
    }

    @PostMapping("/Registrar receta de chef")
    public Receta crearChef(@RequestBody Receta receta) {
        return recetaService.guardar(receta);
    }

    @GetMapping
    public List<Receta> todaslasrecetas() {
        return recetaService.todaslasrecetas();
    }

    @GetMapping("/Receta por ID")
    public Receta porId(@PathVariable Long id) {
        return recetaService.porId(id);
    }

    @GetMapping("/Recetas de participantes")
    public List<Receta> deParticipantes() {
        return recetaService.porTipoChef(TipoDeChef.PARTICIPANTE);
    }

    @GetMapping("/Recetas de televidentes")
    public List<Receta> deTelevidentes() {
        return recetaService.porTipoChef(TipoDeChef.TELEVIDENTE);
    }

    @GetMapping("/Recetas de chefs")
    public List<Receta> deChefs() {
        return recetaService.porTipoChef(TipoDeChef.CHEF);
    }

    @GetMapping("/Recetas por temporada")
    public List<Receta> porTemporada(@PathVariable String temporada) {
        return recetaService.porTemporada(temporada);
    }

    @GetMapping("/Buscar por ingrediente")
    public List<Receta> porIngrediente(@RequestParam String ingrediente) {
        return recetaService.porIngrediente(ingrediente);
    }

    @DeleteMapping("/Eliminar receta")
    public void eliminar(@PathVariable Long id) {
        recetaService.eliminar(id);
    }

    @PutMapping("/Actualizar receta")
    public Receta actualizar(@PathVariable Long id, @RequestBody Receta receta) {
        return recetaService.actualizar(id, receta);
    }
}
