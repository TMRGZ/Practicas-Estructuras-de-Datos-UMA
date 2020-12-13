/**
 * @author Blas Ruiz, Data Structures, Grado en Inform�tica. UMA.
 *
 * Control 2. 13-Febrero-2012
 * Estudio de grafos bipartitos por coloreado con b�squeda en profundidad
 */

package graph;

import dictionary.Dictionary;
import dictionary.HashDictionary;
import stack.Stack;
import stack.StackList;


public class BiPartite<V> {
	
	public enum Color {Red, Blue;
	}

	private static Color nextColor(Color c) {
		return (c == Color.Blue) ?Color.Red:Color.Blue; 
	}
	
	private Stack<Pair<V,Color>> stack; // stack with pair of vertex and color
	private Dictionary<V,Color> dict; // dictionary: Vertices -> Color
	private boolean isBiColored;

	public BiPartite(Graph<V> graph) {
		dict = new HashDictionary<>();
		stack = new StackList<>();
		isBiColored = true;
		if (graph.numVertices() == 0)
			return; 

		V src = graph.vertices().iterator().next(); // initial vertex
		
		stack.push(new Pair<>(src,Color.Red));
		
		while (!stack.isEmpty() && isBiColored) {
			Pair<V,Color> vColor = stack.top();
			stack.pop();

			if(dict.valueOf(vColor.first()) == null){
				dict.insert(vColor.first(), vColor.second());

				for(V v : graph.successors(vColor.first())){
					if(dict.valueOf(v) == null) 	stack.push(new Pair<>(v, nextColor(vColor.second())));
				}

			}else if(dict.valueOf(vColor.first()) != vColor.second()) isBiColored = false;
		} 
	}	
	
	public Dictionary<V,Color> biColored() {
		return dict;
	}
	
	public boolean isBicolored() {
		return isBiColored;
	}
	
}
