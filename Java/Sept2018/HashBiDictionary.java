package dataStructures.dictionary;
import dataStructures.list.List;

import java.util.Iterator;

import dataStructures.list.ArrayList;
import dataStructures.list.LinkedList;
import dataStructures.set.AVLSet;
import dataStructures.set.Set;
import dataStructures.tuple.Tuple2;

/**
 * Estructuras de Datos. Grados en Informatica. UMA.
 * Examen de septiembre de 2018.
 *
 * Apellidos, Nombre:
 * Titulacion, Grupo:
 */
public class HashBiDictionary<K,V> implements BiDictionary<K,V>{
	private Dictionary<K,V> bKeys;
	private Dictionary<V,K> bValues;
	
	public HashBiDictionary() {
		bKeys = new HashDictionary<>();
		bValues = new HashDictionary<>();
	}
	
	public boolean isEmpty() {
		return bKeys.isEmpty() && bValues.isEmpty();
	}
	
	public int size() {
		return bKeys.size();
	}
	
	public void insert(K k, V v) {
		bKeys.insert(k, v);
		bValues.insert(v, k);
	}
	
	public V valueOf(K k) {
		return bKeys.valueOf(k);
	}
	
	public K keyOf(V v) {
		return bValues.valueOf(v);
	}
	
	public boolean isDefinedKeyAt(K k) {
		return bKeys.isDefinedAt(k);
	}
	
	public boolean isDefinedValueAt(V v) {
		return bValues.isDefinedAt(v);
	}
	
	public void deleteByKey(K k) {
		bKeys.delete(k);
		Dictionary<V,K> aux = new HashDictionary<>();
		
		for (Tuple2<K, V> tupla : bKeys.keysValues()) {
			aux.insert(tupla._2(), tupla._1());
		}
		
		bValues = aux;
	}
	
	public void deleteByValue(V v) {
		bValues.delete(v);
		
		Dictionary<K, V> aux = new HashDictionary<>();
		
		for (Tuple2<V, K> tupla : bValues.keysValues()) {
			aux.insert(tupla._2(), tupla._1());
		}
		bKeys = aux;
	}
	
	public Iterable<K> keys() {
		return bKeys.keys();
	}
	
	public Iterable<V> values() {
		return bValues.keys();
	}
	
	public Iterable<Tuple2<K, V>> keysValues() {
		return bKeys.keysValues();
	}
	
		
	public static <K,V extends Comparable<? super V>> BiDictionary<K, V> toBiDictionary(Dictionary<K,V> dict) {
		boolean noIny = false;
	
		if(noIny) {
			throw new IllegalArgumentException();
		}
		
		Dictionary<V,K> dictINV = new HashDictionary<>();
		
		for (Tuple2<K, V> tupla : dict.keysValues()) {
			dictINV.insert(tupla._2(), tupla._1());
		}
		
		HashBiDictionary<K, V> res = new HashBiDictionary<>();
		res.bKeys = dict;
		res.bValues = dictINV;
		
		return res;
	}
	

	public <W> BiDictionary<K, W> compose(BiDictionary<V,W> bdic) {
		List<K> listaComunes = new LinkedList<>();
		
		for (K key : this.keys()) {
			if (bdic.isDefinedKeyAt(this.valueOf(key))) {
				listaComunes.append(key);
			}
		}
		
		
		BiDictionary<K, W> res = new HashBiDictionary<>();
		for (K k : listaComunes) {
			res.insert(k, bdic.valueOf(this.valueOf(k)));
		}
		
		
		return res;
	}
		
	public static <K extends Comparable<? super K>> boolean isPermutation(BiDictionary<K,K> bd) {
		boolean res = false;
		Iterator<K> itv = bd.values().iterator();
		Iterator<K> itk = bd.keys().iterator();
		
		
		while (itv.hasNext()) {
			res = false;
			
			while(itk.hasNext() && !res) {
				if (itv.next().equals(itk.next())) {
					res = true;
				}
			}
		}
		
		return res;
	}
	
	// Solo alumnos con evaluación por examen final.
    // =====================================
	
	public static <K extends Comparable<? super K>> List<K> orbitOf(K k, BiDictionary<K,K> bd) {
		// TODO
		return null;
	}
	
	public static <K extends Comparable<? super K>> List<List<K>> cyclesOf(BiDictionary<K,K> bd) {
		// TODO
		return null;
	}

    // =====================================
	
	
	@Override
	public String toString() {
		return "HashBiDictionary [bKeys=" + bKeys + ", bValues=" + bValues + "]";
	}
	
	
}
