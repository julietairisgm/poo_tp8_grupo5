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
import ar.edu.unju.fi.poo.model.Profesional;
import ar.edu.unju.fi.poo.model.Titulo;

@DisplayName("Pruebas unitarias para ManagerEmpleado")
class ManagerEmpleadoTest {

    private ManagerEmpleado managerEmpleado;
    private Profesional emp1;
    private Administrativo emp2;
    private Limpieza emp3;
    private Profesional emp4;
    private Administrativo emp5;
    private Titulo titulo1;
    private Titulo titulo2;

    @BeforeEach
    void setUp() throws ValidacionException {
        managerEmpleado = new ManagerEmpleado();
        if (managerEmpleado.getEmpleados() != null) {
            managerEmpleado.getEmpleados().clear();
        }

        titulo1 = new Titulo(2020, "Ingenieria Informatica", "Universitario");
        titulo2 = new Titulo(2021, "Tecnicatura en Redes", "Terciario");

        emp1 = new Profesional("1001-26", 99900001, "Juan Perez", LocalDate.of(2020, 1, 15), 2);
        emp1.agregarTitulo(titulo1);

        emp2 = new Administrativo("1002-26", 99900002, "Maria Lopez", LocalDate.of(2021, 5, 20), 1, "A");

        emp3 = new Limpieza("1003-26", 99900003, "Carlos Gomez", LocalDate.of(2019, 3, 10), 0);

        emp4 = new Profesional("1004-26", 99900004, "Ana Rodriguez", LocalDate.of(2022, 8, 12), 3);
        emp4.agregarTitulo(titulo2);

        emp5 = new Administrativo("1005-26", 99900005, "Pedro Sanchez", LocalDate.of(2018, 11, 5), 4, "B");

        managerEmpleado.agregarEmpleado(emp1);
        managerEmpleado.agregarEmpleado(emp2);
        managerEmpleado.agregarEmpleado(emp3);
        managerEmpleado.agregarEmpleado(emp4);
        managerEmpleado.agregarEmpleado(emp5);
    }

    @Test
    @DisplayName("Verificar cantidad total de empleados agregados")
    void testCantidadEmpleados() {
        assertEquals(5, managerEmpleado.getEmpleados().size());
    }

    @Test
    @DisplayName("Validar que un empleado existe por legajo")
    void testBuscarEmpleadoExistente() {
        Empleado emp = managerEmpleado.getEmpleados().stream()
                .filter(e -> e.getLegajo().equals("1001-26"))
                .findFirst()
                .orElse(null);

        assertTrue(emp != null && emp.getDocumento() == 99900001);
    }

    @RepeatedTest(3)
    @DisplayName("Repetir la verificacion de la lista no vacia")
    void testListaNoVaciaRepetida() {
        assertTrue(managerEmpleado.getEmpleados().size() >= 5);
    }
}

@Test
    @DisplayName("Buscar legajo inexistente")
    void testBuscarLegajoInexistenteRetornaNull() {
        Empleado emp = managerEmpleado.getEmpleados().stream()
                .filter(e -> e.getLegajo().equals("9999-26"))
                .findFirst()
                .orElse(null);

        assertNull(emp, "Debe retornar null cuando el legajo buscado no existe.");
    }

    @Test
    @DisplayName("Fecha de ingreso futura")
    void testFechaIngresoFuturaLanzaExcepcion() {
        LocalDate fechaFutura = LocalDate.now().plusDays(2);

        assertThrows(ValidacionException.class, () -> {
            new Limpieza("1006-26", 99900006, "Empleado Invalido", fechaFutura, 1);
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
