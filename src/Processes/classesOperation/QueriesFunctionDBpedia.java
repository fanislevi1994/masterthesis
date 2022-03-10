/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processes.classesOperation;

import static Processes.classesOperation.QueriesFunctionWikidata.cfa;
import static Processes.classesOperation.QueriesFunctionWikidata.encoderDikstra;
import static Processes.classesOperation.QueriesFunctionWikidata.tableValues;
import Processes.classesOperation.utils.SubjectObjectPredicate;
import Processes.classesOperation.utils.TableFrequencys;
import Processes.classesOperation.utils.TablePaths;
import Processes.classesOperation.utils.TableValues;
import Processes.classesOperation.utils.TableValuesDatas;
import Processes.classesOperation.utils.Triplet;
import Processes.classesOperation.utils.TripletValues;   // Import the FileWriter class
import info.aduna.iteration.Iterations;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import javax.security.auth.Subject;
import org.openrdf.OpenRDFException;
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
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
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
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;
import virtuoso.sesame2.driver.VirtuosoRepository;

/**
 * /**
 *
 * @author fanis
 */
public class QueriesFunctionDBpedia {

    RepositoryConnection conn = null;
    private Repository repository;
    URI uri = null;

    public void start(String host, String port, String username, String password) {
        this.repository = new VirtuosoRepository("jdbc:virtuoso://" + host + ":" + port + "/charset=UTF-8/log_enable=2", username, password);

    }

    public RepositoryConnection getConnetion() {
        return conn;
    }

