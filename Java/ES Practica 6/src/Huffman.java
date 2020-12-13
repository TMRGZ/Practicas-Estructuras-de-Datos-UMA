
/**
 * Huffman trees and codes.
 * <p>
 * Data Structures, Grado en Informatica. UMA.
 * <p>
 * <p>
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
        //to do
        Dictionary<Character, Integer> dic = new AVLDictionary<>();

        for (Character c : s.toCharArray()) {
            if (dic.isDefinedAt(c)) {
                dic.insert(c, dic.valueOf(c) + 1);
            } else {
                dic.insert(c, 1);
            }
        }

        return dic;
    }

    // Exercise 2.a
    public static PriorityQueue<WLeafTree<Character>> huffmanLeaves(String s) {
        //to do
        PriorityQueue<WLeafTree<Character>> cola = new BinaryHeapPriorityQueue<>();
        Dictionary<Character, Integer> dic = weights(s);

        for (Tuple2<Character, Integer> tupla : dic.keysValues()) {
            cola.enqueue(new WLeafTree<>(tupla._1(), tupla._2()));
        }
        return cola;
    }

    // Exercise 2.b
    public static WLeafTree<Character> huffmanTree(String s) {
        //to do
        Dictionary<Character, Integer> dic = weights(s);
        PriorityQueue<WLeafTree<Character>> cola = huffmanLeaves(s);
        int sz = 0;
        WLeafTree<Character> primero;

        for (Character key : dic.keys()) {
            sz++;
        }

        if (sz < 2) {
            throw new HuffmanException("Menos de 2 caracteres distintos");
        } else {
            primero = cola.first();
            cola.dequeue();

            while (!cola.isEmpty()) {
                WLeafTree<Character> aux = cola.first();
                cola.dequeue();

                WLeafTree<Character> union = merge(primero, aux);
                cola.enqueue(union);
                primero = cola.first();
                cola.dequeue();
            }
        }

        return primero;
    }

    public static WLeafTree<Character> merge(WLeafTree<Character> l, WLeafTree<Character> r) {
        return new WLeafTree<>(l, r);
    }

    // Exercise 3.a
    public static Dictionary<Character, List<Integer>> joinDics(Dictionary<Character, List<Integer>> d1, Dictionary<Character, List<Integer>> d2) {
        //to do
        for (Tuple2<Character, List<Integer>> pareja : d2.keysValues()) {
            d1.insert(pareja._1(), pareja._2());
        }

        return d1;
    }

    // Exercise 3.b
    public static Dictionary<Character, List<Integer>> prefixWith(int i, Dictionary<Character, List<Integer>> d) {
        //to do
        for (List<Integer> lista : d.values()) {
            lista.prepend(i);
        }
        return d;
    }

    // Exercise 3.c
    public static Dictionary<Character, List<Integer>> huffmanCode(WLeafTree<Character> ht) {
        //to do
        Dictionary<Character, List<Integer>> dic = new AVLDictionary<>();

        if (ht.isLeaf()) {
            dic.insert(ht.elem(), new LinkedList<>());
            return dic;

        } else {
            return joinDics(prefixWith(0, huffmanCode(ht.leftChild())), prefixWith(1, huffmanCode(ht.rightChild())));
        }
    }

    // Exercise 4
    public static List<Integer> encode(String s, Dictionary<Character, List<Integer>> hc) {
        //to do
        List<Integer> res = new ArrayList<>();

        for (Character c : s.toCharArray()) {
            for (Integer i : hc.valueOf(c)) {
                res.append(i);
            }
        }
        return res;
    }

    // Exercise 5
    public static String decode(List<Integer> bits, WLeafTree<Character> ht) {
        StringBuilder sb = new StringBuilder();
        WLeafTree<Character> ref = ht;

        for (int i = 0; i < bits.size(); i++) {
            ref = bits.get(i) == 0 ? ref.leftChild() : ref.rightChild();

            if (ref.isLeaf()) {
                sb.append(ref.elem());
                ref = ht;
            }
        }

        return sb.toString();
    }
}
