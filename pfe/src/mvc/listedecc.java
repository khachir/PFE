package mvc;

import jbotsim.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class listedecc {
    ArrayList listdeecc;

    public listedecc() {
        this.listdeecc=new ArrayList();
    }


    public void addcctolistdecc(comconex cc){this.listdeecc.add(cc);}


    public ArrayList getListdeecc() {
        return listdeecc;
    }
}
