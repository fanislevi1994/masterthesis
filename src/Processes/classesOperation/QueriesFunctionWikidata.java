/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes.classesOperation;

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

/**
 *
 * @author fanis
 */
public class QueriesFunctionWikidata implements Comparator<QueriesFunctionWikidata> {

    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
    private String e;

    public void dikstraSF(ArrayList<String> object, ArrayList<String> propertyListV_D_nodeS, ArrayList<String> subject, int sources) {
        int v = 1011;
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
                    if (objectList_D_node.get(i).equals(entry.getValue())) {
                        x = entry.getKey();
                        a = entry.getValue();
                    }
                    if (subjectList_D_node.get(i).equals(entry.getValue())) {
                        y = entry.getKey();
                        c = entry.getValue();
                    }
                    addEdge(adj, x, y);
                }
            }
        }
        for (int g = 0; g < subjectList_D_node.size(); g++) {

            if ((subjectList_D_node.size() == propertyListV_D_node.size()) && (propertyListV_D_node.size() == objectList_D_node.size())) {
                tripletsValues.add(new TripletValues(subjectList_D_node.get(g), propertyListV_D_node.get(g), objectList_D_node.get(g)));
            }
        }
        for (Map.Entry<Integer, String> entry : encoderDikstra.entrySet()) {
            if (entry.getValue().equals(moreSemantic.get(gloVar))) {//4 beauty
                gathercode.add(entry.getKey());
                int dest = entry.getKey();
                printShortestDistance(adj, sources, dest, v);
            }
        }
    }
    static int allc = 0;

    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
            int dest, int v, int pred[], int dist[]) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean visited[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
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

    private static void printShortestDistance(
            ArrayList<ArrayList<Integer>> adj,
            int s, int dest, int v) {
        Triplet<String, Integer> valuesSort = new Triplet<>("", 1);
        ArrayList<TableValues> allValues = new ArrayList<TableValues>();
        ArrayList<String> test = new ArrayList<String>();
        HashMap<String, String> tempPath = new HashMap<String, String>();
        values = ",";
        lengthpath = 0;
        int pred[] = new int[v];
        int dist[] = new int[v];
        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination"
                    + "are not connected");
            return;
        }
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
        lengthpath = dist[dest];
        String append = "";
        String append2 = "";
        String fin = "";
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print("\n" + path.get(i) + " ");
            append = append + path.get(i) + " ";

        }
        allValues.add(new TableValues(append, String.valueOf(dist[dest]), 1, ""));
        for (Map.Entry<Integer, String> entry : encoderDikstra.entrySet()) {
            for (int i = path.size() - 1; i >= 0; i--) {
                if (entry.getKey().equals(path.get(i))) {
                    System.out.print(" <-> " + entry.getValue());
                    append2 = append2 + entry.getValue() + " ";
                }
            }
            test.add(append2);
            tempPath.put(append2, "");
            append2 = "";
        }

        glob = glob + 1;
        pairsvValues.put(append, dist[dest]);
        tableValues.add(new TableValues(append, String.valueOf(dist[dest]), glob, String.valueOf(tempPath)));
    }

    public void getFrequency(ArrayList<TableValues> table) {

    }

    private static void printShortestDistance2(
            ArrayList<ArrayList<Integer>> adj,
            int s, int dest, int v) {
        int pred[] = new int[v];
        int dist[] = new int[v];
        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination"
                    + "are not connected");
            return;
        }
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
        System.out.println("Shortest path length is: " + dist[dest]);
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    public static void writeToFile() {
        pairs.add("");
        for (int i = 1; i < pairs.size() - 1; i++) {
            System.out.println("<" + pairs.get(i - 1) + "> " + "<>" + " <" + pairs.get(i) + ">.");
        }
        pairs.clear();
    }
    String flaglit = null;
    int kfa = 0;
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
    int kaou = 0;
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

    ArrayList<String> tempAll_C_Update = new ArrayList<String>();

    static HashMap<Integer, String> encoderDikstra = new HashMap<Integer, String>();
    static HashMap<String, Integer> pairsvValues = new HashMap<String, Integer>();

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
    ArrayList<String> allobjects = new ArrayList<String>();
    ArrayList<String> allsubjects = new ArrayList<String>();

    ArrayList<String> calculateNodesArrayRandom = new ArrayList<String>();
    ArrayList<String> calculateEdgesArrayRandom = new ArrayList<String>();
    ArrayList<String> temp_C_Calculation_Random = new ArrayList<String>();
    ArrayList<String> temp_C_Calculation_Predicate_Random = new ArrayList<String>();
    ArrayList<Float> gather_temp_C_Calculation_Random = new ArrayList<Float>();
    ArrayList<Float> gather_temp_C_Calculation_Predicate_Random = new ArrayList<Float>();

    static int nodesmeasure = 0;
    static int edgemeasure = 0;
    static int allqueries = 0;

    public void listFilesForFolder(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String filePath = "C:\\Users\\fanis\\OneDrive\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\queries\\dbpedia3.8-correct\\" + fileEntry.getName().toString() + "";
                System.out.println("filepath is " + filePath);
            }
        }
    }

    private static String readBytesFile(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {

        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void setDecode(String url) {
        this.decoder = url;

    }

    public String getDecode() {
        try {
            String result = java.net.URLDecoder.decode(this.decoder, StandardCharsets.UTF_8.name());
            return result;
        } catch (UnsupportedEncodingException e) {

        }
        return null;
    }

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
            myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\sortWIKIdata.txt");
            for (int i = 0; i < nodes; i++) {
                System.out.println("Top " + nodes + " nodes are " + topElementsKeyS_A.get(i) + " with frequency " + topElementsValues_A.get(i));
                String allsentence = "Top " + nodes + " nodes are  " + topElementsKeyS_A.get(i) + " frequency "
                        + topElementsValues_A.get(i) + "\n";
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
            myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\topKNode.txt");
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
            myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\topKNodes.txt");
            ArrayList<String> tempkey = new ArrayList<String>();
            ArrayList<Integer> tempvalue = new ArrayList<Integer>();
            for (int g = 0; g < tempAll_C.size(); g++) {
                tempAll_C_Not_Dubl.put(tempAll_C.get(g), 0);
            }
            for (Map.Entry mapElement : tempAll_C_Not_Dubl.entrySet()) {
                tempAll_C_Update.add(mapElement.getKey().toString());
            }
            for (int g = 0; g < tempAll_C_Update.size(); g++) {
                for (int h = 0; h < topElementsKeyS_C.size(); h++) {
                    if (tempAll_C_Update.get(g).equals(topElementsKeyS_C.get(h))) {
                        tempkey.add(topElementsKeyS_C.get(h));
                        tempvalue.add(topElementsValues_C.get(h));
                    }
                }
            }
            if (nodesc <= tempkey.size()) {
                for (int i = 0; i < nodesc; i++) {
                    moreSemantic.add(tempkey.get(i));
                    String allsentence = "Topf " + nodesc + " nodes are  " + tempkey.get(i) + " frequency "
                            + tempvalue.get(i) + "\n";
                    System.out.println(allsentence);
                    myWriter.write(allsentence);
                }
            } else {
                System.out.println("size not compact");
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

    public void printTopK_D() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\outputK5FANI.txt");
            System.out.println("nodesd " + topElementsKeyS_D.size());
            for (int i = 0; i < nodesd; i++) {
                System.out.println("Topnouli " + nodesd + " nodes are " + topElementsKeyS_D.get(i) + " with frequency " + topElementsValues_D.get(i));
                String allsentence = "Top " + nodesd + " nodes are  " + topElementsKeyS_D.get(i) + " frequency "
                        + topElementsValues_D.get(i) + "\n";
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

    public void printTopK_D_node() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\outputK5.txt");
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

    public void printNodesFrequencyOrder() {
        final Map<String, Integer> sortedByCount = sortByValue(nodeMapFrequency);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\allnodesFrequency.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                count = count + 1;
                String allsentence = "Querie " + count + " node " + entry.getKey() + " frequency "
                        + entry.getValue() + "\n";
                myWriter.write(allsentence);
                topElementsKeyS_A.add(entry.getKey());
                topElementsValues_A.add(entry.getValue());
            }
            myWriter.close();
            printTopK_A();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printPropertyFrequencyOrder() {
        final Map<String, Integer> sortedByCount = sortByValue(propertyMapFrequency);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\alledgesFrequency.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                count = count + 1;
                String allsentence = "Querie " + count + " node " + entry.getKey() + " frequency "
                        + entry.getValue() + "\n";
                myWriter.write(allsentence);
                topElementsKeyS_B.add(entry.getKey());
                topElementsValues_B.add(entry.getValue());
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
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\allnodesFrequency.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                if (!entry.getKey().equals(" \"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral>") && !entry.getKey().equals("\"de,en\"") && !entry.getKey().equals("\"en,da,de,es,fr,jp,nl,no,ru,sv,zh\"") && !entry.getKey().equals("\"nl,fr,en,de,it,es,no,pt\"") && !entry.getKey().equals("\"tt0053589\"") && !entry.getKey().equals("\"fr,en\"") && !entry.getKey().equals("\"200\"") && !entry.getKey().equals("\"fr,en\"") && !entry.getKey().equals("null") && !entry.getKey().equals("\"fr,en\"") && !entry.getKey().equals("\"en,en,fr\"") && !entry.getKey().equals("\"fr,en\"") && !entry.getKey().equals("\"tt0454873\"") && !entry.getKey().equals("\"en,pt,en\"") && !entry.getKey().equals("\"[AUTO_LANGUAGE],en\"") && !entry.getKey().equals("\"tt0071301\"") && !entry.getKey().equals("\"en,fr,de,ru,es,zh,jp\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"en,en\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"en\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"tt0061410\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"tt0122613\"") && !entry.getKey().equals("\"fr") && !entry.getKey().equals("\"tt0061410\"") && !entry.getKey().equals("\"en,da,de,es,fr,jp,no,ru,sv,zh\"") && !entry.getKey().equals("http://wikiba.se/ontology#Property") && !entry.getKey().equals("\"string1\"") && !entry.getKey().equals("\"en\"")) {
                    topElementsKeyS_C.add(entry.getKey());
                    topElementsValues_C.add(entry.getValue());
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
        if (kaou == 0) {
            printTopK_C();
            kaou = 2;

        }
    }

    public void getLevelOrder_D() throws IOException {
        final Map<String, Integer> sortedByCount = sortByValue(tempAllMap_D);
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\allnodesFrequencyV3.txt");
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
            FileWriter myWriter = new FileWriter("C:\\Users\\30694\\OneDrive\\Desktop\\outputD_node.txt");
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

    public void printNodesFrequencyOrder_C() {
        final Map<String, Integer> sortedByCount = sortByValue(nodeMapFrequency_C);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\30694\\OneDrive\\Desktop\\output_D.txt");
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

    public void printNodesFrequencyOrder_D() {
        System.out.println("/////////////////");
        final Map<String, Integer> sortedByCount = sortByValue(propertyMapFrequency_D);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\30694\\OneDrive\\Desktop\\outputE.txt");
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

    public void printNodesFrequencyOrder_D_node() {
        final Map<String, Integer> sortedByCount = sortByValue(propertyMapFrequency_D_node);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\30694\\OneDrive\\Desktop\\outputE.txt");
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
    }

    public void FinalResultRandom() {
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
        System.out.println("Total edges coverage " + sumPr / gather_temp_C_Calculation_Predicate_Random.size());
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
                    char[] ch = pattern.getSubjectVar().getValue().toString().toCharArray();
                    for (int i = 0; i < ch.length; i++) {
                        if (ch[i] == '"' || ch[i] == '^') {
                            flaglit = "yes";
                        } else {
                            flaglit = "no";
                        }
                    }
                    if (flaglit != "yes") {
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
                    } else {
                        nodesMap_C.put("null", String.valueOf(rows));
                        nodesList_C.add("null");
                        temp_C.add("null");
                        rowsc = rowsc + 1;
                        nodesmeasure = nodesmeasure + 1;
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
                    char[] ch = pattern.getObjectVar().getValue().toString().toCharArray();
                    for (int i = 0; i < ch.length; i++) {
                        if (ch[i] == '"' || ch[i] == '^') {
                            flaglit = "yes";
                        } else {
                            flaglit = "no";
                        }
                    }
                    if (flaglit != "yes") {
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
                    } else {
                        nodesMap_C.put("null", String.valueOf(rows));
                        nodesList_C.add("null");
                        temp_C.add("null");
                        rowsc = rowsc + 1;
                        nodesmeasure = nodesmeasure + 1;
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
            int flag = 0;
            for (String e : temp_C) {
                if (e.equals(nodename)) {
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
        for (int i = 0; i < calculateNodesArrayRandom.size(); i++) {
            for (int j = 0; j < temp_C_Calculation_Random.size(); j++) {
                if (calculateNodesArrayRandom.get(i).trim().equals(temp_C_Calculation_Random.get(j).trim())) {
                    arithm = arithm + 1;
                }
            }
        }
        float paranom = temp_C_Calculation_Random.size();
        float klasma = 0;
        if (paranom != 0) {
            klasma = arithm / paranom;
        }
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
        gather_temp_C_Calculation_Predicate_Random.add(klasmaPredicate);
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
        gather_temp_C_Calculation.add(klasma);
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
        gather_temp_C_Calculation_Predicate.add(klasmaPredicate);
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
                } else if (pattern.getSubjectVar().getValue() == null) {
                    temp_C_Calculation.add("null");
                }
                if (pattern.getPredicateVar().getValue() != null) {
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
                } else if (pattern.getObjectVar().getValue() == null) {
                    temp_C_Calculation.add("null");
                }
            }
            int flag = 0;
            for (String e : temp_C_Calculation) {
                if (e.equals(nodename)) {
                    flag = 1;
                }
            }
            if (flag == 1) {
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

    public void comperator() {
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
                allPaths.add(Integer.valueOf(fin[i]));
                tablePaths.add(new TablePaths(i, Integer.valueOf(fin[i])));
            }
        }
        System.out.println("Shortest path final is " + shortestPath);
    }

    public void algorithm2() {
    }

    public static int getIntValue(Value v, int fallback) {
        if (v instanceof Literal) {
            return getIntValue((Literal) v, fallback);
        } else {
            return fallback;
        }
    }

    public void getQueryLevelD_node(String querys, String nodename) {
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
                if (pattern.getSubjectVar().getValue() != null) {
                    if (pattern.getSubjectVar().getValue().toString().trim().contains("literal") || pattern.getSubjectVar().getValue().toString().trim().contains("''") || pattern.getSubjectVar().getValue().toString().trim().contains("'") || pattern.getSubjectVar().getValue().toString().trim().contains("Literal")) {
                    }
                }
                if (pattern.getObjectVar().getValue() != null) {
                    if (pattern.getObjectVar().getValue().toString().trim().contains("literal") || pattern.getObjectVar().getValue().toString().trim().contains("''") || pattern.getObjectVar().getValue().toString().trim().contains("'") || pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {
                    }

                }
                if (pattern.getSubjectVar().getValue() != null) {
                    if (pattern.getSubjectVar().getValue().toString().trim().contains("\"pl,en\"")) {

                    }
                    if (!pattern.getSubjectVar().getValue().toString().endsWith("\"") || !pattern.getSubjectVar().getValue().toString().startsWith("\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"pl,en\"") || !pattern.getSubjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getSubjectVar().getValue().toString().trim().contains("literal") || !pattern.getSubjectVar().getValue().toString().trim().contains("''") || !pattern.getSubjectVar().getValue().toString().trim().contains("'") || !pattern.getSubjectVar().getValue().toString().trim().contains("Literal")) {
                        subjectList_D_node.add(pattern.getSubjectVar().getValue().toString());
                        encoderDikstra.put(rowsd_node, pattern.getSubjectVar().getValue().toString());
                        rowsd_node = rowsd_node + 1;
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
                }
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
                    int tj = 0;
                    if (!pattern.getObjectVar().getValue().toString().endsWith("\"") || !pattern.getObjectVar().getValue().toString().startsWith("\"") || !pattern.getObjectVar().getValue().toString().trim().contains("pl,en") || !pattern.getObjectVar().getValue().toString().trim().contains("\"POINT(17 53)\"^^<http://www.opengis.net/ont/geosparql#wktLiteral") || !pattern.getObjectVar().getValue().toString().trim().contains("literal") || !pattern.getObjectVar().getValue().toString().trim().contains("''") || !pattern.getObjectVar().getValue().toString().trim().contains("'") || !pattern.getObjectVar().getValue().toString().trim().contains("Literal")) {
                        objectList_D_node.add(pattern.getObjectVar().getValue().toString());
                        encoderDikstra.put(rowsd_node, pattern.getObjectVar().getValue().toString());
                        rowsd_node = rowsd_node + 1;
                    } else if (pattern.getObjectVar().getValue() == null) {
                        objectList_D_node.add("null");
                        encoderDikstraString.add("null");
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodeobject = rowsd_nodeobject + 1;
                        objectList_D_node_Global.add("null");

                    }
                } else if (pattern.getObjectVar().getValue() == null) {
                    objectList_D_node.add("null");
                    encoderDikstraString.add("null");
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodeobject = rowsd_nodeobject + 1;
                    objectList_D_node_Global.add("null");
                }
            }
            int code = 33;
            for (Map.Entry mapElement : encoderDikstra.entrySet()) {

                if (mapElement.getValue().equals(nodename)) {
                    code = Integer.valueOf(mapElement.getKey().toString());
                }
            }
            cfa2 = cfa2 + 1;
            this.dikstraSF(subjectList_D_node, propertyListV_D_node, objectList_D_node, code);
            int counter = 0;
            test = tableValues;
            int freq = 0;
            for (int p = 0; p < tableValues.size(); p++) {
                for (int l = 0; l < tableValues.size(); l++) {
                    if (tableValues.get(p).getPath().equals(tableValues.get(l).getPath())) {
                        freq = freq + 1;
                    }
                }
                tableValuesDatas.add(new TableValuesDatas(tableValues.get(p).getPath().toString(), tableValues.get(p).getNumberPath(), String.valueOf(freq)));
                freq = 0;
            }
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
            rowsd_node = 0;
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    /*store queries in structures*/
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
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void getQueryLevelB(String querys) {
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
                    propertyMap.put(pattern.getPredicateVar().getValue().toString(), String.valueOf(rowsb));
                    propertyList.add(pattern.getPredicateVar().getValue().toString());
                    rowsb = rowsb + 1;
                }
            }
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        }
    }

    public void Αlgo_A(Integer node) throws FileNotFoundException, IOException {
        nodes = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("noUsed"));
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
        System.out.println("End proccess");
    }

    public void Αlgo_B(Integer node) throws FileNotFoundException, IOException {
        nodesb = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("C:\\Users\\30694\\OneDrive\\Desktop\\metaptixiakall\\metaptixiako\\kondilakis\\metaptixiaki-ergasia\\test\\I3_status500_Joined.tsv"));
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
        TSVFile.close();
        getPropertyFrequency();
        printPropertyFrequencyOrder();
    }

    public void Algo_C(String nodename, int node) throws FileNotFoundException, IOException {
        nodesc = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("src\\WorkloadsDatasets\\wikidata\\mergedata.tsv"));//here should put train and in line 1631 
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
            //System.out.println("decoder is " + this.getDecode());//if you want to see queries decode
            getQueryLevelC(this.getDecode(), nodename);
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
        System.out.println("End proccess");
    }

    public void Algo_Calculation_Random(String nodename) throws FileNotFoundException, IOException {
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("src\\WorkloadsDatasets\\wikidata\\mergedata.tsv"));//wikidata\test.tsv //here you can put test.tsv for evaluation
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
            getQueryLevelC_Calculation_Random(this.getDecode(), nodename);
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

    public void Algo_D(String nodename, int node) throws FileNotFoundException, IOException {//
        nodesd = node;
        StringTokenizer st;
        BufferedReader TSVFile = new BufferedReader(new FileReader("notUsed"));
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
        System.out.println("End proccess D");
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
        BufferedReader TSVFile = new BufferedReader(new FileReader("src\\WorkloadsDatasets\\wikidata\\mergedata.tsv"));//here can put train.tsv
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
            convertArrayListToMap();
            getQueryLevelD_node(this.getDecode(), nodename);
            i = i + 1;
            String array[] = new String[dataArray.size()];
            for (int j = 0; j < dataArray.size(); j++) {
                array[j] = dataArray.get(j);
            }
            String[] res = array;
            dataRow = TSVFile.readLine();
        }
        algorithmPaths();
        TSVFile.close();
        System.out.println("End proccess");
    }

    public void algorithmPaths() {
        Collections.sort(tableValues);
        if (tableValues.size() != 0) {
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
            ArrayList<String> orderValues = new ArrayList<String>();
            String tempVar = null;
            String[] myData = maxEntry.getKey().split("=, ");
            for (String s : myData) {
                tempVar = s.replace("=,", "").replace(" =", "").replace("{", "").replace("}", "");
                orderValues.add(tempVar.trim());
            }
            ArrayList<String> orderTempValuesResult = new ArrayList<String>();
            ArrayList<String> orderPredicatesTempValuesResult = new ArrayList<String>();
            ArrayList<String> orderValuesResultLast = new ArrayList<String>();
            ArrayList<String> orderValuesResult = new ArrayList<String>();
            for (String e : orderValues) {
                if (!e.equals("")) {
                    orderValuesResult.add(e);

                }
            }
            String append = "";
            append = append + gloName;
            for (String e : orderValuesResult) {
                if (!e.equals(gloName) && !e.equals(moreSemantic.get(gloVar))) {
                    orderTempValuesResult.add(e);
                }
            }
            append = append + " , ";
            for (String e : orderTempValuesResult) {
                append = append + e + " , ";
            }
            append = " , " + append + moreSemantic.get(gloVar);
            if ((!append.startsWith("\"") && !append.endsWith("\""))) {
                //System.out.println("Path is " + append);
            } else {
                System.out.println("Its contains literal");
            }
            String[] arrOfStr = append.split(",");
            for (String a : arrOfStr) {
                orderValuesResultLast.add(a.trim());
            }
            int paths = 0;
            System.out.println("Algorithm paths are " + orderValuesResultLast);//here are topk Paths tha user select to link
            int sizeall = 0;
            if (allsubjects.size() < allobjects.size()) {
                sizeall = allsubjects.size();
            } else {
                sizeall = allobjects.size();
            }
            for (String e : orderValuesResultLast) {
                for (int j = 0; j < sizeall; j++) {
                    if (e.equals(allobjects.get(j)) || e.equals(allsubjects.get(j))) {
                        System.out.println("Orfani " + allpredicates.get(j));
                        orderPredicatesTempValuesResult.add(allpredicates.get(j));
                    }
                    if (e.equals(allobjects.get(j)) && e.equals(allsubjects.get(j))) {
                        System.out.println("Andfani " + allpredicates.get(j));
                        orderPredicatesTempValuesResult.add(allpredicates.get(j));
                    }
                }
            }
            /*  for (String e : orderValuesResultLast  ) {
            paths=paths+1;
            for(int j=0;j<allsubjects.size();j++){
                if( e.equals(allobjects.get(j))||e.equals(allsubjects.get(j)) ){
                 //   if(e.equals(allsubjects.get(j))){
                     orderPredicatesTempValuesResult.add(allpredicates.get(j));
                }
            }         
             for(int j=0;j<allsubjects.size();j++){
                if( e.equals(allobjects.get(j))&&e.equals(allsubjects.get(j)) ){
                 //   if(e.equals(allsubjects.get(j))){
                     orderPredicatesTempValuesResult.add(allpredicates.get(j));
                   // }
                
                }          
            }
        }*/
            for (int i = 0; i < orderValuesResultLast.size(); i++) {
                kfa = kfa + 1;
            }
            orderValuesResultLast.remove(0);
            setMetrics(orderValuesResultLast, orderPredicatesTempValuesResult);
        }
    }

    public void FinalResult() {
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
        BufferedReader TSVFile = new BufferedReader(new FileReader("src\\WorkloadsDatasets\\wikidata\\mergedata.tsv"));
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
            sizeProperties = orderValuesResultLast.size() - 1;
        }
        for (int y = 0; y < subTempEdges.size(); y++) {
            subFinalEdges.add(subTempEdges.get(y));
        }
        finalNodes.addAll(orderValuesResultLast);
        finalEdges.addAll(subFinalEdges);
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
    }

    public void algorithmPaths2() {
        Collections.sort(tableValues);
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

    public void testRandom(int nodes) throws IOException {
        int min = 0;
        int max = 15;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        for (int k = 0; k < nodes; k++) {
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
            Algo_Calculation_Random("http://www.bigdata.com/rdf#serviceParam");
        }
    }

    public void Calculate_Random() {
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
    }

    public void test() {

    }

    @Override
    public int compare(QueriesFunctionWikidata o1, QueriesFunctionWikidata o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
