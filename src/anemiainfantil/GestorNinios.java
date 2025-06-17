package anemiainfantil;

import java.io.*;
import java.util.ArrayList;

public class GestorNinios {
    private ArrayList<Ninio> listaNinios;
    private final String ARCHIVO = "ninios.dat" ;

    public GestorNinios() {
    listaNinios = new ArrayList<>();
    cargarDesdeArchivo();
    System.out.println(" Constructor ejecutado");
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
        System.out.println("DATOS GUARDADOS EN EL ARCHIVO");
    } catch (IOException e) {
        System.out.println("Error al guardar: " + e.getMessage());
    }
}   
@SuppressWarnings("unchecked")
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) 
        {
            System.out.println("ARCHIVO NO ENCONTRADO. SE INICIA VACÍO");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) 
        {
           listaNinios = (ArrayList<Ninio>) ois.readObject();
            System.out.println("DATOS CARGADOS DESDE ARCHIVO");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
        System.out.println("Se cargaron: " + listaNinios.size()+" niños desde el archivo.");
    }
}