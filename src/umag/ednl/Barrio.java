
package umag.ednl;

import java.util.ArrayList;

public class Barrio {
    private String nombre;
    private int accidentes;
    private int tiempo;
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    private ArrayList<Ambulancia> ambulancias = new ArrayList<>();

    @Override
    public String toString() {
        return "Barrio{" + "nombre=" + nombre + ", accidentes=" + accidentes + ", ambulancias=" + ambulancias + '}';
    }

    public Barrio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the accidentes
     */
    public int getAccidentes() {
        return accidentes;
    }

    /**
     * @param accidentes the accidentes to set
     */
    public void setAccidentes(int accidentes) {
        this.accidentes = accidentes;
    }

    /**
     * @return the ambulancias
     */
    public ArrayList<Ambulancia> getAmbulancias() {
        return ambulancias;
    }

    /**
     * @param ambulancias the ambulancias to set
     */
    public void setAmbulancias(ArrayList<Ambulancia> ambulancias) {
        this.ambulancias = ambulancias;
    }

    public void agregarAmbulancia(Ambulancia ambulancia) {
        ambulancias.add(ambulancia);
    }
}
