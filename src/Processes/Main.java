package Processes;

import Processes.classesOperation.QueriesFunctionDBpedia;
import Processes.classesOperation.QueriesFunctionWikidata;
import QueriesUtils.QueriesVirtuoso;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Pattern;
import org.openrdf.query.MalformedQueryException;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, MalformedQueryException {
        /*
         All nodes for dbpedia that i use are these for k=5,k=10 and k=15 you can change k for whatever value from 1 to 15 as we say in paper
         http://dbpedia.org/class/yago/CapitalsInEurope=15
         http://dbpedia.org/ontology/Settlement=15
         http://dbpedia.org/ontology/Place=15
         http://dbpedia.org/resource/London=15
         http://dbpedia.org/resource/California=15
         http://dbpedia.org/ontology/Book=15
         http://dbpedia.org/resource/Category:French_films=15
         http://dbpedia.org/ontology/Organisation=15
         */
        // operationsdb.Algo_C("http://dbpedia.org/class/yago/CapitalsInEurope",15,"httpdbpediaorgclassyagoCapitalsInEurope");//use this function to check if you have top-k nodes
        QueriesFunctionDBpedia operationsdb1 = new QueriesFunctionDBpedia();
        int k = 5;
        /* for(int i=1;i<k;i++){
         operationsdb1.Algo_C_D("http://dbpedia.org/class/yago/CapitalsInEurope",k,i,i,"httpdbpediaorgclassyagoCapitalsInEurope");
         }
         operationsdb1.Calculate();
         operationsdb1.Algo_Calculation("http://dbpedia.org/class/yago/CapitalsInEurope");*/
        //////
        QueriesFunctionDBpedia operationsdb2 = new QueriesFunctionDBpedia();
        /*  for(int i=1;i<k;i++){
         operationsdb2.Algo_C_D("http://dbpedia.org/ontology/Settlement",k,i,i,"httpdbpediaorgontologySettlement");
         }
         operationsdb2.Calculate();
         operationsdb2.Algo_Calculation("http://dbpedia.org/ontology/Settlement");*/
        //////
       /* QueriesFunctionDBpedia operationsdb3 = new QueriesFunctionDBpedia();
         for(int i=1;i<k;i++){
         operationsdb3.Algo_C_D("http://dbpedia.org/ontology/Place",k,i,i,"httpdbpediaorgontologyPlace");
         }
         operationsdb3.Calculate();
         operationsdb3.Algo_Calculation("http://dbpedia.org/ontology/Place");
         //////
         QueriesFunctionDBpedia operationsdb4 = new QueriesFunctionDBpedia();
         for(int i=1;i<k;i++){
         operationsdb4.Algo_C_D("http://dbpedia.org/resource/London",k,i,i,"httpdbpediaorgresourceLondon");
         }
         operationsdb4.Calculate();
         operationsdb4.Algo_Calculation("http://dbpedia.org/resource/London");
         //////
         QueriesFunctionDBpedia operationsdb5 = new QueriesFunctionDBpedia();
         for(int i=1;i<k;i++){
         operationsdb5.Algo_C_D("http://dbpedia.org/resource/California",k,i,i,"httpdbpediaorgresourceCalifornia");
         }
         operationsdb5.Calculate();
         operationsdb5.Algo_Calculation("http://dbpedia.org/resource/California");
         //////
         QueriesFunctionDBpedia operationsdb6 = new QueriesFunctionDBpedia();
         for(int i=1;i<k;i++){
         operationsdb6.Algo_C_D("http://dbpedia.org/ontology/Book",k,i,i,"httpdbpediaorgontologyBook");
         }
         operationsdb6.Calculate();
         operationsdb6.Algo_Calculation("http://dbpedia.org/ontology/Book");
         //////
         QueriesFunctionDBpedia operationsdb7 = new QueriesFunctionDBpedia();
         for(int i=1;i<k;i++){
         operationsdb7.Algo_C_D("http://dbpedia.org/resource/Category:French_films",k,i,i,"httpdbpediaorgresourceCategoryFrench_films");
         }
         operationsdb7.Calculate();
         operationsdb7.Algo_Calculation("http://dbpedia.org/resource/Category:French_films");
         //////
         QueriesFunctionDBpedia operationsdb8 = new QueriesFunctionDBpedia();
         for(int i=1;i<k;i++){
         operationsdb8.Algo_C_D("http://dbpedia.org/ontology/Organisation",k,i,i,"httpdbpediaorgontologyOrganisation");
         }
         operationsdb8.Calculate();
         operationsdb8.Algo_Calculation("http://dbpedia.org/ontology/Organisation");*/

        //////AND for random dbpedia
        QueriesFunctionDBpedia randomdb = new QueriesFunctionDBpedia();
        randomdb.testRandom(10); //max 40 nodes

        /*All nodes for wikiDATA
         http://www.bigdata.com/rdf#serviceParam
         http://www.wikidata.org/entity/Q571
         http://www.wikidata.org/entity/Q64
         https://en.wikipedia.org/
         http://wikiba.se/ontology#Property
         http://www.bigdata.com/rdf/gas#program
         http://www.wikidata.org/entity/Q11424
         http://www.wikidata.org/entity/Q183
      
         */
        /*
         int k2=5;
         QueriesFunctionWikidata operationwiki1 = new QueriesFunctionWikidata();
         for (int i = 1; i < k2; i++) {
         operationwiki1.Algo_D_nodes("http://www.bigdata.com/rdf#serviceParam", k2, i, i);
         }
         operationwiki1.Calculate();
         operationwiki1.Algo_Calculation("http://www.bigdata.com/rdf#serviceParam",k2);
         //////
         QueriesFunctionWikidata operationwiki2= new QueriesFunctionWikidata();
         for (int i = 1; i < k2; i++) {
         operationwiki2.Algo_D_nodes("http://www.wikidata.org/entity/Q571", k2, i, i);
         }
         operationwiki2.Calculate();
         operationwiki2.Algo_Calculation("http://www.wikidata.org/entity/Q571",k2);
         //////
         QueriesFunctionWikidata operationwiki3= new QueriesFunctionWikidata();
         for (int i = 1; i < k2; i++) {
         operationwiki3.Algo_D_nodes("http://www.wikidata.org/entity/Q64", k2, i, i);
         }
         operationwiki3.Calculate();
         operationwiki3.Algo_Calculation("http://www.wikidata.org/entity/Q64",k2);
         //////
         QueriesFunctionWikidata operationwiki4= new QueriesFunctionWikidata();
         for (int i = 1; i < k2; i++) {
         operationwiki4.Algo_D_nodes("https://en.wikipedia.org/", k2, i, i);
         }
         operationwiki4.Calculate();
         operationwiki4.Algo_Calculation("https://en.wikipedia.org/",k2);
         //////
         QueriesFunctionWikidata operationwiki5= new QueriesFunctionWikidata();
         for (int i = 1; i < k2; i++) {
         operationwiki5.Algo_D_nodes("http://wikiba.se/ontology#Property", k2, i, i);
         }
         operationwiki5.Calculate();
         operationwiki5.Algo_Calculation("http://wikiba.se/ontology#Property",k2);
         //////
         QueriesFunctionWikidata operationwiki6= new QueriesFunctionWikidata();
         for (int i = 1; i < k2; i++) {
         operationwiki6.Algo_D_nodes("http://www.bigdata.com/rdf/gas#program", k2, i, i);
         }
         operationwiki6.Calculate();
         operationwiki6.Algo_Calculation("http://www.bigdata.com/rdf/gas#program",k2);
         //////
         QueriesFunctionWikidata operationwiki7= new QueriesFunctionWikidata();
         for (int i = 1; i < k2; i++) {
         operationwiki7.Algo_D_nodes("http://www.wikidata.org/entity/Q11424", k2, i, i);
         }
         operationwiki7.Calculate();
         operationwiki7.Algo_Calculation("http://www.wikidata.org/entity/Q11424",k2);
         //////
         QueriesFunctionWikidata operationwiki8= new QueriesFunctionWikidata();
         for (int i = 1; i < k2; i++) {
         operationwiki8.Algo_D_nodes("http://www.wikidata.org/entity/Q183", k2, i, i);
         }
         operationwiki8.Calculate();
         operationwiki8.Algo_Calculation("http://www.wikidata.org/entity/Q183",k2);*/
      //  QueriesFunctionWikidata randomwiki = new QueriesFunctionWikidata();
        // randomwiki.testRandom(5);
    }

}
