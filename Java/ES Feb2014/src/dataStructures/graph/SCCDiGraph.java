package dataStructures.graph;

import dataStructures.set.HashSet;
import dataStructures.set.Set;

public class SCCDiGraph {

    /**
     * apartado A
     */
    public static <V> DiGraph<V> reverseDiGraph(DiGraph<V> g) {
        DiGraph<V> reversedDiGraph = new DictionaryDiGraph<>();

        for (V vertex : g.vertices()) {
            reversedDiGraph.addVertex(vertex);
        }


        for (V vertex : g.vertices()) {
            for (V predecessor : g.predecessors(vertex)) {
                reversedDiGraph.addDiEdge(vertex, predecessor);
            }
        }
        return reversedDiGraph;
    }

    /**
     * apartado B
     */
    public static <V> DiGraph<V> restrictDiGraph(DiGraph<V> g, Set<V> vs) {
        DiGraph<V> restrictedGraph = new DictionaryDiGraph<>();

        for (V vertex : vs) {
            restrictedGraph.addVertex(vertex);
        }
        for (V vertex : vs) {
            for (V successor : g.successors(vertex)) {
                if (vs.isElem(successor)) {
                    restrictedGraph.addDiEdge(vertex, successor);
                }
            }
        }
        return restrictedGraph;
    }

    /**
     * apartado C
     */
    public static <V> Set<V> sccOf(DiGraph<V> g, V src) {
        Set<V> vs = iterableToSet(new DepthFirstTraversal<>(g, src).vertices());
        DiGraph<V> gr = restrictDiGraph(g, vs);
        gr = reverseDiGraph(gr);

        return iterableToSet(new DepthFirstTraversal<>(gr, src).vertices());
    }


    /**
     * apartado D
     */
    public static <V> Set<Set<V>> stronglyConnectedComponentsDiGraph(DiGraph<V> graph) {
        Set<Set<V>> components = new HashSet<>();

        for (V vertex : graph.vertices()) {
            if (graph.vertices().isElem(vertex)) {
                Set<V> aux = sccOf(graph, vertex);
                components.insert(aux);

                for (V w : aux) {
                    graph.vertices().delete(w);
                }
            }
        }
        return components;
    }

    static <V> Set<V> iterableToSet(Iterable<V> it) {
        Set<V> set = new HashSet<>();
        for (V v : it)
            set.insert(v);
        return set;
    }

} // class