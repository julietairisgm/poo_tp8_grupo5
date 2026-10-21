package ar.edu.unju.fi.poo;

import java.time.LocalDate;
import java.time.Year;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.poo.exception.ValidacionException;
import ar.edu.unju.fi.poo.model.*;

public class ManagerEmpleado {
	private List<Empleado> empleados;

    public ManagerEmpleado() {
        empleados = new ArrayList<>();
        // Profesionales
        Profesional p1 = new Profesional("0001-26", 12345678, "Ana Lopez",LocalDate.of(2020, 3, 10),2);
        p1.agregarTitulo(new Titulo(2022, "Ingeniería Informática", "Universitario"));
        Profesional p2 = new Profesional("0002-26", 23456789, "Carlos Herrera",LocalDate.of(2018, 5, 15),1);
        p2.agregarTitulo(new Titulo(2020, "Licenciatura en Sistemas", "Universitario"));
        p2.agregarTitulo(new Titulo(2023, "Programación", "Terciario"));

        // Administrativos
        Administrativo a1 = new Administrativo("0003-26", 34567890, "Laura Gomez",LocalDate.of(2021, 2, 20),3,"A");
        Administrativo a2 = new Administrativo("0004-26", 45678901, "Pedro Sanchez",LocalDate.of(2019, 7, 1),0,"B");
        Administrativo a3 = new Administrativo("0005-26", 34567860, "Susana Gomez",LocalDate.of(2022, 3, 10),1,"A");

        // Limpieza
        Limpieza l1 = new Limpieza("0006-26", 56789012, "Marta Diaz",LocalDate.of(2022, 1, 10),2);
        Limpieza l2 = new Limpieza("0007-26", 67890123, "Juan Perez",LocalDate.of(2017, 11, 5), 1);

        // Agregar empleados a la lista
        empleados.add(p1);
        empleados.add(p2);
        empleados.add(a1);
        empleados.add(a2);
        empleados.add(a3);
        empleados.add(l1);
        empleados.add(l2);
    }
    
    public boolean agregarEmpleado(Empleado empleado) {
    	validarLegajo(empleado.getLegajo());
    	validarDocumento(empleado.getDocumento());
    	
        if (buscarLegajo(empleado.getLegajo()) != null) {
            return false;
        }
        empleados.add(empleado);
        return true;
    }
    
    public Empleado buscarLegajo(String legajo) {
        for (Empleado empleado : empleados) {
        	if (empleado.getLegajo().equals(legajo)) {
                return empleado;
            }
        }
        return null;
    }
    
    //Metodo para validar DNI
    private void validarDocumento(int documento) {
        for (Empleado empleado : empleados) {
            if (empleado.getDocumento() == documento) {
                throw new ValidacionException(
                    "Ya existe un empleado con ese documento."
                );
            }
        }
    }
    
    // Metodo para validar el legajo
    private void validarLegajo(String legajo) {
        validarFormatoLegajo(legajo);
        validarAnioLegajo(legajo);
    }
    
    //Metodo auxiliar para validar que el legajo cumpla el formato esperado
    private void validarFormatoLegajo(String legajo) {
        if (legajo == null || !legajo.matches("\\d{4}-\\d{2}")) {
            throw new ValidacionException(
                "El legajo debe tener el formato NNNN-AA."
            );
        }
    }
    
    // Metodo auxiliar que valida que los ultimos 2 digitos sean del anio actual
    private void validarAnioLegajo(String legajo) {
    	String anioActual = obtenerAnioActual();

        if (!legajo.endsWith("-" + anioActual)) {
            throw new ValidacionException(
                "Los dos últimos dígitos del legajo deben coincidir con el año actual."
            );
        }
    }
    
    public String obtenerNuevoLegajo() {
        return generarLegajo();
    }
    
    // Metodo para generar legajos
    private String generarLegajo() {
        String anioActual = obtenerAnioActual();
        String legajo;

        do {
            legajo = generarNumeroLegajo() + "-" + anioActual;
        } while (buscarLegajo(legajo) != null);

        return legajo;
    }
    // Metodo auxiliar para generar numero de legajo aleatorio
    private String generarNumeroLegajo() {
        Random random = new Random();
        int numero = random.nextInt(10000);

        return String.format("%04d", numero);
    }

    // Metodo auxiliar para obtener el anio actual
    private String obtenerAnioActual() {
        return String.valueOf(Year.now().getValue()).substring(2);
    }
    
    public List<Administrativo> obtenerAdministrativosPorCategoria(String categoria) {
        List<Administrativo> resultado = new ArrayList<>();

        for (Empleado empleado : empleados) {
            if (empleado instanceof Administrativo) {
                Administrativo administrativo = (Administrativo) empleado;
                if (administrativo.getCategoria().equalsIgnoreCase(categoria)) {
                    resultado.add(administrativo);
                }
            }
        }
        return resultado;
    }
    
    public double calcularNetoAcumulado(String tipo) {
        double total = 0;

        for (Empleado empleado : empleados) {
            if (esTipoEmpleado(empleado, tipo)) {
                total += empleado.calcularSueldoNeto();
            }
        }
        return total;
    }

    //Metodo auxiliar
    private boolean esTipoEmpleado(Empleado empleado, String tipo) {
        if (tipo.equalsIgnoreCase("profesional")) {
            return empleado instanceof Profesional;
        }
        if (tipo.equalsIgnoreCase("administrativo")) {
            return empleado instanceof Administrativo;
        }
        if (tipo.equalsIgnoreCase("limpieza")) {
            return empleado instanceof Limpieza;
        }
        return false;
    }
    
    //Getters and Setters
    public List<Empleado> getEmpleados() {return empleados;}
    public void setEmpleados(List<Empleado> empleados) {this.empleados = empleados;}
}