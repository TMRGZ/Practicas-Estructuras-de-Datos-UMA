/**
 * APELLIDOS : ..........................  NOMBRE: ............
 *
 * TITULACION: ..............................
 *
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
        Queue<V> sources = new LinkedQueue<>();
        Queue<V> nSources = new LinkedQueue<>();

        // dictionary: vertex -> # of pending predecessors
        Dictionary<V, Integer> pendingPredecessors = new HashDictionary<>();

        ///////////////////////////////////////////

        // Inicializar
        for(V vertice : graph.vertices()){
            pendingPredecessors.insert(vertice, graph.inDegree(vertice));
        }//Inicializado

        while(((!pendingPredecessors.isEmpty()) || !sources.isEmpty()) && !hasCycle){       // Mientras pendientes o sources no esten vacios y no haya ciclo
            for(V vertice : pendingPredecessors.keys()) {
                if(pendingPredecessors.valueOf(vertice) == 0) nSources.enqueue(vertice);
            }   // Meter vertice en nSources

            while(!nSources.isEmpty()) {                // Mientras nSources no este vacio
                V vertice = nSources.first();           // Coger siguiente
                nSources.dequeue();                     // Quitarlo de nSources
                pendingPredecessors.delete(vertice);    // Quitarlo de pendientes
                sources.enqueue(vertice);               // Meterlo en sources
            } // Movidos a sources

            hasCycle = sources.isEmpty() && !pendingPredecessors.isEmpty(); // Es ciclico?

            if(!sources.isEmpty()) {                                                                // Mientras sources no este vacio
                V vertice = sources.first();                                                        // Coger siguiente
                topSort.append(vertice);                                                            // Meterlo en topSort

                for(V suc : graph.successors(vertice)) {                                            // Para cada sucesor
                    pendingPredecessors.insert(suc, pendingPredecessors.valueOf(suc)-1);         // Meter sucesor con el valor asociado
                }
                sources.dequeue();                                                                  // Quitarlo de sources
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
