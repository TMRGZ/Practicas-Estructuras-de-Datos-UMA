/**
 * Student's name:
 * Student's group:
 * <p>
 * Data Structures. Grado en Informática. UMA.
 */

package dataStructures.graph;

import dataStructures.list.*;

public class EulerianCycle<V> {
    private List<V> eCycle;

    @SuppressWarnings("unchecked")
    public EulerianCycle(Graph<V> g) {
        Graph<V> graph = (Graph<V>) g.clone();
        eCycle = eulerianCycle(graph);
    }

    public boolean isEulerian() {
        return eCycle != null;
    }

    public List<V> eulerianCycle() {
        return eCycle;
    }

    // J.1
    private static <V> boolean isEulerian(Graph<V> g) {
        boolean res = true;

        for (V vertice : g.vertices()) {
            if (g.degree(vertice) % 2 != 0) res = false;
        }
        return res;
    }

    // J.2
    private static <V> void remove(Graph<V> g, V v, V u) {
        g.deleteEdge(v, u);

        for (V vertice : g.vertices()) {
            if (g.degree(vertice) == 0) {
                g.deleteVertex(vertice);
            }
        }
    }

    // J.3
    private static <V> List<V> extractCycle(Graph<V> g, V v0) {
        List<V> ciclo = new ArrayList<>();
        ciclo.append(v0);
        V v = v0;

        while (!g.successors(v).isElem(v0) && g.successors(v).iterator().hasNext()) {
            V u = g.successors(v).iterator().next();
            ciclo.append(u);
            remove(g, v, u);
            v = u;
        }

        remove(g, v, v0);
        ciclo.append(v0);

        return ciclo;
    }

    // J.4
    private static <V> void connectCycles(List<V> xs, List<V> ys) {
        if (xs.isEmpty()) {
            xs = ys;
        } else {
            int i = 0;
            int j = 1;

            if (xs.get(i).equals(ys.get(0))) {
                i++;
            }
            i++;

            while (j < ys.size()) {
                xs.insert(i, ys.get(j));
                i++;
                j++;
            }
        }
    }

    // J.5
    private static <V> V vertexInCommon(Graph<V> g, List<V> cycle) {
        boolean res = false;
        int i = 0;
        V v = cycle.get(0);

        while (i < cycle.size() && !res) {
            if (g.vertices().isElem(cycle.get(i))) {
                v = cycle.get(i);
                res = true;
            }
            i++;
        }
        return v;
    }

    // J.6
    private static <V> List<V> eulerianCycle(Graph<V> g) {
        if (isEulerian(g)) {
            V v;
            List<V> ciclo = extractCycle(g, g.vertices().iterator().next());

            while (!g.isEmpty()) {
                v = vertexInCommon(g, ciclo);
                List<V> ciclo2 = extractCycle(g, v);
                connectCycles(ciclo, ciclo2);
            }
            return ciclo;

        }
        return null;
    }
}
