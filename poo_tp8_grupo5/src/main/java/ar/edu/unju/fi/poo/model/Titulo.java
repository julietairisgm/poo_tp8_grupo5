package ar.edu.unju.fi.poo.model;

public class Titulo {
    private int anio;
    private String nombreCarrera;
    private String nivel; 

    // Constructor
    public Titulo(int anio, String nombreCarrera, String nivel) {
        this.anio = anio;
        this.nombreCarrera = nombreCarrera;
        this.nivel = nivel;
    }

    // Getters y Setters
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}