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
import dataStructures.list.ArrayList;
import dataStructures.list.List;
import dataStructures.queue.LinkedQueue;
import dataStructures.queue.Queue;
import dataStructures.set.HashSet;
import dataStructures.set.Set;


public class TopologicalSortingDic<V> {

    private List<V> topSort;
    private boolean hasCycle;

    public TopologicalSortingDic(DiGraph<V> graph) {

        topSort = new ArrayList<>();
        // dictionary: vertex -> # of pending predecessors
        Dictionary<V, Integer> pendingPredecessors = new HashDictionary<>();

        // Colas
        Queue<V> sources = new LinkedQueue<>();
        Queue<V> nSources = new LinkedQueue<>();

        for (V vertex : graph.vertices()) {
            pendingPredecessors.insert(vertex, graph.inDegree(vertex));
        }// Diccionario inicializado

        while ((!pendingPredecessors.isEmpty() || !sources.isEmpty()) && !hasCycle()) {
            for (V v : pendingPredecessors.keys()) {
                if (pendingPredecessors.valueOf(v) == 0) {
                    nSources.enqueue(v);
                }
            } // Seleccionar fuentes del diccionario

            while (!nSources.isEmpty()) {
                V primero = nSources.first();
                nSources.dequeue();
                pendingPredecessors.delete(primero);
                sources.enqueue(primero);
            } // Eliminar fuentes del diccionario

            hasCycle = sources.isEmpty() && !pendingPredecessors.isEmpty();

            if (!sources.isEmpty()) {
                V v = sources.first();
                topSort.append(v);

                for (V successor : graph.successors(v)) {
                    pendingPredecessors.insert(successor, pendingPredecessors.valueOf(successor) - 1);
                }
                sources.dequeue();
            }

        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public List<V> order() {
        return hasCycle ? null : topSort;
    }
}
