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
public class Pairs<Key,Value> {
    private Key key;
    private Value value;

    public Pairs(Key key, Value value){
        this.key = key;
        this.value = value;
    }
    public Key getKeys(){ return this.key; }
    public Value getValue(){ return this.value; }
    
    public void setKey(Key key){ this.key = key; }
    public void setValue(Value value){ this.value = value; }
}