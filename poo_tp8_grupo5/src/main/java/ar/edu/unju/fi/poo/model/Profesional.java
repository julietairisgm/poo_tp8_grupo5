package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Profesional extends Empleado {
    private List<Titulo> titulos;

    public Profesional(int legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
        super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
        this.titulos = new ArrayList<>();
    }

    public void agregarTitulo(Titulo titulo) {
        this.titulos.add(titulo);
    }

    @Override
    public double calcularAdicional() {
        return this.titulos.size() * 30000.0;
    }

    public List<Titulo> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<Titulo> titulos) {
        this.titulos = titulos;
    }
}