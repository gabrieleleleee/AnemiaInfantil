    package anemiainfantil;

import java.io.Serializable;

public class Ninio extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int edadMeses;
    private double hemoglobina;
   
public Ninio()
{
    super(); //Constructor vacío necesario para que la deserialización (lectura desde archivo binario) funcione. CLAVE 
}
    public Ninio(String nombre, int edadMeses, double hemoglobina, String dni) {
        super(nombre, dni);
        this.edadMeses = edadMeses;
        this.hemoglobina = hemoglobina;  
    }
    public int getEdadMeses()
    {  
    return edadMeses; 
    } 
    public double getHemoglobina() 
    {
        return hemoglobina; 
    }

    public void setEdadMeses(int edadMeses) {
        this.edadMeses = edadMeses;
    }

    public void setHemoglobina(double hemoglobina) {
        this.hemoglobina = hemoglobina;
    }
    
    public boolean tieneRiesgoAnemia() 
    {
        return hemoglobina < 11.0;
    }

    public String evaluarRiesgoAnemia() {
       if (tieneRiesgoAnemia())
       {
           System.out.println("Riesgo de anemia detectado");
           return "Riesgo de anemia detectado";
       }
       else
       {
           System.out.println("Nivel de hemoglobina normal");
           return "Hemoglobina normal";
       }
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edadMeses + " meses");
        System.out.println("Hemoglobina: " + hemoglobina + " g/dL");
        System.out.println("DNI: " + dni);
        if (tieneRiesgoAnemia())
                {
                    System.out.println("Riesgo de anemia detectado");
                }
        else
        {
            System.out.println("Nivel de hemoglobina normal");
            
        }
    }

    @Override
    public String toString() {
        return nombre + "," + edadMeses + "," + hemoglobina + "," + dni;
    }

    
}