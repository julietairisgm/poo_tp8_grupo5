package ar.edu.unju.fi.poo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.poo.exception.ValidacionException;
import ar.edu.unju.fi.poo.model.*;

public class ManagerEmpleado {
	private List<Empleado> empleados;

    public ManagerEmpleado() {
        empleados = new ArrayList<>();
        // Profesionales
        Profesional p1 = new Profesional("1", 12345678, "Ana Lopez",LocalDate.of(2020, 3, 10),2);
        p1.agregarTitulo(new Titulo(2022, "Ingeniería Informática", "Universitario"));
        Profesional p2 = new Profesional("2", 23456789, "Carlos Herrera",LocalDate.of(2018, 5, 15),1);
        p2.agregarTitulo(new Titulo(2020, "Licenciatura en Sistemas", "Universitario"));
        p2.agregarTitulo(new Titulo(2023, "Programación", "Terciario"));

        // Administrativos
        Administrativo a1 = new Administrativo("3", 34567890, "Laura Gomez",LocalDate.of(2021, 2, 20),3,"A");
        Administrativo a2 = new Administrativo("4", 45678901, "Pedro Sanchez",LocalDate.of(2019, 7, 1),0,"B");
        Administrativo a3 = new Administrativo("5", 34567860, "Susana Gomez",LocalDate.of(2022, 3, 10),1,"A");

        // Limpieza
        Limpieza l1 = new Limpieza("6", 56789012, "Marta Diaz",LocalDate.of(2022, 1, 10),2);
        Limpieza l2 = new Limpieza("7", 67890123, "Juan Perez",LocalDate.of(2017, 11, 5), 1);

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
    
    private void validarDocumento(int documento) {
        for (Empleado empleado : empleados) {
            if (empleado.getDocumento() == documento) {
                throw new ValidacionException(
                    "Ya existe un empleado con ese documento."
                );
            }
        }
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