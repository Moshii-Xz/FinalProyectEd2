
package umag.ednl;

public class Ambulancia {
    private int id;
    private int carga_de_trabajo = 0;
    private boolean ocupado;

    public String toString() {
        return "Ambulancia{" + "id l=" + id + ", carga_de_trabajo=" + carga_de_trabajo + ", ocupado="
                + ocupado + '}';
    }

    public Ambulancia(int id, boolean ocupado) {
        this.id = id;
        this.ocupado = ocupado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setCarga_de_trabajo(int carga_de_trabajo) {
        this.carga_de_trabajo = carga_de_trabajo;
    }

    public int getCarga_de_trabajo() {
        return this.carga_de_trabajo;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean getOcupado() {
        return this.ocupado;
    }

}
