package com.example.app01sqlite.tablas;

public class Personas {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String edad;
    private String correo;
    private String direccion;


    public Personas(Integer id, String nombres, String apellidos, String edad, String correo, String direccion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Personas() {

    }

    //metodos get y set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
