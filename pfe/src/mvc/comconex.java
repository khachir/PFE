package mvc;

import jbotsim.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class comconex {


    HashSet comc ;

    public comconex() {
         this.comc= new HashSet();
    }

    public void addrouteurtocomc(int r){

        this.comc.add(r);

    }

    public HashSet getComc() {
        return comc;
    }






}
