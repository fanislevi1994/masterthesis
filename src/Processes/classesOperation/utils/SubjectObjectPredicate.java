/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Processes.classesOperation.utils;

/**
 *
 * @author fanis
 */
public class SubjectObjectPredicate {
    
    public int index;
    public String subject;
    public String predicate;
    public String object;
    public SubjectObjectPredicate(int index,String subject,String predicate,String object) {
        super();      
        this.index=index;
        this.subject=subject;
        this.predicate=predicate;
        this.object=object;
    }
    public String getSubject(){
        return this.subject;
    }
    public String getPredicate(){
        return this.predicate;
    }
    public String getObject(){
        return this.object;
    }
    public int getIndex(){
        return this.index;
    }
    @Override
    public String toString() {
        return "TableValues  1 " + subject + "2 " + predicate +"3 "+object+"4 "+index+"]\n";
    
    
    }
}
