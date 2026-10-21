package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Empleado {
    protected String legajo;
    protected int documento;
    protected String nombre;
    protected LocalDate fechaIngreso;
    protected int cantidadHijos;
    protected double sueldoBasico = 400000.0; 

    // Constructor
    public Empleado(String legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
        this.legajo = legajo;
        this.documento = documento;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.cantidadHijos = cantidadHijos;
    }

    public abstract double calcularAdicional();

    public double calcularSalarioFamiliar() {
        return this.cantidadHijos * 15000.0;
    }

    public double calcularAntiguedad() {
        if (fechaIngreso == null) {
            return 0.0;
        }
        int anios = Period.between(fechaIngreso, LocalDate.now()).getYears();
        return anios * 6500.0;
    }

    public double calcularRemunerativoBonificable() {
        return this.sueldoBasico + this.calcularAdicional() + this.calcularAntiguedad();
    }

    public double calcularDescuentos() {
        return this.calcularRemunerativoBonificable() * 0.18;
    }

    public double calcularSueldoNeto() {
        return this.calcularRemunerativoBonificable() + this.calcularSalarioFamiliar() - this.calcularDescuentos();
    }

    // Getters 
    public String getLegajo() {
        return legajo;
    }

    public int getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public int getCantidadHijos() {
        return cantidadHijos;
    }
}