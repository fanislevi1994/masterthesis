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
import java.time.LocalDate;

public class TableValues implements Comparable<TableValues> {

    public int id = 0;
    String decode;
    public String path;
    public String numberpath;

    public TableValues(String path, String numberpath, int id, String decode) {
        super();
        this.numberpath = numberpath;
        this.path = path;
        this.id = id;
        this.decode = decode;
    }

    @Override
    public int compareTo(TableValues o) {
        return this.getNumberPath().compareTo(o.getNumberPath());
    }

    public String getPath() {
        return this.path;
    }

    public String getNumberPath() {
        return this.numberpath;
    }

    public int getId() {
        return this.id;
    }

    public String getDecode() {
        return this.decode;
    }

    @Override
    public String toString() {
        return "TableValueszz [path=" + this.path + ", numberpath=" + this.numberpath + this.decode + " ]\n";
    }
}
