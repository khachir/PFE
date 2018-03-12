package mvc;
import jbotsim.*;

public class Model {

    int i=0;
    int tab[] = new int[2];


    public boolean LinkIsExist(Topology t, Link l) {

        boolean exist = false;
        for (Link lien : t.getLinks())
            if (lien.equals(l))
                exist = true;
        return exist;

    }



public void addlink(Topology topo,Node node){

    tab[i]=node.getID();
    i++;

    if (i > 1) {
        Link l = new Link(topo.getNodes().get(tab[0]), topo.getNodes().get(tab[1]));
        l.setWidth(4);
        topo.addLink(l);
        i=0;
    }
}

public void removelink(Topology topo,Node node){
    tab[i]=node.getID();
    i++;
      if (i > 1 ){

        Link li = new Link(topo.getNodes().get(tab[0]), topo.getNodes().get(tab[1]));
        if (LinkIsExist(topo, li))
            topo.removeLink(li);
    i=0;

    }


}


public void composante (Topology topo){


    //to do
}


public void convertion(Topology topo){}


//to do
}
