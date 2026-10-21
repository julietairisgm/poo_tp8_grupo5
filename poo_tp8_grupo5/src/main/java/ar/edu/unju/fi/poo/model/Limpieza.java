package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;

public class Limpieza extends Empleado {

    public Limpieza(int legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
        super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
    }

    @Override
    public double calcularAdicional() {
        return 25000.0;
    }
}