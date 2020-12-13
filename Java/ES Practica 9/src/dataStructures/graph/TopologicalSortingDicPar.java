/**
 * APELLIDOS : ..........................  NOMBRE: ............
 * <p>
 * TITULACION: ..............................
 * <p>
 * Computes Topological Sorting for DiGraphs
 */

package dataStructures.graph;

import dataStructures.dictionary.Dictionary;
import dataStructures.dictionary.HashDictionary;
import dataStructures.list.List;
import dataStructures.list.ArrayList;
import dataStructures.set.Set;
import dataStructures.set.HashSet;

public class TopologicalSortingDicPar<V> {

    private List<Set<V>> topSort;
    private boolean hasCycle;

    public TopologicalSortingDicPar(DiGraph<V> graph) {

        topSort = new ArrayList<>();
        // dictionary: vertex -> # of pending predecessors
        Dictionary<V, Integer> pendingPredecessors = new HashDictionary<>();

        // Sets
        Set<V> sources = new HashSet<>();
        Set<V> nSources = new HashSet<>();

        // completar
        for (V vertex : graph.vertices()) {
            pendingPredecessors.insert(vertex, graph.inDegree(vertex));
        }// Diccionario inicializado

        while ((!pendingPredecessors.isEmpty() || !sources.isEmpty()) && !hasCycle()) {
            for (V key : pendingPredecessors.keys()) {
                if (pendingPredecessors.valueOf(key) == 0) {
                    nSources.insert(key);
                }
            }

            while (!nSources.isEmpty()) {
                V primero = nSources.iterator().next();
                nSources.delete(primero);
                pendingPredecessors.delete(primero);
                sources.insert(primero);
            }

            hasCycle = sources.isEmpty() && !pendingPredecessors.isEmpty();
            Set<V> setSources = new HashSet<>();
            topSort.append(setSources);

            if (!sources.isEmpty()) {
                V v = sources.iterator().next();
                setSources.insert(v);

                for (V successor : graph.successors(v)) {
                    pendingPredecessors.insert(successor, graph.inDegree(successor) - 1);
                }
                sources.delete(v);
            }
        }

    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public List<Set<V>> order() {
        return hasCycle ? null : topSort;
    }

}
