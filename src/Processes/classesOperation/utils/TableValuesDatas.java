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



public class TableValuesDatas implements Comparable<TableValuesDatas>{

    public String path;
    public String numberpath;
    public String freqpath;
    public TableValuesDatas(String path,String numberpath,String freqpath) {
        super();      
        this.numberpath=numberpath;
        this.path=path;
        this.freqpath=freqpath=freqpath;
    }

 
 
    public String getPath(){
        return this.path;
    }
    public String getNumberPath(){
        return this.numberpath;
    }
    public String getFreqPath(){
        return this.freqpath;
    }


   
     @Override
    public int compareTo(TableValuesDatas o) {
        return this.getFreqPath().compareTo(o.getFreqPath());
    }
    
    @Override
    public String toString() {
        return "this.freqpath [path=" + path + ", this.freqpath=" + this.freqpath +"]\n";
    }

  
    
}

