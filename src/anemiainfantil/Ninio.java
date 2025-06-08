package anemiainfantil;

public class Ninio {
    private String nombre;
    private int edadMeses;
    private double hemoglobina;
    private String dni;

    public Ninio(String nombre, int edadMeses, double hemoglobina, String dni) {
        this.nombre = nombre;
        this.edadMeses = edadMeses;
        this.hemoglobina = hemoglobina;
        this.dni = dni;
    }

    public String getNombre() { return nombre; }
    public int getEdadMeses() { return edadMeses; }
    public double getHemoglobina() { return hemoglobina; }
    public String getDni() { return dni; }

    public boolean tieneRiesgoAnemia() {
        return hemoglobina < 11.0;
    }

    public String evaluarRiesgoAnemia() {
        return tieneRiesgoAnemia() ? "Riesgo de anemia" : "Hemoglobina normal";
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edadMeses + " meses");
        System.out.println("Hemoglobina: " + hemoglobina + " g/dL");
        System.out.println("DNI: " + dni);
        System.out.println(tieneRiesgoAnemia() ? " Riesgo de anemia detectado." : " Nivel de hemoglobina normal.");
    }

    @Override
    public String toString() {
        return nombre + "," + edadMeses + "," + hemoglobina + "," + dni;
    }

    public static Ninio fromString(String linea) {
        String[] partes = linea.split(",");
        return new Ninio(partes[0], Integer.parseInt(partes[1]), Double.parseDouble(partes[2]), partes[3]);
    }
}