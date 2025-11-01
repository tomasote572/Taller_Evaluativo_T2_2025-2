package is.escuela.edu.co.Taller_Evaluativo_2;

import is.escuela.edu.co.Taller_Evaluativo_2.Controlador.RecetaController;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Chef;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.Receta;
import is.escuela.edu.co.Taller_Evaluativo_2.modelo.TipoDeChef;
import is.escuela.edu.co.Taller_Evaluativo_2.servicios.RecetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Pruebas del RecetaController")
class RecetaControllerTest {

    @Mock
    private RecetaService recetaService;

    @InjectMocks
    private RecetaController recetaController;

    private Receta recetaMock;
    private Chef chefMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        chefMock = new Chef(1L, "Gordon Ramsay", TipoDeChef.CHEF, "Invierno");
        recetaMock = new Receta(
                1L,
                "Pasta Carbonara",
                Arrays.asList("Pasta", "Huevo", "Tocino"),
                Arrays.asList("Cocinar pasta", "Mezclar ingredientes"),
                chefMock
        );
    }

    @Test
    @DisplayName("Debe crear una receta de televidente exitosamente")
    void testCrearTelevidente() {
        when(recetaService.crearReceta(any(Receta.class))).thenReturn(recetaMock);

        Receta resultado = recetaController.crearTelevidente(recetaMock);

        assertNotNull(resultado);
        assertEquals("Pasta Carbonara", resultado.getTitulo());
        assertEquals(1L, resultado.getId());
        verify(recetaService, times(1)).crearReceta(any(Receta.class));
    }

    @Test
    @DisplayName("Debe crear una receta de participante exitosamente")
    void testCrearParticipante() {
        when(recetaService.crearReceta(any(Receta.class))).thenReturn(recetaMock);

        Receta resultado = recetaController.crearParticipante(recetaMock);

        assertNotNull(resultado);
        assertEquals("Pasta Carbonara", resultado.getTitulo());
        verify(recetaService, times(1)).crearReceta(any(Receta.class));
    }

    @Test
    @DisplayName("Debe crear una receta de chef exitosamente")
    void testCrearChef() {
        when(recetaService.crearReceta(any(Receta.class))).thenReturn(recetaMock);

        Receta resultado = recetaController.crearChef(recetaMock);

        assertNotNull(resultado);
        assertEquals("Pasta Carbonara", resultado.getTitulo());
        verify(recetaService, times(1)).crearReceta(any(Receta.class));
    }

    @Test
    @DisplayName("Debe obtener todas las recetas")
    void testObtenerTodasRecetas() {
        Chef chef2 = new Chef(2L, "Julia Child", TipoDeChef.TELEVIDENTE, "Verano");
        Receta receta2 = new Receta(
                2L,
                "Beef Bourguignon",
                Arrays.asList("Carne", "Vino"),
                Arrays.asList("Dorar carne", "Cocinar lentamente"),
                chef2
        );

        List<Receta> recetas = Arrays.asList(recetaMock, receta2);
        when(recetaService.obtenerTodasRecetas()).thenReturn(recetas);

        List<Receta> resultado = recetaController.obtenerTodasRecetas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Pasta Carbonara", resultado.get(0).getTitulo());
        assertEquals("Beef Bourguignon", resultado.get(1).getTitulo());
        verify(recetaService, times(1)).obtenerTodasRecetas();
    }

    @Test
    @DisplayName("Debe obtener lista vacía cuando no hay recetas")
    void testObtenerTodasRecetasVacio() {
        when(recetaService.obtenerTodasRecetas()).thenReturn(Arrays.asList());

        List<Receta> resultado = recetaController.obtenerTodasRecetas();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(recetaService, times(1)).obtenerTodasRecetas();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando receta no existe")
    void testObtenerRecetaPorIdNoExiste() {
        when(recetaService.obtenerRecetaPorId(999L))
                .thenThrow(new RuntimeException("Receta no encontrada con id: 999"));

        assertThrows(RuntimeException.class, () -> recetaController.obtenerRecetaPorId(999L));
        verify(recetaService, times(1)).obtenerRecetaPorId(999L);
    }

    @Test
    @DisplayName("Debe obtener recetas de participantes")
    void testObtenerRecetasParticipantes() {
        Chef chefParticipante = new Chef(2L, "Juan", TipoDeChef.PARTICIPANTE, "Primavera");
        Receta recetaParticipante = new Receta(
                2L,
                "Receta Participante",
                Arrays.asList("Ingrediente1"),
                Arrays.asList("Paso1"),
                chefParticipante
        );

        when(recetaService.obtenerRecetasPorTipoChef(TipoDeChef.PARTICIPANTE))
                .thenReturn(Arrays.asList(recetaParticipante));

        List<Receta> resultado = recetaController.obtenerRecetasParticipantes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(TipoDeChef.PARTICIPANTE, resultado.get(0).getChef().getTipo());
        verify(recetaService, times(1)).obtenerRecetasPorTipoChef(TipoDeChef.PARTICIPANTE);
    }

    @Test
    @DisplayName("Debe obtener recetas de televidentes")
    void testObtenerRecetasTelevidentes() {
        Chef chefTelevidiente = new Chef(3L, "Pedro", TipoDeChef.TELEVIDENTE, "Otoño");
        Receta recetaTelevidiente = new Receta(
                3L,
                "Receta Televidente",
                Arrays.asList("Ingrediente2"),
                Arrays.asList("Paso2"),
                chefTelevidiente
        );

        when(recetaService.obtenerRecetasPorTipoChef(TipoDeChef.TELEVIDENTE))
                .thenReturn(Arrays.asList(recetaTelevidiente));

        List<Receta> resultado = recetaController.obtenerRecetasTelevidentes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(TipoDeChef.TELEVIDENTE, resultado.get(0).getChef().getTipo());
        verify(recetaService, times(1)).obtenerRecetasPorTipoChef(TipoDeChef.TELEVIDENTE);
    }

    @Test
    @DisplayName("Debe obtener recetas de chefs")
    void testObtenerRecetasChefs() {
        when(recetaService.obtenerRecetasPorTipoChef(TipoDeChef.CHEF))
                .thenReturn(Arrays.asList(recetaMock));

        List<Receta> resultado = recetaController.obtenerRecetasChefs();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(TipoDeChef.CHEF, resultado.get(0).getChef().getTipo());
        verify(recetaService, times(1)).obtenerRecetasPorTipoChef(TipoDeChef.CHEF);
    }

    @Test
    @DisplayName("Debe obtener recetas por temporada")
    void testObtenerRecetasPorTemporada() {
        when(recetaService.obtenerRecetasPorTemporada("Invierno"))
                .thenReturn(Arrays.asList(recetaMock));

        List<Receta> resultado = recetaController.obtenerRecetasPorTemporada("Invierno");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Invierno", resultado.get(0).getChef().getTemporada());
        verify(recetaService, times(1)).obtenerRecetasPorTemporada("Invierno");
    }

    @Test
    @DisplayName("Debe buscar recetas por ingrediente")
    void testBuscarRecetasPorIngrediente() {
        when(recetaService.buscarRecetasPorIngrediente("Pasta"))
                .thenReturn(Arrays.asList(recetaMock));

        List<Receta> resultado = recetaController.buscarRecetasPorIngrediente("Pasta");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getIngredientes().contains("Pasta"));
        verify(recetaService, times(1)).buscarRecetasPorIngrediente("Pasta");
    }

    @Test
    @DisplayName("Debe buscar ingrediente que no existe")
    void testBuscarRecetasPorIngredienteNoExiste() {
        when(recetaService.buscarRecetasPorIngrediente("Chocolate"))
                .thenReturn(Arrays.asList());

        List<Receta> resultado = recetaController.buscarRecetasPorIngrediente("Chocolate");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(recetaService, times(1)).buscarRecetasPorIngrediente("Chocolate");
    }

    @Test
    @DisplayName("Debe eliminar receta exitosamente")
    void testEliminarReceta() {
        doNothing().when(recetaService).eliminarReceta(1L);

        assertDoesNotThrow(() -> recetaController.eliminarReceta(1L));

        verify(recetaService, times(1)).eliminarReceta(1L);
    }

    @Test
    @DisplayName("Debe eliminar receta inexistente sin error")
    void testEliminarRecetaInexistente() {
        doNothing().when(recetaService).eliminarReceta(999L);

        assertDoesNotThrow(() -> recetaController.eliminarReceta(999L));

        verify(recetaService, times(1)).eliminarReceta(999L);
    }

    @Test
    @DisplayName("Debe actualizar receta exitosamente")
    void testActualizarReceta() {
        Receta recetaActualizada = new Receta(
                1L,
                "Pasta Alfredo",
                Arrays.asList("Pasta", "Crema", "Queso"),
                Arrays.asList("Cocinar pasta", "Hacer salsa"),
                chefMock
        );

        when(recetaService.actualizarReceta(1L, recetaActualizada))
                .thenReturn(recetaActualizada);

        Receta resultado = recetaController.actualizarReceta(1L, recetaActualizada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Pasta Alfredo", resultado.getTitulo());
        verify(recetaService, times(1)).actualizarReceta(1L, recetaActualizada);
    }

    @Test
    @DisplayName("Debe actualizar receta con cambios en ingredientes")
    void testActualizarRecetaIngredientes() {
        Receta recetaConCambios = new Receta(
                1L,
                "Pasta Carbonara Mejorada",
                Arrays.asList("Pasta", "Huevo", "Tocino", "Parmesano"),
                Arrays.asList("Cocinar pasta", "Mezclar ingredientes"),
                chefMock
        );

        when(recetaService.actualizarReceta(1L, recetaConCambios))
                .thenReturn(recetaConCambios);

        Receta resultado = recetaController.actualizarReceta(1L, recetaConCambios);

        assertEquals(4, resultado.getIngredientes().size());
        verify(recetaService, times(1)).actualizarReceta(1L, recetaConCambios);
    }

    @Test
    @DisplayName("Debe validar que receta creada tenga ID")
    void testRecetaCreadaTieneId() {
        when(recetaService.crearReceta(any(Receta.class))).thenReturn(recetaMock);

        Receta resultado = recetaController.crearChef(recetaMock);

        assertNotNull(resultado.getId());
        assertTrue(resultado.getId() > 0);
    }

    @Test
    @DisplayName("Debe validar que receta tenga chef asociado")
    void testRecetaTieneChefAsociado() {
        when(recetaService.crearReceta(any(Receta.class))).thenReturn(recetaMock);

        Receta resultado = recetaController.crearChef(recetaMock);

        assertNotNull(resultado.getChef());
        assertNotNull(resultado.getChef().getNombre());
        assertNotNull(resultado.getChef().getTipo());
    }

    @Test
    @DisplayName("Debe validar que receta tenga ingredientes")
    void testRecetaTieneIngredientes() {
        when(recetaService.crearReceta(any(Receta.class))).thenReturn(recetaMock);

        Receta resultado = recetaController.crearChef(recetaMock);

        assertNotNull(resultado.getIngredientes());
        assertFalse(resultado.getIngredientes().isEmpty());
        assertTrue(resultado.getIngredientes().size() > 0);
    }

    @Test
    @DisplayName("Debe validar que receta tenga pasos")
    void testRecetaTienePasos() {
        when(recetaService.crearReceta(any(Receta.class))).thenReturn(recetaMock);

        Receta resultado = recetaController.crearChef(recetaMock);

        assertNotNull(resultado.getPasos());
        assertFalse(resultado.getPasos().isEmpty());
    }
}