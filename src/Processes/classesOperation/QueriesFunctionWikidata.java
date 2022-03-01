/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes.classesOperation;

//import Processes.classesOperation.utils.TableValues;
//import Processes.classesOperation.utils.Employee;
import static Processes.classesOperation.QueriesFunctionDBpedia.encoderDikstra;
import static Processes.classesOperation.QueriesFunctionWikidata.tableValues;
import Processes.classesOperation.utils.SubjectObjectPredicate;
import Processes.classesOperation.utils.TableFrequencys;
import Processes.classesOperation.utils.TablePaths;
import Processes.classesOperation.utils.TableValues;
import Processes.classesOperation.utils.TableValuesDatas;
import Processes.classesOperation.utils.Triplet;
import static Processes.classesOperation.utils.Triplet.sortByValue;
import Processes.classesOperation.utils.TripletValues;
import info.aduna.iteration.Iterations;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.util.Pair;
import javax.security.auth.Subject;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Graph;
import org.openrdf.model.Graph;
import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.GraphImpl;
import org.openrdf.model.util.GraphUtil;
import org.openrdf.model.util.Literals;
import org.openrdf.model.vocabulary.FOAF;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.query.BindingSet;
import org.openrdf.query.BindingSet;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandler;
import org.openrdf.query.Update;
import org.openrdf.query.UpdateExecutionException;
import org.openrdf.query.algebra.Projection;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.helpers.StatementPatternCollector;
import org.openrdf.query.parser.ParsedQuery;
import org.openrdf.query.parser.QueryParser;
import org.openrdf.query.parser.sparql.SPARQLParserFactory;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;
import virtuoso.sesame2.driver.VirtuosoRepository;

// Java program to find shortest path in an undirected
// graph
/**
 *
 * @author fanis
 */
public class QueriesFunctionWikidata implements Comparator<QueriesFunctionWikidata> {

    //dikstra start
    // function to form edge between two vertices
    // source and dest
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
    private String e;

    public void dikstraSF(ArrayList<String> object, ArrayList<String> propertyListV_D_nodeS, ArrayList<String> subject, int sources) {
      //  int source =0;

        // System.out.println("sizes are "+object.size()+"--"+subject.size()+"--"+propertyListV_D_node.size());
        int v = 1011;// subject.size();
        ArrayList<ArrayList<Integer>> adj
                = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }
        ArrayList<String> paths = new ArrayList<String>();
        int x = 0;
        int y = 0;
        int z = 0;

        String a = null, b = null, c = null;
        for (int i = 0; i < subjectList_D_node.size(); i++) {

            if (encoderDikstra.size() != 0) {
                for (Map.Entry<Integer, String> entry : encoderDikstra.entrySet()) {
                    //  System.out.println("varss "+objectList_D_node);

                    if (objectList_D_node.get(i).equals(entry.getValue())) {
                        x = entry.getKey();
                        a = entry.getValue();
                    }

                    if (subjectList_D_node.get(i).equals(entry.getValue())) {
                        y = entry.getKey();
                        c = entry.getValue();
                    }

                    addEdge(adj, x, y);

                    if ((x != 0 && y != 0) || (x != 0 || y != 0) || (x != 0 || y == 0)) {
                        //System.out.println("addEdge"+x+","+y);
                        // addEdge(adj, x, y);

                    }

                    //  }
                }

            }

            // }
        }

        //System.out.println("Sizes are "+subjectList_D_node.size()+" 2 "+propertyListV_D_node.size()+" 3 "+objectList_D_node.size());
        for (int g = 0; g < subjectList_D_node.size(); g++) {

            if ((subjectList_D_node.size() == propertyListV_D_node.size()) && (propertyListV_D_node.size() == objectList_D_node.size())) {
                tripletsValues.add(new TripletValues(subjectList_D_node.get(g), propertyListV_D_node.get(g), objectList_D_node.get(g)));
                //System.out.println("hierr aaa");
            }

        }

        // for (int i = 0; i < moreSemantic.size(); i++) {
        for (Map.Entry<Integer, String> entry : encoderDikstra.entrySet()) {

            //  System.out.println(entry.getKey()+"aaa "+entry.getValue());
            //  if(!entry.getKey().equals("null")){
            // System.out.println("sem is "+moreSemantic.get(2));
            if (entry.getValue().equals(moreSemantic.get(gloVar))) {//4 beauty
                gathercode.add(entry.getKey());
                int dest = entry.getKey();
                printShortestDistance(adj, sources, dest, v);
                //  }
            }
        }//}

    }
    static int allc = 0;

    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
            int dest, int v, int pred[], int dist[]) {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    // stopping condition (when we find
                    // our destination)
                    if (adj.get(u).get(i) == dest) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    static String values = ",", ss = ",";
    static int lengthpath = 0;

    // function to print the shortest distance and path
    // between source vertex and destination vertex
    private static void printShortestDistance(
            ArrayList<ArrayList<Integer>> adj,
            int s, int dest, int v) {
        Triplet<String, Integer> valuesSort = new Triplet<>("", 1);
        ArrayList<TableValues> allValues = new ArrayList<TableValues>();
        ArrayList<String> test = new ArrayList<String>();
        HashMap<String, String> tempPath = new HashMap<String, String>();
        values = ",";
        lengthpath = 0;
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];

        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination"
                    + "are not connected");
            return;
        }

        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
        //System.out.println("\n Shortest path length is: " + dist[dest]);
        lengthpath = dist[dest];
        String append = "";
        String append2 = "";
        String fin = "";
        // System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print("\n" + path.get(i) + " ");
            append = append + path.get(i) + " ";

        }

        allValues.add(new TableValues(append, String.valueOf(dist[dest]), 1, ""));
        //System.out.println("decode append is "+append);
        for (Map.Entry<Integer, String> entry : encoderDikstra.entrySet()) {
            for (int i = path.size() - 1; i >= 0; i--) {
                // if (!entry.getValue().equals("null")) {  
                //  System.out.println("size encoder is "+entry.getKey() +" path is "+path.get(i));
                if (entry.getKey().equals(path.get(i))) {

                    System.out.print(" <-> " + entry.getValue());
                    // pairs.add(entry.getValue());
                    append2 = append2 + entry.getValue() + " ";

                }
               // test.add(append2);
                // tempPath.put(append2, "");
                // System.out.println("temp path is "+test);

                // }
                // append2=append2.replaceAll(",", "\",\"");
            }
            test.add(append2);

            // System.out.println(append2);
            tempPath.put(append2, "");
            append2 = "";
        }

        glob = glob + 1;
        // System.out.println("globbbsss " + glob);
        // System.out.println(" \n encode is " + append);
        // System.out.println("append is " + tempPath + " similar " + append);
        // System.out.println("tempPath " + test);
        pairsvValues.put(append, dist[dest]);
        tableValues.add(new TableValues(append, String.valueOf(dist[dest]), glob, String.valueOf(tempPath)));

        /* for (Map.Entry<String, String> entry : tempPath.entrySet()) {
         if(!entry.getKey().equals("")){
         System.out.println(" entry is "+entry.getKey());
                 
         }
             
             
         }*/
        //    System.out.println("decode is "+append2);
        //  System.out.println(" is "+pairs.get(0));
    }

    public void getFrequency(ArrayList<TableValues> table) {

    }

    // function to print the shortest distance and path
    // between source vertex and destination vertex
    private static void printShortestDistance2(
            ArrayList<ArrayList<Integer>> adj,
            int s, int dest, int v) {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];

        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination"
                    + "are not connected");
            return;
        }

        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        // Print distance
        System.out.println("Shortest path length is: " + dist[dest]);

        // Print path
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    public static void writeToFile() {
        //  System.out.println("\nfile is -=====");
        pairs.add("");
        for (int i = 1; i < pairs.size() - 1; i++) {

            // System.out.println("pairs are "+pairs.get(i-1)+"-"+pairs.get(i));
            System.out.println("<" + pairs.get(i - 1) + "> " + "<>" + " <" + pairs.get(i) + ">.");

        }

        //  for(int i=0;i<object.size();i++){
        // System.out.println("<"+object.get(i)+">"+"<"+propertyListV_D_node.get(i)+">"+" <"+subject.get(i)+">.");
        //}
        pairs.clear();

    }
    //dikstra end

    String decoder;
    static int glob = 0;
    int rows = 0;
    int rowsb = 0;
    int rowsc = 0;
    int rowsd = 0;
    int nodes = 0;
    int nodesb = 0;
    int nodesc = 0;
    int nodesd = 0;
    int rowsd_node = 1;
    int nodesd_node = 0;
    int rowsd_nodesubject = 0;
    int rowsd_nodeobject = 0;
    int rowsd_nodeproperty = 0;
    int mSem = 0;
    int gloVar = 0;
    static int cfa = 0;
    static int cfa2 = 0;
    String gloName = null;
    ArrayList<String> GLOBAL = new ArrayList<String>();

    HashMap<String, String> nodesMap = new HashMap<String, String>();
    HashMap<String, Integer> nodeMapFrequency = new HashMap<String, Integer>();
    ArrayList<String> nodesList = new ArrayList<String>();

    HashMap<String, String> propertyMap = new HashMap<String, String>();
    HashMap<String, Integer> propertyMapFrequency = new HashMap<String, Integer>();
    ArrayList<String> propertyList = new ArrayList<String>();

    HashMap<String, String> nodesMap_C = new HashMap<String, String>();
    HashMap<String, Integer> nodeMapFrequency_C = new HashMap<String, Integer>();
    ArrayList<String> nodesList_C = new ArrayList<String>();
    ArrayList<String> temp_C = new ArrayList<String>();
    ArrayList<String> tempAll_C = new ArrayList<String>();

    HashMap<String, String> nodesMap_C_node = new HashMap<String, String>();
    HashMap<String, Integer> nodeMapFrequency_C_node = new HashMap<String, Integer>();
    ArrayList<String> nodesList_C_node = new ArrayList<String>();
    ArrayList<String> temp_C_node = new ArrayList<String>();
    ArrayList<String> tempAll_C_node = new ArrayList<String>();

    HashMap<String, String> propertyMap_D = new HashMap<String, String>();
    HashMap<String, Integer> propertyMapFrequency_D = new HashMap<String, Integer>();
    ArrayList<String> propertyList_D = new ArrayList<String>();
    ArrayList<String> temp_D = new ArrayList<String>();
    ArrayList<String> tempAll_D = new ArrayList<String>();

    HashMap<String, String> propertyMap_D_node = new HashMap<String, String>();
    HashMap<String, Integer> propertyMapFrequency_D_node = new HashMap<String, Integer>();
    ArrayList<String> propertyList_D_node = new ArrayList<String>();
    ArrayList<String> temp_D_node = new ArrayList<String>();
    ArrayList<String> tempAll_D_node = new ArrayList<String>();

    HashMap<String, String> subjectMap_D_node = new HashMap<String, String>();
    HashMap<String, Integer> subjectMapFrequency_D_node = new HashMap<String, Integer>();
    ArrayList<String> subjectList_D_node = new ArrayList<String>();
    ArrayList<String> tempsubject_D_node = new ArrayList<String>();
    ArrayList<String> tempAllsubject_D_node = new ArrayList<String>();
    ArrayList<String> subjectList_D_node_Global = new ArrayList<String>();

    HashMap<String, String> propertyMapV_D_node = new HashMap<String, String>();
    HashMap<String, Integer> propertyMapFrequencyV_D_node = new HashMap<String, Integer>();
    ArrayList<String> propertyListV_D_node = new ArrayList<String>();
    ArrayList<String> tempropertyV_D_node = new ArrayList<String>();
    ArrayList<String> tempAllpropertyV_D_node = new ArrayList<String>();
    ArrayList<String> propertyListV_D_node_Global = new ArrayList<String>();

    HashMap<String, String> objectMap_D_node = new HashMap<String, String>();
    HashMap<String, Integer> objectMapFrequency_D_node = new HashMap<String, Integer>();
    ArrayList<String> objectList_D_node = new ArrayList<String>();
    ArrayList<String> tempobject_D_node = new ArrayList<String>();
    ArrayList<String> tempAllobject_D_node = new ArrayList<String>();
    ArrayList<String> objectList_D_node_Global = new ArrayList<String>();

    HashMap<String, String> converter = new HashMap<String, String>();

    int co3 = 0;

    /*
     propertyMap_D
     propertyList_D
     temp_D
     rowsd
     tempAll_D
     */
    HashMap<String, Integer> tempAllMap_C = new HashMap<String, Integer>();
    HashMap<String, Integer> tempAllMap_D = new HashMap<String, Integer>();
    HashMap<String, Integer> tempAllMap_D_node = new HashMap<String, Integer>();

    ArrayList<String> topElementsKeyS_A = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_A = new ArrayList<Integer>();

    ArrayList<String> topElementsKeyS_B = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_B = new ArrayList<Integer>();

    ArrayList<String> topElementsKeyS_C = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_C = new ArrayList<Integer>();

    ArrayList<String> topElementsKeyS_D = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_D = new ArrayList<Integer>();

    ArrayList<String> topElementsKeyS_D_node = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_D_node = new ArrayList<Integer>();

    //dikstra
    static HashMap<Integer, String> encoderDikstra = new HashMap<Integer, String>();
    static HashMap<String, Integer> pairsvValues = new HashMap<String, Integer>();

    // ArrayList<TableValues> list = new ArrayList<>();
    ArrayList<String> encoderDikstraString = new ArrayList<String>();
    ArrayList<Integer> encoderDikstraInteger = new ArrayList<Integer>();
    ArrayList<String> dikstraSubject = new ArrayList<String>();
    ArrayList<String> dikstraObject = new ArrayList<String>();
    ArrayList<String> moreSemantic = new ArrayList<String>();
    ArrayList<Integer> pathsss = new ArrayList<Integer>();
    ArrayList<Integer> gathercode = new ArrayList<Integer>();

    static ArrayList<String> pairs = new ArrayList<String>();
    static ArrayList<TableValues> tableValues = new ArrayList<TableValues>();
    static ArrayList<TableValuesDatas> tableValuesDatas = new ArrayList<TableValuesDatas>();

    static ArrayList<TablePaths> tablePaths = new ArrayList<TablePaths>();
    static ArrayList<SubjectObjectPredicate> subjectObjectPredicate = new ArrayList<SubjectObjectPredicate>();
    ArrayList<TableFrequencys> tableFreq = new ArrayList<TableFrequencys>();

    ArrayList<TripletValues> tripletsValues = new ArrayList<TripletValues>();

    ArrayList<String> finalNodes = new ArrayList<String>();
    ArrayList<String> finalEdges = new ArrayList<String>();

    ArrayList<String> calculateNodesArray = new ArrayList<String>();
    ArrayList<String> calculateEdgesArray = new ArrayList<String>();
    ArrayList<String> temp_C_Calculation = new ArrayList<String>();
    ArrayList<String> temp_C_Calculation_Predicate = new ArrayList<String>();
    ArrayList<Float> gather_temp_C_Calculation = new ArrayList<Float>();
    ArrayList<Float> gather_temp_C_Calculation_Predicate = new ArrayList<Float>();

    ArrayList<String> allpredicates = new ArrayList<String>();
    ArrayList<String> allnodes = new ArrayList<String>();

    ArrayList<String> calculateNodesArrayRandom = new ArrayList<String>();
    ArrayList<String> calculateEdgesArrayRandom = new ArrayList<String>();
    ArrayList<String> temp_C_Calculation_Random = new ArrayList<String>();
    ArrayList<String> temp_C_Calculation_Predicate_Random = new ArrayList<String>();
    ArrayList<Float> gather_temp_C_Calculation_Random = new ArrayList<Float>();
    ArrayList<Float> gather_temp_C_Calculation_Predicate_Random = new ArrayList<Float>();

    ArrayList<String> tempAll_C_Update = new ArrayList<String>();
    
    ArrayList<String> allobjects = new ArrayList<String>();
    ArrayList<String> allsubjects = new ArrayList<String>();
    
    static int nodesmeasure = 0;
    static int edgemeasure = 0;
    static int allqueries = 0;

