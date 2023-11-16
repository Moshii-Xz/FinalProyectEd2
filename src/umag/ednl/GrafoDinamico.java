
package umag.ednl;

import java.util.ArrayList;

public class GrafoDinamico<V, A> implements Grafo<V, A> {
    ArrayList<Vertice<V, A>> vertices = new ArrayList<>();

    @Override
    public void insVertice(V x) {
        vertices.add(new Vertice<V, A>(x));
    }

    @Override
    public V obtVertice(int pos) {
        for (int i = 0; i < vertices.size(); i++) {
            // Vertice<V, A> get = vertices.get(i);
            if (pos == i) {
                return vertices.get(i).getVertice();
            }
        }
        return null;
    }

    @Override
    public void insArista(int vi, int vf, A costo) {
        vertices.get(vi).addArista(new Arista<V, A>(obtVertice(vf), costo));
    }

    @Override
    public A obtArista(int vi, int vf) {
        Vertice<V, A> ver = vertices.get(vi);
        ArrayList<Arista<V, A>> aristas = ver.getAristas();
        for (int j = 0; j < aristas.size(); j++) {
            Arista<V, A> aris = aristas.get(j);
            if (aris.getSucesor().equals(obtVertice(vf))) {
                return aris.getArista();
            }
        }
        return null;
    }

    @Override
    public void elimArista(int vi, int vf) {
        Vertice<V, A> ver = vertices.get(vi);
        ArrayList<Arista<V, A>> aristas = ver.getAristas();
        for (int j = 0; j < aristas.size(); j++) {
            Arista<V, A> aris = aristas.get(j);
            if (aris.getSucesor().equals(obtVertice(vf))) {
                aristas.remove(j);
                break;
            }
        }
    }

    @Override
    public int orden() {
        return vertices.size();
    }

    @Override
    public ArrayList<V> sucesores(int pos) {
        ArrayList<V> suces = new ArrayList<>();
        Vertice<V, A> vert = vertices.get(pos);
        ArrayList<Arista<V, A>> aristas = vert.getAristas();
        for (int i = 0; i < aristas.size(); i++) {
            suces.add(aristas.get(i).getSucesor());
        }
        return suces;
    }

    public String mostrar() {
        String list = "";
        for (Vertice<V, A> vertice : vertices) {
            list += vertice.getVertice().toString() + " -> ";
            ArrayList<Arista<V, A>> aristas = vertice.getAristas();
            for (Arista<V, A> arista : aristas) {
                list += "|" + arista.getSucesor().toString() + "|--Costo: " + arista.getArista().toString() + "| ";
            }
            list += "\n";
        }

        return list;
    }

    @Override
    public void modificarVertice(int pos, V x) {
        vertices.get(pos).setVertice(x);

    }

}