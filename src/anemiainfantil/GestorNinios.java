package anemiainfantil;

import java.io.*;
import java.util.ArrayList;

public class GestorNinios {
    private ArrayList<Ninio> listaNinios;
    private final String ARCHIVO = "ninios.txt";

    public GestorNinios() {
        listaNinios = new ArrayList<>();
        cargarDesdeArchivo();
    }

    public boolean agregarNinio(Ninio n) {
        for (Ninio existente : listaNinios) {
            if (existente.getDni().equals(n.getDni())) return false;
        }
        listaNinios.add(n);
        guardarEnArchivo();
        return true;
    }

    public void mostrarTodos() {
        if (listaNinios.isEmpty()) {
            System.out.println("No hay niños registrados.");
        } else {
            for (Ninio n : listaNinios) {
                n.mostrarInformacion();
                System.out.println("------------");
            }
        }
    }

    public Ninio buscarPorNombre(String nombre) {
        for (Ninio n : listaNinios) {
            if (n.getNombre().equalsIgnoreCase(nombre)) return n;
        }
        return null;
    }

    public Ninio buscarPorDni(String dni) {
        for (Ninio n : listaNinios) {
            if (n.getDni().equalsIgnoreCase(dni)) return n;
        }
        return null;
    }

    private void guardarEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Ninio n : listaNinios) {
                writer.println(n.toString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                listaNinios.add(Ninio.fromString(linea));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }
}