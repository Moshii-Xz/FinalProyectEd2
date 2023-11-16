
package umag.ednl;

import java.util.ArrayList;

public class Barrio {
    private String nombre;
    private int accidentes;
    private int tiempo;
    private ArrayList<Ambulancia> ambulancias = new ArrayList<>();

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Barrio{" + "nombre=" + nombre + ", accidentes=" + accidentes + ", ambulancias=" + ambulancias + '}';
    }

    public Barrio(String nombre) {
        this.nombre = nombre;
    }

    public Barrio() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimeraLetra() {
        return nombre.substring(0, 1);
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

    public String totalAmbulancias() {
        // ambulancias por id
        String list = "";
        for (Ambulancia ambulancia : ambulancias) {
            list += ambulancia.getId() + " ";
        }
        return list;
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
