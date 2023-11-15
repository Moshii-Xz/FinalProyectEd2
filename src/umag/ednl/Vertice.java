/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umag.ednl;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Vertice<V, A> {

    private V vertice;
    private ArrayList<Arista<V, A>> aristas = new ArrayList<>();

    public Vertice(V vertice) {
        this.vertice = vertice;
    }

    public void addArista(Arista<V, A> arista) {
        aristas.add(arista);
    }

    public A getArista(int pos) {
        return aristas.get(pos).getArista();
    }

    /**
     * @return the vertice
     */
    public V getVertice() {
        return vertice;
    }

    /**
     * @param vertice the vertice to set
     */
    public void setVertice(V vertice) {
        this.vertice = vertice;
    }

    /**
     * @return the aristas
     */
    public ArrayList<Arista<V, A>> getAristas() {
        return aristas;
    }

    /**
     * @param aristas the aristas to set
     */
    public void setAristas(ArrayList<Arista<V, A>> aristas) {
        this.aristas = aristas;
    }
}
