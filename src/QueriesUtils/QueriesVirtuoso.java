/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package QueriesUtils;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openrdf.OpenRDFException;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import virtuoso.sesame2.driver.VirtuosoRepository;
/**
 *
 * @author fanis
 */
public class QueriesVirtuoso {
    
     RepositoryConnection conn = null;
    private Repository repository;
    URI uri = null;

    public QueriesVirtuoso(String host, String port, String username, String password) {
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
            Logger.getLogger(QueriesVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void terminateConnection() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (RepositoryException ex) {
                System.out.println("Exception caught while closing the connection of Virtuoso Client" + ex);
                Logger.getLogger(QueriesVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(QueriesVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(QueriesVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return uri;
    }

    //graphname: Name of graph in Virtuoso 
    //file: File name
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
            Logger.getLogger(QueriesVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    public void printTriples(String graphName) {
        String query = "select distinct ?subject ?predicate ?object from <" + graphName + "> where  {?subject ?predicate ?object} limit 20";
        openConnection();
        TupleQueryResult result = executeSparqlQuery(query);
        try {
            System.out.println("*** TRIPLES ***");
            while (result.hasNext()) {
                BindingSet r = result.next();
                String subject = r.getValue("subject").stringValue();
                String predicate = r.getValue("predicate").stringValue();
                String object = r.getValue("object").stringValue();

                System.out.println(subject + " - " + predicate + " - " + object);
            }
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(QueriesVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
        }
        terminateConnection();
    }
    
}
