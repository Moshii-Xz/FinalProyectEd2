package umag;

import umag.ednl.Barrio;
import umag.ednl.Grafo;

public class Floyd {
    Grafo<Barrio, Integer> grafo;
    public int d[][];
    public int p[][];

    public Floyd(Grafo<Barrio, Integer> grafo) {
        this.grafo = grafo;
        int n = grafo.orden();
        d = new int[n][n];
        p = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grafo.obtArista(i, j) != null) {
                    d[i][j] = grafo.obtArista(i, j);
                } else {
                    d[i][j] = 99999999;
                }
                p[i][j] = -1;
            }
        }
        costoMinino();
    }

    public String solucion(){
        String list = "";
        for(int i = 0; i < grafo.orden(); i++){
            for(int j = 0; j < grafo.orden(); j++){
                list += d[i][j] + " ";
            }
            list += "\n";
        }
        return list;
    }
    String rut = "";
    public String ruta (int vi , int vf){
        rut = "";
        rutaRecursivo(vi, vf);
        return rut;
    }

    public void rutaRecursivo(int vi, int vf){
        if(p[vi][vf] == -1){
            rut += grafo.obtVertice(vi).getNombre() + " ";
            rut += grafo.obtVertice(vf).getNombre() + " ";
        }else{
            rutaRecursivo(vi, p[vi][vf]);
            rutaRecursivo(p[vi][vf], vf);
        }
    } 

    

    public void costoMinino(){
        int n = grafo.orden();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++) {
                    if (d[i][k] + d[k][j] < d[i][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        p[i][j] = k; //Hallar la ruta
                    }
                }
            }
        }
    }
    
}
