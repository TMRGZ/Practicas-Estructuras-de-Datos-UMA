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
        Set<V> sources = new HashSet<>();
        Set<V> nSources = new HashSet<>();

        // dictionary: vertex -> # of pending predecessors
        Dictionary<V, Integer> pendingPredecessors = new HashDictionary<>();

        /////////////////////////////////

        //Inicializar
        for(V vertice : graph.vertices()){
            pendingPredecessors.insert(vertice, graph.inDegree(vertice));
        }//Inicializado

        while(((!pendingPredecessors.isEmpty()) || !sources.isEmpty()) && !hasCycle){       // Mientras pendientes o sources no esten vacios y no haya ciclo
            for(V vertice : pendingPredecessors.keys()){
                if(pendingPredecessors.valueOf(vertice) == 0)  nSources.insert(vertice);
            } // Meter en nSources

            while(!nSources.isEmpty()){                     // Mientras nSources no este vacio
                V vertice = nSources.iterator().next();     // Coger siguiente
                nSources.delete(vertice);                   // Borrarlo de nSources
                pendingPredecessors.delete(vertice);        // Borrarlo de pendientes
                sources.insert(vertice);                    // Meterlo en sources
            } // Movidos a sources

            hasCycle = sources.isEmpty() && !pendingPredecessors.isEmpty(); // Es ciclico?
            Set<V> setSource = new HashSet<>();                             // Set de sources
            topSort.append(setSource);                                      // Meter en topSort

            while(!sources.isEmpty()){                                                          // Mientras sources no este vacio
                V vertice = sources.iterator().next();                                          // Coger siguiente
                setSource.insert(vertice);                                                      // Meter vertice en set

                for(V suc : graph.successors(vertice)){                                         // Para cada sucesor de un vertice
                    pendingPredecessors.insert(suc, pendingPredecessors.valueOf(suc) - 1);   // Meter sucesor con el valor asociado
                }
                sources.delete(vertice);                                                        // Borrarlo de sources
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
