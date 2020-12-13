import dataStructures.graph.DiGraph;
import dataStructures.graph.DictionaryDiGraph;
import dataStructures.set.HashSet;
import dataStructures.set.Set;

import static dataStructures.graph.SCCDiGraph.restrictDiGraph;
import static dataStructures.graph.SCCDiGraph.reverseDiGraph;
import static dataStructures.graph.SCCDiGraph.stronglyConnectedComponentsDiGraph;

public class main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DiGraph graph = new DictionaryDiGraph<>();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addVertex('H');

        graph.addDiEdge('A', 'B');
        graph.addDiEdge('B', 'E');
        graph.addDiEdge('E', 'A');
        graph.addDiEdge('B', 'F');
        graph.addDiEdge('E', 'F');
        graph.addDiEdge('F', 'G');
        graph.addDiEdge('G', 'F');
        graph.addDiEdge('C', 'G');
        graph.addDiEdge('H', 'G');
        graph.addDiEdge('C', 'D');
        graph.addDiEdge('D', 'C');
        graph.addDiEdge('D', 'H');
        graph.addDiEdge('H', 'D');

        System.out.println("Grafo original");
        System.out.println(graph);
        System.out.println("Grafo invertido");
        System.out.println(reverseDiGraph(graph));
        Set vertices = new HashSet<>();
        vertices.insert('A');
        vertices.insert('B');
        vertices.insert('E');
        vertices.insert('F');
        vertices.insert('G');
        System.out.println("Grafo restringido");
        System.out.println(restrictDiGraph(graph, vertices));
        System.out.println("Partes conexas");
        System.out.println(stronglyConnectedComponentsDiGraph(graph));

    }
}
