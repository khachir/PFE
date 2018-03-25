package mvc;

import jbotsim.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class listedecc {
    HashSet listdeecc;

    public listedecc() {
        this.listdeecc=new HashSet();
    }


    public void addcctolistdecc(HashSet cc){this.listdeecc.add(cc);}


    public HashSet getListdeecc() {
        return listdeecc;
    }
}
