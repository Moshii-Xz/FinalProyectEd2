package umag;

import java.util.HashSet;

import umag.ednl.Grafo;

public class Dijktra {
    Grafo<String, Integer> grafo;
    int origen;
    HashSet<String> visitados;
    int d[];

    public Dijktra(Grafo<String, Integer> grafo, int origen) {
        this.grafo = grafo;
        this.origen = origen;
        visitados = new HashSet<>();
        int n = grafo.orden();
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = grafo.obtArista(origen, i);
        }
        costoMinino();
    }
    public void costoMininoPro() {
        visitados.add(grafo.obtVertice(origen));
        int n = grafo.orden();
        for (int i = 0; i < n; i++) {
            int min = 0;
            int pos = -1;
            for (int j = 0; j < n; j++) {
                if (!visitados.contains(grafo.obtVertice(j))) {
                    if (d[j] < min) {
                        min = d[j];
                        pos = j;
                    }
                }
            }
            if (pos != -1) {
                visitados.add(grafo.obtVertice(pos));
                for (int j = 0; j < n; j++) {
                    if (!visitados.contains(grafo.obtVertice(j))) {
                        int costo = grafo.obtArista(pos, j);
                        if (costo != 0) {
                            if (d[j] > d[pos] + costo) {
                                d[j] = d[pos] + costo;
                            }
                        }
                    }
                }
            }
        }
    }

    public void costoMinino() {
        visitados.add(grafo.obtVertice(origen));
        int n = grafo.orden();
        for (int i = 0; i < n; i++) {
            int pos = menor();
            visitados.add(grafo.obtVertice(pos));
            for (int j = 0; j < n; j++) {
                if (!visitados.contains(grafo.obtVertice(j))) {
                    d[j] = Integer.min(d[j], d[pos] + grafo.obtArista(pos, j));
                }
            }
        }
    }

    private int menor() {
        int min = 0;
        int imin = 0;
        for (int i = 0; i < grafo.orden(); i++) {
            if (!visitados.contains(grafo.obtVertice(i))) {
                if (d[i] < min) {
                    min = d[i];
                    imin = i;
                }
            }
        }
        return imin;
    }

}