    public void openConnection() {
        try {
            this.conn = this.repository.getConnection();
        } catch (RepositoryException ex) {
            System.out.println("Exception caught while removing the known modules from a profile" + ex);
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void terminateConnection() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (RepositoryException ex) {
                System.out.println("Exception caught while closing the connection of Virtuoso Client" + ex);
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteGraph() {
        try {
            //delete graph in virtuoso
            openConnection();
            this.conn.clear(this.uri);
            terminateConnection();
        } catch (RepositoryException ex) {
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //graphname: graph name in Virtuoso 
    //urlDocument: url of online rdfs/owl file
    public URI uploadGraphInVirtuosos(String graphName, String urlDocument) {
        URL url;
        try {
            url = new URL(urlDocument);
            this.uri = this.repository.getValueFactory().createURI(graphName);
            //this.conn = repository.getConnection();
            deleteGraph();
            openConnection();
            this.conn.add(url, graphName, RDFFormat.RDFXML, this.uri); //add graph in virtuoso
            terminateConnection();

        } catch (Exception ex) {
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return uri;
    }

    public URI uploadGraphInVirtuoso(String graphName, File file) {
        try {
            this.uri = repository.getValueFactory().createURI(graphName);
            deleteGraph();
            openConnection();
            conn.add(file, graphName, RDFFormat.RDFXML, uri); //add graph in virtuso
            terminateConnection();

        } catch (Exception ex) {
            System.out.println("createGraph - EXCEPTION: " + ex);
            return null;
        }
        return uri;
    }

    public TupleQueryResult executeSparqlQuery(String query) {
        TupleQueryResult result = null;
        try {
            TupleQuery tupleQuery = this.conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
            result = tupleQuery.evaluate();
        } catch (Exception ex) {
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    public void printTriples(String queriesdatabase) {
        String query = "select distinct ?subject ?predicate ?object  where  {?subject ?predicate ?object} limit 20";
        openConnection();
        TupleQueryResult result = executeSparqlQuery(queriesdatabase);
        try {
            while (result.hasNext()) {
                BindingSet r = result.next();
            }
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally block executed");
        }

        terminateConnection();
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
    private String e;

    public void dikstraSF(ArrayList<String> object, ArrayList<String> propertyListV_D_nodes, ArrayList<String> subject, int sources) {
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
            for (Map.Entry<Integer, String> entry : encoderDikstra.entrySet()) {
                if (objectList_D_node.get(i).equals(entry.getValue())) {
                    x = entry.getKey();
                    a = entry.getValue();
                }
                if (subjectList_D_node.get(i).equals(entry.getValue())) {
                    y = entry.getKey();
                    c = entry.getValue();
                }
                //System.out.println("addEdge(adj," + x + "," + y + ");");
                addEdge(adj, x, y);
            }
        }
        for (int g = 0; g < subjectList_D_node.size(); g++) {
            if ((subjectList_D_node.size() == propertyListV_D_node.size()) && (propertyListV_D_node.size() == objectList_D_node.size())) {
                tripletsValues.add(new TripletValues(subjectList_D_node.get(g), propertyListV_D_node.get(g), objectList_D_node.get(g)));
            }
        }
        int size = moreSemantic.size();
        for (Map.Entry<Integer, String> entry : encoderDikstra.entrySet()) {
            if (entry.getValue().equals(moreSemantic.get(gloFan))) {//4 beauty
                int dest = entry.getKey();
                printShortestDistance(adj, sources, dest, v, propertyListV_D_node);
                System.out.println("ok aa ------------------------------> sources " + sources + " destination " + dest);
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
    static int time1 = 0;
    static int time2 = 0;

    private static void printShortestDistance(
            ArrayList<ArrayList<Integer>> adj,
            int s, int dest, int v, ArrayList<String> propertiesValues) {
        time1 = time1 + 1;
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
        time2 = time2 + 1;
        for (Map.Entry<Integer, String> entry : encoderDikstra.entrySet()) {
            for (int i = path.size() - 1; i >= 0; i--) {
                if (entry.getKey().equals(path.get(i))) {
                    System.out.println("shortest is");
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
    static int allqueries = 0;
    String filesinputs = null;
    static int xaxa = 0;
    static int cctrue = 0;
    static int ccfalse = 0;
    static int cc = 0;
    static int loop = 0;
    static int code = 33;
    static int endNode = 34;
    String decoder;
    static int glob = 0;
    static int countAA = 0;
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
    String gloName = null;
    int rowssec = 0;
    int rowsthird = 0;
    int nodesa = 0;
    int count = 0;
    static int gloFan = 0;
    static int cfaa5 = 0;
    static int cfaa4 = 0;
    static int cfaa3 = 0;
    static int cfaa2 = 0;
    static int cfaa = 0;
    static int gloNode = 0;
    static int in11 = 0;
    static int in22 = 0;
    int kaou = 0;
    String endvar = null;
    static int resvir = 0;
    HashMap<String, String> nodesMap = new HashMap<String, String>();
    HashMap<String, Integer> nodeMapFrequency = new HashMap<String, Integer>();
    ArrayList<String> nodesList = new ArrayList<>();
    String flaglit = null;
    int rowsfor = 0;
    HashMap<String, String> propertyMap = new HashMap<String, String>();
    HashMap<String, Integer> propertyMapFrequency = new HashMap<String, Integer>();
    ArrayList<String> propertyList = new ArrayList<String>();

    HashMap<Integer, List<String>> coreCharacteristicSet = new HashMap<Integer, List<String>>();

    HashMap<String, Integer> classesMapDBpedia = new HashMap<String, Integer>();

    ArrayList<TripletValues> tripletsValues = new ArrayList<TripletValues>();

    ArrayList<String> tempAll = new ArrayList<String>();
    ArrayList<String> temp = new ArrayList<String>();

    ArrayList<String> topElementsKeyS_A = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_A = new ArrayList<Integer>();

    ArrayList<String> topElementsKeyS_B = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_B = new ArrayList<Integer>();

    ArrayList<String> topElementsKeyS_C = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_C = new ArrayList<Integer>();

    ArrayList<String> topElementsKeyS_D = new ArrayList<String>();
    ArrayList<Integer> topElementsValues_D = new ArrayList<Integer>();

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

    HashMap<String, String> objectMap_D_node = new HashMap<String, String>();
    HashMap<String, Integer> objectMapFrequency_D_node = new HashMap<String, Integer>();
    ArrayList<String> objectList_D_node = new ArrayList<String>();
    ArrayList<String> tempobject_D_node = new ArrayList<String>();
    ArrayList<String> tempAllobject_D_node = new ArrayList<String>();

    HashMap<String, String> converter = new HashMap<String, String>();

    ArrayList<String> subjectList_D_node_Global = new ArrayList<String>();

    HashMap<String, String> propertyMapV_D_node = new HashMap<String, String>();
    HashMap<String, Integer> propertyMapFrequencyV_D_node = new HashMap<String, Integer>();
    ArrayList<String> propertyListV_D_node = new ArrayList<String>();
    ArrayList<String> tempropertyV_D_node = new ArrayList<String>();
    ArrayList<String> tempAllpropertyV_D_node = new ArrayList<String>();
    ArrayList<String> propertyListV_D_node_Global = new ArrayList<String>();

    ArrayList<String> objectList_D_node_Global = new ArrayList<String>();

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

    ArrayList<String> globalList = new ArrayList<String>();

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

    ArrayList<String> allobjects = new ArrayList<String>();
    ArrayList<String> allsubjects = new ArrayList<String>();

    ArrayList<String> tempAll_C_Update = new ArrayList<String>();

    static int nodesmeasure = 0;
    static int edgemeasure = 0;
    static int queriesnumber = 0;
    static int nodesbad = 0;

    /*DBpedia folder*/
    public void listFilesForFolderDBpedia(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia(fileEntry);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\" + fileEntry.getName().toString() + "";
                Parser_folder(readBytesFile(filePath));
            }
        }
    }

    /*DBpedia folder*/
    public void listFilesForFolderDBpedia_B(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia_B(fileEntry);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\" + fileEntry.getName().toString() + "";
                Parser_folder_B(readBytesFile(filePath));
            }
        }
    }

    /*DBpedia folder*/
    public void listFilesForFolderDBpedia_C(final File folder, String nodename) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia_C(fileEntry, nodename);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\" + fileEntry.getName().toString() + "";
                Parser_folder_C(readBytesFile(filePath), nodename);
                System.out.println("file now is " + filePath);
                System.out.println("map isfa ---------------------------------*******************");
            }
        }
    }

    public void listFilesForFolderDBpedia_metrisis(final File folder, String nodename) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia_metrisis(fileEntry, nodename);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\" + fileEntry.getName().toString() + "";
                Parser_folder_metrisis(readBytesFile(filePath), nodename);
            }
        }
    }

    /*DBpedia folder*/
    public void listFilesForFolderDBpedia_C_Calculation(final File folder, String nodename) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia_C_Calculation(fileEntry, nodename);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\" + fileEntry.getName().toString() + "";
                Parser_folder_C_Calculation(readBytesFile(filePath), nodename);
            }
        }
    }

    public void listFilesForFolderDBpedia_C_Calculation_Random(final File folder, String nodename) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia_C_Calculation_Random(fileEntry, nodename);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpediaqueriesdatasets+extras\\t3\\" + fileEntry.getName().toString() + "";
                Parser_folder_C_Calculation_Random(readBytesFile(filePath), nodename);
            }
        }
    }

    /*DBpedia folder*/
    public void listFilesForFolderDBpedia_C_D(final File folder, String nodename) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia_C(fileEntry, nodename);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\" + fileEntry.getName().toString() + "";
                Parser_folder_C_D(readBytesFile(filePath), nodename);
            }
        }
    }

    /*DBpedia folder*/
    public void listFilesForFolderDBpedia_D(final File folder, String nodename) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia_C(fileEntry, nodename);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\" + fileEntry.getName().toString() + "";
                Parser_folder_C(readBytesFile(filePath), nodename);
                System.out.println("file now is " + filePath);
            }
        }
    }

    /*DBpedia folder*/
    public void listFilesForFolderDBpedia_D_nodes(final File folder, String nodename) throws IOException, MalformedQueryException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolderDBpedia_D_nodes(fileEntry, nodename);
            } else {
                String filePath = "src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\" + fileEntry.getName().toString() + "";
                convertArrayListToMap();
                Parser_folder_D_Node(readBytesFile(filePath), nodename);
            }
        }
    }

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

    /*all proccess get folder files and final print frequency*/
    public void Algo_A(int node) throws IOException {
        nodesa = node;
        final File folder = new File("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");
        listFilesForFolderDBpedia(folder);
        getNodeFrequency();
        printNodesFrequencyOrder();
    }

    /*all proccess get folder files and final print frequency*/
    public void Algo_B(int node) throws IOException {
        nodesb = node;
        final File folder = new File("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");
        listFilesForFolderDBpedia_B(folder);
        getNodeFrequency_B();
        printNodesFrequencyOrder_B();
    }

    /*all proccess get folder files and final print frequency*/
    public void Algo_C(String nodename, int node, String filenamevar) throws IOException {
        nodesc = node;
        filesinputs = filenamevar;
        System.out.println("algo c node " + nodesc);
        String host = "139.91.210.38/";
        String port = "1111";
        String username = "dba";
        String password = "dba";
        String graphName2 = "http://localhost:8890/dbpedia3.8";
        start(host, port, username, password);
        final File folder = new File("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");//fnanis t3
        listFilesForFolderDBpedia_C(folder, nodename);
        getNodeFrequency_C();
        printNodesFrequencyOrder_C();
        System.out.println("End algo");
    }

    public void Algo_Metrisis(String nodename, int node) throws IOException {
        final File folder = new File("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");//fnanis t3
        listFilesForFolderDBpedia_metrisis(folder, nodename);
    }

    public void Calculate_Random() {
        System.out.println("Prin calculate calculateNodesArray " + calculateNodesArray);
        System.out.println("Prin calculate calculateEdgesArray " + calculateEdgesArray);
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Tom_Cruise");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Argentina");
        calculateNodesArrayRandom.add("http://dbpedia.org/ontology/Organisation");
        calculateNodesArrayRandom.add("http://dbpedia.org/ontology/Software");
        calculateNodesArrayRandom.add("http://xmlns.com/foaf/0.1/Person");
        calculateNodesArrayRandom.add("http://www.w3.org/2004/02/skos/core#Concept");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Category:Polish_scientists");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Category:Polish_mathematicians");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Fortaleza");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Alaska");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Alexander_Marcus");
        calculateNodesArrayRandom.add("http://www.opengis.net/gml/_Feature");
        calculateNodesArrayRandom.add("http://dbpedia.org/ontology/Place");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Lake_Clifton_Eastern_High_School");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Santa_Fe_Indian_School");
        calculateNodesArrayRandom.add("http://dbpedia.org/ontology/Organisation");
        calculateNodesArrayRandom.add("http://dbpedia.org/ontology/ComicsCreator");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Sherman_Oaks_Center_for_Enriched_Studies");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Category:Car_manufacturers");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Zaporozhets");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Berlin");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Ellen_Franz");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Alexander_Marcus");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Friedrich_Werner");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Paolo_Soleri_Amphitheater");
        calculateNodesArrayRandom.add("http://dbpedia.org/ontology/ComicsCreator");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/James_A._Owen");
        calculateNodesArrayRandom.add("http://dbpedia.org/ontology/ComicsCreator");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Tokyo");
        calculateNodesArrayRandom.add("http://dati.camera.it/ocd/attocamera.rdf/ac04_2295");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Category:Luxury_vehicles");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Ellen_Franz");
        calculateNodesArrayRandom.add("http://www.w3.org/2004/02/skos/core#Concept");
        calculateNodesArrayRandom.add("http://en.wikipedia.org/wiki/Arthur_Schnitzler");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Nicole_Kidman");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Category:Austrian_writers");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Category:Australian_film_actors");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Karl_Kautsky");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/19th-century_philosophy");
        calculateNodesArrayRandom.add("http://dbpedia.org/resource/Western_philosophy");

        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/thumbnail");
        calculateEdgesArrayRandom.add("http://www.w3.org/2000/01/rdf-schema#label");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/developer");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/birthPlace");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/starring");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/seasonNumber");
        calculateEdgesArrayRandom.add("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        calculateEdgesArrayRandom.add("http://www.w3.org/2002/07/owl#sameAs");
        calculateEdgesArrayRandom.add("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        calculateEdgesArrayRandom.add("http://www.w3.org/2000/01/rdf-schema#comment");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/birthDate");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/profession");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/wordnet_type");
        calculateEdgesArrayRandom.add("http://www.w3.org/2000/01/rdf-schema#subClassOf");
        calculateEdgesArrayRandom.add("http://xmlns.com/foaf/0.1/primaryTopic");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/originalArtist");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/currentMember");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/starring");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/owningCompany");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/wordnet_type");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/openingTheme");
        calculateEdgesArrayRandom.add("http://www.w3.org/2004/02/skos/core#subject");
        calculateEdgesArrayRandom.add("http://xmlns.com/foaf/0.1/primaryTopic");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/basedOn");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/directedby");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/tenant");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/voice");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/callsignMeaning");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/basedOn");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/number");
        calculateEdgesArrayRandom.add("http://xmlns.com/foaf/0.1/name");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/deathDate");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/starring");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/release_date");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/narrated");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/voiceActors");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/appointer");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/narrated");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/liveactor");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/directedby");
        calculateEdgesArrayRandom.add("http://dbpedia.org/property/entrant");
        calculateEdgesArrayRandom.add("http://dbpedia.org/ontology/basedOn");
    }

    public void Algo_Random(String value) {
        moreSemantic.add(values);
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

    public void Algo_Calculation_Random(String nodename) throws IOException {
        filesinputs="";
        String host = "139.91.210.38/";
        String port = "1111";
        String username = "dba";
        String password = "dba";
        String graphName2 = "http://localhost:8890/dbpedia3.8";
        start(host, port, username, password);
        final File folder = new File("src\\WorkloadsDatasets\\dbpediaqueriesdatasets+extras\\t3\\");
        listFilesForFolderDBpedia_C_Calculation_Random(folder, nodename);
        FinalResultRandom();
    }

    public void Algo_Calculation(String nodename) throws IOException {
        String host = "139.91.210.38/";
        String port = "1111";
        String username = "dba";
        String password = "dba";
        String graphName2 = "http://localhost:8890/dbpedia3.8";
        start(host, port, username, password);
        final File folder = new File("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");//fnanis t3
        listFilesForFolderDBpedia_C_Calculation(folder, nodename);
        FinalResult();
        System.out.println("End algo all");
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
            ArrayList<String> orderValuesResult = new ArrayList<String>();
            ArrayList<String> orderValuesResultLast = new ArrayList<String>();
            for (String e : orderValues) {
                if (!e.equals("")) {
                    orderValuesResult.add(e);
                }
            }
            String append = "";
            ArrayList<String> orderTempValuesResult = new ArrayList<String>();
            ArrayList<String> orderPredicatesTempValuesResult = new ArrayList<String>();
            append = append + gloName;
            for (String e : orderValuesResult) {
                if (!e.equals(gloName) && !e.equals(moreSemantic.get(gloNode))) {
                    orderTempValuesResult.add(e);
                }
            }
            append = append + " , ";
            for (String e : orderTempValuesResult) {
                append = append + e + " , ";

            }
            append = " , " + append + moreSemantic.get(gloNode);
            String[] arrOfStr = append.split(",");
            for (String a : arrOfStr) {
                orderValuesResultLast.add(a.trim());
            }
            System.out.println("Algorithm paths are " + orderValuesResultLast);//Here are ouptut user paths for k that select
            int sizeall = 0;
            if (allsubjects.size() < allobjects.size()) {
                sizeall = allsubjects.size();
            } else {
                sizeall = allobjects.size();
            }
            for (String e : orderValuesResultLast) {
                for (int j = 0; j < sizeall; j++) {
                    if (e.equals(allobjects.get(j)) || e.equals(allsubjects.get(j))) {
                        orderPredicatesTempValuesResult.add(allpredicates.get(j));
                    }
                    if (e.equals(allobjects.get(j)) && e.equals(allsubjects.get(j))) {
                        orderPredicatesTempValuesResult.add(allpredicates.get(j));
                    }
                }
            }
            orderValuesResultLast.remove(0);
            setMetrics(orderValuesResultLast, orderPredicatesTempValuesResult);
        }
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
        for (int y = 0; y < sizeProperties; y++) {
            subFinalEdges.add(subTempEdges.get(y));
        }
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
    }

    public void Algo_C_D(String nodename, int moreSem, int nodeSem, int gloFans, String filevar) throws IOException {
        String host = "139.91.210.38/";
        String port = "1111";
        String username = "dba";
        String password = "dba";
        String graphName2 = "http://localhost:8890/dbpedia3.8";
        start(host, port, username, password);
        gloFan = gloFans;
        filesinputs = filevar;
        gloVar = moreSem;
        gloNode = nodeSem;
        gloName = nodename;
        Algo_C(nodename, gloVar, filesinputs);
        final File folder = new File("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");
        listFilesForFolderDBpedia_C_D(folder, nodename);
        algorithmPaths();
        System.out.println("End algo");
    }

    /*all proccess get folder files and final print frequency*/
    public void Algo_D(String nodename, int node) throws IOException {
        nodesd = node;
        final File folder = new File("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");
        listFilesForFolderDBpedia_D(folder, nodename);
    }

    /*all proccess get folder files and final print frequency*/
    public void Algo_D_nodes(String nodename, int numbernodes, int node, int moreSem, String filenamevar) throws IOException, MalformedQueryException {//fan
        filesinputs = filenamevar;
        gloVar = moreSem;
        gloName = nodename;
        nodesd = node;
        Algo_C(nodename, gloVar, filesinputs);
        final File folder = new File("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");
        listFilesForFolderDBpedia_D_nodes(folder, nodename);
    }

    public void test() {
        System.out.println("fanis");
    }

    public void testRandom() throws IOException {
        int min = 2;
        int max = 8;//limits for randomeness generator
        System.out.println("Random value in int from " + min + " to " + max + ":");
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        System.out.println(random_int);

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
            Algo_Calculation_Random("http://dbpedia.org/ontology/Organisation");
        }

    }

    /*print subject frequency DBpedia order*/
    public void printSubjectsFrequencyOrderDBpedia() {
        int count = 0;
        int sum = 0;
        final Map<String, Integer> sortedByCount = sortByValue(nodeMapFrequency);
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\dbpedia\\" + filesinputs + "\\");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                sum = sum + entry.getValue();
                count = count + 1;
                String allsentence = "Querie" + count + " class " + entry.getKey() + " frequency "
                        + entry.getValue() + "summ" + sum + "\n";
                myWriter.write(allsentence);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printTopK_A() {
        int sum = 0;
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\ocheck1aaa.txt");
            for (int i = 0; i < nodesa; i++) {
                // myWriter.write("aaa");
                String allsentence = "Top " + nodesa + " nodes are  " + topElementsKeyS_A.get(i) + " frequency "
                        + topElementsValues_A.get(i) + "\n";
                myWriter.write(allsentence);
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printTopK_B() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\topKNodesDBpedia.txt");
            for (int i = 0; i < nodesb; i++) {
                System.out.println("Top " + nodesb + " nodes are " + topElementsKeyS_B.get(i) + " with frequency " + topElementsValues_B.get(i));
                String allsentence = "Top " + nodesb + " nodes are  " + topElementsKeyS_B.get(i) + " frequency "
                        + topElementsValues_B.get(i) + "\n";
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
            myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\topKNodesDBpedia.txt");
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
                    System.out.println("Topf " + nodesc + " nodes are " + tempkey.get(i) + " with frequency " + tempvalue.get(i));
                    moreSemantic.add(tempkey.get(i));
                    String allsentence = "Topf " + nodesc + " nodes are  " + tempkey.get(i) + " frequency "
                            + tempvalue.get(i) + "\n";
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
            myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\topKNodesDBpedia.txt");
            for (int i = 0; i < nodesd; i++) {
                System.out.println("Top " + nodesd + " properties are " + topElementsKeyS_D.get(i) + " with frequency " + topElementsValues_D.get(i));
                String allsentence = "Top " + nodesd + " properties are  " + topElementsKeyS_D.get(i) + " frequency "
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

    /*print subject frequency order*/
    public void printNodesFrequencyOrder() {
        System.out.println("/////////////////");
        int sum = 0;
        final Map<String, Integer> sortedByCount = sortByValue(nodeMapFrequency);//it was 
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\outputDBpediasf.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                count = count + 1;
                sum = sum + entry.getValue();
                String allsentence = "Querie " + count + " node " + entry.getKey() + " frequency "
                        + entry.getValue() + " sum " + sum + "\n";

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

    /*print subject frequency order*/
    public void printNodesFrequencyOrder_B() {
        int sum = 0;
        final Map<String, Integer> sortedByCount = sortByValue(propertyMapFrequency);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\outputDBpedias.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                count = count + 1;
                sum = sum + entry.getValue();
                String allsentence = "Querie  for outputDBpedia2 " + count + " property " + entry.getKey() + " frequency "
                        + entry.getValue() + " sum " + sum + "\n";

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

    /*print subject frequency order*/
    public void printNodesFrequencyOrder_C() {
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        nodeMapFrequency_C.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        int sum = 0;
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\allnodesFrequencyDBpedia.txt");
            for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
                count = count + 1;
                sum = sum + entry.getValue();
                String allsentence = "Querie  for outputDBpedia2 " + count + " property " + entry.getKey() + " frequency "
                        + entry.getValue() + " sum " + sum + "\n";
                myWriter.write(allsentence);
                topElementsKeyS_C.add(entry.getKey());
                topElementsValues_C.add(entry.getValue());
            }
            myWriter.close();
            if (kaou == 0) {
                printTopK_C();
                kaou = 2;
            }
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*print subject frequency order*/
    public void printNodesFrequencyOrder_D() {
        int sum = 0;
        final Map<String, Integer> sortedByCount = sortByValue(propertyMapFrequency_D);
        int count = 0;
        try {
            FileWriter myWriter = new FileWriter("src\\WorkloadsDatasets\\outputs\\outputDBpedia15.txt");
            for (Map.Entry<String, Integer> entry : sortedByCount.entrySet()) {
                count = count + 1;
                System.out.println("list size fani is " + propertyList_D.size());
                sum = sum + entry.getValue();
                String allsentence = "Querie  for outputDBpedia2 " + count + " property " + entry.getKey() + " frequency "
                        + entry.getValue() + " sum " + sum + "\n";
                myWriter.write(allsentence);
                topElementsKeyS_D.add(entry.getKey());
                topElementsValues_D.add(entry.getValue());
            }
            myWriter.close();
            printTopK_D();
            System.out.println("Successfully wrote to the file.");
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
    }

    /*get subject frequency testing*/
    public void getNodeFrequency_B() {
        int freqproperty = 0;
        for (String node : propertyMap.keySet()) {
            for (int i = 0; i < propertyList.size(); i++) {
                if (node.equals(propertyList.get(i))) {
                    freqproperty = freqproperty + 1;
                }
            }
            propertyMapFrequency.put(node, freqproperty);
            freqproperty = 0;
        }
    }
    int counterr = 0;

    public void getNodeFrequency_C() {
        int freqnodec = 0;
        for (String node : nodesMap_C.keySet()) {
            for (int i = 0; i < nodesList_C.size(); i++) {
                if (node.trim().equals(nodesList_C.get(i).trim())) {
                    freqnodec = freqnodec + 1;
                }
            }
            nodeMapFrequency_C.put(node, freqnodec);
            freqnodec = 0;
        }
        for (Map.Entry<String, Integer> entry : nodeMapFrequency_C.entrySet()) {
            counterr = counterr + entry.getValue();
        }
    }

    /*get subject frequency testing*/
    public void getNodeFrequency_D() {
        int freqnodec = 0;
        for (String node : propertyMap_D.keySet()) {
            for (int i = 0; i < propertyList_D.size(); i++) {
                if (node.equals(propertyList_D.get(i))) {
                    freqnodec = freqnodec + 1;
                }
            }
            propertyMapFrequency_D.put(node, freqnodec);
            freqnodec = 0;
        }
    }

    public void getPredicateFrequencyDBpedia() {
        System.out.println("rpedicate frequency swf");
        int freqsubject = 0;
        for (String property : propertyMap.keySet()) {
            for (int i = 0; i < propertyList.size(); i++) {
                if (property.equals(propertyList.get(i))) {
                    freqsubject = freqsubject + 1;
                }
            }
            propertyMapFrequency.put(property, freqsubject);
            freqsubject = 0;
        }
    }

    /*store querie in structures for DBpeia*/
    public void Parser_folder(String querys) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------", 1116000);
        for (String a : arrOfStr) {
            c = c + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    in = in + 1;
                    if (pattern.getSubjectVar().getValue() != null) {
                        rows = rows + 1;
                        nodesMap.put(pattern.getSubjectVar().getValue().toString(), String.valueOf(rows));
                        nodesList.add(pattern.getSubjectVar().getValue().toString());
                    }

                    if (pattern.getObjectVar().getValue() != null) {
                        rows = rows + 1;
                        nodesMap.put(pattern.getObjectVar().getValue().toString(), String.valueOf(rows));
                        nodesList.add(pattern.getObjectVar().getValue().toString());
                    }
                }
            } catch (MalformedQueryException e) {
                e.printStackTrace();
                cc = cc + 1;

            }
            counter = counter + 1;
        }
    }

    /*store querie in structures for DBpeia*/
    public void Parser_folder_B(String querys) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------", 1116000);
        for (String a : arrOfStr) {
            c = c + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    System.out.println("vars are \n" + pattern.getPredicateVar().getValue());
                    in = in + 1;
                    if (pattern.getPredicateVar().getValue() != null) {
                        rowssec = rowssec + 1;
                        propertyMap.put(pattern.getPredicateVar().getValue().toString(), String.valueOf(rowssec));
                        propertyList.add(pattern.getPredicateVar().getValue().toString());
                    }
                }
            } catch (MalformedQueryException e) {
                e.printStackTrace();
                cc = cc + 1;
            }
            counter = counter + 1;
        }
    }

    public void GetResults() {
        System.out.println("GetResultsgather_temp_C_Calculation are " + gather_temp_C_Calculation);
        System.out.println("GetResultsgather_temp_C_Calculation_Predicate are " + gather_temp_C_Calculation_Predicate);
    }

    public void GetResultsRandom() {
        System.out.println("gather_temp_C_Calculation_Random are " + gather_temp_C_Calculation_Random);
        System.out.println("gather_temp_C_Calculation_Predicate_Random are " + gather_temp_C_Calculation_Predicate_Random);
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
        GetResults();
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
        GetResultsRandom();
    }

    public void testa() {
        String host = "139.91.190.144/";
        String port = "1111";
        String username = "dba";
        String password = "dba";
        start(host, port, username, password);
        System.out.println("VirtuosoResult ");
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String append2 = ""
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "PREFIX : <http://dbpedia.org/resource/>\n"
                + "PREFIX dbpedia2: <http://dbpedia.org/property/>\n"
                + "PREFIX dbpedia: <http://dbpedia.org/>\n"
                + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"
                + "\n"
                + "SELECT ?abstract\n"
                + "from <http://localhost:8890/dbpedia3.8/30gb> where {\n"
                + "{ <http://dbpedia.org/resource/Institut_Eurecom> rdf:type ?abstract .\n"
                + "}\n"
                + "}";
        openConnection();
        TupleQueryResult result = executeSparqlQuery(append2);
        try {
            while (result.hasNext()) {
                BindingSet r = result.next();
                System.out.println("in");
                //  String var2 = r.getValue(variable).stringValue();

            }
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Alless guttenndaaaa");
        }
    }

    public void VirtuosoResult_c(String querys, String variable) {//use this funcgtion to run queries for virtuoso
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            System.out.println("Query before " + query);
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        rowsthird = rowsthird + 1;
                        nodesMap_C.put(var2, String.valueOf(rowsthird));
                        nodesList_C.add(var2);
                        temp_C.add(var2);
                        allnodes.add(var2);
                        xaxa = xaxa + 1;
                        break;
                    } else {
                        rowsthird = rowsthird + 1;
                        nodesMap_C.put("null", String.valueOf(rowsthird));
                        nodesList_C.add("null");
                        temp_C.add("null");
                        break;
                    }
                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println("Alless guttenndaaaa");
            }
        }
    }

    public void VirtuosoResult_c_Calculation(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        temp_C_Calculation.add(var2);
                        break;
                    } else {
                        temp_C_Calculation.add("null");
                        break;
                    }
                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println("Alless guttenndaaaa");
            }
        }
    }

    public void VirtuosoResult_c_Calculation_Random(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        temp_C_Calculation_Random.add(var2);
                        break;
                    } else {
                        temp_C_Calculation_Random.add("null");
                        break;
                    }
                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println("Alless guttenndaaaa");
            }
        }
    }

    public void VirtuosoResult_c_Calculation_Predicate_Random(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        temp_C_Calculation_Predicate_Random.add(var2);
                        break;
                    } else {
                        temp_C_Calculation_Predicate_Random.add("null");
                        break;
                    }

                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                System.out.println("Alless guttenndaaaa");
            }
        }
    }

    public void VirtuosoResult_c_Calculation_Predicate(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        temp_C_Calculation_Predicate.add(var2);
                        break;
                    } else {
                        temp_C_Calculation_Predicate.add("null");
                        break;
                    }
                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                System.out.println("Alless guttenndaaaa");
            }
        }
        System.out.println("Alless guttenn");
    }

    public void VirtuosoResult_c_pre(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        allpredicates.add(var2);
                        break;
                    } else {
                        allpredicates.add("null");
                        break;
                    }
                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println("Alless guttenndaaaa");
            }
        }
        System.out.println("Alless guttenn");
    }

    public void Parser_folder_C(String querys, String nodename) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        for (String a : arrOfStr) {
            c = c + 1;
            allqueries = allqueries + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    in = in + 1;
                    if (pattern.getSubjectVar().getValue() != null) {
                        char[] ch = pattern.getSubjectVar().getValue().toString().toCharArray();
                        for (int i = 0; i < ch.length; i++) {
                            if (ch[i] == '"' || ch[i] == '^') {
                                flaglit = "yes";
                            } else {
                                flaglit = "no";
                            }
                        }
                        if (flaglit != "yes") {
                            rowsthird = rowsthird + 1;
                            nodesMap_C.put(pattern.getSubjectVar().getValue().toString(), String.valueOf(rowsthird));
                            nodesList_C.add(pattern.getSubjectVar().getValue().toString());
                            temp_C.add(pattern.getSubjectVar().getValue().toString());
                            allnodes.add(pattern.getSubjectVar().getValue().toString());
                            allsubjects.add(pattern.getSubjectVar().getValue().toString());
                        } else {
                            rowsthird = rowsthird + 1;
                            nodesMap_C.put("null", String.valueOf(rowsthird));
                            nodesList_C.add("null");
                            temp_C.add("null");
                        }
                    } else if (pattern.getSubjectVar().getValue() == null) {
                        nodesMap_C.put(pattern.getSubjectVar().getName().toString(), String.valueOf(rowsthird));
                        nodesList_C.add(pattern.getSubjectVar().getName().toString());
                        temp_C.add(pattern.getSubjectVar().getName().toString());
                        allnodes.add(pattern.getSubjectVar().getName().toString());
                        allsubjects.add(pattern.getSubjectVar().getName().toString());
                    }
                    if (pattern.getObjectVar().getValue() != null) {
                        char[] ch = pattern.getObjectVar().getValue().toString().toCharArray();
                        for (int i = 0; i < ch.length; i++) {
                            if (ch[i] == '"' || ch[i] == '^') {
                                flaglit = "yes";
                            } else {
                                flaglit = "no";
                            }
                        }
                        if (flaglit != "yes") {
                            rowsthird = rowsthird + 1;
                            nodesMap_C.put(pattern.getObjectVar().getValue().toString(), String.valueOf(rowsthird));
                            nodesList_C.add(pattern.getObjectVar().getValue().toString());
                            temp_C.add(pattern.getObjectVar().getValue().toString());
                            allnodes.add(pattern.getObjectVar().getValue().toString());
                            allobjects.add(pattern.getObjectVar().getValue().toString());
                        } else {
                            rowsthird = rowsthird + 1;
                            nodesMap_C.put("null", String.valueOf(rowsthird));
                            nodesList_C.add("null");
                            temp_C.add("null");
                        }
                    } else if (pattern.getObjectVar().getValue() == null) {
                        rowsthird = rowsthird + 1;
                        nodesMap_C.put(pattern.getObjectVar().getName().toString(), String.valueOf(rowsthird));
                        nodesList_C.add(pattern.getObjectVar().getName().toString());
                        temp_C.add(pattern.getObjectVar().getName().toString());
                        allnodes.add(pattern.getObjectVar().getName().toString());
                        allobjects.add(pattern.getObjectVar().getName().toString());
                    }
                    if (pattern.getPredicateVar().getValue() != null) {
                        allpredicates.add(pattern.getPredicateVar().getValue().toString());
                    } else if (pattern.getPredicateVar().getValue() == null) {
                        allpredicates.add(pattern.getPredicateVar().getName().toString());
                        allpredicates.add("null");
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
                    System.out.println("Its has " + temp_C);
                }
                flag = 0;
                temp_C = new ArrayList<String>();
            } catch (MalformedQueryException e) {
                e.printStackTrace();
                cc = cc + 1;

            }
            counter = counter + 1;
        }
        for (String e : tempAll_C) {
            System.out.println("temp fani is is " + e);
        }
    }

    public void Parser_folder_metrisis(String querys, String nodename) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        for (String a : arrOfStr) {
            c = c + 1;
            allqueries = allqueries + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                queriesnumber = queriesnumber + 1;
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    in = in + 1;
                    if (pattern.getSubjectVar().getValue() != null) {
                        nodesmeasure = nodesmeasure + 1;
                    } else if (pattern.getSubjectVar().getValue() == null) {
                        nodesmeasure = nodesmeasure + 1;
                    }
                    if (pattern.getObjectVar().getValue() != null) {
                        nodesmeasure = nodesmeasure + 1;
                    } else if (pattern.getObjectVar().getValue() == null) {
                        nodesmeasure = nodesmeasure + 1;
                    }
                    if (pattern.getPredicateVar().getValue() != null) {
                        edgemeasure = edgemeasure + 1;
                    } else if (pattern.getPredicateVar().getValue() == null) {
                        edgemeasure = edgemeasure + 1;
                    }
                }
            } catch (MalformedQueryException e) {
                e.printStackTrace();
                cc = cc + 1;
                nodesbad = nodesbad + 1;
            }
            counter = counter + 1;
        }
    }

    public void Parser_folder_C_Calculation(String querys, String nodename) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        for (String a : arrOfStr) {
            c = c + 1;
            allqueries = allqueries + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    in = in + 1;
                    if (pattern.getSubjectVar().getValue() != null) {
                        temp_C_Calculation.add(pattern.getSubjectVar().getValue().toString());
                    } else if (pattern.getSubjectVar().getValue() == null) {
                        if (!query.contains("DESCRIBE")) {
                            temp_C_Calculation.add(pattern.getSubjectVar().getName().toString());
                            // VirtuosoResult_c_Calculation(query, pattern.getSubjectVar().getName());
                        }
                    }
                    if (pattern.getPredicateVar().getValue() != null) {
                        temp_C_Calculation_Predicate.add(pattern.getPredicateVar().getValue().toString());
                    } else if (pattern.getPredicateVar().getValue() == null) {
                        if (!query.contains("DESCRIBE")) {
                            // VirtuosoResult_c_Calculation_Predicate(query, pattern.getPredicateVar().getName());
                            temp_C_Calculation_Predicate.add(pattern.getPredicateVar().getName().toString());
                        }
                    }
                    if (pattern.getObjectVar().getValue() != null) {
                        temp_C_Calculation.add(pattern.getObjectVar().getValue().toString());
                    } else if (pattern.getObjectVar().getValue() == null) {
                        if (!query.contains("DESCRIBE")) {
                            // VirtuosoResult_c_Calculation(query, pattern.getObjectVar().getName());
                            temp_C_Calculation.add(pattern.getObjectVar().getName().toString());
                        }

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
                cc = cc + 1;

            }
            counter = counter + 1;
        }
    }

    public void Parser_folder_C_Calculation_Random(String querys, String nodename) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        for (String a : arrOfStr) {
            c = c + 1;
            allqueries = allqueries + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    in = in + 1;
                    if (pattern.getSubjectVar().getValue() != null) {
                        temp_C_Calculation_Random.add(pattern.getSubjectVar().getValue().toString());
                    } else if (pattern.getSubjectVar().getValue() == null) {
                        temp_C_Calculation_Random.add(pattern.getSubjectVar().getName().toString());
                        if (!query.contains("DESCRIBE")) {
                            //  VirtuosoResult_c_Calculation_Random(query, pattern.getSubjectVar().getName());
                        }
                    }
                    if (pattern.getPredicateVar().getValue() != null) {
                        temp_C_Calculation_Predicate_Random.add(pattern.getPredicateVar().getValue().toString());
                    } else if (pattern.getPredicateVar().getValue() == null) {
                        temp_C_Calculation_Predicate_Random.add(pattern.getPredicateVar().getName().toString());
                        if (!query.contains("DESCRIBE")) {
                            //  VirtuosoResult_c_Calculation_Predicate_Random(query, pattern.getPredicateVar().getName());

                        }
                    }
                    if (pattern.getObjectVar().getValue() != null) {
                        temp_C_Calculation_Random.add(pattern.getObjectVar().getValue().toString());
                    } else if (pattern.getObjectVar().getValue() == null) {
                        temp_C_Calculation_Random.add(pattern.getObjectVar().getName().toString());
                        if (!query.contains("DESCRIBE")) {
                            // VirtuosoResult_c_Calculation_Random(query, pattern.getObjectVar().getName());
                        }
                    }
                }
                CompareCalculationRandomsss();
                temp_C_Calculation_Random = new ArrayList<String>();
                temp_C_Calculation_Predicate_Random = new ArrayList<String>();
            } catch (MalformedQueryException e) {
                e.printStackTrace();
                cc = cc + 1;
            }
            counter = counter + 1;
        }
    }

    /*store querie in structures for DBpeia*/
    public void Parser_folder_D(String querys, String nodename) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------", 1116000);
        for (String a : arrOfStr) {
            c = c + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    in = in + 1;
                }
                int flag = 0;
                for (String e : temp_D) {
                    if (e.equals(/*"http://www.wikidata.org/entity/Q1284"*/nodename)) {
                        flag = 1;
                    }
                }
                if (flag == 1) {
                    tempAll_D.addAll(temp_D);
                    System.out.println("Its has");
                }
                flag = 0;
                temp_D = new ArrayList<String>();
            } catch (MalformedQueryException e) {
                e.printStackTrace();
                cc = cc + 1;
            }
            counter = counter + 1;
        }
        for (String e : tempAll_D) {
            System.out.println("temp fani is is " + e);
        }
    }

    /*store querie in structures for DBpeia*/
    public void Parser_folder_D_Node(String querys, String nodename) throws MalformedQueryException {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        String[] arrOfStr = querys.split("-----------------", 1116000);
        for (String a : arrOfStr) {
            c = c + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            countAA = countAA + 1;
            ParsedQuery parsedQuery = parser.parseQuery(query, null);
            StatementPatternCollector collector = new StatementPatternCollector();
            TupleExpr tupleExpr = parsedQuery.getTupleExpr();
            tupleExpr.visit(collector);
            for (StatementPattern pattern : collector.getStatementPatterns()) {
                in = in + 1;
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
        }

    }

    public void VirtuosoResult2(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        openConnection();
        TupleQueryResult result = executeSparqlQuery(querys);
        try {
            while (result.hasNext()) {
                BindingSet r = result.next();
                Value var = r.getValue(variable);//.stringValue();
                if (var != null) {
                    String var2 = r.getValue(variable).stringValue();
                    subjectMap_D_node.put(var2, String.valueOf(rowsd_nodeobject));
                    subjectList_D_node.add(var2);
                    encoderDikstra.put(rowsd_node, var2);
                    encoderDikstraString.add(var2);
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodesubject = rowsd_nodesubject + 1;
                    rowsd_node = rowsd_node + 1;
                    subjectList_D_node_Global.add(var2);
                    break;
                } else {
                    subjectList_D_node.add("null");
                    encoderDikstra.put(rowsd_node, "null");
                    encoderDikstraString.add("null");
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodesubject = rowsd_nodesubject + 1;
                    rowsd_node = rowsd_node + 1;
                    subjectList_D_node_Global.add("null");
                    break;
                }
            }
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Alless guttenndaaaa");
        }
    }

    public void VirtuosoResult(String querys, String variable) {//use this function for query virtuoso
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        openConnection();
        TupleQueryResult result = executeSparqlQuery(querys);
        try {
            while (result.hasNext()) {
                BindingSet r = result.next();
                Value var = r.getValue(variable);
                if (var != null) {
                    String var2 = r.getValue(variable).stringValue();
                    objectMap_D_node.put(var2, String.valueOf(rowsd_nodeobject));
                    objectList_D_node.add(var2);
                    encoderDikstra.put(rowsd_node, var2);
                    encoderDikstraString.add(var2);
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodeobject = rowsd_nodeobject + 1;
                    rowsd_node = rowsd_node + 1;
                    objectList_D_node_Global.add(var2);
                    break;
                } else {
                    objectMap_D_node.put("null", String.valueOf(rowsd_nodeobject));
                    objectList_D_node.add("null");
                    encoderDikstra.put(rowsd_node, "null");
                    encoderDikstraString.add("null");
                    encoderDikstraInteger.add(rowsd_node);
                    rowsd_nodeobject = rowsd_nodeobject + 1;
                    rowsd_node = rowsd_node + 1;
                    objectList_D_node_Global.add("null");
                    break;
                }
            }
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Alless guttenndaaaa");
        }
        System.out.println("Alless guttenn");
    }

    public void VirtuosoResult_d_3(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];

        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        propertyListV_D_node.add(var2);
                        break;
                    } else {
                        propertyListV_D_node.add("null");
                        break;
                    }
                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println("Alless guttenndaaaa");
            }
        }
    }

    public void VirtuosoResult_d_2(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];

        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        subjectMap_D_node.put(var2, String.valueOf(rowsd_nodesubject));
                        subjectList_D_node.add(var2);
                        encoderDikstra.put(rowsd_node, var2);
                        encoderDikstraString.add(var2);
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodesubject = rowsd_nodesubject + 1;
                        rowsd_node = rowsd_node + 1;
                        subjectList_D_node_Global.add(var2);
                        break;
                    } else {
                        subjectMap_D_node.put("null", String.valueOf(rowsd_nodesubject));
                        subjectList_D_node.add("null");
                        encoderDikstra.put(rowsd_node, "null");
                        encoderDikstraString.add("null");
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodesubject = rowsd_nodesubject + 1;
                        rowsd_node = rowsd_node + 1;
                        subjectList_D_node_Global.add("null");
                        break;
                    }

                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println("Alless guttenndaaaa");
            }

        }
    }

    public void VirtuosoResult_d(String querys, String variable) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        int flag = 1;
        int xx = 0;
        int end = 0;
        end = arrOfStr.length;
        c = c + 1;
        cc = cc + 1;
        SPARQLParserFactory factory = new SPARQLParserFactory();
        QueryParser parser = factory.getParser();
        String query = arrOfStr[loop];
        if (!query.contains("ASK") && query.contains("http://dbpedia3.8")) {
            String[] splita1 = query.split("http://dbpedia3.8", 2);
            String append1 = splita1[0] + "http://localhost:8890/dbpedia3.8";
            String append2 = append1 + splita1[1];
            openConnection();
            TupleQueryResult result = executeSparqlQuery(append2);
            try {
                while (result.hasNext()) {
                    BindingSet r = result.next();
                    Value var = r.getValue(variable);/*.stringValue();*/
                    if (var != null) {
                        String var2 = r.getValue(variable).stringValue();
                        objectMap_D_node.put(var2, String.valueOf(rowsd_nodeobject));
                        objectList_D_node.add(var2);
                        encoderDikstra.put(rowsd_node, var2);
                        encoderDikstraString.add(var2);
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodeobject = rowsd_nodeobject + 1;
                        rowsd_node = rowsd_node + 1;
                        objectList_D_node_Global.add(var2);
                        break;
                    } else {
                        objectMap_D_node.put("null", String.valueOf(rowsd_nodeobject));
                        objectList_D_node.add("null");
                        encoderDikstra.put(rowsd_node, "null");
                        encoderDikstraString.add("null");
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodeobject = rowsd_nodeobject + 1;
                        rowsd_node = rowsd_node + 1;
                        objectList_D_node_Global.add("null");
                        break;
                    }
                }
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                System.out.println("Alless guttenndaaaa");
            }
        }
    }

    /*store querie in structures for DBpeia*/
    public void Parser_folder_C_D(String querys, String nodename) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------");
        for (String a : arrOfStr) {
            c = c + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                countAA = countAA + 1;
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    in = in + 1;
                    cfaa5 = cfaa5 + 1;
                    if (pattern.getSubjectVar().getValue() != null) {
                        subjectMap_D_node.put(pattern.getSubjectVar().getValue().toString(), String.valueOf(rowsd_nodesubject));
                        subjectList_D_node.add(pattern.getSubjectVar().getValue().toString());
                        encoderDikstra.put(rowsd_node, pattern.getSubjectVar().getValue().toString());
                        encoderDikstraString.add(pattern.getSubjectVar().getValue().toString());
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodesubject = rowsd_nodesubject + 1;
                        rowsd_node = rowsd_node + 1;
                        subjectList_D_node_Global.add(pattern.getSubjectVar().getValue().toString());
                    } else if (pattern.getSubjectVar().getValue() == null) {
                        subjectMap_D_node.put(pattern.getSubjectVar().getName().toString(), String.valueOf(rowsd_nodesubject));
                        subjectList_D_node.add(pattern.getSubjectVar().getName().toString());
                        encoderDikstra.put(rowsd_node, pattern.getSubjectVar().getName().toString());
                        encoderDikstraString.add(pattern.getSubjectVar().getName().toString());
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodesubject = rowsd_nodesubject + 1;
                        rowsd_node = rowsd_node + 1;
                        subjectList_D_node_Global.add(pattern.getSubjectVar().getName().toString());
                    }
                    if (pattern.getObjectVar().getValue() != null) {
                        objectMap_D_node.put(pattern.getObjectVar().getValue().toString(), String.valueOf(rowsd_nodeobject));
                        objectList_D_node.add(pattern.getObjectVar().getValue().toString());
                        encoderDikstra.put(rowsd_node, pattern.getObjectVar().getValue().toString());
                        encoderDikstraString.add(pattern.getObjectVar().getValue().toString());
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodeobject = rowsd_nodeobject + 1;
                        rowsd_node = rowsd_node + 1;
                        objectList_D_node_Global.add(pattern.getObjectVar().getValue().toString());
                    } else if (pattern.getObjectVar().getValue() == null) {
                        objectMap_D_node.put(pattern.getObjectVar().getName().toString(), String.valueOf(rowsd_nodeobject));
                        objectList_D_node.add(pattern.getObjectVar().getName().toString());
                        encoderDikstra.put(rowsd_node, pattern.getObjectVar().getName().toString());
                        encoderDikstraString.add(pattern.getObjectVar().getName().toString());
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodeobject = rowsd_nodeobject + 1;
                        rowsd_node = rowsd_node + 1;
                        objectList_D_node_Global.add(pattern.getObjectVar().getName().toString());
                    }
                    if (pattern.getPredicateVar().getValue() != null) {
                        propertyListV_D_node.add(pattern.getPredicateVar().getValue().toString());
                    } else if (pattern.getPredicateVar().getValue() == null) {
                        propertyListV_D_node.add(pattern.getPredicateVar().getName().toString());
                    }
                }
                for (Map.Entry mapElement : encoderDikstra.entrySet()) {
                    if (mapElement.getValue().equals(nodename)) {
                        code = Integer.valueOf(mapElement.getKey().toString());
                    }
                    if (moreSemantic.get(gloNode).equals(mapElement.getValue())) {
                        endNode = 25;
                    }
                }
                if (endNode == 25) {
                    cfaa4 = cfaa4 + 1;
                } else {
                    endNode = 34;
                }
                if (code != 33) {
                    cfaa2 = cfaa2 + 1;
                    in22 = in22 + 1;
                    if (endNode == 25) {
                        cfaa3 = cfaa3 + 1;
                    } else {
                        endNode = 34;
                    }
                } else {
                    code = 33;
                    cfaa = cfaa + 1;
                    in11 = in11 + 1;
                }
                if (subjectList_D_node.size() > objectList_D_node.size()) {
                    for (int i = 0; i < subjectList_D_node.size(); i++) {
                        objectMap_D_node.put("null", String.valueOf(rowsd_nodeobject));
                        objectList_D_node.add("null");
                        encoderDikstra.put(rowsd_node, "null");
                        encoderDikstraString.add("null");
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodeobject = rowsd_nodeobject + 1;
                        rowsd_node = rowsd_node + 1;
                        objectList_D_node_Global.add("null");
                    }
                } else if (subjectList_D_node.size() < objectList_D_node.size()) {
                    for (int i = 0; i < objectList_D_node.size(); i++) {
                        subjectMap_D_node.put("null", String.valueOf(rowsd_nodesubject));
                        subjectList_D_node.add("null");
                        encoderDikstra.put(rowsd_node, "null");
                        encoderDikstraString.add("null");
                        encoderDikstraInteger.add(rowsd_node);
                        rowsd_nodesubject = rowsd_nodesubject + 1;
                        rowsd_node = rowsd_node + 1;
                        subjectList_D_node_Global.add("null");
                    }
                }
                this.dikstraSF(subjectList_D_node, propertyListV_D_node, objectList_D_node, code);
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
                propertyListV_D_node.clear();
                encoderDikstraString.clear();
                encoderDikstraInteger.clear();
                pairsvValues.clear();
                rowsd_node = 0;
            } catch (MalformedQueryException e) {
                e.printStackTrace();
                cc = cc + 1;
            }
            counter = counter + 1;
        }

    }

    public void testCall(int loopes, String querys) {
        ArrayList<TableValues> test = new ArrayList<TableValues>();
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------", 1116000);
        for (loop = loopes; loop < arrOfStr.length; loop++) {
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = arrOfStr[loop];
        }

    }

    public void printTriples2(String graphName) {
        String query = "select distinct ?subject ?predicate ?object from <" + graphName + "> where  {?subject ?predicate ?object} limit 20";
        openConnection();
        TupleQueryResult result = executeSparqlQuery(graphName);
        try {

            while (result.hasNext()) {
                BindingSet r = result.next();
            }
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(QueriesFunctionDBpedia.class.getName()).log(Level.SEVERE, null, ex);
        }
        terminateConnection();
    }

    public void convertArrayListToMap() {
        String value = "-";
        for (int i = 0; i < tempAll_C_node.size(); i++) {
            converter.put(tempAll_C_node.get(i), value);
        }
    }

    public void comperator() {
        for (String name : converter.keySet()) {
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

    /*store querie in structures for DBpeia*/
    public void getNodes2(String querys, String nodename) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------", 1116000);
        for (String a : arrOfStr) {
            c = c + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
            try {
                ParsedQuery parsedQuery = parser.parseQuery(query, null);
                StatementPatternCollector collector = new StatementPatternCollector();
                TupleExpr tupleExpr = parsedQuery.getTupleExpr();
                tupleExpr.visit(collector);
                for (StatementPattern pattern : collector.getStatementPatterns()) {
                    if (pattern.getPredicateVar().getValue() != null) {
                        propertyList_D_node.add(pattern.getPredicateVar().getValue().toString());
                    } else if (pattern.getPredicateVar().getValue() == null) {
                        propertyList_D_node.add("out");
                    }
                    if (pattern.getSubjectVar().getValue() != null) {
                        subjectList_D_node.add(pattern.getSubjectVar().getValue().toString());
                        rowsd_nodesubject = rowsd_nodesubject + 1;
                    } else if (pattern.getSubjectVar().getValue() == null) {
                        subjectList_D_node.add("out");
                    }
                    if (pattern.getObjectVar().getValue() != null) {
                        objectList_D_node.add(pattern.getObjectVar().getValue().toString());
                        rowsd_nodeobject = rowsd_nodeobject + 1;
                    } else if (pattern.getObjectVar().getValue() == null) {
                        objectList_D_node.add("out");
                    }
                }
            } catch (MalformedQueryException e) {
                e.printStackTrace();
                cc = cc + 1;
            }
            counter = counter + 1;
        }
        for (String e : tempAll_D) {
            System.out.println("temp fani is is " + e);
        }
    }

    /*store querie in structures for DBpeia*/
    public void getNodes(String querys, String nodename) {
        int c = 0, cc = 0, in = 0, in2, counter = 0;
        String[] arrOfStr = querys.split("-----------------", 1116000);
        for (String a : arrOfStr) {
            c = c + 1;
            SPARQLParserFactory factory = new SPARQLParserFactory();
            QueryParser parser = factory.getParser();
            String query = a;
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
                cc = cc + 1;

            }
            counter = counter + 1;
        }
        for (String e : tempAll_D) {
            System.out.println("temp fani is is " + e);
        }
    }

    public void printNodesMap() {
        for (String all : tempAll) {
            System.out.println("************** set is " + all);

        }
    }

}
