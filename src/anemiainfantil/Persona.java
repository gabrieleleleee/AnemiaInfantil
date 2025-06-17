
package anemiainfantil;

import java.io.Serializable;

public class Persona implements Serializable {
    protected String nombre;
    protected String dni;
public Persona()
{
    
}
    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
}
