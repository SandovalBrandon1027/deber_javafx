package com.example.deber_javafx;

public class users {
    private int id;
    private String Nombre;
    private String Cedula;

    public users(int id, String nombre, String cedula) {
        this.id = id;
        Nombre = nombre;
        Cedula = cedula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }
}
