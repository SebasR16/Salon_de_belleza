package com.example.salondebelleza;

public class mLista {
    private String nombre;
    private String fecha;
    private String hora;
    private String dinero;

    public mLista(String nombre,String fecha,String hora){
        this.nombre=nombre;
        this.fecha = fecha;
        this.hora = hora;
    }
    public mLista(String nombre,String fecha,String hora,String dinero){
        this.nombre=nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.dinero =dinero;
    }
    public String getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHora() {
        return hora;
    }
    public String getDinero() {
        return dinero;
    }
}
