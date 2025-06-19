package anemiainfantil;

import java.util.Scanner;

public class AnemiaApp {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorNinios gestor = new GestorNinios();

    public static void main(String[] args) {
        int opcion;      
        do {
            System.out.println("----- Sistema de Registro de Anemia Infantil -----");
            System.out.println("1. Registrar niño");
            System.out.println("2. Ver historial");
            System.out.println("3. Buscar niño por nombre");
            System.out.println("4. Buscar niño por DNI");
            System.out.println("5. Atender Paciente: ");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: registrarNinio(); break;
                case 2: mostrarHistorial(); break;
                case 3: buscarNinioPorNombre(); break;
                case 4: buscarNinioPorDni(); break;
                case 5: atender(); break;
                case 6: System.out.println("Saliendo del programa..."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    private static void registrarNinio() {
        System.out.print("Nombre del niño/a: ");
        String nombre = scanner.nextLine();

        System.out.print("Edad en años: ");
        int edadAnios = scanner.nextInt();
        scanner.nextLine();

        int edadMeses = edadAnios * 12;
        if (edadMeses >= 36) {
            System.out.println("Error: Solo se pueden registrar niños menores de 3 años.");
            return;
        }

        System.out.print("Nivel de hemoglobina (g/dL): ");
        double hemoglobina = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("DNI: ");
        String dni = scanner.nextLine();

        Ninio nuevoNinio = new Ninio(nombre, edadMeses, hemoglobina, dni);
        boolean agregado = gestor.agregarNinio(nuevoNinio);

        if (agregado) {
            System.out.println("Niño registrado con éxito.");
            System.out.println("Evaluación: " + nuevoNinio.evaluarRiesgoAnemia());
        } else {
            System.out.println("Error: Ya existe un niño registrado con ese DNI.");
        }
    }

    private static void mostrarHistorial() {
        System.out.println("Historial niños registrados: ");
        gestor.mostrarTodos();
    }

    private static void buscarNinioPorNombre() {
        System.out.print("Nombre del niño/a a buscar: ");
        String nombre = scanner.nextLine();
        Ninio encontrado = gestor.buscarPorNombre(nombre);
        if (encontrado != null) {
            encontrado.mostrarInformacion();
        } else {
            System.out.println("No se encontró ningún niño/a con ese nombre.");
        }
    }

    private static void buscarNinioPorDni() {
        System.out.print("DNI del niño/a a buscar: ");
        String dni = scanner.nextLine();
        Ninio encontrado = gestor.buscarPorDni(dni);
        if (encontrado != null) {
            encontrado.mostrarInformacion();
        } else {
            System.out.println("No se encontró ningún niño/a con ese DNI.");
        }
    }
    private static void atender()
    {
        System.out.println("Ingrese dni para ser atendido: ");
        String dni = scanner.nextLine();
        Ninio n= gestor.buscarPorDni(dni);
        if(n != null)
        {
            System.out.println("REPORTE: ");
            n.mostrarInformacion();
            if(n.tieneRiesgoAnemia())
            {
                System.out.println("ESTADO: RIESGO ANEMIA, dirijase al hematólogo");
                
            }
            else
            {
                System.out.println("ESTADO: SIN RIESGO DE ANEMIA, SANO");
                
            }
            System.out.println("FECHA DE ATENCIÓN: " + java.time.LocalDate.now());
            gestor.eliminarNinio(dni);
            
        }
        else
        {
            System.out.println("DNI INCORRECTO. ");
        }
    }
   
}