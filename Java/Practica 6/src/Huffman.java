
/**
 * Huffman trees and codes.
 *
 * Data Structures, Grado en Informatica. UMA.
 *
 *
 * Student's name:
 * Student's group:
 */

import dataStructures.dictionary.AVLDictionary;
import dataStructures.dictionary.Dictionary;
import dataStructures.list.ArrayList;
import dataStructures.list.LinkedList;
import dataStructures.list.List;
import dataStructures.priorityQueue.BinaryHeapPriorityQueue;
import dataStructures.priorityQueue.PriorityQueue;
import dataStructures.tuple.Tuple2;

public class Huffman {

    // Exercise 1
    public static Dictionary<Character, Integer> weights(String s) {
        Dictionary<Character, Integer> dic = new AVLDictionary<>();

        for(char c : s.toCharArray()){
            if(dic.isDefinedAt(c)) dic.insert(c, 1 + dic.valueOf(c));
            else dic.insert(c, 1);
        }
        return dic;
    }

    // Exercise 2.a
    public static PriorityQueue<WLeafTree<Character>> huffmanLeaves(String s) {
        PriorityQueue<WLeafTree<Character>> cola = new BinaryHeapPriorityQueue<>();
        Dictionary<Character, Integer> dic = weights(s);

        for(Tuple2<Character, Integer> par : dic.keysValues()){
            cola.enqueue(new WLeafTree<>(par._1(), par._2()));
        }
        return cola;
    }

    // Exercise 2.b
    public static WLeafTree<Character> huffmanTree(String s) {
        WLeafTree<Character> arbol;
        WLeafTree primero;

        if(!hayAlMenosDos(s)) throw new HuffmanException("No hay dos o mas caracteres distintos en s");
        else{
            PriorityQueue<WLeafTree<Character>> cola = huffmanLeaves(s);

            primero = cola.first();
            cola.dequeue();

            while(!cola.isEmpty()){

                WLeafTree<Character> segundo = cola.first();
                cola.dequeue();

                arbol = new WLeafTree<Character>(primero, segundo);
                cola.enqueue(arbol);
                primero = cola.first();
                cola.dequeue();
            }
       }
    	return primero;
    }

    private static boolean hayAlMenosDos(String s) {
        int i =1;

        if(!s.isEmpty()){
            char c = s.charAt(0);
            while(i<s.length() && s.charAt(i) == c) i++;
        }
        return i < s.length();
    }

    // Exercise 3.a
    public static Dictionary<Character, List<Integer>> joinDics(Dictionary<Character, List<Integer>> d1, Dictionary<Character, List<Integer>> d2) {
        AVLDictionary<Character, List<Integer>> dic = new AVLDictionary<>();

        for(Tuple2<Character, List<Integer>> par : d1.keysValues()) dic.insert(par._1(), par._2());
        for(Tuple2<Character, List<Integer>> par : d2.keysValues()) dic.insert(par._1(), par._2());

    	return dic;
    }

    // Exercise 3.b
    public static Dictionary<Character, List<Integer>> prefixWith(int i, Dictionary<Character, List<Integer>> d) {
        for(List<Integer> lista : d.values()) lista.prepend(i);

    	return d;
    }

    // Exercise 3.c
    public static Dictionary<Character, List<Integer>> huffmanCode(WLeafTree<Character> ht) {
        Dictionary<Character, List<Integer>> dic;
        if(ht.isLeaf()){
            dic = new AVLDictionary<>();
            dic.insert(ht.elem(), new LinkedList<>());
            return dic;
        }else{
            dic = joinDics(prefixWith(0, huffmanCode(ht.leftChild())), prefixWith(1, huffmanCode(ht.rightChild())));
        }

        return  dic;
    }

    // Exercise 4
    public static List<Integer> encode(String s, Dictionary<Character, List<Integer>> hc) {
        ArrayList<Integer> solList = new ArrayList<>();

        for(char c : s.toCharArray()) for (int i : hc.valueOf(c)) solList.append(i);

    	return solList;
    }

    // Exercise 5
    public static String decode(List<Integer> bits, WLeafTree<Character> ht) {
        int i=0;
        StringBuilder sb = new StringBuilder();
        WLeafTree<Character> copia = ht;

        while(i<bits.size()){
            if(bits.get(i)==0){
                copia = copia.leftChild();
                if(copia.isLeaf()){
                    sb.append(copia.elem());
                    copia = ht;
                }
            }else{
                copia = copia.rightChild();
                if(copia.isLeaf()){
                    sb.append(copia.elem());
                    copia = ht;
                }
            }
            i++;
        }
    	return sb.toString();
    }
}
