/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examen;

/**
 *
 * @author DAW
 */
public class Actrices extends Autoras {

    public Actrices(int id, String nombre, String alias, String apellidos, String fecha_nacimiento, int premios_recibidos, String pais_residencia, String area_trabajo) {
        super(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
    }

    public static void interpretar() {

        System.out.println("Estoy interpretando");

    }

    @Override
    public String toString() {
        interpretar();
        return "Autoras{" + "id=" + id + ", nombre=" + nombre + ", alias=" + alias + ", apellidos=" + apellidos + ", fecha_nacimiento=" + fecha_nacimiento + ", premios_recibidos=" + premios_recibidos + ", pais_residencia=" + pais_residencia + ", area_trabajo=" + area_trabajo + '}';
    }

}