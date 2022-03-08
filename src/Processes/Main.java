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
        ArrayList<String> calculateNodesArrayRandom = new ArrayList<String>();

        QueriesFunctionDBpedia operationsdb = new QueriesFunctionDBpedia();
     //   operationsdb.Algo_Metrisis("",0);
        // operationsdb.Algo_A(10404);//for DBpedia 
        // operationsdb.Algo_B(3);//for DBpedia 

        //http://dbpedia.org/resource/Isola_del_Giglio
        //operationsdb.testa();
        // operationsdb.Algo_C("http://dbpedia.org/resource/Category:Marketing",2);
        //  operationsdb.Algo_C("http://dbpedia.org/resource/John_Coltrane",3);
        // operationsdb.Algo_C("http://dbpedia.org/resource/California",2);//5  dataset
        //   operationsdb.Algo_C("http://dbpedia.org/ontology/SoccerPlayer",21);
        // //  operationsdb.Algo_C("http://dbpedia.org/resource/Sex",1);//8  dataset
        // operationsdb.Algo_C("http://www.w3.org/2006/03/wn/wn20/instances/synset-software-noun-1",1);/98  dataset
        // operationsdb.Algo_C("http://dbpedia.org/resource/Category:German_musicians",2);//9  dataset
        //operationsdb.Algo_C("http://dbpedia.org/resource/Category:French_films",1);//10  dataset
        // operationsdb.Algo_C("http://ufc.br/Fortaleza",1);//10  dataset
         // operationsdb.Algo_C("http://dbpedia.org/ontology/SoccerPlayer",5);
        // operationsdb.Algo_C_D("http://dbpedia.org/resource/John_Coltrane",2,1,1);//1 dataset 3
        // operationsdb.Algo_C_D("http://dbpedia.org/ontology/SoccerPlayer",2,1,1);//1 dataset 4
        //     operationsdb.Algo_C_D("http://dbpedia.org/ontology/SoccerPlayer",21,1,1);//1 dataset 9
        //  operationsdb.Algo_C_D("http://dbpedia.org/resource/The_Sopranos",8,3,3);//1 correct_http15062013
        // operationsdb.Algo_C("http://dbpedia.org/resource/The_Sopranos",4); 
        //operationsdb.Algo_C("http://dbpedia.org/ontology/Band",15);  
         for(int i=1;i<15;i++){
         operationsdb.Algo_C_D("http://dbpedia.org/ontology/Band",15,i,i);//5
         }
         
         operationsdb.Calculate();
         operationsdb.Algo_Calculation("http://dbpedia.org/ontology/Band");
        // operationsdb.Calculate_Random();
        // operationsdb.Algo_Calculation_Random("http://dbpedia.org/resource/The_Sopranos");
        //operationsdb.Algo_C("http://dbpedia.org/resource/The_Sopranos",8); 
        /*   for(int i=2;i<8;i++){
         operationsdb.Algo_C_D("http://dbpedia.org/resource/The_Sopranos",8,i,i);//5
         }
         
         operationsdb.Calculate_Random();
         operationsdb.Algo_Calculation("http://dbpedia.org/resource/The_Sopranos");*/
       //   operationsdb.Algo_C("http://dbpedia.org/resource/The_Sopranos",8);
        /*for(int i=2;i<8;i++){
         operationsdb.Algo_C_D("http://dbpedia.org/resource/The_Sopranos",8,i,i);//1 correct_http15062013
         }
         
         operationsdb.Calculate_Random();
         operationsdb.Algo_Calculation("http://dbpedia.org/resource/The_Sopranos");*/
        /*  for(int i=2;i<5;i++){
         operationsdb.Algo_C_D("http://dbpedia.org/resource/California",5,i,i);//5  dataset
         }
         
         operationsdb.Calculate();
         operationsdb.Algo_Calculation("http://dbpedia.org/resource/California");*/
        /*  for(int i=1;i<2;i++){
         operationsdb.Algo_C_D("http://dbpedia.org/resource/Sex",2,i,i);//8  dataset
         }
         
         operationsdb.Calculate();
         operationsdb.Algo_Calculation("http://dbpedia.org/resource/Sex");*/
      //    operationsdb.Algo_C_D("http://dbpedia.org/resource/California",5,2,2);//5  dataset //future run
        //    operationsdb.Algo_C_D("http://dbpedia.org/resource/Sex",2,1,1);//8  dataset //future run
        //   
        // operationsdb.Algo_C_D("http://dbpedia.org/resource/Category:German_musicians",2,1,1);//8  dataset //future run
        //    operationsdb.Algo_C_D("http://www.w3.org/2006/03/wn/wn20/instances/synset-software-noun-1",1,0,0);//9  dataset καλια αστο
        //   operationsdb.Algo_C_D("http://dbpedia.org/resource/Category:French_films",1,0,0);//9  dataset καλια αστο
        // operationsdb.testRandmom();
        QueriesFunctionWikidata operationwiki = new QueriesFunctionWikidata();//start tr
           /*QueriesFunctionWikidata operationwiki = new QueriesFunctionWikidata();//start tr
         for (int i = 2; i < 5; i++) {
         operationwiki.Algo_D_nodes("http://www.bigdata.com/rdf#serviceParam", 10, i, i);//test-play3  dataset
         }
         operationwiki.Calculate();
         operationwiki.Algo_Calculation("http://www.bigdata.com/rdf#serviceParam", 2);*///end tr

          // operationwiki.testRandmom();// RANDOM CALCULATION MESAURE
        //   
         //operationwiki.Algo_D_nodes("http://www.wikidata.org/entity/Q33999",4,4,2);
        //START test-play2
      /*    for(int i=2;i<4;i++){
         operationwiki.Algo_D_nodes("http://www.wikidata.org/entity/Q33999",4,i,i);//5  dataset
         }
         
         operationwiki.Calculate();
         operationwiki.Algo_Calculation("http://www.wikidata.org/entity/Q33999",4);*///test1-play2
        //END TEST1-PLAY2
        //  operationwiki.Algo_C("http://wikiba.se/ontology#language",5);
        //  operationwiki.Calculate_Random();
        //  operationwiki.Algo_Calculation_Random("http://www.wikidata.org/entity/Q33999");
        //START test-play3
       /*    for(int i=2;i<5;i++){
         operationwiki.Algo_D_nodes("http://www.bigdata.com/rdf#serviceParam",5,i,i);//test-play3  dataset
         }
       
         
         operationwiki.Calculate();
         operationwiki.Algo_Calculation("http://www.bigdata.com/rdf#serviceParam",5);*/
         /////END test-play3 
        // operationwiki.Calculate_Random();
        // operationwiki.Algo_Calculation_Random("http://www.bigdata.com/rdf#serviceParam");
        ///    operationwiki.Algo_C("", 2);
        /*   for(int i=2;i<3;i++){
         operationwiki.Algo_D_nodes("http://www.bigdata.com/rdf#serviceParam",4,i,i);//test-play3  dataset
         }
         
         operationwiki.Calculate();
         operationwiki.Algo_Calculation("http://www.bigdata.com/rdf#serviceParam",4);
         /////END test-play4 */
        //operationwiki.Calculate_Random();
        // operationwiki.Algo_Calculation_Random("http://www.bigdata.com/rdf#serviceParam");
        
       /*  long startTime = System.nanoTime();
        System.out.println("DurationstartTime is " + startTime);
        
         for(int i=2;i<5;i++){
        operationwiki.Algo_D_nodes("http://www.wikidata.org/entity/Q571",5,i,i);//test-play3  dataset
         }
//http://www.wikidata.org/entity/Q16970
        operationwiki.Calculate();
        operationwiki.Algo_Calculation("http://www.wikidata.org/entity/Q571",5);//test-play5 
        long endTime = System.nanoTime();
        System.out.println("DurationendTime is " + endTime);
        long duration = (endTime - startTime);     */   


