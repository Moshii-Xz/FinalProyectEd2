package umag.ednl;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
//import com.mxgraph.model.mxGeometry;
import java.util.List;
import javax.swing.*;

public class GrafoVisualizer {
    private Grafo<Barrio, Integer> grafo;
    public JFrame frame;

    public GrafoVisualizer(Grafo<Barrio, Integer> grafo) {
        this.grafo = grafo;
    }

    public void visualizarGrafo() {
        // Crear el objeto mxGraph
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        // Set the background color of the graph
        graph.setCellStyle("fillColor=#FFFFFF");

        graph.getModel().beginUpdate();
        try {
            // Crear los vértices del grafo
            Object[] vertices = new Object[grafo.orden()];

            for (int i = 0; i < grafo.orden(); i++) {
                Barrio barrio = grafo.obtVertice(i);
                vertices[i] = graph.insertVertex(parent, "Accidentes: sdsadd ",
                        barrio.getNombre() + "\n Accidentes :" + barrio.getAccidentes() + "\nAmbulancias ID:"
                                + barrio.totalAmbulancias(),
                        20, 60, 100, 100,
                        mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_DOUBLE_ELLIPSE
                                + ";fillColor=#000000;strokeColor=#FF0000;rounded=false" + ";" + mxConstants.STYLE_IMAGE
                                + "=" + "/src/umag/Imagen/Ambulancia.png" + ";" + mxConstants.STYLE_FONTCOLOR + "="
                                + "#FFFFFF");
            }

            // Crear las aristas del grafo
            for (int i = 0; i < grafo.orden(); i++) {
                List<Barrio> sucesores = grafo.sucesores(i);
                for (int j = 0; j < sucesores.size(); j++) {
                    Barrio sucesor = sucesores.get(j);
                    int vf = posicion(grafo, sucesor.getNombre());

                    // obtener el costo de la arista
                    int costo = grafo.obtArista(i, vf);
                    String costoString = String.valueOf(costo);

                    graph.insertEdge(parent, null, "\n \n" + costoString, vertices[i], vertices[vf],
                            mxConstants.STYLE_EDGE + "=" + mxConstants.EDGESTYLE_LOOP
                                    + ";strokeColor=#0c181c;fillColor=#000000;rounded=true"
                                    + ";endArrow=classic;endSize=20");
                }
            }

            // Aplicar un algoritmo de diseño para organizar los vértices
            // mxFastOrganicLayout layout = new mxFastOrganicLayout(graph);
            mxCircleLayout layout = new mxCircleLayout(graph);
            // Configurar opciones de diseño (opcional)
            layout.setDisableEdgeStyle(true);
            layout.execute(parent);

        } finally {
            graph.getModel().endUpdate();
        }

        // Crear el componente de gráficos de JGraphX
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        // Crear el marco para mostrar el grafo
        if (frame == null) {
            // Crear el marco para mostrar el grafo solo si no existe
            frame = new JFrame("Grafo Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.getContentPane().add(graphComponent);
            frame.pack();
            frame.setVisible(true);
        } else {
            // Si el marco ya existe, actualizar el componente del gráfico
            frame.getContentPane().removeAll();
            frame.getContentPane().add(graphComponent);
            frame.revalidate();
            frame.repaint();
        }

    }

    public void cerrarVentana() {
        if (frame != null) {
            frame.dispose(); // Cierra la ventana
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

    public void actualizarVistaGrafo() {
        if (frame != null) {
            frame.repaint(); // Actualiza la ventana
        }
    }

}
