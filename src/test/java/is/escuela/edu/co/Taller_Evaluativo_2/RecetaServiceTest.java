package is.escuela.edu.co.Taller_Evaluativo_2;

import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Chef;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;
import is.escuela.edu.co.Taller_Evaluativo_2.repositorio.RecetaRepository;
import is.escuela.edu.co.Taller_Evaluativo_2.servicios.RecetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaServiceTest {

    @Mock
    private RecetaRepository recetaRepository;

    @InjectMocks
    private RecetaService recetaService;

    private Receta receta;
    private Chef chef;

    @BeforeEach
    void setUp() {
        chef = new Chef(1L, "Chef Test", TipoDeChef.PARTICIPANTE, "Temporada 1");
        receta = new Receta(1L, "Test Recipe", Arrays.asList("ing1", "ing2"), Arrays.asList("step1", "step2"), chef);
    }

    @Test
    void testCrearReceta() {
        when(recetaRepository.save(any(Receta.class))).thenReturn(receta);
        Receta resultado = recetaService.crearReceta(receta);
        assertNotNull(resultado);
        assertEquals("Test Recipe", resultado.getTitulo());
        verify(recetaRepository, times(1)).save(receta);
    }

    @Test
    void testObtenerTodasRecetas() {
        List<Receta> recetas = Arrays.asList(receta);
        when(recetaRepository.findAll()).thenReturn(recetas);
        List<Receta> resultado = recetaService.obtenerTodasRecetas();
        assertEquals(1, resultado.size());
        verify(recetaRepository, times(1)).findAll();
    }

    @Test
    void testObtenerRecetaPorIdExitoso() {
        when(recetaRepository.findById(1L)).thenReturn(Optional.of(receta));
        Receta resultado = recetaService.obtenerRecetaPorId(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(recetaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerRecetaPorIdNoEncontrada() {
        when(recetaRepository.findById(999L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> recetaService.obtenerRecetaPorId(999L));
        assertTrue(exception.getMessage().contains("no encontrada"));
        verify(recetaRepository, times(1)).findById(999L);
    }

    @Test
    void testObtenerRecetasPorTipoChef() {
        List<Receta> recetas = Arrays.asList(receta);
        when(recetaRepository.findByChefTipo(TipoDeChef.PARTICIPANTE)).thenReturn(recetas);
        List<Receta> resultado = recetaService.obtenerRecetasPorTipoChef(TipoDeChef.PARTICIPANTE);
        assertEquals(1, resultado.size());
        verify(recetaRepository, times(1)).findByChefTipo(TipoDeChef.PARTICIPANTE);
    }

    @Test
    void testObtenerRecetasPorTemporada() {
        List<Receta> recetas = Arrays.asList(receta);
        when(recetaRepository.findByChefTemporada("Temporada 1")).thenReturn(recetas);
        List<Receta> resultado = recetaService.obtenerRecetasPorTemporada("Temporada 1");
        assertEquals(1, resultado.size());
        verify(recetaRepository, times(1)).findByChefTemporada("Temporada 1");
    }

    @Test
    void testBuscarRecetasPorIngrediente() {
        List<Receta> recetas = Arrays.asList(receta);
        when(recetaRepository.findByIngredientesContaining("ing1")).thenReturn(recetas);
        List<Receta> resultado = recetaService.buscarRecetasPorIngrediente("ing1");
        assertEquals(1, resultado.size());
        verify(recetaRepository, times(1)).findByIngredientesContaining("ing1");
    }

    @Test
    void testEliminarReceta() {
        doNothing().when(recetaRepository).deleteById(1L);
        recetaService.eliminarReceta(1L);
        verify(recetaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testActualizarRecetaNoEncontrada() {
        Receta recetaActualizada = new Receta(999L, "Updated", Arrays.asList("ing"), Arrays.asList("step"), chef);
        when(recetaRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> recetaService.actualizarReceta(999L, recetaActualizada));
        verify(recetaRepository, never()).save(any(Receta.class));
    }


    @Test
    void testObtenerRecetasPorDiferentesTiposChef() {
        List<Receta> vacia = Arrays.asList();
        when(recetaRepository.findByChefTipo(TipoDeChef.TELEVIDENTE)).thenReturn(vacia);
        when(recetaRepository.findByChefTipo(TipoDeChef.CHEF)).thenReturn(vacia);
        assertEquals(0, recetaService.obtenerRecetasPorTipoChef(TipoDeChef.TELEVIDENTE).size());
        assertEquals(0, recetaService.obtenerRecetasPorTipoChef(TipoDeChef.CHEF).size());
        verify(recetaRepository, times(1)).findByChefTipo(TipoDeChef.TELEVIDENTE);
        verify(recetaRepository, times(1)).findByChefTipo(TipoDeChef.CHEF);
    }

    @Test
    void testBuscarRecetasPorIngredienteNoEncontrado() {
        List<Receta> vacia = Arrays.asList();
        when(recetaRepository.findByIngredientesContaining("inexistente")).thenReturn(vacia);
        List<Receta> resultado = recetaService.buscarRecetasPorIngrediente("inexistente");
        assertTrue(resultado.isEmpty());
        verify(recetaRepository, times(1)).findByIngredientesContaining("inexistente");
    }

    @Test
    void testObtenerRecetasPorTemporadaNoEncontrada() {
        List<Receta> vacia = Arrays.asList();
        when(recetaRepository.findByChefTemporada("Temporada X")).thenReturn(vacia);
        List<Receta> resultado = recetaService.obtenerRecetasPorTemporada("Temporada X");
        assertTrue(resultado.isEmpty());
        verify(recetaRepository, times(1)).findByChefTemporada("Temporada X");
    }

    @Test
    void testEliminarRecetaConIdInexistente() {
        doNothing().when(recetaRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> recetaService.eliminarReceta(999L));
        verify(recetaRepository, times(1)).deleteById(999L);
    }

}