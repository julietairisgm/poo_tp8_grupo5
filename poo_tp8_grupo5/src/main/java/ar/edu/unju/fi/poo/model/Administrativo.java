package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;

public class Administrativo extends Empleado {
    private String categoria; 

    public Administrativo(int legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos, String categoria) {
        super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
        this.categoria = categoria.toUpperCase();
    }

    @Override
    public double calcularAdicional() {
        switch (this.categoria) {
            case "A":
                return 30000.0;
            case "B":
                return 45000.0;
            case "C":
                return 55000.0;
            default:
                return 0.0;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria != null ? categoria.toUpperCase() : "";
    }
}