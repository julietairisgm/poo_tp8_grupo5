package ar.edu.unju.fi.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ar.edu.unju.fi.poo.exception.ValidacionException;
import ar.edu.unju.fi.poo.model.Administrativo;
import ar.edu.unju.fi.poo.model.Empleado;
import ar.edu.unju.fi.poo.model.Limpieza;

@DisplayName("Pruebas unitarias para ManagerEmpleado")
class ManagerEmpleadoTest {

    private ManagerEmpleado managerEmpleado;

    @BeforeEach
    void setUp() throws ValidacionException {
        managerEmpleado = new ManagerEmpleado();
    }

    @Test
    @DisplayName("Verificar cantidad total de empleados agregados")
    void testCantidadEmpleados() {
        assertEquals(7, managerEmpleado.getEmpleados().size());
    }

    @Test
    @DisplayName("Validar que un empleado existe por legajo")
    void testBuscarEmpleadoExistente() {
        Empleado emp = managerEmpleado.buscarLegajo("0001-26");
        assertTrue(emp != null && emp.getDocumento() == 12345678);
    }

    @RepeatedTest(3)
    @DisplayName("Repetir la verificacion de la lista no vacia")
    void testListaNoVaciaRepetida() {
        assertTrue(managerEmpleado.getEmpleados().size() >= 7);
    }

    @Test
    @DisplayName("Buscar legajo inexistente")
    void testBuscarLegajoInexistenteRetornaNull() {
        Empleado emp = managerEmpleado.buscarLegajo("9999-26");
        assertNull(emp, "Debe retornar null cuando el legajo buscado no existe.");
    }

    @Test
    @DisplayName("Fecha de ingreso futura")
    void testFechaIngresoFuturaLanzaExcepcion() {
        LocalDate fechaFutura = LocalDate.now().plusDays(2);
        assertThrows(ValidacionException.class, () -> {
            Empleado empInvalido = new Limpieza("1006-26", 99900006, "Empleado Invalido", fechaFutura, 1);
            managerEmpleado.agregarEmpleado(empInvalido);
        }, "Debe lanzar ValidacionException si la fecha de ingreso es mayor a la del sistema.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 6, 10})
    @DisplayName("Cantidad de hijos invalida")
    void testCantidadHijosInvalidaLanzaExcepcion(int cantidadHijosInvalida) {
        assertThrows(ValidacionException.class, () -> {
            new Administrativo("1007-26", 99900007, "Prueba Hijos", LocalDate.now(), cantidadHijosInvalida, "A");
        }, "Debe lanzar ValidacionException si la cantidad de hijos no esta entre 0 y 5.");
    }

    @Disabled("Prueba deshabilitada explícitamente para cumplir el requisito de la etiqueta @Disabled")
    @Test
    @DisplayName("Uso de la etiqueta @Disabled")
    void testPruebaDeshabilitada() {
        fail("Este test esta marcado con @Disabled y no debe ejecutarse.");
    }
}