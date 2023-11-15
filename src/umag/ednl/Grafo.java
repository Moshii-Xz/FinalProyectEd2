
package umag.ednl;

import java.util.List;

public interface Grafo<V, A> {
    void insVertice(V x);

    V obtVertice(int pos);

    void insArista(int vi, int vf, A costo);

    A obtArista(int vi, int vf);


    void elimArista(int vi, int vf);

    int orden(); // # de verticies en el grafo

    String toString();

    List<V> sucesores(int pos); // Vertices que tienen una arista desde el vertice en la posición pos hacia
                                // otros vertices

    void modificarVertice(int pos, V x);

    String mostrar();
}