// operationwiki.Calculate_Random();
        // operationwiki.Algo_Calculation_Random("http://www.bigdata.com/rdf#serviceParam");
      /*   for(int i=2;i<9;i++){
         operationwiki.Algo_D_nodes("http://www.bigdata.com/rdf#serviceParam",9,i,i);//test-play3  dataset
         }
         
         operationwiki.Calculate();
         operationwiki.Algo_Calculation("http://www.bigdata.com/rdf#serviceParam",9);//test-play6 */
          // operationwiki.Algo_C("", 2);
        // operationwiki.Algo_C("", 2);
        /*    for(int i=2;i<9;i++){
         operationwiki.Algo_D_nodes("http://www.bigdata.com/rdf#serviceParam",9,i,i);//test-play3  dataset
         }
         
         operationwiki.Calculate();
         operationwiki.Algo_Calculation("http://www.bigdata.com/rdf#serviceParam",9);//test-play6 
         */
     //   operationwiki.Algo_C("", 2);
        //  operationwiki.Calculate_Random();
        //  operationwiki.Algo_Calculation_Random("http://www.bigdata.com/rdf#serviceParam");
        /* for(int i=2;i<6;i++){
         operationwiki.Algo_D_nodes("http://www.wikidata.org/entity/Q303479",6,i,i);
         }
         
         operationwiki.Calculate();
         operationwiki.Algo_Calculation("http://www.wikidata.org/entity/Q303479",6);
         
         *//*
         for(int i=2;i<6;i++){
         operationwiki.Algo_D_nodes("http://www.wikidata.org/entity/Q430711",6,i,i);
         }
         
         operationwiki.Calculate();
         operationwiki.Algo_Calculation("http://www.wikidata.org/entity/Q430711",6);
         */

        /*  calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q500548");
         calculateNodesArrayRandom.add("http://www.bigdata.com/rdf/gas#program");
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q24283660");
         calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
       
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q3305213");
         calculateNodesArrayRandom.add("http://wikiba.se/ontology#Property");
        
         calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q3455803");
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/P1449");
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q2714270");
       
         calculateNodesArrayRandom.add("https://fr.wikipedia.org/");
        
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q889");
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q93301");
         calculateNodesArrayRandom.add("http://www.bigdata.com/queryHints#Query");
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q5");
         calculateNodesArrayRandom.add("http://www.wikidata.org/entity/Q30524710");*/
        /* calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P1459");
         calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P373");
         calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P718");
         calculateEdgesArrayRandom.add("http://www.wikidata.org/prop/direct/P18");
         calculateEdgesArrayRandom.add("http://wikiba.se/ontology#language");*/
     //   System.out.println("Meta calculate calculateNodesArrayRandom "+calculateNodesArrayRandom);
        // System.out.println("Meta calculate calculateEdgesArrayRandom "+calculateEdgesArrayRandom);
        // for(int k=4;k<6;k++){
        /* for(int i=1;i<14;i++){
         operationwiki.Algo_D_nodes(calculateNodesArrayRandom.get(6),14,i,i);
         }
         
         operationwiki.Calculate();
         operationwiki.Algo_Calculation(calculateNodesArrayRandom.get(6),14);*///END 
        //  }
        // operationwiki.testRandmom();
    }

}
