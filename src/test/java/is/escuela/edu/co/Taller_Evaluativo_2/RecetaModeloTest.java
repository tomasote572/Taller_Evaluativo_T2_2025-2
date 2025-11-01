package is.escuela.edu.co.Taller_Evaluativo_2;

import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Chef;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RecetaModeloTest {

    @Test
    void testCreacionReceta() {
        Chef chef = new Chef(1L, "Test Chef", TipoDeChef.TELEVIDENTE, null);
        Receta receta = new Receta(1L, "Test Recipe", Arrays.asList("ing1", "ing2"), Arrays.asList("step1", "step2"), chef);
        assertEquals(1L, receta.getId());
        assertEquals("Test Recipe", receta.getTitulo());
        assertEquals(2, receta.getIngredientes().size());
        assertEquals(chef, receta.getChef());
    }

    @Test
    void testSettersReceta() {
        Receta receta = new Receta();
        Chef chef = new Chef(1L, "Chef", TipoDeChef.PARTICIPANTE, "T1");
        receta.setId(1L);
        receta.setTitulo("Nuevo Titulo");
        receta.setIngredientes(Arrays.asList("a", "b"));
        receta.setPasos(Arrays.asList("1", "2"));
        receta.setChef(chef);
        assertEquals(1L, receta.getId());
        assertEquals("Nuevo Titulo", receta.getTitulo());
        assertEquals(2, receta.getIngredientes().size());
        assertEquals(chef, receta.getChef());
    }

    @Test
    void testCreacionChef() {
        Chef chef = new Chef(1L, "Nombre", TipoDeChef.CHEF, "Temporada5");
        assertEquals(1L, chef.getId());
        assertEquals("Nombre", chef.getNombre());
        assertEquals(TipoDeChef.CHEF, chef.getTipo());
        assertEquals("Temporada5", chef.getTemporada());
    }

    @Test
    void testSettersChef() {
        Chef chef = new Chef(1L, "Original", TipoDeChef.TELEVIDENTE, null);
        chef.setId(2L);
        chef.setNombre("Nuevo Nombre");
        chef.setTipo(TipoDeChef.PARTICIPANTE);
        chef.setTemporada("T10");
        assertEquals(2L, chef.getId());
        assertEquals("Nuevo Nombre", chef.getNombre());
        assertEquals(TipoDeChef.PARTICIPANTE, chef.getTipo());
        assertEquals("T10", chef.getTemporada());
    }

    @Test
    void testValoresEnumTipoDeChef() {
        assertEquals("TELEVIDENTE", TipoDeChef.TELEVIDENTE.name());
        assertEquals("PARTICIPANTE", TipoDeChef.PARTICIPANTE.name());
        assertEquals("CHEF", TipoDeChef.CHEF.name());
        assertEquals(3, TipoDeChef.values().length);
    }

    @Test
    void testRecetaConListasVacias() {
        Chef chef = new Chef(1L, "Chef", TipoDeChef.TELEVIDENTE, null);
        Receta receta = new Receta(1L, "Sin Ingredientes", Arrays.asList(), Arrays.asList(), chef);
        assertTrue(receta.getIngredientes().isEmpty());
        assertTrue(receta.getPasos().isEmpty());
    }

    @Test
    void testIgualdadRecetas() {
        Chef chef = new Chef(1L, "Mismo", TipoDeChef.TELEVIDENTE, null);
        Receta receta1 = new Receta(1L, "Igual", Arrays.asList("a"), Arrays.asList("1"), chef);
        Receta receta2 = new Receta(1L, "Igual", Arrays.asList("a"), Arrays.asList("1"), chef);
        assertEquals(receta1.getId(), receta2.getId());
        assertEquals(receta1.getTitulo(), receta2.getTitulo());
    }
}