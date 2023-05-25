/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examen;

/**
 *
 * @author DAW
 */
public abstract class Autoras {

    protected int id;
    protected String nombre;
    protected String alias;
    protected String apellidos;
    protected String fecha_nacimiento;
    protected int premios_recibidos;
    protected String pais_residencia;
    protected String area_trabajo;

    public Autoras(int id, String nombre, String alias, String apellidos, String fecha_nacimiento, int premios_recibidos, String pais_residencia, String area_trabajo) {
        this.id = id;
        this.nombre = nombre;
        this.alias = alias;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.premios_recibidos = premios_recibidos;
        this.pais_residencia = pais_residencia;
        this.area_trabajo = area_trabajo;
    }

    @Override
    public String toString() {
        return "Autoras{" + "id=" + id + ", nombre=" + nombre + ", alias=" + alias + ", apellidos=" + apellidos + ", fecha_nacimiento=" + fecha_nacimiento + ", premios_recibidos=" + premios_recibidos + ", pais_residencia=" + pais_residencia + ", area_trabajo=" + area_trabajo + '}';
    }

}
