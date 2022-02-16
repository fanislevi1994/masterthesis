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
public class TablePaths {

    public int index;
    public int path;

    public TablePaths(int index, int path) {
        super();
        this.index = index;
        this.path = path;
    }

    public int getPath() {
        return this.path;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        return "TableValuesaa [path=" + path + ", numberpath=" + index + "]\n";
    }

}
