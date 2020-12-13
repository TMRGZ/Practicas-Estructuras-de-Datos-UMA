package dataStructures.graph;

import dataStructures.set.Set;
import dataStructures.set.HashSet;

public class SCCDiGraph {

    /*
     * apartado A
     */
    public static <V> DiGraph<V> reverseDiGraph(DiGraph<V> g){
        DiGraph<V> reversedDiGraph = new DictionaryDiGraph<V>();
        // . . .
        return reversedDiGraph;
    }

    /*
     * apartado B
     */
    public static <V> DiGraph<V> restrictDiGraph(DiGraph<V> g, Set<V> vs){

        DiGraph<V> restrictedGraph = new DictionaryDiGraph<V>();
        // . . .
        return restrictedGraph;
    }

    /*
     * apartado C
     */
    public static <V> Set<V> sccOf (DiGraph<V>g, V src) {
        // . . .
        return null;
    }


    /*
     * apartado D
     */
    public static <V> Set<Set<V>> stronglyConnectedComponentsDiGraph(DiGraph<V> graph) {
        Set<Set<V>> components  = new HashSet<Set<V>>();
        // ...
        return components;
    }

    static <V> Set<V> iterableToSet(Iterable<V> it) {
        Set<V> set = new HashSet<V>();
        for(V v : it)
            set.insert(v);
        return set;
    }

} // class