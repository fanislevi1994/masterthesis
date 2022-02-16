/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes.classesOperation.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author fanis
 */
public class Triplet<T, U> {

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer>> list
                = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    private final T node;
    private final U code;
    ArrayList<String> nodeArray = new ArrayList<String>();
    ArrayList<Integer> codeArray = new ArrayList<Integer>();
    HashMap<String, Integer> nodesCodesMap = new HashMap<String, Integer>();

    public Triplet(T node, U code) {
        this.node = node;
        this.code = code;
    }

    public void addElements(T node, U code) {
        nodeArray.add((String) node);
        codeArray.add((Integer) code);
        nodesCodesMap.put((String) node, (Integer) code);
    }

    public void printElements() {
        Map<String, Integer> hm1 = sortByValue(nodesCodesMap);
        printMap(hm1);
        System.out.println("\n");
    }

    public void printMap(Map<String, Integer> map) {
        for (Entry<String, Integer> entry : map.entrySet()) {
            //  System.out.println("caalled prins   innnnn");
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("\n");
    }
}
