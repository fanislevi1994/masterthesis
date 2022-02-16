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
public class TripletValues {

    public String subject;
    public String predicate;
    public String object;

    public TripletValues(String subject, String predicate, String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;

    }

    public String getSubject() {
        return this.subject;
    }

    public String getPredicate() {
        return this.predicate;
    }

    public String getObject() {
        return this.object;
    }

    @Override
    public String toString() {
        return "TripletValueszz [subject=" + this.subject + " predicate=" + this.predicate + " object=" + this.object + " ]\n";
    }

}
