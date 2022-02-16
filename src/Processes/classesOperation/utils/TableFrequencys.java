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
public class TableFrequencys implements Comparable<TableFrequencys> {

    public String path;
    public String freqpath;

    public TableFrequencys(String path, String freqpath) {
        super();
        this.freqpath = freqpath;
        this.path = path;
    }

    @Override
    public int compareTo(TableFrequencys o) {
        return this.getFreqpath().compareTo(o.getFreqpath());
    }

    public String getPath() {
        return this.path;
    }

    public String getFreqpath() {
        return this.freqpath;
    }

    @Override
    public String toString() {
        return "Table [getFreqpath=" + path + ", getFreqpath=" + freqpath + "]\n";
    }
}
