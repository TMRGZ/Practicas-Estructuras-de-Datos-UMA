package dataStructures.graph; /** ------------------------------------------------------------------------------
  * Estructuras de Datos. 2º Curso. ETSI Informática. UMA
  *
  * Control del día 4-9-2013
  * 
  * Diámetro de un grafo conexo 
  *
  * (completa y sustituye los siguientes datos)
  * Titulación: Grado en Ingeniería …………………………………… [Informática | del Software | de Computadores].
  *
  * Alumno: APELLIDOS, NOMBRE
  *
  * -------------------------------------------------------------------------------
  */

public class GraphUtil {

	/**
	 * LENGTH: Calcula el número de elementos que contiene un iterable
	 * 
	 * @param it  El iterador
	 * @return   Número de elementos en el iterador
	 */
	public static <T> int length(Iterable<T> it) {
		int l=0;
		for(T t : it) l++;
	    return l;
	}

	/**
	 * ECCENTRICITY: Calcula la excentricidad de un vértice en un grafo El algoritmo toma la
	 * longitud del camino máximo en un recorrido en profundidad del grafo
	 * comenzando en el vértice dado.
	 * 
	 * @param graph    Grafo
	 * @param v        Vértice del grafo
	 * @return         Excentricidad del vértice
	 */
	public static <T> int eccentricity(Graph<T> graph, T v) {
		Traversal<Integer> bfs = new BreadthFirstTraversal(graph, v);
		int max = 0;

		for(Iterable<Integer> i : bfs.paths()){
			if(length(i) > max) max = length(i);
		}

		return max;
	}

	/**
	 * DIAMETER: Se define como la máxima excentricidad de los vértices del grafo.
	 * 
	 * @param graph
	 * @return
	 */

	public static <T> int diameter(Graph<T> graph) {
		int max =0;

		for(T v : graph.vertices()){
			int nMax = eccentricity(graph, v);
			if(nMax > max)  max = nMax;
		}

	    return max;
	}
	
	/** 
	 * Estima y justifica la complejidad del método diameter
	 */
}
