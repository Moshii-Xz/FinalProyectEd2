package test;

import java.util.ArrayList;
import java.util.HashSet;
import umag.Floyd;
import umag.ednl.Ambulancia;
import umag.ednl.Barrio;
import umag.ednl.Grafo;
import umag.ednl.GrafoDinamico;
import umag.ednl.GrafoVisualizer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Prueba {
    private static ArrayList<String> listaInformes = new ArrayList<>();
    private static ArrayList<String> incidentes = new ArrayList<>();
    private static GrafoVisualizer visualizer;

    public static void main(String[] args) {
        Grafo<Barrio, Integer> grafiti = crearGrafo();
        visualizer = new GrafoVisualizer(grafiti);
        insertarAmbulancias(grafiti);

        // Crear hilo para el menú
        Thread menuThread = new Thread(() -> menu(grafiti));
        menuThread.start();
        visualizer.visualizarGrafo();

        // Crear hilo para la tarea que se ejecuta cada minuto
        Thread tareaMinutoThread = new Thread(() -> ejecutarTareaCadaMinuto(grafiti));
        tareaMinutoThread.start();
    }

    // crear Grafo de Barrios
    private static Grafo<Barrio, Integer> crearGrafo() {
        Grafo<Barrio, Integer> grafo = new GrafoDinamico<>();

        // 20 barrios
        grafo.insVertice(new Barrio("Pescaito"));
        grafo.insVertice(new Barrio("Almendros"));
        grafo.insVertice(new Barrio("El Pando"));
        grafo.insVertice(new Barrio("Ciudadela 20 Julio"));
        grafo.insVertice(new Barrio("Libano"));
        grafo.insVertice(new Barrio("Don Jaca"));
        grafo.insVertice(new Barrio("Mamatoco"));
        grafo.insVertice(new Barrio("La Paz"));
        grafo.insVertice(new Barrio("Alfonso Lopez"));
        grafo.insVertice(new Barrio("El Rodadero"));
        // conectar barrios distancia en minutos en carro aleatoria
        grafo.insArista(posicion(grafo, "Pescaito"), posicion(grafo, "Almendros"), 15);
        grafo.insArista(posicion(grafo, "Pescaito"), posicion(grafo, "El Pando"), 25);
        grafo.insArista(posicion(grafo, "Pescaito"), posicion(grafo, "Ciudadela 20 Julio"), 12);
        grafo.insArista(posicion(grafo, "Pescaito"), posicion(grafo, "Libano"), 30);
        grafo.insArista(posicion(grafo, "Pescaito"), posicion(grafo, "Don Jaca"), 15);
        grafo.insArista(posicion(grafo, "Almendros"), posicion(grafo, "Pescaito"), 8);
        grafo.insArista(posicion(grafo, "Almendros"), posicion(grafo, "El Pando"), 20);
        grafo.insArista(posicion(grafo, "Almendros"), posicion(grafo, "Ciudadela 20 Julio"), 35);
        grafo.insArista(posicion(grafo, "Almendros"), posicion(grafo, "Libano"), 18);
        grafo.insArista(posicion(grafo, "El Pando"), posicion(grafo, "Pescaito"), 40);
        grafo.insArista(posicion(grafo, "El Pando"), posicion(grafo, "Almendros"), 10);
        grafo.insArista(posicion(grafo, "El Pando"), posicion(grafo, "Ciudadela 20 Julio"), 22);
        grafo.insArista(posicion(grafo, "El Pando"), posicion(grafo, "Libano"), 12);
        grafo.insArista(posicion(grafo, "El Pando"), posicion(grafo, "Don Jaca"), 30);
        grafo.insArista(posicion(grafo, "Ciudadela 20 Julio"), posicion(grafo, "Pescaito"), 27);
        grafo.insArista(posicion(grafo, "Ciudadela 20 Julio"), posicion(grafo, "Almendros"), 15);
        grafo.insArista(posicion(grafo, "Ciudadela 20 Julio"), posicion(grafo, "El Pando"), 22);
        grafo.insArista(posicion(grafo, "Ciudadela 20 Julio"), posicion(grafo, "Libano"), 10);
        grafo.insArista(posicion(grafo, "Ciudadela 20 Julio"), posicion(grafo, "Don Jaca"), 20);
        grafo.insArista(posicion(grafo, "Ciudadela 20 Julio"), posicion(grafo, "Mamatoco"), 15);
        grafo.insArista(posicion(grafo, "Libano"), posicion(grafo, "Pescaito"), 30);
        grafo.insArista(posicion(grafo, "Libano"), posicion(grafo, "Almendros"), 20);
        grafo.insArista(posicion(grafo, "Libano"), posicion(grafo, "El Pando"), 15);
        grafo.insArista(posicion(grafo, "Libano"), posicion(grafo, "Ciudadela 20 Julio"), 25);
        grafo.insArista(posicion(grafo, "Libano"), posicion(grafo, "Don Jaca"), 30);
        grafo.insArista(posicion(grafo, "Libano"), posicion(grafo, "Mamatoco"), 20);
        grafo.insArista(posicion(grafo, "Don Jaca"), posicion(grafo, "Pescaito"), 30);
        grafo.insArista(posicion(grafo, "Don Jaca"), posicion(grafo, "Almendros"), 20);
        grafo.insArista(posicion(grafo, "Don Jaca"), posicion(grafo, "El Pando"), 15);
        grafo.insArista(posicion(grafo, "Don Jaca"), posicion(grafo, "Ciudadela 20 Julio"), 25);
        grafo.insArista(posicion(grafo, "Don Jaca"), posicion(grafo, "Libano"), 30);
        grafo.insArista(posicion(grafo, "Don Jaca"), posicion(grafo, "Mamatoco"), 10);
        grafo.insArista(posicion(grafo, "Mamatoco"), posicion(grafo, "Pescaito"), 20);
        grafo.insArista(posicion(grafo, "Mamatoco"), posicion(grafo, "Almendros"), 30);
        grafo.insArista(posicion(grafo, "Mamatoco"), posicion(grafo, "El Pando"), 25);
        grafo.insArista(posicion(grafo, "Mamatoco"), posicion(grafo, "Ciudadela 20 Julio"), 35);
        grafo.insArista(posicion(grafo, "Mamatoco"), posicion(grafo, "Libano"), 20);
        grafo.insArista(posicion(grafo, "Mamatoco"), posicion(grafo, "Don Jaca"), 10);
        grafo.insArista(posicion(grafo, "La Paz"), posicion(grafo, "Alfonso Lopez"), 15);
        grafo.insArista(posicion(grafo, "La Paz"), posicion(grafo, "El Rodadero"), 25);
        grafo.insArista(posicion(grafo, "Alfonso Lopez"), posicion(grafo, "La Paz"), 8);
        grafo.insArista(posicion(grafo, "Alfonso Lopez"), posicion(grafo, "El Rodadero"), 20);
        grafo.insArista(posicion(grafo, "Alfonso Lopez"), posicion(grafo, "Almendros"), 14);
        grafo.insArista(posicion(grafo, "Alfonso Lopez"), posicion(grafo, "Pescaito"), 17);
        grafo.insArista(posicion(grafo, "El Rodadero"), posicion(grafo, "La Paz"), 40);
        grafo.insArista(posicion(grafo, "El Rodadero"), posicion(grafo, "Don Jaca"), 28);
        grafo.insArista(posicion(grafo, "El Rodadero"), posicion(grafo, "Alfonso Lopez"), 35);
        grafo.insArista(posicion(grafo, "El Rodadero"), posicion(grafo, "El Pando"), 49);
        return grafo;
    }

    static int inf = 99999;

    // Función que ejecuta una tarea cada minuto
    private static void ejecutarTareaCadaMinuto(Grafo<Barrio, Integer> grafo) {
        // Crear una instancia del temporizador
        Timer timer = new Timer();

        // Definir la tarea a ejecutar
        TimerTask tarea = new TimerTask() {
            public void run() {
                visualizer.visualizarGrafo();
                // System.out.println("¡Se actualizo el grafo!");
            };
        };

        // Programar la tarea para ejecutarse cada minuto (en milisegundos)
        timer.schedule(tarea, 0, 2 * 1000);

        // Esperar unos minutos antes de detener el programa (solo para observar la
        // salida)
        try {
            Thread.sleep(5 * 60 * 1000); // Esperar 5 minutos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detener el temporizador después de un tiempo
        timer.cancel();
    }

    // mover la ambulancia desde el origen hasta el destino usando el algoritmo de
    // Floyd y luego de moverla se debe
    // actualizar el grafo
    private static void moverAmbulancia(Grafo<Barrio, Integer> grafo, Barrio barrioAmb, Barrio barrio) {
        Floyd floyd = new Floyd(grafo);
        int origen = posicion(grafo, barrioAmb.getNombre());
        int destino = posicion(grafo, barrio.getNombre());

        int costo = floyd.d[origen][destino];
        int pos = posicion(grafo, barrioAmb.getNombre());
        Barrio barrioAmbulancia = grafo.obtVertice(pos);
        Ambulancia ambulancia = barrioAmbulancia.getAmbulancias().removeFirst();
        barrio.agregarAmbulancia(ambulancia);
        // Barrio barrioDestino = grafo.obtVertice(destino);
        barrioAmbulancia.setAccidentes(barrioAmbulancia.getAccidentes() - 1);
        barrioAmbulancia.setTiempo(barrioAmbulancia.getTiempo() + costo);
        barrioAmbulancia.setAccidentes(barrioAmbulancia.getAccidentes() + 1);
        if (costo != 99999999) {
            JOptionPane.showMessageDialog(null, "La ambulancia " + ambulancia.toString()
                    + " se movio desde " + barrioAmbulancia.getNombre() + " hasta " + barrio.getNombre() + " en "
                    + costo
                    + " minutos");
        }
    }

    private static void generarIncidente(Grafo<Barrio, Integer> grafo) {
        int pos = (int) (Math.random() * grafo.orden());
        Barrio barrio = grafo.obtVertice(pos);
        barrio.setAccidentes(barrio.getAccidentes() + 1);
        String siniestro = listaIncidentes();
        JOptionPane.showMessageDialog(null, " se reportó " + siniestro + " en el barrio " + barrio.getNombre());
        registrarIncidente(barrio, siniestro);
        visualizer.actualizarVistaGrafo();
    }

    private static int costoMinimo(Grafo<Barrio, Integer> grafo, Barrio barrioAmb, Barrio barrio) {
        Floyd floyd = new Floyd(grafo);
        int origen = posicion(grafo, barrioAmb.getNombre());
        int destino = posicion(grafo, barrio.getNombre());
        return floyd.d[origen][destino];
    }

    // Función para resolver un incidente en un barrio
    private static void resolverIncidente(Grafo<Barrio, Integer> grafo) {
        String nombreBarrio = JOptionPane.showInputDialog("Ingrese el nombre del barrio con incidente");
        int pos = posicion(grafo, nombreBarrio);

        if (pos != -1) {
            Barrio barrioIncidente = grafo.obtVertice(pos);
            boolean aislado = grafo.sucesores(pos).isEmpty();
            if (barrioIncidente.getAccidentes() > 0) {
                Barrio barrioAmbulancia = obtenerBarrioConAmbulancia(grafo);

                if (!barrioIncidente.getAmbulancias().isEmpty()) {
                    barrioIncidente.setAccidentes(barrioIncidente.getAccidentes() - 1);
                    JOptionPane.showMessageDialog(null,
                            "Incidente en el barrio " + barrioIncidente.getNombre() + "ha sido solucionado");
                } else if (barrioAmbulancia != null) {
                    int costo = costoMinimo(grafo, barrioAmbulancia, barrioIncidente);

                    // Mover la ambulancia
                    if (!aislado) {
                        moverAmbulancia(grafo, barrioAmbulancia, barrioIncidente);
                    }

                    // Actualizar la vista del grafo
                    GrafoVisualizer visualizer = new GrafoVisualizer(grafo);
                    visualizer.actualizarVistaGrafo();
                    if (costo != 99999999 && !aislado) {
                        JOptionPane.showMessageDialog(null, "Incidente en el barrio " +
                                barrioIncidente.getNombre() +
                                " resuelto. La ambulancia se movió desde " + barrioAmbulancia.getNombre() +
                                " hasta " + barrioIncidente.getNombre() + " en " + costo + " minutos.");
                        barrioIncidente.setAccidentes(barrioIncidente.getAccidentes() - 1);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay ruta disponible para llegar al barrio " + barrioIncidente.getNombre());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No hay ambulancias disponibles para resolver el incidente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El barrio " + barrioIncidente.getNombre() +
                        " no tiene incidentes para resolver.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Barrio no encontrado.");
        }
    }

    // Obtener un barrio con ambulancia disponible
    private static Barrio obtenerBarrioConAmbulancia(Grafo<Barrio, Integer> grafo) {
        for (int i = 0; i < grafo.orden(); i++) {
            Barrio barrio = grafo.obtVertice(i);
            if (!barrio.getAmbulancias().isEmpty()) {
                return barrio;
            }
        }
        return null;
    }

    /*
     * Situar N ambulancias en esos 20 barrios. N debe ser mayor que 3 y menor que
     * 6. No
     * pueden estar en el mismo barrio.
     */
    private static void insertarAmbulancias(Grafo<Barrio, Integer> grafo) {
        HashSet<Barrio> asignados = new HashSet<>();
        int nAmbulancia = 3 + (int) (Math.random() * 6);
        for (int i = 0; i < nAmbulancia; i++) {
            int pos = (int) (Math.random() * grafo.orden());
            Barrio barrio = grafo.obtVertice(pos);
            if (!asignados.contains(barrio)) {
                barrio.agregarAmbulancia(new Ambulancia(i + 1, false));
                asignados.add(barrio);
            }
        }
    }

    private static int posicion(Grafo<Barrio, Integer> grafo, String nombre) {
        for (int i = 0; i < grafo.orden(); i++) {
            if (grafo.obtVertice(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    private static int posicion(Grafo<Barrio, Integer> grafo, Barrio x) {
        for (int i = 0; i < grafo.orden(); i++) {
            if (grafo.obtVertice(i).equals(x))
                return i;
        }
        return -1;
    }

    private static String datosBarrio(Grafo<Barrio, Integer> grafo, String nombre) {
        int pos = posicion(grafo, nombre);
        if (pos != -1) {
            Barrio barrio = grafo.obtVertice(pos);
            for (int i = 0; i < grafo.orden(); i++) {
                if (barrio.getNombre().equalsIgnoreCase(nombre)) {
                    return barrio.toString();
                }
            }
        }
        return "";
    }

    // aislar barrio es eliminar todas las aristas que salen y entran a un barrio
    private static void aislarBarrio(Grafo<Barrio, Integer> grafo, String nombre) {
        int pos = posicion(grafo, nombre);
        if (pos != -1) {
            Barrio barrio = grafo.obtVertice(pos);
            for (int i = 0; i < grafo.orden(); i++) {
                if (barrio.getNombre().equalsIgnoreCase(nombre)) {
                    grafo.elimArista(pos, i);
                    grafo.elimArista(i, pos);
                }
            }
        }
    }

    public static ArrayList<Barrio> DFS(Grafo<Barrio, Integer> grafo, Barrio o) {
        visitados.add(o);
        ArrayList<Barrio> sucesores = grafo.sucesores(posicion(grafo, o));
        for (Barrio b : sucesores) {
            if (!visitados.contains(b)) {
                visitados.add(b);
                recorrido.add(b);
            } else {
                DFS(grafo, b);
            }
        }
        return recorrido;
    }

    public static String listaIncidentes() {
        ArrayList<String> nombreIncidentes = new ArrayList<>();
        nombreIncidentes.add("3 muertos y 1 herido en enfrentamiento por partido del union");
        nombreIncidentes.add("pelea por cuernos");
        nombreIncidentes.add("accidente de transito");
        nombreIncidentes.add("persona herida en robo");
        nombreIncidentes.add("persona muerta por infarto");
        nombreIncidentes.add("persona enferma de gravedad");
        nombreIncidentes.add("persona Electrocutada");
        nombreIncidentes.add("persona muerta por incidente sicarial");
        nombreIncidentes.add("heridos en contienda politica");
        return nombreIncidentes.get((int) (Math.random() * nombreIncidentes.size()));
    }

    public static String historicoDeInformes() {
        String data = "";
        for (int i = 0; i < listaInformes.size(); i++) {
            data += listaInformes.get(i) + "\n";
        }
        return data;
    }

    public static void registrarIncidente(Barrio barrio, String siniestro) {

        String informe = "-----------------------------\nBarrio: " + barrio.getNombre() + "\n"
                + "Siniestro: " + siniestro + "\n";
        listaInformes.add(informe);
    }

    public static String informes(Grafo<Barrio, Integer> g, int op, int id) {
        String informe = "";
        ArrayList<Ambulancia> ambulancias = new ArrayList<>();
        ArrayList<Barrio> barrios = new ArrayList<>();
        for (int i = 0; i < g.orden(); i++) {
            Barrio barrio = g.obtVertice(i);
            barrios.add(barrio);
            for (Ambulancia amb : barrio.getAmbulancias()) {
                ambulancias.add(amb);
            }
        }

        switch (op) {// Ambulancia con mas trabajo
            case 1: {
                int loadAverage = 0;
                Ambulancia may = ambulancias.get(0);
                for (Ambulancia amb : ambulancias) {
                    if (amb.getCarga_de_trabajo() > loadAverage) {
                        loadAverage = may.getCarga_de_trabajo();
                        may = amb;
                    }
                }
                informe += may.toString();
                return informe;
            }

            case 2: {// barrio con mas accidentes
                Barrio may = barrios.get(0);
                int nAccidentes = 0;
                for (Barrio barrio : barrios) {
                    if (barrio.getAccidentes() > nAccidentes) {
                        nAccidentes = may.getAccidentes();
                        may = barrio;
                    }
                }
                informe += may.toString();
                return informe;
            }

            case 3: {// informacion sobre ambulancia
                for (int i = 0; i < ambulancias.size(); i++) {
                    if (ambulancias.get(i).getId() == id) {
                        informe += ambulancias.get(i).toString();
                        for (Barrio barrio : barrios) {
                            if (barrio.getAmbulancias().contains(ambulancias.get(i))) {
                                informe += "\nUbicacion: " + barrio.getNombre();
                                break;
                            }
                        }

                        return informe;
                    }
                }
            }
            default:
                break;
        }

        return informe;

    }

    static ArrayList<Barrio> recorrido = new ArrayList<>();
    static HashSet<Barrio> visitados = new HashSet<>();
    static Barrio origen = new Barrio();

    public static ArrayList<Barrio> recorridoMaximoAmbulancias(Grafo<Barrio, Integer> grafo, int id) {
        visitados.clear();
        recorrido.clear();
        ArrayList<Ambulancia> ambulancias = new ArrayList<>();
        Ambulancia ambAux = new Ambulancia();
        for (int i = 0; i < grafo.orden(); i++) {
            ArrayList<Ambulancia> ambu = grafo.obtVertice(i).getAmbulancias();
            if (ambu.size() >= 0) {
                for (int j = 0; j < ambu.size(); j++) {
                    if (ambu.get(j).getId() == id) {
                        ambulancias = grafo.obtVertice(i).getAmbulancias();
                        origen = grafo.obtVertice(i);
                        for (Ambulancia amb : ambulancias) {
                            if (amb.getId() == id) {
                                ambAux = amb;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return DFS(grafo, origen);
    }

    // Funcion para mostrar la matriz de costos del grafo y el nombre de los barrios
    public static String matrizCostos(Grafo<Barrio, Integer> grafo) {
        String matriz = "       ";
        for (int i = 0; i < grafo.orden(); i++) {
            matriz += grafo.obtVertice(i).getPrimeraLetra() + "-<>-";
        }
        matriz += "\n";
        for (int i = 0; i < grafo.orden(); i++) {
            matriz += grafo.obtVertice(i).getPrimeraLetra() + "->";
            for (int j = 0; j < grafo.orden(); j++) {
                if (grafo.obtArista(i, j) == null) {
                    matriz += "--∞--";
                    continue;
                }
                matriz += "--" + grafo.obtArista(i, j) + "--";
            }
            matriz += "\n";
        }
        return matriz;
    }

    private static void menu(Grafo<Barrio, Integer> grafo) {
        String men = "—————————————————————————\n"
                + " MENU DE OPCIONES\n"
                + "—————————————————————————\n"
                + "     1. Mostrar Matriz de costos\n"
                + "     2. Mostrar datos de un barrio\n"
                + "     3. Aislar barrio\n"
                + "     4. Generar insidente\n"
                + "     5. Resolver incidente\n"
                + "     6. Recorrido Maximo\n"
                + "     7. Registro incidente\n"
                + "     8. Informes\n"
                + "     0. Salir\n"
                + "—————————————————————————\n";
        do {
            int op = Integer.parseInt(JOptionPane.showInputDialog(men));
            switch (op) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Matriz de costos:\n "
                            + matrizCostos(grafo));
                    break;
                case 2:
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del barrio");
                    JOptionPane.showMessageDialog(null, datosBarrio(grafo, nombre));
                    break;
                case 3:
                    String nombreBarrio = JOptionPane.showInputDialog("Ingrese el nombre del barrio");
                    aislarBarrio(grafo, nombreBarrio);
                    break;

                case 4:
                    generarIncidente(grafo);
                    break;

                case 5:
                    resolverIncidente(grafo);
                    break;
                case 6:
                    String i = JOptionPane.showInputDialog("Ingrese el ID de la ambulancia");
                    int id = Integer.parseInt(i);
                    ArrayList<Barrio> reco = recorridoMaximoAmbulancias(grafo, id);
                    StringBuilder cadena = new StringBuilder();
                    for (Barrio ba : reco) {
                        cadena.append(ba.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, "Recorrido maximo de las ambulancia:\n "
                            + cadena.toString());
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Informe de incidentes:\n "
                            + historicoDeInformes());
                    break;
                case 8:
                    String mensaje = ("—————————————————————————\n"
                            + " MENU DE INFORMES\n"
                            + "—————————————————————————\n"
                            + "     1. Ambulancia con mas trabajo\n"
                            + "     2. barrio con mas accidentes\n"
                            + "     3. informacion sobre ambulancia\n"
                            + "—————————————————————————");
                    int opa = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
                    switch (opa) {
                        case 1:
                            JOptionPane.showMessageDialog(null, "Ambulancia con mas trabajo:\n "
                                    + informes(grafo, opa, 0));
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "Barrio con mas accidentes:\n "
                                    + informes(grafo, opa, 0));
                            break;
                        case 3:
                            String idA = JOptionPane.showInputDialog("Ingrese el ID de la ambulancia");
                            int idAm = Integer.parseInt(idA);
                            JOptionPane.showMessageDialog(null, "Informacion sobre ambulancia:\n "
                                    + informes(grafo, opa, idAm));
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "\n");
                            break;
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "\n");

            }
        } while (true);

    }

}