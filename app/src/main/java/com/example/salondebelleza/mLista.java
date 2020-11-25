package com.example.salondebelleza;

public class mLista {
    private String nombre;
    private String fecha;
    private String hora;
    private String dinero;

    public String getTipoPago() {
        return tipoPago;
    }

    private  String tipoPago;
    public String getTelefono() {
        return telefono;
    }

    private  String telefono;
    private  int id;
    public mLista( int id, String nombre,String fecha,String hora, String telefono){
        this.nombre=nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.id = id;
        this.telefono=telefono;
    }
    public mLista(int id, String nombre,String fecha,String hora, String telefono,String dinero,String tipoPago){
        this.nombre=nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.dinero =dinero;
        this.id = id;
        this.telefono=telefono;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
