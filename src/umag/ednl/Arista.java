
package umag.ednl;

public class Arista<V, A> {

    private V sucesor;
    private A arista;

    public Arista(V sucesor, A arista) {
        this.sucesor = sucesor;
        this.arista = arista;
    }

    /**
     * @return the sucesor
     */
    public V getSucesor() {
        return sucesor;
    }

    /**
     * @param sucesor the sucesor to set
     */
    public void setSucesor(V sucesor) {
        this.sucesor = sucesor;
    }

    /**
     * @return the arista
     */
    public A getArista() {
        return arista;
    }

    /**
     * @param arista the arista to set
     */
    public void setArista(A arista) {
        this.arista = arista;
    }
}
