package anemiainfantil;

import java.io.*;
import java.util.ArrayList;

public class GestorNinios {
    private ArrayList<Ninio> listaNinios;
    private final String ARCHIVO = "ninios.bin";

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
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
        oos.writeObject(listaNinios);
    } catch (IOException e) {
        System.out.println("Error al guardar datos: " + e.getMessage());
    }
}
@SuppressWarnings("unchecked")
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) 
        {
           listaNinios = (ArrayList<Ninio>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }
}