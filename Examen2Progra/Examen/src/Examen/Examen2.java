/*
 * EJERCICIO 1 DEL EXAMEN
 */
package Examen;

import java.io.File;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DAW
 */
public class Examen2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner lector = new Scanner(System.in);
        String nombre_base = "Autoras";//BASE DE DATOS A UTILIZAR
        String ip;//IP DESDE LA QUE CONECTAMOS
        String nombre_tabla = "Autoras";//TABLA A USAR
        String comando_mysql;//COMANDO A EJECUTAR
        ResultSet consulta;//CONSULTA REALIZADA

        HashMap<String, Integer> autora_premios = new HashMap<String, Integer>();
        HashMap<String, Integer> autora_pais = new HashMap<String, Integer>();
        HashMap<String, Integer> autora_trabajo = new HashMap<String, Integer>();

        int id;
        String nombre;
        String apellidos;
        String alias;
        String fecha_nacimiento;
        int premios_recibidos;
        String pais_residencia;
        String area_trabajo;
        boolean salida = false;//CONDICION BUCLE

        ArrayList Listado_Autoras = new ArrayList();

        System.out.println("1.Para usar la base de datos");
        System.out.println("2.Para usar el archivo");
        int seleccion = lector.nextInt();

        switch (seleccion) {
            case 1:
                try {
                Usar_BD(lector, nombre_base, nombre_tabla, autora_premios, autora_pais, autora_trabajo, Listado_Autoras);
            } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                System.out.println("Excepcion: " + e);
            } catch (Exception e) {
                System.out.println("Excepcion: " + e);
                e.printStackTrace();
            }
            break;
            case 2://USAR ARCHIVO
                try {

                System.out.println("Introduce el nombre o direccion del archivo");

                File archivo = new File(lector.nextLine());//Crea el archivo

                Scanner lectorArchivo = new Scanner(archivo);//LEE EL FICHERO

                while (lectorArchivo.hasNext()) {//MIENTRAS HAYA ALGO,SEGUIRA LEYENDO

                    String linea = lectorArchivo.nextLine();
                    String[] caso = linea.split(",");//array

                    String id_palabra = caso[0];
                    nombre = caso[1];
                    apellidos = caso[2];
                    alias = caso[3];
                    fecha_nacimiento = caso[4];
                    String premios_recibidos_palabra = caso[5];
                    pais_residencia = caso[6];
                    area_trabajo = caso[7];
//                    String linea = id_palabra + "," + nombre + "," + apellidos + "," + fecha_nacimiento + "," + premios_recibidos_palabra + "," + pais_residencia + "," + area_trabajo;
//
//                    FileWriter escritor_archivos = new FileWriter(llenar, true);

                }

            } catch (Exception e) {
                System.out.println("Excepcion: " + e);
                e.printStackTrace();
            }
            break;

        }

        //FIN CONEXION BASE DE DATOS
        try {
            while (salida == false) {

                Menu();// MUESTRA LAS OPCIONES QUE TIENE EL MENU

                int opciones = lector.nextInt();

                switch (opciones) {
                    case 1://MUESTRA AUTORA ELEGIDA POR POSICION
                        Mostrar_Autora(lector, Listado_Autoras);
                        break;

                    case 2://MOSTRAR LA AUTORA CON MAS PREMIOS
                        Autora_Premios(autora_premios);
                        break;

                    case 3://MOSTRAR EL NUMERO DE AUTORAS POR PAIS DE RESIDENCIA
                        Autoras_por_Pais(autora_pais);

                        break;

                    case 4://MOSTRAR EL NUMERO DE AUTORAS POR CAMPO DE TRABAJO
                        Autoras_por_Trabajo(autora_trabajo);
                        break;

                    case 5://AÑADIR DATOS
                        AñadirDatos(Listado_Autoras, lector, autora_pais, autora_trabajo, autora_premios);
                        break;

                    case 6://SALIR
                        salida = true;

                }//FIN SWITCH

            }//FIN WHILE2

//            File archivo = new File("Lista_Autoras");//Crea el archivo
//            archivo.mkdir();
//
//            for (int contador = 0; contador <= Listado_Autoras.size(); contador++) {
//                
//                String llenar = (String) Listado_Autoras.get(contador);
//                FileWriter escritor_archivos = new FileWriter(llenar, true);
//
//            }
        } catch (Exception e) {
            System.out.println("Excepcion: " + e);
            e.printStackTrace();
        }
        //RECOGEN LAS EXCEPCIONES DADAS
    }

    public static void Menu() {
        // MUESTRA LAS OPCIONES QUE TIENE EL MENU
        System.out.println("¿Que accion quieres realizar?");
        System.out.println("1.MOSTRAR LOS DATOS DE UNA AUTORA Y REALIZAR SU TRABAJO");
        System.out.println("2.MOSTRAR LA AUTORA CON MAS PREMIOS");
        System.out.println("3.MOSTRAR EL NUMERO DE AUTORAS POR PAIS DE RESIDENCIA");
        System.out.println("4.MOSTRAR EL NUMERO DE AUTORAS POR CAMPO DE TRABAJO");
        System.out.println("5.AÑADIR UNA AUTORA");
        System.out.println("6.SALIR");
    }

    public static void Usar_BD(Scanner lector, String nombre_base, String nombre_tabla, HashMap<String, Integer> autora_premios, HashMap<String, Integer> autora_pais, HashMap<String, Integer> autora_trabajo, ArrayList Listado_Autoras) throws SQLException, InstantiationException, ClassNotFoundException, IllegalAccessException {
        String ip;
        String comando_mysql;
        ResultSet consulta;
        int id;
        String nombre;
        String apellidos;
        String alias;
        String fecha_nacimiento;
        int premios_recibidos;
        String pais_residencia;
        String area_trabajo;
        //Usar base de datos

        System.out.println("Introduce tu ip");//la actual es 10.230.109.71
        ip = lector.next();
        // Cargamos la clase que implementa el Driver
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        // Creamos una nueva conexión a la base de datos elegida
        String url = "jdbc:mysql://" + ip + ":3306/" + nombre_base + "?serverTimezone=UTC";
        //INICIO CONEXION
        Connection conexion = DriverManager.getConnection(url, "root", "");
        // Obtenemos un Statement de la conexión
        Statement st = conexion.createStatement();
        comando_mysql = "SELECT * FROM " + nombre_tabla;
        consulta = st.executeQuery(comando_mysql);
        while (consulta.next()) {
            id = consulta.getInt("id");
            nombre = consulta.getString("nombre");
            apellidos = consulta.getString("apellidos");
            alias = consulta.getString("alias");
            fecha_nacimiento = consulta.getString("fecha_nacimiento");
            premios_recibidos = consulta.getInt("premios_recibidos");
            pais_residencia = consulta.getString("pais_residencia");
            area_trabajo = consulta.getString("area_trabajo");

            CalculoMapa(autora_premios, nombre, premios_recibidos, autora_pais, pais_residencia, autora_trabajo, area_trabajo);

            if ("directoras".equals(area_trabajo)) {
                Directoras directora = new Directoras(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
                Listado_Autoras.add(directora);
            } else if ("actrices".equals(area_trabajo)) {
                Actrices actriz = new Actrices(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
                Listado_Autoras.add(actriz);
            } else if ("cientificas".equals(area_trabajo)) {
                Cientificas cientifica = new Cientificas(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
                Listado_Autoras.add(cientifica);
            } else {
                Escritoras escritora = new Escritoras(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
                Listado_Autoras.add(escritora);
            }
        }
        st.close();
        conexion.close();
    }

    public static void Mostrar_Autora(Scanner lector, ArrayList Listado_Autoras) {
        //MUESTRA AUTORA ELEGIDA POR POSICION

        System.out.println("Introduce la posicion que deseas mostrar");

        int Posicion_Mostrar = lector.nextInt(); // ID que deseas buscar

        if (Posicion_Mostrar <= Listado_Autoras.size() && Posicion_Mostrar > 0) {

            System.out.println(Listado_Autoras.get(Posicion_Mostrar));
        } else {
            System.out.println("Posicion inexistente");
        }
        Separacion();
    }

    public static void Autora_Premios(HashMap<String, Integer> autora_premios) {
        //MOSTRAR LA AUTORA CON MAS PREMIOS

        int maxValor = Collections.max(autora_premios.values());

        for (HashMap.Entry<String, Integer> entry : autora_premios.entrySet()) {
            if (entry.getValue() == maxValor) {
                System.out.println("Autor: " + entry.getKey() + ", Premios: " + entry.getValue());
            }
        }
        Separacion();
    }

    public static void Autoras_por_Pais(HashMap<String, Integer> autora_pais) {
        //MOSTRAR EL NUMERO DE AUTORAS POR PAIS DE RESIDENCIA

        for (Map.Entry<String, Integer> entry : autora_pais.entrySet()) {
            String pais = entry.getKey();
            Integer cantidad = entry.getValue();
            System.out.println("Pais: " + pais + ", Cantidad: " + cantidad);
        }
    }

    public static void Autoras_por_Trabajo(HashMap<String, Integer> autora_trabajo) {
        //MOSTRAR EL NUMERO DE AUTORAS POR CAMPO DE TRABAJO

        for (Map.Entry<String, Integer> entry : autora_trabajo.entrySet()) {
            String profesion = entry.getKey();
            Integer cantidad = entry.getValue();
            System.out.println("Profesion: " + profesion + ", Cantidad: " + cantidad);
        }
        Separacion();
    }

    public static void AñadirDatos(ArrayList Listado_Autoras, Scanner lector, HashMap<String, Integer> autora_pais, HashMap<String, Integer> autora_trabajo, HashMap<String, Integer> autora_premios) {
        int id;
        String nombre;
        String alias;
        String apellidos;
        String fecha_nacimiento;
        int premios_recibidos;
        String pais_residencia;
        String area_trabajo;
        //AÑADIR DATOS

        id = Listado_Autoras.size() + 1;
        System.out.println("Introduce el nuevo nombre");
        nombre = lector.next();
        System.out.println("Introduce el nuevo alias");
        alias = lector.next();
        System.out.println("Introduce el nuevos apellidos");
        apellidos = lector.nextLine();
        System.out.println("Introduce el nueva fecha de nacimiento");
        fecha_nacimiento = lector.nextLine();
        System.out.println("Introduce el nuevos premios recibidos");
        premios_recibidos = lector.nextInt();
        System.out.println("Introduce el nuevo pais de residencia");
        pais_residencia = lector.next();
        System.out.println("Introduce el nuevo area de trabajo");
        area_trabajo = lector.next();
        if ("directoras".equals(area_trabajo)) {
            Directoras directora = new Directoras(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
            Listado_Autoras.add(directora);
        } else if ("actrices".equals(area_trabajo)) {
            Actrices actriz = new Actrices(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
            Listado_Autoras.add(actriz);
        } else if ("cientificas".equals(area_trabajo)) {
            Cientificas cientifica = new Cientificas(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
            Listado_Autoras.add(cientifica);
        } else {
            Escritoras escritora = new Escritoras(id, nombre, alias, apellidos, fecha_nacimiento, premios_recibidos, pais_residencia, area_trabajo);
            Listado_Autoras.add(escritora);
        }
        if (autora_pais.get(pais_residencia) == null) {//Se guardan las cantidad de autoras por pais de residencia
            autora_pais.put(pais_residencia, 1);
        } else {
            int cantidad = autora_pais.get(pais_residencia) + 1;
            autora_pais.put(pais_residencia, cantidad);
        }
        if (autora_trabajo.get(area_trabajo) == null) {//Se guardan las cantidad de autoras por campo de trabajo
            autora_trabajo.put(area_trabajo, 1);
        } else {
            int cantidad = autora_trabajo.get(area_trabajo) + 1;
            autora_trabajo.put(area_trabajo, cantidad);
        }
        CalculoMapa(autora_premios, nombre, premios_recibidos, autora_pais, pais_residencia, autora_trabajo, area_trabajo);
        Separacion();
    }

    public static void Separacion() {
        System.out.println("----------------------------------------------------------------------");
    }

    public static void CalculoMapa(HashMap<String, Integer> autora_premios, String nombre, int premios_recibidos, HashMap<String, Integer> autora_pais, String pais_residencia, HashMap<String, Integer> autora_trabajo, String area_trabajo) {
        autora_premios.put(nombre, premios_recibidos);//Se guardan las autoras y premios recibidos

        if (autora_pais.get(pais_residencia) == null) {//Se guardan las cantidad de autoras por pais de residencia
            autora_pais.put(pais_residencia, 1);
        } else {
            int cantidad = autora_pais.get(pais_residencia) + 1;
            autora_pais.put(pais_residencia, cantidad);
        }

        if (autora_trabajo.get(area_trabajo) == null) {//Se guardan las cantidad de autoras por campo de trabajo
            autora_trabajo.put(area_trabajo, 1);
        } else {
            int cantidad = autora_trabajo.get(area_trabajo) + 1;
            autora_trabajo.put(area_trabajo, cantidad);
        }
    }

}