//encoderDikstra
    /*parse files from folder*/
    public void listFilesForFolder(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String filePath = "C:\\Users\\fanis\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\queries\\dbpedia3.8-correct\\" + fileEntry.getName().toString() + "";
                System.out.println("filepath is " + filePath);
            }
        }
    }
    /*return bytes*/

    private static String readBytesFile(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    /*sort frequency by value*/

    public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {

        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /*WIKIpedia url encode queries*/
    public void setDecode(String url) {
        this.decoder = url;

    }
    /*get decode bytes*/

    public String getDecode() {
        try {
            String result = java.net.URLDecoder.decode(this.decoder, StandardCharsets.UTF_8.name());
            // System.out.println("Result is " + result);
            return result;
        } catch (UnsupportedEncodingException e) {

        }
        return null;
    }
    /*print subjet frequency*/

    public void printSubjectsFrequency() {
        for (Map.Entry<String, Integer> entry : nodeMapFrequency.entrySet()) {
            System.out.println("Nodeb = " + entry.getKey()
                    + ", FrequencyB = " + entry.getValue());
        }

    }

    public void getFreqData() {
        System.out.println("freq data ss\\\\\\\\\\\\");
        for (String e : tempAll_C) {
            System.out.println("e " + e);
        }
    }

    public void getFreqData_D() {
        System.out.println("freq data ss\\\\\\\\\\\\");
        for (String e : tempAll_D) {
            System.out.println("e " + e);
        }
    }

    public void getFreqData_D_node() {
        System.out.println("freq data ss\\\\\\\\\\\\");
        for (String e : tempAll_D) {
            System.out.println("e " + e);
        }
    }

    public void printTopK_A() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\sortWIKIdata.txt");
            for (int i = 0; i < nodes; i++) {
                System.out.println("Top " + nodes + " nodes are " + topElementsKeyS_A.get(i) + " with frequency " + topElementsValues_A.get(i));
                String allsentence = "Top " + nodes + " nodes are  " + topElementsKeyS_A.get(i) + " frequency "
                        + topElementsValues_A.get(i) + "\n";
                // String allsentence=topElementsValues_A.get(i)+",";
                myWriter.write(allsentence);
            }
        } catch (IOException ex) {
            Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                myWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void printTopK_B() {
        for (int i = 0; i < nodesb; i++) {
            System.out.println("Top " + nodesb + " nodes are " + topElementsKeyS_B.get(i) + " with frequency " + topElementsValues_B.get(i));
        }
    }

    public void printTopK_C2() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\topKNode.txt");
            for (int i = 0; i < nodesc; i++) {

                System.out.println("Top " + nodesc + " nodes are " + topElementsKeyS_C.get(i) + " with frequency " + topElementsValues_C.get(i));

                moreSemantic.add(topElementsKeyS_C.get(i));
                String allsentence = "Top " + nodesc + " nodes are  " + topElementsKeyS_C.get(i) + " frequency "
                        + topElementsValues_C.get(i) + "\n";
                myWriter.write(allsentence);
            }

        } catch (IOException ex) {
            Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                myWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void printTopK_C() {
        HashMap<String, Integer> tempAll_C_Not_Dubl = new HashMap<String, Integer>();
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputkana2.txt");

            ArrayList<String> tempkey = new ArrayList<String>();
            ArrayList<Integer> tempvalue = new ArrayList<Integer>();
            // System.out.println("ff "+tempAll_C);
            
            
            for (int g = 0; g < tempAll_C.size(); g++) {
                tempAll_C_Not_Dubl.put(tempAll_C.get(g), 0);
            }
           // System.out.println("ffm "+tempAll_C_Not_Dubl.size());
            
            
           // tempAll_C.clear();
            for (Map.Entry mapElement : tempAll_C_Not_Dubl.entrySet()) {
              //  System.out.println("\n"+mapElement.getKey().toString());
                tempAll_C_Update.add(mapElement.getKey().toString());//fani ayrio edo des to
            }
            
            
            
            

            for (int g = 0; g < tempAll_C_Update.size(); g++) {
                ///System.out.println("ff "+tempAll_C.get(g));
                for (int h = 0; h < topElementsKeyS_C.size(); h++) {

                    if (tempAll_C_Update.get(g).equals(topElementsKeyS_C.get(h))) {
                        // System.out.println("ff "+topElementsKeyS_C);
                        tempkey.add(topElementsKeyS_C.get(h));
                        tempvalue.add(topElementsValues_C.get(h));
                    }

                }

                /* System.out.println("Topf " + nodesc + " nodes are " + topElementsKeyS_C.get(g) + " with frequency " + topElementsValues_C.get(g));
                 moreSemantic.add(topElementsKeyS_C.get(g));
                 String allsentence = "Topf nodes are  " + topElementsKeyS_C.get(g) + " frequency "
                 + topElementsValues_C.get(g) + "\n";
                 myWriter.write(allsentence);*/
            }

            System.out.println("tempkey are " + tempkey);

            System.out.println("tempvalue are " + tempvalue);

            if (nodesc <= tempkey.size()) {

                for (int i = 0; i < nodesc; i++) {
                    System.out.println("Topf " + nodesc + " nodes are " + tempkey.get(i) + " with frequency " + tempvalue.get(i));
                    moreSemantic.add(tempkey.get(i));
                    String allsentence = "Topf " + nodesc + " nodes are  " + tempkey.get(i) + " frequency "
                            + tempvalue.get(i) + "\n";
                    myWriter.write(allsentence);
                }

            } else {
                System.out.println("size not compact");

            }

            /*   for (int i = 0; i < nodesc; i++) {
             System.out.println("Topf " + nodesc + " nodes are " + topElementsKeyS_C.get(i) + " with frequency " + topElementsValues_C.get(i));
                
                
                
               
                
             moreSemantic.add(topElementsKeyS_C.get(i));
             String allsentence = "Topf " + nodesc + " nodes are  " + topElementsKeyS_C.get(i) + " frequency "
             + topElementsValues_C.get(i) + "\n";
             myWriter.write(allsentence);
             }*/
        } catch (IOException ex) {
            Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                myWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void printTopK_D() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputK5.txt");
            System.out.println("nodesd " + topElementsKeyS_D.size());
            for (int i = 0; i < nodesd; i++) {

                System.out.println("Top " + nodesd + " nodes are " + topElementsKeyS_D.get(i) + " with frequency " + topElementsValues_D.get(i));
                String allsentence = "Top " + nodesd + " nodes are  " + topElementsKeyS_D.get(i) + " frequency "
                        + topElementsValues_D.get(i) + "\n";
                // myWriter.write(allsentence);
            }
        } catch (IOException ex) {
            Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                myWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void printTopK_D_node() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputK5.txt");
            for (int i = 0; i < nodesd_node; i++) {
                System.out.println("Top " + nodesd_node + " nodes are " + topElementsKeyS_D_node.get(i) + " with frequency " + topElementsValues_D_node.get(i));
                String allsentence = "Top " + nodesd_node + " nodes are  " + topElementsKeyS_D_node.get(i) + " frequency "
                        + topElementsValues_D_node.get(i) + "\n";
                myWriter.write(allsentence);
            }
        } catch (IOException ex) {
            Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                myWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(QueriesFunctionWikidata.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*print subject frequency order*/
    public void printNodesFrequencyOrder() {

        System.out.println("/////////////////");
        final Map<String, Integer> sortedByCount = sortByValue(nodeMapFrequency);
        int count = 0;
        try {

            FileWriter myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\wikidata_frequency.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {

                count = count + 1;
                /* System.out.println("Nodesa = " + entry.getKey()
                 + ", Frequency = " + entry.getValue());*/
                String allsentence = "Querie " + count + " node " + entry.getKey() + " frequency "
                        + entry.getValue() + "\n";
                myWriter.write(allsentence);
                topElementsKeyS_A.add(entry.getKey());
                topElementsValues_A.add(entry.getValue());
                //fff

            }
            myWriter.close();
            printTopK_A();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*print subject frequency order*/
    public void printPropertyFrequencyOrder() {
        System.out.println("/////////////////");
        final Map<String, Integer> sortedByCount = sortByValue(propertyMapFrequency);
        int count = 0;
        try {

            FileWriter myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputB.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                count = count + 1;
                /* System.out.println("Nodesa = " + entry.getKey()
                 + ", Frequency = " + entry.getValue());*/
                String allsentence = "Querie " + count + " node " + entry.getKey() + " frequency "
                        + entry.getValue() + "\n";
                myWriter.write(allsentence);
                topElementsKeyS_B.add(entry.getKey());
                topElementsValues_B.add(entry.getValue());
                //fff

            }
            myWriter.close();
            printTopK_B();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void getLevelOrder_C() throws IOException {
        final Map<String, Integer> sortedByCount = sortByValue(tempAllMap_C);
        System.out.println("size is " + tempAllMap_C.size());
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputD.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                if (!entry.getKey().equals(" \"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral>") && !entry.getKey().equals("\"de,en\"") && !entry.getKey().equals("\"en,da,de,es,fr,jp,nl,no,ru,sv,zh\"") && !entry.getKey().equals("\"nl,fr,en,de,it,es,no,pt\"") && !entry.getKey().equals("\"tt0053589\"") && !entry.getKey().equals("\"fr,en\"") && !entry.getKey().equals("\"200\"") && !entry.getKey().equals("\"fr,en\"") && !entry.getKey().equals("null") && !entry.getKey().equals("\"fr,en\"") && !entry.getKey().equals("\"en,en,fr\"") && !entry.getKey().equals("\"fr,en\"") && !entry.getKey().equals("\"tt0454873\"") && !entry.getKey().equals("\"en,pt,en\"") && !entry.getKey().equals("\"[AUTO_LANGUAGE],en\"") && !entry.getKey().equals("\"tt0071301\"") && !entry.getKey().equals("\"en,fr,de,ru,es,zh,jp\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"en,en\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"en\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"tt0061410\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"tt0122613\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"tt0061410\"") && !entry.getKey().equals("\"en,da,de,es,fr,jp,no,ru,sv,zh\"") && !entry.getKey().equals("http://wikiba.se/ontology#Property") && !entry.getKey().equals("\"string1\"") && !entry.getKey().equals("\"en\"")) {
                    topElementsKeyS_C.add(entry.getKey());
                    topElementsValues_C.add(entry.getValue());
                    // System.out.println("top element topElementsKeyS_C are "+entry.getKey()+"size "+topElementsKeyS_C.size()+" soze value "+topElementsValues_C.size());
                    //System.out.println("top element topElementsValues_C are "+entry.getValue());

                    String allsentence = "Node " + entry.getKey() + " frequency "
                            + entry.getValue() + "\n";
                    myWriter.write(allsentence);

                }
            }
            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        printTopK_C();

    }

    public void getLevelOrder_D() throws IOException {
        final Map<String, Integer> sortedByCount = sortByValue(tempAllMap_D);
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputD.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                topElementsKeyS_D.add(entry.getKey());
                topElementsValues_D.add(entry.getValue());

                String allsentence = "Node " + entry.getKey() + " frequency "
                        + entry.getValue() + "\n";
                myWriter.write(allsentence);
            }
            myWriter.close();

            System.out.println("Successfully wrote to the file.==================");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        printTopK_D();

    }

    public void getLevelOrder_D_node() throws IOException {
        final Map<String, Integer> sortedByCount = sortByValue(tempAllMap_D_node);
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputD_node.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                topElementsKeyS_D_node.add(entry.getKey());
                topElementsValues_D_node.add(entry.getValue());

                String allsentence = "Node " + entry.getKey() + " frequency "
                        + entry.getValue() + "\n";
                myWriter.write(allsentence);
            }
            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        printTopK_D_node();

    }
    /*print subject frequency order*/

    public void printNodesFrequencyOrder_C() {

        /* LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
         nodeMapFrequency_C.entrySet()
         .stream()
         .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
         .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
 
         System.out.println("Reversefan Sorted Map   : " + reverseSortedMap);*/
//antikatestisa sto sorteByValue apo nodeMapFrequency_C se reverse map
        final Map<String, Integer> sortedByCount = sortByValue(nodeMapFrequency_C);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\output_D.txt");
            for (int i = 0; i < tempAll_C.size(); i++) {
                for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                    if (tempAll_C.get(i).equals(entry.getKey())) {
                        tempAllMap_C.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            getLevelOrder_C();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*print subject frequency order*/
    public void printNodesFrequencyOrder_D() {
        System.out.println("/////////////////");
        final Map<String, Integer> sortedByCount = sortByValue(propertyMapFrequency_D);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputE.txt");

            //  System.out.println("sizee" +tempAll_D.size());
            for (int i = 0; i < tempAll_D.size(); i++) {
                for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                    if (tempAll_D.get(i).equals(entry.getKey())) {

                        tempAllMap_D.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            getLevelOrder_D();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /*print subject frequency order*/

    public void printNodesFrequencyOrder_D_node() {
        System.out.println("/////////////////");
        final Map<String, Integer> sortedByCount = sortByValue(propertyMapFrequency_D_node);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\fanis\\Desktop\\outputE.txt");
            for (int i = 0; i < tempAll_D_node.size(); i++) {
                for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                    if (tempAll_D_node.get(i).equals(entry.getKey())) {
                        tempAllMap_D_node.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            getLevelOrder_D_node();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /*get subject frequency testing*/

    public void getNodeFrequency() {
        int freqnode = 0;
        for (String node : nodesMap.keySet()) {
            for (int i = 0; i < nodesList.size(); i++) {
                if (node.equals(nodesList.get(i))) {
                    freqnode = freqnode + 1;
                }
            }
            nodeMapFrequency.put(node, freqnode);
            freqnode = 0;
        }
        System.out.println("size map is " + nodesMap.size());
    }

    /*get subject frequency testing*/
    public void getNodeFrequency_C() {
        int freqnodec = 0;
        for (String node : nodesMap_C.keySet()) {
            for (int i = 0; i < nodesList_C.size(); i++) {
                if (node.equals(nodesList_C.get(i))) {
                    freqnodec = freqnodec + 1;
                }
            }
            nodeMapFrequency_C.put(node, freqnodec);
            freqnodec = 0;
        }
        //  System.out.println("size map is " + nodesMap_C.size());
    }

    public void FinalResultRandom() {

        //System.out.println("gather_temp_C_Calculation_Random are "+gather_temp_C_Calculation_Random);
        //System.out.println("gather_temp_C_Calculation_Predicate_Random are "+gather_temp_C_Calculation_Predicate_Random);
        double sum = 0;
        for (int v = 0; v < gather_temp_C_Calculation_Random.size(); v++) {
            sum = sum + gather_temp_C_Calculation_Random.get(v);
        }
        double sumPr = 0;
        for (int v = 0; v < gather_temp_C_Calculation_Predicate_Random.size(); v++) {
            sumPr = sumPr + gather_temp_C_Calculation_Predicate_Random.get(v);
        }

        System.out.println("///////////////////FINAL RESULT///////////////////////////");
        System.out.println("Total nodes coverage " + sum / gather_temp_C_Calculation_Random.size());
        //System.out.println("Total edges coverage " + sumPr / gather_temp_C_Calculation_Predicate_Random.size());
        System.out.println("///////////////////END RESULT///////////////////////////");

    }

    /*get subject frequency testing*/
    public void getNodeFrequency_D() {
        int freqnoded = 0;
        for (String node : propertyMap_D.keySet()) {
            for (int i = 0; i < propertyList_D.size(); i++) {
                if (node.equals(propertyList_D.get(i))) {
                    freqnoded = freqnoded + 1;
                }
            }
            propertyMapFrequency_D.put(node, freqnoded);
            freqnoded = 0;
        }
        System.out.println("size map is " + propertyMap_D.size());
    }
    /*get subject frequency testing*/

    public void getNodeFrequency_D_node() {
        int freqnoded = 0;
        for (String node : propertyMap_D_node.keySet()) {
            for (int i = 0; i < propertyList_D_node.size(); i++) {
                if (node.equals(propertyList_D_node.get(i))) {
                    freqnoded = freqnoded + 1;
                }
            }
            propertyMapFrequency_D_node.put(node, freqnoded);
            freqnoded = 0;
        }
        System.out.println("size map is " + propertyMap_D_node.size());
    }
    /*get subject frequency testing*/

    public void getPropertyFrequency() {
        int freqproperty = 0;
        for (String property : propertyMap.keySet()) {
            for (int i = 0; i < propertyList.size(); i++) {
                if (property.equals(propertyList.get(i))) {
                    freqproperty = freqproperty + 1;
                }
            }
            propertyMapFrequency.put(property, freqproperty);
            freqproperty = 0;
        }
        // System.out.println("size map frequncy in is " + propertyMapFrequency.size());
    }
    /*store querie in structures*/

    public void getQueryLevelC(String querys, String nodename) {
        allqueries = allqueries + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = querys;
        try {
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {

                if (pattern.getSubjectVar().getValue() != null) {
                    nodesmeasure = nodesmeasure + 1;
                    if (Pattern.matches("[^\"''']+", pattern.getSubjectVar().getValue().toString())) {
                        if (!pattern.getSubjectVar().getValue().toString().toLowerCase().trim().contains("\"none\"") || !pattern.getSubjectVar().getValue().toString().endsWith("\"") || !pattern.getSubjectVar().getValue().toString().startsWith("\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"pl,en\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getSubjectVar().getValue().toString().trim().contains("literal") || !pattern.getSubjectVar().getValue().toString().trim().contains("''") || !pattern.getSubjectVar().getValue().toString().trim().contains("'") || !pattern.getSubjectVar().getValue().toString().trim().contains("Literal")) {

                            nodesMap_C.put(pattern.getSubjectVar().getValue().toString(), String.valueOf(rows));
                            nodesList_C.add(pattern.getSubjectVar().getValue().toString());
                            temp_C.add(pattern.getSubjectVar().getValue().toString());
                            rowsc = rowsc + 1;
                            allnodes.add(pattern.getSubjectVar().getValue().toString());
                            allsubjects.add(pattern.getSubjectVar().getValue().toString());
                        } else if (pattern.getSubjectVar().getValue() == null) {
                            nodesMap_C.put("null", String.valueOf(rows));
                            nodesList_C.add("null");
                            temp_C.add("null");
                            rowsc = rowsc + 1;
                        }
                    }

                } else if (pattern.getSubjectVar().getValue() == null) {
                    nodesMap_C.put("null", String.valueOf(rows));
                    nodesList_C.add("null");
                    temp_C.add("null");
                    rowsc = rowsc + 1;
                    nodesmeasure = nodesmeasure + 1;
                }

                if (pattern.getObjectVar().getValue() != null) {
                    nodesmeasure = nodesmeasure + 1;
                    if (!pattern.getObjectVar().getValue().toString().toLowerCase().trim().contains(" \"none\"") || !pattern.getObjectVar().getValue().toString().endsWith("\"") || !pattern.getObjectVar().getValue().toString().startsWith("\"") || !pattern.getObjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getObjectVar().getValue().toString().trim().contains("literal") || !pattern.getObjectVar().getValue().toString().trim().contains("''") || !pattern.getObjectVar().getValue().toString().trim().contains("'") || !pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {

                        nodesMap_C.put(pattern.getObjectVar().getValue().toString(), String.valueOf(rows));
                        nodesList_C.add(pattern.getObjectVar().getValue().toString());
                        temp_C.add(pattern.getObjectVar().getValue().toString());
                        rowsc = rowsc + 1;
                        allnodes.add(pattern.getObjectVar().getValue().toString());
                        allobjects.add(pattern.getObjectVar().getValue().toString());
                    } else if (pattern.getObjectVar().getValue() == null) {
                        nodesMap_C.put("null", String.valueOf(rows));
                        nodesList_C.add("null");
                        temp_C.add("null");
                        rowsc = rowsc + 1;
                    }

                } else if (pattern.getObjectVar().getValue() == null) {
                    nodesMap_C.put("null", String.valueOf(rows));
                    nodesList_C.add("null");
                    temp_C.add("null");
                    rowsc = rowsc + 1;
                    nodesmeasure = nodesmeasure + 1;
                }
                if (pattern.getPredicateVar().getValue() != null) {

                    allpredicates.add(pattern.getPredicateVar().getValue().toString());
                    edgemeasure = edgemeasure + 1;
                } else {
                    edgemeasure = edgemeasure + 1;
                }

            }
            // System.out.println("///////////////dada///////// "+temp_C);

            int flag = 0;
            for (String e : temp_C) {
                if (e.equals(/*"http://www.wikidata.org/entity/Q1284"*/nodename)) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                tempAll_C.addAll(temp_C);
            }
            flag = 0;
            temp_C = new ArrayList<String>();
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void GetResultsRandom() {

        System.out.println("gather_temp_C_Calculation_Random are " + gather_temp_C_Calculation_Random);
        System.out.println("gather_temp_C_Calculation_Predicate_Random are " + gather_temp_C_Calculation_Predicate_Random);

    }

    public void CompareCalculationRandomsss() {
        float arithm = 0;
        //System.out.println("sizes are "+calculateNodesArrayRandom.size()+"cc "+temp_C_Calculation_Random.size());
        for (int i = 0; i < calculateNodesArrayRandom.size(); i++) {
            for (int j = 0; j < temp_C_Calculation_Random.size(); j++) {
                // System.out.println("aaaaaaaaaaaaaaaaaaaaaxaxaxaxzzzzzzzzzzzz");
                if (calculateNodesArrayRandom.get(i).trim().equals(temp_C_Calculation_Random.get(j).trim())) {
                    arithm = arithm + 1;

                    //System.out.println("aaaaaaaaaaaaaaaaaaaaaxaxaxax");
                }
            }
        }
        float paranom = temp_C_Calculation_Random.size();

        float klasma = 0;
        if (paranom != 0) {
            klasma = arithm / paranom;
        }

        //System.out.println("Klasma Arithm prin bi "+arithm);
        //System.out.println("Klasma paranom prin bi "+paranom);
        //System.out.println("Klasma prin bi "+klasma);
        gather_temp_C_Calculation_Random.add(klasma);
        float arithmPredicate = 0;
        for (int i = 0; i < calculateEdgesArrayRandom.size(); i++) {
            for (int j = 0; j < temp_C_Calculation_Predicate_Random.size(); j++) {
                if (calculateEdgesArrayRandom.get(i).trim().equals(temp_C_Calculation_Predicate_Random.get(j).trim())) {
                    arithmPredicate = arithmPredicate + 1;

                }
            }
        }
        float paranomPredicate = temp_C_Calculation_Predicate_Random.size();
        float klasmaPredicate = 0;
        if (paranomPredicate != 0) {
            klasmaPredicate = arithmPredicate / paranomPredicate;

        }
        // System.out.println("klasmaPredicate Arithm prin bi "+arithmPredicate);
        // System.out.println("Klasma paranomPredicate prin bi "+paranomPredicate);
        // System.out.println("klasmaPredicate prin bi "+klasmaPredicate);
        gather_temp_C_Calculation_Predicate_Random.add(klasmaPredicate);
        //GetResultsRandom();
    }

    public void getQueryLevelC_Calculation_Random(String querys, String nodename) {
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = querys;
        try {
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {

                if (pattern.getSubjectVar().getValue() != null) {

                    if (Pattern.matches("[^\"''']+", pattern.getSubjectVar().getValue().toString())) {
                        if (!pattern.getSubjectVar().getValue().toString().toLowerCase().trim().contains("\"none\"") || !pattern.getSubjectVar().getValue().toString().endsWith("\"") || !pattern.getSubjectVar().getValue().toString().startsWith("\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"pl,en\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getSubjectVar().getValue().toString().trim().contains("literal") || !pattern.getSubjectVar().getValue().toString().trim().contains("''") || !pattern.getSubjectVar().getValue().toString().trim().contains("'") || !pattern.getSubjectVar().getValue().toString().trim().contains("Literal")) {

                            temp_C_Calculation_Random.add(pattern.getSubjectVar().getValue().toString());
                        } else if (pattern.getSubjectVar().getValue() == null) {
                            temp_C_Calculation_Random.add("null");
                        }
                    }

                } else if (pattern.getSubjectVar().getValue() == null) {
                    temp_C_Calculation_Random.add("null");
                }

                if (pattern.getObjectVar().getValue() != null) {

                    if (!pattern.getObjectVar().getValue().toString().toLowerCase().trim().contains(" \"none\"") || !pattern.getObjectVar().getValue().toString().endsWith("\"") || !pattern.getObjectVar().getValue().toString().startsWith("\"") || !pattern.getObjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getObjectVar().getValue().toString().trim().contains("literal") || !pattern.getObjectVar().getValue().toString().trim().contains("''") || !pattern.getObjectVar().getValue().toString().trim().contains("'") || !pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {

                        temp_C_Calculation_Random.add(pattern.getObjectVar().getValue().toString());
                    } else if (pattern.getObjectVar().getValue() == null) {
                        temp_C_Calculation_Random.add("null");
                    }

                } else if (pattern.getObjectVar().getValue() == null) {
                    temp_C_Calculation_Random.add("null");
                }

                if (pattern.getPredicateVar().getValue() != null) {

                    temp_C_Calculation_Predicate_Random.add(pattern.getPredicateVar().getValue().toString());

                } else if (pattern.getPredicateVar().getValue() == null) {
                    temp_C_Calculation_Predicate_Random.add("null");
                }

            }
            // System.out.println("///////////////dada///////// "+temp_C);

            CompareCalculationRandomsss();
            temp_C_Calculation_Random = new ArrayList<String>();
            temp_C_Calculation_Predicate_Random = new ArrayList<String>();
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void GetResults() {

        System.out.println("GetResultsgather_temp_C_Calculation are " + gather_temp_C_Calculation);
        System.out.println("GetResultsgather_temp_C_Calculation_Predicate are " + gather_temp_C_Calculation_Predicate);

    }

    public void CompareCalculation() {

     //statnode=startnode+1;  
        // System.out.println("temp_C_Calculation " + temp_C_Calculation);
        // System.out.println("temp_C_Calculation_Predicate " + temp_C_Calculation_Predicate);
        // System.out.println("calculate nodes "+calculateNodesArray);
        //  System.out.println("calculate edges "+calculateEdgesArray);
        float arithm = 0;

        for (int i = 0; i < calculateNodesArray.size(); i++) {
            for (int j = 0; j < temp_C_Calculation.size(); j++) {
                if (calculateNodesArray.get(i).trim().equals(temp_C_Calculation.get(j).trim())) {
                    arithm = arithm + 1;
                }
            }
        }
        float paranom = temp_C_Calculation.size();
        float klasma = arithm / paranom;
        // System.out.println("Klasma Arithm prin bi "+arithm);
        // System.out.println("Klasma paranom prin bi "+paranom);
        // System.out.println("Klasma prin bi "+klasma);
        gather_temp_C_Calculation.add(klasma);
        // System.out.println("size temp_C_Calculation is "+temp_C_Calculation.size());
        // System.out.println("size calculateNodesArray is "+calculateNodesArray.size());

        float arithmPredicate = 0;
        for (int i = 0; i < calculateEdgesArray.size(); i++) {
            for (int j = 0; j < temp_C_Calculation_Predicate.size(); j++) {
                if (calculateEdgesArray.get(i).trim().equals(temp_C_Calculation_Predicate.get(j).trim())) {
                    arithmPredicate = arithmPredicate + 1;
                }
            }
        }
        float paranomPredicate = temp_C_Calculation_Predicate.size();
        float klasmaPredicate = arithmPredicate / paranomPredicate;
        System.out.println("klasmaPredicate Arithm prin bi " + arithmPredicate);
        System.out.println("Klasma paranomPredicate prin bi " + paranomPredicate);
        System.out.println("klasmaPredicate prin bi " + klasmaPredicate);
        gather_temp_C_Calculation_Predicate.add(klasmaPredicate);
        // System.out.println("size temp_C_Calculation_Predicate is "+temp_C_Calculation_Predicate.size());
        //System.out.println("size calculateEdgesArray is "+calculateEdgesArray.size());
        // GetResults();

    }

    public void getQueryLevelC_Calculation(String querys, String nodename) {
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = querys;
        try {
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {

                if (pattern.getSubjectVar().getValue() != null) {

                    if (Pattern.matches("[^\"''']+", pattern.getSubjectVar().getValue().toString())) {
                        if (!pattern.getSubjectVar().getValue().toString().toLowerCase().trim().contains("\"none\"") || !pattern.getSubjectVar().getValue().toString().endsWith("\"") || !pattern.getSubjectVar().getValue().toString().startsWith("\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"pl,en\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getSubjectVar().getValue().toString().trim().contains("literal") || !pattern.getSubjectVar().getValue().toString().trim().contains("''") || !pattern.getSubjectVar().getValue().toString().trim().contains("'") || !pattern.getSubjectVar().getValue().toString().trim().contains("Literal")) {

                            temp_C_Calculation.add(pattern.getSubjectVar().getValue().toString());
                        } else if (pattern.getSubjectVar().getValue() == null) {
                            temp_C_Calculation.add("null");
                        }
                    }
                    //    temp_C_Calculation.add(pattern.getSubjectVar().getValue().toString());

                } else if (pattern.getSubjectVar().getValue() == null) {
                    temp_C_Calculation.add("null");
                }

                if (pattern.getPredicateVar().getValue() != null) {
                    //temp_C_Calculation_Predicate.add(pattern.getPredicateVar().getValue().toString());
                    if (!pattern.getPredicateVar().getValue().toString().toLowerCase().trim().contains(" \"none\"") || !pattern.getObjectVar().getValue().toString().endsWith("\"") || !pattern.getObjectVar().getValue().toString().startsWith("\"") || !pattern.getObjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getObjectVar().getValue().toString().trim().contains("literal") || !pattern.getObjectVar().getValue().toString().trim().contains("''") || !pattern.getObjectVar().getValue().toString().trim().contains("'") || !pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {

                        temp_C_Calculation_Predicate.add(pattern.getPredicateVar().getValue().toString());
                    } else if (pattern.getSubjectVar().getValue() == null) {

                        temp_C_Calculation_Predicate.add("null");
                    }

                } else if (pattern.getPredicateVar().getValue() == null) {

                    temp_C_Calculation_Predicate.add("null");
                }

                if (pattern.getObjectVar().getValue() != null) {

                    if (!pattern.getObjectVar().getValue().toString().toLowerCase().trim().contains(" \"none\"") || !pattern.getObjectVar().getValue().toString().endsWith("\"") || !pattern.getObjectVar().getValue().toString().startsWith("\"") || !pattern.getObjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getObjectVar().getValue().toString().trim().contains("literal") || !pattern.getObjectVar().getValue().toString().trim().contains("''") || !pattern.getObjectVar().getValue().toString().trim().contains("'") || !pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {

                        temp_C_Calculation.add(pattern.getObjectVar().getValue().toString());
                    } else if (pattern.getObjectVar().getValue() == null) {
                        temp_C_Calculation.add("null");
                    }
                    // temp_C_Calculation.add(pattern.getObjectVar().getValue().toString());
                } else if (pattern.getObjectVar().getValue() == null) {
                    temp_C_Calculation.add("null");
                }

            }
            // System.out.println("///////////////dada///////// "+temp_C);

            int flag = 0;
            for (String e : temp_C_Calculation) {
                if (e.equals(/*"http://www.wikidata.org/entity/Q1284"*/nodename)) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                //  System.out.println("temp_C_Calculation " + temp_C_Calculation);
                System.out.println("temp_C_Calculation_Predicate " + temp_C_Calculation_Predicate);
                //    
                CompareCalculation();
            }
            flag = 0;
            temp_C_Calculation = new ArrayList<String>();
            temp_C_Calculation_Predicate = new ArrayList<String>();
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void getNodes(String querys, String nodename) {
        // System.out.println("node called");
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = querys;
        try {
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {
                if (pattern.getSubjectVar().getValue() != null) {
                    nodesMap_C_node.put(pattern.getSubjectVar().getValue().toString(), String.valueOf(rows));
                    nodesList_C_node.add(pattern.getSubjectVar().getValue().toString());
                    temp_C_node.add(pattern.getSubjectVar().getValue().toString());
                    rowsc = rowsc + 1;
                }
                if (pattern.getObjectVar().getValue() != null) {
                    nodesMap_C_node.put(pattern.getObjectVar().getValue().toString(), String.valueOf(rows));
                    nodesList_C_node.add(pattern.getObjectVar().getValue().toString());
                    temp_C_node.add(pattern.getObjectVar().getValue().toString());
                    rowsc = rowsc + 1;
                }
            }

            int flag = 0;
            for (String e : temp_C_node) {
                if (e.equals(nodename)) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                tempAll_C_node.addAll(temp_C_node);
            }
            flag = 0;
            temp_C_node = new ArrayList<String>();
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void convertArrayListToMap() {
        String value = "-";
        for (int i = 0; i < tempAll_C_node.size(); i++) {
            converter.put(tempAll_C_node.get(i), value);
        }
    }

    /*objectList_D_node subjectList_D_node propertyList_D_node  */
    public void comperator() {

        /*objectList_D_node subjectList_D_node propertyList_D_node  */
        // using keySet() for iteration over keys
        for (String name : converter.keySet()) {
            System.out.println("Node: " + name);
            for (int i = 0; i < objectList_D_node.size(); i++) {

                if (name.equals(objectList_D_node.get(i))) {
                    System.out.println("Properties object are " + propertyList_D_node.get(i));
                }

                if (name.equals(subjectList_D_node.get(i))) {
                    System.out.println("Properties object are " + propertyList_D_node.get(i));
                }

            }

        }

        // using values() for iteration over values
        //    for (String url : converter.values())
        //System.out.println("value: " + url);
    }

    public void algorithm() {

        int flag = 0;
        int flagNext = 0;
        String counter = "2";
        String shortestPath = "null";
        String shortestpathNext = null;
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<Integer> allPaths = new ArrayList<Integer>();
        HashMap<String, Integer> pathsFreq = new HashMap<String, Integer>();
        /*  for (int i = 0; i < tableValuesDatas.size(); i++) {
         if (tableValuesDatas.get(0).getFreqPath() < 2) {
         shortestPath = tableValuesDatas.get(0).getPath();
         flag = 1;
         break;
         }
         }*/
        if (flag == 1) {
            //  System.out.println("final path is  \n" + shortestPath);
        } else {
            for (int i = 0; i < tableValuesDatas.size(); i++) {
                if (tableValuesDatas.get(i).getNumberPath().equals(counter)) {
                    temp.add(tableValuesDatas.get(i).getPath());
                } else {
                    if (temp.get(i) != null) {
                        flagNext = 1;
                    } else {
                        counter = counter + 1;
                        flagNext = 0;
                    }
                }
                if (flagNext == 1) {
                    break;
                }

            }

            // HashMap<String, Integer> pathsFreq = new HashMap<String, Integer>();
            int freq = 0;
            for (String e : temp) {
                freq = 0;
                for (String e2 : temp) {
                    if (e.equals(e2)) {
                        freq = freq + 1;
                    }
                }
                pathsFreq.put(e, freq);
            }

        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : pathsFreq.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
                shortestPath = maxEntry.getKey();
            }
        }
        System.out.println("final path2 is  " + shortestPath);

        if (!shortestPath.equals("null")) {
            String[] fin = shortestPath.split("\\s+");

            for (int i = 0; i < fin.length; i++) {

                // System.out.println("char at "+i+" index is: "+Integer.valueOf(fin[i]));  
                allPaths.add(Integer.valueOf(fin[i]));
                // tablePaths(i,Integer.valueOf(fin[i]));
                tablePaths.add(new TablePaths(i, Integer.valueOf(fin[i])));
            }
        }

        /*
         System.out.println(" objectList_D_node "+objectList_D_node.size());
         for(int a=0;a<objectList_D_node.size();a++){
         tablePaths.add((new TablePaths(a,1,subjectList_D_node.get(a),propertyListV_D_node.get(a),objectList_D_node.get(a))));
     
         }*/
     //tablePaths.add((new TablePaths(1,1,"","","")));
        // System.out.println(tablePaths);
    /* for(int k=0;k<tablePaths.size();k++){
         
         System.out.println("fan "+tablePaths.get(k));
         }
     
         ArrayList<Integer> rowP = new ArrayList<Integer>();
         ArrayList<Integer> rowA = new ArrayList<Integer>();
    
     
     
         for(int i=0;i<tablePaths.size();i++){
         if(tablePaths.get(i).getIndex()%2==0){
             
         rowA.add(tablePaths.get(i).getPath());
         
         }else if(tablePaths.get(i).getIndex()%2==1){
             
         rowP.add(tablePaths.get(i).getPath());
             
         
         }
     
         }
         */
        /*
         encoderDikstra.clear();
         objectMap_D_node.clear();
         objectList_D_node.clear();
         rowsd_nodeobject = 0;

         subjectMap_D_node.clear();
         subjectList_D_node.clear();
         rowsd_nodesubject = 0;

         encoderDikstraString.clear();
         encoderDikstraInteger.clear();

         pairsvValues.clear();

         tableValues.clear();
         tableValuesDatas.clear();
     
         */
      //System.out.println("rowP is "+rowP.size());
        //   System.out.println("rowP is "+rowP.size());
     /* for(int i=0;i<rowP.size();i++){
         for(int i=0;i<subjectList_D_node.size();i++){
         if(){
         //subjectList_D_node
          
         }
          
         }
      
      
         }*/
        /* ArrayList<String> rowPstring = new ArrayList<String>();
         ArrayList<String> rowAstring = new ArrayList<String>();
         for (Map.Entry<String, Integer> entry : encoderDikstra.entrySet()) {
         for (int e =0;e<allPaths.size();e++) {
         if (!entry.getKey().equals("null")) {
         if (entry.getValue().equals(allPaths.get(e))) {
         System.out.print("< - > \n" + entry.getKey());
         rowPstring.add(entry.getKey());
         }                 
         }
         }
         }
       
         for (Map.Entry<String, Integer> entry : encoderDikstra.entrySet()) {
         for (int e =0;e<rowA.size();e++) {
         if (!entry.getKey().equals("null")) {
         if (entry.getValue().equals(rowA.get(e))) {
         System.out.print("< -rowA > \n" + entry.getKey());
         rowAstring.add(entry.getKey());
         }                 
         }
         }
         }
              
         for (Map.Entry<String, Integer> entry : encoderDikstra.entrySet()) {
         for (int e =0;e<rowP.size();e++) {
         if (!entry.getKey().equals("null")) {
         if (entry.getValue().equals(rowP.get(e))) {
         System.out.print("\n < -rowP > " + entry.getKey());
         rowPstring.add(entry.getKey());
         }                 
         }
         }
         }*/
        /*String triplet1=null;
         String triplet2=null;
     
         for(int l=0;l<rowPstring.size();l++){
                     
         for(int p=0;p<objectList_D_node.size();p++){  
         if(((rowPstring.get(l).equals(objectList_D_node.get(p))   )) || ((rowPstring.get(l).equals(subjectList_D_node.get(p))   )) ) {
             
         triplet1="triplet is "+objectList_D_node.get(p)+" - "+propertyListV_D_node.get(p)+" - "+subjectList_D_node.get(p);
         System.out.println("------------------------->>>fffaaaaaaaaaaa " +triplet1);
         }
         }}
                 
                 
         for(int l=0;l<rowPstring.size();l++){
                     
         for(int p=0;p<objectList_D_node.size();p++){  
         if(((rowPstring.get(l).equals(objectList_D_node.get(p))   )) || ((rowPstring.get(l).equals(subjectList_D_node.get(p))   )) ) {
             
         triplet2="triplet is "+objectList_D_node.get(p)+" - "+propertyListV_D_node.get(p)+" - "+subjectList_D_node.get(p);
          
         System.out.println("------------------------->>>fffaaaaaaaaaaa b--- "+triplet2);
            
               
         }
         }}*/
        /*   for(int l=0;l<rowP.size();l++){
                     
         for(int p=0;p<subjectList_D_node.size();p++){  
               
               
               
         if((rowP.get(l).equals(objectList_D_node.get(p)) && rowP.get(l).equals(subjectList_D_node.get(p))) || (rowP.get(l).equals(objectList_D_node.get(p)) && rowP.get(l).equals(subjectList_D_node.get(p)))        ) {
         System.out.println("------------------------->>>fffaaaaaaaaaaa");
         // triplet1="triplet is "+objectList_D_node.get(p)+" - "+propertyListV_D_node.get(nodes)+" - "+subjectList_D_node.get(p);
         }
         }}*/
        /*   for(int l=0;l<rowA.size();l++){
         System.out.println("\n rowA is  is "+rowA.get(l));
       
       
         }
        
        
         for(int l=0;l<rowP.size();l++){
         System.out.println("\n rowP is  is "+rowP.get(l));
       
       
         }*/
        /* String triplet1=null;
         String triplet2=null;
    
         for(int l=0;l<rowA.size();l++){
         for(int p=0;p<objectList_D_node.size();p++){          
         if((rowA.get(l).equals(objectList_D_node.get(p)) && rowA.get(l).equals(subjectList_D_node.get(p))) || (rowA.get(l).equals(objectList_D_node.get(p)) && rowA.get(l).equals(subjectList_D_node.get(p)))        ) {
             
         triplet1="triplet is "+objectList_D_node.get(p)+" - "+propertyListV_D_node.get(nodes)+" - "+subjectList_D_node.get(p);
         }
         }
         }*/
        ///  System.out.println("\n All Path querie are \n "+tablePaths);
        //tablePaths.clear();
        //rowP.clear();
        //rowA.clear();
        System.out.println("Shortest path final is " + shortestPath);
    }

    public void algorithm2() {

        /*  int flag = 0;
         int flagNext = 0;
         String counter = "2";
         String shortestPath = "null";
         String shortestpathNext = null;
         ArrayList<String> temp = new ArrayList<String>();
         ArrayList<Integer> allPaths = new ArrayList<Integer>();
         HashMap<String, Integer> pathsFreq = new HashMap<String, Integer>();
         for (int i = 0; i < tableValuesDatas.size(); i++) {
         if (tableValuesDatas.get(0).getFreqPath() < 2) {
         shortestPath = tableValuesDatas.get(0).getPath();
         flag = 1;
         break;
         }
         }
         if (flag == 1) {
         //  System.out.println("final path is  \n" + shortestPath);
         } */
    }
    /*store querie in structures*/

    public static int getIntValue(Value v, int fallback) {
        if (v instanceof Literal) {
            return getIntValue((Literal) v, fallback);
        } else {
            return fallback;
        }
    }

    public void getQueryLevelD_node(String querys, String nodename) {
        // System.out.println("calledd");
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = querys;
        int co = 0, co2 = 0;
        try {
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {

                // int literal=0;
                if (pattern.getSubjectVar().getValue() != null) {
                    if (pattern.getSubjectVar().getValue().toString().trim().contains("literal") || pattern.getSubjectVar().getValue().toString().trim().contains("''") || pattern.getSubjectVar().getValue().toString().trim().contains("'") || pattern.getSubjectVar().getValue().toString().trim().contains("Literal")) {

                        //  System.out.println("outtf----------------------------->");
                    }

                }

                if (pattern.getObjectVar().getValue() != null) {
                    if (pattern.getObjectVar().getValue().toString().trim().contains("literal") || pattern.getObjectVar().getValue().toString().trim().contains("''") || pattern.getObjectVar().getValue().toString().trim().contains("'") || pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {

                        //  System.out.println("outtf----------------------------->");
                    }

                }

                // System.out.println("lit2 " + Literals.getIntValue(pattern.getSubjectVar().getValue(), 1));
                // System.out.println("lit2 " + Literals.getIntValue(pattern.getObjectVar().getValue(), 1));
                if (pattern.getSubjectVar().getValue() != null) {
                    if (pattern.getSubjectVar().getValue().toString().trim().contains("\"pl,en\"")) {
                        //System.out.println("sub                    cssssssssssssssccs");

                    }
                    // if(pattern.getSubjectVar().getValue().toString().trim().contains("''")){
                    // if(pattern.getSubjectVar().getValue().toString().trim().contains("'")){
                    if (!pattern.getSubjectVar().getValue().toString().endsWith("\"") || !pattern.getSubjectVar().getValue().toString().startsWith("\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"pl,en\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getSubjectVar().getValue().toString().trim().contains("literal") || !pattern.getSubjectVar().getValue().toString().trim().contains("''") || !pattern.getSubjectVar().getValue().toString().trim().contains("'") || !pattern.getSubjectVar().getValue().toString().trim().contains("Literal")) {

                        // subjectMap_D_node.put(pattern.getSubjectVar().getValue().toString(), String.valueOf(rowsd_nodesubject));
                        subjectList_D_node.add(pattern.getSubjectVar().getValue().toString());
                        encoderDikstra.put(rowsd_node, pattern.getSubjectVar().getValue().toString());
                        // encoderDikstraString.add(pattern.getSubjectVar().getValue().toString());
                        // encoderDikstraInteger.add(rowsd_node);
                        // rowsd_nodesubject = rowsd_nodesubject + 1;
                        rowsd_node = rowsd_node + 1;
                        //subjectList_D_node_Global.add(pattern.getSubjectVar().getValue().toString());

                    } else if (pattern.getSubjectVar().getValue() == null) {
                        subjectList_D_node.add("null");
                        encoderDikstraString.add("null");
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodesubject = rowsd_nodesubject + 1;
                        subjectList_D_node_Global.add("null");

                    }
                } else if (pattern.getSubjectVar().getValue() == null) {
                    subjectList_D_node.add("null");
                    encoderDikstraString.add("null");
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodesubject = rowsd_nodesubject + 1;
                    subjectList_D_node_Global.add("null");
               // }

                    // }
                    // }
                    //}
                }

                // if(Literals.getIntValue(pattern.getPredicateVar().getValue(), 1)!=1){
                if (pattern.getPredicateVar().getValue() != null) {
                    propertyMapV_D_node.put(pattern.getPredicateVar().getValue().toString(), String.valueOf(rowsd_nodeproperty));
                    propertyListV_D_node.add(pattern.getPredicateVar().getValue().toString());
                    encoderDikstra.put(rowsd_node, pattern.getPredicateVar().getValue().toString());
                    encoderDikstraString.add(pattern.getPredicateVar().getValue().toString());
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodeproperty = rowsd_nodeproperty + 1;
                    rowsd_node = rowsd_node + 1;
                    propertyListV_D_node_Global.add(pattern.getPredicateVar().getValue().toString());
                } else if (pattern.getPredicateVar().getValue() == null) {
                    propertyListV_D_node.add("null");
                    encoderDikstraString.add("null");
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodeobject = rowsd_nodeproperty + 1;
                    propertyListV_D_node_Global.add("null");
                }
                if (pattern.getObjectVar().getValue() != null) {
                    // if(pattern.getObjectVar().getValue().toString().trim().contains("''")){
                    // if(pattern.getObjectVar().getValue().toString().trim().contains("'")){
                    //  if(!pattern.getObjectVar().getValue().toString().trim().contains("Literal")){
                    // if(!pattern.getObjectVar().getValue().toString().trim().contains("literal")){

                    /*     if (pattern.getObjectVar().getValue() != null) {
                     if (pattern.getObjectVar().getValue().toString().trim().contains("literal")||pattern.getObjectVar().getValue().toString().trim().contains("''")||pattern.getObjectVar().getValue().toString().trim().contains("'")||pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {

                     System.out.println("outtf----------------------------->");
                     break;

                      
                        

                     }

                     int tg=0;         }*/
                    /*  if (pattern.getObjectVar().getValue().toString().endsWith("\"")){
                     System.out.println("ob varr is  "+pattern.getObjectVar().getValue().toString());
                            
                            
                            
                     }*/
                    int tj = 0;
                    if (!pattern.getObjectVar().getValue().toString().endsWith("\"") || !pattern.getObjectVar().getValue().toString().startsWith("\"") || !pattern.getObjectVar().getValue().toString().trim().contains("pl,en") || !pattern.getObjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getObjectVar().getValue().toString().trim().contains("literal") || !pattern.getObjectVar().getValue().toString().trim().contains("''") || !pattern.getObjectVar().getValue().toString().trim().contains("'") || !pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {
 // if(!Pattern.matches("[^\"''']+", pattern.getObjectVar().getValue().toString())){

                        //  objectMap_D_node.put(pattern.getObjectVar().getValue().toString(), String.valueOf(rowsd_nodeobject));
                        objectList_D_node.add(pattern.getObjectVar().getValue().toString());
                        encoderDikstra.put(rowsd_node, pattern.getObjectVar().getValue().toString());
                        // encoderDikstraString.add(pattern.getObjectVar().getValue().toString());
                        // encoderDikstraInteger.add(rowsd_node);
                        // rowsd_nodeobject = rowsd_nodeobject + 1;
                        rowsd_node = rowsd_node + 1;
                        // objectList_D_node_Global.add(pattern.getObjectVar().getValue().toString());

                    } else if (pattern.getObjectVar().getValue() == null) {
                        objectList_D_node.add("null");
                        encoderDikstraString.add("null");
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodeobject = rowsd_nodeobject + 1;
                        objectList_D_node_Global.add("null");

                    }
                    //}}
                } else if (pattern.getObjectVar().getValue() == null) {
                    objectList_D_node.add("null");
                    encoderDikstraString.add("null");
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodeobject = rowsd_nodeobject + 1;
                    objectList_D_node_Global.add("null");

                    //  }
                    // }
                    //}
                }
            }
            // System.out.println("size objectList_D_node "+objectList_D_node+" size predicate "+propertyListV_D_node.size()+"size subjectList_D_node "+subjectList_D_node);
            int code = 33;
            for (Map.Entry mapElement : encoderDikstra.entrySet()) {

                if (mapElement.getValue().equals(nodename)) {
                    code = Integer.valueOf(mapElement.getKey().toString());
                }

            }

            cfa2 = cfa2 + 1;
            //  System.out.println("encoder dikstra "+encoderDikstra+" node name-----------> "+nodename+" code "+code+" out "+cfa+" out2 "+cfa2);
            this.dikstraSF(subjectList_D_node, propertyListV_D_node, objectList_D_node, code);
            int counter = 0;
            test = tableValues;
            int freq = 0;
            //System.out.println(" b3");
            for (int p = 0; p < tableValues.size(); p++) {
                for (int l = 0; l < tableValues.size(); l++) {

                    if (tableValues.get(p).getPath().equals(tableValues.get(l).getPath())) {
                        freq = freq + 1;

                    }
                }
                tableValuesDatas.add(new TableValuesDatas(tableValues.get(p).getPath().toString(), tableValues.get(p).getNumberPath(), String.valueOf(freq)));
                freq = 0;
            }

            /////////////////////////////
            encoderDikstra.clear();
            objectMap_D_node.clear();
            objectList_D_node.clear();
            rowsd_nodeobject = 0;

            subjectMap_D_node.clear();
            subjectList_D_node.clear();
            rowsd_nodesubject = 0;

            encoderDikstraString.clear();
            encoderDikstraInteger.clear();

            pairsvValues.clear();

            // tableValues.clear();
            //tableValuesDatas.clear();
            rowsd_node = 0;

            //    System.out.println("next queryyyyyyy ///////");
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    /*store querie in structures*/
    public void getQueryLevelD(String querys, String nodename) {
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = querys;
        try {
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {
                if (pattern.getPredicateVar().getValue() != null) {
                    propertyMap_D.put(pattern.getPredicateVar().getValue().toString(), String.valueOf(rows));
                    propertyList_D.add(pattern.getPredicateVar().getValue().toString());
                    temp_D.add(pattern.getPredicateVar().getValue().toString());
                    rowsd = rowsd + 1;
                }

            }
            System.out.println("////////////////////////");
            ///
            int flag = 0;
            for (String e : temp_D) {
                if (e.equals(/*"http://www.wikidata.org/entity/Q1284"*/nodename)) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                tempAll_D.addAll(temp_D);
            }
            flag = 0;
            temp_D = new ArrayList<String>();
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void getQueryLevelA(String querys) {
        //nodes=node;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = querys;
        // System.out.println("Queries " + query);
        try {
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {
                if (pattern.getSubjectVar().getValue() != null) {
                    nodesMap.put(pattern.getSubjectVar().getValue().toString(), String.valueOf(rows));
                    nodesList.add(pattern.getSubjectVar().getValue().toString());
                    rows = rows + 1;
                }
                if (pattern.getObjectVar().getValue() != null) {
                    nodesMap.put(pattern.getObjectVar().getValue().toString(), String.valueOf(rows));
                    nodesList.add(pattern.getObjectVar().getValue().toString());
                    rows = rows + 1;
                }

            }
            //  System.out.println("next query");
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void getQueryLevelB(String querys) {
        //nodes=node;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = querys;
        //System.out.println("Queries " + query);
        try {
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {
                // System.out.println("Predicates are "+pattern.getPredicateVar().getValue());
                if (pattern.getPredicateVar().getValue() != null) {
                    propertyMap.put(pattern.getPredicateVar().getValue().toString(), String.valueOf(rowsb));
                    propertyList.add(pattern.getPredicateVar().getValue().toString());
                    rowsb = rowsb + 1;
                }
            }
            // System.out.println("next query");
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void Αlgo_A(Integer node) throws FileNotFoundException, IOException {
        nodes = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("C:\\Users\\fanis\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\test\\I3_status500_Joined.tsv"));
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {
            st = new StringTokenizer(dataRow, "\t");
            List<String> dataArray = new ArrayList<String>();
            while (st.hasMoreElements()) {
                dataArray.add(st.nextElement().toString());
            }
            String listString = "";
            for (String s : dataArray) {
                listString += s + "\t";
            }
            listString = listString.split("\\s", 2)[0];
            listString = listString.replace("#anonymizedQuery", "").trim();
            listString = listString.replace("#timestamp", "").trim();
            listString = listString.replace("sourceCategory", "").trim();
            listString = listString.replace("#userAgent", "").trim();
            listString = listString.replace("?query=", "").trim();
            this.setDecode(listString);
            getQueryLevelA(this.getDecode());
            String array[] = new String[dataArray.size()];
            for (int j = 0; j < dataArray.size(); j++) {
                array[j] = dataArray.get(j);
            }
            String[] res = array;
            dataRow = TSVFile.readLine();
        }
        TSVFile.close();
        getNodeFrequency();
        printNodesFrequencyOrder();
        //getFreqData();      
        System.out.println("End proccess");
    }

    public void Αlgo_B(Integer node) throws FileNotFoundException, IOException {
        nodesb = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("C:\\Users\\fanis\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\test\\I3_status500_Joined.tsv"));
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {
            st = new StringTokenizer(dataRow, "\t");
            List<String> dataArray = new ArrayList<String>();
            while (st.hasMoreElements()) {
                dataArray.add(st.nextElement().toString());
            }
            String listString = "";
            for (String s : dataArray) {
                listString += s + "\t";
            }
            listString = listString.split("\\s", 2)[0];
            listString = listString.replace("#anonymizedQuery", "").trim();
            listString = listString.replace("#timestamp", "").trim();
            listString = listString.replace("sourceCategory", "").trim();
            listString = listString.replace("#userAgent", "").trim();
            listString = listString.replace("?query=", "").trim();
            this.setDecode(listString);
            getQueryLevelB(this.getDecode());
            String array[] = new String[dataArray.size()];
            for (int j = 0; j < dataArray.size(); j++) {
                array[j] = dataArray.get(j);
            }
            String[] res = array;
            dataRow = TSVFile.readLine();
        }
        // System.out.println("size map are "+propertyMap.size()+" size array "+propertyList.size());
        TSVFile.close();
        getPropertyFrequency();
        //printSubjectsFrequencyOrder();
        printPropertyFrequencyOrder();
        //getFreqData();      
        //  System.out.println("End proccess");
    }

    public void Algo_C(String nodename, int node) throws FileNotFoundException, IOException {
        nodesc = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("C:\\Users\\fanis\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\test\\test.tsv"));
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {
            st = new StringTokenizer(dataRow, "\t");
            List<String> dataArray = new ArrayList<String>();
            while (st.hasMoreElements()) {
                dataArray.add(st.nextElement().toString());
            }
            String listString = "";
            for (String s : dataArray) {
                listString += s + "\t";
            }
            listString = listString.split("\\s", 2)[0];
            listString = listString.replace("#anonymizedQuery", "").trim();
            listString = listString.replace("#timestamp", "").trim();
            listString = listString.replace("sourceCategory", "").trim();
            listString = listString.replace("#userAgent", "").trim();
            listString = listString.replace("?query=", "").trim();
            this.setDecode(listString);

             // System.out.println("decoder is " + this.getDecode());
            //getQueryLevelC(this.getDecode(), nodename);
            String que = "SELECT DISTINCT ?var1  ?var1Label  ?var2  ?var2Label  ?var3  ?var4 \n"
                    + "WHERE {\n"
                    + "  ?var2 ( <http://www.wikidata.org/prop/direct/P31> / <http://www.wikidata.org/prop/direct/P279> *) <http://www.wikidata.org/entity/Q515> .\n"
                    + "  ?var1  <http://www.wikidata.org/prop/direct/P31>  <http://www.wikidata.org/entity/Q644371> .\n"
                    + "  ?var1  ?var5  ?var2 .\n"
                    + "  ?var1  <http://www.wikidata.org/prop/direct/P238>  ?var3 .\n"
                    + " SERVICE  <http://wikiba.se/ontology#around>   {\n"
                    + "    ?var2  <http://www.wikidata.org/prop/direct/P625>  ?var6 .\n"
                    + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#center>  \"string1\"^^<http://www.opengis.net/ont/geosparql#wktLiteral> .\n"
                    + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#radius>  \"200\".\n"
                    + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#distance>  ?var7 .\n"
                    + "  }\n"
                    + " SERVICE  <http://wikiba.se/ontology#label>   {\n"
                    + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#language>  \"en\".\n"
                    + "  }\n"
                    + " OPTIONAL {\n"
                    + "  ?var2  <http://www.wikidata.org/prop/direct/P625>  ?var4 .\n"
                    + " }\n"
                    + "}\n"
                    + "ORDER BY ASC( ?var7 )\n"
                    + "LIMIT 1";
//System.out.println("decoder is " + this.getDecode());
            getQueryLevelC(this.getDecode(), nodename);
            // getQueryLevelC(que, nodename);

            String array[] = new String[dataArray.size()];
            for (int j = 0; j < dataArray.size(); j++) {
                array[j] = dataArray.get(j);
            }
            String[] res = array;
            dataRow = TSVFile.readLine();
        }
        TSVFile.close();
        getNodeFrequency_C();

        printNodesFrequencyOrder_C();

        System.out.println("All nodes" + allnodes);
        System.out.println("All predicates" + allpredicates);
        // edgemeasure
        System.out.println("Nodes measure " + nodesmeasure + " and edge measure " + edgemeasure + " all queries " + allqueries);
        System.out.println("End proccess");
    }

    public void Algo_Calculation_Random(String nodename) throws FileNotFoundException, IOException {

        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("C:\\Users\\fanis\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\test\\test1.tsv"));
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {
            st = new StringTokenizer(dataRow, "\t");
            List<String> dataArray = new ArrayList<String>();
            while (st.hasMoreElements()) {
                dataArray.add(st.nextElement().toString());
            }
            String listString = "";
            for (String s : dataArray) {
                listString += s + "\t";
            }
            listString = listString.split("\\s", 2)[0];
            listString = listString.replace("#anonymizedQuery", "").trim();
            listString = listString.replace("#timestamp", "").trim();
            listString = listString.replace("sourceCategory", "").trim();
            listString = listString.replace("#userAgent", "").trim();
            listString = listString.replace("?query=", "").trim();
            this.setDecode(listString);

            //  System.out.println("decoder is " + this.getDecode());
            //getQueryLevelC(this.getDecode(), nodename);
            String que = "SELECT DISTINCT ?var1  ?var1Label  ?var2  ?var2Label  ?var3  ?var4 \n"
                    + "WHERE {\n"
                    + "  ?var2 ( <http://www.wikidata.org/prop/direct/P31> / <http://www.wikidata.org/prop/direct/P279> *) <http://www.wikidata.org/entity/Q515> .\n"
                    + "  ?var1  <http://www.wikidata.org/prop/direct/P31>  <http://www.wikidata.org/entity/Q644371> .\n"
                    + "  ?var1  ?var5  ?var2 .\n"
                    + "  ?var1  <http://www.wikidata.org/prop/direct/P238>  ?var3 .\n"
                    + " SERVICE  <http://wikiba.se/ontology#around>   {\n"
                    + "    ?var2  <http://www.wikidata.org/prop/direct/P625>  ?var6 .\n"
                    + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#center>  \"string1\"^^<http://www.opengis.net/ont/geosparql#wktLiteral> .\n"
                    + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#radius>  \"200\".\n"
                    + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#distance>  ?var7 .\n"
                    + "  }\n"
                    + " SERVICE  <http://wikiba.se/ontology#label>   {\n"
                    + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#language>  \"en\".\n"
                    + "  }\n"
                    + " OPTIONAL {\n"
                    + "  ?var2  <http://www.wikidata.org/prop/direct/P625>  ?var4 .\n"
                    + " }\n"
                    + "}\n"
                    + "ORDER BY ASC( ?var7 )\n"
                    + "LIMIT 1";
//System.out.println("decoder is " + this.getDecode());
            getQueryLevelC_Calculation_Random(this.getDecode(), nodename);
            // getQueryLevelC(que, nodename);

            String array[] = new String[dataArray.size()];
            for (int j = 0; j < dataArray.size(); j++) {
                array[j] = dataArray.get(j);
            }
            String[] res = array;
            dataRow = TSVFile.readLine();
        }
        TSVFile.close();

        FinalResultRandom();
        System.out.println("End proccess");
    }

    public void Algo_D(String nodename, int node) throws FileNotFoundException, IOException {
        nodesd = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("C:\\Users\\fanis\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\test\\itest.tsv"));
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {
            st = new StringTokenizer(dataRow, "\t");
            List<String> dataArray = new ArrayList<String>();
            while (st.hasMoreElements()) {
                dataArray.add(st.nextElement().toString());
            }
            String listString = "";
            for (String s : dataArray) {
                listString += s + "\t";
            }
            listString = listString.split("\\s", 2)[0];
            listString = listString.replace("#anonymizedQuery", "").trim();
            listString = listString.replace("#timestamp", "").trim();
            listString = listString.replace("sourceCategory", "").trim();
            listString = listString.replace("#userAgent", "").trim();
            listString = listString.replace("?query=", "").trim();
            this.setDecode(listString);

            System.out.println("decoder is " + this.getDecode());
            getQueryLevelD(this.getDecode(), nodename);

            String array[] = new String[dataArray.size()];
            for (int j = 0; j < dataArray.size(); j++) {
                array[j] = dataArray.get(j);
            }
            String[] res = array;
            dataRow = TSVFile.readLine();
        }
        TSVFile.close();

        getNodeFrequency_D();
        getFreqData_D();
        printNodesFrequencyOrder_D();

        System.out.println("End proccess");
    }
    static int i = 0;

    public void Algo_D_nodes(String nodename, int numbernodes, int node, int moreSem) throws FileNotFoundException, IOException {
        gloVar = moreSem;
        gloName = nodename;
        String que = "SELECT ?var1  ?var1Label \n"
                + "WHERE {\n"
                + " SERVICE  <http://wikiba.se/ontology#label>   {\n"
                + "    <http://www.bigdata.com/rdf#serviceParam>  <http://wikiba.se/ontology#language>  \"fr,en\".\n"
                + "  }\n"
                + "  ?var1  <http://www.wikidata.org/prop/direct/P31>  <http://www.wikidata.org/entity/Q12516> .\n"
                + "  ?var1  <http://www.wikidata.org/prop/direct/P17>  <http://www.wikidata.org/entity/Q79> .\n"
                + " OPTIONAL {\n"
                + "  ?var1  <http://www.wikidata.org/prop/direct/P31>  ?var1 .\n"
                + " }\n"
                + "}";
        this.Algo_C(nodename, numbernodes);
        int cou = 0;
        nodesd_node = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("C:\\Users\\fanis\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\test\\test.tsv"));
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {

            st = new StringTokenizer(dataRow, "\t");
            List<String> dataArray = new ArrayList<String>();
            while (st.hasMoreElements()) {
                dataArray.add(st.nextElement().toString());
            }
            String listString = "";
            for (String s : dataArray) {
                listString += s + "\t";
            }
            listString = listString.split("\\s", 2)[0];
            listString = listString.replace("#anonymizedQuery", "").trim();
            listString = listString.replace("#timestamp", "").trim();
            listString = listString.replace("sourceCategory", "").trim();
            listString = listString.replace("#userAgent", "").trim();
            listString = listString.replace("?query=", "").trim();
            this.setDecode(listString);
            cou = cou + 1;
            // System.out.println("num is decode is "+cou+this.getDecode());
            //  getNodes(this.getDecode(), nodename);

            convertArrayListToMap();
          //  System.out.println("decode is prinn");

            // getQueryLevelD_node(que, nodename);
            getQueryLevelD_node(this.getDecode(), nodename);

            i = i + 1;
//  comperator();

            //System.out.println("sizs are pro  "+propertyList_D_node.size() +" sub "+subjectList_D_node.size()+" ob "+objectList_D_node.size());
            //  System.out.println("size is "+tempAll_C_node.size()+" size map is "+converter.size());
            String array[] = new String[dataArray.size()];
            for (int j = 0; j < dataArray.size(); j++) {
                array[j] = dataArray.get(j);
            }
            String[] res = array;
            dataRow = TSVFile.readLine();
            //  System.out.println("\n next query-------->");
        }

        algorithmPaths();
        TSVFile.close();

        /* getNodeFrequency_D_node();
         getFreqData_D_node();
         printNodesFrequencyOrder_D_node();*/
        System.out.println("End proccess");
    }

    public void algorithmPaths() {

        Collections.sort(tableValues);
        // System.out.println("table values are " + tableValues + " size is " + tableValues.size());
        // System.out.println("Min is " + tableValues.get(0));
        int np = Integer.valueOf(tableValues.get(0).getNumberPath());
        ArrayList<String> temp = new ArrayList<String>();
        for (int r = 0; r < tableValues.size(); r++) {
            if (Integer.valueOf(tableValues.get(r).getNumberPath()) <= Integer.valueOf(tableValues.get(0).getNumberPath())) {
                temp.add(tableValues.get(r).getDecode());
            }
        }

        HashMap<String, String> tempFreq = new HashMap<String, String>();
        HashMap<String, Integer> finalFreq = new HashMap<String, Integer>();
        for (String s : temp) {
            tempFreq.put(s, "");
        }

        for (Map.Entry mapElement : tempFreq.entrySet()) {
            int count = 0;
            for (String e : temp) {
                if (e.equals(mapElement.getKey().toString())) {
                    count = count + 1;
                }
            }
            finalFreq.put(mapElement.getKey().toString(), count);
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : finalFreq.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        //  System.out.println(" semantics" + moreSemantic);
        // System.out.println("more s ias " + moreSemantic.get(gloVar));
        // System.out.println("final path is " + maxEntry.getKey());
        ArrayList<String> orderValues = new ArrayList<String>();
        // String moreSymanticFix=moreSemantic.get(gloVar);
        //// moreSymanticFix=moreSymanticFix.replace("=,", "null");

        String tempVar = null;
        String[] myData = maxEntry.getKey().split("=, ");
        for (String s : myData) {
            tempVar = s.replace("=,", "").replace(" =", "").replace("{", "").replace("}", "");
           // System.out.println(" splitted " + tempVar);
            //
            //if(!s.replace("=,", "null").replace("=", "null").equals("null")){

            // gloName
            // if(!tempVar.equals("null")){
            orderValues.add(tempVar.trim());

            // }
            //}
        }

        ArrayList<String> orderTempValuesResult = new ArrayList<String>();
        ArrayList<String> orderPredicatesTempValuesResult = new ArrayList<String>();
        ArrayList<String> orderValuesResultLast = new ArrayList<String>();
//System.out.println("---  --  -- "+orderValues);
        ArrayList<String> orderValuesResult = new ArrayList<String>();
        for (String e : orderValues) {
            if (!e.equals("")) {
                orderValuesResult.add(e);

            }

        }
        String append = "";
        // System.out.println("Glo name isi " + gloName + " moew semantic " + moreSemantic.get(gloVar));
        append = append + gloName;
        for (String e : orderValuesResult) {
            //System.out.println(e);
            if (!e.equals(gloName) && !e.equals(moreSemantic.get(gloVar))) {
                orderTempValuesResult.add(e);

            }

        }
        append = append + " , ";
        for (String e : orderTempValuesResult) {
            append = append + e + " , ";

        }
        append = " , " + append + moreSemantic.get(gloVar);
        //  if(!Pattern.matches("[^\"''']+", append)){
        if ((!append.startsWith("\"") && !append.endsWith("\""))) {
            System.out.println("Path is " + append);
        } else {
            System.out.println("Its contains literal");
        }

        String[] arrOfStr = append.split(",");

        for (String a : arrOfStr) {

            orderValuesResultLast.add(a.trim());

        }
           // System.out.println(a);

        // System.out.println("ZAZA "+orderValuesResultLast);
        //   System.out.println("ZAZA tripletsValues "+tripletsValues);
        int paths = 0;
        
        
        for (String e : orderValuesResultLast  ) {
            paths=paths+1;
            
            for(int j=0;j<allsubjects.size();j++){
                if( e.equals(allobjects.get(j))  ){
                    if(e.equals(allobjects.get(j))){
                     orderPredicatesTempValuesResult.add(allpredicates.get(j));
                    }
                
                }
            
            }
            
           /*  for(int j=0;j<allobjects.size();j++){
                if(e.equals(allobjects.get(j))){
                     orderPredicatesTempValuesResult.add(allpredicates.get(j));
                
                }
            
            }*/
            
            
           
            

        }
       /* for (String e : orderValuesResultLast) {
            paths = paths + 1;
            for (TripletValues e2 : tripletsValues) {

                if (e.equals(e2.getSubject())) {
                    // System.out.println("mastorrrrrrrrrrr "+e2.getPredicate());
                    orderPredicatesTempValuesResult.add(e2.getPredicate());

                }

                if (e.equals(e2.getObject())) {
                    //  System.out.println("mastorrrrrrrrrrr "+e2.getPredicate());
                    orderPredicatesTempValuesResult.add(e2.getPredicate());

                }

            }

        }*/
        System.out.println("Valuesf are " + orderValuesResultLast);
        System.out.println("Pathsf are " + paths);
        //   }
        /* ArrayList<String> orderValues = new ArrayList<String>();
         for(int i=0;i<orderValues.size();i++){
         orderValues.add();
        
         }*/
        orderValuesResultLast.remove(0);
        setMetrics(orderValuesResultLast, orderPredicatesTempValuesResult);
    }

    public void FinalResult() {

        // System.out.println("FinalResultgather_temp_C_Calculation are "+gather_temp_C_Calculation);
        // System.out.println("FinalResultgather_temp_C_Calculation_Predicate are "+gather_temp_C_Calculation_Predicate);
        double sum = 0;
        for (int v = 0; v < gather_temp_C_Calculation.size(); v++) {
            sum = sum + gather_temp_C_Calculation.get(v);
        }
        double sumPr = 0;
        for (int v = 0; v < gather_temp_C_Calculation_Predicate.size(); v++) {
            sumPr = sumPr + gather_temp_C_Calculation_Predicate.get(v);
        }

        System.out.println("///////////////////FINAL RESULT///////////////////////////");
        System.out.println("Total nodes coverage " + sum / gather_temp_C_Calculation.size());
        System.out.println("Total edges coverage " + sumPr / gather_temp_C_Calculation_Predicate.size());
        System.out.println("///////////////////END RESULT///////////////////////////");

    }

    public void Algo_Calculation(String nodename, int node) throws FileNotFoundException, IOException {
        nodesd = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("C:\\Users\\fanis\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\test\\test.tsv"));
        String dataRow = TSVFile.readLine();
        while (dataRow != null) {
            st = new StringTokenizer(dataRow, "\t");
            List<String> dataArray = new ArrayList<String>();
            while (st.hasMoreElements()) {
                dataArray.add(st.nextElement().toString());
            }
            String listString = "";
            for (String s : dataArray) {
                listString += s + "\t";
            }
            listString = listString.split("\\s", 2)[0];
            listString = listString.replace("#anonymizedQuery", "").trim();
            listString = listString.replace("#timestamp", "").trim();
            listString = listString.replace("sourceCategory", "").trim();
            listString = listString.replace("#userAgent", "").trim();
            listString = listString.replace("?query=", "").trim();
            this.setDecode(listString);

            //System.out.println("decoder is " + this.getDecode());
            getQueryLevelC_Calculation(this.getDecode(), nodename);

            String array[] = new String[dataArray.size()];
            for (int j = 0; j < dataArray.size(); j++) {
                array[j] = dataArray.get(j);
            }
            String[] res = array;
            dataRow = TSVFile.readLine();
        }
        TSVFile.close();

        FinalResult();
        System.out.println("End algo all");
        System.out.println("End proccess");
    }

    public void setMetrics(ArrayList<String> orderValuesResultLast, ArrayList<String> orderPredicatesTempValuesResult) {

        int sizeProperties = orderValuesResultLast.size();

        HashMap<String, String> uniqueProperties = new HashMap<String, String>();
        ArrayList<String> subTempEdges = new ArrayList<String>();
        ArrayList<String> subFinalEdges = new ArrayList<String>();
        for (String e : orderPredicatesTempValuesResult) {
            uniqueProperties.put(e, "");

        }
        for (Map.Entry<String, String> entry : uniqueProperties.entrySet()) {
            subTempEdges.add(entry.getKey());
        }

        if (orderValuesResultLast.size() > 0) {
            System.out.println("in faaannn+subTempEdges" + subTempEdges + " -- " + subTempEdges.size() + sizeProperties);
            sizeProperties = orderValuesResultLast.size() - 1;
        }

        for (int y = 0; y < subTempEdges.size(); y++) {
            subFinalEdges.add(subTempEdges.get(y));
            System.out.println("hi");
            //  
        }

        System.out.println("Final nodes last " + orderValuesResultLast);
        System.out.println("Final last " + subFinalEdges);

        finalNodes.addAll(orderValuesResultLast);
        finalEdges.addAll(subFinalEdges);

        print();
    }

    public void print() {

        System.out.println("Final nodes all " + finalNodes);
        System.out.println("Final edges all " + finalEdges);
    }

    public void Calculate() {
        HashMap<String, String> calculateNodes = new HashMap<String, String>();
        HashMap<String, String> calculateEdges = new HashMap<String, String>();
        for (String e : finalNodes) {
            calculateNodes.put(e, "");
        }
        for (String e : finalEdges) {
            calculateEdges.put(e, "");
        }

        for (Map.Entry<String, String> entry : calculateNodes.entrySet()) {
            calculateNodesArray.add(entry.getKey());
        }
        for (Map.Entry<String, String> entry : calculateEdges.entrySet()) {
            calculateEdgesArray.add(entry.getKey());
        }

        //  System.out.println("calculate nodes "+calculateNodesArray);
        // System.out.println("calculate edges "+calculateEdgesArray);
    }

    public void algorithmPaths2() {
        Collections.sort(tableValues);
        System.out.println("table values are " + tableValues + " size is " + tableValues.size());
        System.out.println("Min is " + tableValues.get(0));
        int np = Integer.valueOf(tableValues.get(0).getNumberPath());
        ArrayList<String> temp = new ArrayList<String>();
        for (int r = 0; r < tableValues.size(); r++) {
            if (Integer.valueOf(tableValues.get(r).getNumberPath()) <= Integer.valueOf(tableValues.get(0).getNumberPath())) {
                temp.add(tableValues.get(r).getPath());
            }
        }

        HashMap<String, String> tempFreq = new HashMap<String, String>();
        HashMap<String, Integer> finalFreq = new HashMap<String, Integer>();
        for (String s : temp) {
            tempFreq.put(s, "");
        }

        for (Map.Entry mapElement : tempFreq.entrySet()) {
            int count = 0;
            for (String e : temp) {
                if (e.equals(mapElement.getKey().toString())) {
                    count = count + 1;
                }
            }
            finalFreq.put(mapElement.getKey().toString(), count);
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : finalFreq.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        System.out.println("final path is " + maxEntry.getKey());

    }
    /*public void Calculate_Random(){
        
     System.out.println("Prin calculate calculateNodesArray "+calculateNodesArray);
     System.out.println("Prin calculate calculateEdgesArray "+calculateEdgesArray);
        
     
        
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q500548");
     calculateNodesArrayRandom.add("http://www.bigdata.com/rdf/gas#program");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q24283660");
     calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
       
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q3305213");
     calculateNodesArrayRandom.add("http://wikiba.se/ontology#Property");
        
        
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P1459");
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P373");
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P718");
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P18");
     calculateEdgesArrayRandom.add("http://wikiba.se/ontology#language");
     System.out.println("Meta calculate calculateNodesArrayRandom "+calculateNodesArrayRandom);
     System.out.println("Meta calculate calculateEdgesArrayRandom "+calculateEdgesArrayRandom);
     //////////////////test-play2
        
      
        
        
     }*/
    /*
     public void Calculate_Random(){
        
     System.out.println("Prin calculate calculateNodesArray "+calculateNodesArray);
     System.out.println("Prin calculate calculateEdgesArray "+calculateEdgesArray);
        
     
        
     calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q3455803");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/P1449");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q2714270");
       
     calculateNodesArrayRandom.add("https://fr.wikipedia.org/");
        
        
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P279");
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P625");
     calculateEdgesArrayRandom.add("http://wikiba.se/ontology#cornerNorthEast");
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P276");
      
     System.out.println("Meta calculate calculateNodesArrayRandom "+calculateNodesArrayRandom);
     System.out.println("Meta calculate calculateEdgesArrayRandom "+calculateEdgesArrayRandom);
     //////////////////test-play3
        
      
        
        
     }
     */

    /*
     public void Calculate_Random(){
        
     System.out.println("Prin calculate calculateNodesArray "+calculateNodesArray);
     System.out.println("Prin calculate calculateEdgesArray "+calculateEdgesArray);
        
     
        
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q889");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q93301");
     calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q5");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q30524710");
         
         
      
         
     calculateEdgesArrayRandom.add("http://wikiba.se/ontology#language");
     calculateEdgesArrayRandom.add("http://schema.org/inLanguage");
     calculateEdgesArrayRandom.add("http://schema.org/isPartOf");
     calculateEdgesArrayRandom.add("http://schema.org/about");
      
     System.out.println("Meta calculate calculateNodesArrayRandom "+calculateNodesArrayRandom);
     System.out.println("Meta calculate calculateEdgesArrayRandom "+calculateEdgesArrayRandom);
     //////////////////test-play4
        
      
        
        
     }*/
    /*
     public void Calculate_Random(){
        
     System.out.println("Prin calculate calculateNodesArray "+calculateNodesArray);
     System.out.println("Prin calculate calculateEdgesArray "+calculateEdgesArray);
        
     
        
     calculateNodesArrayRandom.add("http://www.bigdata.com/rdf#serviceParam");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q327332");
     calculateNodesArrayRandom.add("http://www.bigdata.com/rdf#serviceParam");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q33999");
     calculateNodesArrayRandom.add("http://www.bigdata.com/rdf/gas#program");
         
         
      
         
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P31");
     calculateEdgesArrayRandom.add("http://schema.org/description");
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P21");
     calculateEdgesArrayRandom.add("http://www.w3.org/2000/01/rdf-schema#label");
      
     System.out.println("Meta calculate calculateNodesArrayRandom "+calculateNodesArrayRandom);
     System.out.println("Meta calculate calculateEdgesArrayRandom "+calculateEdgesArrayRandom);
     //////////////////test-play5
        
      
        
        
     }*/
    /*  public void Calculate_Random(){
        
     System.out.println("Prin calculate calculateNodesArray "+calculateNodesArray);
     System.out.println("Prin calculate calculateEdgesArray "+calculateEdgesArray);
        
     
        
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q901894");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q958245");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q97080");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q24090");
     calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q29530");
         
         
      
         
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/statement/P39");
     calculateEdgesArrayRandom.add("http://schema.org/about");
     calculateEdgesArrayRandom.add("http://wikiba.se/ontology#language");
     calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/qualifier/P580");
      
     System.out.println("Meta calculate calculateNodesArrayRandom "+calculateNodesArrayRandom);
     System.out.println("Meta calculate calculateEdgesArrayRandom "+calculateEdgesArrayRandom);
     //////////////////test-play6
        
      
        
        
     }*/
    public void testRandmom() throws IOException {
        int min = 1;
        int max = 20;

        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        //  System.out.println(random_int);

        for (int k = 0; k < 10; k++) {
            Calculate_Random();
            random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            ArrayList<String> temp = new ArrayList<String>();
            ArrayList<String> temp2 = new ArrayList<String>();
            for (int a = 0; a < random_int; a++) {
                temp.add(calculateNodesArrayRandom.get(a));
            }

            for (int a = 0; a < random_int; a++) {
                temp2.add(calculateEdgesArrayRandom.get(a));
            }

            calculateNodesArrayRandom.clear();
            calculateNodesArrayRandom = temp;

            calculateEdgesArrayRandom.clear();
            calculateEdgesArrayRandom = temp2;

            // System.out.println("Vars are "+calculateNodesArrayRandom);
            Algo_Calculation_Random("http://www.bigdata.com/rdf#serviceParam");
        }

        /*  Random rand = new Random();
         int upperbound = 50;
         int int_random = rand.nextInt(upperbound); 
         for(int k=0;k<100;k++){
         Calculate_Random();
         int_random = rand.nextInt(upperbound); 
         System.out.println("Random integer value from 0 to" + (upperbound-1) + " : "+ int_random);     
         ArrayList<String> temp=new ArrayList<String>();
         for(int a=0;a<int_random-1;a++){
         temp.add(calculateNodesArrayRandom.get(a));
      
         }
         System.out.println("Vars are "+temp);
         calculateNodesArrayRandom.clear();
         calculateNodesArrayRandom=temp;   
         //  Algo_Calculation_Random();
         }*/
    }

    public void Calculate_Random() {

        // System.out.println("Prin calculate calculateNodesArray "+calculateNodesArray);
        //System.out.println("Prin calculate calculateEdgesArray "+calculateEdgesArray);
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q901894");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q958245");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q97080");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q24090");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q29530");
        calculateNodesArrayRandom.add("http://www.bigdata.com/rdf#serviceParam");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q327332");
        calculateNodesArrayRandom.add("http://www.bigdata.com/rdf#serviceParam");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q33999");
        calculateNodesArrayRandom.add("http://www.bigdata.com/rdf/gas#program");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q889");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q93301");
        calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q5");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q30524710");
        calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q3455803");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/P1449");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q2714270");
        calculateNodesArrayRandom.add("https://fr.wikipedia.org/");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q500548");
        calculateNodesArrayRandom.add("http://www.bigdata.com/rdf/gas#program");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q24283660");
        calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q3305213");
        calculateNodesArrayRandom.add("http://wikiba.se/ontology#Property");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q532");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q1248784");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q13100073");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q1190554");
        calculateNodesArrayRandom.add("http://wikiba.se/ontology#PreferredRank");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q11696");
        calculateNodesArrayRandom.add("http://www.w3.org/2001/XMLSchema#integer");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q54939");
        calculateNodesArrayRandom.add("http://wikiba.se/ontology#Monolingualtext");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q19595382");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q659396");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q142");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q104123");
        calculateNodesArrayRandom.add("http://www.wikidata.org/prop/direct/P577");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q104123");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q30");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q11570");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q3647172");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q4167836");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q8617207");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q11424");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q3918");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q298");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q5398426");
        calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q21191270");

        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/statement/P39");
        calculateEdgesArrayRandom.add("http://schema.org/about");
        calculateEdgesArrayRandom.add("http://wikiba.se/ontology#language");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/qualifier/P580");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P31");
        calculateEdgesArrayRandom.add("http://schema.org/description");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P21");
        calculateEdgesArrayRandom.add("http://www.w3.org/2000/01/rdf-schema#label");
        calculateEdgesArrayRandom.add("http://wikiba.se/ontology#language");
        calculateEdgesArrayRandom.add("http://schema.org/inLanguage");
        calculateEdgesArrayRandom.add("http://schema.org/isPartOf");
        calculateEdgesArrayRandom.add("http://schema.org/about");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P279");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P625");
        calculateEdgesArrayRandom.add("http://wikiba.se/ontology#cornerNorthEast");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P276");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P1459");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P373");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P718");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P18");
        calculateEdgesArrayRandom.add("http://wikiba.se/ontology#language");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P527");
        calculateEdgesArrayRandom.add("http://www.w3.org/2000/01/rdf-schema#label");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P1441");
        calculateEdgesArrayRandom.add("http://schema.org/isPartOf");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P17");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P170");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P495");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P793");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P88");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P186");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P180");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P195");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P608");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P1071");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P856");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P608");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P1476");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P2049");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P136");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P373");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P1476");
        calculateEdgesArrayRandom.add("http://wikiba.se/ontology#center");
        calculateEdgesArrayRandom.add("http://wikiba.se/ontology#radius");
        calculateEdgesArrayRandom.add("http://wikiba.se/ontology#distance");

        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P608");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P1476");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P2049");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P136");
        calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P373");
        //end new edges 25
        //System.out.println("Meta calculate calculateNodesArrayRandom "+calculateNodesArrayRandom);
        //System.out.println("Meta calculate calculateEdgesArrayRandom "+calculateEdgesArrayRandom);
        //////////////////test-play7

    }

    public void test() {
//ArrayList<Employee> list = new ArrayList<>();

 //ArrayList<Employee> emp = new ArrayList<Employee>();
//System.out.println(list);
        /*   List<Pairs<Integer, String>> pairList = new ArrayList<Pairs<Integer, String>>();//first way
         pairList.add(new Pairs<Integer, String>(90, "Student A"));
         pairList.add(new Pairs<Integer, String>(90, "Student A"));
         for (Pairs<Integer, String> a : pairList) {
         System.out.println(a.getKeys() + " - " + a.getValue());
         }*/
        //ArrayList<Employee> emp = new ArrayList<Employee>();
        //emp.add(new Employee("1", "Alex", "sa"));
        /*      
         emp.add(new Employee(1l, "Alex", LocalDate.of(2018, Month.APRIL, 21)));
         emp.add(new Employee(4l, "Brian", LocalDate.of(2018, Month.APRIL, 22)));
         emp.add(new Employee(3l, "Piyush", LocalDate.of(2018, Month.APRIL, 25)));
         emp.add(new Employee(5l, "Charles", LocalDate.of(2018, Month.APRIL, 23)));
         emp.add(new Employee(2l, "Pawan", LocalDate.of(2018, Month.APRIL, 24)));

         Collections.sort(list);*/
    }

    @Override
    public int compare(QueriesFunctionWikidata o1, QueriesFunctionWikidata o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
