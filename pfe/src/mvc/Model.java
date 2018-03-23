package mvc;
import jbotsim.*;

import java.util.*;

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

    public static boolean memetype(Routeur z, Routeur q) {



        if ((z.getType()==4 && q.getType()==4)||(z.getType()==6 && q.getType()==6)){

            return true;}


        else
            return false;

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



    public  ArrayList<Routeur> visitednode = new ArrayList<Routeur>();

    public static    comconex com=new comconex();



    static void parcoursProfondeur(Routeur origine, ArrayList visitednode) {

        visitednode.add(origine);
        com.addrouteurtocomc(origine);
//        System.out.println(origine.getID());
        Iterator m = origine.getNeighbors().iterator();
        while (m.hasNext()) {

            Routeur suivant = (Routeur)m.next();
            if (!visitednode.contains(suivant) && memetype(suivant,origine)) {
                com.addrouteurtocomc(suivant);
                parcoursProfondeur(suivant, visitednode);
            }


        }

System.out.print(com.getComc());

        com.getComc().clear();


    }








    public void composante (Topology topo){


        Iterator p = topo.getNodes().iterator();


        while (p.hasNext()) {
            Routeur s = (Routeur) p.next();
            if (!visitednode.contains(s)) {
                parcoursProfondeur(s, visitednode);
            }
        }



    }






    public void changerderout(Topology topo,Node node){

        if (node instanceof Routeur_ip4)
            System.out.print("hada ip4");


        else if (node instanceof Routeur_ip6)
            System.out.print("hada ip6");


    }



    public void convertion(Topology topo){}


//to do
}
