package org.egibide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * RandomAccessFile - Acceso a Datos - 2024-2025
 */
public class App {
    public static void main(String[] args) {
        //escribirEmpleados();

        //leerEmpleados();

        leerEmpleado(7);

        //modificarEmpleado(6, "Fernández", 30, 7777.3)
    }

    public static void escribirEmpleados() {
        try {
            File fichero = new File("./ficheros/AleatorioEmple.dat");
            // Declaramos el fichero de acceso aleatorio
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            ArrayList<Empleado> empleados = new ArrayList<>();
            empleados.add(new Empleado(1, "Suárez", 10, 1000.45));
            empleados.add(new Empleado(2, "López", 20, 2400.60));
            empleados.add(new Empleado(3, "Etxeberria", 10, 3000.0));
            empleados.add(new Empleado(4, "Castillo", 10, 1500.50));
            empleados.add(new Empleado(5, "Agirre", 30, 2200.0));
            empleados.add(new Empleado(6, "Pérez", 30, 1400.65));
            empleados.add(new Empleado(7, "Sáenz de Ocáriz", 20, 2000000000000000.0));


            for (Empleado empleado : empleados) {
                System.out.println("Posición inicio empleado: " + file.getFilePointer());
                file.writeInt(empleado.getIdEmpleado());
                System.out.println("Posición inicio apellido: " + file.getFilePointer());
                long posicionInicioApellido = file.getFilePointer();
                file.writeChars(empleado.getApellido().length() > 10 ? empleado.getApellido().substring(0,10) : empleado.getApellido()); // Insertamos el apellido asegurándonos de que ocupa 10 o menos caractéres
                file.seek(posicionInicioApellido+20); // Nos movemos al inicio del apellido + 20 bytes para 10 caracteres
                System.out.println("Posición inicio departamento: " + file.getFilePointer());
                file.writeInt(empleado.getDepartamento()); // Insertamos el departamento
                System.out.println("Posición inicio salario: " + file.getFilePointer());
                file.writeDouble(empleado.getSalario()); // Insertamos salario
                System.out.println("Posición final empleado: " + file.getFilePointer());
            }

            file.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error al abrir el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }

    public static void leerEmpleados() {
        try {
            File fichero = new File("./ficheros/AleatorioEmple.dat");
            // Declaramos el fichero de acceso aleatorio
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            ArrayList<Empleado> empleados = new ArrayList<>();

            char[] buffer = new char[10];

            long posicion = file.getFilePointer();

            while(file.getFilePointer() != file.length()) {
                Empleado empleado = new Empleado();
                file.seek(posicion); // Nos posicionamos al inicio del empleado
                empleado.setIdEmpleado(file.readInt()); // Obtenemos el id del empleado, que es lo primero que está guardado

                // Recorremos uno a uno los caracteres del apellido
                for (int i = 0; i < buffer.length; i++) { // En este caso será de 10 el bucle
                    buffer[i] = file.readChar(); // Se van guardando en el array apellido
                }

                // Se convierte a String el array
                empleado.setApellido(new String(buffer).replace("\u0000", "")); // Reemplazamos el caracter \u0000 con un string vacio

                // Obtenemos el departamento
                empleado.setDepartamento(file.readInt());

                // Salario;
                empleado.setSalario(file.readDouble());

                // Guardamos el empleado en la lista
                empleados.add(empleado);

                // Nos posicionamos para el siguiente empleando, teniendo en cuenta que cada uno ocupa 36 bytes
                posicion = posicion + 36;
            }

            // Mostramos por pantalla los datos leidos desde el fichero
            for (Empleado emp : empleados) {
                System.out.println(emp);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al abrir el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }

    public static void leerEmpleado(Integer idEmpleado) {
        try {
            File fichero = new File("./ficheros/AleatorioEmple.dat");
            // Declaramos el fichero de acceso aleatorio
            RandomAccessFile file = new RandomAccessFile(fichero, "r");

            Empleado empleado = new Empleado();

            char[] buffer = new char[10];

            long posicion = (idEmpleado - 1) * 36; // Calculamos la posición en la que debería estar el empleado que nos solicitan

            if (posicion >= file.length() || posicion < 0) {
                // Si la posición calculada está más alla del final del fichero o es negativa
                System.err.println("Error al acceder al empleado: no existe");
            } else {
                file.seek(posicion); // Nos posicionamos al inicio del empleado
                empleado.setIdEmpleado(file.readInt()); // Obtenemos el id del empleado, que es lo primero que está guardado

                if (idEmpleado != empleado.getIdEmpleado()) {
                    System.err.println("Error al acceder al empleado: su posición en el fichero no es correcta");
                } else {
                    // Recorremos uno a uno los caracteres del apellido
                    for (int i = 0; i < buffer.length; i++) { // En este caso será de 10 el bucle
                        buffer[i] = file.readChar(); // Se van guardando en el array apellido
                    }

                    // Se convierte a String el array
                    empleado.setApellido(new String(buffer).replace("\u0000", "")); // Reemplazamos el caracter \u0000 con un string vacio

                    // Obtenemos el departamento
                    empleado.setDepartamento(file.readInt());

                    // Salario;
                    empleado.setSalario(file.readDouble());

                    // Mostramos por pantalla los datos leidos desde el fichero
                    System.out.println(empleado);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al abrir el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }

    public static void modificarEmpleado(Integer idEmpleado, String apellido, int departamento, double salario) {
        /***/
    }
}
